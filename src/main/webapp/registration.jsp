<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body align="center">
<form action="/reg" method="post">
    <label>Username:</label>
    <input type="text" name="login"><br><br>
    <label>Password:</label>
    <input type="password" name="pass"><br><br>
    <label>Email:</label>
    <input type="text" name="email"><br><br>
    <input type="submit" value="Sign Up"><br><br>
    <%String msg = request.getParameter("msg");%>
    <label><%=msg == null ? "" : msg%></label>
</form>
</body>
</html>
