<%@include file="header.jsp" %>
<div class="content" align="center">
    <div class="page-header">
        <h1>Login</h1>
    </div>
    <form action="/login" method="post">
        <div class="input-group" style="width: 200px">
            <input type="text" name="login" class="form-control" placeholder="Login" required>
            <input type="password" name="password" class="form-control" placeholder="Password" required>
        </div>
        <br>
        <label>Remember me</label>
        <input type="checkbox" value="Remember me" name="remember">
        <input type="submit" value="Sign In"><br><br>
        <p>First time with us? Please <a href="/registration">Sign Up</a> otherwise you will not be able to make
            purchases.</p>
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <div class="alert alert-danger" role="alert"><b>Oh no! </b><%=error%>
        </div>
        <%}%>
        </p>
    </form>
</div>

<%@include file="footer.jsp" %>
