<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include.inc.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=7" />

<%@ include file="/easyui.jsp"%>


<script type="text/javascript">


	function successLoads(data) {
		
		if (data.rows.length > 0) {
			//循环判断操作为新增的不能选择 
			for (var i = 0; i < data.rows.length; i++) {
				$('#dg').datagrid('checkRow', i);
				//根据operate让某些行不可选 
				if (data.rows[i].verity_status != "0") {
					$("input[type='checkbox']")[i + 1].disabled = true;
				}
			}
		}

		loadSuccess(data);
	}
	

	function formatCheckInfo(value, row) {
		if (value == "0") {
			return "未核实";
		} else {
			return "已核实";
		}
	}

	function cellStyler(value, row, index) {
		if (row.showhl == 1 || row.showhl == "1") {
			return 'color:red;';
		}
	}

	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg').datagrid('validateRow', editIndex)) {
			$('#dg').datagrid('endEdit', editIndex);

			var row = $('#dg').datagrid('getRows')[editIndex];
			handleFormula(row, editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index, rowData) {
		if (editIndex != index) {
			if (endEditing() && !isCheck(rowData.verity_status)) {
				$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
						index);
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}

	var canSave = true;
	function save() {
		var json1 = accept();
		if ("1" == json1) {
			return false;
		}

		var url = "${webroot }/SalaryGroupAction.do?method=updateSalaryGroup4Datagrid";

		if (!canSave)
			return false;

		canSave = false;

		$.ajax({
			url : url,
			type : "POST",
			dataType : "json",
			data : {
				"info" : json1
			},
			cache : false,
			success : function(data) {
				if (data.statusCode == 200) {
					$.messager.show({
						title : '成功',
						msg : '工资信息保存成功',
						showType : 'slide',
						style : {
							right : '',
							top : document.body.scrollTop
									+ document.documentElement.scrollTop,
							bottom : ''
						}
					});

					window.setTimeout(closeWindow, 5000);

				} else {
					canSave = true;
					$.messager.alert('失败', '工资信息保存错误!', 'error');
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				canSave = true;
				$.messager.alert('失败', "Http status: " + xhr.status + " "
						+ xhr.statusText + "\najaxOptions: " + ajaxOptions
						+ "\nthrownError:" + thrownError + "\n"
						+ xhr.responseText, 'error');
			}
		});

	}

	function accept() {
		if (endEditing()) {
			var changerows = $('#dg').datagrid('getChanges');
			var leng = 0;
			if (changerows != null)
				leng = changerows.length;
			for (var index = 0; index < leng; index++) {
				changerows[index].id = "1";
			}

			var objs = [];
			var rows = $('#dg').datagrid('getRows');
			leng = 0;
			if (rows != null) leng = rows.length;
			
			
			var checkrows = $('#dg').datagrid('getChecked');

			for (var index = 0; index < leng; index++) {	
				
				if(rows[index].verity_status != "0") continue;
				
				if(checkrows == null) rows[index].id = "2";
				else {
					var isCheck = false;
					for(var ci=0;ci<checkrows.length;ci++){
						if(checkrows[ci].salary_id == rows[index].salary_id){
							isCheck = true;
							break;
						}
					}
					if(isCheck == false) rows[index].id = "2";
				}
			}
			
			
			
			for (var index = 0; index < leng; index++) {			
				if(rows[index].verity_status != "0") continue;	
				if (rows[index].id == "1" || rows[index].id == "2") {
					objs.push(rows[index]);
				}
			}
			var json1 = JSON.stringify(objs);
			return json1;

		} else {
			return "1";
		}
	}
	function reject() {
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function getChanges() {
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length + ' rows are changed!');
		if (rows.length > 0) {
			for (var index = 0; index < rows.length; index++) {
				var row = rows[index];
				alert(row.staff_name);
			}
		}
	}

	function closeWindow() {
		window.close();
	}
</script>

</head>



<body>
	<table id="dg" class="easyui-datagrid" style="width:550px;height:220px" 
			<c:if test="${full!=null }">title="修改工资： ${salary1.salary_month } ${salary1.project_name }"</c:if>
			data-options="fit:true,singleSelect:true,url:'${webroot}/SalaryGroupAction.do?method=edit4EasyuiDatagrid&id=${id }',
			method:'get',onClickRow: onClickRow,onLoadSuccess:successLoads,toolbar:'#tb',
			selectOnCheck:false,checkOnSelect:false,
			rownumbers: true,idField:'salary_id'">
		<thead data-options="frozen:true">
			<tr>								
				<c:if test="${empty salary1.verify_userid}">
					<th data-options="field:'ck',checkbox:true"></th>
				</c:if>				
				<th data-options="field:'verity_status',width:60,formatter:formatCheckInfo">核实情况</th>
				<th data-options="field:'staff_name',width:100,styler:cellStyler">姓名</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th data-options="field:'basic_salary',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">基本工资</th>	
				<th data-options="field:'post_salary',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">岗位工资</th>	
				<th data-options="field:'performance_allowances',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">绩效津贴</th>	
				<th data-options="field:'travel_allowance',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">出差补助</th>	
				<th data-options="field:'tax_bonus',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">奖金(税)</th>	
				<th data-options="field:'should_work_days',width:80">应出勤天数</th>	
				<th data-options="field:'work_days',width:100">实际工作天数</th>	
				<th data-options="field:'paid_leave_days',width:60">带薪假</th>	
				<th data-options="field:'business_trip_days',width:60">出差</th>						
				<th data-options="field:'personal_leave_days',width:80">事假天数</th>
				<th data-options="field:'sick_leave_days',width:80">病假天数</th>
				<th data-options="field:'neglect_work_days',width:80">旷工天数</th>
				<th data-options="field:'late_days',width:80">迟到天数</th>
				<th data-options="field:'weekend_overtime_days',width:80">加班天数</th>
				<th data-options="field:'sick_leave_salary',width:80">病假工资</th>
				<th data-options="field:'neglect_work_salary',width:80">旷工工资</th>
				<th data-options="field:'late_salary',width:80">迟到工资</th>
				<th data-options="field:'actual_travel_allowance',width:80">出差补贴</th>
				<th data-options="field:'actual_computer_allowance',width:80">电脑补贴</th>
				<th data-options="field:'actual_extra_allowance',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">额外补贴</th>
				<th data-options="field:'overtime_allowance',width:80">加班补贴</th>
				<th data-options="field:'meals_allowance',width:60">餐补</th>
				<th data-options="field:'actual_tax_bonus',width:160">实发奖金(税前)</th>
				<!-- 
				<th data-options="field:'extra_expend',width:80">额外支出</th>
				 -->
				<th data-options="field:'should_salary',width:80">应发工资</th>
				
				<th data-options="field:'pension_insurance',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">养老个人</th>
				<th data-options="field:'endowment_insurance_bycompany',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">养老单位</th>
				<th data-options="field:'medical_Insurance',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">医疗个人</th>
				<th data-options="field:'medical_insurance_bycompany',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">医疗单位</th>
				<th data-options="field:'unemployment_insurance',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">失业个人</th>
				<th data-options="field:'losejob_insurance_bycompany',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">失业单位</th>
				<th data-options="field:'maternity_insurance_bycompany',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">生育单位</th>
				<th data-options="field:'jobharm_insurance_bycompany',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">工伤单位</th>
				<th data-options="field:'accumulation_fund',width:100,editor:{type:'numberbox',options:{precision:2,required:true}}">公积金个人</th>
				<th data-options="field:'reservefund_bypcompany',width:100,editor:{type:'numberbox',options:{precision:2,required:true}}">公积金单位</th>


				<th data-options="field:'children_education',width:160">子女教育</th>
				<th data-options="field:'housing_loans',width:160">住房贷款利息</th>
				<th data-options="field:'housing_rent',width:160">住房租金</th>
				<th data-options="field:'support_elderly',width:160">赡养老人</th>
				<th data-options="field:'continuing_education',width:160">继续教育</th>

				<th data-options="field:'deductions_cost',width:160">当月五险一金代扣款</th>
				<th data-options="field:'taxable_income',width:160">当月应纳税所得额</th>


				<th data-options="field:'accumulated_pretax_income',width:160">累计税前收入额</th>
				<th data-options="field:'accumulated_tax_deduction',width:160">个税累计减除费用</th>
				<th data-options="field:'accumulated_children_education',width:160">累计子女教育</th>
				<th data-options="field:'accumulated_housing_loans',width:160">累计住房贷款利息</th>
				<th data-options="field:'accumulated_housing_rent',width:160">累计住房租金</th>
				<th data-options="field:'accumulated_support_elderly',width:160">累计赡养老人</th>
				<th data-options="field:'accumulated_continuing_education',width:160">累计继续教育</th>
				<th data-options="field:'accumulated_deductions_cost',width:160">累计五险一金代扣款</th>
				<th data-options="field:'accumulated_taxable_income',width:160">累计应纳税所得额</th>
				<th data-options="field:'accumulated_deductible_taxpaid',width:160">累计应扣缴税额</th>
				<th data-options="field:'accumulated_prepaid_tax',width:160">累计已预缴税额</th>
				<th data-options="field:'accumulated_replenishment_tax',width:160">累计应补（退）税额</th>


				<th data-options="field:'personal_income_tax',width:80">当月应扣所得税</th>
				<th data-options="field:'actual_bonus',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">奖金（后）</th>
				<th data-options="field:'overdue_tax_salary',width:80,editor:{type:'numberbox',options:{precision:2,required:true}}">补税工资</th>
				<th data-options="field:'actual_salary',width:80"><b>实发工资</b></th>
				<th data-options="field:'description',width:280,editor:{type:'textbox'}"><b>说明</b></th>
				
				<th data-options="field:'id',width:18,hidden:true"><b>标记</b></th>		
				<th data-options="field:'computer_allowance',width:18,hidden:true"><b>电脑补助</b></th>				
				<th data-options="field:'meal_allowance',width:18,hidden:true"><b>餐补</b></th>

				<th data-options="field:'before_accumulated_pretax_income',width:18,hidden:true"><b>之前  累计税前收入额</b></th>
				<th data-options="field:'before_accumulated_tax_deduction',width:18,hidden:true"><b>之前  个税累计减除费用</b></th>
				<th data-options="field:'before_accumulated_children_education',width:18,hidden:true"><b>之前  累计子女教育</b></th>
				<th data-options="field:'before_accumulated_housing_loans',width:18,hidden:true"><b>之前  累计住房贷款利息</b></th>
				<th data-options="field:'before_accumulated_housing_rent',width:18,hidden:true"><b>之前  累计住房租金</b></th>
				<th data-options="field:'before_accumulated_support_elderly',width:18,hidden:true"><b>之前  累计赡养老人</b></th>
				<th data-options="field:'before_accumulated_continuing_education',width:18,hidden:true"><b>之前  累计继续教育</b></th>
				<th data-options="field:'before_accumulated_deductions_cost',width:18,hidden:true"><b>之前  累计五险一金代扣款</b></th>
			</tr>
		</thead>

	</table>
	
	
	<div id="tb" style="height:auto">
	<c:if test="${full != null }">
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel',plain:true" onclick="window.close()">关闭窗口</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveSalaryInfo"  data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
	</c:if>
	</div>
</body>
</html>