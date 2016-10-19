<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>${jobPosting.jobTitle} @ ${jobPosting.location}</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
		<jsp:include page="../header.jsp" />
	    <table border="1" cellspacing="1" cellpadding="1" bgcolor="#99ccff" width="80%" height="100px">
	        <tr>
	            <th>ID</th>
	            <th>Title</th>
	            <th>Employer</th>
	            <th>Description</th>
	            <th>Location</th>
	            <th>Salary</th>
	            <th>Website</th>
	            <th>Date Posted</th>
	            <th>Date Closed</th>
	            <th>Contact</th>
	        </tr>
	        <tr>
	            <td>${jobPosting.id}</td>
	            <td>${jobPosting.jobTitle}</td>
	            <td>${jobPosting.company.employerName}</td>            
	            <td>${jobPosting.jobDescription}</td>
	            <td>${jobPosting.location}</td>
	            <td>${jobPosting.salary}</td>
	            <td>${jobPosting.website}</td>
	            <td>${jobPosting.datePosted}</td>
	            <td>${jobPosting.dateClosed}</td>
	            <td>${jobPosting.pointOfContact.firstName} ${jobPosting.pointOfContact.lastName}<br /> 
	            ${jobPosting.pointOfContact.jobTitle}<br /> ${jobPosting.pointOfContact.email}<br />
	            ${jobPosting.pointOfContact.phone}</td>    
	        </tr>
	    </table>
	</body>
</html>