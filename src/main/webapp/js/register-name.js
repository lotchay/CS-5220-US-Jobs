$(document).ready(function(){
	$("#username").keyup(username_check);
	$("#email").keyup(email_check);
});

function username_check(){
	var username = $("#username").val();
	
	if(username == "" || username.length<4){
		$("#usernameTakenMessage").html("");
	} else{
		$.ajax({
			type: "POST",
			url: "/usjobs/service/register/username",
			data: "username=" + username,
			success: function(response){
				//console.log(response);
				if (response!="null"){
					$("#usernameTakenMessage").html("<font color=\"red\">This username is already taken.</font>");
				} else{
					$("#usernameTakenMessage").html("<font color=\"green\">This username is available.</font>");
				}
			}
		});
	}
}

function email_check(){
	var email = $("#email").val();
	
	if(email == "" || email.length<7){
		$("#emailTakenMessage").html("");
	}else{
		$.ajax({
			type: "POST",
			url: "/usjobs/service/register/email",
			data: "email=" + email,
			success: function(response){
				if (response!="null"){
					$("#emailTakenMessage").html("<font color=\"red\">This email is already linked to an account.</font>");
				} else{
					$("#emailTakenMessage").html("<font color=\"green\">This email is available for use.</font>");
				}
			}
		});
	}
}