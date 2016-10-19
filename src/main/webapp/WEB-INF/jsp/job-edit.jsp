<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Job</title>

<link href="css/vendor/bower.css" rel="stylesheet" />
<link href="css/us-jobs.css" rel="stylesheet" />
<link href="bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="animated fadeIn row">
		<div class="col col-md-4 col-md-offset-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Edit Job</h3>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="editJob" class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="title" class="col-lg-2 control-label">Title</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="jobTitle"
										id="title" placeholder="Job Title" />
								</div>
							</div>
							<div class="form-group">
								<label for="website" class="col-lg-2 control-label">Website</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="website"
										id="website" placeholder="Job posting website" />
								</div>
							</div>
							<div class="form-group">
								<label for="location" class="col-lg-2 control-label">Location</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="location"
										id="location" placeholder="Job Location" />
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-lg-2 control-label">Description</label>
								<div class="col-lg-10">
									<form:textarea class="form-control" path="jobDescription"
										rows="3" id="description" />
								</div>
							</div>
							<div class="form-group">
								<label for="salary" class="col-lg-2 control-label">Salary</label>
								<div class="col-lg-10">
									<form:input type="text" class="form-control" path="salary"
										id="salary" placeholder="Job Salary" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="reset" class="btn btn-default">Cancel</button>
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="js/vendor/bower.js"></script>
</html>