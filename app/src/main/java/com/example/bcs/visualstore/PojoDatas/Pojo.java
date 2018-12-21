package com.example.bcs.visualstore.PojoDatas;

public class Pojo {
    private String username;

    public Pojo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}