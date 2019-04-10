package com.sample.security.browser;

import com.sample.security.browser.authentication.SampleAuthenticationFailureHandler;
import com.sample.security.browser.authentication.SampleAuthenticationSuccessHandler;
import com.sample.security.core.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SampleAuthenticationSuccessHandler sampleAuthenticationSuccessHandler;
    @Autowired
    private SampleAuthenticationFailureHandler sampleAuthenticationFailureHandler;
    @Autowired
    private CustomFilter customFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/authentication/form")
                .successHandler(sampleAuthenticationSuccessHandler)
                .failureHandler(sampleAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
