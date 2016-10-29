package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends HttpServlet {

    private Map passwords;

    @Override
    public void init() {
        passwords = new HashMap();
        passwords.put("martin", "123");
        passwords.put("Jackson", "1995");
        passwords.put("Godzilla", "argh");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (s.getAttribute("user") != null) {
            response.sendRedirect("/products");
        } else {
            request.getRequestDispatcher("/loginpage").forward(request, response);
        }
    }

    private boolean isRegistered(HttpServletRequest rq){
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}


