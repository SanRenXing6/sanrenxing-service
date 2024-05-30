package com.sanrenxing.service.config;

import com.sanrenxing.service.api.CallWSController;
import com.sanrenxing.service.api.TextWSController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final static String TEXT_ENDPOINT = "/api/v1/text";
    private final static String CALL_ENDPOINT = "/api/v1/call";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getTextWebSocketHandler(), TEXT_ENDPOINT)
                .setAllowedOrigins("*")
                .addInterceptors(new SessionInterceptor());
        registry.addHandler(getCallWebSocketHandler(), CALL_ENDPOINT)
                .setAllowedOrigins("*")
                .addInterceptors(new SessionInterceptor());
    }

    @Bean
    public TextWSController getTextWebSocketHandler() {
        return new TextWSController();
    }

    @Bean
    public CallWSController getCallWebSocketHandler() {
        return new CallWSController();
    }

}




