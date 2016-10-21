<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>US Jobs - View Job Posting</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
    <jsp:include page="../header.jsp" />
	    <div class="col col-md-8 col-md-offset-2 jumbotron">
	    	<h2>View Job Posting ID #${jobPosting.id}</h2>
		    <table class="table table-striped table-hover">
		        <tr>
		            <th>ID</th>
		            <th>Job Title</th>
		            <th>Job Description</th>
		            <th>Employer</th>
		            <th>Location</th>
		            <th>Salary</th>           
		            <th>Link</th>
		        </tr>
		        <tr>
		            <td>${jobPosting.id}</td>
		            <td>${jobPosting.jobTitle}</td>
		            <td>${jobPosting.jobDescription}</td>
		            <td>${jobPosting.company.employerName}</td>
		            <td>${jobPosting.location}</td>
		            <td>${jobPosting.salary}</td>
		            <td>${jobPosting.website}</td>           
		        </tr>
		    </table>
		</div>
	</body>
</html>