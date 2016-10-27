<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>US Jobs - View Job Posting</title>
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
					Job Posting
				</h3>
			</div>
			<div class="panel-body">
				<h4>
					<strong>Id</strong>
				</h4>
				<p>${jobPosting.id }</p>
				<h4>
					<strong>Title</strong>
				</h4>
				<p>${jobPosting.jobTitle }</p>
				<h4>
					<strong>Description</strong>
				</h4>
				<p>${jobPosting.jobDescription }</p>
				<h4>
					<strong>Employer</strong>
				</h4>
				<p>${jobPosting.company.employerName}</p>
				<h4><strong>Location</strong></h4>
				<p>${jobPosting.location }</p>
				<h4>
					<strong>Salary</strong>
				</h4>
				<p>${jobPosting.salary }</p>
				<h4>
					<strong>Website</strong>
				</h4>
				<p>${jobPosting.website }</p>
			</div>
		</div>
	</div>
</body>
</html>