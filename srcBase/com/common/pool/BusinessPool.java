package com.common.pool;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.file.PropertyConfig;

/**
 * @ClassName: ThreadPool
 * @Title:线程池
 * @Description:支持 不同任务排它执行,<br>
 *                 支持排它任务优先级,<br>
 *                 支持线程池满后是否强制执行<br>
 *                 同一目标同一任务可以同时运行几个
 * @Author:Hongli
 * @Since:2014年8月29日下午1:34:28
 * @Version:1.0
 */

/**
 * 对于同步的处理， 可以认为同步的都是强制执行， 任务非两种,目前的可以认为是异步方式， 需要加同步方式的Task类， 再用countdown来控制同步关系
 * @author zhonglh
 *
 */
public final class BusinessPool {

    private final static Logger logger = LoggerFactory.getLogger (BusinessPool.class);

    private static class ThreadPoolHolder {

        static BusinessPool instance = new BusinessPool ();
    }

    public static BusinessPool me(){
        return ThreadPoolHolder.instance;
    }

    private boolean                    isClosed        = false;                        // 线程池是否关闭
    private List<BusinessTask>         workQueue;                                      // 工作队列
    private static final AtomicInteger poolnumber      = new AtomicInteger (1);
    private static final AtomicInteger groupnumber     = new AtomicInteger (1);
    private final AtomicLong           threadNumber    = new AtomicLong (1);
    private String                     namePrefix;
    // private final Object lock = new Object ();
    // 任务调度周期
    private static final int           TASK_QOS_PERIOD = 100000;
    private int                        initnum         = 5;                            // 初始化线程数
    private int                        maxnum          = 20;                           // 最大线程数
    private int                        minnum          = 5;                            // 最小线程数
    private int                        threadidetime   = 1000;                         // 线程空闲时间
    // private int tasktimeout = 1000; // 线程空闲时间
    private ThreadGroup                threadGroup;
    private List<WorkThread>           workThreads     = new LinkedList<WorkThread> ();

    public BusinessPool() {
        loadConfig ();
        setPoolParam ();
        initWorkThread ();
    }

    public BusinessPool(int maxnum, int minnum, int initnum) {
        if (maxnum > 0) this.maxnum = maxnum;
        if (minnum > 0) this.minnum = minnum;
        if (initnum > -1) this.initnum = initnum;
        this.initnum = initnum;
        setPoolParam ();
        initWorkThread ();
    }

    void initWorkThread(){
        workQueue = new LinkedList<BusinessTask> ();
        for ( int i = 0 ; i < this.initnum ; i++ ) {// 初始化常驻线程
            newWorkThread ();
        }
    }

    void loadConfig(){
        PropertyConfig p = new PropertyConfig ();
        p.loadPropertyFile ("buspool.properties");
        this.initnum = p.getPropertyToInt ("pool.initmum");
        this.maxnum = p.getPropertyToInt ("pool.maxnum");
        this.minnum = p.getPropertyToInt ("pool.minnum");
        this.threadidetime = p.getPropertyToInt ("pool.thread.ide.time");
        // this.tasktimeout = p.getPropertyToInt ("pool.task.timeout");

    }

    String getPoolName(){
        return "custom-group".concat ("" + groupnumber.getAndIncrement ());
    }

    void setPoolParam(){
        threadGroup = new ThreadGroup (getPoolName ());
        threadGroup.setDaemon (true);
        this.namePrefix = getClass ().getSimpleName () + "-" + poolnumber.getAndIncrement () + "-thread-";
    }

    /** 创建一个新线程，并增加到工作队列中 */
    private void newWorkThread(){
        synchronized (workThreads) {
            WorkThread wt = new WorkThread ();
            workThreads.add (wt);
            wt.start ();
        }
    }

    /** 向工作队列中加入一个新任务,由工作线程去执行该任务 */
    public synchronized void execute(BusinessTask task){
        if (isClosed) throw new IllegalStateException ("thread pool is closed.");
        if (task != null) {
            task.mutualExclusionTaskVerification (getAllTasks ());
            task.initTask ();
            TaskCondition c = task.getCondition ();
            if (null != c && c.coerce ()) {
                if (getIdeNum () > 0) {
                    workQueue.add (task);
                    this.notify ();// 唤醒一个线程
                } else new WorkThread (true,task).start ();// 执行完消除此线程
            } else {
                workQueue.add (task);
                if (workThreads.size () < workQueue.size ()) {// 当空闲线程为0
                    if (workThreads.size () < maxnum) newWorkThread ();// 当线程个数小于最大线程时创建一个线程
                } else // 当空闲线程大于0时
                this.notify ();// 唤醒一个线程
            }
        }
    }

