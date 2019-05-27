package com.pm.action;

import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.enums.EnumYesNo;
import com.pm.domain.business.DicData;
import com.pm.domain.business.Statistics;
import com.pm.domain.system.Dept;
import com.pm.service.IDepartStatisticsService;
import com.pm.service.IDeptService;
import com.pm.service.IDicDataService;
import com.pm.service.IRoleService;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumDicType;
import com.pm.util.excel.EnumCellFormat;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门统计抽象类
 * @author Administrator
 */
public abstract class DepartStatisticsAbstractAction extends BaseAction {


    protected static String unconfirmedItem4RP = "还未核单的回款";
    protected static String unconfirmedItem4MS = "还未核单的项目收入";

    /**
     * 获取标题列表
     * @return
     */
    protected abstract List<String> getTitles() ;


    @Autowired
    protected IDepartStatisticsService departStatisticsService;


    @Autowired
    protected IDeptService deptService;

    @Autowired
    protected IDicDataService dicDataService;

    @Autowired
    protected IRoleService roleService;




    /**
     * 处理查询条件
     * @param searchStatistics
     * @param request
     */
    protected void processSearch(Statistics searchStatistics, HttpServletRequest request) {
        if(searchStatistics.getDeptId() == null || searchStatistics.getDeptId().isEmpty()) {
            searchStatistics.setDeptId(request.getParameter("dept.dept_id"));
        }

        if(searchStatistics.getDeptName() == null || searchStatistics.getDeptName().isEmpty()) {
            searchStatistics.setDeptName(request.getParameter("dept.dept_name"));
        }
    }


    /**
     * 获取要统计的部门
     * @param searchStatistics
     * @param userPermit
     * @return
     */
    protected List<Dept> getDepts(Statistics searchStatistics, UserPermit userPermit) {
        Dept dept = new Dept();
        dept.setCurr_years(0);
        dept.setStatisticsed(EnumYesNo.Yes.getCode());
        if(searchStatistics.getMonth1() != 0) {
            dept.setYear1(searchStatistics.getMonth1()/100);
        }
        if(searchStatistics.getMonth2() != 0) {
            dept.setYear2(searchStatistics.getMonth2()/100);
        }

        if(searchStatistics.getDeptId() != null && searchStatistics.getDeptId().length() >0){
            dept.setDept_id(searchStatistics.getDeptId());
        }
        List<Dept> depts = null;

        if(searchStatistics.getDeptId() != null && searchStatistics.getDeptId().length() >0){
            dept.setDept_id(searchStatistics.getDeptId());
        }

        Pager<Dept> departs = deptService.queryDept(dept, userPermit, PubMethod.getPagerByAll(Dept.class));
        depts = departs.getResultList();
        if(depts == null) {
            depts = new ArrayList<Dept>();
        }
        return depts;
    }


    /**
     * 处理统计内容
     * @param depts
     * @param map
     * @param index
     * @param url
     * @param itemFormatter
     * @return
     */
    protected List<DepartStatisticsItem> handleStatistics(
            List<Dept> depts,
            Map<String, DepartStatisticsItem> map,
            int index,
            String url,
            String itemFormatter) {


        List<DepartStatisticsItem> list1 = new ArrayList<DepartStatisticsItem>();

        //处理第一列
        DepartStatisticsItem temp1 = new DepartStatisticsItem();
        if(index >= 0) {
            temp1.setItemName(getTitles().get(index));
        }else {
            temp1.setItemName(map.get(depts.get(0).getDept_id()).getItemName());
        }
        temp1.setItemFormatter(itemFormatter);
        list1.add(temp1);

        double sum1 = 0;
        for(Dept dept1 : depts){
            DepartStatisticsItem departStatisticsItem = map.get(dept1.getDept_id());
            if(departStatisticsItem == null) {
                departStatisticsItem = new DepartStatisticsItem();
                departStatisticsItem.setDeptId(dept1.getDept_id());
            }else {
                if(url != null && url.length() >0) {
                    departStatisticsItem.setUrl(url+"&deptId="+dept1.getDept_id());
                }
            }
            departStatisticsItem.setItemId("");
            if(index >= 0) {
                departStatisticsItem.setItemName(getTitles().get(index));
            }
            sum1 += departStatisticsItem.getVal();

            departStatisticsItem.setItemFormatter(itemFormatter);
            list1.add(departStatisticsItem);
        }

        if(depts.size() > 1){
            DepartStatisticsItem last1 = new DepartStatisticsItem();
            last1.setDeptId("");
            last1.setItemId("");
            if(index >= 0) {
                last1.setItemName(getTitles().get(index));
            }
            last1.setVal(sum1);
            last1.setUrl(url);
            last1.setItemFormatter(itemFormatter);
            list1.add(last1);
        }

        return list1;
    }


