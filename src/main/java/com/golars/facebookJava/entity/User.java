package com.golars.facebookJava.entity;

public class User {

    private String id;

    private String name;

    private String email;

    private String cover;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.email = "";
        this.cover = "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCover() {
        return cover;
    }
}