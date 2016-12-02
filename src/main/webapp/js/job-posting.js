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

function viewJobPosting(){
    var jobPostingId = $(this).closest("tr").attr("data-job-posting-id");
    $.ajax({
        url: "/usjobs/service/job/" + jobPostingId,
        contentType : "application/json",
        dataType: "json",
        success: function(data) {
            $("#job-posting-display td[data-field='id']").html(data.id);
            $("#job-posting-display td[data-field='jobTitle']").html(data.jobTitle);
            $("#job-posting-display td[data-field='employerName']").html(data.company.employerName);
            $("#job-posting-display td[data-field='jobDescription']").html(data.jobDescription);
            $("#job-posting-display td[data-field='location']").html(data.location);
            $("#job-posting-display td[data-field='salary']").html(data.salary);
            $("#job-posting-display td[data-field='website']").html(data.website);
        }
    });
    $("#job-posting-display").dialog("open");
}

function disableJobPosting(){
    var jobPostingId = $(this).closest("tr").attr("data-job-posting-id");
    var disableEnableDiv = $(this).closest("div");
    $.ajax({
        url: "/usjobs/service/job/admin/toggle/" + jobPostingId,
        method : "PUT",
        context : $(this),
        success: function() {
        	var newHtml = "<a class=\"enablePosting\" role=\"button\" href=\"javascript:void(0)\">|<i class=\"fa fa-level-up\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Enable</a>";
        	console.log(disableEnableDiv.html());
        	disableEnableDiv.html(newHtml);
        	disableEnableDiv.find(".enablePosting").click(enableJobPosting);
        }
    });
}

function enableJobPosting(){
    var jobPostingId = $(this).closest("tr").attr("data-job-posting-id");
    var disableEnableDiv = $(this).closest("div");
    $.ajax({
        url: "/usjobs/service/job/admin/toggle/" + jobPostingId,
        method : "PUT",
        context : $(this),
        success: function() {
        	var newHtml = "<a class=\"disablePosting\" role=\"button\" href=\"javascript:void(0)\">|<i class=\"fa fa-level-up\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Disable</a>";
            console.log(disableEnableDiv.html());
            disableEnableDiv.html(newHtml);
            disableEnableDiv.find(".disablePosting").click(disableJobPosting);
        }
    });
}

function toggleJob() {
	console.log('toggle called: ' + $(this).closest("tr").attr("data-job-id"));
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
						console.log('Has close job; ' + newHtml);
					} else if ($(this).hasClass("openJob")) {
						$(this).removeClass("btn-info openJob").addClass(
								"btn-warning closeJob");
						newHtml = "<i class=\"fa fa-level-down\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Close";
						console.log('Has open job; ' + newHtml);
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

function addJob() {
	 $.ajax({
	        url: "/usjobs/service/job",
	        method: "POST",
	        dataType: "json",
	        processData: false,
	        contentType: "application/json",
	        data: JSON.stringify({
				jobTitle : $("input[name='jobtitle']").val(),
				website : $("input[name='website']").val(),
				location : $("input[name='location']").val(),
				jobDescription : $("textarea[name='jobdescription']").val(),
				salary : $("input[name='salary']").val()
			}),
	        success: function(data){
	        	var jobId = data.id;
	        	var title = data.jobTitle;
	        	var website = data.website;
	        	var location = data.location;
	        	var description = data.jobDescription;
	        	var salary = data.salary;
	        	var newRow = "<tr " +
	        			"data-job-id='" + jobId + "' " +
	        			"data-job-title='" + title + "' " + 
	        			"data-job-website='" + website + "' " + 
	        			"data-job-location='" + location + "' " + 
	        			"data-job-description='" + description + "' " +
	        			"data-job-salary='" + salary + "' >" + 
	        			"<td data-field='jobTitle'>" + title + "</td>" +
	        			"<td>" +
	        			"<a href='/usjobs/application/jobApplications?jobId=" + jobId + "' " +
	        			"role='button' class='btn btn-sm btn-primary'><i class='fa fa-shopping-basket' " +
	        			"aria-hidden='true'></i>&nbsp;&nbsp;Applications </a>" +
	        			"<a role='button' class='btn btn-sm btn-info openJob'> " +
	        			"<i class='fa fa-level-up' aria-hidden='true'></i>&nbsp;&nbsp;Open</a>" +
	        			"<a role='button' class='btn btn-sm btn-success editJob'> " +
	        			"<i class='fa fa-pencil-square-o' aria-hidden='true'></i>&nbsp;&nbsp;Edit </a> " +
	        			"<a role='button' class='btn btn-sm btn-danger deleteJob'> " +
	        			"<i class='fa fa-times-circle'></i>&nbsp;&nbsp;Delete </a>" +
						"</td></tr>";
	        	
	        	console.log(newRow);
	        	$("#jobs").append($(newRow));
	        	
	        	// Need to re-attach event handlers to account for this newly added row.
	        	$(".deleteJob").unbind('click.namespace').bind('click.namespace', deleteJob);
	        	$(".openJob, .closeJob").unbind('click.namespace').bind('click.namespace', toggleJob);
	        	$(".editJob").unbind('click.namespace').bind('click.namespace', editHandler);
	        }
	    });
}

function editHandler() {
	console.log('editHndler called');
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
}

$(function() {

	/**
	 * Dialog config for the edit job panel
	 */
	$("#editJobDialog").dialog({
		autoOpen : false,
		minWidth: 500,
		title: "Edit Job",
		close: function(event, ui) {
			$("input[name='jobId']").val('');
		},
		buttons : {
			"Save" : function() {
				if (!$("input[name='jobId']").val()) {
					addJob();
				} else {
					editJob();
				}
				$("#editJobDialog").dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	
	/**
	 * Dialog config for the view job panel
	 */
	$("#job-posting-display").dialog({
		autoOpen: false,
		minWidth: 500,
		title: "View Job Postingb",
	});

	$(".deleteJob").unbind('click.namespace').bind('click.namespace', deleteJob);
	$(".openJob, .closeJob").unbind('click.namespace').bind('click.namespace', toggleJob);
	$(".editJob").unbind('click.namespace').bind('click.namespace', editHandler);
	
	/**
	 * Event handler for adding a job under 'Manage Job Listings'
	 *  in the Employer profile
	 */
	$("#addJob").click(function() {
		$("#editJobForm")[0].reset();
		$("#editJobDialog").dialog("open");
	});
	
	/**
	 * Event handler for viewing/enabling/disabling a job under 'Admin Job Listings'
	 */
	$(".viewPosting").click(viewJobPosting);
	$(".enablePosting").click(enableJobPosting);
	$(".disablePosting").click(disableJobPosting);
});
