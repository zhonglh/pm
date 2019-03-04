<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>



<div class="pageHeader">
    <form rel="pagerForm" id="pagerForm"  method="post">
        <div class="searchBar">
            <ul class="searchContent">


                <li>
                    <label>月份：</label>
                    <input name="month" id="month" class="digits  month date" readonly format="yyyyMM" minlength="6" maxlength="6" type="text"  value="${month }" />
                </li>


                <li>
                    <label>外协人员：</label>
                    <select name="outsource_staff" style="width:153px">
                        <option value="">全部</option>
                        <option value="0" <c:if test="${staffCost.outsource_staff == '0' }">selected</c:if>>否</option>
                        <option value="1" <c:if test="${staffCost.outsource_staff == '1' }">selected</c:if>>是</option>
                    </select>
                </li>



                <li style="width:80px">
                <a class="icon" href="${webroot}/PersonnelMonthlyBaseAction.do?method=export" target="dwzExport"
                       targetType="navTab" title="确定要导出吗?"><span>导出EXCEL</span></a>
                </li>

            </ul>


        </div>
    </form>
</div>