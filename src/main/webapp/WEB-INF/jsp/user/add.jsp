<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a New Admin User</title>
</head>
<body>
    <form:form modelAttribute="user">
        Username: <form:input path="username" size='30'/> <form:errors path="username" /> <br />
        Password: <form:input path="password" size='30'/> <form:errors path="password" /> <br />
        First Name: <form:input path="firstName" size='50' /> <form:errors path="firstName" /> <br />
        Last Name: <form:input path="lastName" size='50' /> <form:errors path="lastName" /> <br />
        Email: <form:input path="email" size='60' /> <form:errors path="email" /> <br />
        <input type="submit" name="add" value="Add" />
    </form:form>
</body>
</html>
