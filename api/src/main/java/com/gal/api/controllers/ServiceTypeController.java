package com.gal.api.controllers;

import java.io.IOException;

import com.gal.domain.common.exception.ValidationFaultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gal.api.mappers.ServiceTypeMapper;
import com.gal.domain.ServiceTypeDomain;
import com.gal.domain.request.ServiceTypeDomainRequest;
import com.gal.domain.response.ServiceTypeDomainCollectionResponse;
import com.gal.domain.response.ServiceTypeDomainResponse;
import com.gal.dto.request.api.ServiceTypeDtoRequest;
import com.gal.dto.response.api.ServiceTypeDtoCollectionResponse;
import com.gal.dto.response.api.ServiceTypeDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/servicetype/")
@Api(value = "ServiceTypeControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceTypeController {

    @Autowired
    private ServiceTypeDomain serviceTypeDomain;

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @ApiOperation("get client orders by Id get request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ServiceTypeDtoResponse.class)
    })
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ServiceTypeDtoResponse getById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        ServiceTypeDomainResponse domainResponse = serviceTypeDomain.getById(id);
        ServiceTypeDtoResponse respone = serviceTypeMapper.DomainToDto(domainResponse);
        return respone;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServiceTypeDtoCollectionResponse getServiceTypes(@RequestParam(name = "ids", required = false) int[] ids) throws IOException {
        if (ids != null) {
            if (ids.length > 0) {
                ServiceTypeDomainCollectionResponse domainResponse = serviceTypeDomain.getList(ids);
                return serviceTypeMapper.DomainCollectionToDtoCollection(domainResponse);
            }
        }
        return serviceTypeMapper.DomainCollectionToDtoCollection(serviceTypeDomain.getAll());
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(@RequestBody ServiceTypeDtoRequest request) throws IOException, ValidationFaultException {
        ServiceTypeDomainRequest domainRequest = serviceTypeMapper.DtoToDomain(request);
        serviceTypeDomain.add(domainRequest);
    }


    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(@RequestBody ServiceTypeDtoRequest request) throws IOException, ValidationFaultException {
        ServiceTypeDomainRequest domainRequest = serviceTypeMapper.DtoToDomain(request);
        serviceTypeDomain.update(domainRequest);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        serviceTypeDomain.delete(id);
    }
}
