package com.weboniselab.keenjal.java.assignment1;

public class Student {
    private int rollNo;
    private String name;
    private int age;

    public Student() {
        rollNo = 101;
        name = "kinjal";
        age = 24;
    }

    public Student(int age, int rollNo, String name) {
        this.age = age;
        this.rollNo = rollNo;
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
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

    public void display(){
        System.out.println("name :" +name);
        System.out.println("age :" +age);
        System.out.println("roll num :" +rollNo);
    }

}




