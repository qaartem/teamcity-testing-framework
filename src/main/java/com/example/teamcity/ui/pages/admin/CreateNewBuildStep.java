package com.example.teamcity.ui.pages.admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.pages.Page;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;

public class CreateNewBuildStep extends Page {
    private SelenideElement submitButton = element(Selectors.byName("submitButton"));
    private SelenideElement buildStepsBtn = element(Selectors.byId("runType_Tab"));
    private SelenideElement addBuildStepBtn = element(Selectors.byText("Add build step"));
    private SelenideElement commandLineBtn = element(Selectors.byText("Command Line"));
    private SelenideElement buildStepNameInput = element(Selectors.byId("buildStepName"));
    private SelenideElement customScriptInput = element(Selectors.byId("command.executable"));
//    private SelenideElement runBuildButton = element(Selectors.byId("buildStepName"));
    private SelenideElement runBuildButton = element(Selectors.byName("-ufd-teamcity-ui-prop:use.custom.script"));
//    private SelenideElement runExecutableWithParameters = $(byText("Executable with parameters"));
    private SelenideElement runExecutableWithParameters = element(Selectors.byDataTitle("Executable with parameters"));

    public CreateNewBuildStep navigateToBuildStepsSection() {
        buildStepsBtn.click();
        return this;
    }

    public CreateNewBuildStep selectAddBuildStep() {
        addBuildStepBtn.click();
        return this;
    }

    public CreateNewBuildStep selectCommandLineBtn() {
        commandLineBtn.click();
        return this;
    }
    public CreateNewBuildStep setupBuildStepWithoutScript(String projectName) {
        buildStepNameInput.clear();
        buildStepNameInput.sendKeys(projectName);
        submit();
        return this;
    }

    public void setupBuildStep(String projectName, String customScript) {
        waitBuildStepNameInput();
        buildStepNameInput.clear();
        buildStepNameInput.sendKeys(projectName);
        runBuildButton.click();
//        customScriptInput.clear();
        runExecutableWithParameters.click();
        customScriptInput.sendKeys(customScript);
//        submitButton.click();
        submitConfigBuildStep();
    }

    public void waitBuildStepNameInput() {
        buildStepNameInput.shouldBe(Condition.visible, Duration.ofMinutes(15));
    }

    public CreateNewBuildStep runBuild() {
        runBuildButton.click();
        return this;
    }
}
