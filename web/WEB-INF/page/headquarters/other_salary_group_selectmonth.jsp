<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>

<div class="pageContent">

    <form method="post" action="${webroot }/OtherSalaryGroupAction.do?method=autoAddSalaryGroup" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent" layoutH="58">


            <p>
                <label>工资月份：</label>
                <input name="salary_month" class="digits date required month" autocomplete="off"  readonly format="yyyyMM" minlength="6" maxlength="6" type="text" size="30" value="${salary_month }" />
            </p>



        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">制作工资</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>

</div>
