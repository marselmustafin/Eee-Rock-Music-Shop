<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body align="center">
<form action="/products" method="get">
    <label>Username:</label>
    <input type="text" name="user"><br><br>
    <label>Password:</label>
    <input type="password" name="pass"><br><br>
    <label>Remember me</label>
    <input type="checkbox" value="Remember me" name="remember">
    <input type="submit" value="Sign In"><br><br>
    <%String error = (String) session.getAttribute("log_error_msg");%>
    <p><%=error == null ? "" : error%></p>
    <p>First time with us? Please <a href="/register">Sign Up</a>> otherwise you will not be able to make purchases.</p>
</form>
</body>
</html>
