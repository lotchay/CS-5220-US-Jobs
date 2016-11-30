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
	$.ajax({
		url : "/usjobs/service/job/toggle/" + jobId,
		method : "PUT",
		context : $(this),
		success : function() {
			var newHtml;
			if ($(this).hasClass("closeJob")) {
				$(this)
					.removeClass("btn-warning closeJob")
					.addClass("btn-info openJob");
				newHtml = "<i class=\"fa fa-level-up\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Open";
			} else if ($(this).hasClass("openJob")) {
				$(this)
					.removeClass("btn-info openJob")
					.addClass("btn-warning closeJob");
				newHtml = "<i class=\"fa fa-level-down\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Close";
			} else {
				console.log('could not determine toggle type.');
			}
			$(this).html(newHtml);
		}
	});
}

$(function() {

	/**
	 * Event handler for when we delete a job posting in the Employer profile
	 * page
	 */
	$(".deleteJob").click(deleteJob);
	
	/**
	 * Event handler for toggling open/close status job posting in the Employer profile
	 */
	$(".openJob, .closeJob").click(toggleJob);
	
});
