package com.gal.authorization.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gal.authorization.mappers.UserMapper;
import com.gal.domain.UserDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.UserDomainRequest;
import com.gal.domain.response.UserDomainResponse;
import com.gal.dto.request.authorization.UserDtoRequest;
import com.gal.dto.response.authorization.UserDtoCollectionResponse;
import com.gal.dto.response.authorization.UserDtoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = int.class)
    })
    public int createUser(@RequestBody UserDtoRequest user) throws IOException, ValidationFaultException {
        UserDomainRequest request = userMapper.DtoToDomain(user);
        return userDomain.add(request);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        userDomain.delete(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserDtoRequest user) throws IOException, ValidationFaultException {
        userDomain.update(userMapper.DtoToDomain(user));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public UserDtoResponse getUserById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        UserDomainResponse domainResponse = userDomain.getById(id);
        return userMapper.DomainToDto(domainResponse);
    }

    @ApiOperation("get order items collection by Ids post request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = UserDtoCollectionResponse.class)
    })
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public UserDtoCollectionResponse getUsers(@RequestParam(name = "ids", required = false) int[] ids) throws IOException {
        if (ids != null) {
            if (ids.length > 0) {
                return userMapper.DomainCollectionToDtoCollection(userDomain.getList(ids));
            }
        }
        return userMapper.DomainCollectionToDtoCollection(userDomain.getAll());
    }
}
