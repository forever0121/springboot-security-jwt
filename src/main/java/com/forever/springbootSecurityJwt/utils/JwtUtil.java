package com.forever.springbootSecurityJwt.utils;


import com.forever.springbootSecurityJwt.model.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String ROLE_CLAIMS = "rol";

//    @Value("${jwt.header}")
    public static String TOKEN_HEADER = "Authorization";

//    @Value("${jwt.secret}}")
    public static String SECRET = "mySecret";

//    @Value("${jwt.expiration}")
    public static Long TOKEN_EXPIRE = 86400L;

//    @Value("${jwt.tokenHead}")
    public static String TOKEN_PREFIX = "Bearer ";

    public static String createToken(CustomUserDetail detail){
        Map<String,Object> roleMap = new HashMap<>();
        Collection<? extends GrantedAuthority> authorities = detail.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            roleMap.put(ROLE_CLAIMS,authority.getAuthority());
        }
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .addClaims(roleMap)
                .setSubject(detail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE * 1000))
                .compact();
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }

    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    public static String getUserRole(String token){
        return (String)getTokenBody(token).get(ROLE_CLAIMS);
    }
}
