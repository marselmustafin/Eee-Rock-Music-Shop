<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.models.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.kpfu.marsel_mustafin.controllers.db.CommentDAO" %>
<div>
    <%
        CommentDAO commentDao = new CommentDAO();
        List<Comment> comments = commentDao.getList("product_id", request.getParameter("id"));
        commentDao.close();
        if (comments != null) {
    %>
    <ol>
        <%for (Comment c : comments) { %>
        <li>
            Author: <%=c.getAuthor()%>
            <br>
            Comment: <%=c.getText()%>
        </li>
        <%
                }
            }
        %>
    </ol>
</div>