    /** 为每个线程初始化一个工作队列 */
    private Map<String, List<BusinessTask>> threadTasks = new ConcurrentHashMap<String, List<BusinessTask>> ();

    /** 为每个线程初始化一个工作队列 */
    private List<BusinessTask> initThreadTasks(String name){
        if (!threadTasks.containsKey (name)) threadTasks.put (name, Collections.synchronizedList (new ArrayList<BusinessTask> ()));
        return threadTasks.get (name);
    }

    /** 空闲线程 */
    public int getIdeNum(){
        return workThreads.size () - getRuningNum ();
    }

    /** 移除无效工作线程 */
    private synchronized void removeWorkThread(WorkThread wt){
        workThreads.remove (wt);
        String threadName = Thread.currentThread ().getName ();
        if (threadTasks.containsKey (threadName)) {
            for ( BusinessTask bt : initThreadTasks (threadName) ) {
                execute (bt);
            }
        }
    }

    /** 同一对像，同一任务正在执行的个数 */
    private int getSameWorkTaskTypeNum(BusinessTask task){
        int num = 0;
        Iterator<WorkThread> threads = workThreads.iterator ();
        while (threads.hasNext ()) {
            WorkThread work = threads.next ();
            if (null != work.task) {
                try {
                    if (work.task.equalsTask (task)) num += 1;
                } catch (Exception e) {
                    // logger.warn ("task is finish.");
                }

            }
        }
        return num;
    }

    /** 运行线程 */
    public int getRuningNum(){
        int runingNum = 0;
        Iterator<WorkThread> threads = workThreads.iterator ();
        while (threads.hasNext ()) {
            WorkThread work = threads.next ();
            try {
                if (null != work.task) runingNum += 1;
            } catch (Exception e) {
                // logger.warn ("task is finish.");
            }
        }
        return runingNum;
    }

    /** 排序 */
    private void sort(List<BusinessTask> tasks){
        SortList<BusinessTask> sort = new SortList<BusinessTask> ();
        sort.Sort (tasks, "desc");// 排序
    }

    private boolean isSameTasks(List<BusinessTask> tasks){
        int sameTaskNum = 0;
        if (null != tasks && !tasks.isEmpty ()) {
            BusinessTask firstTask = tasks.get (0);
            for ( BusinessTask baseTask : tasks ) {
                if (baseTask.equalsTask (firstTask)) sameTaskNum += 1;
            }
            if (tasks.size () == sameTaskNum) { return true; }
        }
        return false;
    }

    // 取执行任务，此处需要优化
    private synchronized BusinessTask getTask() throws InterruptedException{
        String threadName = Thread.currentThread ().getName ();
        setExclusiveTaskToWorkIngThread (null);
        sort (workQueue);
        if (threadTasks.containsKey (threadName)) {
            List<BusinessTask> tasks = initThreadTasks (threadName);
            int size = tasks.size ();
            if (size > 0) {
                sort (tasks);
                BusinessTask firstTask = tasks.get (0);
                if (setExclusiveTaskToWorkIngThread (firstTask)) {
                    tasks.remove (firstTask);
                    return null;
                }
                if (size == 1) return tasks.remove (0);
                int workNum = getSameWorkTaskTypeNum (firstTask);
                if (isSameTasks (tasks)) {// 如果线程队列中所有任务为同一个任务
                    Iterator<BusinessTask> bts = tasks.iterator ();
                    while (bts.hasNext ()) {
                        BusinessTask bt = bts.next ();
                        if (0 != firstTask.getParallel () && workNum >= firstTask.getParallel ()) {// 运行线程数大于等于能同时运行的任务
                            if (divisionSameTaskToWorkingThread (bt)) {
                                bts.remove ();
                            }
                        } else {
                            if (!isClosed) {
                                bts.remove ();
                                execute (bt);
                            } else {
                                bts.remove ();
                                return bt;
                            }
                        }
                    }
                } else {// 如果不同说明有排它任务
                    tasks.remove (firstTask);
                    Iterator<BusinessTask> bts = tasks.iterator ();
                    while (bts.hasNext ()) {
                        BusinessTask bt = bts.next ();
                        if (bt.equalsTask (firstTask)) {
                            if (0 != firstTask.getParallel () && workNum == firstTask.getParallel ()) {// 运行线程数大于等于能同时运行的任务
                                if (divisionSameTaskToWorkingThread (bt)) {
                                    bts.remove ();
                                }
                            } else {
                                if (!isClosed) {
                                    bts.remove ();
                                    execute (bt);
                                }
                            }
                        } else {
                            if (!isClosed) {
                                bts.remove ();
                                execute (bt);
                            }
                        }
                    }
                    return firstTask;
                }
            } else {
                threadTasks.remove (threadName);
                return null;
            }
        } else {
            if (workQueue.size () == 0) {
                if (isClosed) return null;
                threadTasks.remove (threadName);
                wait ();
            } else {
                BusinessTask _task = workQueue.get (0);
                if (setExclusiveTaskToWorkIngThread (_task)) {
                    workQueue.remove (_task);
                    return null;
                }
                return workQueue.remove (0);
            }
        }
        return null;
    }

