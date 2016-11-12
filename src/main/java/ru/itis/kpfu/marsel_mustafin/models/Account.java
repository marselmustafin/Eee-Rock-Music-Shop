package ru.itis.kpfu.marsel_mustafin.models;

public class Account {
    private String login;
    private String password;
    private String email;
    private int role = 1;//1 - user, 2 - admin

    public Account(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public boolean isAdmin() {
        return role == 2;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
