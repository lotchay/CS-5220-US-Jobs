<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>US Jobs - Job Postings Management</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
	    <jsp:include page="../header.jsp" />
	    <div class="col col-md-8 col-md-offset-2 jumbotron">
		    <h1>Job Posting Management</h1>		    
		    <table class="table table-striped table-hover">
		        <tr>
		            <th>ID</th>
		            <th>Job Title</th>
		            <th>Employer</th>
		            <th>Location</th>
		            <th>Salary</th>           
		            <th>Link</th>
		        </tr>
		        <c:forEach items="${jobPostings}" var="jobPosting">
		        <tr>
		            <td>${jobPosting.id}</td>
		            <td>${jobPosting.jobTitle}</td>
		            <td>${jobPosting.company.employerName}</td>
		            <td>${jobPosting.location}</td>
		            <td>${jobPosting.salary}</td>
		            <td>${jobPosting.website}</td>
		            <td>
		                <security:authorize access="hasRole('ROLE_ADMIN')">
		                <a href="view/${jobPosting.id}.html">View</a> &nbsp; | &nbsp;&nbsp;
		                <c:if test="${jobPosting.enabled}">
		               		<a href="disable.html?id=${jobPosting.id}">Disable</a>
		                </c:if>
		                <c:if test="${not jobPosting.enabled}">
		               		<a href="enable.html?id=${jobPosting.id}">Enable</a>
		                </c:if>
		                </security:authorize> 
		            </td>            
		        </tr>
		        </c:forEach>
		    </table> 
	    </div>
	</body>
</html>