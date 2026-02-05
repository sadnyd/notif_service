package com.example.notifservice;

import com.example.notifservice.service.NotificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("sms")
public class StartupSmsTestRunner implements CommandLineRunner {

    private final NotificationService notificationService;

    public StartupSmsTestRunner(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("[StartupSmsTestRunner] Sending test SMS notifications (sms profile)");
        notificationService.sendOtp("999999", "+19999999999");
        notificationService.sendWithdrawalAlert(6000, "+19999999998");
        notificationService.sendMonthlyStatement("+19999999997");
        System.out.println("[StartupSmsTestRunner] Done sending SMS test notifications");
    }
}
