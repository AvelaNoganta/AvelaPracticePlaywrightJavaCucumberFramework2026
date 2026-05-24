# Avela Playwright Practice - Java & Cucumber

A comprehensive test automation framework built with **Playwright**, **Java**, **Cucumber**, and **Maven**. This framework demonstrates BDD (Behavior-Driven Development) testing practices with a well-structured page object model.

## 📋 Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Features](#features)
- [Page Objects](#page-objects)
- [Test Data](#test-data)
- [CI/CD Integration](#cicd-integration)
- [Troubleshooting](#troubleshooting)

## 🎯 Overview

This project provides a robust test automation framework for testing web applications using:
- **Playwright**: Cross-browser automation
- **Cucumber**: BDD test scenarios written in Gherkin language
- **Java**: Strong typing and object-oriented design
- **Maven**: Dependency management and build automation
- **JUnit**: Test execution and assertions

### Technology Stack

| Component | Version |
|-----------|---------|
| Java | 11+ |
| Maven | 3.6+ |
| Playwright | 1.45.0 |
| Cucumber | 7.14.0 |
| JUnit | 4.13.2 |

## 📦 Prerequisites

Before getting started, ensure you have the following installed:

1. **Java Development Kit (JDK)**
   ```bash
   java -version  # Should be 11 or higher
   ```
   - Download from: https://www.oracle.com/java/technologies/downloads/

2. **Maven**
   ```bash
   mvn -version  # Should be 3.6 or higher
   ```
   - Download from: https://maven.apache.org/download.cgi

3. **Git** (optional, for cloning the repository)
   ```bash
   git --version
   ```

## 📁 Project Structure

```
AvelaPlaywrightPracticeJavaCucumber/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── kkautomation/
│   │               ├── pages/
│   │               │   ├── BasePage.java          # Base class for all pages
│   │               │   └── LoginPage.java         # Login page object
│   │               ├── data/
│   │               │   └── TestData.java          # Test constants and credentials
│   │               └── hooks/
│   │                   └── Hooks.java             # Cucumber setup/teardown
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── kkautomation/
│   │   │           ├── stepdefinitions/
│   │   │           │   ├── LoginSteps.java        # Login step definitions
│   │   │           │   └── CommonSteps.java       # Common step definitions
│   │   │           └── runners/
│   │   │               └── TestRunner.java        # Cucumber test runner
│   │   └── resources/
│   │       └── features/
│   │           ├── Login.feature                  # Login feature file
│   │           └── Basics.feature                 # Basic tests feature file
├── target/                                        # Build output
│   ├── cucumber-reports/                          # Test reports
│   ├── screenshots/                               # Failed test screenshots
│   └── test-classes/                              # Compiled test classes
├── pom.xml                                        # Maven configuration
├── README.md                                      # This file
└── .github/
    └── workflows/
        └── test.yml                               # GitHub Actions workflow
```

## 🚀 Installation

### Step 1: Clone or Download the Project

```bash
# If using Git
git clone <repository-url>
cd AvelaPlaywrightPracticeJavaCucumber
```

### Step 2: Install Dependencies

Maven will automatically download all dependencies from pom.xml:

```bash
mvn clean install
```

This command will:
- Clean previous builds
- Download all dependencies
- Compile the project
- Run any setup tasks

### Step 3: Verify Installation

Verify that everything is installed correctly:

```bash
mvn --version
java -version
```

## ⚙️ Configuration

### Browser Configuration

The framework currently uses **Chromium** by default. You can modify browser settings in [src/main/java/com/kkautomation/hooks/Hooks.java](src/main/java/com/kkautomation/hooks/Hooks.java):

```java
// Change headless mode
browser = playwright.chromium().launch(
    new Browser.LaunchOptions().setHeadless(true)  // Set to true for headless
);
```

### Test Data

Update test credentials in [src/main/java/com/kkautomation/data/TestData.java](src/main/java/com/kkautomation/data/TestData.java):

```java
public static final String TEST_USER_EMAIL = "your-email@gmail.com";
public static final String TEST_USER_PASSWORD = "your-password";
public static final String BASE_URL = "https://your-app-url.com/";
```

### Timeout Configuration

Adjust timeouts in TestData.java:

```java
public static final int IMPLICIT_WAIT = 10000;  // 10 seconds
public static final int EXPLICIT_WAIT = 15000;  // 15 seconds
```

## 🧪 Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Feature File

```bash
mvn test -Dcucumber.filter.tags="@login"
```

### Run with Specific Tags

```bash
# Run tests with @smoke tag
mvn test -Dcucumber.filter.tags="@smoke"

# Run tests with @regression tag
mvn test -Dcucumber.filter.tags="@regression"

# Run tests excluding @skip tag
mvn test -Dcucumber.filter.tags="not @skip"
```

### Run in Headless Mode

Add system property to run in headless mode:

```bash
mvn test -DargLine="-Dheadless=true"
```

### Run with Specific Browser

Currently configured for Chromium. To use Firefox or WebKit, modify Hooks.java:

```java
// For Firefox
browser = playwright.firefox().launch(new Browser.LaunchOptions().setHeadless(false));

// For WebKit
browser = playwright.webkit().launch(new Browser.LaunchOptions().setHeadless(false));
```

## 📊 Test Reports

After running tests, reports are generated in `target/cucumber-reports/`:

### View HTML Report

1. Open `target/cucumber-reports/index.html` in a web browser
2. View detailed test results, step definitions, and execution times

### Report Contents

- ✅ Passed scenarios
- ❌ Failed scenarios with error messages
- ⏱️ Execution time per scenario
- 📸 Screenshots of failed tests (when available)
- 📋 Step definitions and step duration

### Generate Reports Manually

```bash
mvn clean test
mvn io.cucumber:cucumber-maven-plugin:report
```

## ✨ Features

### 1. **Page Object Model (POM)**
- Organized page classes in `src/main/java/com/kkautomation/pages/`
- Reusable element locators
- Centralized element interactions

### 2. **Cucumber BDD**
- Feature files written in Gherkin language
- Step definitions mapped to feature file steps
- Scenario outline support for data-driven testing

### 3. **Hooks & Fixtures**
- Automatic browser initialization before each test
- Automatic cleanup after each test
- Screenshot capture on test failure

### 4. **Test Data Management**
- Centralized test data in TestData.java
- Easy credential management
- Constants for URLs, timeouts, and expected values

### 5. **Multiple Browsers Support**
- Chromium (default)
- Firefox
- WebKit

### 6. **Cross-Platform**
- Runs on Windows, macOS, and Linux
- CI/CD ready with GitHub Actions

## 🏗️ Page Objects

### BasePage

Base class containing common web interactions:

```java
public class BasePage {
    public void navigateTo(String url)
    public void clickElement(Locator locator)
    public void fillInput(Locator locator, String value)
    public boolean isElementVisible(Locator locator)
    public void waitForTimeout(int millis)
    public String getPageTitle()
    public String getCurrentUrl()
}
```

### LoginPage

Extends BasePage with login-specific operations:

```java
public class LoginPage extends BasePage {
    public void login(String email, String password)
    public void navigateToLoginPage()
    public boolean isLoginButtonVisible()
}
```

### Adding New Page Objects

1. Create a new class in `src/main/java/com/kkautomation/pages/`
2. Extend BasePage
3. Define page-specific locators and methods

Example:

```java
public class DashboardPage extends BasePage {
    private final Locator welcomeMessage;
    
    public DashboardPage(Page page) {
        super(page);
        this.welcomeMessage = page.getByText("Welcome");
    }
    
    public boolean isWelcomeMessageVisible() {
        return isElementVisible(welcomeMessage);
    }
}
```

## 📝 Test Data

Test credentials and URLs are managed in [TestData.java](src/main/java/com/kkautomation/data/TestData.java):

```java
// Valid test users
public static final String TEST_USER_EMAIL = "aveetestuser@gmail.com";
public static final String TEST_USER_PASSWORD = "Testing123456!";

public static final String ADMIN_EMAIL = "admin@gmail.com";
public static final String ADMIN_PASSWORD = "@12345678!";

// Application URL
public static final String BASE_URL = "https://ndosisimplifiedautomation.vercel.app/";
```

**Note:** Never commit actual credentials to version control. Use environment variables or secrets management for production.

## 🔄 CI/CD Integration

### GitHub Actions

This project includes a GitHub Actions workflow file (`.github/workflows/test.yml`) that automatically runs tests on every push and pull request.

#### Workflow Features

- ✅ Runs on Linux
- ✅ Uses Java 11
- ✅ Installs dependencies
- ✅ Runs all tests
- ✅ Generates test reports
- ✅ Uploads reports as artifacts

#### Setting Up GitHub Actions

1. Push the code to a GitHub repository
2. Navigate to **Actions** tab in your repository
3. The workflow will automatically trigger on push/PR
4. View logs and reports in the Actions dashboard

#### Customizing the Workflow

Edit `.github/workflows/test.yml` to:
- Change trigger events (push, pull_request, schedule)
- Add more jobs (parallel execution)
- Deploy artifacts
- Send notifications

### Running Tests Locally Like CI

```bash
mvn clean install
mvn clean test
mvn io.cucumber:cucumber-maven-plugin:report
```

## 🆕 Creating New Tests

### Step 1: Create a Feature File

Create a new `.feature` file in `src/test/resources/features/`:

```gherkin
Feature: User Profile Management

  Scenario: User can update their profile
    Given user is logged in
    When user updates their name to "John Doe"
    Then user profile should be updated successfully
```

### Step 2: Create Step Definitions

Create step definitions in `src/test/java/com/kkautomation/stepdefinitions/`:

```java
public class ProfileSteps {
    private Page page;
    
    public ProfileSteps() {
        this.page = Hooks.getPage();
    }
    
    @Given("user is logged in")
    public void userIsLoggedIn() {
        // Implementation
    }
    
    @When("user updates their name to {string}")
    public void userUpdatesTheirName(String name) {
        // Implementation
    }
}
```

### Step 3: Run the Tests

```bash
mvn test
```

## 🔍 Locator Strategies

Playwright supports multiple locator strategies:

```java
// Role-based (recommended)
page.getByRole("button", new Page.GetByRoleOptions().setName("Submit"))

// Text content
page.getByText("Welcome")

// Placeholder text
page.getByPlaceholder("Enter your name")

// Label text
page.getByLabel("Email")

// CSS selectors
page.locator("css=button.submit")

// XPath
page.locator("xpath=//button[@id='submit']")
```

## ❌ Troubleshooting

### Issue: Tests fail with "Element not found"

**Solution:**
- Increase wait timeout in hooks or use explicit waits
- Verify element locators are correct
- Check if element is visible/enabled before interaction

### Issue: Playwright browser not launching

**Solution:**
```bash
# Install browser dependencies
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
```

### Issue: Maven dependencies not downloading

**Solution:**
```bash
mvn clean install -U  # -U forces update of dependencies
```

### Issue: Tests pass locally but fail in CI

**Common causes:**
- Environment differences (Linux vs macOS)
- Network/firewall issues
- Missing browser dependencies
- Timing issues

**Solutions:**
- Use explicit waits
- Check CI logs for detailed errors
- Use headless mode in CI
- Verify all dependencies are in pom.xml

### Enable Debug Logging

Add logging to see detailed execution flow:

```java
System.out.println("Debug message: " + variable);
```

Or use a logging framework:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
logger.info("User logging in with email: {}", email);
```

## 📚 Resources

- [Playwright Java Documentation](https://playwright.dev/java/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [JUnit Documentation](https://junit.org/junit4/)
- [Gherkin Syntax](https://cucumber.io/docs/gherkin/reference/)

## 🤝 Contributing

1. Create a new branch: `git checkout -b feature/your-feature`
2. Make changes and commit: `git commit -m 'Add new feature'`
3. Push to branch: `git push origin feature/your-feature`
4. Submit a pull request

## 📄 License

This project is licensed under the ISC License.

## ✉️ Support

For issues, questions, or suggestions:
1. Check the [Troubleshooting](#troubleshooting) section
2. Review test logs in `target/` directory
3. Create an issue in the repository
4. Contact the development team

---

**Last Updated:** 2026-05-24
**Framework Version:** 1.0.0
**Maintained By:** KK Automation Team
