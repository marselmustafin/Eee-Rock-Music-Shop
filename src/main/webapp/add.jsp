<%@include file="header.jsp" %>
<div align="center">
    <label>Edit</label>
    <form action="/add" method="post">
        <table>
            <tr>
                <td>
                    <input type="image" name="image">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Band name</label>
                </td>
                <td>
                    <input type="text" name="band">
                </td>
            </tr>
            <tr>
                <td><label>Album name</label></td>
                <td><input type="text" name="album"></td>
            </tr>
            <tr>
                <td><label>Quantity</label></td>
                <td><input type="text" name="quantity"></td>
            </tr>
            <tr>
                <td>
                    <label>Price</label>
                </td>
                <td>
                    <input type="text" name="price">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Description</label>
                </td>
                <td>
                    <textarea name="description"></textarea>
                </td>
            </tr>
        </table>
        <input type="submit" value="Add"><br>
        <%
            String msg = request.getParameter("msg");
            String success = request.getParameter("succ");
        %>
        <p class="error"><%=msg == null ? "" : msg%>
        <p class="success"><%=success == null ? "" : success%>
        </p>
    </form>
</div>
<%@include file="footer.jsp" %>