    /**
     * 处理明细内容
     * @param depts
     * @param salesCostTypes
     * @param mapsc
     * @param url
     * @return
     */
    protected List<List<DepartStatisticsItem>> handleDetails(
            List<Dept> depts,
            List<DicData> salesCostTypes,
            Map<String, Map<String, DepartStatisticsItem>> mapsc,
            String url) {

        List<List<DepartStatisticsItem>> list = new ArrayList<>();

        for(DicData dicData : salesCostTypes){

            Map<String, DepartStatisticsItem> tempMap = mapsc.get(dicData.getId());
            if(tempMap == null || tempMap.isEmpty()) {
                continue;
            }

            List<DepartStatisticsItem> tempList = new ArrayList<DepartStatisticsItem>();

            DepartStatisticsItem temp = new DepartStatisticsItem();
            temp.setItemName(dicData.getDic_data_name());
            temp.setItemId(dicData.getId());
            tempList.add(temp);

            double sum = 0;
            for(Dept dept1 : depts){
                DepartStatisticsItem departStatisticsItem = tempMap.get(dept1.getDept_id());
                if(departStatisticsItem == null) {
                    departStatisticsItem = new DepartStatisticsItem();
                    departStatisticsItem.setDeptId(dept1.getDept_id());
                }else {
                    departStatisticsItem.setUrl(url+"&deptId="+dept1.getDept_id()+"&type_id="+dicData.getId());
                }
                departStatisticsItem.setItemName(dicData.getDic_data_name());
                departStatisticsItem.setItemId(dicData.getId());
                sum += departStatisticsItem.getVal();
                tempList.add(departStatisticsItem);
            }


            if(depts.size() > 1){
                DepartStatisticsItem last = new DepartStatisticsItem();
                last.setItemId(dicData.getId());
                last.setItemName(dicData.getDic_data_name());
                last.setVal(sum);
                last.setUrl("");
                tempList.add(last);
            }
            list.add(tempList);
        }

        return list;
    }


















