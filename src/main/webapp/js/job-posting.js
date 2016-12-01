/**
 * CRUD operations with job postings.
 */
function deleteJob() {
	var jobId = $(this).closest("tr").attr("data-job-id");
	$.ajax({
		url : "/usjobs/service/job/" + jobId,
		method : "DELETE",
		context : $(this),
		success : function() {
			$(this).closest("tr").remove();
		}
	});
}

function toggleJob() {
	var jobId = $(this).closest("tr").attr("data-job-id");
	$
			.ajax({
				url : "/usjobs/service/job/toggle/" + jobId,
				method : "PUT",
				context : $(this),
				success : function() {
					var newHtml;
					if ($(this).hasClass("closeJob")) {
						$(this).removeClass("btn-warning closeJob").addClass(
								"btn-info openJob");
						newHtml = "<i class=\"fa fa-level-up\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Open";
					} else if ($(this).hasClass("openJob")) {
						$(this).removeClass("btn-info openJob").addClass(
								"btn-warning closeJob");
						newHtml = "<i class=\"fa fa-level-down\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Close";
					} else {
						console.log('could not determine toggle type.');
					}
					$(this).html(newHtml);
				}
			});
}

function editJob() {
	var jobId = $("input[name='jobId']").val();
	$.ajax({
		url : "/usjobs/service/job/" + jobId,
		method : "PUT",
		dataType : "json",
		processData : false,
		contentType : "application/json",
		data : JSON.stringify({
			jobTitle : $("input[name='jobtitle']").val(),
			website : $("input[name='website']").val(),
			location : $("input[name='location']").val(),
			jobDescription : $("textarea[name='jobdescription']").val(),
			salary : $("input[name='salary']").val()
		}),
		statusCode : {
			200 : function() {
				var rowToEdit = $("tr[data-job-id='" + jobId + "']");
				console.log(rowToEdit);
				rowToEdit.attr("data-job-title", $("input[name='jobtitle']")
						.val());
				rowToEdit.attr("data-job-website", $("input[name='website']")
						.val());
				rowToEdit.attr("data-job-location", $("input[name='location']")
						.val());
				rowToEdit.attr("data-job-description", $(
						"textarea[name='jobdescription']").val());
				rowToEdit.attr("data-job-salary", $("input[name='salary']")
						.val());
				rowToEdit.find("td[data-field='jobTitle']").html(
						$("input[name='jobtitle']").val());
				$("#editJobDialog").dialog("close");
			}
		}
	});
}

$(function() {

	/**
	 * Dialog config for the edit job panel
	 */
	$("#editJobDialog").dialog({
		autoOpen : false,
		minWidth: 500,
		title: "Edit Job",
		buttons : {
			"Save" : editJob,
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});

	/**
	 * Event handler for when we delete a job posting in the Employer profile
	 * page
	 */
	$(".deleteJob").click(deleteJob);

	/**
	 * Event handler for toggling open/close status job posting in the Employer
	 * profile
	 */
	$(".openJob, .closeJob").click(toggleJob);

	$(".editJob").click(function() {
		var id = $(this).closest("tr").attr("data-job-id");
		var title = $(this).closest("tr").attr("data-job-title");
		var website = $(this).closest("tr").attr("data-job-website");
		var location = $(this).closest("tr").attr("data-job-location");
		var description = $(this).closest("tr").attr("data-job-description");
		var salary = $(this).closest("tr").attr("data-job-salary");

		$("input[name='jobtitle']").val(title);
		$("input[name='website']").val(website);
		$("input[name='location']").val(location);
		$("textarea[name='jobdescription']").val(description);
		$("input[name='salary']").val(salary);
		$("input[name='jobId']").val(id);

		$("#editJobDialog").dialog("open");
	})

});
