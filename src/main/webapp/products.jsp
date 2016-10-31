<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO" %>
<%@ page import="java.util.ListIterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<%
    ProductDAO dao = new ProductDAO();
    ArrayList<Product> products = dao.getAll();
    int types = products.size();
    int num = Integer.parseInt(request.getParameter("page"));
    int end = num * 10;
    int cursor = end - 10;
%>
<h2 align="center">Albums</h2>
<table align="center">
    <%
        while (cursor < end) {
    %>
    <tr>
        <%
            for (int k = 0; k < 2; k++) {
                Product p = cursor < types ? products.get(cursor) : null;
                if (p != null) {
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
                }
                cursor++;
            }
        %>
    </tr>
    <%} %>
</table>
<%
    for (int i = 0; i < types / 10; i++) {
        String href = "\"products.jsp?page=" + i + 1 + "\"";
%>
<a href=<%=href%>><%=i + 1%>
</a>
<%
    }
%>
<br>
<a href="/logout">Log Out</a>
</body>
</html>
