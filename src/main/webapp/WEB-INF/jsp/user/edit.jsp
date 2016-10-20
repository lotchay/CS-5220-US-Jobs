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
        Password: <form:input path="password" size='30' /> <br />
        E-mail: <form:input path="email" size='50' /> <br />
        Street: <form:input path="address.street" size='50' /> <br />
        City: <form:input path="address.city" size='30' /> <br />
        State: <form:input path="address.state" size='30' /> <br />
        Zip Code: <form:input path="address.zip" size='30' /> <br />
        <input type="submit" name="save" value="Save" />
    </form:form>
</body>
</html>