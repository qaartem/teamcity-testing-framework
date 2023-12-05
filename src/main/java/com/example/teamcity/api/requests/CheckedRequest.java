package com.example.teamcity.api.request;

import com.example.teamcity.api.request.checked.CheckedBuildConfig;
import com.example.teamcity.api.request.checked.CheckedProject;
import com.example.teamcity.api.request.checked.CheckedUser;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class CheckedRequest {

    private CheckedUser userRequest;
    private CheckedProject projectRequest;
    private CheckedBuildConfig buildConfigRequest;

    public CheckedRequest(RequestSpecification spec) {
        this.userRequest = new CheckedUser(spec);
        this.buildConfigRequest = new CheckedBuildConfig(spec);
        this.projectRequest = new CheckedProject(spec);
    }
}
