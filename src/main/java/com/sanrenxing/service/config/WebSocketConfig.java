package com.sanrenxing.service.config;

import com.sanrenxing.service.api.WebSocketController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final static String CHAT_ENDPOINT = "/api/v1/chat";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getWebSocketHandler(), CHAT_ENDPOINT)
                .setAllowedOrigins("*")
                .addInterceptors(new SessionInterceptor());
    }

    @Bean
    public WebSocketController getWebSocketHandler() {
        return new WebSocketController();
    }

}




