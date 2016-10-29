package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Products extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
        String user = (String) rq.getParameter("user");
        String pass = (String) rq.getParameter("pass");
        if (user == null && pass == null) {
            user = (String) rq.getSession().getAttribute("user");
            pass = (String) rq.getSession().getAttribute("pass");
        }
        HttpSession s = rq.getSession();
        Map m = (HashMap) s.getAttribute("passwords");
        if (m.containsKey(user) && pass.equals(m.get(user))) {
            if (rq.getParameter("remember") != null) {
                Cookie cookie = new Cookie(user, pass);
                cookie.setMaxAge(24 * 60 * 60);
                rs.addCookie(cookie);
            }
            rq.getRequestDispatcher("/products.jsp").forward(rq, rs);
        } else if (!m.containsKey(user)) {
            s.setAttribute("log_error_msg", "Uncorrect name:" + user);
            rs.sendRedirect("/login");
        } else {
            s.setAttribute("log_error_msg", "Uncorrect password for user:" + user);
            rs.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
    }
}
