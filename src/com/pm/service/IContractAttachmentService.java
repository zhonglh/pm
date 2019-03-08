package com.pm.service;

import com.pm.domain.business.ContractAttachment;

import java.util.List;

/**
 * 合同附件
 * @author Administrator
 */
public interface IContractAttachmentService {


    public List<ContractAttachment> queryContractAttachment(ContractAttachment contractAttachment);

    public ContractAttachment getContractAttachment(ContractAttachment contractAttachment);

    public void addContractAttachment(ContractAttachment contractAttachment);

    public void deleteContractAttachment(ContractAttachment contractAttachment);



}
