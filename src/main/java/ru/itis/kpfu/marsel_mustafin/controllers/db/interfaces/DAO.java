package ru.itis.kpfu.marsel_mustafin.controllers.db.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<E> {

    boolean addNew(E element) throws SQLException;

    ArrayList<E> getAll() throws SQLException;

    ArrayList<E> getList(String param, String value) throws SQLException;

    void close();
}
