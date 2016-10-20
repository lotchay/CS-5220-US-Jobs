<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>US Jobs - Add User</title>
</head>
<body>
    <h2><a href="../index.html">US Jobs</a></h2>
    <h2>Add an Admin User</h2>
    
    <form:form modelAttribute="user">
    <table border="8" cellspacing="8" cellpadding="8" bgcolor="#99ccff" width="80%" height="100px">
        <tr><td>Username: </td><td><form:input path="username" size='30'/> <form:errors path="username" /></td></tr>
        <tr><td>Password: </td><td><form:input path="password" size='30'/> <form:errors path="password" /></td></tr>
        <tr><td>First Name: </td><td><form:input path="firstName" size='50' /> <form:errors path="firstName" /></td></tr>
        <tr><td>Last Name: </td><td><form:input path="lastName" size='50' /> <form:errors path="lastName" /></td></tr>
        <tr><td>Email: </td><td><form:input path="email" size='50' /> <form:errors path="email" /></td></tr>
    </table>    
    <input type="submit" name="add" value="Add" />        
    </form:form>
</body>
</html>