    /**
     * @Title: distributeExclusiveTaskWorkIngThread
     * @Description:分配排他任务到工作线程队列中
     * @Author: ZhongLiHong
     * @Since: 2014年10月31日上午10:43:14
     * @param tasks
     *            待分配的任务
     * @param workTask
     *            工作中的任务
     */
    private void distributeExclusiveTaskWorkIngThread(List<BusinessTask> tasks,BusinessTask workTask,String threadName){
        Iterator<BusinessTask> disTasks = tasks.iterator ();
        while (disTasks.hasNext ()) {
            BusinessTask disTask = disTasks.next ();
            if (disTask.exclusiveTaskVerification (workTask)) {
                initThreadTasks (threadName).add (disTask);
                disTasks.remove ();
            }
        }
        distributeSameTask (tasks, workTask);
    }

    /**
     * @Title: distributeSameTask
     * @Description: 分配同任务
     * @Author: ZhongLiHong
     * @Since: 2014年10月31日下午1:18:28
     * @param tasks
     * @param workTask
     */
    private void distributeSameTask(List<BusinessTask> tasks,BusinessTask workTask){
        Iterator<BusinessTask> disTasks = tasks.iterator ();
        while (disTasks.hasNext ()) {
            BusinessTask disTask = disTasks.next ();
            // 如果是同一个对像，且操作类型也相同，则为同一个线程排队执行
            if (disTask.equalsTask (workTask)) {
                if (disTask.getParallel () > 0) {// 5
                    if (getSameWorkTaskTypeNum (disTask) >= disTask.getParallel ()) {// 运行线程数大于等于能同时运行的任务
                        if (divisionSameTaskToWorkingThread (disTask)) {
                            disTasks.remove ();
                        }
                        continue;
                    }
                }
            }
        }
    }

    /**
     * @Title: setExclusiveTaskToWorkIngThread
     * @Description: 分配排它任务到相应的工作线程队列中
     * @Author: ZhongLiHong
     * @Since: 2014年10月31日上午10:33:16
     * @param task
     *            为空时分配线程池工作队列中的任务，不为空时，分配传入的任务
     * @return
     */
    private boolean setExclusiveTaskToWorkIngThread(BusinessTask task){
        // 先在工作的线程中匹配是否有任务类型排他的
        Iterator<WorkThread> threads = workThreads.iterator ();
        while (threads.hasNext ()) {
            WorkThread work = threads.next ();
            if (work != Thread.currentThread () && null != work.task) {
                try {
                    String threadName = work.getName ();
                    if (null != task) {
                        List<BusinessTask> bts = new ArrayList<BusinessTask> ();
                        bts.add (task);
                        distributeExclusiveTaskWorkIngThread (bts, work.task, threadName);
                        return bts.isEmpty ();
                    } else {
                        distributeExclusiveTaskWorkIngThread (workQueue, work.task, threadName);
                    }

                } catch (Exception e) {}
            }
        }
        return false;
    }

    /** 分配任务到相应的工作线程队列中 */
    private boolean divisionSameTaskToWorkingThread(BusinessTask task){
        List<List<BusinessTask>> list = new ArrayList<List<BusinessTask>> ();
        synchronized (workThreads) {
            Iterator<WorkThread> threads = workThreads.iterator ();
            while (threads.hasNext ()) {
                WorkThread work = threads.next ();
                if (work != Thread.currentThread () && null != work.task) {
                    try {
                        if (task.equalsTask (work.task)) {
                            list.add (initThreadTasks (work.getName ()));
                        }

                    } catch (Exception e) {}
                }
            }
        }

        if (null != list && !list.isEmpty ()) {
            Collections.sort (list, new Comparator<List<BusinessTask>> () {

                @Override
                public int compare(List<BusinessTask> o1,List<BusinessTask> o2){
                    return String.valueOf (o1.size ()).compareTo (String.valueOf (o2.size ()));
                }
            });
            list.get (0).add (task);
            return true;
        } else return false;
    }

    /** 关闭线程池 */
    public void closePool(){
        if (!isClosed) {
            synchronized (this) {
                isClosed = true;
            }
            taskHandler.cancel (true);
            if (isClosed != true) return;
            waitFinish (); // 等待工作线程执行完毕
            workQueue.clear (); // 清空工作队列
            scheduler.shutdownNow ();
            threadGroup.interrupt ();
            threadTasks.clear ();
            workThreads.clear ();
        }
    }

