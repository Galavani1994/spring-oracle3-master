package com.developer.springOracle3.util;

public class ExceptionMessage2 {
    private String errorMessage;

    public ExceptionMessage2(String eroeMessage) {
        this.errorMessage = eroeMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ExceptionMessage2 setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
