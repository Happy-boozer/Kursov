package com.example.kursov;

public class FIL {

    private int id;
    private String address;
    private int number;

    public FIL(int id, String name, int number){
        this.id = id;
        this.address = name;
        this.number = number;
    }

    public FIL(int anInt, String string, String string1, String string2, String string3) {
    }

    public int getId() {
        return this.id;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String name) {
        this.address = name;
    }
    
    public void setNumber(int number){
        this.number = number;
    }
}
