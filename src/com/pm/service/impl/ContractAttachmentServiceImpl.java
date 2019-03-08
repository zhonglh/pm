package com.pm.service.impl;

import com.pm.dao.IContractAttachmentDao;
import com.pm.domain.business.ContractAttachment;
import com.pm.service.IContractAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class ContractAttachmentServiceImpl implements IContractAttachmentService {

    @Autowired
    private IContractAttachmentDao contractAttachmentDao;

    @Override
    public List<ContractAttachment> queryContractAttachment(ContractAttachment contractAttachment) {
        return contractAttachmentDao.queryContractAttachment(contractAttachment);
    }

    @Override
    public ContractAttachment getContractAttachment(ContractAttachment contractAttachment) {
        return contractAttachmentDao.getContractAttachment(contractAttachment);
    }

    @Override
    public void addContractAttachment(ContractAttachment contractAttachment) {
        contractAttachmentDao.addContractAttachment(contractAttachment);
    }

    @Override
    public void deleteContractAttachment(ContractAttachment contractAttachment) {
         contractAttachmentDao.deleteContractAttachment(contractAttachment);
    }
}
