package com.kkautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Base page class that contains common web page operations
 */
public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    /**
     * Navigate to a URL
     *
     * @param url the URL to navigate to
     */
    public void navigateTo(String url) {
        page.navigate(url);
    }

    /**
     * Click on an element
     *
     * @param locator the locator of the element to click
     */
    public void clickElement(Locator locator) {
        locator.click();
    }

    /**
     * Fill input field with text
     *
     * @param locator the locator of the input field
     * @param value   the value to fill
     */
    public void fillInput(Locator locator, String value) {
        locator.fill(value);
    }

    /**
     * Verify if an element is visible
     *
     * @param locator the locator of the element
     * @return true if element is visible, false otherwise
     */
    public boolean isElementVisible(Locator locator) {
        try {
            return locator.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for a specific time in milliseconds
     *
     * @param millis the time to wait
     */
    public void waitForTimeout(int millis) {
        page.waitForTimeout(millis);
    }

    /**
     * Get page title
     *
     * @return the page title
     */
    public String getPageTitle() {
        return page.title();
    }

    /**
     * Get current URL
     *
     * @return the current URL
     */
    public String getCurrentUrl() {
        return page.url();
    }
}
