package com.common.daos;

import java.util.List;
import java.util.Map;

import com.common.beans.Pager;

public interface IBatisDao {

    /**
     * jdbc
     */

    public abstract int execSql(String sql);

    public abstract int execSql(String sql,Object[] objs,Object object);
    

    public <T> List<T> query(String sql) ;

    public abstract <T> List<T> query(String sql,Class<T> clazz);

    public abstract <T> List<T> query(String sql,Class<T> clazz,Object object);

    public abstract int insert(String sql);

    public abstract int update(String sql);

    public abstract int delete(String sql);

    public abstract int insert(String sql,Object object);

    public abstract int update(String sql,Object object);

    public abstract int delete(String sql,Object object);

    public abstract int batchInsert(String sql,List<Object[]> objs);

    public abstract int batchUpdate(String sql,List<Object[]> objs);

    public abstract int batchDelete(String sql,List<Object[]> objs);

    public abstract <T> int batchInsert2(String sql,List<T> objs);

    public abstract <T> int batchUpdate2(String sql,List<T> objs);

    public abstract <T> int batchDelete2(String sql,List<T> objs);

    /**
     * 分页
     */
   

    public abstract <T> Pager<T> query4Pager(int pageNo,int pageSize,String selSql,Class<T> clazz,Object ... object);
   
    /**
     * 存储过程调用
     */
    public <K, V> Map<K, V> execProc4Map(String sql,Map<K, V> paramMap);
    public <K, V> List<Map<K, V>> execProc4List(String sql,Map<K, V> paramMap);
    public <K, V,T> List<T> execProc4ListObject(String sql,Map<K, V> paramMap);
    
    public <K,V> void execProc(String sql,Map<K, V> paramMap);
    

    // 查询记录数
    public int query4Int(String sql,Object object);
    

    /**
     * @Title: execSqlFile
     * @Description:执行sql文件
     * @Author: ZhongLiHong
     * @Since: 2014年4月1日上午10:21:38
     * @param sqlPath
     */
    public void execSqlFile(String sqlPath);

}
