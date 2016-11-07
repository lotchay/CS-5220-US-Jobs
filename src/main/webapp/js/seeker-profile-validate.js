/**
 * This JS file is used for validation in job seeker's profile.
 */
$(document).ready(function() {
	
	/* Validate the upload. Make sure it's pdf, doc, or docx*/
	$("#uploadForm").validate({
		rules: {
			resume: {
				required: true,
				accept: "application/pdf, application/msword," +
						"application/vnd.openxmlformats-officedocument.wordprocessingml.document"
			}
		}
	});
})