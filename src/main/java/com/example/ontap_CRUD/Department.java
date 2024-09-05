package com.example.ontap_CRUD;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("department")
public class Department {
    @Id
    private String id;
    private String nameDepartment;
    private String[] users;

    public Department(String nameDepartment, String[] users) {
        super();
        this.nameDepartment = nameDepartment;
        this.users = users;
    }

    public String getnameDepartment() {
        return this.nameDepartment;
    }

    public void setnameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String[] getUsers() {
        return this.users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
