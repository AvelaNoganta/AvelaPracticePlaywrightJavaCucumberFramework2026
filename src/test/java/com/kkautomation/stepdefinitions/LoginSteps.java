package com.kkautomation.stepdefinitions;

import com.kkautomation.data.TestData;
import com.kkautomation.pages.LoginPage;
import com.kkautomation.hooks.Hooks;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

/**
 * Step definitions for login scenarios
 */
public class LoginSteps {
    private Page page;
    private LoginPage loginPage;

    public LoginSteps() {
        this.page = Hooks.getPage();
        this.loginPage = new LoginPage(page);
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        loginPage.navigateToLoginPage();
        Assert.assertTrue("Login button should be visible", loginPage.isLoginButtonVisible());
    }

    @When("user enters email {string} and password {string}")
    public void userEntersEmailAndPassword(String email, String password) {
        loginPage.login(email, password);
    }

    @When("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        loginPage.login(TestData.TEST_USER_EMAIL, TestData.TEST_USER_PASSWORD);
    }

    @When("user logs in with admin credentials")
    public void userLogsInWithAdminCredentials() {
        loginPage.login(TestData.ADMIN_EMAIL, TestData.ADMIN_PASSWORD);
    }

    @Then("user should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        // Wait for navigation to complete
        page.waitForTimeout(3000);
        
        // Verify page has changed (you can add more specific assertions based on your app)
        String currentUrl = page.url();
        System.out.println("Current URL after login: " + currentUrl);
        
        // Assert that we're not on the login page anymore
        Assert.assertNotEquals("Should not be on login page", 
            "https://ndosisimplifiedautomation.vercel.app/", currentUrl);
    }

    @Then("page title should be {string}")
    public void pageTitleShouldBe(String expectedTitle) {
        String actualTitle = page.title();
        Assert.assertEquals("Page title mismatch", expectedTitle, actualTitle);
    }
}
