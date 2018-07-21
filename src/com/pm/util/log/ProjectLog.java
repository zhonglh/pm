package com.pm.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import com.common.utils.IDKit;
import com.common.utils.spring.SpringContextUtil;
import com.pm.domain.business.Project;
import com.pm.domain.business.ProjectContract;
import com.pm.domain.business.ProjectStaff;
import com.pm.domain.system.Log;
import com.pm.domain.system.LogDetail;
import com.pm.domain.system.User;
import com.pm.service.IProjectService;
import com.pm.util.PubMethod;
import com.pm.util.constant.LogConstant;

public class ProjectLog extends BasicLog {


	public List<Log> calculateLog(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser) {
		
		IProjectService projectService = SpringContextUtil.getApplicationContext().getBean(IProjectService.class);
		List<Log> logs = new ArrayList<Log>();
		
		if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_DELETE)){
			
			Project[] projects = (Project[])invocation.getArguments()[0];
			if(projects == null || projects.length == 0) return null;
			for(Project project : projects){
				Log log = super.getLog(methodAnnotation, invocation,sessionUser );
				Project preProject = projectService.getProject(project.getProject_id());
				if(preProject == null) preProject = new Project();
				log.setEntity_id(project.getProject_id());
				log.setEntity_name(project.getProject_name()==null?preProject.getProject_name():project.getProject_name());
				log.setProject_id(project.getProject_id());
				List<LogDetail>  details = PubMethod.getLogDetails(log,Project.class, preProject,project);	
				special(details);				
				this.delProjectStaff(preProject, sessionUser, log, details);
				this.delProjectContract(preProject, sessionUser, log, details);

				log.setDetails(details);
				logs.add(log);
			}
			
		}else {
			
			Log log = super.getLog(methodAnnotation, invocation,sessionUser );
			List<LogDetail> details = null;
			
			if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_INSERT)){
				Project project = (Project)invocation.getArguments()[0];
				Project preProject = new Project();				
				log.setEntity_id(project.getProject_id());
				log.setEntity_name(project.getProject_name()==null?preProject.getProject_name():project.getProject_name());
				log.setProject_id(project.getProject_id());
				details = PubMethod.getLogDetails(log,Project.class, preProject,project);
				

				addProjectStaff(methodAnnotation,invocation,sessionUser,log,details);
				addProjectContract(methodAnnotation,invocation,sessionUser,log,details);
				special(details);
				log.setDetails(details);			
				logs.add(log);
			}else if(methodAnnotation.operation_type().equals(LogConstant.OPERATION_UPDATE)){

				if("updateProject".equals(invocation.getMethod().getName())){	
					Project project = (Project)invocation.getArguments()[0];
					Project preProject = projectService.getProject(project.getProject_id());				
					log.setEntity_id(project.getProject_id());
					log.setEntity_name(project.getProject_name()==null?preProject.getProject_name():project.getProject_name());
					log.setProject_id(project.getProject_id());
					details = PubMethod.getLogDetails(log,Project.class, preProject,project);
				
					addProjectStaff(methodAnnotation,invocation,sessionUser,log,details);
					addProjectContract(methodAnnotation,invocation,sessionUser,log,details);				
				}else if("exchangeProjectStaff".equals(invocation.getMethod().getName())){	
					ProjectStaff projectStaff = (ProjectStaff)invocation.getArguments()[0];
					Project project = projectService.getProject(projectStaff.getProject_id());			
					log.setEntity_id(project.getProject_id());
					log.setEntity_name(project.getProject_name()==null?"":project.getProject_name());
					log.setProject_id(project.getProject_id());
					details = new ArrayList<LogDetail>();
					LogDetail logDetail = new LogDetail();
					logDetail.setLog_detail_id(IDKit.generateId());
					logDetail.setLog_id(log.getLog_id());
					logDetail.setData_item_code("del_project_staff");
					logDetail.setData_item_i18n("移除项目人员");
					logDetail.setOperation_before(projectStaff.getStaff_name());
					details.add(logDetail);
					
					String project_id = (String)invocation.getArguments()[1];					
					if(project_id != null && project_id.length() > 0){
						Log log1 = super.getLog(methodAnnotation, invocation,sessionUser );
						log1.setEntity_id(project_id);
						Project newProject = projectService.getProject(project_id);	
						log1.setEntity_name(newProject.getProject_name()==null?"":newProject.getProject_name());
						List<LogDetail> details1 = new ArrayList<LogDetail>();
						LogDetail logDetail1 = new LogDetail();
						logDetail1.setLog_detail_id(IDKit.generateId());
						logDetail1.setLog_id(log1.getLog_id());
						logDetail1.setData_item_code("add_project_staff");
						logDetail1.setData_item_i18n("增加项目人员");
						logDetail1.setOperation_after(projectStaff.getStaff_name());
						details1.add(logDetail1);
						special(details1);
						log1.setDetails(details1);
						logs.add(log1);
					}
				}else if("doRemoveProjectStaff".equals(invocation.getMethod().getName())){	

					ProjectStaff projectStaff = (ProjectStaff)invocation.getArguments()[0];
					Project project = projectService.getProject(projectStaff.getProject_id());			
					log.setEntity_id(project.getProject_id());
					log.setEntity_name(project.getProject_name()==null?"":project.getProject_name());
					log.setProject_id(project.getProject_id());
					details = new ArrayList<LogDetail>();
					LogDetail logDetail = new LogDetail();
					logDetail.setLog_detail_id(IDKit.generateId());
					logDetail.setLog_id(log.getLog_id());
					logDetail.setData_item_code("del_project_staff");
					logDetail.setData_item_i18n("删除项目人员");
					logDetail.setOperation_before(projectStaff.getStaff_name());
					details.add(logDetail);
					
				}else if("addProjectContract".equals(invocation.getMethod().getName())){	
					ProjectContract projectContract = (ProjectContract)invocation.getArguments()[0];
					Project project = projectService.getProject(projectContract.getProject_id());			
					log.setEntity_id(project.getProject_id());
					log.setEntity_name(project.getProject_name()==null?"":project.getProject_name());
					log.setProject_id(project.getProject_id());
					details = new ArrayList<LogDetail>();
					LogDetail logDetail = new LogDetail();
					logDetail.setLog_detail_id(IDKit.generateId());
					logDetail.setLog_id(log.getLog_id());
					logDetail.setData_item_code("add_project_contract");
					logDetail.setData_item_i18n("增加项目合同");
					logDetail.setOperation_after(projectContract.getAttachment_name());
					details.add(logDetail);
				}else if("deleteProjectContract".equals(invocation.getMethod().getName())){	
					ProjectContract projectContract = (ProjectContract)invocation.getArguments()[0];
					Project project = projectService.getProject(projectContract.getProject_id());			
					log.setEntity_id(project.getProject_id());
					log.setEntity_name(project.getProject_name()==null?"":project.getProject_name());
					log.setProject_id(project.getProject_id());
					details = new ArrayList<LogDetail>();
					LogDetail logDetail = new LogDetail();
					logDetail.setLog_detail_id(IDKit.generateId());
					logDetail.setLog_id(log.getLog_id());
					logDetail.setData_item_code("del_project_contract");
					logDetail.setData_item_i18n("删除项目合同");
					logDetail.setOperation_before(projectContract.getAttachment_name());
					details.add(logDetail);
				}
				special(details);
				log.setDetails(details);
				logs.add(log);
			}
			
			
		}
		
		return logs;
	}
	
	
	
	private void addProjectStaff(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser,Log log,List<LogDetail>  details){
		ProjectStaff[] projectStaffs = (ProjectStaff[])invocation.getArguments()[1];
		if(projectStaffs != null){
			for(ProjectStaff projectStaff : projectStaffs){
				LogDetail logDetail = new LogDetail();
				logDetail.setLog_detail_id(IDKit.generateId());
				logDetail.setLog_id(log.getLog_id());
				if(StringUtils.isEmpty(projectStaff.getProject_staff_id())){
					logDetail.setData_item_code("add_project_staff");
					logDetail.setData_item_i18n("增加项目人员");
				}else {
					logDetail.setData_item_code("update_project_staff");
					logDetail.setData_item_i18n("修改项目人员");
					logDetail.setOperation_before(projectStaff.getStaff_name());
				}
				logDetail.setOperation_after(projectStaff.getStaff_name());
				details.add(logDetail);
			}
		}
	}
	

	
	private void delProjectStaff(Project project, User sessionUser,Log log,List<LogDetail>  details){
		IProjectService projectService = SpringContextUtil.getApplicationContext().getBean(IProjectService.class);
		List<ProjectStaff> projectStaffs = projectService.queryProjectStaff(project);
		if(projectStaffs != null){
			for(ProjectStaff projectStaff : projectStaffs){
				LogDetail logDetail = new LogDetail();
				logDetail.setLog_detail_id(IDKit.generateId());
				logDetail.setLog_id(log.getLog_id());
				logDetail.setData_item_code("del_project_staff");
				logDetail.setData_item_i18n("移除项目人员");
				logDetail.setOperation_before(projectStaff.getStaff_name());
				details.add(logDetail);
			}
		}
	}	
	

	private void addProjectContract(LogAnnotation methodAnnotation,MethodInvocation invocation, User sessionUser,Log log,List<LogDetail>  details){
		ProjectContract[] projectContracts = (ProjectContract[])invocation.getArguments()[2];
		if(projectContracts != null){
			for(ProjectContract projectContract : projectContracts){
				LogDetail logDetail = new LogDetail();
				logDetail.setLog_detail_id(IDKit.generateId());
				logDetail.setLog_id(log.getLog_id());
				logDetail.setData_item_code("add_project_staff");
				logDetail.setData_item_i18n("增加项目附件");
				logDetail.setOperation_after(projectContract.getAttachment_name());
				details.add(logDetail);
			}
		}
	}	
	
	

	private void delProjectContract(Project project, User sessionUser,Log log,List<LogDetail>  details){
		IProjectService projectService = SpringContextUtil.getApplicationContext().getBean(IProjectService.class);
		List<ProjectContract> projectContracts = projectService.queryProjectContract(project);
		if(projectContracts != null){
			for(ProjectContract projectContract : projectContracts){
				LogDetail logDetail = new LogDetail();
				logDetail.setLog_detail_id(IDKit.generateId());
				logDetail.setLog_id(log.getLog_id());
				logDetail.setData_item_code("add_project_staff");
				logDetail.setData_item_i18n("删除项目附件");
				logDetail.setOperation_before(projectContract.getAttachment_name());
				details.add(logDetail);
			}
		}
	}	
		
	

	private void special(List<LogDetail> details){
		if(details == null || details.isEmpty()) return ;
		for(LogDetail detail : details){
			if(detail.getData_item_code().equals("project_type")){
				if(detail.getOperation_after() != null && !detail.getOperation_after().isEmpty()){
					detail.setOperation_after(SpringContextUtil.getApplicationContext ().getMessage ("project.type."+detail.getOperation_after(), null, Locale.CHINA));
				}
				if(detail.getOperation_before() != null && !detail.getOperation_before().isEmpty()){
					detail.setOperation_before(SpringContextUtil.getApplicationContext ().getMessage ("project.type."+detail.getOperation_before(), null, Locale.CHINA));
				}
			}
		}
	}

}
