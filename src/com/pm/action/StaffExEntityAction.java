package com.pm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.Ztree;
import com.pm.domain.business.StaffCost;
import com.pm.domain.business.StaffExEntity;
import com.pm.domain.system.MarketSets;
import com.pm.engine.BusinessSend;
import com.pm.service.IRoleService;
import com.pm.service.IStaffCostService;
import com.pm.service.IStaffExEntityService;
import com.pm.util.PubMethod;
import com.pm.util.constant.BusinessUtil;
import com.pm.util.constant.EnumOperationType;
import com.pm.util.constant.EnumPermit;
import com.pm.util.constant.EnumSalaryModel;
import com.pm.vo.UserPermit;


@Controller
@RequestMapping("StaffExEntityAction.do")
public class StaffExEntityAction extends BaseAction {


	private static final String rel = "rel19";

	@Autowired
	private IStaffExEntityService staffExEntityService;
	

	@Autowired
	private IStaffCostService staffCostService;



	@Autowired
	private IRoleService roleService;
	
	

	@RequestMapping(params = "method=toTree")
	public String toTree(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		request.setAttribute("staffExEntity1", staffExEntity);
		request.setAttribute("MAX_LEVEL", BusinessUtil.MAX_LEVEL);
		return "basicdata/staffexentity_ztree";
	}
	

