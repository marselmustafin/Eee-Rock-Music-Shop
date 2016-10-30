package ru.itis.kpfu.marsel_mustafin.models;

import ru.itis.kpfu.marsel_mustafin.controllers.db.AccountDAO;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AccountManager {

    private static ArrayList<Account> accounts = null;

    public static boolean isRegistrated(String column, String value){
        AccountDAO dao = new AccountDAO();
        try {
            accounts = dao.getUserList(column, value);
            if (accounts != null && !accounts.isEmpty()) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validate(String login, String password){
        if(isRegistrated("login", login)){
            Account acc = accounts.get(0);
            return acc.getPassword().equals(password);
        }
        return false;
    }

    public static boolean emailCheck(String email){
        String email_regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        return Pattern.compile(email_regexp).matcher(email).matches();
    }

    public static  boolean isAdmin(String login){
        if(isRegistrated("login", login)){
            Account acc = accounts.get(0);
            return acc.isAdmin();
        }
        return false;
    }
}
