package ru.itis.kpfu.marsel_mustafin.controllers.db;

import ru.itis.kpfu.marsel_mustafin.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    private Connection con;
    private DBController controller;

    public boolean registrate(User user) throws SQLException {
        controller = new DBController();
        String query = "INSERT INTO users (login, password) VALUES (?, ?)";
        con = controller.getCon();
        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                return ps.execute();
            } else {
                return false;
            }
        } finally {
            con.close();
        }
    }

    public ArrayList<User> getUserList() {
        controller = new DBController();
        ArrayList result = new ArrayList();
        String query = "SELECT login, password FROM users";
        ResultSet rs = controller.executeStatement(query);
        try {
            while (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");
                result.add(new User(login, password));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result.isEmpty()? null : result;
    }
}
