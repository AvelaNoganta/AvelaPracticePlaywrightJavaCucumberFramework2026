package com.kkautomation.stepdefinitions;

import com.kkautomation.hooks.Hooks;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

/**
 * Step definitions for common operations
 */
public class CommonSteps {
    private Page page;

    public CommonSteps() {
        this.page = Hooks.getPage();
    }

    @Given("user navigates to the application URL")
    public void userNavigatesToTheApplicationURL() {
        page.navigate("https://ndosisimplifiedautomation.vercel.app/");
        page.waitForTimeout(2000);
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        String actualTitle = page.title();
        Assert.assertEquals("Page title mismatch", expectedTitle, actualTitle);
        System.out.println("Page title verified: " + actualTitle);
    }

    @Then("user waits for {int} seconds")
    public void userWaitsForSeconds(int seconds) {
        page.waitForTimeout(seconds * 1000);
    }
}
