<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a New User</title>
</head>
<body>
    <form:form modelAttribute="user">
        Username: <form:input path="username" /> <form:errors path="username" /> <br />
        Password: <form:input path="password" /> <form:errors path="password" /> <br />
        <input type="submit" name="add" value="Add" />
    </form:form>
</body>
</html>