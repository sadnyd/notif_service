Notification Service: a Spring Boot app that sends transactional customer alerts (OTP, withdrawal alerts, monthly statements) using profile-based gateways.
SMS and timeout

- To enable the SMS implementation, start the app with the `sms` profile active (can be combined with other profiles):

```bash
# enable sms-only gateway
SPRING_PROFILES_ACTIVE=sms java -jar target/notif-service-0.0.1-SNAPSHOT.jar

```
```bash
# or combine with dev to run dev components plus SMS
SPRING_PROFILES_ACTIVE=dev,sms java -jar target/notif-service-0.0.1-SNAPSHOT.jar
```

- `SmsGateway` reads `bank.config.api-timeout` and will cancel the simulated external call if it exceeds the configured timeout (milliseconds). Replace the simulation with a real HTTP client when integrating with an SMS provider.
# Notification Service (notif_service)

This project is a minimal Spring Boot service demonstrating:

- Dependency Injection via constructor injection and `@Autowired`.
- Profile-based beans (`dev` uses `ConsoleGateway`; `prod` uses `SmtpGateway`).
- Configuration properties using `@ConfigurationProperties(prefix="bank.config")`.

How to run

1. Build:

```bash
mvn -f notif_service/pom.xml package
```

2. Run (dev is default):

```bash
java -jar notif_service/target/notif-service-0.0.1-SNAPSHOT.jar
```

3. To run in prod (make sure SMTP env vars are set):

```bash
set SPRING_PROFILES_ACTIVE=prod
set SMTP_USERNAME=your_user
set SMTP_PASSWORD=your_pass
java -jar notif_service/target/notif-service-0.0.1-SNAPSHOT.jar
```

Notes

- In `dev` profile, notifications are printed to console by `ConsoleGateway`.
- In `prod` profile, `SmtpGateway` uses `JavaMailSender` to send real emails. Configure `spring.mail.*` accordingly.
- `BankConfigProperties` holds `bank.config.*` values such as `daily-withdrawal-limit`, `support-email`, `region`, and `api-timeout`.
# notif_service