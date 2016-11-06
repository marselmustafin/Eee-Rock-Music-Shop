<%@include file="header.jsp" %>
<div align="center">
    <form action="/reg" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Confirm password:</td>
                <td><input type="password" name="confirm"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email"></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Sign Up"><br><br>
        <%String msg = request.getParameter("msg");%>
        <p class="error"><%=msg == null ? "" : msg%>
        </p>
    </form>
</div>
<%@include file="footer.jsp" %>
