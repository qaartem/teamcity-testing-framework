package com.example.teamcity.api.request.unchecked;

import com.example.teamcity.api.request.CrudInterface;
import com.example.teamcity.api.request.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UncheckedUser extends Request implements CrudInterface {
    private final static String USER_ENDPOINT = "/app/rest/users";

    public UncheckedUser(RequestSpecification spec) {
        super(spec);
    }


    @Override
    public Response create(Object obj) {
        return given()
                .spec(spec)
                .body(obj)
                .post(USER_ENDPOINT);
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public Object update(Object ob) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return given()
                .spec(spec)
                .delete(USER_ENDPOINT + "/username:" + id);
    }
}
