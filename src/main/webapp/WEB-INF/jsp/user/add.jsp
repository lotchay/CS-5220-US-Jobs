<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>US Jobs - Add User</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
		<jsp:include page="../header.jsp" />
	    <div class="col col-md-8 col-md-offset-2 jumbotron">
		    <h2>Add an Admin User</h2>		    
		    <form:form modelAttribute="user">
		    <table class="table table-striped table-hover">
		        <tr><td>Username: </td><td><form:input path="username" size='30'/> <form:errors path="username" /></td></tr>
		        <tr><td>Password: </td><td><form:input path="password" size='30'/> <form:errors path="password" /></td></tr>
		        <tr><td>Confirm Password: </td><td><form:input path="password2" size='30'/> <form:errors path="password2" /></td></tr>
		        <tr><td>First Name: </td><td><form:input path="firstName" size='50' /> <form:errors path="firstName" /></td></tr>
		        <tr><td>Last Name: </td><td><form:input path="lastName" size='50' /> <form:errors path="lastName" /></td></tr>
		        <tr><td>Email: </td><td><form:input path="email" size='50' /> <form:errors path="email" /></td></tr>
		    </table>    
		    <input type="submit" name="add" value="Add" />        
		    </form:form>
		</div>
	</body>
</html>
