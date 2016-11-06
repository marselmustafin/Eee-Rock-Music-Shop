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

    public boolean addNew(Account element) {
        String query = "INSERT INTO accounts (login, password, email) VALUES (?, ?, ?)";
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, element.getLogin());
                ps.setString(2, element.getPassword());
                ps.setString(3, element.getEmail());
                ps.execute();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Account> getAll() {
        String query = "SELECT login, password, email FROM accounts;";
        ResultSet rs = controller.executeStatement(query);
        return getListFromResultSet(rs);
    }

    public List<Account> getList(String param, String value) {
        String query = "SELECT * FROM accounts WHERE " + param + " = ?;";
        if (con != null) {
            try {
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
        }
        return null;
    }

    public Account getFirst(String type, String value) {
        List<Account> temp = getList(type, value);
        return temp == null ? null : temp.get(0);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<Account> getListFromResultSet(ResultSet rs) {
        List<Account> result = new ArrayList<Account>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    int role = rs.getInt("role");
                    Account temp = new Account(login, password, email);
                    temp.setRole(role);
                    result.add(temp);
                }
                return result.isEmpty() ? null : result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void close() {
        controller.closeCon();
    }
}
