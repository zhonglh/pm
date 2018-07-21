package com.common.pool;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Target(value = { ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskCondition {

    /** 优先级,等级越高，优先执行 */
    public TaskPriority priority( ) default TaskPriority.P0;

    /** 排它任务 */
    public Class<? extends BusinessTask>[] exclusiveTask( ) default {};

    /** 互斥任务 */
    public Class<? extends BusinessTask>[] mutualExclusionTask( ) default {};

    /** 线程池满时是否强制执行 */
    public boolean coerce( ) default false;

    /** 同时运行个数，这里指相同目标，相同任务类型 */
    public int parallel( ) default 0;

}
