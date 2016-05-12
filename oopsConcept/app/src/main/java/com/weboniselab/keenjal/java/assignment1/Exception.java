package com.weboniselab.keenjal.java.assignment1;


public class Exception {

    /*static void check() throws ArithmeticException
    {
        System.out.println("inside check function");
        throw new ArithmeticException("demo");
    }*/

    public static void main(String args[])
    {
       try
       {
       //   check();
           int a[] = new int[2];
           System.out.println("Access element three :" + a[3]);
       }
       catch (ArrayIndexOutOfBoundsException e)
       {
           System.out.println("Exception thrown :" + e);
       }
        finally {
        // a[0] = 6;
          // System.out.println("First Element value :" +a[0]);
           System.out.println("The finally statement is executed");
       }
        //System.out.println("out of the block");
    }
}
