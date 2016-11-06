package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.models.Account;

import javax.servlet.http.*;
import java.io.IOException;

public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        HttpSession s = rq.getSession();
        Cookie[] cookies = rq.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                Account acc = (Account) s.getAttribute("account");
                if (cookie.getName().equals(acc.getLogin())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    rs.addCookie(cookie);
                }
            }
        }
        s.invalidate();
        rs.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {

    }
}
