/**
 * This JS file is used for validation.
 */
$(document).ready(function() {
	
	// JOB SEEKER PROFILE VALIDATION
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
	
	$("#editProfileSeeker").validate({
		rules: {
			password: {
				required: true,
				minlength: 5,
				nowhitespace: true
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password",
				nowhitespace: true
			},
			email: {
				required: true,
				email: true,
			},
			street: {
				required: true,
				minlength: 1,
			},
			city: {
				required: true,
				lettersonly: true,
				minlength: 1,
			},
			state: {
				required: true,
				stateUS: true,
			}, 
			zip: {
				required: true,
				minlength: 5,
				zipcodeUS: true,
			}
		}
	});
	// END JOB SEEKER VALIDATION
	
	
	//EMPLOYER VALIDATION
	$("#editProfileEmployer").validate({
		rules: {
			password: {
				required: true,
				minlength: 5,
				nowhitespace: true
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password",
				nowhitespace: true
			},
			email: {
				required: true,
				email: true,
			},
			street: {
				required: true,
				minlength: 1,
			},
			city: {
				required: true,
				lettersonly: true,
				minlength: 1,
			},
			state: {
				required: true,
				stateUS: true,
			}, 
			zip: {
				required: true,
				minlength: 5,
				zipcodeUS: true,
			}
		}
	});
	
	//ADMIN VALIDATION
	$("#editProfileAdmin").validate({
		rules: {
			password: {
				required: true,
				minlength: 5,
				nowhitespace: true
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password",
				nowhitespace: true
			},
			email: {
				required: true,
				email: true,
			},
			street: {
				required: true,
				minlength: 1,
			},
			city: {
				required: true,
				lettersonly: true,
				minlength: 1,
			},
			state: {
				required: true,
				stateUS: true,
			}, 
			zip: {
				required: true,
				minlength: 5,
				zipcodeUS: true,
			}
		}
	});
	
	
})