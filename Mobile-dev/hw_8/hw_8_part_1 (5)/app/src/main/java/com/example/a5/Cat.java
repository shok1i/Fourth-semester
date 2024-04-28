package com.example.a5;

public class Cat {
    private String _name;
    private int _age;

    Cat (String name, int age) { _name = name; _age = age; }

    public String get_name() { return _name; }
    public void set_name(String name) { _name = name; }

    public int get_age() { return _age; }
    public void set_age(int age) { _age = age; }

    @Override
    public String toString() {
        return "Кличка кота: " + _name  + ", Возраст: " + _age;
    }
}

