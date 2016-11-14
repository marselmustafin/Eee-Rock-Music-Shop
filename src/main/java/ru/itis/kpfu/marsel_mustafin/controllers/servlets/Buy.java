package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.models.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Buy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        super.doGet(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Cart cart = (Cart) rq.getSession().getAttribute("cart");
        int quantuty = cart.getProductCount();
        int totalPrice = cart.getTotalPrice();
        if(cart.getProductCount()!=0) {
            cart.clear();
            rs.getWriter().write("You bought " + quantuty + " goods worth " + totalPrice + "$");
        }else {
            rs.getWriter().write("Your cart is empty!");
        }
    }
}
