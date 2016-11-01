package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO;
import ru.itis.kpfu.marsel_mustafin.models.Product;

import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;


public class Products extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        try {
            ProductDAO dao = new ProductDAO();
            ArrayList<Product> all = dao.getAll();
            int page = Integer.parseInt(rq.getParameter("page"));
            int end = page * 10 < all.size() ? page * 10 : all.size();
            int start = 10 * (page - 1);
            List<Product> products = all.subList(start, end);
            rq.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/products.jsp").forward(rq, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {

    }
}
