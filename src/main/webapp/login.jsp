<%@include file="header.jsp" %>
<div align="center">
<form action="/login" method="post">
    <label>Username:</label>
    <input type="text" name="login"><br><br>
    <label>Password:</label>
    <input type="password" name="password"><br><br>
    <label>Remember me</label>
    <input type="checkbox" value="Remember me" name="remember">
    <input type="submit" value="Sign In"><br><br>
    <p>First time with us? Please <a href="/reg">Sign Up</a> otherwise you will not be able to make purchases.</p>
    <%String msg = request.getParameter("msg");%>
    <label><%=msg == null ? "" : msg%></label>
</form>
</div>
<%@include file="footer.jsp" %>
