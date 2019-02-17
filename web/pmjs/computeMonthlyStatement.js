
(function($){
	
	$.fn.extend({		
			
		
		monthlyStatementComputer: function(){			

			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody:first");
				var fields=[];

				$tbody.find("tr").each(function(i){
					var $tr = $(this);					
					var field = tr2Obj($tr);					
					fields.push(field);
				});
				
				initFormula(fields);
				initChange(fields);
				initTotal(fields);


                processAddBtn($table , fields.length);
			});			
			
		},

		monthlyStatementUpdateComputer: function(){			

			return this.each(function(){
				var $table = $(this).css("clear","both"), $tbody = $table.find("tbody:first");
				var fields=[];

				$tbody.find("tr").each(function(i){
					var $tr = $(this);					
					var field = tr2Obj($tr);					
					fields.push(field);
				});
				
				//var amount = $table.find("#amount").val();
				$totalTD = $("tr:last", $table).find("td:last");
				var $amount = $totalTD.find("#amount");
				var value1= $amount.val();
				initChange(fields);
				initTotal(fields);
				$amount.val(value1);


                processAddBtn($table , fields.length);



			});
			
		}		
		
	});
	

	function processAddBtn($table , trLength){
        var addButTxt = $table.attr('addButton') ;
        var $box = $table.parents(".unitBox:first");
        var addButton = $("#addButton" , $box);
        if (addButTxt && (addButton == undefined || addButton.length == 0)) {
            var $addBut = $('<div id="addButton" class="button"><div class="buttonContent"><button currIndex="'+trLength+'" type="button">'+addButTxt+'</button></div></div>').insertBefore($table).find("button");

            var webroot = $table.attr("webroot");
            $addBut.click(function(){
                addMSD($table , this, webroot);
            });
        }
	}
	

	
	function tr2Obj(tr){
		var $tr = tr;
		var field = {
			technical_cost: parseFloat($("#technical_cost",$tr).val()),
			should_work_days: parseFloat($("#should_work_days",$tr).val()),
			work_days: parseFloat($("#work_days",$tr).val()),
			daily_overtime: parseFloat($("#daily_overtime",$tr).val()),
			weekend_overtime: parseFloat($("#weekend_overtime",$tr).val()),
			overtime_cost: parseFloat($("#overtime_cost",$tr).val()),
			business_trip_days: parseFloat($("#business_trip_days",$tr).val()),
			business_trip_cost: parseFloat($("#business_trip_cost",$tr).val()),
			other_cost: parseFloat($("#other_cost",$tr).val()),
			total_cost: parseFloat($("#total_cost",$tr).val()),
			man_month: parseFloat($("#man_month",$tr).val()),
			tr:$tr 
		};
		return field;
	}
	
	
	function initFormula(fields){
		$(fields).each(function(){	
			handleFormula(this);
		});
	}
	
	

	function initTotal(fields){	
		
		if(fields.length == 0) return ;
		
		var total = {
				technical_cost: 0,
				should_work_days: 0,
				work_days: 0,
				daily_overtime: 0,
				weekend_overtime: 0,
				overtime_cost: 0,
				business_trip_days: 0,
				business_trip_cost: 0,
				other_cost: 0,
				total_cost: 0,
				man_month: 0
		};
		
		try{
			$(fields).each(function(){
				
					var field = this;
					$.each(field,function(k,v){
						if(k != "tr")
						eval("total."+k+ " += "+v+";");
					});
				
			});	
		}catch(e){}
		
		$totalTR = $("tr:last", fields[0].tr.parent().parent());
		$tdArray = $totalTR.find("td");
		$($tdArray[2]).html(fixed(total.technical_cost));
		$($tdArray[3]).html(fixed(total.should_work_days));
		$($tdArray[4]).html(fixed(total.work_days));
		$($tdArray[5]).html(fixed(total.daily_overtime));
		$($tdArray[6]).html(fixed(total.weekend_overtime));
		$($tdArray[7]).html(fixed(total.overtime_cost));
		$($tdArray[8]).html(fixed(total.business_trip_days));
		$($tdArray[9]).html(fixed(total.business_trip_cost));
		$($tdArray[10]).html(fixed(total.other_cost));
		$($tdArray[11]).html(fixed(total.man_month));
		$($tdArray[12]).find("#amount").val(fixed(total.total_cost));
		
		total = null;
		
	}
	
	function initChange(fields){				

		$(fields).each(function(){	
			var field = this;
			field.tr.find("input[type=text]").change(function(){
				thisVal = changeDouble(this);
				var idAttr = $(this).attr("id");
				eval("field."+this.id+"="+thisVal+";");
				if(idAttr != 'total_cost' && idAttr != 'man_month' ){
					handleFormula(field);
				}
				
				initTotal(fields);
			});
		});		
	}
	
	/**
	 * 处理公式
	 */
	function handleFormula(field){
		
		$field = $(field);
		$.each(field,function(k,v){
			try{
				var execCode = k+"($field.get(0),k,v);";							
				eval(execCode);
			}catch(e){
										
			}
		});
	}
	
	

	function backFillMonthlyStatement(field,key,result){	
		if(isNaN( result )) result = 0;
		$("#"+key,field.tr).val(result);		
		eval("field."+key+" = result;");
	}
	
		
	
	/**
	 * 小计
	 * @param field
	 * @param key
	 * @param val
	 */
	function total_cost(field,key,val){
		var result = 0.0;
		if(field.should_work_days != 0){
			result = field.work_days/field.should_work_days*field.technical_cost;
			result = fixed(result);		
		}
		result += field.overtime_cost + field.other_cost + field.business_trip_cost;
		result = fixed(result);				
		backFillMonthlyStatement(field,key,result);

	}
	

	/**
	 * 人月
	 * @param field
	 * @param key
	 * @param val
	 */
	function man_month(field,key,val){
		var result = 0.0;
		result = field.total_cost/field.technical_cost;
		result = fixed(result);	
		backFillMonthlyStatement(field,key,result);		
	}







    /**
     * 增加月报明细记录
     */
    function addMSD($table ,btn , webroot ){

        var $btn = $(btn);
        var curr = $btn.attr("currIndex");
        curr = parseInt(curr);
        var $box = $btn.parents(".unitBox:first");

        var $tbody = $table.find("tbody");

        var trTm = "<tr>";
        trTm += '<td nowrap><a href="javascript:void(0)" class="btnDel ">删除</a></td>';

        trTm += '<td nowrap>';
        trTm += ' <input type="hidden" size="2" id="id" name="id" value="'+(curr+1)+'"/> <input type="hidden"   name="monthly_statement_detail_id'+(curr+1)+'"/>' ;

        trTm += ' <input type="hidden" name="staff'+(curr+1)+'.staff_id">';
        trTm += ' <input type="text" readonly name="staff'+(curr+1)+'.staff_name" lookuppk="staff_id" size="8" class="required textInput readonly valid">';
        trTm += ' <a class="btnLook" width="950" href="'+webroot+'/StaffCostAction.do?method=lookup&delete_flag=0" lookupgroup="staff'+(curr+1)+'" lookuppk="staff_id" title="查找">查找</a>' ;


        trTm += '</td>';


        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="technical_cost" 	name="technical_cost'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="should_work_days" 	name="should_work_days'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="work_days" 	name="work_days'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="daily_overtime" 	name="daily_overtime'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="weekend_overtime" 	name="weekend_overtime'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="overtime_cost" 	name="overtime_cost'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="business_trip_days" 	name="business_trip_days'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="business_trip_cost" 	name="business_trip_cost'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="other_cost" 	name="other_cost'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="man_month" 	name="man_month'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="0.0" size="7" maxlength="10" class="number required"  id="total_cost" 	name="total_cost'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="" size="16" maxlength="50" class="text"  id="client_dept" 	name="client_dept'+(curr+1)+'"  /></td>';
        trTm += '<td><input type="text" value="" size="38" maxlength="150" class="text"  id="description" 	name="description'+(curr+1)+'"  /></td>';
        trTm += "</tr>";

        var $tr = $(trTm);
        $tr.appendTo($tbody).find("a.btnDel").click(function(){
            $(this).parents("tr:first").remove();

            var fields=[];

            $tbody.find("tr").each(function(i){
                var $tr = $(this);
                var field = tr2Obj($tr);
                fields.push(field);
            });

            initTotal(fields);



            return false;
        });

        $box.initUI();

        $btn.attr("currIndex" , (curr+1));
    }
	
	
})(jQuery);

