package com.rest.webservices.helloworld;

public class helloWorldBean {
    @Override
    public String toString() {
        return "helloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private  String message;

    public helloWorldBean(String message) {
        this.message = message;
    }
}
