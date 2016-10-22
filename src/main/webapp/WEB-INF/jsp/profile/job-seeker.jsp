<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Seeker Profile</title>

<link href="../css/vendor/bower.css" rel="stylesheet" />
<link href="../css/us-jobs.css" rel="stylesheet" />
<link href="../bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="row animated fadeIn">
		<div class="col col-md-2 col-md-offset-2">
			<ul id="options-list" class="list-group">
				<li id="viewProfile" class="list-group-item"><a href="#">View
						Profile</a></li>
				<li id="profile" class="list-group-item"><a href="#">Update
						Profile</a></li>
				<li id="resume" class="list-group-item"><a href="#">Resumes</a></li>
				<li id="appJobs" class="list-group-item"><a href="#">Applied
						Jobs</a></li>
				<li id="favJobs" class="list-group-item"><a href="#">Favorited
						Jobs</a></li>
			</ul>
		</div>
		<div id="detail" class="col col-md-6">
			<div id="viewProfileDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">View ${user.firstName} ${user.lastName }'s Profile</h3>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-hover">
				        <tr>
				            <th>ID</th>
				            <th>Username</th>
				            <th>First Name</th>
				            <th>Last Name</th>
				            <th>Email</th>
				            <th>Password</th>
				        </tr>
				        <tr>
				            <td>${user.id}</td>
				            <td>${user.username}</td>
				            <td>${user.firstName}</td>
				            <td>${user.lastName}</td>
				            <td>${user.email}</td>
				            <td>${user.password}</td>            
				        </tr>
				    </table>
				</div>
			</div>
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
										value="${user.username}" disabled>
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
			<div id="resumeDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Resume (s)</h3>
				</div>
				<div class="panel-body">TODO</div>
			</div>
			<div id="appJobsDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Jobs Applied</h3>
				</div>
				<div class="panel-body">
					<div class="panel-body">
						<table class="table table-striped table-hover">
							<tr class="info">
								<th>Title</th>
								<th>Salary</th>
								<th>Location</th>
								<th>Link</th>
							</tr>
							<c:forEach items="${user.appliedJobs}" var="appliedJob">
								<tr>
									<td>${appliedJob.jobTitle}</td>									
									<td>${appliedJob.salary}</td>
									<td>${appliedJob.location}</td>
									<td>${appliedJob.website}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div id="favJobsDetail" class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Jobs Favorited</h3>
				</div>
				<div class="panel-body">
					<div class="panel-body">
						<table class="table table-striped table-hover">
							<tr class="info">
								<th>Title</th>
								<th>Salary</th>
								<th>Location</th>
								<th>Link</th>
							</tr>
							<c:forEach items="${user.favoritedJobs}" var="favoritedJob">
								<tr>
									<td>${favoritedJob.jobTitle }</td>
									<td>${favoritedJob.salary}</td>
									<td>${favoritedJob.location}</td>
									<td>${favoritedJob.website}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="../js/vendor/bower.js"></script>
<script src="../js/list-detail.js"></script>
</html>