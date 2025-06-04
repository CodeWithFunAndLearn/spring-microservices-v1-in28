package com.rest.webservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public helloWorldBean helloWorldBean() {
        return new  helloWorldBean ("Hello World");
    }
}
