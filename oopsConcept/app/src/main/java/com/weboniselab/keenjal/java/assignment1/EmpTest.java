package com.weboniselab.keenjal.java.assignment1;

/**
 * Created by webonise on 10/5/16.
 */
public class EmpTest {

        public static void main(String args[])
        {
            Encapsulation obj = new Encapsulation();
            obj.setEmpNo(101);
            obj.setEmpName("Sandeep");
            obj.setEmpAge(26);
            System.out.println("Employee No :" +obj.getEmpNo());
            System.out.println("Employee Name :" +obj.getEmpName());
            System.out.println("Employee Age :" +obj.getEmpAge());
        }
    }
