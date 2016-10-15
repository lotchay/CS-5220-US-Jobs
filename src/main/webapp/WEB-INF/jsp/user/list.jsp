<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List All Users</title>
</head>
<body>
    <h1>User Management</h1>
    
    <table border="8" cellspacing="8" cellpadding="8" bgcolor="#99ccff" width="80%" height="100px">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>           
            <th>Operations</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <a href="view/${user.id}.html">View</a> |
                <a href="edit.html?id=${user.id}">Edit</a>
            </td>            
        </tr>
        </c:forEach>
    </table>
    <p><a href="add.html">Add New User</a></p>        
</body>
</html>