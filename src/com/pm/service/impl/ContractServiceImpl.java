package com.pm.service.impl;

import com.common.beans.Pager;
import com.common.utils.Base64Kit;
import com.common.utils.file.FileKit;
import com.pm.dao.IContractAttachmentDao;
import com.pm.dao.IContractDao;
import com.pm.domain.business.Contract;
import com.pm.domain.business.ContractAttachment;
import com.pm.service.IContractService;
import com.pm.util.PubMethod;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContractServiceImpl implements  IContractService {

	@Autowired
	private IContractDao contractDao;

	@Autowired
	private IContractAttachmentDao  contractAttachmentDao;


	@Override
	public boolean isContractNoExist(Contract contract) {
		return contractDao.isContractNoExist(contract);
	}
	
	@Override
	public int addContract(Contract contract,ContractAttachment[] cas) {

		if(cas != null && cas.length > 0){
			for(ContractAttachment ca : cas) {
				contractAttachmentDao.addContractAttachment(ca);
			}
		}

		return contractDao.addContract(contract);
	}

	@Override
	public int updateContract(Contract contract , ContractAttachment[] cas) {


		if(cas != null && cas.length > 0){
			for(ContractAttachment ca : cas) {
				contractAttachmentDao.addContractAttachment(ca);
			}
		}

		return contractDao.updateContract(contract);
	}

	@Override
	public void deleteContract(Contract[] contracts) {
		for(Contract contract : contracts){

			try {
				//删除合同附件
				ContractAttachment search = new ContractAttachment();
				search.setContract_id(contract.getId());
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

			contractDao.deleteContract(contract);
		}
	}


	@Override
	public Contract getContract(String id) {
		Contract contract = contractDao.getContract(id);
		contract.setEffectivedate(PubMethod.twoDate2Str(contract.getValidity_date1(), contract.getValidity_date2()));
		return contract;
	}
	
	@Override
	public <T> T get(String id) {
		return (T)getContract(id);
	}

	@Override
	public Pager<Contract> queryContract(
		Contract contract,
		UserPermit userPermit,
		Pager<Contract> pager){

		Pager<Contract> pager1 =  contractDao.queryContract(contract, userPermit, pager);
		
		if(pager1.getResultList() != null){
			for(Contract contract1 : pager1.getResultList()){
				contract1.setEffectivedate(PubMethod.twoDate2Str(contract1.getValidity_date1(), contract1.getValidity_date2()));
			}
		}
		
		return pager1;
	}


}