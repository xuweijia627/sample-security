package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuWeiJia
 * @date 2020/04/03
 */
@Getter
@Setter
public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;

    private int accessTokenValidateSeconds = 7200;
}
