package com.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;



/**
 * 线程池服务类
 * 
 * @author luocheng
 */
public class ThreadPoolService {
    /**
     * 默认线程池大小
     */
    public static final int  DEFAULT_POOL_SIZE    = 30;

    /**
     * 默认一个任务的超时时间，单位为毫秒
     */
    public static final long DEFAULT_TASK_TIMEOUT = -1;

    private int              poolSize             = DEFAULT_POOL_SIZE;
    private ExecutorService  executorService;
    

	final static Logger logger = Logger.getLogger(ThreadPoolService.class);
    
    public ThreadPoolService(){
    	setPoolSize(DEFAULT_POOL_SIZE);
    }
    
    /**
     * 根据给定大小创建线程池
     * @param poolSize 池大小
     */
    public ThreadPoolService(int poolSize) {
        setPoolSize(poolSize);
    }

    /**
     * 使用线程池中的线程来执行任务
     */
    public void execute(Runnable task) {
        executorService.execute(task);
    }

    /**
     * 在线程池中执行所有给定的任务并取回运行结果，使用默认超时时间
     * 
     * @see #invokeAll(List, long)
     */
    //public List<Future<Object>> invokeAll(List<Runnable> tasks) {
    //    return executorService.invokeAll(tasks, TimeUnit.MILLISECONDS);
    //}


    /**
     * 关闭当前ExecutorService
     * 
     * @param timeout 以毫秒为单位的超时时间
     */
    public void destroyExecutorService(long timeout) {
        if (executorService != null && !executorService.isShutdown()) {
            try {
                executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
    			e.printStackTrace();
            }
            executorService.shutdown();
        }
    }
    /**
     * 关闭当前ExecutorService
     */
    public void destroyExecutorService() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    /**
     * 关闭当前ExecutorService，随后根据poolSize创建新的ExecutorService
     */
    public void createExecutorService() {
        destroyExecutorService(1000);
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    /**
     * 调整线程池大小
     * @see #createExecutorService()
     */
    public void setPoolSize(int poolSize) {
    	poolSize = poolSize == 0 ? 1 :poolSize;
        this.poolSize = poolSize;
        createExecutorService();
    }
    
    


}