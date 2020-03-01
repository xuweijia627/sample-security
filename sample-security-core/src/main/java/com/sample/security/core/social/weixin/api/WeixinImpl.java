package com.sample.security.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xuWeiJia
 * @date 2020/03/01
 */
@Slf4j
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";
    private ObjectMapper objectMapper = new ObjectMapper();

    public WeixinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WeixinUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO + openId;
        log.info("获取用户信息, url={}", url);
        String response = getRestTemplate().getForObject(url, String.class);
        log.info("获取用户信息结果:{}", response);
        if(StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeixinUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WeixinUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
