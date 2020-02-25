package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author xuWeiJia
 * @date 2020/02/25
 */
@Getter
@Setter
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
