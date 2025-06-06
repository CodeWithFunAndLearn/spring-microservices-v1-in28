package com.rest.webservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VersioningPersonController {
    // Versioning using URI
    @GetMapping(path = "/v1/person")
    public Person getV1Person() {
        return new Person("Bob the Builder");
    }

    @GetMapping(path = "/v2/person", produces = "application/json")
    public Name getV2Person() {
        return new Name("Bob", "The Builder");
    }
    // Versioning using request parameter
    @GetMapping(path = "/person", params = "version=1")
    public Person getV1PersonRequestParam() {
        return new Person("Bob the Builder");
    }

    @GetMapping(path = "/person", params = "version=2")
    public Name getV2PersonRequestParam() {
        return new Name("Bob", "The Builder");
    }
    //
    @GetMapping(path = "/person/header", headers = "api-version=1")
    public Person getV1PersonHeader() {
        return new Person("Bob the Builder");
    }

    @GetMapping(path = "/person/header", headers = "api-version=2")
    public Name getV2PersonHeader() {
        return new Name("Bob", "The Builder");
    }
    // Versioning using Accept Header
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public Person getV1PersonAcceptHeader() {
        return new Person("Bob the Builder");
    }
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public Name getV2PersonAcceptHeader() {
        return new Name("Bob", "The Builder");
    }
}

