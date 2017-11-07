package com.example.demo.config;

import com.example.demo.services.impl.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//扩展SpringSecurity配置需要继承此类

    @Bean
    UserDetailsService customUserService(){//注册UserDetailsService的bean
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());//添加自定义的userDetailsService认证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()//所有的请求需要认证即登陆后才能访问
                .and()
                .formLogin().loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //登录页面可任意访问
                .and().csrf().disable()
                .logout().permitAll();//注销请求可任意访问
    }
}