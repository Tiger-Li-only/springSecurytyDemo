package com.example.demo.services.impl;

import com.example.demo.domain.SysUser;
import com.example.demo.services.SysUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class CustomUserService implements UserDetailsService {
    @Resource
    SysUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.print("s:"+s);
        System.out.print("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }
}