package com.sample.security.core.validate.code.sms;

/**
 * @author xuWeiJia
 * @date 2020/02/16
 */
public class DefaultSmsCodeSender implements SmsCodeSender {


    @Override
    public void send(String mobile, String code) {
        System.out.println("发送短信验证码,mobile=" + mobile + ",code=" +code);
    }
}
