package com.forever.springbootSecurityJwt.model;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class Role {

    private int id;
    private String name;
    private String nameZh;

    public Role(int id, String name, String nameZh) {
        this.id = id;
        this.name = name;
        this.nameZh = nameZh;
    }

    public Role() {}
}
