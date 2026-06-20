# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | ✅ Fully supported |

## Reporting a Vulnerability

We take the security of MediCore HMS seriously. If you discover a security vulnerability, please follow these steps:

1. **Do not** disclose the vulnerability publicly (e.g., by opening a public issue)
2. Send a description of the vulnerability to the project maintainer
3. Include steps to reproduce and potential impact

You can expect:

- Acknowledgment of your report within 48 hours
- A timeline for a fix and disclosure
- Credit for discovering the issue (if desired)

## Security Measures

This application implements the following security measures:

- **Authentication**: Spring Security with BCrypt password hashing
- **Authorization**: Role-based access control (ADMIN, DOCTOR, NURSE, RECEPTIONIST)
- **CSRF Protection**: Enabled for all state-changing requests
- **Session Management**: Secure session handling

## Best Practices for Deployment

- Change default credentials immediately after first login
- Use environment variables for database credentials
- Enable HTTPS in production
- Keep dependencies updated
