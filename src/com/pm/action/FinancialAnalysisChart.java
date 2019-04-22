package com.pm.action;

import com.common.actions.BaseAction;
import com.common.utils.DateKit;
import com.pm.vo.AnalysisResult;
import com.pm.vo.AnalysisResultTable;
import com.pm.vo.AnalysisSearch;
import com.pm.vo.echarts.Option;
import com.pm.vo.echarts.Option4;
import com.pm.vo.echarts.base.*;
import com.pm.vo.echarts.series.*;
import com.pm.vo.echarts.title.Title;
import com.pm.vo.echarts.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务分析 EChart 公共类
 * @author Administrator
 */
public abstract class FinancialAnalysisChart extends FinancialAnalysisAbstract {


    public String[] getDataTitle(AnalysisResultTable analysisResultTable){
        String[] dataTitles = new String[analysisResultTable.getResult().size()];
        int index = 0;
        for(AnalysisResult result : analysisResultTable.getResult()){
            dataTitles[index] = result.getItem_name();
            index ++ ;
        }
        return dataTitles;
    }

    public List<Bar4> toBars(String startTime , String endTime ,AnalysisResultTable analysisResultTable){
        List<Bar4> bars = new ArrayList<Bar4>();

        Bar4 bar1 = new Bar4();
        Bar4 bar2 = new Bar4();
        bar1.setName(startTime);
        bar2.setName(endTime);
        double[] barData1 = new double[analysisResultTable.getResult().size()];
        double[] barData2 = new double[analysisResultTable.getResult().size()];

        int index = 0;
        for(AnalysisResult result : analysisResultTable.getResult()){
            barData1[index] = (result.getCurr_statistics_amount());
            barData2[index] = (result.getPre_statistics_amount());
            index ++ ;
        }
        bar1.setData(barData1);
        bar2.setData(barData2);

        bars.add(bar1);
        bars.add(bar2);
        return bars;
    }



    public List<Pie> toPies(String startTime , String endTime , AnalysisResultTable analysisResultTable){
        List<Pie> pies = new ArrayList<Pie>();

        Pie pie1 = new Pie();
        Pie pie2 = new Pie();
        pie1.setName(startTime);
        pie2.setName(endTime);
        pie1.setCenter(new String[]{"25%" , "25%"});
        pie2.setCenter(new String[]{"75%" , "25%"});
        pie1.setRadius(new String[]{"0%","25%"});
        pie2.setRadius(new String[]{"0%","25%"});
        Data[] barData1 = new Data[analysisResultTable.getResult().size()];
        Data[] barData2 = new Data[analysisResultTable.getResult().size()];

        int index = 0;
        for(AnalysisResult result : analysisResultTable.getResult()){
            barData1[index] = new Data(result.getItem_name() ,result.getCurr_statistics_amount());
            barData2[index] = new Data(result.getItem_name() ,result.getPre_statistics_amount());
            index ++ ;
        }
        pie1.setData(barData1);
        pie2.setData(barData2);

        pies.add(pie1);
        pies.add(pie2);
        return pies;
    }

    public Option4 toOption(String startTime , String endTime , AnalysisResultTable analysisResultTable){
        Option4 option = new Option4();
        option.setBackgroundColor(new BackgroundColor4());

        Tooltip tooltip1 = new Tooltip();
        tooltip1.setTrigger("axis");
        tooltip1.setFormatter(null);


        /*Tooltip tooltip2 = new Tooltip();
        tooltip2.setFormatter(null);

        Tooltip tooltip3 = new Tooltip();
        tooltip3.setFormatter(null);*/

        option.setTooltip(tooltip1);

        Title title1 = new Title();
        title1.setText(analysisResultTable.getLabel());

        /*Title title2 = new Title();
        title2.setLeft("25%");
        title2.setText(startTime);
        title2.setTextAlign("center");

        Title title3 = new Title();
        title3.setLeft("75%");
        title3.setText(endTime);
        title3.setTextAlign("center");*/

        Title[] titles = new Title[]{title1 };
        option.setTitle(titles);


        Grid grid = new Grid();
        grid.setTop("50%");
        grid.setLeft("10");
        Grid[] grids = new Grid[]{grid};
        option.setGrid(grids);

        Yaxis yaxis = new Yaxis();
        yaxis.setSplitLine(new SplitLine());
        Yaxis[] yaxiss = new Yaxis[]{yaxis};
        option.setYaxis(yaxiss);

        Xaxis xaxis = new Xaxis();
        xaxis.setType("category");
        xaxis.setData(this.getDataTitle(analysisResultTable));
        xaxis.setSplitLine(new SplitLine());
        xaxis.setAxisLabel(new AxisLabel(0,30));
        Xaxis[] xaxiss = new Xaxis[]{xaxis};
        option.setXaxis(xaxiss);

        Bar4[] bars =  toBars(startTime,endTime,analysisResultTable).toArray(new Bar4[2]);
        Pie[] pies = toPies(startTime,endTime,analysisResultTable).toArray(new Pie[2]);

        option.setSeries(new Series[bars.length + pies.length]);
        int index = 0;
        for(Bar4 bar4 : bars) {
            option.getSeries()[index ++] = bar4;
        }
        for(Pie pie : pies) {
            option.getSeries()[index ++] = pie;
        }

        return option;
    }

}
