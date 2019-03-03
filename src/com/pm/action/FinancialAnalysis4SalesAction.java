package com.pm.action;

import com.common.actions.BaseAction;
import com.common.utils.BeanKit;
import com.common.utils.DateKit;
import com.common.utils.DateUtils;
import com.pm.service.IAnalysisDepartService;
import com.pm.service.IAnalysisSalesService;
import com.pm.service.IDeptService;
import com.pm.service.IRoleService;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.BusinessExExcel;
import com.pm.util.excel.Column;
import com.pm.util.excel.EnumCellFormat;
import com.pm.util.excel.ThreadLocalModifyColumn;
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
import java.util.Date;
import java.util.List;


/**
 * 销售分析
 * @author Administrator
 */
@Controller
@RequestMapping(value = "FinancialAnalysis4SalesAction.do")
public class FinancialAnalysis4SalesAction extends BaseAction {

    @Autowired
    IDeptService deptService;


    @Autowired
    private IRoleService roleService;

    ;

    @Autowired
    private IAnalysisSalesService analysisSalesService;


    private static List<String> tableName = new ArrayList<String>();


    static{
        tableName.add("");
        tableName.add("销售收款表分析");
        tableName.add("销售支出表分析");
        tableName.add("销售现金流分析");



    }



    @RequestMapping(params = "method=export")
    public void export(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){


        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTMENTFINANCIALANALYSISVIEW.getId());
        paramprocess(analysisSearch , request);

        if(analysisSearch.getMonth1() == 0 || analysisSearch.getMonth2() == 0) {
            //初始化查询月份
            Date date2 = new Date();
            Date date1 = DateKit.getYearStart(date2) ;
            analysisSearch.setMonth1(Integer.parseInt(DateKit.fmtDateToStr(date1 , "yyyyMM")));
            analysisSearch.setMonth2(Integer.parseInt(DateKit.fmtDateToStr(date2 , "yyyyMM")));
        }

        List<AnalysisResultTable> arts = getAnalysisSalesList(analysisSearch, userPermit);


        try{

            List<Column> modifyColumns = new ArrayList<Column>();

            Column column0 = new Column();
            column0.setNumber(2);
            column0.setName("销售姓名");
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
            headers.add("销售层面财务分析");
            BusinessExExcel.export(res, headers , lists,false);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ThreadLocalModifyColumn.setColumns(null);
        }
    }




    @RequestMapping(params = "method=list")
    public String list(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.DEPARTMENTFINANCIALANALYSISVIEW.getId());
        paramprocess(analysisSearch , request);

        if(analysisSearch.getMonth1() == 0 || analysisSearch.getMonth2() == 0) {
            //初始化查询月份
            Date date2 = new Date();
            Date date1 = DateKit.getYearStart(date2) ;
            analysisSearch.setMonth1(Integer.parseInt(DateKit.fmtDateToStr(date1 , "yyyyMM")));
            analysisSearch.setMonth2(Integer.parseInt(DateKit.fmtDateToStr(date2 , "yyyyMM")));
        }

        request.setAttribute("startTimeQuantum", DateUtils.getTimeQuantum(analysisSearch.getMonth1(),analysisSearch.getMonth2()));
        List<AnalysisResultTable> arts = getAnalysisSalesList(analysisSearch, userPermit);
        request.setAttribute("arts", arts);
        request.setAttribute("endTimeQuantum", DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100));


        request.setAttribute("analysisSearch", analysisSearch);

        return "analysis/analysis_sales_list";
    }

    /**
     *
     * @param analysisSearch
     * @param userPermit
     * @return
     */
    private List<AnalysisResultTable> getAnalysisSalesList(AnalysisSearch analysisSearch, UserPermit userPermit) {

        List<AnalysisResultTable> arts = new ArrayList<AnalysisResultTable>();

        AnalysisResultTable ar1 = getAnalysis1(analysisSearch , userPermit);
        arts.add(ar1);

        AnalysisResultTable ar2 = getAnalysis2(analysisSearch , userPermit);
        arts.add(ar2);

        AnalysisResultTable ar3 = getAnalysis3(ar1,ar2 );
        arts.add(ar3);


        return arts;
    }


    /**
     * 部门项目收款表分析
     * @return
     */
    private AnalysisResultTable getAnalysis1(AnalysisSearch analysisSearch, UserPermit userPermit){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(1) );


        List<AnalysisResult> ars = analysisSalesService.querySalesReceivedPayments(analysisSearch ,userPermit );
        art.setResult(ars);


        if(art.getResult() != null) {
            for (AnalysisResult ar : ars) {
                ar.setAnalysis_type(art.getLabel());
            }
        }

        return art;
    }


    /**
     * 部门项目支出表分析
     * @return
     */
    private AnalysisResultTable getAnalysis2(AnalysisSearch analysisSearch, UserPermit userPermit){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(2) );


        List<AnalysisResult> ars = analysisSalesService.querySalesAllCosts(analysisSearch ,userPermit );
        art.setResult(ars);

        if(art.getResult() != null) {
            for (AnalysisResult ar : art.getResult()) {
                ar.setAnalysis_type(art.getLabel());
            }
        }

        return art;
    }


    /**
     * 部门项目现金流分析
     * @return
     */
    private AnalysisResultTable getAnalysis3(AnalysisResultTable art1,AnalysisResultTable art2){

        AnalysisResultTable art = new AnalysisResultTable();
        art.setLabel( tableName.get(3) );

        List<AnalysisResult> ars1 = new ArrayList<AnalysisResult>();
        if(art1 != null && art1.getResult() != null) {
            for(AnalysisResult temp : art1.getResult()) {
                AnalysisResult ar = new AnalysisResult();
                BeanKit.copyProperties(temp, ar);
                ars1.add(ar);
            }
        }

        List<AnalysisResult> ars2 = new ArrayList<AnalysisResult>();
        if(art2 != null && art2.getResult() != null) {
            for(AnalysisResult temp : art2.getResult()) {
                AnalysisResult ar = new AnalysisResult();
                BeanKit.copyProperties(temp, ar);
                ars2.add(ar);
            }
        }

        List<AnalysisResult> ars = analysisSalesService.queryCashFlow(ars1 ,ars2 );
        art.setResult(ars);


        if(art.getResult() != null) {
            for (AnalysisResult ar : art.getResult()) {
                ar.setAnalysis_type(art.getLabel());
            }
        }

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
