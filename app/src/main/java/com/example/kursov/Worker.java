package com.example.kursov;

public class Worker {
    private int id;
    private String name;
    private String position;
    private String salary;
    private String experience;

    public Worker(int id, String name, String position, String salary, String experience){
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.position = position;
        this.salary = salary;
    }

    public String getExperience() {
        return this.experience;
    }

    public int getId() {
        return this.id;
    }

    public String getSalary() {
        return this.salary;
    }

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
