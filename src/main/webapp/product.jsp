<%@include file="header.jsp" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDAO dao = new ProductDAO();
    Product p = dao.getFirst("id", request.getParameter("id"));
    dao.close();
%>
<div align="center">
    <table>
        <tr>
            <td>
                <%String source = "img/albumcovers/" + p.getId() + ".jpg";%>
                <p><img width="256" height="256" src=<%=source%>></p>
            </td>
            <td>
                <h3><%=p.getBandName()%> - <%=p.getAlbumName()%>
                </h3>
                <p>Quantity: <%=p.getQuantity()%>
                </p>
                <p>Price: <%=p.getPrice()%>$</p><br>
            </td>
        </tr>
        <tr>
            <td><p>Description: <%=p.getDescription()%>
            </p></td>
        </tr>
    </table>
    <br>
    <% Integer role = (Integer) session.getAttribute("role");
        if (role != null && role == 1) { %>
    <select>
        <%
            for (int i = 1; i <= p.getQuantity(); i++) {
                String quantity = String.valueOf(i);
        %>
        <option value=<%=quantity%>><%=i%>
        </option>
        <%}%>
    </select>
    <input type="button" value="Buy">
    <%}%>
    <a href="products?page=1">Back</a>
</div>
<%@include file="footer.jsp" %>