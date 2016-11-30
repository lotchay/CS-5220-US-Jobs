/**
 * CRUD operations with job postings.
 */

$(function() {
	console.log('jquery loaded.');
	$.ajax({
		url: "service/job/1",
		dataType: "json",
		success: function(data) {
			console.log(JSON.stringify(data));
		}
	});
});
