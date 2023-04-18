package com.fatih.catan.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class CatanPerson {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "age")
    private int age;

    @Column(name = "firstname")
    private String firstName;

    public CatanPerson() {
    }

    public CatanPerson(int age, String firstName) {
        this.age = age;
        this.firstName = firstName;
    }

    public CatanPerson(int id, int age, String firstName) {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "CatanPerson{" +
                "id=" + id +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
