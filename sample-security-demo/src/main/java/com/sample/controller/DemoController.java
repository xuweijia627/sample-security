package com.sample.controller;

import com.sample.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
public class DemoController {

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

    @GetMapping("/me")
    public Authentication me(HttpServletRequest request) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //return (UserDetails) authentication.getPrincipal();
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(authorization, "Bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();
        String company = (String) claims.get("company");
        System.out.println(company);

        return authentication;
    }

    @GetMapping("/me1")
    public UserDetails me1(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
