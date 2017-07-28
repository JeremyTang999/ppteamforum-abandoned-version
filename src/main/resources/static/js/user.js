/**
 * 
 */
var username="",id;
$(document).ready(function(){
	
	$.ajax({
		url:"user/basicinfo",
		type:"GET",
		async:false,
		success:function(data,status){
			id=data.id;
			$("#username").text(data.username);
		}
	});
	
	$.ajax({
		url:"user/userinfo",
		type:"GET",
		data:"id="+id,
		success:function(data,status){
			switch(data.gender){
			case "male":gender="男";break;
			case "female":gender="女";break;
			case "other":gender="其他";break;
			case "unknown":gender="";break;
			}
			$("#gender").text(gender);
			if(data.photoPath!="" && data.photoPath!=null){
				$("#p").attr("src",data.photoPath);
			}
			if(data.personalSignature!=null){
				$("#sig").text(data.personalSignature);
			}
		}
	});
	
})

