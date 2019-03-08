package com.pm.dao.impl;

import com.common.daos.BatisDao;
import com.pm.dao.IContractAttachmentDao;
import com.pm.domain.business.Contract;
import com.pm.domain.business.ContractAttachment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class ContractAttachmentDaoImpl extends BatisDao implements IContractAttachmentDao {

    @Override
    public List<ContractAttachment> queryContractAttachment(ContractAttachment contractAttachment) {

        String sql = "ContractAttachmentMapping.queryContractAttachment";
        return  this.query(sql, ContractAttachment.class, contractAttachment);
    }

    @Override
    public ContractAttachment getContractAttachment(ContractAttachment contractAttachment) {

        String sql = "ContractAttachmentMapping.getContractAttachment";
        List<ContractAttachment> cas =  this.query(sql, ContractAttachment.class, contractAttachment);
        if(cas != null && !cas.isEmpty()){
            return cas.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void addContractAttachment(ContractAttachment contractAttachment) {

        String sql = "ContractAttachmentMapping.addContractAttachment";
        this.insert(sql, contractAttachment);
    }

    @Override
    public void deleteContractAttachment(ContractAttachment contractAttachment) {

        String sql = "ContractAttachmentMapping.deleteContractAttachment";
        this.delete(sql, contractAttachment);
    }
}
