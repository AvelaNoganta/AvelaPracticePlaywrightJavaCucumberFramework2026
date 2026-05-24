package com.kkautomation.pages;

import com.kkautomation.data.TestData;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * Login page class that contains login-specific operations
 */
public class LoginPage extends BasePage {

    private final Locator firstLoginButton;
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator loginButton;

    public LoginPage(Page page) {
        super(page);

        this.firstLoginButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")
        ).first();

        this.emailInput = page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Email")
        );

        this.passwordInput = page.getByRole(
                AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Password")
        );

        this.loginButton = page.locator("button:has-text('Login')").last();
    }

    public void login(String email, String password) {
        clickElement(firstLoginButton);

        waitForTimeout(1000);

        fillInput(emailInput, email);
        fillInput(passwordInput, password);

        clickElement(loginButton);

        waitForTimeout(2000);
    }

    public void navigateToLoginPage() {
        navigateTo(TestData.BASE_URL);
    }

    public boolean isLoginButtonVisible() {
        return isElementVisible(firstLoginButton);
    }
}