package com.weboniselab.keenjal.java.assignment1;


public class StudentInfo {

        public static void main(String args[])
        {
            Student student = new Student();

            student.setAge(20);
            student.setName("Kinjal");
            student.setRollNo(45);

            System.out.println("base class display method");
            student.display();

            UGStudent ugStudent = new UGStudent();
            ugStudent.setCourse("BCA");
            System.out.println("\n\nsub class display method");
            ugStudent.display();


        }
}

