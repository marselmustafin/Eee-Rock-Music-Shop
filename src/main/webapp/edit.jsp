<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO" %>
<%@include file="header.jsp" %>
<div align="center">
    <%
        Product p = new ProductDAO().getFirst("id", request.getParameter("id"));
    %>
    <form action="/product_edit" method="post">
        <%String source = "\"img/albumcovers/" + p.getId() + ".jpg\"";%>
        <p><img width="256" height="256" src=<%=source%>></p>
        <table>
            <tr>
                <td>
                    <label>Band name</label>
                </td>
                <td>
                    <input type="text" name="band" value=<%="\"" + p.getBandName() + "\""%>>
                </td>
            </tr>
            <tr>
                <td><label>Album name</label></td>
                <td><input type="text" name="album" value= <%="\"" + p.getAlbumName() + "\""%>></td>
            </tr>
            <tr>
                <td><label>Quantity</label></td>
                <td><input type="text" name="quantity" value= <%="\"" + p.getQuantity() + "\""%>></td>
            </tr>
            <tr>
                <td>
                    <label>Price</label>
                </td>
                <td>
                    <input type="text" name="price" value= <%="\"" + p.getPrice() + "\""%>>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Description</label>
                </td>
                <td>
                    <textarea name="description"><%=p.getDescription()%></textarea>
                </td>
            </tr>
        </table>
        <input type="submit" value="Edit"><br>
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