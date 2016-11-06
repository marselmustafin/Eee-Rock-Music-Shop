package ru.itis.kpfu.marsel_mustafin.models;

public class Account {
    private String login;
    private String password;
    private String email;
    private int role = 1;//1 - user, 2 - admin

    public Account(String login, String password, String email) {
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
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

    public int getRole() {
        return role;
    }

    public boolean isAdmin() {
        return role == 2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
