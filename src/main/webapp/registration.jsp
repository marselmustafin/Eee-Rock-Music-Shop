<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/products" method="get">
    <label>Username:</label>
    <input type="text" name="user"><br><br>
    <label>Password:</label>
    <input type="password" name="pass"><br><br>
    <input type="submit" value="Sign Up"><br><br>
    <%String error = (String) session.getAttribute("reg_error_msg");%>
    <p><%=error == null ? "" : error%></p>
</form>
</body>
</html>
