var gross_profit_urls=[
"",
"/ProjectGrossProfitStatisticsAction.do?method=list",
"/ProjectGrossProfitStatisticsAction.do?method=salesUserList",
"/ProjectGrossProfitStatisticsAction.do?method=managerList",
"/ProjectGrossProfitStatisticsAction.do?method=infoSourceList",
"/ProjectGrossProfitStatisticsAction.do?method=clientList",
"/ProjectGrossProfitStatisticsAction.do?method=salesDeptList",
"/ProjectGrossProfitStatisticsAction.do?method=execDeptList",
"/ProjectGrossProfitStatisticsAction.do?method=deptList",
"/ProjectGrossProfitStatisticsAction.do?method=yearList",
"/ProjectGrossProfitStatisticsAction.do?method=quarterList",
"/ProjectGrossProfitStatisticsAction.do?method=allList"                            
                      ];

var gross_profit2_urls=[
"",
"/ProjectGrossProfitStatisticsAction2.do?method=list",
"/ProjectGrossProfitStatisticsAction2.do?method=salesUserList",
"/ProjectGrossProfitStatisticsAction2.do?method=managerList",
"/ProjectGrossProfitStatisticsAction2.do?method=infoSourceList",
"/ProjectGrossProfitStatisticsAction2.do?method=clientList",
"/ProjectGrossProfitStatisticsAction2.do?method=salesDeptList",
"/ProjectGrossProfitStatisticsAction2.do?method=execDeptList",
"/ProjectGrossProfitStatisticsAction2.do?method=deptList",
"/ProjectGrossProfitStatisticsAction2.do?method=yearList",
"/ProjectGrossProfitStatisticsAction2.do?method=quarterList",
"/ProjectGrossProfitStatisticsAction2.do?method=allList"                            
                      ];

var gross_profit3_urls=[
    "",
    "/ProjectGrossProfitStatisticsAction3.do?method=list",
    "/ProjectGrossProfitStatisticsAction3.do?method=salesUserList",
    "/ProjectGrossProfitStatisticsAction3.do?method=managerList",
    "/ProjectGrossProfitStatisticsAction3.do?method=infoSourceList",
    "/ProjectGrossProfitStatisticsAction3.do?method=clientList",
    "/ProjectGrossProfitStatisticsAction3.do?method=salesDeptList",
    "/ProjectGrossProfitStatisticsAction3.do?method=execDeptList",
    "/ProjectGrossProfitStatisticsAction3.do?method=deptList",
    "/ProjectGrossProfitStatisticsAction3.do?method=yearList",
    "/ProjectGrossProfitStatisticsAction3.do?method=quarterList",
    "/ProjectGrossProfitStatisticsAction3.do?method=allList"
];



var sales_urls=[
"",
"/ProjectSalesStatisticsAction.do?method=list",
"/ProjectSalesStatisticsAction.do?method=salesUserList",
"/ProjectSalesStatisticsAction.do?method=managerList",
"/ProjectSalesStatisticsAction.do?method=infoSourceList",
"/ProjectSalesStatisticsAction.do?method=clientList",
"/ProjectSalesStatisticsAction.do?method=salesDeptList",
"/ProjectSalesStatisticsAction.do?method=execDeptList",
"/ProjectSalesStatisticsAction.do?method=deptList",
"/ProjectSalesStatisticsAction.do?method=yearList",
"/ProjectSalesStatisticsAction.do?method=quarterList",
"/ProjectSalesStatisticsAction.do?method=allList"    
               ];



var receivables_urls=[
"",
"/ProjectReceivablesStatisticsAction.do?method=list",
"/ProjectReceivablesStatisticsAction.do?method=salesUserList",
"/ProjectReceivablesStatisticsAction.do?method=managerList",
"/ProjectReceivablesStatisticsAction.do?method=infoSourceList",
"/ProjectReceivablesStatisticsAction.do?method=clientList",
"/ProjectReceivablesStatisticsAction.do?method=salesDeptList",
"/ProjectReceivablesStatisticsAction.do?method=execDeptList",
"/ProjectReceivablesStatisticsAction.do?method=deptList",
"/ProjectReceivablesStatisticsAction.do?method=yearList",
"/ProjectReceivablesStatisticsAction.do?method=quarterList",
"/ProjectReceivablesStatisticsAction.do?method=allList"               
               ];


function changeFormAction(form1,options){	
	var $form = $(form1);
	var statisticsby = $('#statisticsby').val();
	var index = parseInt(statisticsby);
	if(index == 0) index = 1;
	var url = contextpath+options[index];
	$form.attr("action", url);	
	
	$pageform = $form.parent().parent().find("#pagerForm");
	$pageform.attr("action", url);
}


/**
 * 处理navTab上的查询, 会重新载入当前navTab
 * 用于处理统计功能
 * @param {Object} form
 */
function navTabStaisticsSearch(form,options ){
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	
	changeFormAction(form,options);
	
	var navTabId;
	if (form[DWZ.pageInfo.pageNum]) form[DWZ.pageInfo.pageNum].value = 1;
	navTab.reload($form.attr('action'), {data: $form.serializeArray(), navTabId:navTabId});
	return false;
}
