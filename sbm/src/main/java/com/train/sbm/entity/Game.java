package com.train.sbm.entity;

public class Game {

    private Integer id;
    private String name;
    private String type;
    private String role_id;
    private String role_name;

    public Game() {

    }

    public Game(Integer id, String name, String type, String role_id, String role_name) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", role_id='" + role_id + '\'' +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
