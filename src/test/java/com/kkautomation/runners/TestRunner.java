package com.kkautomation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Cucumber test runner configuration
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.kkautomation.stepdefinitions",
                "com.kkautomation.hooks"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/index.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true,
        dryRun = false
)
public class TestRunner {
}