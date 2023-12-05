package com.example.teamcity.api.request;

import com.example.teamcity.api.request.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.request.unchecked.UncheckedProject;
import com.example.teamcity.api.request.unchecked.UncheckedUser;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class UncheckedRequest {

    private UncheckedUser userRequest;
    private UncheckedProject projectRequest;
    private UncheckedBuildConfig buildConfigRequest;

    public UncheckedRequest(RequestSpecification spec) {
        this.userRequest = new UncheckedUser(spec);
        this.buildConfigRequest = new UncheckedBuildConfig(spec);
        this.projectRequest = new UncheckedProject(spec);
    }
}
