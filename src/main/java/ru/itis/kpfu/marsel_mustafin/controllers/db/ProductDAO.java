package ru.itis.kpfu.marsel_mustafin.controllers.db;

import ru.itis.kpfu.marsel_mustafin.controllers.db.interfaces.DAO;
import ru.itis.kpfu.marsel_mustafin.models.Product;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product> {

    private DBController controller;
    private Connection con;

    public ProductDAO() {
        controller = new DBController();
        con = controller.getCon();
    }

    public boolean addNew(Product element) {
        String query = "INSERT INTO albums (band_name, album_name, description, quantity, price) VALUES (?, ?, ?, ?, ?)";
        return execute(element, query);
    }

    public boolean edit(Product element) {
        String query = "UPDATE albums SET band_name = ?, album_name = ?, description = ? , quantity = ?, price = ? WHERE id = " + element.getId();
        return execute(element, query);
    }

    public boolean execute(Product element, String query) {
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, element.getBandName());
                ps.setString(2, element.getAlbumName());
                ps.setString(3, element.getDescription());
                ps.setInt(4, element.getQuantity());
                ps.setInt(5, element.getPrice());
                ps.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean remove(int id) {
        if (con != null) {
            try {
                String query = "DELETE FROM albums WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                return ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Product> getAll() {
        String query = "SELECT id, band_name, album_name, description, quantity, price FROM albums";
        ResultSet rs = controller.executeQuery(query);
        return getListFromResultSet(rs);
    }

    public List<Product> getList(String param, String value) {
        try {
            String query = "SELECT * FROM albums WHERE " + param + " = ?;";
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

    public Product getFirst(String type, String value) {
        List<Product> temp = getList(type, value);
        return temp == null ? null : temp.get(0);
    }

    private List<Product> getListFromResultSet(ResultSet rs) {
        List<Product> result = new ArrayList<Product>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    String bandName = rs.getString("band_name");
                    String albumName = rs.getString("album_name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    int id = rs.getInt("id");
                    Product temp = new Product(bandName, albumName, description, quantity, price);
                    temp.setId(id);
                    result.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result.isEmpty() ? null : result;
    }

    public int getLastId() {
        String query = "SELECT max(id) FROM albums";
        ResultSet rs = controller.executeQuery(query);
        try {
            rs.next();
            return rs.getInt("max");
        } catch (SQLException e) {
            return 0;
        }
    }

    public void close() {
        controller.closeCon();
    }

}
