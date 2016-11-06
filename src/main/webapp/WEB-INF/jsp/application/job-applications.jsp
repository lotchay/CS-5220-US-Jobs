<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>US Jobs - View User</title>
<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
<link
	href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />"
	rel="stylesheet" />
<script src="<c:url value='/js/vendor/bower.js' />"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="animated fadeIn col-md-8 col-md-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-info-circle" aria-hidden="true"></i>&nbsp;&nbsp;View
					${user.firstName }'s profile
				</h3>
			</div>
			<div class="panel-body">
				<h4>
					<strong>Id</strong>
				</h4>
				<p>${user.id }</p>
				<h4>
					<strong>Username</strong>
				</h4>
				<p>${user.username }</p>
				<h4>
					<strong>First Name</strong>
				</h4>
				<p>${user.firstName }</p>
				<h4>
					<strong>Last Name</strong>
				</h4>
				<p>${user.lastName}</p>
				<h4>
					<strong>Email</strong>
				</h4>
				<p>${user.email }</p>
				<h4>
					<strong>Password</strong>
				</h4>
				<p>${user.password }</p>
			</div>
		</div>
	</div>
</body>
</html>