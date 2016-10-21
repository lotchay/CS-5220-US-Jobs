<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>US Jobs - Edit User</title>
		<link href="<c:url value='/css/vendor/bower.css' />" rel="stylesheet" />
		<link href="<c:url value='/css/us-jobs.css' />" rel="stylesheet" />
		<link href="<c:url value='/bower_components/font-awesome/css/font-awesome.css' />" rel="stylesheet" />
		<script src="<c:url value='/js/vendor/bower.js' />"></script>
	</head>
	<body>
	    <jsp:include page="../header.jsp" />
	    <div class="col col-md-8 col-md-offset-2 jumbotron">
	    	<h2>Edit ${user.firstName}'s Profile</h2>
		    <form:form modelAttribute="user">
		    <table class="table table-striped table-hover">
		        <tr><td>Username: </td><td>${user.username}</td></tr>
		        <tr><td>Password: </td><td><form:input path="password" size='30' /></td></tr>
		        <tr><td>E-mail: </td><td><form:input path="email" size='50' /></td></tr>
		        <tr><td>Street: </td><td><form:input path="address.street" size='50' /></td></tr>
		        <tr><td>City: </td><td><form:input path="address.city" size='30' /></td></tr>
		        <tr><td>State: </td><td><form:input path="address.state" size='30' /></td></tr>
		        <tr><td>Zip Code: </td><td><form:input path="address.zip" size='30' /></td></tr>
		    </table>    
		    <input type="submit" name="save" value="Save" />
		    </form:form>
	    </div>
	</body>
</html>