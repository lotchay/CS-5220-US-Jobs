<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
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
                <security:authorize access="hasRole('ROLE_ADMIN') or principal.username == '${user.username}'">
                <a href="view/${user.id}.html">View</a> &nbsp; | &nbsp;&nbsp;
                <a href="edit.html?id=${user.id}">Edit</a>
                </security:authorize> 
            </td>            
        </tr>
        </c:forEach>
    </table>
    <security:authorize access="hasRole('ROLE_ADMIN')">
    <p><a href="add.html">Add New User</a></p>
    </security:authorize> 
    <form action="<c:url value='/logout' />" method="POST">
        <input name="_csrf" type="hidden" value="${_csrf.token}" />
        <input name="submit" type="submit" value="Logout" />
    </form>        
</body>
</html>