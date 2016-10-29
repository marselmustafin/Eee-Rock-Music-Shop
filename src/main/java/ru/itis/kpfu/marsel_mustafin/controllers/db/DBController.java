package ru.itis.kpfu.marsel_mustafin.controllers.db;

import java.sql.*;

public class DBController {

    private Connection CON = null;
    private final String USER = "postgres";
    private final String PASS = "1234";
    private final String URL = "jdbc:postgresql://localhost:5432/shop";

    DBController(){
        try {
            Class.forName("org.postgresql.Driver");
            CON = DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        Connection con = CON;
        return con;
    }

    public void closeCon() throws SQLException {
        CON.close();
    }

    public ResultSet executeStatement(String s){
        ResultSet rs = null;
        try {
            rs = CON.createStatement().executeQuery(s);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }

}
