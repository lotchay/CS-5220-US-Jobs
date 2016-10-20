<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>US Jobs - Registration</title>
</head>
<body>
    <h1>US Jobs - Registration</h1>
    
    <form:form modelAttribute="user">
    <table border="8" cellspacing="8" cellpadding="8" bgcolor="#99ccff" width="80%" height="100px">
        <tr><td>Username: </td><td><form:input path="username" size='30'/> <form:errors path="username" /></td></tr>
        <tr><td>Password: </td><td><form:input path="password" size='30'/> <form:errors path="password" /></td></tr>
        <tr><td>First Name: </td><td><form:input path="firstName" size='50' /> <form:errors path="firstName" /></td></tr>
        <tr><td>Last Name: </td><td><form:input path="lastName" size='50' /> <form:errors path="lastName" /></td></tr>
        <tr><td>Email: </td><td><form:input path="email" size='50' /> <form:errors path="email" /></td></tr>
        <tr>
            <td>User Type: </td>
            <td>
                Job Seeker <form:radiobutton path="userRoles" value="ROLE_SEEKER" /> <form:errors path="userRoles" />
                Employer <form:radiobutton  path="userRoles" value="ROLE_EMPLOYER"  /> <form:errors path="userRoles" />            
            </td>        
        </tr>
    </table>
    <input type="submit" name="register" value="Register" />    
    </form:form>
</body>
</html>