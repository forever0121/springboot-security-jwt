package com.forever.springbootSecurityJwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forever.springbootSecurityJwt.model.CustomUserDetail;
import com.forever.springbootSecurityJwt.model.User;
import com.forever.springbootSecurityJwt.utils.JwtUtil;
import com.forever.springbootSecurityJwt.utils.ResponseUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),new ArrayList<>()));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetail customUserDetail = (CustomUserDetail) authResult.getPrincipal();
        String token = JwtUtil.createToken(customUserDetail);
        response.setHeader("token",JwtUtil.TOKEN_PREFIX+token);
        User user = new User();
        user.setUsername(customUserDetail.getUsername());
        user.setId(customUserDetail.getId());
        ResponseUtil.write(response,user);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
