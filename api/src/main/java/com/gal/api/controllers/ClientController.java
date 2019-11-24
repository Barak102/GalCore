package com.gal.api.controllers;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gal.api.mappers.ClientMapper;
import com.gal.domain.ClientDomain;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.ClientDomainRequest;
import com.gal.domain.response.ClientDomainResponse;
import com.gal.dto.request.api.ClientDtoCollectionRequest;
import com.gal.dto.request.api.ClientDtoRequest;
import com.gal.dto.response.api.ClientDtoCollectionResponse;
import com.gal.dto.response.api.ClientDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/api/client/")
@Api(value = "ClientsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ClientDomain clientDomain;

    @Autowired
    private ClientMapper clientMapper;


    @ApiOperation("get client by Id get request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ClientDtoResponse.class)
    }) // defined the api responses
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Response getById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        try {
            ClientDomainResponse serviceResponse = clientDomain.getById(id);
            ClientDtoResponse response = clientMapper.DomainToDto(serviceResponse);
            return Response.ok(response).build();
        } catch (Exception ex) {

        }
        return Response.noContent().build();
    }


    @ApiOperation("get clients collection by Ids post request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ClientDtoCollectionResponse.class)
    })
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public ClientDtoCollectionResponse getClients(@RequestBody ClientDtoCollectionRequest request) throws IOException {
        ClientDtoCollectionResponse response = clientMapper.DomainCollectionToDtoCollection(clientDomain.getList(request.getClientIds()));
        return response;
    }


    @ApiOperation("insert new client, post request.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = int.class)
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public int add(@RequestBody ClientDtoRequest request) throws IOException, ValidationFaultException {
        ClientDomainRequest domainRequest = clientMapper.DtoToDomain(request);
        return clientDomain.add(domainRequest);
    }


    @ApiOperation("update existing client, put request.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@RequestBody ClientDtoRequest request) throws IOException, ValidationFaultException {
        ClientDomainRequest serviceRequest = clientMapper.DtoToDomain(request);
        clientDomain.update(serviceRequest);
    }

    @ApiOperation("delete existing client, delete request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        clientDomain.delete(id);
    }

    @ApiOperation("get all clients list, get request.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ClientDtoCollectionResponse getAll() throws IOException {
        return clientMapper.DomainCollectionToDtoCollection(clientDomain.getAll());
    }


}
