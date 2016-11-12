package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.controllers.db.CommentDAO;
import ru.itis.kpfu.marsel_mustafin.models.Account;
import ru.itis.kpfu.marsel_mustafin.models.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentOperation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        super.doGet(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Account current = (Account) rq.getSession().getAttribute("account");
        String name = current.getLogin();
        String text = rq.getParameter("msg");
        int product_id = Integer.parseInt(rq.getParameter("productid"));
        CommentDAO dao = new CommentDAO();
        dao.addNew(new Comment(name, text, product_id));
    }
}
