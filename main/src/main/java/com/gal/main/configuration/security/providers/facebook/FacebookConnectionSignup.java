package com.gal.main.configuration.security.providers.facebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import com.gal.domain.UserDomain;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    private UserDomain userDomain;

    @Override
    public String execute(Connection<?> connection) {
       // User user = new User();
/*        user.setEmail(connection.getKey().get);
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));*/
        return "User";
    }
}
