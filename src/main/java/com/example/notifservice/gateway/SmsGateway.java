package com.example.notifservice.gateway;

import com.example.notifservice.config.BankConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@Profile("sms")
public class SmsGateway implements MessageGateway {

    private static final Logger log = LoggerFactory.getLogger(SmsGateway.class);

    private final BankConfigProperties config;
    private final ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public SmsGateway(BankConfigProperties config) {
        this.config = config;
    }

    @Override
    public void send(String message, String recipient) {
        long timeoutMs = Math.max(100, config.getApiTimeout());
        Future<?> f = executor.submit(() -> {
            // Simulate external SMS provider call (replace with real HTTP client)
            try {
                log.info("[SmsGateway] sending to {} (simulated)...", recipient);
                // Simulate variable latency
                Thread.sleep(300);
                log.info("[SmsGateway] Sent to {}: {}", recipient, message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("[SmsGateway] interrupted while sending to {}", recipient);
            }
        });

        try {
            f.get(timeoutMs, TimeUnit.MILLISECONDS);
        } catch (TimeoutException te) {
            f.cancel(true);
            log.error("[SmsGateway] Timeout after {}ms sending to {}", timeoutMs, recipient);
        } catch (ExecutionException ee) {
            log.error("[SmsGateway] Execution error sending to {}: {}", recipient, ee.getMessage());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            log.warn("[SmsGateway] interrupted while waiting for send result");
        }
    }
}
