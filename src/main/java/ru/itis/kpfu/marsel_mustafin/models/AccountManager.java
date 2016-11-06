package ru.itis.kpfu.marsel_mustafin.models;

import ru.itis.kpfu.marsel_mustafin.controllers.db.AccountDAO;

import java.util.regex.Pattern;

public class AccountManager {

    private static Account account = null;

    public static boolean isRegistrated(String column, String value) {
        AccountDAO dao = new AccountDAO();
        account = dao.getFirst(column, value);
        dao.close();
        return account != null;
    }

    public static boolean validate(String login, String password) {
        return isRegistrated("login", login) && account.getPassword().equals(password);
    }

    public static boolean emailCheck(String email) {
        String email_regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        return Pattern.compile(email_regexp).matcher(email).matches();
    }

    public static boolean isAdmin(String login) {
        return isRegistrated("login", login) && account.isAdmin();
    }
}
