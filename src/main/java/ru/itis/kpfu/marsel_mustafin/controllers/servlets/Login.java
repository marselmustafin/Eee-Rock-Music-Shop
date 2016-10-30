package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.models.AccountManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
        Cookie[] cookies = rq.getCookies();
        HttpSession s = rq.getSession();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String login = cookie.getName();
                String password = cookie.getValue();
                if(AccountManager.validate(login, password)){
                    s.setAttribute("login", login);
                }
            }
        }
        if (s.getAttribute("login") != null) {
            rs.sendRedirect("/products.jsp");
        } else {
            rq.getRequestDispatcher("/login.jsp").forward(rq, rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        HttpSession s = rq.getSession();
        if (AccountManager.validate(login, password)){
            if (rq.getParameter("remember") != null) {
                Cookie cookie = new Cookie(login, password);
                cookie.setMaxAge(24 * 60 * 60);
                rs.addCookie(cookie);
            }
            s.setAttribute("login", login);
            rs.sendRedirect("/products.jsp");
        }else if(!AccountManager.isRegistrated("login",login)){
            rs.sendRedirect("login.jsp?msg=login is not registered");
        }else {
            rs.sendRedirect("login.jsp?msg=uncorrect password");
        }
    }
}


