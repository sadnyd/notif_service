package com.example.notifservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bank.config")
public class BankConfigProperties {
    private long dailyWithdrawalLimit = 1000L;
    private String supportEmail = "support@example.com";
    private String region = "Americas";
    private long apiTimeout = 3000L; // milliseconds

    public long getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(long dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getApiTimeout() {
        return apiTimeout;
    }

    public void setApiTimeout(long apiTimeout) {
        this.apiTimeout = apiTimeout;
    }
}
