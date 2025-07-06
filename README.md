# Notification Service ISGA

## Description
This is a simple notifications microservice created as a school project for ISGA. It demonstrates basic Spring Boot concepts and REST API development.

## Features
- Email notifications (simulated and real SMTP)
- SMS notifications (simulated)
- Push notifications (simulated)
- REST API with Swagger documentation
- Versioned APIs (v1 and v2)
- Maven build system

## Technologies Used
- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Mail
- Swagger/OpenAPI 3
- Thymeleaf
- Maven

## How to Run
1. Clone the repository
2. Navigate to the project directory
3. Run: `mvn spring-boot:run`
4. Access the application at: `http://localhost:8081`
5. View Swagger UI at: `http://localhost:8081/swagger-ui.html`

## API Endpoints

### V1 API (Deprecated - Simulated)
- `POST /api/v1/notifications/email`
- `POST /api/v1/notifications/sms`
- `POST /api/v1/notifications/push`

### V2 API (Enhanced)
- `POST /api/v2/notifications/email`
- `POST /api/v2/notifications/sms`
- `POST /api/v2/notifications/push`

## Configuration
To enable real email sending, configure SMTP settings in `application.properties`:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Note
This is a student project for educational purposes. All SMS and push notifications are simulated.
