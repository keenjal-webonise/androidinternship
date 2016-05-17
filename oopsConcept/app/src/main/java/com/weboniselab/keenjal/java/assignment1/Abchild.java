package com.weboniselab.keenjal.java.assignment1;

 class Abchild extends Abperson{
    void callme()
    {
        System.out.println("this is callme");
    }

    public static void main(String[] args) {
        Abchild abchild = new Abchild();
        abchild.callme();
        abchild.normal();
    }

}
