package com.management.hotel.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UnifiedPropertiesService {
    @Value("${api.token.username}")
    private String apiUsername;
    @Value("${api.token.password}")
    private String apiPassword;
    @Value("${api.token.key}")
    private String jwtKey;

    public String getApiUsername() {
        return apiUsername;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public String getJwtKey() {
        return jwtKey;
    }
}
