package com.sanrenxing.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://127.0.0.1:3000",
                "https://sanrenxing.site",
                "https://san-ren-xing-console-c0666bedcf9c.herokuapp.com",
                "http://sanrenxing-2g5oqm4j478e488f-1326511498.tcloudbaseapp.com",
                "https://sanrenxing-2g5oqm4j478e488f-1326511498.tcloudbaseapp.com"
        ));
        corsConfiguration.setAllowedHeaders(List.of("Content-Type", "Authorization", "type", "Cache-Control"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
