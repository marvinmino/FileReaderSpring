package com.example.demo.Models;

public class Person {
    String _name;
    String _surname;
    String _city;
    String _address;

    public Person(String name, String surname, String city, String address) {
        _name = name;
        _surname = surname;
        _city = city;
        _address = address;
    }

    public Person(String[] data) {
        _name = data[0];
        _surname = data[1];
        _city = data[2];
        _address = data[3];
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "_name='" + _name + '\'' +
                ", _surname='" + _surname + '\'' +
                ", _city='" + _city + '\'' +
                ", _address='" + _address + '\'' +
                '}';
    }
}
