/*
package com.gal.main.configuration.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gal.entities.User;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException,
        IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        //extract token from header
        final String accessToken = httpRequest.getHeader("Authorization");
        if (accessToken != null) {
            //get and check whether token is valid ( from DB or file wherever you are storing the token)
            //Populate SecurityContextHolder by fetching relevant information using token
            final User user = new User();
            user.setEmail("Barak102@gmail.com");
            user.setPassword("123456");
            final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, "123123");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

}
*/
