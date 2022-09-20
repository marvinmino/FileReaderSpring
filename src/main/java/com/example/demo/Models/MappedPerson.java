package com.example.demo.Models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "citta")
public class MappedPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String name;
    @NotBlank
    @Column(name = "cognome")
    private String surname;
    @NotBlank
    @Column(name = "citta")
    private String city;
    @NotBlank
    @Column(name = "indirizzo")
    private String address;

    public MappedPerson() {
    }

    public MappedPerson(String name, String surname, String city, String address) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.address = address;
    }

    public MappedPerson(String[] data) {
        name = data[0];
        surname = data[1];
        city = data[2];
        address = data[3];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
