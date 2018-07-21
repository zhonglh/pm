package com.common.sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class LockKit {

    private static class LockKitHolder {

        static LockKit instance = new LockKit ();
    }

    public static LockKit me(){
        return LockKitHolder.instance;
    }

    private LockKit() {}

    private Map<String, LockInfo> locks = new HashMap<String, LockInfo> ();

    /**
     * @Title: getLock
     * @Description: 获取一个lock
     * @Author: Hongli
     * @Since: 2014年11月3日下午4:33:43
     * @param type
     * @param id
     * @return
     */
    public Lock getLock(LockType type,String id){
        return getLock (type, id, true);
    }

    private Lock l = new ReentrantLock ();

    private Lock getLock(LockType type,String id,boolean isCount){
        l.lock ();
        try {
            String lockType = type.getType () + id;
            if (locks.containsKey (lockType)) {
                LockInfo lockinfo = locks.get (lockType);
                if (isCount) lockinfo.countjj ();
                return lockinfo.getLock ();
            } else {
                LockInfo newLock = new LockInfo (new ReentrantLock (),lockType);
                if (isCount) newLock.countjj ();
                locks.put (lockType, newLock);
                return newLock.getLock ();
            }
        } finally {
            l.unlock ();
        }

    }

    /**
     * @Title: remove
     * @Description: 移除锁对像
     * @Author: Hongli
     * @Since: 2014年11月3日下午4:50:18
     * @param type
     * @param id
     */
    public void remove(LockType type,String id){
        l.lock ();
        try {
            String lockType = type.getType () + id;
            ReentrantLock lock = null;
            if (locks.containsKey (lockType)) {
                LockInfo lockinfo = locks.get (lockType);
                lockinfo.countJJ ();
                lock = (ReentrantLock) lockinfo.getLock ();
                if (!lock.isLocked () && lockinfo.getCount () == 0) {
                    locks.remove (lockType);
                }
            }
        } finally {
            l.unlock ();
        }
    }

    public void lock(LockType type,String id){
        getLock (type, id, true).lock ();
    }

    /**
     * @Title: sync
     * @Description: 同步执行
     * @Author: Hongli
     * @Since: 2014年11月3日下午5:11:52
     * @param type
     * @param id
     * @param iCallback
     */
    public void sync(LockType type,String id,ISyncCallback iCallback){
        ReentrantLock lock = (ReentrantLock) getLock (type, id, true);
        if (lock.isLocked ()) {
            if (iCallback.isLocked ()) {
                lock.lock ();
                try {
                    iCallback.run ();
                } catch (Exception e) {
                    throw e;
                } finally {
                    unlock (type, id);
                }
            } else {
                remove (type, id);
            }
        } else {
            lock.lock ();
            try {
                iCallback.run ();
            } catch (Exception e) {
                throw e;
            } finally {
                unlock (type, id);
            }
        }
    }

    public void unlock(LockType type,String id){
        getLock (type, id, false).unlock ();
        remove (type, id);
    }

    /**
     * @ClassName: LockInfo
     * @Title:
     * @Description:锁信息
     * @Author:Hongli
     * @Since:2014年11月3日下午4:50:35
     * @Version:1.0
     */
    class LockInfo {

        private Lock          lock;    // 锁对像
        private AtomicInteger count;   // 锁被获取次数
        private String        lockType; // 锁类型

        public LockInfo(Lock lock, String lockType) {
            super ();
            this.lock = lock;
            this.count = new AtomicInteger (0);
            this.lockType = lockType;
        }

        public Lock getLock(){
            return lock;
        }

        public int getCount(){
            return count.get ();
        }

        /**
         * @Title: countjj
         * @Description: count++
         * @Author: Hongli
         * @Since: 2014年11月3日下午4:51:19
         * @return
         */
        public int countjj(){
            return count.getAndIncrement ();
        }

        /**
         * @Title: countJJ
         * @Description: count--
         * @Author: Hongli
         * @Since: 2014年11月3日下午4:51:30
         * @return
         */
        public int countJJ(){
            return count.getAndDecrement ();
        }

        public String getLockType(){
            return lockType;
        }

    }

}
