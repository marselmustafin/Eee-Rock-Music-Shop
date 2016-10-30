package ru.itis.kpfu.marsel_mustafin.controllers.db;

import ru.itis.kpfu.marsel_mustafin.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {

    private Connection con;
    private DBController controller;

    public boolean registrate(Account account) throws SQLException {
        controller = new DBController();
        String query = "INSERT INTO accounts (login, password, email) VALUES (?, ?, ?)";
        con = controller.getCon();
        if (con != null) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, account.getLogin());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.execute();
            con.close();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Account> getUserList() throws SQLException {
        controller = new DBController();
        String query = "SELECT login, password, email FROM accounts;";
        ResultSet rs = controller.executeStatement(query);
        return getListFromResultSet(rs);
    }

    public ArrayList<Account> getUserList(String column, String value) throws SQLException {
        controller = new DBController();
        Connection connection = controller.getCon();
        String query = "SELECT * FROM accounts WHERE "+ column +" = ?;";
        PreparedStatement ps;
        ArrayList result = null;
        ps = connection.prepareStatement(query);
        ps.setString(1, value);
        ResultSet rs = ps.executeQuery();
        result = getListFromResultSet(rs);
        connection.close();
        return result;
    }

    private ArrayList<Account> getListFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Account> result = new ArrayList<Account>();
        while (rs.next()) {
            String login = rs.getString("login");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int role = rs.getInt("role");
            result.add(new Account(login, password, email, role));
        }
        return result.isEmpty() ? null : result;
    }
}
