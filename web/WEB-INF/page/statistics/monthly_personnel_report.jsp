<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<form id="pagerForm" method="post" action="${webroot }/PersonnelReportAction.do?method=list">
    <input type="hidden" name="pageNum" value="${pageNum}" />
    <input type="hidden" name="numPerPage" value="${pageSize}" />
</form>

<div class="pageHeader">
    <form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${webroot }/PersonnelReportAction.do?method=list" method="post">
        <div class="searchBar">
            <ul class="searchContent">


                <li>
                    <label>月份：</label>
                    <input name="month" id="month" class="digits  month date required" readonly format="yyyyMM" minlength="6" maxlength="6" type="text"  value="${month }" />
                </li>


                <li>
                    <label>外协人员：</label>
                    <select name="outsource_staff" style="width:153px">
                        <option value="">全部</option>
                        <option value="0" <c:if test="${staffCost.outsource_staff == '0' }">selected</c:if>>否</option>
                        <option value="1" <c:if test="${staffCost.outsource_staff == '1' }">selected</c:if>>是</option>
                    </select>
                </li>


                <li style="width:40px">
                    <div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
                </li>


            </ul>


        </div>
    </form>
</div>




<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="icon" href="${webroot}/PersonnelReportAction.do?method=export" target="dwzExport" targetType="navTab" title="确定要导出这些记录吗?"><span>导出EXCEL</span></a></li>
        </ul>
    </div>

    <table class="list" width="100%" layoutH="60">
        
        <thead>
        <tr>
            <th width="100">月初人数</th>
            <th width="100">当月入职</th>
            <th width="70">当月离职</th>
            <th width="70">月末人数</th>
            <th width="90">当月转正人数</th>
            <th width="100">当月换项目人数</th>
            <th width="100">考勤总人数</th>
            <th width="100">工资总人数</th>
            <th width="100">工资总金额</th>
            <th width="100">月人均工资</th>
            <th width="120">五险一金缴纳人数</th>
        </tr>
        </thead>
        <tbody>
            <tr >
                <td><fmt:formatNumber value="${personnelStructureVo.month_start_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_join_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_leave_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_end_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_confirmation_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_change_project_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_work_attendance_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelStructureVo.month_salary_peoples }" type="currency" pattern="###,###,##0"/></td>

                <td align="right"><b><fmt:formatNumber value="${personnelStructureVo.month_salary_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
                <td align="right"><b><fmt:formatNumber value="${personnelStructureVo.month_average_salary_amount }" type="currency" pattern="###,###,##0.00"/></b></td>

                <td><fmt:formatNumber value="${personnelStructureVo.month_insurance_fund_peoples }" type="currency" pattern="###,###,##0"/></td>
            </tr>
        </tbody>


        <thead>
        <tr>

            <th width="150">所属部门</th>
            <th width="150">所属项目</th>
            <th width="70">月初人数</th>
            <th width="70">新入职人数</th>
            <th width="70">换项目人数</th>
            <th width="70">月末人数</th>
            <th width="100">考勤总人数</th>
            <th width="100">工资总人数</th>
            <th width="100">工资总金额</th>
            <th width="100">月人均工资</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="personnelChangeVo"  varStatus="status1" items="${personnelChangeVos}">
            <tr >
                <td>${personnelChangeVo.dept_name }</td>
                <td>${personnelChangeVo.project_name }</td>
                <td><fmt:formatNumber value="${personnelChangeVo.month_start_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelChangeVo.month_join_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelChangeVo.month_change_project_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelChangeVo.month_end_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelChangeVo.month_work_attendance_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td><fmt:formatNumber value="${personnelChangeVo.month_salary_peoples }" type="currency" pattern="###,###,##0"/></td>

                <td align="right"><b><fmt:formatNumber value="${personnelChangeVo.month_salary_amount }" type="currency" pattern="###,###,##0.00"/></b></td>
                <td align="right"><b><fmt:formatNumber value="${personnelChangeVo.month_average_salary_amount }" type="currency" pattern="###,###,##0.00"/></b></td>

            </tr>
        </c:forEach>
        </tbody>





        <thead>
        <tr>

            <th width="100">缴纳单位</th>
            <th width="100">社保缴纳人数</th>
            <th width="100">社保个人合计</th>
            <th width="100">社保单位合计</th>
            <th width="100">社保缴费合计</th>
            <th width="100">公积金缴纳人数</th>
            <th width="110">公积金个人金额</th>
            <th width="110">公积金单位金额</th>
            <th width="100">公积金合计</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="personnelInsuranceFundVo"  varStatus="status1" items="${personnelInsuranceFundVos}">
            <tr >
                <td>${personnelInsuranceFundVo.securty_unit }</td>

                <td><fmt:formatNumber value="${personnelInsuranceFundVo.securty_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td align="right"><b><fmt:formatNumber value="${personnelInsuranceFundVo.insurance_bypersonal_sum }" type="currency" pattern="###,###,##0.00"/></b></td>
                <td align="right"><b><fmt:formatNumber value="${personnelInsuranceFundVo.insurance_bycompany_sum }" type="currency" pattern="###,###,##0.00"/></b></td>
                <td align="right"><b><fmt:formatNumber value="${personnelInsuranceFundVo.insurance_all_sum }" type="currency" pattern="###,###,##0.00"/></b></td>

                <td><fmt:formatNumber value="${personnelInsuranceFundVo.fund_peoples }" type="currency" pattern="###,###,##0"/></td>
                <td align="right"><b><fmt:formatNumber value="${personnelInsuranceFundVo.fund_bypersonal_sum }" type="currency" pattern="###,###,##0.00"/></b></td>
                <td align="right"><b><fmt:formatNumber value="${personnelInsuranceFundVo.fund_bypcompany_sum }" type="currency" pattern="###,###,##0.00"/></b></td>
                <td align="right"><b><fmt:formatNumber value="${personnelInsuranceFundVo.fund_all_sum }" type="currency" pattern="###,###,##0.00"/></b></td>

            </tr>
        </c:forEach>
        </tbody>
        
        
        
    </table>


</div>