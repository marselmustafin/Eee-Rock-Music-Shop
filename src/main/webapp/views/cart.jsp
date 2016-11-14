<%@include file="header.jsp" %>
<div class="content" align="center">
    <%
        if (role != 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    %>
    <div class="page-header">
        <h1>Cart</h1>
    </div>
    <div id="products">
        <%@include file="productlist.jsp" %>
    </div>
    <div>
        <form class="buy" method="post">
            <input type="submit" value="Buy All">
        </form>
    </div>
</div>
<%@include file="footer.jsp" %>