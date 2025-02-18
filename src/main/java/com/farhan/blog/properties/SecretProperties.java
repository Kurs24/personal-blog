package com.farhan.blog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:security.properties")
@ConfigurationProperties(prefix = "blog")
public class SecretProperties {
    private String userUserName;
    private String userPassword;
    private String jwtSecret;
}
