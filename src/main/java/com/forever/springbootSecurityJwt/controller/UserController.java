package com.forever.springbootSecurityJwt.controller;

import com.forever.springbootSecurityJwt.model.ResultJson;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResultJson<String> userList(){
        return ResultJson.ok("进来了");
    }
}
