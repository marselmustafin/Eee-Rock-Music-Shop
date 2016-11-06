package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.controllers.db.AccountDAO;
import ru.itis.kpfu.marsel_mustafin.models.AccountManager;
import ru.itis.kpfu.marsel_mustafin.models.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
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
                if (AccountManager.validate(login, password)) {
                    makeAttributes(s, login);
                }
            }
        }
        if (s.getAttribute("account") != null) {
            rs.sendRedirect("/products?page=1");
        } else {
            rq.getRequestDispatcher("/login.jsp").forward(rq, rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        HttpSession s = rq.getSession();
        if (AccountManager.validate(login, password)) {
            if (rq.getParameter("remember") != null) {
                Cookie cookie = new Cookie(login, password);
                cookie.setMaxAge(24 * 60 * 60);
                rs.addCookie(cookie);
            }
            makeAttributes(s, login);
            rs.sendRedirect("/products?page=1");
        } else if (!AccountManager.isRegistrated("login", login)) {
            rs.sendRedirect("login.jsp?msg=Login is not registered");
        } else {
            rs.sendRedirect("login.jsp?msg=Uncorrect password");
        }
    }

    private void makeAttributes(HttpSession s, String login) {
        s.setAttribute("account", new AccountDAO().getFirst("login", login));
        int role = AccountManager.isAdmin(login) ? 2 : 1;
        s.setAttribute("role", role);
        s.setAttribute("cart", new Cart());
    }
}


