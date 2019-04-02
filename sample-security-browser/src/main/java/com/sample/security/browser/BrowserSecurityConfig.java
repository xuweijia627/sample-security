package com.sample.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
        //http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
    }
}
