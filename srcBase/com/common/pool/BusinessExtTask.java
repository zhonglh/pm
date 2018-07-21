package com.common.pool;

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
public class BusinessExtTask extends BusinessTask {

    public BusinessExtTask(String targetId) {
        super (targetId);
    }

    public BusinessExtTask(String targetId, String[] exclusiveTargetIds) {
        super (targetId, exclusiveTargetIds);
    }

    @Override
    public void mutualExclusionTaskVerification(List<BusinessTask> tasks) throws IllegalStateException{}

    @Override
    public boolean exclusiveTaskVerification(BusinessTask workTask){
        return false;
    }

}
