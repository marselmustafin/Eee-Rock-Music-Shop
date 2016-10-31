package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.models.Account;

import javax.servlet.http.*;
import java.io.IOException;

public class Logout extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                Account acc = (Account) s.getAttribute("account");
                if (cookie.getName().equals(acc.getLogin())){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        s.invalidate();
        response.sendRedirect("/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
