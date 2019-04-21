package com.pm.action;

import com.common.beans.Pager;
import com.common.utils.DateKit;
import com.common.utils.DateUtils;
import com.common.utils.NumberKit;
import com.pm.domain.business.Statistics;
import com.pm.service.*;
import com.pm.util.AnalysisUtil;
import com.pm.util.PubMethod;
import com.pm.util.constant.EnumPermit;
import com.pm.util.excel.Column;
import com.pm.util.excel.EnumCellFormat;
import com.pm.util.excel.ThreadLocalModifyColumn;
import com.pm.util.excel.exports.BusinessExExcel;
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
 * 公共费用分析
 * @author Administrator
 */
@Controller
@RequestMapping(value = "FinancialAnalysis4CommonCostAction.do")
public class FinancialAnalysis4CommonCostAction extends FinancialAnalysisAbstract {

    @Autowired
    IDeptService deptService;

    @Autowired
    private IDicDataService dicDataService;

    @Autowired
    private IRoleService roleService;



    @Autowired
    private IAnalysisCommonCostService analysisCommonCostService;


    @RequestMapping(params = "method=export")
    public void export(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){


        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTINANCIALANALYSISVIEW.getId());

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
            headers.add("公共费用分析");
            BusinessExExcel.export(res, headers , lists,false);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ThreadLocalModifyColumn.setColumns(null);
        }
    }




    @RequestMapping(params = "method=list")
    public String list(AnalysisSearch analysisSearch, HttpServletResponse res, HttpServletRequest request){

        UserPermit userPermit = this.getUserPermit(request, roleService, EnumPermit.COMMONCOSTINANCIALANALYSISVIEW.getId());

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
        request.setAttribute("endTimeQuantum", DateUtils.getTimeQuantum(analysisSearch.getMonth1()-100,analysisSearch.getMonth2()-100));


        request.setAttribute("analysisSearch", analysisSearch);

        return "analysis/analysis_commoncost_list";
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
        return arts;
    }


    /**
     * 公共费用分析
     * @return
     */
    private AnalysisResultTable getAnalysis1(AnalysisSearch analysisSearch, UserPermit userPermit){

        int month1 = analysisSearch.getMonth1();
        int month2 = analysisSearch.getMonth2();
        putSearchDate(analysisSearch, month1, month2);



        AnalysisResultTable art = new AnalysisResultTable();


        List<AnalysisResult> ars = analysisCommonCostService.queryCommonCostCosts(analysisSearch,userPermit);
        art.setResult(ars);





        for(AnalysisResult ar : ars){
            ar.setAnalysis_type(art.getLabel());
        }

        analysisSearch.setMonth1(month1);
        analysisSearch.setMonth2(month2);

        return art;
    }


}
