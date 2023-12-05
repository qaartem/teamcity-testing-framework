package com.example.teamcity.api.request;

import io.restassured.specification.RequestSpecification;

public class Request {
    protected final RequestSpecification spec;

    public Request(RequestSpecification spec){
        this.spec = spec;
    }

}
