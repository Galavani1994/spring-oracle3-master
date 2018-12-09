package com.developer.springOracle3.util;

public class MyException2 extends Exception{
    private String message;

    public MyException2(String message){
        super(message);
        this.message=message;
    }



    public String getMessage() {
        return message;
    }
}