	@RequestMapping(params = "method=ztree",method = RequestMethod.GET, produces =  "json;charset=UTF-8")
	@ResponseBody
	public List<Ztree> ztree(HttpServletResponse res,HttpServletRequest request){
		List<Ztree> list = new ArrayList<Ztree>();

		UserPermit userPermit = new UserPermit();
		userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	

		Pager<StaffExEntity> pager = staffExEntityService.queryStaffExEntity(new StaffExEntity(), userPermit, PubMethod.getPagerByAll( StaffExEntity.class));
		
		if(pager.getResultList() != null){
			for(StaffExEntity staffExEntity : pager.getResultList() ){				
				if(staffExEntity.getId() != null && EnumSalaryModel.OriginalMode.getCode().equals(staffExEntity.getSalary_model()) )
					continue;
				
				Ztree ztree = new Ztree();
				ztree.setId(staffExEntity.getStaff_id());
				ztree.setName(staffExEntity.getStaff_name());
				ztree.setNo(staffExEntity.getStaff_no());
				String pid = staffExEntity.getParent_id();
				ztree.setPid((pid != null && pid.isEmpty()) ? null : pid);
				list.add(ztree);
			}
		}
		
		Ztree ztree = new Ztree();
		//ztree.setId(null);
		ztree.setName("全部");
		ztree.setNo("");
		//ztree.setPid("");
		ztree.setOpen(true);
		ztree.setDrag(false);
		list.add(ztree);
		return list;
		
	}
	
	
	/**
	 * 批量组织 上下级关系
	 * @param info
	 * @param res
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=updateParents")
	public String updateParents(String info,HttpServletResponse res,HttpServletRequest request){
		
		try{
			List<Ztree> ztrees = null;
			
			if(info!=null && info.length()>0) ztrees = JSON.parseArray(info, Ztree.class);
			
			if(ztrees == null || ztrees.size() ==0)
				return this.ajaxForwardSuccess(request, rel, true);
			
			List<StaffExEntity> list = new ArrayList<StaffExEntity>();
			Map<String,StaffExEntity> temps = new HashMap<String,StaffExEntity>();
			
			Map<String, Ztree> maps = new HashMap<String, Ztree>();
			for(Ztree ztree : ztrees){
				maps.put(ztree.getId(), ztree);
			}

			UserPermit userPermit = new UserPermit();
			userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
			Pager<StaffExEntity> pager = staffExEntityService.queryStaffExEntity(new StaffExEntity(), userPermit, PubMethod.getPagerByAll( StaffExEntity.class));
			if(pager.getResultList() != null){
				for(StaffExEntity staffExEntity : pager.getResultList() ){
					if(staffExEntity.getId() != null && EnumSalaryModel.OriginalMode.getCode().equals(staffExEntity.getSalary_model()) )
						continue;					
					temps.put(staffExEntity.getStaff_id(), staffExEntity);
				}				
			}
			
			for(StaffExEntity staffExEntity : temps.values()) {
				Ztree ztree = maps.get(staffExEntity.getStaff_id());
				
				if(ztree == null) continue;
				if(ztree.getPid() == staffExEntity.getParent_id() || (ztree.getPid()!= null && staffExEntity.getParent_id() != null && staffExEntity.getParent_id().equals(ztree.getPid())))
					;
				else {
					staffExEntity.setParent_id(ztree.getPid());
					StaffExEntity tt = null;
					if(ztree.getPid() != null)  tt = temps.get(ztree.getPid());
					staffExEntity.setParent_name(tt == null ? "" : tt.getStaff_name());
					if(tt != null && StringUtils.isEmpty(tt.getId()) && !list.contains(tt)) list.add(tt);
					list.add(staffExEntity);
				}
			}
			
			if(list != null && list.size() >0){
				for(StaffExEntity staffExEntity : list){
					if(StringUtils.isEmpty(staffExEntity.getId())){
						staffExEntity.setId(staffExEntity.getStaff_id());
						staffExEntity.setSalary_model(EnumSalaryModel.MarketingModel.getCode());
						staffExEntity.setSalary_model_name(EnumSalaryModel.MarketingModel.getVal());
						staffExEntityService.addStaffExEntity(staffExEntity);
					}else {
						staffExEntityService.updateStaffExEntity(staffExEntity);
					}
				}
			}
			
			

			BusinessSend.send(StaffExEntity.class.getSimpleName());
			
			return this.ajaxForwardSuccess(request, rel, true);
		}catch(Exception e){
			return this.ajaxForwardError(request, "操作失败", true);
		}
		
	}
	
	
	
	
	


	@RequestMapping(params = "method=list")
	public String list(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){

		UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.STAFFEXENTITYVIEW.getId());

		if(staffExEntity.getDept_id() == null || staffExEntity.getDept_id().isEmpty()){
			staffExEntity.setDept_id(request.getParameter("dept.dept_id"));
		}
		if(staffExEntity.getDept_name() == null || staffExEntity.getDept_name().isEmpty()){
			staffExEntity.setDept_name(request.getParameter("dept.dept_name"));
		}

		Pager<StaffExEntity> pager = staffExEntityService.queryStaffExEntity(staffExEntity, userPermit, PubMethod.getPager(request, StaffExEntity.class));
		PubMethod.setRequestPager(request, pager,StaffExEntity.class);	

		request.setAttribute("staffExEntity", staffExEntity);
		request.setAttribute(EnumOperationType.READ.getKey(), userPermit.getPermit_id());	
		UserPermit userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFEXENTITYADD.getId());
		request.setAttribute(EnumOperationType.INSERT.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFEXENTITYUPDATE.getId());
		request.setAttribute(EnumOperationType.UPDATE.getKey(), userPermit1.getPermit_id());
		userPermit1 = this.getUserPermit(request, roleService, EnumPermit.STAFFEXENTITYDELETE.getId());
		request.setAttribute(EnumOperationType.DELETE.getKey(), userPermit1.getPermit_id());

		return "basicdata/staffexentity_list";
	}
	
	


	private void paramprocess(HttpServletRequest request,StaffExEntity staffExEntity){
		staffExEntity.setParent_id(request.getParameter("parent1.staff_id"));
		staffExEntity.setParent_name(request.getParameter("parent1.staff_name"));
		EnumSalaryModel enum1 = EnumSalaryModel.getEnumByCode(staffExEntity.getSalary_model());
		if(enum1 != null) staffExEntity.setSalary_model_name(enum1.getVal());
		
		if(enum1 == EnumSalaryModel.OriginalMode){
			staffExEntity.setGuarantee_amount(0);
			staffExEntity.setOther_expenditures(0);
			staffExEntity.setParent_id(null);
			staffExEntity.setParent_name("");
		}
	}


	@RequestMapping(params = "method=toEdit")
	public String toEdit(StaffExEntity searchStaffExEntity,HttpServletResponse res,HttpServletRequest request){
		StaffExEntity staffExEntity = staffExEntityService.getStaffExEntity(searchStaffExEntity.getId());	
		
		
		if(staffExEntity == null){
			return this.ajaxForwardError(request, "该人员已经离职！", true);
		}
		

		MarketSets marketSets = getMarketSets();	
		request.setAttribute("platform_ratio", marketSets.getPlatform_ratio());
		
		if(staffExEntity != null && staffExEntity.getId()!=null){
			request.setAttribute("next_operation", "updateStaffExEntity");
		}else {
			request.setAttribute("next_operation", "addStaffExEntity");		
			staffExEntity.setSalary_model(EnumSalaryModel.OriginalMode.getCode());
		}
		request.setAttribute("staffExEntity1", staffExEntity);
		return "basicdata/staffexentity_edit";
	}


	@RequestMapping(params = "method=toView")
	public String toView(StaffExEntity staffExEntity,HttpServletResponse res,HttpServletRequest request){
		staffExEntity = staffExEntityService.getStaffExEntity(staffExEntity.getId());
		
		request.setAttribute("staffExEntity1", staffExEntity);
		return "basicdata/staffexentity_view";
	}


	@RequestMapping(params = "method=addStaffExEntity")
	public String addStaffExEntity(StaffExEntity addStaffExEntity,HttpServletResponse res,HttpServletRequest request){
		StaffExEntity staffExEntity = addStaffExEntity;	
		paramprocess(request,staffExEntity);
		staffExEntity.setId(addStaffExEntity.getStaff_id());
		int count = 0;
		try{
			count = staffExEntityService.addStaffExEntity(staffExEntity);
			

			//处理上级
			if(StringUtils.isNotEmpty(staffExEntity.getParent_id())){
				StaffExEntity parent = staffExEntityService.getStaffExEntity(staffExEntity.getParent_id());
				if(StringUtils.isEmpty(parent.getId())){
					parent.setId(parent.getStaff_id());
					parent.setSalary_model(EnumSalaryModel.MarketingModel.getCode());
					parent.setSalary_model_name(EnumSalaryModel.MarketingModel.getVal());
					staffExEntityService.addStaffExEntity(parent);
				}else {
					if(!EnumSalaryModel.MarketingModel.getCode().equals(parent.getSalary_model())){
						parent.setSalary_model(EnumSalaryModel.MarketingModel.getCode());
						parent.setSalary_model_name(EnumSalaryModel.MarketingModel.getVal());
						staffExEntityService.updateStaffExEntity(parent);
					}
				}
				

				BusinessSend.send(StaffExEntity.class.getSimpleName(), parent.getId());
				
			}
		
		
			

			BusinessSend.send(StaffExEntity.class.getSimpleName(), staffExEntity.getId());
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updateStaffExEntity")
	public String updateStaffExEntity(StaffExEntity updateStaffExEntity,HttpServletResponse res,HttpServletRequest request){
		StaffExEntity staffExEntity = updateStaffExEntity;	
		paramprocess(request,staffExEntity);	
		int count = 0;
		try{
			
			StaffExEntity temp = staffExEntityService.getStaffExEntity(staffExEntity.getId());
			if(temp == null){
				return this.ajaxForwardError(request, "该人员已经离职！", true);
			}
			
			//从原来的营销模式 需改为 原有模式时， 如果还有下线就不能修改
			if(updateStaffExEntity.getSalary_model().equals(EnumSalaryModel.OriginalMode.getCode())){
				
				StaffCost staffCost = staffCostService.getStaffCost(temp.getId());
				if(staffCost == null || BusinessUtil.DELETEED.equals(staffCost.getDelete_flag())){
					return this.ajaxForwardError(request, "该人员已经离职！", true);
				}
				
				if(temp.getSalary_model().equals(EnumSalaryModel.MarketingModel.getCode())){
					UserPermit userPermit = new UserPermit();
					userPermit.setRange(BusinessUtil.DATA_RANGE_ALL);	
					StaffExEntity searchStaffExEntity = new StaffExEntity();
					searchStaffExEntity.setParent_id(updateStaffExEntity.getId());
					searchStaffExEntity.setSalary_model(EnumSalaryModel.MarketingModel.getCode());
					Pager<StaffExEntity> pager = staffExEntityService.queryStaffExEntity(searchStaffExEntity, userPermit, PubMethod.getPager(request, StaffExEntity.class));
					if(pager.getResultList() != null && pager.getResultList().size() >0){
						return this.ajaxForwardError(request, "该单据修改错误， 模式设置错误， 还有下线没有处理！", true);
					}
				}
			}else {
				//处理上级
				if(StringUtils.isNotEmpty(staffExEntity.getParent_id())){
					StaffExEntity parent = staffExEntityService.getStaffExEntity(staffExEntity.getParent_id());
					if(StringUtils.isEmpty(parent.getId())){
						parent.setId(parent.getStaff_id());
						parent.setSalary_model(EnumSalaryModel.MarketingModel.getCode());
						parent.setSalary_model_name(EnumSalaryModel.MarketingModel.getVal());
						staffExEntityService.addStaffExEntity(parent);
					}else {
						if(!EnumSalaryModel.MarketingModel.getCode().equals(parent.getSalary_model())){
							parent.setSalary_model(EnumSalaryModel.MarketingModel.getCode());
							parent.setSalary_model_name(EnumSalaryModel.MarketingModel.getVal());
							staffExEntityService.updateStaffExEntity(parent);
						}
					}
					

					BusinessSend.send(StaffExEntity.class.getSimpleName(), parent.getId());
				}
			
			}
			

			count = staffExEntityService.updateStaffExEntity(staffExEntity);
			
			BusinessSend.send(StaffExEntity.class.getSimpleName(), staffExEntity.getId());
		}catch(Exception e){
			
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该人员已经离职！", true);
	}
	

	
	
	

}