package com.pm.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.beans.Pager;
import com.pm.dao.IVoucherInterimDao;
import com.pm.domain.business.VoucherInterim;
import com.pm.domain.system.User;
import com.pm.service.IVoucherInterimService;
import com.pm.util.PubMethod;
import com.pm.vo.UserPermit;

@Component
public class VoucherInterimServiceImpl implements  IVoucherInterimService {

	@Autowired IVoucherInterimDao voucherInterimDao;
	
	

	/**
	 * 处理凭证信息
	 * @param voucherInterims
	 * @return
	 */
	@Override
	public void batchHandleVoucherInterim(VoucherInterim[] voucherInterims) {
		if(voucherInterims == null || voucherInterims.length == 0) {
			return ;
		}
		VoucherInterim search = new VoucherInterim();
		search.setData_type(voucherInterims[0].getData_type());
		search.setData_id(voucherInterims[0].getData_id());
		search.setExported("0");
		Pager<VoucherInterim> pager = voucherInterimDao.queryVoucherInterim(search, null, PubMethod.getPagerByAll(VoucherInterim.class));
		if(pager.getResultList() == null || pager.getResultList().size() == 0){
			for(VoucherInterim voucherInterim : voucherInterims){

				voucherInterim.setTotal_money(PubMethod.getNumberFormatByDouble(voucherInterim.getTotal_money()) );
				voucherInterim.setDebit_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getDebit_amount()) );
				voucherInterim.setLoan_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getLoan_amount()) );
				voucherInterim.setCash_flow_debit_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getCash_flow_debit_amount()) );;
				voucherInterim.setCash_flow_loan_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getCash_flow_loan_amount()) );
				
				voucherInterimDao.addVoucherInterim(voucherInterim);
			}
		}else {
			if(pager.getResultList().size() != voucherInterims.length){
				;
			}else {
				boolean allZero = true;
				Map<String, VoucherInterim> map = new HashMap<String, VoucherInterim>();
				for(VoucherInterim voucherInterim : voucherInterims ){
					map.put(voucherInterim.getCourse_code(), voucherInterim);
				}
				
				for(VoucherInterim voucherInterim : pager.getResultList()){
					VoucherInterim temp = map.get(voucherInterim.getCourse_code());
					if(temp == null){
						String key = "600100100101";
						if(voucherInterim.getCourse_code().equals("600100100101")){
							key = "600100100201";
						}
						temp = map.get(key);
						voucherInterim.setCourse_code(key);
					}
					voucherInterim.setTotal_money(voucherInterim.getTotal_money() + temp.getTotal_money());
					voucherInterim.setDebit_amount(voucherInterim.getDebit_amount() + temp.getDebit_amount());
					voucherInterim.setLoan_amount(voucherInterim.getLoan_amount() + temp.getLoan_amount());
					voucherInterim.setCash_flow_debit_amount(voucherInterim.getCash_flow_debit_amount() + temp.getCash_flow_debit_amount());
					voucherInterim.setCash_flow_loan_amount(voucherInterim.getCash_flow_loan_amount() + temp.getCash_flow_loan_amount());
					
					voucherInterim.setTotal_money(PubMethod.getNumberFormatByDouble(voucherInterim.getTotal_money()) );
					voucherInterim.setDebit_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getDebit_amount()) );
					voucherInterim.setLoan_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getLoan_amount()) );
					voucherInterim.setCash_flow_debit_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getCash_flow_debit_amount()) );;
					voucherInterim.setCash_flow_loan_amount(PubMethod.getNumberFormatByDouble(voucherInterim.getCash_flow_loan_amount()) );
					
					
					if(voucherInterim.getTotal_money() != 0 ) allZero = false;
				}
				
				if(allZero == true) {
					voucherInterimDao.deleteVoucherInterim(pager.getResultList().get(0));
				}else {
					for(VoucherInterim voucherInterim : pager.getResultList()){
						voucherInterimDao.updateVoucherInterim(voucherInterim);
					}
				}
			}
		}
	}
	
	
	@Override
	public int addVoucherInterim(VoucherInterim voucherInterim) {
		return voucherInterimDao.addVoucherInterim(voucherInterim);
	}

	@Override
	public int updateVoucherInterim(VoucherInterim voucherInterim) {
		return voucherInterimDao.updateVoucherInterim(voucherInterim);
	}

	@Override
	public void deleteVoucherInterim(VoucherInterim[] voucherInterims) {
		for(VoucherInterim voucherInterim : voucherInterims){
			voucherInterimDao.deleteVoucherInterim(voucherInterim);
		}
	}


	@Override
	public VoucherInterim getVoucherInterim(String id) {
		return voucherInterimDao.getVoucherInterim(id);
	}
	

	@Override
	public void doExport(List<VoucherInterim> list, User user){
		if(list == null || list.isEmpty()) {
			return ;
		}
		Date date = new Date(System.currentTimeMillis());
		for(VoucherInterim voucherInterim : list){
			voucherInterim.setExported("1");
			voucherInterim.setExported_time(date);
			voucherInterim.setExported_userid(user.getUser_id());
			voucherInterim.setExported_username(user.getUser_name());
			voucherInterimDao.updateVoucherInterimByExport(voucherInterim);
		}
	}

	@Override
	public Pager<VoucherInterim> queryVoucherInterim(
		VoucherInterim voucherInterim,
		UserPermit userPermit,
		Pager<VoucherInterim> pager){

		return voucherInterimDao.queryVoucherInterim(voucherInterim, userPermit, pager);
	}


}