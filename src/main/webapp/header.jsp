<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Eee-Rock Music Shop</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/ajax-req.js"></script>
    <script src="js/ajax-remove-cart.js"></script>
    <script src="js/ajax-add-comment.js"></script>
    <style type="text/css" src="css/style.css"></style>
</head>
<body>
<div align="center">
    <%
        int role = session.getAttribute("account") != null ? (Integer) session.getAttribute("role") : 0;
    %>
    <div>
        <table>
            <tr>
                <td><a href='index.jsp'>About</a></td>
                <td><a href='products?page=1'>Home</a></td>
                <%if (role == 0) {%>
                <td><a href='/login'>Login</a></td>
                <%} else {%>
                <td><a href='/logout'>Logout</a></td>
                <%}%>
                <%if (role == 1) {%>
                <td><a href="/cart.jsp">Cart</a></td>
                <%}%>
                <td>
                </td>
            </tr>
        </table>
        <br>
    </div>
</div>

