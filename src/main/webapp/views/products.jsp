<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Product" %>
<%@ page import="java.util.LinkedList" %>
<%@include file="header.jsp" %>
<%
    LinkedList<Product> products = (LinkedList<Product>) request.getAttribute("products");
%>
<div class="content" align="center">
    <div class="page-header">
        <h1>Albums</h1>
    </div>
    <%if (role == 2) { %>
    <a class="redd" href="/add_product">Add new product</a>
    <% }%>
    <div class="table-responsive" style="margin-top: 2%">
        <table class="product_table">
            <%while (!products.isEmpty()) {%>
            <tr>
                <% for (int i = 0; i < 2; i++) {
                    if (!products.isEmpty()) {
                        Product p = products.poll();
                        String source = "../img/albumcovers/" + p.getId() + ".jpg";
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
                        Description: <%=p.getDescription().length() > 19 ? p.getDescription().substring(0, 20) : ""%>
                        ...</p>
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
                    <div id="result"></div>
                    <%}%>
                    <a class="redd" href=<%=prodHref%>>More</a>
                    <%
                        if (role == 2) {
                            String editHref = "/edit_product?id=" + p.getId();
                            String removeHref = "/product_remove?id=" + p.getId();
                    %>
                    <a class="redd" href=<%=editHref%>>Edit</a>
                    <a class="redd" onclick="alert('You`ve been deleted product');" href=<%=removeHref%>>Remove</a>
                    <%}%>
                </td>
                <%
                        }
                    }%>
            </tr>
            <%}%>
        </table>
    </div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <%
                int pages = (int) request.getAttribute("pages");
                for (int i = 1; i <= pages; i++) {
                    String pageHref = "/products?page=" + i;
            %>
            <li><a href=<%=pageHref%>><%=i%>
            </a></li>
            <% }%>
        </ul>
    </nav>
    <br>
</div>
<%@include file="footer.jsp" %>
