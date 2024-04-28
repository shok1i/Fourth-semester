package com.example.a2;

import java.io.Serializable;

public class User implements Serializable {
    private String _name;
    private int _age;

    public User(String name, int age) {
        _name = name; _age = age;
    }


    public int get_age() { return _age; }
    public void set_age(int age) { _age = age; }

    public String get_name() { return _name; }
    public void set_name(String name) { _name = name; }
}

