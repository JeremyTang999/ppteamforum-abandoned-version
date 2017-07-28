/**
 * 
 */
$(document).ready(function(){
	/*$.get("/user/basicinfo",acallBack);
	
	function acallBack(data,status){alert("未登录");
		if(status=='403'){
			//$("#user").text(
			//		"welcome:"+data.username);
			
		}
		else{
			$("#user").text("未登录");
			$("#user").value="未登录";
			
		}
	}*/
	
	$.ajax({
		url:"/user/basicinfo",
		type:"GET",
		error:function(xhr,status,error){
			$("#user").append('<a href="/login">登录</a>');
			$("#user").append('|<a href="/register">注册</a>')
		},
		success:function(data,status){
			$("#user").append('welcome:'+data.username);
			$("#user").append(' | <a href="/user">个人中心</a>');
			$("#user").append(' | <a href="/p_logout">退出</a>');
		}
	})
})

