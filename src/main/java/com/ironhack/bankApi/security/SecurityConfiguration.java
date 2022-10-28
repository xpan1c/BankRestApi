package com.ironhack.bankApi.security;

import com.ironhack.bankApi.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception{
        return authConf.getAuthenticationManager();
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/accountHolder/**").hasAnyRole("HOLDER","ADMIN")
                .mvcMatchers(HttpMethod.POST, "/api/accountHolder/**").hasAnyRole("HOLDER","ADMIN")
                .mvcMatchers(HttpMethod.GET,"/api/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/api/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/api/admin/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/api/thirdAccount/**").hasAnyRole("THIRDPARTY","ADMIN")
                .anyRequest().permitAll();
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
