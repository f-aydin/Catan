package com.fatih.catan;

import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class CatanPerson {
    @Id
    private Integer id;

    @Column(name = "age")
    private int age;

    @Column(name = "firstname")
    private String name;

    public CatanPerson() {
    }

    public CatanPerson(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public CatanPerson(Integer id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CatanPerson{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
