package com.gal.authorization.controllers;

import com.gal.dto.request.authorization.LoginDtoRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/auth/")
public class AuthenticationController {

    @ApiOperation("get client by Id get request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = String.class)
    }) // defined the api responses
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public String login(@RequestBody LoginDtoRequest request) {
        return "Token";
    }


}
