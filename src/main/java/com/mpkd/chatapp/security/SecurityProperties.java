package com.mpkd.chatapp.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class SecurityProperties {

    private String secret;
    private String tokenPrefix;
    private String tokenHeader;
    private Long expirationTime;

}
