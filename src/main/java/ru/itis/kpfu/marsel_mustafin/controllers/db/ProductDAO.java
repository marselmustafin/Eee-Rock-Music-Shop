package ru.itis.kpfu.marsel_mustafin.controllers.db;

import ru.itis.kpfu.marsel_mustafin.controllers.db.interfaces.DAO;
import ru.itis.kpfu.marsel_mustafin.models.Account;
import ru.itis.kpfu.marsel_mustafin.models.Product;

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


    public boolean addNew(Product element) throws SQLException {
        String query = "INSERT INTO albums (band_name, album_name, description, quantity, price, img_id) VALUES (?, ?, ?, ?, ?, ?)";
        if (con != null) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, element.getBandName());
            ps.setString(2, element.getAlbumName());
            ps.setString(3, element.getDescription());
            ps.setInt(4, element.getQuantity());
            ps.setInt(5, element.getPrice());
            ps.setInt(6, element.getImgId());
            ps.execute();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Product> getAll() throws SQLException {
        String query = "SELECT id, band_name, album_name, description, quantity, price, img_id FROM albums";
        ResultSet rs = controller.executeStatement(query);
        return getListFromResultSet(rs);
    }

    public ArrayList<Product> getList(String param, String value) {
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

    public Product get(int id) {
        ArrayList<Product> temp = getList("id", String.valueOf(id));
        return temp == null ? null : temp.get(0);
    }

    private ArrayList<Product> getListFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Product> result = new ArrayList<Product>();
        while (rs.next()) {
            String bandName = rs.getString("band_name");
            String albumName = rs.getString("album_name");
            String description = rs.getString("description");
            int quantity = rs.getInt("quantity");
            int price = rs.getInt("price");
            int imgId = rs.getInt("img_id");
            int id = rs.getInt("id");
            Product temp = new Product(bandName, albumName, description, quantity, price, imgId);
            temp.setId(id);
            result.add(temp);
        }
        return result.isEmpty() ? null : result;
    }

    public void close() {
        if (controller != null) {
            try {
                controller.closeCon();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
