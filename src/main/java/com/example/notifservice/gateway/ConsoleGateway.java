package com.example.notifservice.gateway;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ConsoleGateway implements MessageGateway {
    @Override
    public void send(String message, String recipient) {
        System.out.println("[ConsoleGateway] To: " + recipient + " | Message: " + message);
    }
}
