package com.gal.main.configuration.security.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    /*
     * @Autowired
     * private TokenAuthentcationService service;
     */

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {



        List<GrantedAuthority> gra = new ArrayList<>();
        GrantedAuthority g = new SimpleGrantedAuthority("ROLE_ADMIN");
        gra.add(g);


        User tempUser = new User("barak", "123123", true, true, true, true, gra);


        Authentication user = new UsernamePasswordAuthenticationToken(tempUser, "1231234", gra);
        return user;
        /*
         * final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
         * final HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
         * final String token = request.getHeader(Constants.AUTH_HEADER_NAME);
         * final Token tokenObj = this.service.getToken(token);
         * final AuthenticationToken authToken = new AuthenticationToken(tokenObj);
         * return authToken;
         */

    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
