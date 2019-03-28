package com.pm.action;

import com.common.beans.Pager;
import com.pm.domain.business.Statistics;
import com.pm.domain.business.StatisticsDetail;
import com.pm.domain.system.Dept;
import com.pm.util.PubMethod;
import com.pm.util.excel.exports.BusinessExcel;
import com.pm.util.excel.exports.DepartStatisticsExcel;
import com.pm.vo.DepartStatisticsItem;
import com.pm.vo.UserPermit;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public abstract class DepartStatisticsBaseAction extends DepartStatisticsAbstractAction {


    protected String getClassName(){
        return this.getClass().getSimpleName();
    }

    protected abstract String getPermitId() ;


    protected abstract List<List<DepartStatisticsItem>> getDepartStatisticsList(Statistics searchStatistics, List<Dept> depts, UserPermit userPermit) ;









    /**
     * 明细界面
     *
     * @param statistics
     * @param res
     * @param request
     * @return
     */
    @RequestMapping(params = "method=queryCostsDetail")
    public String queryCostsDetail(Statistics statistics, HttpServletResponse res, HttpServletRequest request) {
        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());
        Pager<StatisticsDetail> pager = departStatisticsService.queryCostsDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
        PubMethod.setRequestPager(request, pager, StatisticsDetail.class);
        request.setAttribute("actionName" , getClassName());
        return "departstatistics/costs_detail_list";
    }

    /**
     * 明细导出
     *
     * @param statistics
     * @param res
     * @param request
     */
    @RequestMapping(params = "method=excelCostsDetail")
    public void excelCostsDetail(Statistics statistics, HttpServletResponse res, HttpServletRequest request) {
        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());
        Pager<StatisticsDetail> pager = departStatisticsService.queryCostsDetail(statistics, userPermit, PubMethod.getPagerByAll(StatisticsDetail.class));

        try {
            BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 部门费用明细
     *
     * @param statistics
     * @param res
     * @param request
     * @return
     */
    @RequestMapping(params = "method=queryDepartCostsDetail")
    public String queryDepartCostsDetail(Statistics statistics, HttpServletResponse res, HttpServletRequest request) {
        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());
        Pager<StatisticsDetail> pager = departStatisticsService.queryDepartDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
        PubMethod.setRequestPager(request, pager, StatisticsDetail.class);
        request.setAttribute("actionName" , getClassName());
        return "departstatistics/departcosts_detail_list";
    }

    /**
     * 部门费用明细导出excel
     *
     * @param statistics
     * @param res
     * @param request
     */
    @RequestMapping(params = "method=excelDepartCostsDetail")
    public void excelDepartCostsDetail(Statistics statistics, HttpServletResponse res, HttpServletRequest request) {
        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());
        Pager<StatisticsDetail> pager = departStatisticsService.queryDepartDetail(statistics, userPermit, PubMethod.getPagerByAll(StatisticsDetail.class));

        try {
            BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
        } catch (Exception e) {

        }
    }


    /**
     * 总部人员成本明细
     *
     * @param statistics
     * @param res
     * @param request
     * @return
     */
    @RequestMapping(params = "method=queryOtherStaffCostDetail")
    public String queryOtherStaffCostDetail(Statistics statistics, HttpServletResponse res, HttpServletRequest request) {
        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());
        Pager<StatisticsDetail> pager = departStatisticsService.queryOtherStaffCostDetail(statistics, userPermit, PubMethod.getPager(request, StatisticsDetail.class));
        PubMethod.setRequestPager(request, pager, StatisticsDetail.class);
        request.setAttribute("actionName" , getClassName());
        return "departstatistics/other_staff_cost_detail_list";
    }


    /**
     * 总部人员成本明细导出excel
     *
     * @param statistics
     * @param res
     * @param request
     */
    @RequestMapping(params = "method=excelOtherStaffCostDetail")
    public void excelOtherStaffCostDetail(Statistics statistics, HttpServletResponse res, HttpServletRequest request) {
        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());
        Pager<StatisticsDetail> pager = departStatisticsService.queryOtherStaffCostDetail(statistics, userPermit, PubMethod.getPagerByAll(StatisticsDetail.class));

        try {
            BusinessExcel.export(res, null, pager.getResultList(), StatisticsDetail.class);
        } catch (Exception e) {

        }
    }


    @RequestMapping(params = "method=list")
    public String list(Statistics searchStatistics, HttpServletResponse res, HttpServletRequest request) {

        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());


        processSearch(searchStatistics, request);

        List<Dept> depts = getDepts(searchStatistics, userPermit);

        request.setAttribute("statistics1", searchStatistics);

        request.setAttribute("datass", getDepartStatisticsList(searchStatistics, depts, userPermit));
        request.setAttribute("depts", depts);
        request.setAttribute("deptsize", depts.size());
        request.setAttribute("actionName" , getClassName());
        return "departstatistics/depart_statistics_list";
    }


    @RequestMapping(params = "method=export")
    public void export(Statistics searchStatistics, HttpServletResponse res, HttpServletRequest request) {

        UserPermit userPermit = this.getUserPermit(request, roleService, getPermitId());


        processSearch(searchStatistics, request);

        List<Dept> depts = getDepts(searchStatistics, userPermit);

        if (depts == null || depts.isEmpty()) {
            throw new RuntimeException("没有数据!");
        }

        List<DepartStatisticsItem> list = new ArrayList<DepartStatisticsItem>();
        DepartStatisticsItem tempItem = new DepartStatisticsItem();
        list.add(tempItem);
        for (Dept tempDept : depts) {
            DepartStatisticsItem item = new DepartStatisticsItem();
            item.setItemName(tempDept.getDept_name());
            item.setItemFormatter("B");
            list.add(item);
        }
        if (depts.size() > 1) {
            DepartStatisticsItem item = new DepartStatisticsItem();
            item.setItemName("总计");
            item.setItemFormatter("B");
            list.add(item);
        }


        try {
            List<List<DepartStatisticsItem>> lists = getDepartStatisticsList(searchStatistics, depts, userPermit);
            lists.add(0, list);
            DepartStatisticsExcel.export(res, null, lists);
        } catch (Exception e) {

        }
    }
    
    
    
}
