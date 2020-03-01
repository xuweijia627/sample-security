package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author xuWeiJia
 * @date 2020/03/01
 */
@Getter
@Setter
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";
}
