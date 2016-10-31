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
        rq.getRequestDispatcher("/registration.jsp").forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
        String login = rq.getParameter("login");
        String password = rq.getParameter("pass");
        String email = rq.getParameter("email");
        try {
            if (!AccountManager.isRegistrated("login", login)) {
                if (AccountManager.emailCheck(email)) {
                    AccountDAO us = new AccountDAO();
                    if (us.addNew(new Account(login, password, email))) {
                        us.close();
                        rq.getRequestDispatcher("/regsucces.jsp").forward(rq, rs);
                    }
                } else {
                    rs.sendRedirect("registration.jsp?msg=uncorrect email");
                }
            } else if (AccountManager.isRegistrated("login", login)) {
                rs.sendRedirect("registration.jsp?msg=same login is already registered");
            } else {
                rs.sendRedirect("registration.jsp?msg=same email is already used");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}