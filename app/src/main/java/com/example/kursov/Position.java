package com.example.kursov;

public class Position {
    private String Name;
    private int id;
    private String oklad;

    public Position(int anInt, String string, String string1) {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return this.Name;
    }

    public int getId() {
        return this.id;
    }

    public void setOklad(String oklad) {
        this.oklad = oklad;
    }

    public String getOklad() {
        return this.oklad;
    }
}
