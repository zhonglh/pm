package com.common.daos;

import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.beans.Pager;
import com.common.exceptions.CommonErrorConstants;
import com.common.exceptions.PMException;
import com.common.utils.file.FileKit;
import com.common.utils.file.properties.PropertiesUtil;

public class BatisDao extends SqlSessionDaoSupport implements IBatisDao {

    private static final Logger logger = LoggerFactory.getLogger (BatisDao.class);
    private SqlSessionTemplate  sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @PostConstruct
    public void sqlSessionTemplate(){
       
        super.setSqlSessionTemplate (sqlSessionTemplate);
    }

    protected Logger getLog(){
        return logger;
    }

    public void log(String msg){
        this.getLog ().debug (msg);
    }
    

    @Override
    public <T> List<T> query(String sql){
        this.log ("Running sql:" + sql);

        List<T> list = this.getSqlSession ().selectList (sql);
        if (list == null || list.isEmpty ()) return null;
        else return list;
    }

    @Override
    public <T> List<T> query(String sql,Class<T> clazz){
        this.log ("Running sql:" + sql);

        List<T> list = this.getSqlSession ().selectList (sql);
        if (list == null || list.isEmpty ()) return null;
        else return list;
    }

    @Override
    public <T> List<T> query(String sql,Class<T> clazz,Object object){
        this.log ("Running sql:" + sql);
        List<T> list = this.getSqlSession ().selectList (sql, object);
        if (list == null || list.isEmpty ()) return null;
        else return list;
    }

    @Override
    public int insert(String sql){
        this.log ("Running sql:" + sql);
        return this.getSqlSession ().insert (sql);
    }

    @Override
    public int update(String sql){
        this.log ("Running sql:" + sql);
        return this.getSqlSession ().update (sql);
    }

    @Override
    public int delete(String sql){
        this.log ("Running sql:" + sql);
        return this.getSqlSession ().delete (sql);
    }

    @Override
    public int insert(String sql,Object object){
        this.log ("Running sql:" + sql + ", object:" + object);
        return this.getSqlSession ().insert (sql, object);
    }

    @Override
    public int update(String sql,Object object){
        this.log ("Running sql:" + sql + ", object:" + object);
        return this.getSqlSession ().update (sql, object);
    }

    @Override
    public int delete(String sql,Object object){
        this.log ("Running sql:" + sql + ", object:" + object);
        return this.getSqlSession ().delete (sql, object);
    }

    @Override
    public int batchInsert(String sql,List<Object[]> objs){
        this.log ("Running sql:" + sql);
        int count = 0;
        for ( int i = 0 ; i < objs.size () ; i++ )
            count += this.getSqlSession ().insert (sql, objs.get (i));
        return count;
    }

    @Override
    public int batchUpdate(String sql,List<Object[]> objs){
        this.log ("Running sql:" + sql);
        int count = 0;
        for ( int i = 0 ; i < objs.size () ; i++ )
            count += this.getSqlSession ().update (sql, objs.get (i));
        return count;
    }

    @Override
    public int batchDelete(String sql,List<Object[]> objs){
        this.log ("Running sql:" + sql);
        int count = 0;
        for ( int i = 0 ; i < objs.size () ; i++ )
            count += this.getSqlSession ().delete (sql, objs.get (i));
        return count;
    }

    @Override
    public <T> int batchInsert2(String sql,List<T> objs){
        this.log ("Running sql:" + sql);
        int count = 0;
        for ( int i = 0 ; i < objs.size () ; i++ )
            count += this.getSqlSession ().insert (sql, objs.get (i));
        return count;
    }

    @Override
    public <T> int batchUpdate2(String sql,List<T> objs){
        this.log ("Running sql:" + sql);
        int count = 0;
        for ( int i = 0 ; i < objs.size () ; i++ )
            count += this.getSqlSession ().update (sql, objs.get (i));
        return count;
    }

    @Override
    public <T> int batchDelete2(String sql,List<T> objs){
        this.log ("Running sql:" + sql);
        int count = 0;
        for ( int i = 0 ; i < objs.size () ; i++ )
            count += this.getSqlSession ().delete (sql, objs.get (i));
        return count;
    }

  



