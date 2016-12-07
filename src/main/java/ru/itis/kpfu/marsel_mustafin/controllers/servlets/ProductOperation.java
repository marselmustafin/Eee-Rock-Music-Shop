package ru.itis.kpfu.marsel_mustafin.controllers.servlets;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.kpfu.marsel_mustafin.controllers.db.ProductDAO;
import ru.itis.kpfu.marsel_mustafin.models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ProductOperation extends HttpServlet {

    private String filePath;
    private int maxFileSize = 1024 * 1024 * 3;

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file_upload");
        System.out.println(filePath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(rq);
        if (!isMultipart) {
            rs.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setSizeMax(maxFileSize);
        try {
            List<FileItem> fileItems = upload.parseRequest(rq);
            FileItem image = null;
            Map<String, String> textParams = new HashMap<String, String>();
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    textParams.put(item.getFieldName(), item.getString());
                } else {
                    image = item;
                }
            }
            String redirect = saveDataToDB(textParams, image, rq);
            rs.sendRedirect(redirect);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
        }
    }

    private String saveDataToDB(Map<String, String> textParams, FileItem item, HttpServletRequest rq) {
        String operation = textParams.get("operation");
        String band = textParams.get("band");
        String album = textParams.get("album");
        int productId = rq.getParameter("id") != null ? Integer.parseInt(rq.getParameter("id")) : 0;
        int quantity, price;
        try {
            quantity = Integer.parseInt(textParams.get("quantity"));
            price = Integer.parseInt(textParams.get("price"));
        } catch (NumberFormatException e) {
            String err = "Incorrect number";
            if (operation.equals("edit")) {
                return rq.getRequestURI().concat("?id=" + productId + "&error=" + err);
            } else {
                return "/add_product?error=" + err;
            }
        }
        String description = textParams.get("description");
        Product product = new Product(band, album, description, quantity, price);
        ProductDAO dao = new ProductDAO();
        String result;
        if (operation.equals("edit")) {
            product.setId(productId);
            if (dao.edit(product)) {
                result = "/edit_product?id=" + productId + "&succ=Success";
            } else {
                result = "/edit_product?id=" + productId + "&error=Error";
            }
        } else {
            if (dao.addNew(product)) {
                productId = dao.getLastId();
                result = "/add_product?succ=Success";
            } else {
                result = "/add_product?error=Error";
            }
        }
        if (item != null) {
            saveFile(item, productId);
        }
        return result;
    }

    private void saveFile(FileItem item, int fileId) {
        File file = new File(filePath + fileId + ".jpg");
        try {
            item.write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
