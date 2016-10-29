<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%
    String user = (String) request.getParameter("user");
    String pass = (String) request.getParameter("pass");
    if(user==null&&pass==null){
        user = (String) session.getAttribute("user");
        pass = (String) session.getAttribute("pass");
    }
    Map m = (HashMap) session.getAttribute("passwords");
    if (m.containsKey(user) && pass.equals(m.get(user))) {
        if (request.getParameter("remember") != null) {
            Cookie cookie = new Cookie(user, pass);
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
        } %>
<h2>Hi, <%=user%>!</h2>
<a href="/logout">Log Out</a>
<%
    } else if (!m.containsKey(user)) {
        session.setAttribute("error_msg", "Uncorrect name:" + user);
        response.sendRedirect("/login");
    } else {
        session.setAttribute("error_msg", "Uncorrect password for user:" + user);
        response.sendRedirect("/login");
    }
%>
</body>
</html>
