package com.forever.springbootSecurityJwt.controller;

import com.forever.springbootSecurityJwt.model.*;
import com.forever.springbootSecurityJwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/register")
    public ResultJson register(@RequestBody User user){
        if (StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getUsername())){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        return ResultJson.ok(authService.register(user));
    }
}
