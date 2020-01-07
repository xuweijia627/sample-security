package com.sample.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author xuWeiJia
 * @date 2020/1/7
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
