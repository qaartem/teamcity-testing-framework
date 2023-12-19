package com.example.teamcity.ui;

import com.codeborne.selenide.selector.ByAttribute;
import com.codeborne.selenide.selector.ByText;

public class Selectors {
    public static ByText byText(String value) {
        return new ByText(value);
    }
    public static ByAttribute byId(String value){
        return new ByAttribute("id", value);
    }
    public static ByAttribute byType(String value) {
        return new ByAttribute("type", value);
    }

    public static ByAttribute byDataTest(String value) {
        return new ByAttribute("data-test", value);
    }

    public static ByAttribute byClass(String value) {
        return new ByAttribute("class", value);
    }
}
