package com.example.kedwi;

import java.util.Calendar;

public class LocalUserData {
    private String _password, _login;

    public LocalUserData (String login, String password) {
        _password = password; _login = login;
    }

    // GETTER
    public String get_password() {
        return _password;
    }
    public String get_login() {
        return _login;
    }


    // SETTER
    public void set_password(String password) {
        _password = password;
    }
    public void set_login(String login) {
        _login = login;
    }
}
