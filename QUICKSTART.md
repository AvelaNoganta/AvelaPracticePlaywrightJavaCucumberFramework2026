# Quick Start Guide

Get up and running with Avela Playwright Practice in 5 minutes!

## ⚡ Quick Setup (Linux/macOS)

```bash
# 1. Navigate to project directory
cd AvelaPlaywrightPracticeJavaCucumber

# 2. Install dependencies
mvn clean install

# 3. Run tests
mvn test

# 4. View reports
open target/cucumber-reports/index.html
```

## ⚡ Quick Setup (Windows)

```bash
# 1. Navigate to project directory
cd AvelaPlaywrightPracticeJavaCucumber

# 2. Install dependencies
mvn clean install

# 3. Run tests
mvn test

# 4. View reports
start target\cucumber-reports\index.html
```

## 📋 Prerequisites Checklist

- [ ] Java 11+ installed (`java -version`)
- [ ] Maven installed (`mvn -version`)
- [ ] Git installed (optional, `git --version`)

## 🚀 Using Test Runner Scripts

### Linux/macOS

```bash
# Make script executable
chmod +x run-tests.sh

# Run all tests
./run-tests.sh all

# Run smoke tests
./run-tests.sh smoke

# Run login tests
./run-tests.sh login

# Run in headless mode
./run-tests.sh headless

# Generate report
./run-tests.sh report

# View all options
./run-tests.sh help
```

### Windows

```bash
# Run all tests
run-tests.bat all

# Run smoke tests
run-tests.bat smoke

# Run login tests
run-tests.bat login

# Run in headless mode
run-tests.bat headless

# Generate report
run-tests.bat report

# View all options
run-tests.bat help
```

## 📊 Common Commands

| Command | Description |
|---------|-------------|
| `mvn clean install` | Install all dependencies |
| `mvn test` | Run all tests |
| `mvn clean test` | Clean and run tests |
| `mvn test -Dcucumber.filter.tags="@login"` | Run specific feature |
| `mvn io.cucumber:cucumber-maven-plugin:report` | Generate HTML report |

## 🔍 First Test Run

1. Run: `mvn clean test`
2. Open: `target/cucumber-reports/index.html`
3. View the test execution results with details and screenshots

## 📚 Next Steps

- [ ] Read [README.md](README.md) for complete documentation
- [ ] Check [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines
- [ ] Review test examples in `src/test/resources/features/`
- [ ] Study page objects in `src/main/java/com/kkautomation/pages/`

## 🆘 Troubleshooting

### Maven not found?
```bash
# Install Maven from https://maven.apache.org/download.cgi
# Add to system PATH
```

### Java not found?
```bash
# Install JDK from https://www.oracle.com/java/technologies/downloads/
# Add to system PATH
```

### Browser not launching?
```bash
# Install Playwright browsers
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="install"
```

### Tests timing out?
- Increase timeouts in `TestData.java`
- Use headless mode: `mvn test`
- Check internet connection

## 📖 Documentation

- [README.md](README.md) - Full documentation
- [CONTRIBUTING.md](CONTRIBUTING.md) - Contributing guidelines
- [Playwright Documentation](https://playwright.dev/java/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)

---

Happy testing! 🎉
