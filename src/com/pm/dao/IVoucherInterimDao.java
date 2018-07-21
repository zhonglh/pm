package com.pm.dao;

import com.pm.domain.business.VoucherInterim;
import com.pm.vo.UserPermit;
import com.common.beans.Pager;

public interface IVoucherInterimDao {

	public int addVoucherInterim(VoucherInterim voucherInterim) ;

	public int updateVoucherInterim(VoucherInterim voucherInterim) ; 
	public void updateVoucherInterimByExport(VoucherInterim voucherInterim) ; 

	public void deleteVoucherInterim(VoucherInterim voucherInterim) ;


	public VoucherInterim getVoucherInterim(String id) ;	

	public Pager<VoucherInterim> queryVoucherInterim(VoucherInterim voucherInterim,  UserPermit userPermit,Pager<VoucherInterim> pager);

}