
package com.kiet.Student.model;

public class Student {

    private String name;
    private int age;
    private int id;
    private String branch;
    private int year;
    private double cgpa;

    public Student() {
    }

    public Student(String name, int age, int id,
            String branch, int year, double cgpa) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.branch = branch;
        this.year = year;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getClassification() {
        if (cgpa >= 9.0) {
            return "Topper";
        } else if (cgpa >= 8.0) {
            return "Excellent";
        } else if (cgpa >= 7.0) {
            return "Good";
        } else if (cgpa >= 6.0) {
            return "Average";
        } else {
            return "Needs Improvement";
        }
    }
}
