package com.pm.dao;

import com.pm.domain.business.ContractAttachment;

import java.util.List;

public interface IContractAttachmentDao {




    public List<ContractAttachment> queryContractAttachment(ContractAttachment contractAttachment);

    public ContractAttachment getContractAttachment(ContractAttachment contractAttachment);

    public void addContractAttachment(ContractAttachment contractAttachment);

    public void deleteContractAttachment(ContractAttachment contractAttachment);
}
