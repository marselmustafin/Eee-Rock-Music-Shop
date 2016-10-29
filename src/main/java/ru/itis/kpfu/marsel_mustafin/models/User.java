package ru.itis.kpfu.marsel_mustafin.models;

public class User {
    private String login;
    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        String login = this.login;
        return login;
    }

    public String getPassword() {
        String password = this.password;
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
