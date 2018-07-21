/**
 * 工资设置
 */
function changeType1(element){
	
	$this = $(element);
	if($this.val() == '2'){
		$this.parents("p").next().show();
		$this.parents("p").next().find("input").addClass("required");
	}else {
		$this.parents("p").next().find("input").removeClass("required");
		$this.parents("p").next().hide();
	}
}


(function($){
	
	$.fn.extend({

		init_sick_leave_salary: function(){			

			return this.each(function(){
				var sel = $(this).find("select").eq(0);
				changeType1(sel[0]);
				
			});			
			
		}
	
	
	});
	
	
})(jQuery);