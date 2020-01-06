package com.sample.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuWeiJia
 * @date 2020/1/6
 */
@Getter
@Setter
public class BrowserProperties {

    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

    private LoginResponseType loginResponseType = LoginResponseType.JSON;
}
