package ru.itis.kpfu.marsel_mustafin.controllers.db;

import java.sql.*;

public class DBController {

    private Connection con = null;
    private final String USER = "postgres";
    private final String PASS = "1234";
    private final String URL = "jdbc:postgresql://localhost:5432/shop";

    DBController(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        Connection con = this.con;
        return con;
    }

    public void closeCon(){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeStatement(String s){
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery(s);
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

}
