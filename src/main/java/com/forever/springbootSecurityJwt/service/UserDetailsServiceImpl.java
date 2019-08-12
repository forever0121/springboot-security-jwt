package com.forever.springbootSecurityJwt.service;

import com.forever.springbootSecurityJwt.mapper.AuthMapper;
import com.forever.springbootSecurityJwt.model.CustomUserDetail;
import com.forever.springbootSecurityJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthMapper authMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authMapper.findByUsername(username);
        if (user!=null){
            return new CustomUserDetail(user);
        }
        return null;
    }
}
