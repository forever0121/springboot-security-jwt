package com.forever.springbootSecurityJwt.mapper;

import com.forever.springbootSecurityJwt.model.CustomUserDetail;
import com.forever.springbootSecurityJwt.model.SysUserRole;
import com.forever.springbootSecurityJwt.model.User;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {

    User findByUsername(@Param("name") String name);

    void insert(User user);

    void insertRole(SysUserRole sysUserRole);
}
