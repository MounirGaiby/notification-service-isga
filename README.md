# Notifications Service

A Spring Boot microservice for handling email, SMS, and push notifications.

## Features

- Email notifications with SMTP support
- SMS notifications
- Push notifications
- RESTful API with Swagger documentation
- CORS configuration
- Comprehensive error handling

## Configuration

### Local Configuration Setup

This application uses Spring Boot's configuration profile system to manage different environments. For local development and production deployments, you should use local configuration files instead of modifying the main `application.properties`.

#### Setting up Local Configuration

1. **Create your local configuration file:**
   ```bash
   cp src/main/resources/application-local.properties.template src/main/resources/application-local.properties
   ```

2. **Configure your local settings in `application-local.properties`:**

   ```properties
   # Email Configuration - Use your actual email server settings
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-app-password
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   spring.mail.noreply.address=noreply@yourdomain.com
   
   # SMS Configuration (if using SMS service)
   sms.provider.api.key=your-sms-api-key
   sms.provider.api.url=https://api.your-sms-provider.com
   
   # Push Notification Configuration
   push.fcm.server.key=your-fcm-server-key
   push.fcm.project.id=your-firebase-project-id
   
   # Database Configuration (if needed)
   spring.datasource.url=jdbc:postgresql://localhost:5432/notifications_db
   spring.datasource.username=your-db-username
   spring.datasource.password=your-db-password
   
   # Custom application settings
   server.port=8081
   logging.level.com.mounirgaiby.notificationsservice=DEBUG
   ```

3. **Important Notes:**
   - The `application-local.properties` file is automatically imported by the main configuration
   - **Never commit your local configuration file to version control** - it contains sensitive information
   - The `.template` file shows the structure but should not contain real credentials
   - Local properties override the default values in `application.properties`

### Environment-Specific Configuration

You can also create environment-specific configuration files:

- `application-dev.properties` for development
- `application-prod.properties` for production
- `application-test.properties` for testing

Activate a specific profile by setting:
```bash
spring.profiles.active=dev
```

## API Endpoints

The service provides RESTful endpoints for sending notifications:

### V1 API
- `POST /api/v1/notifications/email` - Send email notification
- `POST /api/v1/notifications/sms` - Send SMS notification
- `POST /api/v1/notifications/push` - Send push notification

### V2 API
- `POST /api/v2/notifications/email` - Send email notification (enhanced)
- `POST /api/v2/notifications/sms` - Send SMS notification (enhanced)
- `POST /api/v2/notifications/push` - Send push notification (enhanced)

## API Documentation

Once the application is running, you can access the Swagger UI at:
- http://localhost:8081/swagger-ui.html
- API docs: http://localhost:8081/api-docs

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd notifications-service
   ```

2. **Set up your local configuration** (see Configuration section above)

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR file:
   ```bash
   java -jar target/notifications-service-0.0.1-SNAPSHOT.jar
   ```

The application will start on port 8081 (or the port specified in your local configuration).

## Security Considerations

- Always use `.local` configuration files for sensitive information like API keys, passwords, and email credentials
- Never commit credentials to version control
- Use environment variables in production deployments
- Consider using Spring Boot's encrypted property values for additional security

## Development

### Project Structure
```
src/
├── main/
│   ├── java/com/mounirgaiby/notificationsservice/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── dto/            # Data transfer objects
│   │   └── service/        # Business logic services
│   └── resources/
│       ├── application.properties          # Main configuration
│       ├── application-local.properties   # Local overrides (not in git)
│       └── application-local.properties.template # Template for local config
└── test/                   # Test classes
```

### Adding New Notification Types

To add a new notification type:

1. Create a new service class in the `service` package
2. Add corresponding DTOs in the `dto` package
3. Create controller endpoints in the `controller` package
4. Update the main `NotificationService` if needed

## Troubleshooting

### Common Issues

1. **Email not sending**: Check your SMTP configuration in `application-local.properties`
2. **Port already in use**: Change the port in your local configuration
3. **Missing local configuration**: Ensure you've created and configured your local properties file

### Logs

Enable debug logging by adding to your local configuration:
```properties
logging.level.com.mounirgaiby.notificationsservice=DEBUG
logging.level.org.springframework.mail=DEBUG
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
