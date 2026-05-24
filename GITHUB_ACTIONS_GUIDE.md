# GitHub Actions Workflow Execution Guide

This document explains how the GitHub Actions workflow works and how to monitor and manage test executions.

## 📋 Workflow File

Location: `.github/workflows/test.yml`

## 🔄 Trigger Events

The workflow automatically runs on:

### 1. **Push Events**
```bash
git push origin main          # Triggers workflow
git push origin develop       # Triggers workflow
git push origin feature/name  # Does NOT trigger (unless targeting main/develop)
```

### 2. **Pull Request Events**
- Opening a PR to `main` or `develop` branch
- Pushing updates to a PR

### 3. **Scheduled Runs**
- Daily at 2:00 AM UTC
- Configure with: `cron: '0 2 * * *'`

## 🏃 Workflow Execution Steps

### Step 1: Checkout Code
- Gets the latest code from the branch

### Step 2: Setup Java
- Installs Java 11 and Java 17 (matrix strategy)
- Uses 'adopt' distribution

### Step 3: Display Versions
- Shows Java and Maven versions for debugging

### Step 4: Install Playwright Browsers
```bash
mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" \
  -Dexec.args="install"
```
- Installs Chromium, Firefox, and WebKit browsers

### Step 5: Run Tests
```bash
mvn clean test -DskipITs=false
```
- Cleans previous builds
- Runs all test scenarios
- Continues even if tests fail (`continue-on-error: true`)

### Step 6: Generate Reports
```bash
mvn io.cucumber:cucumber-maven-plugin:report
```
- Creates HTML report from test results

### Step 7: Upload Reports
- Uploads to GitHub artifacts
- Retention: 30 days
- Location: `target/cucumber-reports/`

### Step 8: Upload Screenshots
- Only uploads if tests failed
- Contains failed test screenshots
- Retention: 7 days

### Step 9: Publish Test Results
- Uses GitHub's test result summary
- Displays in PR checks

### Step 10: Comment on PR
- Only runs on pull requests
- Posts test results summary as PR comment

## 📊 Job Matrix Strategy

The workflow runs tests with **multiple Java versions**:

```yaml
strategy:
  matrix:
    java-version: [ 11, 17 ]
    fail-fast: false
```

This means:
- Tests run twice: once with Java 11, once with Java 17
- If one fails, the other still runs (`fail-fast: false`)
- Reports generated for each Java version

## 📈 Monitoring Test Execution

### 1. **In GitHub UI**

**Navigate to:**
```
Repository → Actions → [Workflow Name]
```

**You'll see:**
- ✅ Passed workflows (green)
- ❌ Failed workflows (red)
- ⏳ Running workflows (yellow)
- Execution time
- Trigger information

### 2. **View Detailed Logs**

1. Click on a workflow run
2. Click on "Run Tests" job
3. View detailed step-by-step logs

### 3. **View Test Reports**

**For Each Run:**
1. Click on the workflow run
2. Go to "Artifacts" section
3. Download "cucumber-reports-java-XX"
4. Extract and open `index.html` in browser

### 4. **PR Comments**

When a PR is created:
1. GitHub automatically runs the workflow
2. After completion, it posts a comment with:
   - Test execution status
   - Link to artifacts
   - Summary of results

## 🔍 Interpreting Test Results

### Successful Run
```
✅ Status: Passed
   - All scenarios passed
   - No failed tests
   - Reports generated
```

### Failed Run
```
❌ Status: Failed
   - One or more scenarios failed
   - Check detailed logs for error messages
   - Screenshots available in artifacts
```

### Report Contents

**HTML Report includes:**
- Feature file name
- Scenario name
- Pass/Fail status
- Step definitions
- Execution time
- Error messages (if any)
- Screenshots (if captured)

## 📊 Artifact Management

### Artifacts Uploaded

| Artifact | When | Retention | Contents |
|----------|------|-----------|----------|
| cucumber-reports-java-XX | Always | 30 days | HTML/JSON reports |
| failed-test-screenshots | On failure | 7 days | PNG screenshots |

