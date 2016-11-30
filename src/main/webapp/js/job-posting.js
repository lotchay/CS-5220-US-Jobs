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

$(function() {

	/**
	 * Event handler for when we delete a job posting in the Employer profile
	 * page
	 */
	$(".deleteJob").click(deleteJob);

});
