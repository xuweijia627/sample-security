package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuWeiJia
 * @date 2020/1/8
 */
@Getter
@Setter
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;

    private String url;
}
