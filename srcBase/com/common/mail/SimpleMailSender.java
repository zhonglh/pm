package com.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import org.apache.log4j.Logger;

public class SimpleMailSender {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	/** */
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *              待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		/*mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setFromAddress("493843721@qq.com");
		mailInfo.setToAddress("1374329554@qq.com");
		mailInfo.setUserName("493843721@qq.com");
		mailInfo.setPassword("zhonglhyy2004..");*/
		
		  
	     mailInfo.setMailServerHost("smtp.qiye.163.com");    
	     mailInfo.setMailServerPort("25");    
	     mailInfo.setValidate(true);    
	     mailInfo.setUserName("souvi_hr@souvi.com");    
	     mailInfo.setPassword("hr2016@163");//您的邮箱密码    
	     mailInfo.setFromAddress("souvi_hr@souvi.com");    
	     mailInfo.setToAddress("493843721@qq.com");    

		mailInfo.setSubject("设置邮箱标题");
		mailInfo.setContent("设置邮箱内容");

		SimpleMailSender sms = new SimpleMailSender();

		sms.sendHtmlMail(mailInfo);// 发送html格式

	}

	/** */
	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *              待发送的邮件信息
	 */
	public boolean sendHtmlMail(MailSenderInfo mailInfo) {

		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(pro, authenticator);
		Transport transport = null;
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);

			

			logger.info("mail info send ... :"+Thread.currentThread().getId());

			// 发送邮件
			//Transport.send(mailMessage);
						
			transport = sendMailSession.getTransport("smtp");
			transport.connect(mailInfo.getMailServerHost(), mailInfo.getUserName(), mailInfo.getPassword());
			transport.sendMessage(mailMessage,
					mailMessage.getAllRecipients());
			

			logger.info("mail info send complete :"+Thread.currentThread().getId());

			transport.close();
			transport = null;
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}finally{
			if(transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {

					e.printStackTrace();
				}
			}
		}

	}
}
