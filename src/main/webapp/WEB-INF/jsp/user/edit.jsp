<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>US Jobs - Edit User</title>
</head>
<body>
    <h2><a href="../index.html">US Jobs</a></h2>
    <h2>Edit Your Profile</h2>
    <form:form modelAttribute="user">
    <table border="8" cellspacing="8" cellpadding="8" bgcolor="#99ccff" width="80%" height="100px">
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
</body>
</html>