/**
 * 版本管理
 * @param $
 */
(function($){
	
	$.fn.extend({		
		
		compareVersion: function(){			
			
			return this.each(function(){
				
				 var $this = $(this) ;
				 
				 //滚动条联动
				 var $listenerDiv = $(".listenerScroll" , $this);					 
				 $listenerDiv.scroll(function(event){
					 var $theScroll = $(this) ;
					 var top = $theScroll.scrollTop();
					 var left = $theScroll.scrollLeft();					 
					 $listenerDiv.scrollLeft(left);
					 $listenerDiv.scrollTop(top);
					 
				 });
				 
				 
				 //点击一行，  左右两个表相同行都选中
				 var $listTable = $("table.list" , $this);
				 $listTable.each(function(){ 
					var $this = $(this);
					var $trs = $this.find('tbody>tr');
					var $grid = $this.parent(); 
					$trs.click(function(){
						
						var $tr = $(this);
						var trIndex = $tr.index();
						
						try{
							//将其他表 的相同行 选中
							$theTR = $listTable.find('tbody').find('tr:eq('+trIndex+')');			
							$theTR.each(function(){
								$otherTR = $(this);
								if($tr != $otherTR){
									$otherTR.parent().find('tr').filter(".selected").removeClass("selected");
									$otherTR.addClass("selected"); 
								}
							});						
						}catch(e){}						
					}); 					 
				 });
				 
				 
				 
				 
			});	
			
		}
		
	});
	
})(jQuery);

