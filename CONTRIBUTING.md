# Contributing to Avela Playwright Practice

Thank you for your interest in contributing! Please follow these guidelines to ensure smooth collaboration.

## Getting Started

1. Fork the repository
2. Clone your fork: `git clone https://github.com/your-username/repo.git`
3. Create a feature branch: `git checkout -b feature/your-feature-name`
4. Make your changes
5. Push to your fork: `git push origin feature/your-feature-name`
6. Submit a Pull Request

## Development Guidelines

### Code Style

- Use Java naming conventions (camelCase for variables/methods, PascalCase for classes)
- Write clear, descriptive comments
- Keep methods focused and small
- Follow the existing project structure

### Creating New Tests

1. Create feature files in `src/test/resources/features/`
2. Implement step definitions in `src/test/java/com/kkautomation/stepdefinitions/`
3. Create/update page objects in `src/main/java/com/kkautomation/pages/`
4. Add test data to `src/main/java/com/kkautomation/data/TestData.java`

### Commit Messages

Follow conventional commits format:

```
type(scope): subject

body (optional)
footer (optional)
```

Types: `feat`, `fix`, `test`, `docs`, `refactor`, `ci`, `chore`

Example:
```
feat(login): add two-factor authentication tests

Add step definitions for 2FA login flow
- Handle SMS code input
- Verify successful authentication
```

### Testing

- Run tests locally before pushing: `mvn clean test`
- Ensure all tests pass
- Add new tests for new features
- Update existing tests if functionality changes

### Pull Request Guidelines

- Provide clear description of changes
- Reference related issues if any
- Ensure CI/CD pipeline passes
- Request review from team members

## Reporting Issues

When reporting issues, include:

1. **Description**: Clear explanation of the issue
2. **Steps to Reproduce**: How to reproduce the problem
3. **Expected Behavior**: What should happen
4. **Actual Behavior**: What actually happens
5. **Environment**: OS, Java version, Maven version
6. **Screenshots/Logs**: If applicable

## Questions?

Feel free to open an issue with the `question` label or contact the team.

---

**Thank you for contributing to make this framework better!** 🙏
