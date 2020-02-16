package com.sample.security.browser;

import com.sample.security.browser.authentication.SampleAuthenticationFailureHandler;
import com.sample.security.browser.authentication.SampleAuthenticationSuccessHandler;
import com.sample.security.core.properties.SecurityConstants;
import com.sample.security.core.properties.SecurityProperties;
import com.sample.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SampleAuthenticationSuccessHandler sampleAuthenticationSuccessHandler;
    @Autowired
    private SampleAuthenticationFailureHandler sampleAuthenticationFailureHandler;
    /*
    @Autowired
    private CustomFilter customFilter;*/
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(sampleAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl("/authentication/form")
                .successHandler(sampleAuthenticationSuccessHandler)
                .failureHandler(sampleAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
                        ,securityProperties.getBrowser().getSignInPage()
                        , "/code/image", "/code/sms").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable(); // and().csrf().disable() 关闭跨站请求伪造防护
        /*http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/imooc-signIn.html")
                .loginProcessingUrl("/authentication/form")
                .successHandler(sampleAuthenticationSuccessHandler)
                .failureHandler(sampleAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/imooc-signIn.html").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();*/
    }
}
