package com.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable simple broker with the destination prefixes for both admin and vendor
        registry.enableSimpleBroker("/admin"); // Topics for admin & vendors
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for routes where clients send messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register WebSocket endpoint and allow cross-origin requests (CORS) from the frontend URL
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:5173")  // Frontend's URL
                .withSockJS(); // Enabling SockJS for fallback options
    }
}
