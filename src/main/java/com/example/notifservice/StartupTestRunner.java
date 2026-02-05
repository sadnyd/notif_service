package com.example.notifservice;

import com.example.notifservice.service.NotificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class StartupTestRunner implements CommandLineRunner {

    private final NotificationService notificationService;

    public StartupTestRunner(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("[StartupTestRunner] Sending test notifications (dev profile)");
        notificationService.sendOtp("123456", "+10000000000");
        notificationService.sendWithdrawalAlert(100, "+10000000001"); // below dev limit
        notificationService.sendWithdrawalAlert(600, "+10000000002"); // above dev limit -> should alert
        notificationService.sendMonthlyStatement("+10000000003");
        System.out.println("[StartupTestRunner] Done sending test notifications");
    }
}
