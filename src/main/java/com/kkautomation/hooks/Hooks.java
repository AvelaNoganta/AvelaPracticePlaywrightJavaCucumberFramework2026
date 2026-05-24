package com.kkautomation.hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import com.microsoft.playwright.BrowserType;

/**
 * Cucumber hooks for setup and teardown
 */
public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    public static Page page;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        browserContext = browser.newContext();
        page = browserContext.newPage();

        System.out.println("Browser and page initialized");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Finishing scenario: " + scenario.getName());
        
        if (scenario.isFailed()) {
            System.out.println("Scenario failed, taking screenshot");
            // Optionally take screenshot on failure
            String screenshotPath = "target/screenshots/" + scenario.getName() + ".png";
            page.screenshot(new Page.ScreenshotOptions().setPath(new java.io.File(screenshotPath).toPath()));
        }
        
        // Clean up resources
        if (page != null) {
            page.close();
        }
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        
        System.out.println("Browser and page closed");
    }

    public static Page getPage() {
        return page;
    }
}
