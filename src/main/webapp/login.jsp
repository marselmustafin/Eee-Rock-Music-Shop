<%@include file="header.jsp" %>
<script>
    function showError(container, errorMessage) {
        container.className = 'error';
        var msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error-message") {
            container.removeChild(container.lastChild);
        }
    }

    function validate(form) {
        console.log("hello!!!");
        var elems = form.elements;

        resetError(elems.login.parentNode);
        if (!elems.login.value) {
            showError(elems.login.parentNode, 'Введите логин');
        }

        resetError(elems.password.parentNode);
        if (!elems.password.value) {
            showError(elems.password.parentNode, ' Укажите пароль.');
        }
    }
</script>
<div align="center">
    <form action="/login" method="post" onsubmit="validate(this.form)">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <br>
        <label>Remember me</label>
        <input type="checkbox" value="Remember me" name="remember">
        <input type="submit" value="Sign In"><br><br>
        <p>First time with us? Please <a href="/reg">Sign Up</a> otherwise you will not be able to make purchases.</p>
        <%String msg = request.getParameter("msg");%>
        <p class="error"><%=msg == null ? "" : msg%>
        </p>
    </form>
</div>

<%@include file="footer.jsp" %>
