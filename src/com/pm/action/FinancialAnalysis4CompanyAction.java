package com.pm.action;

import com.alibaba.fastjson.JSON;
import com.common.actions.BaseAction;
import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.DateUtils;
import com.common.utils.NumberKit;
import com.pm.domain.business.Statistics;
import com.pm.service.*;
import com.pm.util.AnalysisUtil;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.exports.BusinessExExcel;
import com.pm.util.excel.Column;
import com.pm.util.excel.EnumCellFormat;
import com.pm.util.excel.ThreadLocalModifyColumn;
import com.pm.vo.*;
import com.pm.vo.echarts.Option4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 企业分析
 * @author Administrator
 */
@Controller
@RequestMapping(value = "FinancialAnalysis4CompanyAction.do")
public class FinancialAnalysis4CompanyAction extends FinancialAnalysisChart {

    @Autowired
    IDeptService deptService;

    @Autowired
    private IDicDataService dicDataService;

    @Autowired
    private IRoleService roleService;



    @Autowired
    private IAnalysisService analysisService;


    @Autowired
    private IReceivablesStatisticsService receivablesStatisticsService;


    private static List<String> tableName = new ArrayList<String>();

    private static List<String> items10 = new ArrayList<String>();
    private static List<String> items20 = new ArrayList<String>();
    private static List<String> items30 = new ArrayList<String>();

    static{
        tableName.add("");
        tableName.add("流动资产负债分析");
        tableName.add("公司账面利润分析");
        tableName.add("现金流分析");
        tableName.add("管理费用分析");


        items10.add("");
        items10.add("结算单收入");
        items10.add("回款");
        items10.add("应收账款");
        items10.add("开具发票");


        items20.add("");
        items20.add("项目不含税收入");
        items20.add("项目人工成本");
        items20.add("项目报销成本");
        items20.add("供应商应付款额");
        items20.add("项目销售总费用");
        items20.add("项目管理总费用");
        items20.add("总部人员成本");
        items20.add("部门总成本");
        items20.add("部门毛利润");
        items20.add("公共管理费用");
        items20.add("公司总利润");


        items30.add("");
        items30.add("公司回款总额");
        items30.add("公司支出总额");
        items30.add("公司现金流");


    }




