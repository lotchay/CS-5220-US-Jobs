<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="../css/vendor/bower.css" rel="stylesheet" />
<link href="../css/us-jobs.css" rel="stylesheet" />
<link href="../bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application for ${jobPosting.jobTitle }</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="animated fadeIn row">
		<div class="col col-md-6 col-md-offset-3">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Application for
						${application.jobApplied.jobTitle }</h3>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="application" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="name" class="col-lg-2 control-label">Name</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="firstName"
										id="firstName" placeholder="First Name"
										value="${application.seeker.firstName }" />
									<form:input type="text" class="form-control" path="lastName"
										id="firstName" placeholder="Last Name"
										value="${application.seeker.lastName }" />
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-lg-2 control-label">Email</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="email"
										id="email" placeholder="Email Address"
										value="${application.seeker.email }" />
								</div>
							</div>
							<div class="form-group">
								<label for="phone" class="col-lg-2 control-label">Phone
									Number</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="phoneNumber"
										id="phone" placeholder="Phone Number"
										value="${application.seeker.phones[0]}" />
								</div>
							</div>
							<div class="form-group">
								<label for="coverLetter" class="col-lg-2 control-label">Cover
									Letter</label>
								<div class="col-lg-10">
									<form:textarea class="form-control" path="coverLetter" rows="5"
										id="coverLetter" placeholder="Write your cover letter here." />
								</div>
							</div>
							<div class="form-group">
								<label for="select" class="col-lg-2 control-label">Select
									a Resume</label>
								<div class="col-lg-10">
									<select class="form-control" id="select">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="submit" id="apply" class="btn btn-info">Apply</button>
									<button onclick="goBack()" class="btn btn-danger">Cancel</button>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="../js/vendor/bower.js"></script>
<script>
	function goBack() {
		window.history.back();
	}
</script>
</body>
</html>