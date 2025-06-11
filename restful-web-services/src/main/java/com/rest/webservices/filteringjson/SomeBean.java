package com.rest.webservices.filteringjson;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//Static Filtering with @JsonIgnore and @JsonIgnoreProperties

//@JsonIgnoreProperties({"field1"})// This annotation will ignore field1 during serialization
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
   // @JsonIgnore // This annotation will ignore field2 during serialization
    private String field2;
    private String field3;


    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
