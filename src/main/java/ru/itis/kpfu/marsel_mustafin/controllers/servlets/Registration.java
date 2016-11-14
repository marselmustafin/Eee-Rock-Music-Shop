package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.controllers.db.AccountDAO;
import ru.itis.kpfu.marsel_mustafin.models.AccountManager;
import ru.itis.kpfu.marsel_mustafin.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        if (rq.getSession().getAttribute("account") != null) {
            rs.sendRedirect("/products?page=1");
        }
        rq.getRequestDispatcher("/views/registration.jsp").forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        String confirm = rq.getParameter("confirm");
        String email = rq.getParameter("email");
        if (!AccountManager.isRegistrated("login", login)) {
            if (!password.equals(confirm)) {
                rs.sendRedirect("/views/registration.jsp?error=Passwords are different");
            }
            if (AccountManager.emailCheck(email)) {
                AccountDAO us = new AccountDAO();
                if (us.addNew(new Account(login, password, email))) {
                    us.close();
                    rq.getRequestDispatcher("/views/registration.jsp?succ=ok").forward(rq, rs);
                }
            } else {
                rs.sendRedirect("/views/registration.jsp?error=Uncorrect email");
            }
        } else if (AccountManager.isRegistrated("login", login)) {
            rs.sendRedirect("/views/registration.jsp?error=Same login is already registered");
        } else {
            rs.sendRedirect("/views/registration.jsp?error=Same email is already used");
        }
    }
}