package com.pm.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.IDKit;
import com.pm.domain.business.DicData;
import com.pm.domain.business.DicType;
import com.pm.domain.system.User;
import com.pm.service.IDicDataService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumAjaxDone;


@Controller
@RequestMapping("DicDataAction.do")
public class DicDataAction extends BaseAction {


	private static final String rel = "rel100001";

	
	@Autowired
	private IDicDataService dicdataService;



	@RequestMapping(params = "method=preDicData")
	public String preDicData(DicData dicdata,HttpServletResponse res,HttpServletRequest request){
		dicdata = dicdataService.getDicData(dicdata.getId());
		dicdataService.preDicData(dicdata);
		return this.ajaxForwardSuccess(request,rel,false);
	}

	@RequestMapping(params = "method=nextDicData")
	public String nextDicData(DicData dicdata,HttpServletResponse res,HttpServletRequest request){
		dicdata = dicdataService.getDicData(dicdata.getId());
		dicdataService.nextDicData(dicdata);
		return this.ajaxForwardSuccess(request,rel,false);
	}

	@RequestMapping(params = "method=initDicDataSort")
	public String initDicDataSort(DicData dicdata,HttpServletResponse res,HttpServletRequest request){
		dicdataService.initDicDataSort(dicdata);
		return this.ajaxForwardSuccess(request,rel,false);
	}


	@RequestMapping(params = "method=list")
	public String list(DicData dicdata,HttpServletResponse res,HttpServletRequest request){
		
		Pager<DicData> pager = dicdataService.queryDicData(dicdata,  PubMethod.getPager(request, DicData.class));
		PubMethod.setRequestPager(request, pager,DicData.class);	

		request.setAttribute("dicdata", dicdata);

		return "common/dicdata_list";
	}
	
	

	@RequestMapping(params = "method=lookup")
	public void lookup(DicData dicdata,HttpServletResponse res,HttpServletRequest request){
		List<DicData> dicDatas = null;
		if(dicdata.getDic_type_id() != null && dicdata.getDic_type_id().length() >0){
			if(dicdata.getDic_type_id().indexOf(",")>0){
				String[] dictypes = dicdata.getDic_type_id().split(",");
				dicDatas = new ArrayList<DicData>();
				for(String dictype : dictypes){
					dicdata.setDic_type_id(dictype);
					List<DicData> list = dicdataService.getDicDataByType(dicdata);
					if(list != null && list.size()>0) dicDatas.addAll(list);
				}
			}else {
				dicDatas = dicdataService.getDicDataByType(dicdata);
			}
		}else {
			dicDatas = dicdataService.getDicDataByType(dicdata);
		}		
		this.writeResJson(res, dicDatas);
	}



	@RequestMapping(params = "method=toEdit")
	public String toEdit(DicData searchDicData,HttpServletResponse res,HttpServletRequest request){
		DicData dicdata = null;
		if(searchDicData != null && searchDicData.getId()!=null){
			request.setAttribute("next_operation", "updateDicData");
			dicdata = dicdataService.getDicData(searchDicData.getId());	
		}else {
			request.setAttribute("next_operation", "addDicData");		
			User sessionUser = PubMethod.getUser(request);
			dicdata = new DicData();	
			dicdata.setDic_type_id(searchDicData.getDic_type_id());
			dicdata.setBuild_userid(sessionUser.getUser_id());
			dicdata.setBuild_username(sessionUser.getUser_name());
			dicdata.setBuild_datetime(PubMethod.getCurrentDate());
			
			String[] dictypes = dicdata.getDic_type_id().split(",");
			List<DicType> dicTypeList = new ArrayList<DicType>();
			for(String dictype : dictypes){
				DicType dicType = dicdataService.getDicType(dictype);
				dicTypeList.add(dicType);
			}
			if(dicTypeList.size() > 0) dicdata.setDic_type_name(dicTypeList.get(0).getDic_type_name());
			
			if(dicTypeList.size() > 1) request.setAttribute("dicTypeList", dicTypeList);
		}
		request.setAttribute("dicdata1", dicdata);
		return "common/dicdata_edit";
	}




	@RequestMapping(params = "method=addDicData")
	public String addDicData(DicData addDicData,HttpServletResponse res,HttpServletRequest request){
		DicData dicdata = addDicData;	
		User sessionUser = PubMethod.getUser(request);
		dicdata.setId(IDKit.generateId());
		dicdata.setBuild_datetime(PubMethod.getCurrentDate());
		dicdata.setBuild_userid(sessionUser.getUser_id());
		dicdata.setBuild_username(sessionUser.getUser_name());
		int count = 0;
		try{
			count = dicdataService.addDicData(dicdata);	
			request.setAttribute(EnumAjaxDone.forwardUrl.name(), request.getContextPath()+"/DicDataAction.do?method=list&dic_type_id="+addDicData.getDic_type_id());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}


	@RequestMapping(params = "method=updateDicData")
	public String updateDicData(DicData updateDicData,HttpServletResponse res,HttpServletRequest request){
		DicData dicdata = updateDicData;	
		int count = 0;
		try{
			count = dicdataService.updateDicData(dicdata);	
			request.setAttribute(EnumAjaxDone.forwardUrl.name(), request.getContextPath()+"/DicDataAction.do?method=list&dic_type_id="+updateDicData.getDic_type_id());
			
		}catch(Exception e){
		}
		if(count == 1) 		return this.ajaxForwardSuccess(request, rel, true);
		else return this.ajaxForwardError(request, "该单据已经存在！", true);
	}	



	@RequestMapping(params = "method=deleteDicData")
	public String deleteDicData(HttpServletResponse res,HttpServletRequest request){
		User sessionUser = PubMethod.getUser(request);
		java.sql.Timestamp deleteDate = PubMethod.getCurrentDate();
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			DicData[] dicdatas = new DicData[ids.length];
			int index = 0;
			for(String id : ids){
				DicData dicdata = new DicData();
				dicdata.setId(id);
				dicdata.setDelete_userid(sessionUser.getUser_id());
				dicdata.setDelete_username(sessionUser.getUser_name());
				dicdata.setDelete_datetime(deleteDate);
				dicdatas[index] = dicdata;
				index ++ ;
			}
			if(dicdatas != null && dicdatas.length > 0)
			dicdataService.deleteDicData(dicdatas);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	
	
	

	@RequestMapping(params = "method=recoverDicData")
	public String recoverDicData(HttpServletResponse res,HttpServletRequest request){
		
		String[] ids = request.getParameterValues("ids");
		if(ids != null && ids.length > 0){
			DicData[] dicdatas = new DicData[ids.length];
			int index = 0;
			for(String id : ids){
				DicData dicdata = dicdataService.getDicData(id);
				dicdatas[index] = dicdata;
				index ++ ;
			}
			if(dicdatas != null && dicdatas.length > 0)
			dicdataService.recoverDicData(dicdatas);
		}
		return this.ajaxForwardSuccess(request,rel,false);
	}	
	
	
	



}