<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="java.util.LinkedList" %>
<%@include file="header.jsp" %>
<%
    LinkedList<Product> products = (LinkedList<Product>) request.getAttribute("products");
    Integer role = (Integer) session.getAttribute("role");
%>
<div align="center">
    <h2>Albums</h2>
    <%if (role != null && role == 2) { %>
    <a href="/add">Add New Product</a>
    <% }%>
    <table>
        <%while (!products.isEmpty()) {%>
        <tr>
            <% for (int i = 0; i < 2; i++) {
                if (!products.isEmpty()) {
                    Product p = products.poll();
                    String source = "img/albumcovers/" + p.getId() + ".jpg";
                    String prodHref = "/product_detail?id=" + p.getId();
            %>
            <td>
                <p><img src=<%=source%> height="180" width="180"></p>
            </td>
            <td>
                <h3><%=p.getBandName()%> - <%=p.getAlbumName()%>
                </h3>
                <p>Quantity: <%=p.getQuantity()%>
                </p>
                <p>Price: <%=p.getPrice()%>$</p>
                <p>
                    Description: <%=p.getDescription().length() > 18 ? p.getDescription().substring(0, 20) : ""%>
                    ...</p>
                <%if (role != null && role == 1) { %>

                <select id="prodq"><label for="prodq">Quantity:</label>
                    <%
                        for (int k = 1; k <= p.getQuantity(); k++) {
                            String quantity = String.valueOf(k);
                    %>
                    <option value=<%=quantity%>><%=k%>
                    </option>
                    <%}%>
                </select>
                <input type="button" value="Buy">
                <%}%>
                <a href=<%=prodHref%>>More</a>
                <%
                    if (role != null && role == 2) {
                        String editHref = "/product_edit?id=" + p.getId();

                %>
                <a href=<%=editHref%>>Edit</a>
                <%}%>
            </td>
            <%
                    }
                }%>
        </tr>
        <%}%>
    </table>
    <br>
</div>
<%@include file="footer.jsp" %>
