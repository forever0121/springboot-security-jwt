package com.forever.springbootSecurityJwt.filter;

import com.forever.springbootSecurityJwt.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenizationFilter extends BasicAuthenticationFilter {

    public JWTAuthenizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtUtil.TOKEN_HEADER);
        if (token==null||!token.startsWith(JwtUtil.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        super.doFilterInternal(request, response, chain);
    }

    private Authentication getAuthentication(String token) {
        token =token.replace(JwtUtil.TOKEN_PREFIX,"");
        String username = JwtUtil.getUsername(token);
        String userRole = JwtUtil.getUserRole(token);
        if (username!=null){
            return new UsernamePasswordAuthenticationToken(username,null,
                    Collections.singleton(new SimpleGrantedAuthority(userRole)));
        }
        return null;
    }
}
