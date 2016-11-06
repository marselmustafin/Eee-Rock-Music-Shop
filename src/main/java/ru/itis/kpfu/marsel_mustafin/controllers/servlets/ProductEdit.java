package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO;
import ru.itis.kpfu.marsel_mustafin.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ProductEdit extends HttpServlet {

    String productId;

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
        productId = rq.getParameter("id");
        rq.getRequestDispatcher("/edit.jsp?id=" + productId).forward(rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        String band = rq.getParameter("band");
        String album = rq.getParameter("album");
        int quantity = Integer.parseInt(rq.getParameter("quantity"));
        int price = Integer.parseInt(rq.getParameter("price"));
        String description = rq.getParameter("description");
        Product edited = new Product(band, album, description, quantity, price);
        edited.setId(Integer.parseInt(productId));
        ProductDAO dao = new ProductDAO();
        if (dao.edit(edited)) {
            rs.sendRedirect("/edit.jsp?id=" + productId + "&succ=Product successfully edited");
        } else {
            rs.sendRedirect("/edit.jsp?id=" + productId + "&error=Error, couldn't change product");
        }
    }
}
