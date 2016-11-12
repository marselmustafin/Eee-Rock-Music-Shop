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
    <%if (role == 1) { %>
    <form class="addform" method="post">
        <select class="prodq" name="prodq"><label>Quantity:</label>
            <%
                for (int k = 1; k <= p.getQuantity(); k++) {
                    String quantity = String.valueOf(k);
            %>
            <option value=<%=quantity%>><%=k%>
            </option>
            <%}%>
        </select>
        <input class="prodid" type="hidden" name="prodid" value=<%=p.getId()%>>
        <input type="submit" value="Add to cart">
    </form>
    <%}%>
    <a href="products?page=1">Back</a>
    <div>
        <h2>Comments</h2>
        <%if (role != 0) { %>
        <form class="commentform" method="post">
            <textarea class="msg" name="msg" cols="40" rows="5"></textarea><br>
            <input class="productid" type="hidden" name="productid" value=<%=request.getParameter("id")%>>
            <input type="submit" value="Post a comment">
        </form>
        <%}%>
    </div>
    <div id="comments">
        <%@include file="commentlist.jsp" %>
    </div>
</div>
<%@include file="footer.jsp" %>