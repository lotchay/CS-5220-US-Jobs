$(document).ready(function(){
	$("#username").keyup(username_check);
});

function username_check(){
	var username = $("#username").val();
	
	if(username == "" || username.length<4){
		$("#takenMessage").html("");
	} else{
		$.ajax({
			type: "POST",
			url: "/usjobs/service/register",
			data: "username=" + username,
			success: function(response){
				//console.log(response);
				if (response!="null"){
					$("#takenMessage").html("<font color=\"red\">This username is not available.</font>");
				} else{
					$("#takenMessage").html("<font color=\"green\">This username is available.</font>");
				}
			}
		});
	}
}