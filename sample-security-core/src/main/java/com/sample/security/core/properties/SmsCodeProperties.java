package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuWeiJia
 * @date 2020/02/16
 */
@Getter
@Setter
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String url;
}
