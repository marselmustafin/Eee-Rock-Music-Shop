package ru.itis.kpfu.marsel_mustafin.controllers.db;

import ru.itis.kpfu.marsel_mustafin.controllers.db.interfaces.DAO;
import ru.itis.kpfu.marsel_mustafin.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements DAO<Account> {

    private Connection con;
    private DBController controller;

    public AccountDAO() {
        controller = new DBController();
        con = controller.getCon();
    }

    public boolean addNew(Account element) throws SQLException {
        String query = "INSERT INTO accounts (login, password, email) VALUES (?, ?, ?)";
        if (con != null) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, element.getLogin());
            ps.setString(2, element.getPassword());
            ps.setString(3, element.getEmail());
            ps.execute();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Account> getAll() throws SQLException {
        String query = "SELECT login, password, email FROM accounts;";
        ResultSet rs = controller.executeStatement(query);
        return getListFromResultSet(rs);
    }

    public ArrayList<Account> getList(String param, String value) throws SQLException {
        String query = "SELECT * FROM accounts WHERE " + param + " = ?;";
        PreparedStatement ps = con.prepareStatement(query);;
        ps.setString(1, value);
        ResultSet rs = ps.executeQuery();
        con.close();
        return getListFromResultSet(rs);
    }

    public Account get(String login) throws SQLException {
        ArrayList<Account> temp = getList("login", login);
        return temp == null ? null : temp.get(0);
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
