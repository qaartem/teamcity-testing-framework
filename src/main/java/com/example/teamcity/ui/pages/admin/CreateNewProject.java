package com.example.teamcity.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.pages.Page;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.sleep;

@Getter
public class CreateNewProject extends Page {
    private SelenideElement urlInput = element(Selectors.byId("url"));
    private SelenideElement urlError = element(Selectors.byId("error_url"));
    private SelenideElement projectNameInput = element(Selectors.byId("projectName"));
    private SelenideElement projectNameErrorInput = element(Selectors.byId("error_projectName"));
    private SelenideElement buildTypeNameInput = element(Selectors.byId("buildTypeName"));
    public CreateNewProject open(String parentProjectId){
        Selenide.open("/admin/createObjectMenu.html?projectId=" +  parentProjectId + "&showMode=createProjectMenu&cameFromUrl=http%3A%2F%2Flocalhost%3A8111%2Ffavorite%2Fprojects#createFromUrl");
        waitUntilPageIsLoaded();
        return this;
    }
    public CreateNewProject createProjectByUrl(String url) {
        urlInput.sendKeys(url);
        submit();
        return this;
    }

    public CreateNewProject createProjectWithoutUrl() {
        submit();
        return this;
    }

    public void setupProject(String projectName, String buildTypeName) {
        sleep(2000);
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        submit();
    }

    public CreateNewProject setupProjectWithoutName(String buildTypeName) {
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        submit();
        return this;
    }

    public CreateNewProject clearURLInput(){
        urlInput.clear();
        return this;
    }

    public CreateNewProject clearNameInput(){
        projectNameInput.clear();
        return this;
    }

}
