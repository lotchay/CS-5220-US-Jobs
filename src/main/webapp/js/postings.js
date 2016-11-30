function viewJobPosting(){
    var jobPostingId = $(this).closest("tr").attr("data-job-posting-id");
    $.ajax({
        url: "service/posting/" + jobPostingId + ".html",
        contentType : "application/json",
        dataType: "json",
        success: function(data) {
            $("#job-posting-display td[data-field='id']").html(data.id);
            $("#job-posting-display td data-field='jobTitle']").html(data.jobTitle);
            $("#job-posting-display td data-field='employerName']").html(data.company.employerName);
            $("#job-posting-display td data-field='location']").html(data.location);
            $("#job-posting-display td data-field='salary']").html(data.salary);
            $("#job-posting-display td data-field='website']").html(data.website);
        }
    });
    $("#job-posting-display").dialog("open");
}

$(document).ready(function(){
	$("#job-posting-display").dialog({
		autoOpen: false
	});
	$(".viewPosting").click(viewJobPosting);
});