    @Override
    public <T> Pager<T> query4Pager(int pageNo,int pageSize,String selSql,Class<T> clazz,Object ... objects){

	    	logger.debug("Running sql:"+selSql);
		Map  map = new HashMap();
		for(Object obj : objects){
			if(obj !=null) map.put(obj.getClass().getSimpleName(), obj);
		}
		
		String jdbctype = PropertiesUtil.getValue("jdbc.properties", "jdbcType");
		if(null != jdbctype){
			jdbctype = jdbctype.trim().toLowerCase();
		}
		String strObj1= selSql+"ByPager"+"_"+jdbctype;
		String strObj2= selSql+"ByCount"+"_"+jdbctype;
		
		int v_endrownum = pageNo * pageSize;			//结束行	
		int v_startrownum = v_endrownum - pageSize ; //开始行 若数据库为mysql
		
		if(null != jdbctype && !"".equals(jdbctype) && "oracle".equals(jdbctype)) {//若数据库是oracle
			v_startrownum += 1;
		}
		
		Pager pager = new Pager();
		pager.setStartRow(v_startrownum);
		pager.setEndRow(v_endrownum);
		pager.setPageSize(pageSize);//用于mysql的分页语句
		map.put("page", pager);

		//总行数
		int totalRow = 0;
		Object obj = getSqlSession().selectOne(strObj2,map);
		
		if(obj instanceof Map){
			Object count1 = ((Map)obj).get("COUNT1");
			Object sum1 = ((Map)obj).get("SUM1");			
			if(count1 != null)  totalRow = Integer.parseInt(count1.toString());
			if(sum1 != null) pager.setTotal_amount(Double.parseDouble(sum1.toString()));
		}else {
			totalRow = Integer.parseInt(obj.toString());
		}
		
		
		List resultList = new ArrayList();
		if(totalRow>0) resultList  = getSqlSession().selectList(strObj1, map);
		
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		pager.setTotalRows(totalRow);
		pager.setTotalPages((int)Math.ceil((double)totalRow / (double)pageSize) );
		pager.setResultList(resultList);
		
		return pager;	    
    }

    @Override
    public <K, V> Map<K, V> execProc4Map(String sql,Map<K, V> paramMap){
        this.log ("Running sql:" + sql);
        Map<K, V> mp = this.getSqlSession ().selectOne (sql, paramMap);
        this.getSqlSession ().clearCache (); // 防止存储过程导致数据改变，mybatis仍从缓存中取
        if (mp == null) mp = paramMap;
        else mp.putAll (paramMap);
        return mp;
    }
    

    public <K, V> List<Map<K, V>> execProc4List(String sql,Map<K, V> paramMap){
    	this.log ("Running sql:" + sql);
        List<Map<K, V>> mp = this.getSqlSession ().selectList (sql, paramMap);
        this.getSqlSession ().clearCache (); // 防止存储过程导致数据改变，mybatis仍从缓存中取
        return mp;
    }
    
    public <K, V,T> List<T> execProc4ListObject(String sql,Map<K, V> paramMap){

    	this.log ("Running sql:" + sql);
        List<T> mp = this.getSqlSession ().selectList(sql, paramMap);
        this.getSqlSession ().clearCache (); // 防止存储过程导致数据改变，mybatis仍从缓存中取
        return mp;    
    }

    public <K, V> void execProc(String sql,Map<K, V> paramMap){
        this.log ("Running sql:" + sql);
        this.getSqlSession ().update (sql, paramMap);
        this.getSqlSession ().clearCache (); // 防止存储过程导致数据改变，mybatis仍从缓存中取
    }

    @Override
    public int query4Int(String sql,Object object){
        this.log ("Running sql:" + sql);
        try{
	        int s = (Integer) this.getSqlSession ().selectOne (sql, object);
	        this.getSqlSession ().clearCache (); // 防止mybatis仍从缓存中取;
	        return s;
        }catch(Exception e){
        	return 1;
        }
    }

    @Override
    public int execSql(String sql){
        if (sql.indexOf ("create") > -1 || sql.indexOf ("insert") > -1) {
            getSqlSession ().insert (sql);
            return 1;
        } else if (sql.indexOf ("update") > -1) {
            return getSqlSession ().update (sql);
        } else {
            return getSqlSession ().delete (sql);
        }
    }

    @Override
    public int execSql(String sql,Object[] objs,Object object){
        if (sql.indexOf ("create") > -1 || sql.indexOf ("insert") > -1) {
            getSqlSession ().insert (sql, object);
            return 1;
        } else if (sql.indexOf ("update") > -1) {
            return getSqlSession ().update (sql, object);
        } else {
            return getSqlSession ().delete (sql, object);
        }
    }

    public void execSqlFile(String sqlPath){
        PrintWriter pw = null;
        Connection conn = null;
        String pwname = "./" + UUID.randomUUID ().toString ();
        FileReader reader = null;
        try {
            SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession ();
            conn = SqlSessionUtils.getSqlSession (st.getSqlSessionFactory (), st.getExecutorType (), st.getPersistenceExceptionTranslator ()).getConnection ();
            conn.setAutoCommit (true);
            ScriptRunner runner = new ScriptRunner (conn);
            runner.setEscapeProcessing (true);
            pw = new PrintWriter (pwname);
            runner.setErrorLogWriter (pw);
            runner.setAutoCommit (true);
            reader = new FileReader (sqlPath);
            runner.runScript (reader);
        } catch (Exception e) {
            logger.error ("sql文件执行失败", e);
            throw new PMException (CommonErrorConstants.e223000,e);
        } finally {
            try {
                if (null != pw) pw.close ();
                if (null != conn) conn.close ();
                if (null != reader) reader.close ();
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }
        String err = FileKit.getString4File (pwname, "utf-8").trim ();
        try {
            FileKit.deleteFile (pwname);
        } catch (Exception e) {
            logger.error (e.getMessage (), e);
        }
        if (StringUtils.isNotBlank (err)) {
            logger.error (err);
            throw new PMException (CommonErrorConstants.e999999.getCode (),err);
        }
    }
}
