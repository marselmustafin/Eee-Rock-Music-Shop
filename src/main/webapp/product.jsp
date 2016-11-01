<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int productId = Integer.parseInt(request.getParameter("id"));
    ProductDAO dao = new ProductDAO();
    Product p = dao.get(productId);
    dao.close();
%>
<div align="center">
    <table>
        <tr>
            <td>
                <%String source = "\"img/albumcovers/" + p.getImgId() + ".jpg\"";%>
                <p><img width="256" height="256" src=<%=source%>></p>
            </td>
            <td>
                <h3><%=p.getBandName()%> - <%=p.getAlbumName()%>
                </h3>
                <p>Quantity: <%=p.getQuantity()%>
                </p>
                <p>Price: <%=p.getPrice()%>$</p>
            </td>
        </tr>
        <tr>
            <td><p>Description: <%=p.getDescription()%>
            </p></td>
        </tr>
    </table>
    <br>
    <%if (session.getAttribute("role") != null) { %>
    <select>
        <%
            for (int i = 1; i <= p.getQuantity(); i++) {
                String quantity = "\"" + String.valueOf(i) + "\"";
        %>
        <option value=<%=quantity%>><%=i%>
        </option>
        <%}%>
    </select>
    <input type="button" value="Buy">
    <%}%>
</div>
<%@include file="footer.jsp" %>