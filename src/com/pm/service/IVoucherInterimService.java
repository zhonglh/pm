package com.pm.service;

import java.util.List;

import com.common.beans.Pager;
import com.pm.domain.business.VoucherInterim;
import com.pm.domain.system.User;
import com.pm.vo.UserPermit;

public interface IVoucherInterimService {
	
	/**
	 * 处理凭证信息
	 * @param voucherInterim
	 * @return
	 */
	public void batchHandleVoucherInterim(VoucherInterim[] voucherInterims) ;

	public int addVoucherInterim(VoucherInterim voucherInterim) ;

	public int updateVoucherInterim(VoucherInterim voucherInterim) ; 

	public void deleteVoucherInterim(VoucherInterim[] voucherInterims) ;
	public VoucherInterim getVoucherInterim(String id) ;	
	
	
	public void doExport(List<VoucherInterim> list,User user);

	public Pager<VoucherInterim> queryVoucherInterim(VoucherInterim voucherInterim,  UserPermit userPermit,Pager<VoucherInterim> pager);

}