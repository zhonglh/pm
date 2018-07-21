package com.common.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.common.utils.ConstantUtil;
import com.common.utils.StringKit;

public class Pager<T> {

	// 每页多少行记录
	private int pageSize = ConstantUtil.PAGE_SIZE;
	// 记录总数
	private int totalRows = 0;
	// 开始记录数
	private int startRow;
	// 结束记录数
	private int endRow;
	// 总页数
	private int totalPages;
	// 记录集合
	@JSONField(name = "rows")
	private List<T> resultList;
	// 排序方式 asc desc
	private String sortorder = "desc";
	// 排序名称
	private String sortname;
	// 查询过滤条件
	private String query;
	// 查询属性
	private String qtype;

	private List<Condition> condition;

	private int rp = ConstantUtil.PAGE_SIZE; // 每页的条数
	private int pageNo = 1; // 第几页

	// 专门为特殊业务增加的
	private double total_amount;

	// 结果中的对象，比如合计
	private Object resultObj;

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public Pager(int pageNo, int pageSize, List<T> resultList, int totalRows) {
		super();
		this.pageNo = pageNo;
		this.rp = pageSize;
		this.resultList = resultList;
		this.totalRows = totalRows;
	}

	public Pager() {
	}

	public Pager(int pageNo) {
		int v_endrownum = pageNo * pageSize;
		this.startRow = v_endrownum - pageSize + 1;
	}

	public Pager(int pageNo, int pageSize) {
		int v_endrownum = pageNo * pageSize;
		this.startRow = v_endrownum - pageSize + 1;
		if (pageSize != 0)
			this.pageSize = pageSize;
	}

	public int getPageSize() {
		return rp;
	}

	public void setPageSize(int pageSize) {
		this.rp = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public void setCondition(List<Condition> condition) {
		this.condition = condition;
	}

	public List<Condition> getCondition() {
		if (!StringKit.isBlank(qtype)) {
			if (condition == null) {
				condition = new ArrayList<Condition>();
			}
			Condition con = new Condition(qtype, query);
			condition.add(con);
		}
		return condition;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}
	
	

}
