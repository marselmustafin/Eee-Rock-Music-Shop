package ru.itis.kpfu.marsel_mustafin.controllers.db;

import ru.itis.kpfu.marsel_mustafin.controllers.db.interfaces.DAO;
import ru.itis.kpfu.marsel_mustafin.models.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements DAO<Comment> {

    private Connection con;
    private DBController controller;

    public CommentDAO() {
        controller = new DBController();
        con = controller.getCon();
    }

    public boolean addNew(Comment element) {
        String query = "INSERT INTO comments (author, text, product_id) VALUES (?, ?, ?)";
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, element.getAuthor());
                ps.setString(2, element.getText());
                ps.setInt(3, element.getProductId());
                ps.execute();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Comment> getAll() {
        String query = "SELECT id, author, text, product_id FROM comments;";
        ResultSet rs = controller.executeQuery(query);
        return getListFromResultSet(rs);
    }

    public List<Comment> getList(String param, String value) {
        try {
            String query = "SELECT * FROM comments WHERE " + param + " = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            if (isDigit(value)) {
                ps.setInt(1, Integer.parseInt(value));
            } else {
                ps.setString(1, value);
            }
            ResultSet rs = ps.executeQuery();
            return getListFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Comment getFirst(String type, String value) {
        List<Comment> temp = getList(type, value);
        return temp == null ? null : temp.get(0);
    }

    private List<Comment> getListFromResultSet(ResultSet rs) {
        List<Comment> result = new ArrayList<Comment>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    String author = rs.getString("author");
                    String text = rs.getString("text");
                    int productId = rs.getInt("product_id");
                    int id = rs.getInt("id");
                    Comment temp = new Comment(author, text, productId);
                    temp.setId(id);
                    result.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result.isEmpty() ? null : result;
    }

    public void close() {
        controller.closeCon();
    }
}
