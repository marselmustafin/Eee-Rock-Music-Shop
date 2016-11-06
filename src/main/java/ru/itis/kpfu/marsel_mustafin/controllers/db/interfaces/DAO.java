package ru.itis.kpfu.marsel_mustafin.controllers.db.interfaces;

import ru.itis.kpfu.marsel_mustafin.models.Account;

import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {

    boolean addNew(E element) throws SQLException;

    List<E> getAll() throws SQLException;

    List<E> getList(String param, String value);

    E getFirst(String type, String value);

    void close();
}
