package com.gal.main.configuration.security.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gal.domain.UserDomain;
import com.gal.domain.response.UserDomainResponse;
import com.gal.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainUserDetails implements UserDetailsService {

    @Autowired
    private UserDomain userRepository;


    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {

        UserDomainResponse response = userRepository.getByEmail(email);

        if (response.getUser() == null) {
            throw new UsernameNotFoundException(
                "No user found with username: " + email);
        }
        User user = response.getUser();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword().toLowerCase(), enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked,
            getAuthorities(new ArrayList<String>()));
    }

    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
