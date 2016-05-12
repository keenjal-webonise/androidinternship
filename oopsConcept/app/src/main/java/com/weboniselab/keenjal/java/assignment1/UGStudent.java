package com.weboniselab.keenjal.java.assignment1;


public class UGStudent extends Student{

    private String course;

    public UGStudent(String course) {
        this.course = course;
    }

    public UGStudent(int age, int rollNo, String name, String course) {
        super(age, rollNo, name);
        this.course = course;
    }

    public UGStudent() {

    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


    @Override
    public void display(){
        System.out.println("Course : " +course);
    }
}