    /**
     * 项目结算单统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> queryMonthlyStatement20(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> receivedPayments =
                departStatisticsService.queryMonthlyStatement20(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map0 = PubMethod.list2Map(receivedPayments);
        return handleStatistics( depts,  map0,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=20"+searchStr,"");
    }


    /**
     * 项目未确认结算单统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> queryMonthlyStatement22(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> receivedPayments =
                departStatisticsService.queryMonthlyStatement22(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map0 = PubMethod.list2Map(receivedPayments);
        List<DepartStatisticsItem> list =  handleStatistics( depts,  map0,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=22"+searchStr,"");
        if(list != null && !list.isEmpty()){
            for(DepartStatisticsItem dsi : list){
                dsi.setItemName(unconfirmedItem4MS);
                dsi.setItemFormatter(EnumCellFormat.red.getCode());
            }
        }
        return list;
    }


    /**
     * 项目回款统计(总回款，未减去税额)
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getReceivedPayments10(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> receivedPayments =
                departStatisticsService.queryReceivedPayments10(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map0 = PubMethod.list2Map(receivedPayments);
        return handleStatistics( depts,  map0,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=10"+searchStr,"");
    }
    protected List<DepartStatisticsItem> getReceivedPayments13(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> receivedPayments =
                departStatisticsService.queryReceivedPayments13(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map0 = PubMethod.list2Map(receivedPayments);
        return handleStatistics( depts,  map0,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=13"+searchStr,"");
    }

    /**
     * 项目未确认回款统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getReceivedPayments12(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> receivedPayments =
                departStatisticsService.queryReceivedPayments12(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map0 = PubMethod.list2Map(receivedPayments);
        List<DepartStatisticsItem> list =  handleStatistics( depts,  map0,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=12"+searchStr,"");
        if(list != null && !list.isEmpty()){
            for(DepartStatisticsItem dsi : list){
                dsi.setItemName(unconfirmedItem4RP);
                dsi.setItemFormatter(EnumCellFormat.red.getCode());
            }
        }
        return list;
    }


    /**
     * 项目未确认回款统计 ,  月份按照收款日期
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getReceivedPayments14(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> receivedPayments =
                departStatisticsService.queryReceivedPayments14(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map0 = PubMethod.list2Map(receivedPayments);
        List<DepartStatisticsItem> list =  handleStatistics( depts,  map0,0,"/DepartStatisticsAction.do?method=queryCostsDetail&x=14"+searchStr,"");
        if(list != null && !list.isEmpty()){
            for(DepartStatisticsItem dsi : list){
                dsi.setItemName(unconfirmedItem4RP);
                dsi.setItemFormatter(EnumCellFormat.red.getCode());
            }
        }
        return list;
    }


    /**
     * 项目人工成本 , 所有成本 ，  工资+税+社保
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getProjectStaffCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> projectStaffCosts =
                departStatisticsService.queryProjectStaffCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map30 = PubMethod.list2Map(projectStaffCosts);
        return handleStatistics( depts,  map30,3,"/DepartStatisticsAction.do?method=queryCostsDetail&x=51"+searchStr,"");
    }


    /**
     * 项目报销成本 , 所有的报销
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getReimburseCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> reimburseCosts =
                departStatisticsService.queryReimburseCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map90 = PubMethod.list2Map(reimburseCosts);
        return handleStatistics( depts,  map90,9,"/DepartStatisticsAction.do?method=queryCostsDetail&x=30"+searchStr,"B");
    }

    /**
     * 项目报销明细
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<List<DepartStatisticsItem>> getReimburseCostDetails(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        DicData searchDicDeta = new DicData();
        searchDicDeta.setDic_type_id(EnumDicType.REIMBURSE_ITEM.name());
        List<DicData> reimburseCostTypes = dicDataService.getAllDicDataByType(searchDicDeta);
        List<DepartStatisticsItem> reimburseCostDetails =
                departStatisticsService.queryReimburseCostDetails(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, Map<String, DepartStatisticsItem>> maprc = PubMethod.list2Map2(reimburseCostDetails);
        List<List<DepartStatisticsItem>> list =handleDetails( depts, reimburseCostTypes, maprc,"/DepartStatisticsAction.do?method=queryCostsDetail&x=30"+searchStr);
        return list;
    }


    /**
     * 项目支付信息， 付款金额为实际付款，如果没有付款，付款金额为应付款
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getProjectExpends40(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> projectExpends =
                departStatisticsService.queryProjectExpends40(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map100 = PubMethod.list2Map(projectExpends);
        return handleStatistics( depts,  map100,10,"/DepartStatisticsAction.do?method=queryCostsDetail&x=40"+searchStr,"B");
    }

    protected List<DepartStatisticsItem> getProjectExpends41(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> projectExpends =
                departStatisticsService.queryProjectExpends41(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map100 = PubMethod.list2Map(projectExpends);
        return handleStatistics( depts,  map100,10,"/DepartStatisticsAction.do?method=queryCostsDetail&x=41"+searchStr,"B");
    }

    protected List<DepartStatisticsItem> getProjectExpends42(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> projectExpends =
                departStatisticsService.queryProjectExpends42(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map100 = PubMethod.list2Map(projectExpends);
        return handleStatistics( depts,  map100,10,"/DepartStatisticsAction.do?method=queryCostsDetail&x=42"+searchStr,"B");
    }


    /**
     * 销售成本统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @return
     */
    protected List<DepartStatisticsItem> getSalseCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> salseCosts =
                departStatisticsService.querySalseCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map110 = PubMethod.list2Map(salseCosts);
        return handleStatistics( depts,  map110,11,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=100"+searchStr,"B");
    }

