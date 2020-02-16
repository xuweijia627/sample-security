package com.sample.security.core.validate.code.sms;

/**
 * @author xuWeiJia
 * @date 2020/02/16
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
