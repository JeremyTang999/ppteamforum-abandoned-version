/**
 * 
 */

function getXMLHttpRequest(){
	try{
		try{
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch(e){
			return new ActiveXObject("Msxml2.XMLHTTP");
		}
	}
	catch(e){
		return new XMLHttpRequest();
	}
}

function requestGet(url,requestParam,req){
	var rand=parseInt(Math.random()*9999);
	var callUrl=url;
	if(requestParam=""){
		callUrl+=("?r="+rand);
	}else{
		callUrl+=("?"+requestParam+"&r="+rand);
	}
	
	req.open("GET",callUrl,true);
	req.send(null);
}

function requestPost(url,requestBody,req,ctype){
	req.open("POST",url,true);
	if(ctype!=""){
		req.setRequestHeader("Content-Type",ctype);
	}
	req.send(requestBody);
}

/*url
 * 方法
 * 请求体：GET为“参数=值&参数=值”形式，POST为JSON
 * callBack：回调函数，必须带有两个参数：返回数据、返回状态码、返回实体类型*/
function doAjax(url,method,requestBody,callBack){
	var ajaxRequest=getXMLHttpRequest();
	ajaxRequest.onreadystatechange=function(){
		if(ajaxRequest.readyState==4){
			var items=ajaxRequest.responseText;
			var responseStatus=ajaxRequest.status;
			var contentType=ajaxRequest.getResponseHeader("Content-Type");
			eval(callBack+"(items,responseStatus,contentType)");
		}
	}
	if(method.toUpperCase()=="POST"){
		requestPost(url,requestBody,ajaxRequest,"application/json");
	}
	else{
		requestGet(url,requestBody,ajaxRequest);
	}
}
function doUpload(url,file,callBack){
	var ajaxRequest=getXMLHttpRequest();
	ajaxRequest.onreadystatechange=function(){
		if(ajaxRequest.readyState==4){
			var items=ajaxRequest.responseText;
			var responseStatus=ajaxRequest.status;
			var contentType=ajaxRequest.getResponseHeader("Content-Type");
			eval(callBack+"(items,responseStatus,contentType)");
		}
	}
	requestPost(url,file,ajaxRequest,"");



}