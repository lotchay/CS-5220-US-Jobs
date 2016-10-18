$(document).ready(function() {
	
	/* Store the id of the job */
	var jobPostingId = $("#jobid").val();
	
	/* Favorite a job */
	$('#favorite').click(function(event) {
		$.post('favoriteAdd.html', {
			jobid : jobPostingId
		}, function(responseText) {
			$('#status-msg').text("Added to favorite jobs.");
			setTimeout(function() {
				$("#status-msg").empty();
			}, 2000);
		});
	});
	
	$('#apply').click(function(event) {
		$.post('applyAdd.html', {
			jobid : jobPostingId
		}, function(responseText) {
			$('#status-msg').text("Added to applied jobs.");
			setTimeout(function() {
				$("#status-msg").empty();
			}, 4000);
		});
	});
});