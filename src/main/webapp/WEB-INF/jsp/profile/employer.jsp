<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employer Profile</title>

<link href="css/vendor/bower.css" rel="stylesheet" />
<link href="css/us-jobs.css" rel="stylesheet" />
<link href="bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="row animated fadeIn">
		<div class="col col-md-2 col-md-offset-2">
			<ul id="options-list" class="list-group">
				<li id="profile" class="list-group-item"><a href="#">Update
						Profile</a></li>
				<li id="company" class="list-group-item"><a href="#">Company
						Description</a></li>
				<li id="newJob" class="list-group-item"><a href="#">Add a
						New Job Listing</a></li>
				<li id="job" class="list-group-item"><a href="#">Your Job
						Listings</a></li>
			</ul>
		</div>
		<div id="detail" class="col col-md-6">
			<div id="profileDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Update Profile</h3>
				</div>
				<div class="panel-body">
					<form class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="username" class="col-lg-2 control-label">Username</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="username"
										value="Username goes here" disabled>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-lg-2 control-label">Password</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="inputPassword"
										placeholder="Password">
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-lg-2 control-label">Email</label>
								<div class="col-lg-10">
									<input type="email" class="form-control" id="email"
										placeholder="email">
								</div>
							</div>
							<div class="form-group">
								<label for="address" class="col-lg-2 control-label">Address</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="address"
										placeholder="address">
								</div>
							</div>
							<div class="form-group">
								<label for="phone" class="col-lg-2 control-label">Phone</label>
								<div class="col-lg-10">
									<input type="number" class="form-control" id="phone"
										placeholder="Phone #">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">Contact Sharing</label>
								<div class="col-lg-10">
									<div class="radio">
										<label> <input type="radio" name="contact-sharing"
											id="enableSharing" value="option1" checked="">
											Enabled
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="contact-sharing"
											id="disableSharing" value="option2"> Disabled
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="submit" class="btn btn-primary btn-block">Save</button>
									<button type="reset" class="btn btn-default btn-block">Reset</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div id="companyDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Company Description</h3>
				</div>
				<div class="panel-body">TODO</div>
			</div>
			<div id="newJobDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Post a New Job</h3>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="newJob" class="form-horizontal"
						action="addJob.html?employerId=${user.id}">
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
			<div id="jobDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Your Job Listings</h3>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-hover">
						<tr class="info">
							<th>Title</th>
							<th></th>
						</tr>
						<c:forEach items="${user.jobsPosted}" var="jobPosting">
							<tr>
								<td>${jobPosting.jobTitle }</td>
								<td>
									<form class="form-btn-container" action="editJob.html" method="get">
										<input type="hidden" name="employerId" value="3"/>
										<input type="hidden" name="jobId" value="${jobPosting.id }" />
										<button type="submit" class="btn btn-sm btn-success">Edit</button>
									</form>

									<form class="form-btn-container"
										action="deleteJob.html?employerId=${user.id}&jobId=${jobPosting.id }"
										method="post">
										<button type="submit" class="btn btn-sm btn-danger">Delete</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="js/vendor/bower.js"></script>
<script src="js/list-detail.js"></script>
</html>