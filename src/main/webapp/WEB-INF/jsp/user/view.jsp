<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display One User</title>
</head>
<body>
    <table border="8" cellspacing="8" cellpadding="8" bgcolor="#99ccff" width="80%" height="100px">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Password</th>
        </tr>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>            
        </tr>
    </table>
</body>
</html>