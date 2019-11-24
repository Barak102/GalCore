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

import com.gal.api.mappers.TagMapper;
import com.gal.domain.TagDomain;
import com.gal.domain.request.TagDomainRequest;
import com.gal.domain.response.TagDomainCollectionResponse;
import com.gal.domain.response.TagDomainResponse;
import com.gal.dto.request.api.TagToOrderDtoRequest;
import com.gal.dto.request.api.TagDtoRequest;
import com.gal.dto.response.api.TagDtoCollectionResponse;
import com.gal.dto.response.api.TagDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api")
@Api(value = "TagControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    @Autowired
    TagDomain tagDomain;

    @Autowired
    TagMapper tagMapper;


    @ApiOperation("get client orders by Id get request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = TagDtoResponse.class)
    })
    @RequestMapping(value = "tag/{id}", method = RequestMethod.GET)
    public TagDtoResponse getById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        TagDomainResponse domainResponse = tagDomain.getById(id);
        return tagMapper.DomainToDto(domainResponse);
    }

    @RequestMapping(value = "tag/get", method = RequestMethod.GET)
    public TagDtoCollectionResponse getTags(@RequestParam(name = "ids", required = false) int[] ids) throws IOException {
        if (ids != null) {
            if (ids.length > 0) {
        TagDomainCollectionResponse domainResponse = tagDomain.getList(ids);
        return tagMapper.DomainCollectionToDtoCollection(domainResponse);
            }
        }
        return tagMapper.DomainCollectionToDtoCollection(tagDomain.getAll());
    }


    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public void add(@RequestBody TagDtoRequest request) throws IOException, ValidationFaultException {
        TagDomainRequest domainRequest = tagMapper.DtoToDomain(request);
        tagDomain.add(domainRequest);
    }


    @RequestMapping(value = "tag", method = RequestMethod.PUT)
    public void update(@RequestBody TagDtoRequest request) throws IOException, ValidationFaultException {
        TagDomainRequest domainRequest = tagMapper.DtoToDomain(request);
        tagDomain.update(domainRequest);
    }

    @RequestMapping(value = "tag/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        tagDomain.delete(id);
    }


    @RequestMapping(value = "order/{id}/tag", method = RequestMethod.POST)
    public void addTagToOrder(@PathVariable(name = "id") int orderId, @RequestBody TagToOrderDtoRequest request) throws IOException, ValidationFaultException {
        tagDomain.addTagNamesToOrder(orderId, request.getNames());
    }

    @RequestMapping(value = "order/{id}/tag", method = RequestMethod.DELETE)
    public void deleteTagOfOrder(@PathVariable(name="id") int orderId, @RequestBody TagToOrderDtoRequest request) throws IOException, ValidationFaultException {
        tagDomain.deleteTagNamesFromOrder(orderId,request.getNames());
    }

    @RequestMapping(value = "order/{id}/tags/", method = RequestMethod.GET)
    public TagDtoCollectionResponse getByOrderId(@PathVariable(name = "id") int orderId) throws IOException, ValidationFaultException {
        return tagMapper.DomainCollectionToDtoCollection(tagDomain.getByOrderId(orderId));
    }



}
