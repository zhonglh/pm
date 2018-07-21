package com.pm.dao;

import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.vo.UserPermit;

public interface ISalesStatisticsDao {


	public Pager<Statistics> queryByProject(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);	

	public Pager<Statistics> queryBySales(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByManager(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByInfoSource(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByClient(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);
	
	public Pager<Statistics> queryBySalesDept(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByExecDept(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByDept(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByYear(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByQuarter(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);

	public Pager<Statistics> queryByAll(Statistics statistics,UserPermit userPermit,Pager<Statistics> pager);


}
