package com.sample.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xuWeiJia
 * @date 2020/1/8
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);

}
