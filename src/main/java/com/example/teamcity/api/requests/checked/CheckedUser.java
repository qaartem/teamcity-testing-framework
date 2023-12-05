package com.example.teamcity.api.request.checked;

import com.example.teamcity.api.models.User;
import com.example.teamcity.api.request.CrudInterface;
import com.example.teamcity.api.request.Request;
import com.example.teamcity.api.request.unchecked.UncheckedUser;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class CheckedUser extends Request implements CrudInterface {


    public CheckedUser(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public User create(Object obj) {
        return new UncheckedUser(spec)
                .create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(User.class);
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
    public String delete(String id) {
        return new UncheckedUser(spec)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().asString();
    }
}
