package com.brw;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Owner on 28/06/2015.
 */


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {"pretty", "html:target/cucumber-reports/html", "json:target/cucumber.json"},
        glue = "com.brw.stepdefinitions",
        tags = {}
)

public class RunTest {
}
