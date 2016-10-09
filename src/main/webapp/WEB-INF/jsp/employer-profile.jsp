<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User CP</title>

<link href="../css/bower.css" rel="stylesheet" />
<link href="../css/us-jobs.css" rel="stylesheet" />
<link href="../bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="row animated fadeIn">
		<div class="col col-md-2 col-md-offset-2">
			<ul class="list-group">
				<li class="list-group-item"><a href="#">Update Company Profile</a></li>
				<li class="list-group-item"><a href="#">Add a New Job Listing</a></li>
				<li class="list-group-item"><a href="#">Your Job Listings</a></li>
			</ul>
		</div>
		<div class="col col-md-6">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Update Profile</h3>
				</div>
				<div class="panel-body">
					<form class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="inputEmail" class="col-lg-2 control-label">Username</label>
								<div class="col-lg-10">Your Username</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-lg-2 control-label">Password</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="inputPassword"
										placeholder="Password">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-lg-2 control-label">Email</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="email"
										placeholder="email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-lg-2 control-label">Address</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="address"
										placeholder="address">
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
		</div>
	</div>
</body>

<script src="../js/bower.js"></script>
</html>