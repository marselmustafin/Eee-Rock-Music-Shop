package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO;
import ru.itis.kpfu.marsel_mustafin.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class ProductAdd extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
        rq.getRequestDispatcher("/add.jsp").forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        String band = rq.getParameter("band");
        String album = rq.getParameter("album");
        int quantity = Integer.parseInt(rq.getParameter("quantity"));
        int price = Integer.parseInt(rq.getParameter("price"));
        String description = rq.getParameter("description");
        Product newProd = new Product(band, album, description, quantity, price);
        ProductDAO dao = new ProductDAO();
        if (dao.addNew(newProd)) {
            rs.sendRedirect("/add.jsp?succ=Product successfully added");
        } else {
            rs.sendRedirect("/add.jsp?error=Error, couldn't add product");
        }
    }
}
