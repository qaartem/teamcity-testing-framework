package com.example.teamcity.api.request.unchecked;

import com.example.teamcity.api.request.CrudInterface;
import com.example.teamcity.api.request.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UncheckedProject extends Request implements CrudInterface {
    private static final String PROJECT_ENDPOINT = "app/rest/projects";

    public UncheckedProject(RequestSpecification spec) {
        super(spec);
    }


    @Override
    public Response create(Object obj) {
        return given()
                .spec(spec)
                .body(obj)
                .post(PROJECT_ENDPOINT);
    }

    @Override
    public Response get(String id) {
        return given().spec(spec).get(PROJECT_ENDPOINT + "/id:" + id);
    }

    @Override
    public Object update(Object ob) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return given()
                .spec(spec)
                .delete(PROJECT_ENDPOINT + "/id:" + id);
    }
}
