package com.developer.springOracle3;

public class MyException extends Exception {

    private String str;
    private String code;



    public MyException(String str,String code) {
        super(str);
        this.str = str;
        this.code=code;
    }

    public String getStr() {
        return str;
    }

    public String getCode() {
        return code;
    }
}