    /** 等待工作线程把所有任务执行完毕 */
    private void waitFinish(){
        synchronized (this) {
            isClosed = true;
            notifyAll ();
        }
        Thread[] threads = new Thread[threadGroup.activeCount ()];
        int count = threadGroup.enumerate (threads);
        for ( int i = 0 ; i < count ; i++ ) {
            try {
                threads[i].join ();
            } catch (InterruptedException ex) {
                ex.printStackTrace ();
            }
        }
    }

    private List<BusinessTask> getAllTasks(){
        List<BusinessTask> list = new ArrayList<BusinessTask> ();
        list.addAll (getWaitTasks ());
        list.addAll (getWorkingTasks ());
        return list;
    }

    /** 获取待分配的任务 */
    public synchronized List<BusinessTask> getWaitTasks(){
        List<BusinessTask> list = new ArrayList<BusinessTask> ();
        if (workQueue.size () > 0) list.addAll (workQueue);
        if (threadTasks.size () > 0) {
            for ( List<BusinessTask> threadTask : threadTasks.values () ) {
                if (threadTask.size () > 0) list.addAll (threadTask);
            }
        }
        return list;
    }

    /** 获正在执行的任务 */
    public synchronized List<BusinessTask> getWorkingTasks(){
        List<BusinessTask> list = new ArrayList<BusinessTask> ();
        for ( WorkThread work : workThreads ) {
            if (null == work || null == work.task || work.getState () == State.TERMINATED) {
                continue;
            } else {
                list.add (work.task);
            }
        }
        return list;
    }

    /**
     * 内部类,工作线程,负责从工作队列中取出任务,并执行
     */
    private class WorkThread extends Thread {

        /** 最后运行时间 */
        private long         lastRunTimes;

        private BusinessTask task;

        private boolean      isCoerce;

        public WorkThread() {
            this (false, null);
        }

        public WorkThread(boolean isCoerce, BusinessTask task) {
            super (threadGroup, namePrefix + threadNumber.getAndIncrement ());
            this.isCoerce = isCoerce;
            this.task = task;
        }

        public long getLastRunTimes(){
            return lastRunTimes;
        }

        public void run(){
            while (!isInterrupted ()) {
                try {
                    lastRunTimes = System.currentTimeMillis ();
                    if (task != null) {
                        task.run ();
                        this.task = null;
                        if (isCoerce) return;
                    } else {
                        synchronized (this) {
                            task = getTask ();
                            if (task != null) {
                                task.run ();
                                this.task = null;
                                if (isClosed == true || isCoerce) {
                                    removeWorkThread (this);
                                    return;
                                }
                            } else {
                                if (isClosed == true) {
                                    removeWorkThread (this);
                                    return;
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    logger.debug ("清除空闲超时线程：" + this.getName ());
                    removeWorkThread (this);
                    return;
                } catch (Throwable e) {
                    this.task = null;
                    logger.error (e.getMessage (), e);
                    if (isClosed == true || isCoerce) {
                        removeWorkThread (this);
                        return;
                    }
                } finally {}

            }
        }
    }

    /*
     * 创建一个调度线程池
     */
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool (1);

    private synchronized void removeIdeTimeoutThreads(){
        int size = workThreads.size ();
        if (size > minnum) {
            Iterator<WorkThread> wroks = workThreads.iterator ();
            while (wroks.hasNext ()) {
                if (size > minnum) {
                    WorkThread wt = wroks.next ();
                    if (null == wt.task && System.currentTimeMillis () - wt.getLastRunTimes () > threadidetime) {
                        wt.interrupt ();
                        wroks.remove ();
                        size--;
                    }
                } else break;
            }
        }
    }

    // 定时清理空闲线程
    private ScheduledFuture<?> taskHandler = scheduler.scheduleAtFixedRate (new Runnable () {

                                               public void run(){
                                                   removeIdeTimeoutThreads ();
                                               }
                                           }, 0, TASK_QOS_PERIOD, TimeUnit.MILLISECONDS);

    /**
     * @Description:排序
     */
    private class SortList<E> {

        public void Sort(List<E> list,final String sort){
            Collections.sort (list, new Comparator<E> () {

                public int compare(Object a,Object b){
                    int ret = 0;
                    TaskCondition c1 = a.getClass ().getAnnotation (TaskCondition.class);
                    TaskCondition c2 = b.getClass ().getAnnotation (TaskCondition.class);
                    if (sort != null && "desc".equals (sort)) // 倒序
                    ret = c2.priority ().name ().compareTo (c1.priority ().name ());
                    else // 正序
                    ret = c1.priority ().name ().compareTo (c2.priority ().name ());
                    return ret;
                }
            });
        }
    }

}