/**
 * 
 */
function regProc(){
	
	if(document.getElementById("username").value=="" ||
			document.getElementById("password").value==""){
		alert("用户名或密码不能为空");
		return;
	}
	
	var regInfo=new Object();
	var radio=document.getElementsByName("gender");
	var selected=null;
	if(radio[0].checked==true){
		selected="male";
	}
	else if(radio[1].checked==true){
		selected="female";
	}
	regInfo.username=document.getElementById("username").value;
	regInfo.gender=selected;
	regInfo.password=document.getElementById("password").value;
	regInfo.question1=document.getElementById("question1").value;
	regInfo.answer1=document.getElementById("answer1").value;
	regInfo.question2=document.getElementById("question2").value;
	regInfo.answer2=document.getElementById("answer2").value;
	regInfo.question3=document.getElementById("question3").value;
	regInfo.answer3=document.getElementById("answer3").value;
	
	/*for(attr in regInfo){
		if(regInfo[attr]==""){
			alert("不能为空");
			return;
		}
	}*/

	alert(JSON.stringify(regInfo));
	doAjax("/user/registerinfo","POST",JSON.stringify(regInfo),"regCallBack");
	
	
}

function regCallBack(items,responseStatus,contentType){
	if(responseStatus=="200"){
		alert("注册成功");
	}
	else{
		alert("注册失败，请重试");
	}
}


function checkName(){
	var unstate=document.getElementById("unstate");
	var username=document.getElementById("username").value;
	if(username==""){
		unstate.innerHTML="用户名不能为空";
	}
	else{
		unstate.innerHTML="";
	}
	var usernameObj={"username":username};
	doAjax("/user/username_available","POST",JSON.stringify(usernameObj),"checkNameCallBack");
}

function checkNameCallBack(items,responseStatus,contentType){
	if(items=="available"){
		//document.getElementById("unstate").innerHTML="";
	}
	else{
		document.getElementById("unstate").innerHTML="用户名已被使用";
	}
}

function checkPass(){
	var pwstate=document.getElementById("pwstate");
	var password=document.getElementById("password").value;
	if(password==""){
		pwstate.innerHTML="密码不能为空";
	}
	else{
		pwstate.innerHTML="";
	}
}