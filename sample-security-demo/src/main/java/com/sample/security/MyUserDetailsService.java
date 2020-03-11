package com.sample.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username={}",username);
        // passwordEncoder.encode("12345") 是在注册的时候做的, 登录时的密码是否正确由spring security去判断
        String password = passwordEncoder.encode("12345");
        log.info("password={}",password);
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("userId={}",userId);
        // passwordEncoder.encode("12345") 是在注册的时候做的, 登录时的密码是否正确由spring security去判断
        String password = passwordEncoder.encode("12345");
        log.info("password={}",password);
        return new SocialUser(userId, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
