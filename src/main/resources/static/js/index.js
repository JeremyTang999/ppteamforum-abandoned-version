/**
 * 
 */
function setUsername(items,responseStatus,contentType){
	if(responseStatus=="403"){
		document.getElementById("user").innerHTML=
			'<a href="/login">登录</a> | '+
			'<a href="/register">注册</a>';
		/*var userBar=document.getElementById("user");
		var loginHref=document.createElement("a");
		var registerHref=document.createElement("a");
		loginHref.setAttribute("href","\\login.html");
		loginHref.innerHTML="登录 ";
		registerHref.setAttribute("href","\\register.html");
		registerHref.innerHTML="注册";
		userBar.appendChild(loginHref);
		userBar.appendChild(registerHref);*/
	}
	else if(responseStatus=="200"){
		name=JSON.parse(items).username;
		document.getElementById("user").innerHTML=
			'welcome, '+'<a href="/user">'+name+'</a>'+
			' | '+
			'<a href="/#">车手中心</a>'+
			' | '+
			'<a href="/p_logout">退出</a>';
	}
}
window.onload=function(){
		
	doAjax("/user/username_role","GET","","setUsername");
}