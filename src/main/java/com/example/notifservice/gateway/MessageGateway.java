package com.example.notifservice.gateway;

public interface MessageGateway {
    /**
     * Send a message to a recipient (email or phone number depending on
     * implementation)
     * 
     * @param message   the message body
     * @param recipient recipient address/number
     */
    void send(String message, String recipient);
}