    /**
     * 销售成本明细
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<List<DepartStatisticsItem>> getSalesCostDetails(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        DicData searchDicDeta = new DicData();
        searchDicDeta.setDic_type_id(EnumDicType.SALES_COSTS.name());
        List<DicData> salesCostTypes = dicDataService.getAllDicDataByType(searchDicDeta);
        List<DepartStatisticsItem> salesCostDetails =
                departStatisticsService.querySalseCostsDetail(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, Map<String, DepartStatisticsItem>> mapsc = PubMethod.list2Map2(salesCostDetails);
        return handleDetails( depts, salesCostTypes, mapsc,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=100"+searchStr);

    }


    /**
     * 部门成本统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @return
     */
    protected List<DepartStatisticsItem> getDepartCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit , String searchStr) {
        List<DepartStatisticsItem> departCosts =
                departStatisticsService.queryDepartCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map120 = PubMethod.list2Map(departCosts);
        return handleStatistics( depts,  map120,12,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=101"+searchStr,"B");
    }


    /**
     * 部门成本明细
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<List<DepartStatisticsItem>> getDepartCostDetails(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        DicData searchDicDeta = new DicData();
        searchDicDeta.setDic_type_id(EnumDicType.DEPART_MANAG_COSTS.name());
        List<DicData> departCostTypes = dicDataService.getAllDicDataByType(searchDicDeta);
        List<DepartStatisticsItem> departCostDetails =
                departStatisticsService.queryDepartCostsDetail(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, Map<String, DepartStatisticsItem>> mapdc = PubMethod.list2Map2(departCostDetails);
        return handleDetails( depts, departCostTypes, mapdc,"/DepartStatisticsAction.do?method=queryDepartCostsDetail&x=101"+searchStr);
    }






    /**
     * 总部人员成本统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @return
     */
    protected List<DepartStatisticsItem> getOtherStaffCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit , String searchStr) {
        List<DepartStatisticsItem> otherStaffCosts = departStatisticsService.queryOtherStaffCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map130 = PubMethod.list2Map(otherStaffCosts);
        return handleStatistics( depts,  map130,13,"/DepartStatisticsAction.do?method=queryOtherStaffCostDetail"+searchStr,"B");
    }




    /**
     * 部门销售成本统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getSalesStaffCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> otherStaffCosts = departStatisticsService.querySalesStaffCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map130 = PubMethod.list2Map(otherStaffCosts);
        return handleStatistics( depts,  map130,-1,"/DepartStatisticsAction.do?method=querySalesStaffCostDetail"+searchStr,"B");


    }


    /**
     * 部门管理成本统计
     * @param searchStatistics
     * @param depts
     * @param userPermit
     * @param searchStr
     * @return
     */
    protected List<DepartStatisticsItem> getManageStaffCosts(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit, String searchStr) {
        List<DepartStatisticsItem> otherStaffCosts = departStatisticsService.queryManageStaffCosts(searchStatistics, userPermit, PubMethod.getPagerByAll(DepartStatisticsItem.class)).getResultList();
        Map<String, DepartStatisticsItem> map130 = PubMethod.list2Map(otherStaffCosts);
        return handleStatistics( depts,  map130,-1,"/DepartStatisticsAction.do?method=queryManageStaffCostDetail"+searchStr,"B");


    }




}