### Accessing Artifacts

1. Go to Actions tab
2. Click on workflow run
3. Scroll to "Artifacts" section
4. Click to download

### Automatic Cleanup

- Reports: Deleted after 30 days
- Screenshots: Deleted after 7 days
- Manual deletion available on GitHub

## 🛠️ Customizing the Workflow

### Change Trigger Events

```yaml
on:
  push:
    branches: [ main, develop, staging ]  # Add staging branch
  pull_request:
    branches: [ main, develop, staging ]
```

### Add More Java Versions

```yaml
matrix:
  java-version: [ 11, 17, 21 ]  # Add Java 21
```

### Change Schedule

```yaml
schedule:
  - cron: '0 2 * * *'  # Daily at 2 AM UTC
  - cron: '0 9 * * 1'  # Monday at 9 AM UTC
```

### Add Environment Variables

```yaml
env:
  BROWSER: chromium
  HEADLESS: true
```

### Run Only Specific Tests

```yaml
- name: Run Login Tests Only
  run: mvn test -Dcucumber.filter.tags="@login"
```

## 🚀 Manual Workflow Trigger

You can manually trigger a workflow:

1. Go to Actions tab
2. Select the workflow
3. Click "Run workflow"
4. Select branch
5. Click "Run workflow" button

## 📧 Notifications

### GitHub Default Notifications
- You'll get notified on workflow failure
- Configure in GitHub Settings → Notifications

### Custom Notifications (Advanced)
Add to workflow to send notifications:
```yaml
- name: Send Slack Notification
  run: |
    curl -X POST ${{ secrets.SLACK_WEBHOOK }} \
      -d "Tests completed with status: ${{ job.status }}"
```

## 🔐 Using Secrets

For sensitive data (credentials, tokens):

1. Go to Repository → Settings → Secrets
2. Click "New repository secret"
3. Add name and value
4. Use in workflow: `${{ secrets.SECRET_NAME }}`

Example:
```yaml
env:
  TEST_USER_PASSWORD: ${{ secrets.TEST_USER_PASSWORD }}
```

## 📝 Viewing Workflow Logs

### Console Output

All logs are visible in the GitHub Actions UI under each step.

### Debug Logging (Advanced)

Add debug logging:
```yaml
env:
  ACTIONS_STEP_DEBUG: true
```

This provides more verbose output.

## 🆘 Troubleshooting Workflows

### Workflow Not Triggering

**Check:**
1. Workflow file syntax is valid
2. Push is to correct branch (main/develop)
3. Branch protection rules aren't blocking
4. Workflow file is in `.github/workflows/` directory

### Tests Failing in CI but Passing Locally

**Common Causes:**
1. Different browser versions
2. Network/firewall issues
3. Different OS environment
4. Missing browser dependencies
5. Timing issues

**Solutions:**
1. Use explicit waits
2. Add logging
3. Check CI logs carefully
4. Test on Linux locally

### Artifacts Not Uploading

**Check:**
1. Tests completed (passed or failed)
2. Reports generated
3. No disk space issues
4. Artifact path is correct

## 📊 Sample Workflow Output

```
✓ Checkout code
✓ Set up JDK 11
✓ Set up JDK 17
✓ Display Java version
  openjdk version "11.0.15" 2022-04-19
  Maven: 3.8.1
✓ Install Playwright browsers
✓ Run tests
  [INFO] -------
  [INFO] Total tests run: 4
  [INFO] Tests passed: 3
  [INFO] Tests failed: 1
✓ Generate Cucumber report
✓ Upload test reports
✓ Publish test results
  Test Results: 3 passed, 1 failed
✓ Comment on PR
```

## 📚 Additional Resources

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [GitHub Actions Best Practices](https://docs.github.com/en/actions/guides)
- [Workflow Syntax Reference](https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions)
- [Using Cron Expressions](https://docs.github.com/en/actions/using-workflows/events-that-trigger-workflows#schedule)

---

**Last Updated:** 2026-05-24
