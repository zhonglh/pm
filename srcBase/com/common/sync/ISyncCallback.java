package com.common.sync;

public interface ISyncCallback {

    /**
     * @Title: isLocked
     * @Description: 当被锁时，执行此方法<br>
     *               此方法返回false，不执行run方法，<br>
     *               当方法返回true时等待锁执行run方法
     * @Author: Hongli
     * @Since: 2014年11月5日下午3:24:35
     * @return
     */
    boolean isLocked();

    /**
     * @Title: run
     * @Description: 同步执行方法主体
     * @Author: Hongli
     * @Since: 2014年11月5日下午3:25:10
     */
    void run();
}