    @RequestMapping(params = "method=toChart1")
    public String toChart1(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        String startTimeQuantum = DateUtils.getTimeQuantum(analysisSearch.getMonth1(),analysisSearch.getMonth2());
        String endTimeQuantum =  DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100);
        List<AnalysisResultTable> arts = (List<AnalysisResultTable>)request.getSession().getAttribute(this.getClass().getSimpleName());
        Option4 option =  toOption(startTimeQuantum , endTimeQuantum , arts.get(0) , false);
        String o = JSON.toJSONString(option).replaceAll("xaxis", "xAxis").replaceAll("yaxis", "yAxis");
        request.setAttribute("o", o);
        return "analysis/analysis_simple_chart";
    }



    @RequestMapping(params = "method=toChart2")
    public String toChart2(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        String startTimeQuantum = DateUtils.getTimeQuantum(analysisSearch.getMonth1(),analysisSearch.getMonth2());
        String endTimeQuantum =  DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100);
        List<AnalysisResultTable> arts = (List<AnalysisResultTable>)request.getSession().getAttribute(this.getClass().getSimpleName());
        Option4 option =  toOption(startTimeQuantum , endTimeQuantum , arts.get(1) , true);
        String o = JSON.toJSONString(option).replaceAll("xaxis", "xAxis").replaceAll("yaxis", "yAxis");
        request.setAttribute("o", o);
        return "analysis/analysis_all_chart";
    }



    @RequestMapping(params = "method=toChart3")
    public String toChart3(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        String startTimeQuantum = DateUtils.getTimeQuantum(analysisSearch.getMonth1(),analysisSearch.getMonth2());
        String endTimeQuantum =  DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100);
        List<AnalysisResultTable> arts = (List<AnalysisResultTable>)request.getSession().getAttribute(this.getClass().getSimpleName());
        Option4 option =  toOption(startTimeQuantum , endTimeQuantum , arts.get(2) , false);
        String o = JSON.toJSONString(option).replaceAll("xaxis", "xAxis").replaceAll("yaxis", "yAxis");
        request.setAttribute("o", o);
        return "analysis/analysis_simple_chart";
    }

    @RequestMapping(params = "method=export")
    public void export(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){


        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.COMPANYFINANCIALANALYSISVIEW.getId());
        paramprocess(analysisSearch , request);

        if(analysisSearch.getMonth1() == 0 || analysisSearch.getMonth2() == 0) {
            //初始化查询月份
            Date date2 = new Date();
            Date date1 = DateKit.getYearStart(date2) ;
            analysisSearch.setMonth1(Integer.parseInt(DateKit.fmtDateToStr(date1 , "yyyyMM")));
            analysisSearch.setMonth2(Integer.parseInt(DateKit.fmtDateToStr(date2 , "yyyyMM")));
        }

        List<AnalysisResultTable> arts = getAnalysisList(analysisSearch, userPermit);


        try{

            List<Column> modifyColumns = new ArrayList<Column>();
            Column column1 = new Column();
            column1.setNumber(3);
            column1.setName(DateUtils.getTimeQuantum(analysisSearch.getMonth1(),analysisSearch.getMonth2()));
            Column column2 = new Column();
            column2.setNumber(4);
            column2.setName(DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100));
            Column column3 = new Column();
            column3.setNumber(6);
            column3.setFormats(new String[]{EnumCellFormat.bold.getCode(),EnumCellFormat.percent.getCode()});
            modifyColumns.add(column1);
            modifyColumns.add(column2);
            modifyColumns.add(column3);
            ThreadLocalModifyColumn.setColumns(modifyColumns);

            List<List<?>> lists = new ArrayList<>();
            lists.add(arts.get(0).getResult());
            lists.add(arts.get(1).getResult());
            lists.add(arts.get(2).getResult());

            List<String> headers = new ArrayList<String>(1);
            headers.add("公司层面财务分析");
            BusinessExExcel.export(res, headers , lists,false);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ThreadLocalModifyColumn.setColumns(null);
        }
    }




    @RequestMapping(params = "method=list")
    public String list(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.COMPANYFINANCIALANALYSISVIEW.getId());
        paramprocess(analysisSearch , request);

        if(analysisSearch.getMonth1() == 0 || analysisSearch.getMonth2() == 0) {
            //初始化查询月份
            Date date2 = new Date();
            Date date1 = DateKit.getYearStart(date2) ;
            analysisSearch.setMonth1(Integer.parseInt(DateKit.fmtDateToStr(date1 , "yyyyMM")));
            analysisSearch.setMonth2(Integer.parseInt(DateKit.fmtDateToStr(date2 , "yyyyMM")));
        }

        request.setAttribute("startTimeQuantum", DateUtils.getTimeQuantum(analysisSearch.getMonth1(),analysisSearch.getMonth2()));
        List<AnalysisResultTable> arts = getAnalysisList(analysisSearch, userPermit);
        request.setAttribute("arts", arts);
        request.getSession().setAttribute(this.getClass().getSimpleName() , arts);
        request.setAttribute("endTimeQuantum", DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100));


        request.setAttribute("analysisSearch", analysisSearch);

        return "analysis/analysis_company_list";
    }

    /**
     *
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    private List<AnalysisResultTable> getAnalysisList(AnalysisSearch analysisSearch, UserPermit userPermit) {

        List<AnalysisResultTable> arts = new ArrayList<AnalysisResultTable>();

        AnalysisResultTable ar1 = getAnalysis1(analysisSearch , userPermit);
        arts.add(ar1);

        AnalysisResultTable ar2 = getAnalysis2(analysisSearch , userPermit);
        arts.add(ar2);

        AnalysisResultTable ar3 = getAnalysis3(ar2 ,analysisSearch , userPermit);
        arts.add(ar3);

        //AnalysisResultTable ar4 = getAnalysis4(analysisSearch , userPermit);
        //arts.add(ar4);

        return arts;
    }


    /**
     * 流动资产负债分析
     * @return
     */
    private AnalysisResultTable getAnalysis1(AnalysisSearch analysisSearch, UserPermit userPermit){

        int month1 = analysisSearch.getMonth1();
        int month2 = analysisSearch.getMonth2();
        putSearchDate(analysisSearch, month1, month2);


        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(1) );


        AnalysisResult ar10 = analysisService.queryMonthlyStatements(analysisSearch ,userPermit );
        AnalysisResult ar20 = analysisService.queryReceivedPayments(analysisSearch ,userPermit );


        Statistics statistics = new Statistics();
        statistics.setMonth2(month2);
        Pager<Statistics> all1 = receivablesStatisticsService.queryByAll(statistics , userPermit , PubMethod.getPagerByAll( Statistics.class));


        statistics.setMonth2(month2-100);
        Pager<Statistics> all2 = receivablesStatisticsService.queryByAll(statistics , userPermit , PubMethod.getPagerByAll( Statistics.class));



        AnalysisResult ar30 = new AnalysisResult();
        ar30.setCurr_statistics_amount(all1.getResultList().get(0).getStatistics_amount());
        ar30.setPre_statistics_amount(all2.getResultList().get(0).getStatistics_amount());
        AnalysisUtil.processesult(ar30);


        AnalysisResult ar40 = analysisService.queryInvoices(analysisSearch ,userPermit );

        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();
        ars.add(ar10);
        ars.add(ar20);
        ars.add(ar30);
        ars.add(ar40);

        art.setResult(ars);


        int index = 1;
        for(AnalysisResult ar : ars){
            ar.setItem_name(items10.get(index++));
            ar.setAnalysis_type(art.getLabel());
        }

        analysisSearch.setMonth1(month1);
        analysisSearch.setMonth2(month2);

        return art;
    }



    /**
     * 公司账面利润分析
     * 按照月份查询
     * @return
     */
    private AnalysisResultTable getAnalysis2(AnalysisSearch analysisSearch, UserPermit userPermit){

        analysisSearch.setDate1(null);
        analysisSearch.setDate2(null);

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(2) );

        double rate = computeTaxRate();
        AnalysisResult ar10 = analysisService.queryMonthlyStatements(analysisSearch ,userPermit );
        ar10.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(ar10.getPre_statistics_amount() * ( 1- rate)));
        ar10.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(ar10.getCurr_statistics_amount() * ( 1- rate)));
        AnalysisUtil.processesult(ar10);


        AnalysisResult ar20 = analysisService.queryProjectStaffCosts(analysisSearch ,userPermit );
        AnalysisResult ar30 = analysisService.queryReimburseCosts(analysisSearch ,userPermit );
        AnalysisResult ar40 = analysisService.queryProjectExpends(analysisSearch ,userPermit );

        AnalysisResult ar50 = analysisService.querySalseCosts(analysisSearch ,userPermit );
        AnalysisResult ar60 = analysisService.queryDepartCosts(analysisSearch ,userPermit );
        AnalysisResult ar70 = analysisService.queryOtherStaffCosts(analysisSearch ,userPermit );


        AnalysisResult ar80 = new AnalysisResult();
        ar80.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(
                 ar20.getPre_statistics_amount() + ar30.getPre_statistics_amount() + ar40.getPre_statistics_amount()
                 + ar50.getPre_statistics_amount() + ar60.getPre_statistics_amount() + ar70.getPre_statistics_amount()
        ));
        ar80.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar20.getCurr_statistics_amount() + ar30.getCurr_statistics_amount() +ar40.getCurr_statistics_amount()
                +ar50.getCurr_statistics_amount() + ar60.getCurr_statistics_amount() + ar70.getCurr_statistics_amount()
        ));
        AnalysisUtil.processesult(ar80);


        AnalysisResult ar90 = new AnalysisResult();
        ar90.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar10.getPre_statistics_amount() - ar80.getPre_statistics_amount()
        ));
        ar90.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar10.getCurr_statistics_amount() - ar80.getCurr_statistics_amount()
        ));
        AnalysisUtil.processesult(ar90);


        AnalysisResult ar100 = analysisService.queryCommonCosts(analysisSearch ,userPermit );

        AnalysisResult ar110 = new AnalysisResult();
        ar110.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar90.getPre_statistics_amount() - ar100.getPre_statistics_amount()
        ));
        ar110.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar90.getCurr_statistics_amount() - ar100.getCurr_statistics_amount()
        ));
        AnalysisUtil.processesult(ar110);



        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();
        ars.add(ar10);
        ars.add(ar20);
        ars.add(ar30);
        ars.add(ar40);
        ars.add(ar50);
        ars.add(ar60);
        ars.add(ar70);
        ars.add(ar80);
        ars.add(ar90);
        ars.add(ar100);
        ars.add(ar110);

        art.setResult(ars);

        int index = 1;
        for(AnalysisResult ar : ars){
            ar.setItem_name(items20.get(index++));
            ar.setAnalysis_type(art.getLabel());
        }




        return art;
    }


    /**
     * 现金流分析
     * @return
     */
    private AnalysisResultTable getAnalysis3(AnalysisResultTable art2,AnalysisSearch analysisSearch, UserPermit userPermit){


        int month1 = analysisSearch.getMonth1();
        int month2 = analysisSearch.getMonth2();
        putSearchDate(analysisSearch, month1, month2);

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(3) );

        AnalysisResult ar10 = analysisService.queryReceivedPayments(analysisSearch ,userPermit );

        AnalysisResult ar20 = new AnalysisResult();
        AnalysisResult departCost = art2.getResult().get(7);
        AnalysisResult commonCost = art2.getResult().get(9);

        //应付
        AnalysisResult shouldPay = art2.getResult().get(3);

        //实付
        AnalysisResult actualPay = analysisService.queryProjectExpendsByPay(analysisSearch ,userPermit );

        ar20.setCurr_statistics_amount(departCost.getCurr_statistics_amount() +
                commonCost.getCurr_statistics_amount()-
                shouldPay.getCurr_statistics_amount() +
                actualPay.getCurr_statistics_amount()
        );
        ar20.setPre_statistics_amount(departCost.getPre_statistics_amount() +
                        commonCost.getPre_statistics_amount()-
                        shouldPay.getPre_statistics_amount() +
                        actualPay.getPre_statistics_amount()
                );
        AnalysisUtil.processesult(ar20);


        AnalysisResult ar30 = new AnalysisResult();
        ar30.setPre_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar10.getPre_statistics_amount() - ar20.getPre_statistics_amount()
        ));
        ar30.setCurr_statistics_amount(NumberKit.getNumberFormatByDouble(
                ar10.getCurr_statistics_amount() - ar20.getCurr_statistics_amount()
        ));
        AnalysisUtil.processesult(ar30);


        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();
        ars.add(ar10);
        ars.add(ar20);
        ars.add(ar30);

        art.setResult(ars);


        int index = 1;
        for(AnalysisResult ar : ars){
            ar.setItem_name(items30.get(index++));
            ar.setAnalysis_type(art.getLabel());
        }


        analysisSearch.setMonth1(month1);
        analysisSearch.setMonth2(month2);

        return art;
    }



    /**
     * 管理费用对比分析
     * @return
     */
    @Deprecated
    private AnalysisResultTable getAnalysis4(AnalysisSearch analysisSearch, UserPermit userPermit){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(4) );


        List<AnalysisResult> ars = this.analysisService.queryDepartCostDetail(analysisSearch ,userPermit);


        art.setResult(ars);

        return art;
    }



    private void paramprocess(AnalysisSearch analysisSearch, HttpServletRequest request){

        if(analysisSearch.getDept_id() == null || analysisSearch.getDept_id().isEmpty()) {
            analysisSearch.setDept_id(request.getParameter("dept.dept_id"));
        }

        if(analysisSearch.getDept_name() == null || analysisSearch.getDept_name().isEmpty()) {
            analysisSearch.setDept_name(request.getParameter("dept.dept_name"));
        }
    }


}
