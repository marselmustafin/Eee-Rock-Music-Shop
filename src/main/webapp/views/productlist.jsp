<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Cart" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<div>
    <%
        Cart cart;
        cart = (Cart) session.getAttribute("cart");
        Map<Integer, Product> products = (Map) cart.getProducts();
    %>
    <h4>Products in cart:<%=cart.getProductCount()%>
    </h4>
    <h5>Total price:<%=cart.getTotalPrice()%>$
    </h5>
    <table>
        <%
            Iterator it = products.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Product> pair = (Map.Entry) it.next();
                Product p = pair.getValue();
        %>
        <div id=<%=pair.getKey()%>>
            <tr>
                <td>Product number:</td>
                <td><%=p.getId()%>
                </td>

            </tr>
            <tr>
                <td>Product name:</td>
                <td><%=p.getBandName()%> - <%=p.getAlbumName()%>
                </td>

            </tr>
            <tr>
                <td>Quantity:</td>
                <td><%=p.getQuantity()%>
                </td>
            </tr>
            <tr>
                <td>
                    <form class="removefromcart" method="post">
                        <input class="prodid" type="hidden" name="prodid" value=<%=pair.getKey()%>>
                        <input type="submit" value="Remove">
                    </form>
                </td>
            </tr>
        </div>
        <%}%>
    </table>
</div>
