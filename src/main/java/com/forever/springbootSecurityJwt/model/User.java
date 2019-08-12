package com.forever.springbootSecurityJwt.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

//@Builder
@Data
public class User implements Serializable {

    private int id;
    @Size(min = 6,max = 10)
    private String username;
    @Size(min = 6,max = 10)
    private String password;
    private String nickname;
    private List<Role> roleList;

    public User(){}
}
