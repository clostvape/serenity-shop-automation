package com.intercorp.reto.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.intercorp.reto.stepdefinitions",
        plugin = {"pretty"},
        tags = "not @ignore"
)
public class ShopCucumberTest {
}
