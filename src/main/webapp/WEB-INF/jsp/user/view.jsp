<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>US Jobs - View User</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
    <jsp:include page="../header.jsp" />
	    <div class="col col-md-8 col-md-offset-2 jumbotron">
	    	<h2>View ${user.firstName} Profile</h2>
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
	</body>
</html>