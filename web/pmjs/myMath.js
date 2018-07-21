Number.prototype.toFixed=function (d) {
	
	var s=this+""; 
	if(!d)d=0; 
	if(s.indexOf(".")==-1)s+="."; 
	s+=new Array(d+1).join("0"); 
	if(new RegExp("^(-|\\+)?(\\d+(\\.\\d{0,"+(d+1)+"})?)\\d*$").test(s)){
		var s="0"+RegExp.$2,pm=RegExp.$1,a=RegExp.$3.length,b=true;
		if(a==d+2){
			a=s.match(/\d/g); 
			if(parseInt(a[a.length-1])>4){
				for(var i=a.length-2;i>=0;i--){
					a[i]=parseInt(a[i])+1;
					if(a[i]==10){
						a[i]=0;
						b=i!=1;
					}else break;
				}
			}
			s=a.join("").replace(new RegExp("(\\d+)(\\d{"+d+"})\\d$"),"$1.$2");
 
		}
		if(b)s=s.substr(1); 
		return (pm+s).replace(/\.$/,"");
	}
	return this+"";
 
};	




/**
 * 将输入框中的数据转换为数字， 如果不是数字返回0
 */
function changeDouble(textField){
	var thisVal = 0.0;
	try{
		thisVal = parseFloat(textField.value);
		thisVal = fixed(thisVal);
	}catch(e){
		thisVal = 0.0;
	}	
	if(isNaN( thisVal )) thisVal = 0;
	return thisVal;
}

/**
 * 四舍五入
 * @param result
 */
function fixed(result){
	result = parseFloat(result).toFixed(10);
	result = parseFloat(result);
	return parseFloat(tofixed1(result,2));
}

/**
 * 真正四舍五入
 * @param result
 * @param fractionDigits
 * @returns
 */
function tofixed1(result,fractionDigits){
	try{
		with(Math){     
		    return parseFloat(round(this*pow(10,d)))/pow(10,d);     
		} 
	}catch(e){
	}

	return result.toFixed(fractionDigits);
}

