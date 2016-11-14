<%@include file="header.jsp" %>
<div class="content" align="center">
    <div class="page-header"><h1>Registration</h1></div>
    <form action="/registration" method="post">
        <div class="input-group" style="width: 200px">
            <input type="text" name="login" class="form-control" placeholder="Login" required>
            <input type="password" name="password" class="form-control" placeholder="Password" required>
            <input type="password" name="confirm" class="form-control" placeholder="Confirm password" required>
            <input type="email" name="email" class="form-control" placeholder="Email" required>
        </div>
        <br>
        <input type="submit" value="Sign Up"><br><br>
        <%
            String error = request.getParameter("error");
            if (error != null) {
        %>
        <div class="alert alert-danger" role="alert"><b>Oh no! </b><%=error%>
        </div>
        <%} else if (request.getParameter("succ") != null) {%>
        <div class="alert alert-success" role="alert">
            <b>Registration Successful!</b> Now you can<a href="login"> Sign in</a>
        </div>
        <%}%>
    </form>
</div>
<%@include file="footer.jsp" %>
