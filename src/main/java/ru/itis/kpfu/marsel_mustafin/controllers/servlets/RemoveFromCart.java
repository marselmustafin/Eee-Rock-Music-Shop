package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.models.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        super.doGet(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        int id = Integer.parseInt(rq.getParameter("prodid"));
        Cart cart = (Cart) rq.getSession().getAttribute("cart");
        if (cart.removeProduct(id)){
            rs.getWriter().print("Succes");
        }else {
            rs.getWriter().print("Can't remove");
        }
    }
}
