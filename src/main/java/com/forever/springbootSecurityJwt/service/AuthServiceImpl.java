package com.forever.springbootSecurityJwt.service;

import com.forever.springbootSecurityJwt.exception.CustomException;
import com.forever.springbootSecurityJwt.mapper.AuthMapper;
import com.forever.springbootSecurityJwt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public User register(User user) {
        User dbUser = authMapper.findByUsername(user.getUsername());
        if (dbUser!=null){
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
        }
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(1);
        roles.add(role);
        user.setRoleList(roles);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        authMapper.insert(user);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(1);
        sysUserRole.setUserId(user.getId());
        authMapper.insertRole(sysUserRole);
        return null;
    }
}
