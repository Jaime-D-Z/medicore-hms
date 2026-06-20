# Contributing to MediCore HMS

Thank you for considering contributing to the Hospital Management System! This document outlines the guidelines for contributing.

## Code of Conduct

By participating, you are expected to uphold our [Code of Conduct](CODE_OF_CONDUCT.md).

## How Can I Contribute?

### Reporting Bugs

Before submitting a bug report:

- Check the [issues](https://github.com/Jaime-D-Z/medicore-hms/issues) to see if it has already been reported
- Provide a clear description of the issue, steps to reproduce, and expected behavior
- Include screenshots if relevant

### Suggesting Enhancements

- Open an issue describing the enhancement, why it's useful, and how it should work
- Label it as `enhancement`

### Pull Requests

1. Fork the repository and create your branch from `main`
2. Follow the existing code style and conventions
3. Make sure your code compiles: `mvn clean install`
4. Update the README if needed
5. Submit the pull request with a clear description of the changes

## Development Setup

```bash
# Clone your fork
git clone https://github.com/your-username/medicore-hms.git

# Create the database
mysql -u root -e "CREATE DATABASE hms1"

# Build and run
mvn clean install
mvn spring-boot:run
```

The app will be available at `http://localhost:8080`.

## Project Structure

```
src/main/java/com/hms/
├── config/       # Security, MVC configuration
├── controller/   # MVC controllers
├── entity/       # JPA entities
├── repository/   # Spring Data repositories
└── service/      # Business logic
```

## Database

- The app uses `spring.jpa.hibernate.ddl-auto=update` — tables are created automatically
- Optional seed data is in `seed-data.sql`

Thank you for contributing!
