<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ListIterator" %>
<%@include file="header.jsp" %>
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        ListIterator<Product> li = products.listIterator();
    %>
<div align="center">
<h2>Albums</h2>
<table>
    <%show:while (true) {%>
    <tr>
        <% for (int i = 0; i < 2; i++) {
            if (li.hasNext()) {
                Product p = li.next();
                String source = "\"img/albumcovers/" + p.getImgId() + ".jpg\"";
                String href = "\"product.jsp?id=" + p.getId() + "\"";
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
            <p>Description: <%=p.getDescription().substring(0, 20)%>...</p>
            <a href=<%=href%>>More</a>
        </td>
        <%
                } else {
                    break show;
                }
            }
        %>
    </tr>
    <%}%>
</table>
<br>
</div>
<%@include file="footer.jsp" %>
