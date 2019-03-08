package com.pm.action;

import com.common.actions.BaseAction;
import com.common.utils.Base64Kit;
import com.common.utils.IDKit;
import com.common.utils.file.FileKit;
import com.pm.domain.business.ContractAttachment;
import com.pm.domain.business.ProjectContract;
import com.pm.service.IContractAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * 合同附件处理
 * @author Administrator
 */
@Controller
@RequestMapping(value = "ContractAttachmentAction.do")
public class ContractAttachmentAction extends BaseAction {

    @Autowired
    private IContractAttachmentService contractAttachmentService;




    @RequestMapping(params = "method=toUplodad")
    public String toUplodad(ContractAttachment contractAttachment, HttpServletResponse res, HttpServletRequest request){
        return "basicdata/contract_attachment_upload";
    }


    @RequestMapping(params = "method=uploadProjectAttach")
    public String uploadProjectAttach(@RequestParam("image") MultipartFile file, ContractAttachment contractAttachment, HttpServletResponse res, HttpServletRequest request) throws Exception{
        String id = IDKit.generateId();
        String folderPath = FileKit.getContractAttachDir(contractAttachment.getContract_id()).toString();

        File folder1 = new File(folderPath);
        if (!folder1.exists()) {
            folder1.mkdirs();
            folderPath = folder1.getPath();
        }

        String filePath = folderPath + File.separatorChar + id;
        file.transferTo(new File(filePath));

        contractAttachment.setAttachment_id(id);
        contractAttachment.setAttachment_name(file.getOriginalFilename());
        contractAttachment.setAttachment_path(Base64Kit.encode(filePath.getBytes()) );

        request.setAttribute("contractAttachment1", contractAttachment);

        return "basicdata/contract_attachment_detail";
    }


    @RequestMapping(params = "method=deleteContractAttachment")
    public String deleteContractAttachment(ContractAttachment searchContractAttachment,HttpServletResponse res,HttpServletRequest request){
        ContractAttachment contractAttachment = contractAttachmentService.getContractAttachment(searchContractAttachment);
        if(contractAttachment != null && contractAttachment.getAttachment_id() != null){
            try{
                FileKit.deleteFile(new String(Base64Kit.decode(contractAttachment.getAttachment_path())));
            }catch(Exception e){
                e.printStackTrace();
            }
            contractAttachmentService.deleteContractAttachment(contractAttachment);
        }


        request.setAttribute("rownum", request.getParameter("rownum"));
        request.setAttribute("other", "contract_attachment_table");

        return this.ajaxForwardSuccess(request,"",false);
    }


    @RequestMapping(params = "method=printAttach")
    public void printAttach(ContractAttachment search, HttpServletResponse res, HttpServletRequest request) throws Exception{
        try{
            ContractAttachment contractAttachment = contractAttachmentService.getContractAttachment(search);
            if(contractAttachment == null) {
                return ;
            }
            if(!contractAttachment.getContract_id().equals(search.getContract_id())) {
                return ;
            }
            FileKit.pringFile(new String(Base64Kit.decode(contractAttachment.getAttachment_path())), contractAttachment.getAttachment_name(), request, res);
        }catch(Exception e){

        }
    }
}
