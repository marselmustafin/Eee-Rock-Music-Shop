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

    public Account(String login, String password, String email, int role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        String email = this.email;
        return email;
    }

    public String getLogin() {
        String login = this.login;
        return login;
    }

    public String getPassword() {
        String password = this.password;
        return password;
    }

    public boolean isAdmin(){
        return role==2;
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
}
