package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.utils.Base64Kit;
import com.common.utils.file.FileKit;
import com.pm.dao.IContractAttachmentDao;
import com.pm.dao.IPayContractDao;
import com.pm.domain.business.ContractAttachment;
import com.pm.domain.business.PayContract;
import com.pm.service.IPayContractService;
import com.pm.util.PubMethod;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class PayContractServiceImpl implements  IPayContractService  {

	@Autowired IPayContractDao payContractDao;

	@Autowired
	private IContractAttachmentDao contractAttachmentDao;


	@Override
	public  boolean isNoExist(PayContract payContract){
		return payContractDao.isNoExist(payContract);
	}

	@Override
	public int addPayContract(PayContract payContract ,ContractAttachment[] cas) {

		if(cas != null && cas.length > 0){
			for(ContractAttachment ca : cas) {
				contractAttachmentDao.addContractAttachment(ca);
			}
		}

		return payContractDao.addPayContract(payContract);
	}

	@Override
	public int updatePayContract(PayContract payContract , ContractAttachment[] cas) {

		if(cas != null && cas.length > 0){
			for(ContractAttachment ca : cas) {
				contractAttachmentDao.addContractAttachment(ca);
			}
		}

		return payContractDao.updatePayContract(payContract);
	}

	@Override
	public void deletePayContract(PayContract[] payContracts) {
		for(PayContract payContract : payContracts){

			try {
				//删除合同附件
				ContractAttachment search = new ContractAttachment();
				search.setContract_id(payContract.getId());
				List<ContractAttachment> contractAttachments = contractAttachmentDao.queryContractAttachment(search);
				for (ContractAttachment contractAttachment : contractAttachments) {
					if (contractAttachment != null && contractAttachment.getAttachment_id() != null) {
						try {
							FileKit.deleteFile(new String(Base64Kit.decode(contractAttachment.getAttachment_path())));
						} catch (Exception e) {
							e.printStackTrace();
						}
						contractAttachmentDao.deleteContractAttachment(contractAttachment);
					}
				}
			}catch (Exception e){
			}
			payContractDao.deletePayContract(payContract);
		}
	}

	@Override
	public void verifyPayContract(PayContract payContract) {
		payContractDao.verifyPayContract(payContract);
	}

	@Override
	public void unVerify(String id) {
		PayContract payContract = new PayContract();
		payContract.setId(id);
		payContractDao.unVerifyPayContract(payContract);
	}

	@Override
	public PayContract getPayContract(String id) {
		return payContractDao.getPayContract(id);
	}

	@Override
	public Pager<PayContract> queryPayContract(
		PayContract payContract,
		UserPermit userPermit,
		Pager<PayContract> pager){

		Pager<PayContract> pager1 = payContractDao.queryPayContract(payContract, userPermit, pager);


		if(pager1.getResultList() != null){
			for(PayContract contract1 : pager1.getResultList()){
				contract1.setEffectivedate(PubMethod.twoDate2Str(contract1.getValidity_date1(), contract1.getValidity_date2()));
			}
		}

		return pager1;

	}


	@Override
	public <T> T get(String id) {
		return (T)getPayContract(id);
	}
}