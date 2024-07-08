package com.example.teamcity.ui.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;
@Getter
public class StartUpPage extends Page {
    private SelenideElement createAdminAccountHeader = element(Selectors.byId("header"));
    private SelenideElement header = element(Selectors.byId("header"));
    private SelenideElement acceptLicense = element(Selectors.byId("accept"));
//    private SelenideElement proceedButton = element(Selectors.byId("proceedButton"));
    private SelenideElement proceedButton = element(Selectors.byValue("Proceed"));
    private SelenideElement submitButton = element(Selectors.byType("submit"));

    public StartUpPage open(){
        Selenide.open("/mnt");
        return this;
    }

    public SelenideElement getHeader() {
        return createAdminAccountHeader.shouldBe(Condition.visible, Duration.ofMinutes(15));
    }

    public StartUpPage setupTeamCityServer(){
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        header.shouldBe(Condition.visible, Duration.ofMinutes(10));
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(10));
        acceptLicense.scrollTo();
        acceptLicense.click();
        submitButton.click();
        return this;

    }

}

