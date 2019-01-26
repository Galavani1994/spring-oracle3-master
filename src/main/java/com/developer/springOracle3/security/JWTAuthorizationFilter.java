package com.developer.springOracle3.security;


import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.developer.springOracle3.security.SecurityConstans.*;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService customeUserDetailService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService customeUserDetailService) {
        super(authenticationManager);
        this.customeUserDetailService=customeUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
       String header=request.getHeader(HEADER_STRING);

       if (header == null || !header.startsWith(TOKEN_PREFIX))
       {
           chain.doFilter(request,response);
           return;
       }

       UsernamePasswordAuthenticationToken usernamepasswordAuth=getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamepasswordAuth);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token=request.getHeader(HEADER_STRING);
        if (token == null)
        {
            return null;
        }
        String username=Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                .getBody()
                .getSubject();

        UserDetails userDetails=customeUserDetailService.loadUserByUsername(username);
        //UserTable applicationUser=customeUserDetailService.loadApplicationUserByUsername(username);

        return username !=null ? new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()):null;
    }
}
