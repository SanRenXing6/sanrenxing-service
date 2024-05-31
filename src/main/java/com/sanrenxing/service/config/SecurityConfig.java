package com.sanrenxing.service.config;

import com.sanrenxing.service.config.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/api/v1/auth/**").permitAll()
                            .requestMatchers("api/v1/call/**").permitAll()
                            .requestMatchers("api/v1/text/**").permitAll()
                            .requestMatchers("/api/v1/users/**").authenticated()
                            .requestMatchers("/api/v1/profiles/**").authenticated()
                            .requestMatchers("/api/v1/feedbacks/**").authenticated()
                            .requestMatchers("/api/v1/messages/**").authenticated()
                            .requestMatchers("/api/v1/images/**").authenticated()
                            .anyRequest()
                            .authenticated());
        return http.build();
    }
}
