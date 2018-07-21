package com.common.pool;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BusinessTask
 * @Title:
 * @Description:任务基类，所有子类都默认使用了注解
 * @Author:Hongli
 * @Since:2014年9月12日下午2:39:04
 * @Version:1.0
 */
@TaskCondition
public class BusinessTask extends Thread implements ITaskService, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Fields targetId : 操作的目标ID，主要用于排它任务判断
     */
    private String            targetId;

    /**
     * @Fields exclusiveIds : 其它排他目标id
     */
    private String[]          exclusiveTargetIds;

    public String getTargetId(){
        return targetId;
    }

    public String[] getExclusiveTargetIds(){
        return exclusiveTargetIds;
    }

    /**
     * @param targetId
     *            操作的目标ID，主要用于排它任务判断
     */
    public BusinessTask(String targetId) {
        this (targetId, null);
    }

    /**
     * @param targetId操作的目标ID
     *            ，主要用于排它任务判断
     * @param exclusiveTargetIds
     *            其它排他目标id
     */
    public BusinessTask(String targetId, String[] exclusiveTargetIds) {
        if (null == targetId) targetId = "";
        this.targetId = targetId;
        if (null == exclusiveTargetIds) exclusiveTargetIds = new String[] {};
        this.exclusiveTargetIds = exclusiveTargetIds;
    }

    protected TaskCondition getCondition(){
        return getClass ().getAnnotation (TaskCondition.class);
    }

    protected String getClassName(){
        return getClass ().getName ();
    }

    /**
     * @Title: getExclusiveTask
     * @Description: 取排它任务
     * @Author: Hongli
     * @Since: 2014年10月31日上午10:27:10
     * @return
     */
    protected Class<? extends BusinessTask>[] getExclusiveTask(){
        return getCondition ().exclusiveTask ();
    }

    /**
     * @Title: getMutualExclusionTask
     * @Description: 取互斥任务
     * @Author: Hongli
     * @Since: 2014年10月31日上午10:26:50
     * @return
     */
    protected Class<? extends BusinessTask>[] getMutualExclusionTask(){
        return getCondition ().mutualExclusionTask ();
    }

    /**
     * @Title: getTaskPriority
     * @Description: 取任务优先级
     * @Author: Hongli
     * @Since: 2014年10月31日上午10:29:52
     * @return
     */
    protected TaskPriority getTaskPriority(){
        return getCondition ().priority ();
    }

    /**
     * @Title: getParallel
     * @Description: 取任务可同时运行个数
     * @Author: Hongli
     * @Since: 2014年10月31日上午10:36:59
     * @return
     */
    protected int getParallel(){
        return getCondition ().parallel ();
    }

    /**
     * @Title: getCoerce
     * @Description: 是否立即执行，默认false
     * @Author: Hongli
     * @Since: 2014年10月31日上午10:30:36
     * @return
     */
    protected boolean getCoerce(){
        return getCondition ().coerce ();
    }

    @Override
    public void initTask(){

    }

    /**
     * <p>
     * Title: mutualExclusionTaskVerification
     * </p>
     * <p>
     * Description: 互斥任务验证
     * </p>
     * 
     * @param tasks
     * @throws IllegalStateException
     * @see com.teamsun.pd.intf.pool.ITaskService#mutualExclusionTaskVerification(java.util.List)
     */
    @Override
    public void mutualExclusionTaskVerification(List<BusinessTask> tasks) throws IllegalStateException{
        if (null == tasks || tasks.isEmpty ()) return;
        Class<? extends BusinessTask>[] mutualExclusionTasks = getMutualExclusionTask ();
        if (mutualExclusionTasks.length > 0) {
            for ( Class<? extends BusinessTask> mutualExclusionTask : mutualExclusionTasks ) {
                for ( BusinessTask task : tasks ) {
                    // if(task.equals (this))continue;
                    if (null != task) {// 当前task互斥名单和已有task名匹配
                        if (mutualExclusionTask.getName ().equals (task.getClassName ())) {
                            // 相同的目标
                            if (equalsTarget (task)) throw new IllegalStateException ("The current task with the running task conflict.");
                        }
                        Class<? extends BusinessTask>[] workMutualExclusionTasks = task.getMutualExclusionTask ();
                        for ( Class<? extends BusinessTask> workTask : workMutualExclusionTasks ) {
                            if (workTask.getName ().equals (getClassName ())) {
                                if (equalsTarget (task)) throw new IllegalStateException ("The current task with the running task conflict.");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * <p>
     * Title: exclusiveTaskVerification
     * </p>
     * <p>
     * Description: 排他任务验证
     * </p>
     * 
     * @param workTask
     * @return
     * @see com.teamsun.pd.intf.pool.ITaskService#exclusiveTaskVerification(com.teamsun.pd.intf.pool.BusinessTask)
     */
    @Override
    public boolean exclusiveTaskVerification(BusinessTask workTask){
        for ( Class<? extends BusinessTask> type : workTask.getExclusiveTask () ) {
            if (type.getName ().equals (getClassName ()) && isExclusive (workTask)) {// 任类型相同，并且操作的是同一个对像
                return true;
            }
        }
        return false;
    }
    
    
    public static void main(String[] args){
	    System.out.println(false && false);
    }

    protected final boolean isExclusive(BusinessTask workTask){
        boolean isSameClass = true;
        String workTargetId = workTask.getTargetId ();
        String[] workExclusiveTargetIds = workTask.getExclusiveTargetIds ();
        if ("".equals (targetId)) {
            if (exclusiveTargetIds.length == 0) return isSameClass && false;
            else {
                for ( String exclusiveTargetId : exclusiveTargetIds ) {
                    if (null != exclusiveTargetId && !"".equals (exclusiveTargetId) && exclusiveTargetId.equals (workTargetId)) { return isSameClass && true; }
                }
            }
        } else {
            if (targetId.equals (workTargetId)) return isSameClass && true;
            else {
                if (workExclusiveTargetIds.length > 0) {
                    for ( String otherId : workExclusiveTargetIds ) {
                        if (null != otherId && !"".equals (otherId) && otherId.equals (targetId)) { return isSameClass && true; }
                    }
                }
                if ("".equals (targetId)) return isSameClass && false;
                else {
                    if (workExclusiveTargetIds.length > 0) for ( String workExclusiveTargetId : workExclusiveTargetIds ) {
                        if (null != workExclusiveTargetId && !"".equals (workExclusiveTargetId) && workExclusiveTargetId.equals (targetId)) { return isSameClass && true; }
                    }
                }
            }
        }
        return isSameClass && false;
    }

    public boolean equalsTarget(BusinessTask task){
        if (!"".equals (task.getTargetId ()) && !"".equals (getTargetId ()) && task.getTargetId ().equals (getTargetId ())) return true;
        return false;
    }

    @Override
    public boolean equalsTask(BusinessTask task){
        if (task.getClassName ().equals (getClassName ()) && equalsTarget (task)) return true;
        return false;
    }
}
