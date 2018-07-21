package com.common.pool;

import java.util.List;

public interface ITaskService {

    /**
     * @Title: initTask
     * @Description: 任务初始化
     * @Author: Hongli
     * @Since: 2014年10月30日下午1:39:57
     */
    void initTask();

    /**
     * @Title: mutualExclusionTaskVerification
     * @Description: 互斥任务验证
     * @Author: Hongli
     * @Since: 2014年10月30日下午1:41:06
     * @throws IllegalStateException
     */
    void mutualExclusionTaskVerification(List<BusinessTask> tasks) throws IllegalStateException;

    /**
     * @Title: exclusiveTaskVerification
     * @Description: 排他任务验证
     * @Author: Hongli
     * @Since: 2014年10月30日下午1:42:37
     * @return
     */
    boolean exclusiveTaskVerification(BusinessTask workTask);

    /**
     * @Title: equalsTask
     * @Description: 验证是否相同任务
     * @Author: Hongli
     * @Since: 2014年10月31日上午11:17:01
     * @param task
     * @return
     */
    boolean equalsTask(BusinessTask task);

}
