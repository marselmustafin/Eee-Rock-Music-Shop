<%@include file="header.jsp" %>
<div class="content" align="center">
    <%
        if (role != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    %>
    <div class="page-header">
        <h1>Add new product</h1>
    </div>
    <form action="/product_operation" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>
                    <input type="file" name="file" size="50"/>
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
            <input type="hidden" name="operation" value="add">
        </table>
        <input type="submit" value="Add"><br>
        <%
            request.setAttribute("operation", "add");
            String msg = request.getParameter("msg");
            String success = request.getParameter("succ");
        %>
        <p class="error"><%=msg == null ? "" : msg%>
        <p class="success"><%=success == null ? "" : success%>
        </p>
    </form>
</div>
<%@include file="footer.jsp" %>
