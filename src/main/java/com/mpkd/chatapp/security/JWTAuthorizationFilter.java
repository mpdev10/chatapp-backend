package com.mpkd.chatapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.isNull;

class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final SecurityProperties properties;

    JWTAuthorizationFilter(AuthenticationManager authManager, SecurityProperties properties) {
        super(authManager);
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = Strings.nullToEmpty(request.getHeader(properties.getTokenHeader()));
        if (header.startsWith(properties.getTokenPrefix())) {
            var authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(properties.getTokenHeader());
        if (isNull(token)) {
            return null;
        }
        String user = JWT.require(Algorithm.HMAC512(properties.getSecret().getBytes()))
                .build()
                .verify(token.replace(properties.getTokenPrefix(), ""))
                .getSubject();
        return Optional.ofNullable(user)
                .map(u -> new UsernamePasswordAuthenticationToken(u, null, Lists.newArrayList()))
                .orElse(null);
    }
}