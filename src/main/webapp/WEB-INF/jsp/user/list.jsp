<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>US Jobs - Users Management</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
	    <jsp:include page="../header.jsp" />
	    <div class="col col-md-8 col-md-offset-2 jumbotron">
		    <h1>User Management</h1>		    
		    <table class="table table-striped table-hover">
		        <tr>
		            <th>ID</th>
		            <th>Username</th>
		            <th>First Name</th>
		            <th>Last Name</th>
		            <th>Email</th>
		            <th>User Roles</th>
		            <th>Operations</th>
		        </tr>
		        <c:forEach items="${users}" var="user">
		        <tr>
		            <td>${user.id}</td>
		            <td>${user.username}</td>
		            <td>${user.firstName}</td>
		            <td>${user.lastName}</td>
		            <td>${user.email}</td>
		            <td>${user.userRoles}</td>
		            <td>
		                <security:authorize access="hasRole('ROLE_ADMIN')">
		                <a href="view/${user.id}.html">View</a> &nbsp; | &nbsp;&nbsp;
		                <a href="edit.html?id=${user.id}">Edit</a> &nbsp; | &nbsp;&nbsp;
		                <c:if test="${user.enabled}">
		               		<a href="disable.html?id=${user.id}">Disable</a>
		                </c:if>
		                <c:if test="${not user.enabled}">
		               		<a href="enable.html?id=${user.id}">Enable</a>
		                </c:if>
		                </security:authorize> 
		            </td>            
		        </tr>
		        </c:forEach>
		    </table>
		    <security:authorize access="hasRole('ROLE_ADMIN')">
	    		<p><a href="add.html">Add New User</a></p>
	    	</security:authorize>   
	    </div>
	</body>
</html>