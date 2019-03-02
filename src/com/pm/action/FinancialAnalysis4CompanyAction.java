package com.pm.action;

import com.common.actions.BaseAction;
import com.pm.service.IAnalysisService;
import com.pm.service.IDeptService;
import com.pm.service.IDicDataService;
import com.pm.service.IRoleService;
import com.pm.util.constant.EnumPermit;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisResultTable;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.UserPermit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 企业分析
 * @author Administrator
 */
@Controller
@RequestMapping(value = "FinancialAnalysis4CompanyAction.do")
public class FinancialAnalysis4CompanyAction extends BaseAction {

    @Autowired
    IDeptService deptService;

    @Autowired
    private IDicDataService dicDataService;

    @Autowired
    private IRoleService roleService;

    ;

    @Autowired
    private IAnalysisService analysisService;


    private static List<String> tableName = new ArrayList<String>();

    private static List<String> items10 = new ArrayList<String>();
    private static List<String> items20 = new ArrayList<String>();
    private static List<String> items30 = new ArrayList<String>();

    static{
        tableName.add("");
        tableName.add("流动资产负债分析");
        tableName.add("公司利润表分析");
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
        items20.add("部门毛利润");
        items20.add("公共管理费用");
        items20.add("公司总利润");


        items30.add("");
        items30.add("公司回款总额");
        items30.add("公司支出总额");
        items30.add("公司现金流");


    }


    @RequestMapping(params = "method=list")
    public String list(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.COMPANYFINANCIALANALYSISVIEW.getId());
        paramprocess(analysisSearch , request);

        List<AnalysisResultTable> arts = getAnalysisList(analysisSearch,userPermit);
        request.setAttribute("arts", arts);

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

        arts.add(getAnalysis1(analysisSearch , userPermit));
        arts.add(getAnalysis2(analysisSearch , userPermit));
        arts.add(getAnalysis3(analysisSearch , userPermit));
        arts.add(getAnalysis4(analysisSearch , userPermit));

        return arts;
    }


    /**
     * 流动资产负债分析
     * @return
     */
    private AnalysisResultTable getAnalysis1(AnalysisSearch analysisSearch, UserPermit userPermit){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(1) );

        AnalysisResult ar10 = analysisService.queryMonthlyStatements(analysisSearch ,userPermit );
        AnalysisResult ar20 = analysisService.queryReceivedPayments(analysisSearch ,userPermit );
        AnalysisResult ar30 = analysisService.queryReceivables(analysisSearch ,userPermit );
        AnalysisResult ar40 = analysisService.queryInvoices(analysisSearch ,userPermit );

        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();
        ars.add(ar10);
        ars.add(ar20);
        ars.add(ar30);
        ars.add(ar40);

        art.setResult(ars);

        return art;
    }


    /**
     * 公司利润表分析
     * @return
     */
    private AnalysisResultTable getAnalysis2(AnalysisSearch analysisSearch, UserPermit userPermit){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(2) );

        AnalysisResult ar10 = analysisService.queryReceivedPayments(analysisSearch ,userPermit );
        //todo 处理税


        AnalysisResult ar20 = analysisService.queryProjectStaffCosts(analysisSearch ,userPermit );
        AnalysisResult ar30 = analysisService.queryReimburseCosts(analysisSearch ,userPermit );
        AnalysisResult ar40 = analysisService.queryProjectExpends(analysisSearch ,userPermit );

        AnalysisResult ar50 = analysisService.querySalseCosts(analysisSearch ,userPermit );
        AnalysisResult ar60 = analysisService.queryDepartCosts(analysisSearch ,userPermit );
        AnalysisResult ar70 = analysisService.queryOtherStaffCosts(analysisSearch ,userPermit );


        AnalysisResult ar80 = new AnalysisResult();
        //部门毛利率

        AnalysisResult ar90 = analysisService.queryCommonCosts(analysisSearch ,userPermit );

        AnalysisResult ar100 = new AnalysisResult();
        //公司利润



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

        art.setResult(ars);

        return art;
    }


    /**
     * 现金流分析
     * @return
     */
    private AnalysisResultTable getAnalysis3(AnalysisSearch analysisSearch, UserPermit userPermit){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(3) );

        AnalysisResult ar10 = analysisService.queryMonthlyStatements(analysisSearch ,userPermit );
        AnalysisResult ar20 = analysisService.queryReceivedPayments(analysisSearch ,userPermit );
        AnalysisResult ar30 = new AnalysisResult();

        List<AnalysisResult> ars = new ArrayList<AnalysisResult>();
        ars.add(ar10);
        ars.add(ar20);
        ars.add(ar30);

        art.setResult(ars);

        return art;
    }



    /**
     * 管理费用对比分析
     * @return
     */
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
