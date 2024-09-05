package com.example.ontap_CRUD;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("students")
public class Students {
    @Id
    private String id;
    private String name;
    private int age;

    public Students(String name, int age) {
        super();
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
