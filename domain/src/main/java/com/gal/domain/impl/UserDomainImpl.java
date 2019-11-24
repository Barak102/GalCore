package com.gal.domain.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.gal.domain.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gal.domain.UserDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.UserDomainRequest;
import com.gal.domain.response.UserDomainCollectionResponse;
import com.gal.domain.response.UserDomainResponse;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.User;
import gap.app.contracts.UserStorageManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service("UserDomainImpl")
public class UserDomainImpl implements UserDomain {


    @Autowired
    UserStorageManager storage;

    @Autowired
    @Qualifier("UserHibernateDomainValidatorImpl")
    HibernateDomainValidator<User> validator;

    @Override
    public UserDomainResponse getById(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        User user = storage.getById(id);
        if (user == null) {
            throw new IOException("User is not exist");
        }
        UserDomainResponse response = new UserDomainResponse();
        response.setUser(user);
        return response;
    }

    @Override
    public UserDomainCollectionResponse getList(int[] ids) throws IOException {
        UserDomainCollectionResponse response = new UserDomainCollectionResponse();
        response.setUsers(new ArrayList<>(storage.getByIds(ids)));
        return response;
    }

    @Override
    public int add(UserDomainRequest request) throws IOException, ValidationFaultException {
        User user = request.getUser();
        validator.validRequest(user);
        User existedUser = storage.getByEmail(user.getEmail());
        if(existedUser != null) {
            throw new IOException("User already exist.");
        }
        String salt = generatePrivateSalt();
        String privatePass = PasswordUtils.generateSecurePassword(user.getPassword(),salt);
        user.setPassword(privatePass);
        user.setSalt(salt);
        return storage.save(request.getUser());
    }

    @Override
    public void update(UserDomainRequest request) throws IOException, ValidationFaultException {
        validator.validRequest(request.getUser().getId());
        validator.validRequest(request.getUser());
        storage.save(request.getUser());
    }

    @Override
    public void delete(int id) throws IOException, ValidationFaultException {
        validator.validRequest(id);
        storage.delete(id);
    }

    @Override
    public UserDomainCollectionResponse getAll() throws IOException {
        UserDomainCollectionResponse response = new UserDomainCollectionResponse();
        response.setUsers(new ArrayList<>(storage.getAll()));
        return response;
    }

    @Override
    public UserDomainResponse getByEmail(String email) {
            UserDomainResponse response = new UserDomainResponse();
            response.setUser(storage.getByEmail(email));
            return response;
    }


    private String generatePrivateSalt() {
        Random rand = new Random();
        int saltLength = rand.nextInt(50);
        return PasswordUtils.getSalt(saltLength);
    }

}
