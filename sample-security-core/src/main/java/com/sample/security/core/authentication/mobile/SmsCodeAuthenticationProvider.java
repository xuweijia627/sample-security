package com.sample.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author xuWeiJia
 * @date 2020/02/20
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobile = (String) authentication.getPrincipal();
        UserDetails userDetails = this.getUserDetailsService().loadUserByUsername(mobile);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        smsCodeAuthenticationToken.setDetails(authentication.getDetails());
        return smsCodeAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
