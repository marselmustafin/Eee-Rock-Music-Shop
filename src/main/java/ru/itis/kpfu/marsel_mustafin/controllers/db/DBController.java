package ru.itis.kpfu.marsel_mustafin.controllers.db;

import java.sql.*;

public class DBController {

    private Connection con = null;
    private final String USER = "postgres";
    private final String PASS = "1234";
    private final String URL = "jdbc:postgresql://localhost:5432/shop";

    public DBController(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getCon() {
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

    public ResultSet executeQuery(String s){
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
