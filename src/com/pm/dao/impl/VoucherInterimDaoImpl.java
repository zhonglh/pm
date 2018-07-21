package com.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.common.daos.BatisDao;
import com.pm.dao.IVoucherInterimDao;
import com.pm.domain.business.VoucherInterim;
import com.pm.vo.UserPermit;

@Component
public class VoucherInterimDaoImpl extends BatisDao implements IVoucherInterimDao  {

	@Override
	public int addVoucherInterim(VoucherInterim voucherInterim) {
		String sql = "VoucherInterimMapping.addVoucherInterim";
		return this.insert(sql, voucherInterim);
	}

	@Override
	public int updateVoucherInterim(VoucherInterim voucherInterim) {
		String sql = "VoucherInterimMapping.updateVoucherInterim";
		return this.update(sql, voucherInterim);
	}
	

	@Override
	public void updateVoucherInterimByExport(VoucherInterim voucherInterim) {
		String sql = "VoucherInterimMapping.updateVoucherInterimByExport";
		this.update(sql, voucherInterim);
	}

	@Override
	public void deleteVoucherInterim(VoucherInterim voucherInterim) {
		String sql = "VoucherInterimMapping.deleteVoucherInterim";
		this.delete(sql, voucherInterim);
	}

	@Override
	public VoucherInterim getVoucherInterim(String id) {

		String sql = "VoucherInterimMapping.getVoucherInterim"; 
		VoucherInterim voucherInterim = new VoucherInterim(); 
		voucherInterim.setId(id); 
		List<VoucherInterim> list = this.query(sql, VoucherInterim.class, voucherInterim); 
		if(list == null || list.isEmpty()) return null; 
		else return list.get(0);	
	}

	@Override
	public Pager<VoucherInterim> queryVoucherInterim(
		VoucherInterim voucherInterim,
		UserPermit userPermit,
		Pager<VoucherInterim> pager){

		String sql = "VoucherInterimMapping.queryVoucherInterim"; 
		Pager<VoucherInterim> pager1 =  this.query4Pager(pager.getPageNo(), pager.getPageSize(), sql,VoucherInterim.class, voucherInterim,userPermit); 
		
		return pager1;
	}


}