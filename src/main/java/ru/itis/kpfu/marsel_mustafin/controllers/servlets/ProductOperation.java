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
        filePath = getServletContext().getRealPath("/") + "img/albumcovers/";
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
        int quantity = Integer.parseInt(textParams.get("quantity"));
        int price = Integer.parseInt(textParams.get("price"));
        String description = textParams.get("description");
        Product product = new Product(band, album, description, quantity, price);
        ProductDAO dao = new ProductDAO();
        String result;
        int productId = 0;
        if (operation.equals("edit")) {
            productId = Integer.parseInt(rq.getParameter("id"));
            product.setId(productId);
            if (dao.edit(product)) {
                result = "/edit.jsp?id=" + productId + "&succ=Product successfully edited";
            } else {
                result = "/edit.jsp?id=" + productId + "&error=Error, couldn't change product";
            }
        } else {
            if (dao.addNew(product)) {
                productId = dao.getLastId();
                result = "/add.jsp?succ=Product successfully added";
            } else {
                result = "/add.jsp?error=Error, couldn't add product";
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
