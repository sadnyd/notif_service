package com.example.notifservice.service;

import com.example.notifservice.config.BankConfigProperties;
import com.example.notifservice.gateway.MessageGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationService {

    private final MessageGateway gateway;
    private final BankConfigProperties config;

    @Autowired
    public NotificationService(MessageGateway gateway, BankConfigProperties config) {
        this.gateway = gateway;
        this.config = config;
    }

    public void sendOtp(String otp, String recipient) {
        String msg = format("Your OTP is %s. Do not share. Support: %s", otp, config.getSupportEmail());
        gateway.send(msg, recipientForRegion(recipient));
    }

    public void sendWithdrawalAlert(long amount, String recipient) {
        if (amount >= config.getDailyWithdrawalLimit()) {
            String msg = format("ALERT: Withdrawal of %d detected (limit: %d). If unauthorised, contact %s.",
                    amount, config.getDailyWithdrawalLimit(), config.getSupportEmail());
            gateway.send(msg, recipientForRegion(recipient));
        }
    }

    public void sendMonthlyStatement(String recipient) {
        String msg = format("Monthly statement available. For help, contact %s.", config.getSupportEmail());
        gateway.send(msg, recipientForRegion(recipient));
    }

    private String recipientForRegion(String recipient) {
        String region = config.getRegion();
        if (region == null)
            return recipient;
        switch (region.toLowerCase()) {
            case "asia":
                return "+ASIA:" + recipient;
            case "europe":
                return "+EU:" + recipient;
            case "americas":
            default:
                return "+AM:" + recipient;
        }
    }

    private String format(String template, Object... args) {
        return String.format(template, args) + "\nSent at: " + Instant.now();
    }
}
