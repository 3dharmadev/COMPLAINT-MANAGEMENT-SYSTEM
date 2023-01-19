package com.queryresolvingsystem.bean;

public class Engineer {

    private String email;
    private int password;

    private  String Type;


    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "username='" + email + '\'' +
                ", password=" + password +
                '}';
    }
}
