package com.mpkd.chatapp.user.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "user.validation")
@AllArgsConstructor
@NoArgsConstructor
public class UserValidationConfig {

    /**
     * Minimal user's password length
     */
    private Integer minPasswordLength;

}
