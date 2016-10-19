<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit a User</title>
</head>
<body>
    <form:form modelAttribute="user">
        Username: ${user.username} <br />
        Password: <form:input path="password" /> <br />
        E-mail: <form:input path="email" /> <br />
        Street: <form:input path="address.street" /> <br />
        City: <form:input path="address.city" /> <br />
        State: <form:input path="address.state" /> <br />
        Zip Code: <form:input path="address.zip" /> <br />
        <input type="submit" name="save" value="Save" />
    </form:form>
</body>
</html>