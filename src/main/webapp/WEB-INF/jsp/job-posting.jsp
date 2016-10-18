<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Posting ${jobPosting.jobTitle}</title>

<link href="css/vendor/bower.css" rel="stylesheet" />
<link href="css/us-jobs.css" rel="stylesheet" />
<link href="bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="row">
		<div class="col col-md-8 col-md-offset-2">
			<h3>${jobPosting.jobTitle }</h3>
			<p>${jobPosting.jobDescription }</p>
			<div class="container">
				<input type="hidden" id="jobid" name="jobid"
					value="${jobPosting.id }" /> 
					<button type="button" id="favorite"class="btn btn-info">Favorite</button> 
					<button type="button" id="apply" class="btn btn-info">Apply</button>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-8 col-md-offset-2">
			<p class="text-success" id="status-msg"></p>
		</div>
	</div>
</body>

<script src="js/vendor/bower.js"></script>
<script src="js/job-posting.js"></script>

</html>