package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuWeiJia
 * @date 2020/1/8
 */
@Getter
@Setter
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();
}
