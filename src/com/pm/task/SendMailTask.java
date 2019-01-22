package com.pm.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.common.mail.MailSenderInfo;
import com.common.mail.SimpleMailSender;
import com.common.utils.IDKit;
import com.pm.domain.business.SalaryMail;
import com.pm.domain.business.SalaryMailDetail;
import com.pm.domain.business.StaffCost;
import com.pm.service.ISalaryMailService;
import com.pm.service.IStaffCostService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.excel.BusinessExcel;
import com.pm.util.excel.Column;

public class SendMailTask implements Runnable{
	
	private SalaryMail salaryMail;
	private MailSenderInfo mailInfo;

	private ISalaryMailService salaryMailService;
	private IStaffCostService staffCostService;
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public SendMailTask(ISalaryMailService salaryMailService,IStaffCostService staffCostService,SalaryMail salaryMail,MailSenderInfo mailInfo){
		this.salaryMail = salaryMail;
		this.salaryMailService = salaryMailService;
		this.mailInfo = mailInfo;
		this.staffCostService = staffCostService;
	}
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		String remark = null;
		
		SalaryMailDetail salaryMailDetail = new SalaryMailDetail();
		
		logger.info("start send mail:"+Thread.currentThread().getId()+"  "+salaryMail.getStaff_name());
		
		try{
		
			salaryMailDetail.setDetail_id(IDKit.getUUID());
			salaryMailDetail.setSalary_id(salaryMail.getSalary_id());
			salaryMailDetail.setSend_userid(salaryMail.getSend_userId());
			salaryMailDetail.setSend_date(PubMethod.getCurrentDate());
			
			
			
			MailSenderInfo sendMailInfo = new MailSenderInfo();
			BeanUtils.copyProperties(mailInfo, sendMailInfo);
			

			logger.info("ready data object:"+Thread.currentThread().getId()+"  "+salaryMail.getStaff_name());
			
			StaffCost staffCost = staffCostService.getStaffCost(salaryMail.getStaff_id());
			
			if(staffCost.getEmail() == null || staffCost.getEmail().isEmpty()){
				remark = "请先设置收件人邮箱地址";
				throw new Exception(remark);
			}
			
			
			sendMailInfo.setToAddress(staffCost.getEmail());
			
			
			sendMailInfo.setSubject(salaryMail.getStaff_name() +"   "+salaryMail.getSalary_month()+" 工资单");   
			
			
			
			StringBuffer htmml = new StringBuffer("<html>");
			

			htmml.append(" <body>");
			

			htmml.append(" <h3> "+ salaryMail.getStaff_name() +",你好！</h3>");

			htmml.append("  <span margin-left='40px'> 以下是您在");
			
			htmml.append(salaryMail.getSalary_month()/100+"年"+salaryMail.getSalary_month()%100+"月");
			
			htmml.append("的工资详细信息！</span>");

			htmml.append(" <br><br>");
			
			htmml.append(" <table  border=1 cellspacing=0 cellpadding=0 style='solid #CCFFFF;border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt; mso-yfti-tbllook:1184;mso-padding-alt:0cm 5.4pt 0cm 5.4pt'");
			
			List<Column> list = BusinessExcel.getColume(salaryMail.getClass());
			if(list == null || list.isEmpty()){
				remark = "没有取到工资信息";
				throw new Exception(remark);
			}
			
			int count = list.size();
			
			for(int i=0; i<count; i=i+2){
				htmml.append("<tr>");
				
				if(i==count-1){

					htmml.append("<td bgcolor=\"FFFFCC\" align=\"center\">");
					htmml.append(list.get(i).getName());
					htmml.append("</td>");
					
					htmml.append("<td colspan=\"3\">");
					list.get(i).getField().setAccessible(true);
					Object obj = list.get(i).getField().get(salaryMail);
					if(obj == null){
						htmml.append(" ");
					}else {
						htmml.append(obj);
					}
					htmml.append("</td>");
				}else {

					htmml.append("<td width=\"150\" bgcolor=\"FFFFCC\" align=\"center\">");
					htmml.append(list.get(i).getName());
					htmml.append("</td>");
					
					htmml.append("<td width=\"300\" align=\"left\">");
					list.get(i).getField().setAccessible(true);
					Object obj = list.get(i).getField().get(salaryMail);
					if(obj == null){
						htmml.append(" ");
					}else {
						htmml.append(obj);
					}
					htmml.append("</td>");
					

					htmml.append("<td width=\"150\" bgcolor=\"FFFFCC\" align=\"center\">");
					htmml.append(list.get(i+1).getName());
					htmml.append("</td>");

					htmml.append("<td width=\"300\" align=\"left\">");
					list.get(i+1).getField().setAccessible(true);
					Object obj1 = list.get(i+1).getField().get(salaryMail);
					if(obj1 == null){
						htmml.append(" ");
					}else {
						htmml.append(obj1);
					}
					htmml.append("</td>");
					
				}

				htmml.append("</tr>");
			}
			
			htmml.append("</table>");

			htmml.append(" <br><br><h4><b>注: </b><span margin-left='160px'><font color='red'>准许扣除的费用是指社保公积金个人扣除数</font></span></h4><br>");
			htmml.append(" <h4><b>专项附加扣除:</b><span margin-left='160px'><font color='red'>子女教育 继续教育  住房贷款利息  住房租金 赡养老人</font></span></h4><br>");
			htmml.append(" </body>");
			htmml.append("</html>");
			
			sendMailInfo.setContent(htmml.toString());
			


			logger.info("mail info ready:"+Thread.currentThread().getId()+"  "+salaryMail.getStaff_name()+ " "+sendMailInfo.getUserName());

			
			salaryMailDetail.setMail_server_host(sendMailInfo.getMailServerHost());
			salaryMailDetail.setMail_server_port(sendMailInfo.getMailServerPort());
			salaryMailDetail.setFrom_username(sendMailInfo.getUserName());
			salaryMailDetail.setFrom_password(sendMailInfo.getPassword());
			salaryMailDetail.setTo_Address(sendMailInfo.getToAddress());
			

			SimpleMailSender sms = new SimpleMailSender();  
			sms.sendHtmlMail(sendMailInfo);

			logger.info("mail info send completed:"+Thread.currentThread().getId()+"  "+salaryMail.getStaff_name());

			
			salaryMailDetail.setSend_remark("正常发送");
			salaryMailDetail.setSend_status(BusinessUtil.SEND_SALARY_MAIL_SUCCESS);
			
			
		}catch(Exception e){

			logger.info("mail info send error:"+Thread.currentThread().getId()+"  "+salaryMail.getStaff_name());
			e.printStackTrace();
			salaryMailDetail.setSend_remark(e.getMessage());
			salaryMailDetail.setSend_status(BusinessUtil.SEND_SALARY_MAIL_FAILURE);
		}
		
		List<SalaryMailDetail> details = new ArrayList<SalaryMailDetail>();
		details.add(salaryMailDetail);
		salaryMail.setDetails(details);
		salaryMailService.doAfterSend(salaryMail);
		

		logger.info("mail info save db:"+Thread.currentThread().getId()+"  "+salaryMail.getStaff_name());
		
	}

	

}
