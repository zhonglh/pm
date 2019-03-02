<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/FinancialAnalysis4CompanyAction.do?method=list">

</form>


<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/FinancialAnalysis4CompanyAction.do?method=list" method="post">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>时间段：</label>
                    <input type="text" class="digits date month required" readonly maxlength="6" minlength="6" format="yyyyMM" size="7" name="month1" value="${param.month1}"/>
                    <input type="text" class="digits date month required" readonly maxlength="6" minlength="6" format="yyyyMM" size="7" name="month2" value="${param.month2}"/>

                </li>


                <li>
                    <div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
                </li>


            </ul>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">

            <li><a class="icon" href="${webroot}/FinancialAnalysis4CompanyAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>

        </ul>
    </div>
    <table class="table" width="100%" layoutH="100">
        <c:forEach var="analysisTable" items="${arts}">
        <tr>
            <td>

                <h3 class="contentTitle">${analysisTable.label}</h3>
                <table class="table" width="100%" >
                    <thead>
                    <th  width="120">类别</th>
                    <th  width="120">${ startTimeQuantum }</th>
                    <th  width="120">${ endTimeQuantum }</th>
                    <th  width="120">增减额</th>
                    <th  width="120">变动比例</th>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="analysisTable.result">
                        <tr>
                            <td align="center">${item.item_name}</td>
                            <td align="right">${item.curr_statistics_amount}</td>
                            <td align="right">${item.pre_statistics_amount}</td>
                            <td align="right">${item.increase_or_decrease}</td>
                            <td align="right">${item.change_ratio}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
