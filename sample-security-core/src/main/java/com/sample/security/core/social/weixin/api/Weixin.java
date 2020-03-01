package com.sample.security.core.social.weixin.api;

/**
 * @author xuWeiJia
 * @date 2020/03/01
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
