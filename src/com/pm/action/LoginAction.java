package com.pm.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.utils.encrypt.MD5Kit;
import com.pm.domain.system.User;
import com.pm.service.IUserService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;



@Controller
@RequestMapping(value = "LoginAction.do")
public class LoginAction extends BaseAction {
	
	@Autowired
	private IUserService userService;
	
	
		
	@RequestMapping(params = "method=toLogin")
	public String toLogin(HttpServletResponse res,HttpServletRequest request){
		try{
			String loginName = PubMethod.getCookieValue(request, BusinessUtil.COOKIE_LOGINNAME);
			request.setAttribute(BusinessUtil.COOKIE_LOGINNAME, loginName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "login";		    
	}	
	

	@RequestMapping(params = "method=toAjaxLogin")
	public String toAjaxLogin(HttpServletResponse res,HttpServletRequest request){
		try{
			String loginName = PubMethod.getCookieValue(request, BusinessUtil.COOKIE_LOGINNAME);
			request.setAttribute(BusinessUtil.COOKIE_LOGINNAME, loginName);
		}catch(Exception e){
			
		}
		return "toAjaxLogin";
	}	
	
	@RequestMapping(params = "method=login")
	public String login(HttpServletResponse res,HttpServletRequest request){		    
		
		String loginName = request.getParameter(BusinessUtil.USER_LOGIN_NAME);
		if(loginName != null) loginName = loginName.trim();
		
		User user = userService.getUserByLoginName(loginName);
		if(user == null) request.setAttribute("message", this.getMsg("login.error1", request));
		else if(PubMethod.isDelete(user.getDelete_flag())) request.setAttribute("message", this.getMsg("login.error2", request));
		else {
			String password = request.getParameter(BusinessUtil.USER_LOGIN_PASSWD);
			password = password.trim();
			password = MD5Kit.md5(password);
			if(!password.equals(user.getUser_password().trim())) {
				 request.setAttribute("message", this.getMsg("login.error3", request));
			}else {
				request.getSession().setAttribute(BusinessUtil.SESSION_USER, user);				

				Cookie cookie = new Cookie(BusinessUtil.COOKIE_LOGINNAME, loginName);
				cookie.setMaxAge(30 * 24 * 60 * 60);
				res.addCookie(cookie);
				
				return "to_index";
			}
			
		}
		
		return "login";
		    
	}
	

	@RequestMapping(params = "method=ajaxLogin")
	public String ajaxLogin(HttpServletResponse res,HttpServletRequest request){
		    
		String error = null;
		String loginName = request.getParameter(BusinessUtil.USER_LOGIN_NAME);
		if(loginName != null) loginName = loginName.trim();
		
		User user = userService.getUserByLoginName(loginName);
		if(user == null) error = this.getMsg("login.error1", request);
		else if(PubMethod.isDelete(user.getDelete_flag())) error = this.getMsg("login.error2", request);
		else {
			String password = request.getParameter(BusinessUtil.USER_LOGIN_PASSWD);
			password = password.trim();
			password = MD5Kit.md5(password);
			if(!password.equals(user.getUser_password().trim())) {
				 error = this.getMsg("login.error3", request);
			}else {
				request.getSession().setAttribute(BusinessUtil.SESSION_USER, user);
				return this.ajaxForwardSuccess(request, "", true);
			}
			
		}
		
		return this.ajaxForwardError(request, error,false);
		    
	}
		
	
	
	@RequestMapping(params = "method=logout")
	public String logout(HttpServletResponse res,HttpServletRequest request){
		try{
			request.getSession().removeAttribute(BusinessUtil.SESSION_USER);
		}catch(Exception e){
			
		}
		
		return this.toLogin(res, request);
	}



}
