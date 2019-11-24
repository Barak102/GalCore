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

import com.gal.api.mappers.OrderItemMapper;
import com.gal.domain.OrderItemDomain;
import com.gal.domain.request.OrderItemDomainRequest;
import com.gal.domain.response.OrderItemDomainResponse;
import com.gal.dto.request.api.OrderItemDtoRequest;
import com.gal.dto.response.api.OrderItemDtoCollectionResponse;
import com.gal.dto.response.api.OrderItemDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path="/api/")
@Api(value="OrderItemControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemController {


    @Autowired
    private OrderItemDomain orderItemDomain;

    @Autowired
    private OrderItemMapper orderItemMapper;



    @ApiOperation("get orderItem by Id get request")
    @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = OrderItemDtoResponse.class)}) // defined the api responses
    @RequestMapping(value = "orderitem/{id}",method = RequestMethod.GET)
    public OrderItemDtoResponse getById(@PathVariable(name="id") int id) throws IOException, ValidationFaultException {
        OrderItemDomainResponse serviceResponse = orderItemDomain.getById(id);
        return orderItemMapper.DomainToDto(serviceResponse);
    }


    @ApiOperation("get order items collection by Ids post request")
    @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = OrderItemDtoCollectionResponse.class)})
    @RequestMapping(value="orderitem/get", method = RequestMethod.GET)
    public OrderItemDtoCollectionResponse getOrders(@RequestParam(name = "ids", required = false) int[] ids) throws IOException {
        if(ids != null) {
            if(ids.length > 0) {
         return orderItemMapper.DomainCollectionToDtoCollection(orderItemDomain.getList(ids));
            }
        }
        return orderItemMapper.DomainCollectionToDtoCollection(orderItemDomain.getAll());
    }


    @ApiOperation("insert new client, post request.")
    @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = int.class)})
    @RequestMapping(value="orderitem",method = RequestMethod.POST)
    public int add(@RequestBody OrderItemDtoRequest request) throws IOException, ValidationFaultException {
        OrderItemDomainRequest domainRequest = orderItemMapper.DtoToDomain(request);
        return orderItemDomain.add(domainRequest);
    }


    @ApiOperation("update existing client, put request.")
    @ApiResponses(value = {@ApiResponse(code=200,message = "OK")})
    @RequestMapping(value="orderitem",method = RequestMethod.PUT)
    public void update(@RequestBody OrderItemDtoRequest request) throws IOException, ValidationFaultException {
        OrderItemDomainRequest domainRequest = orderItemMapper.DtoToDomain(request);
        orderItemDomain.update(domainRequest);
    }

    @ApiOperation("delete existing client, delete request")
    @ApiResponses(value = {@ApiResponse(code=200,message = "OK")})
    @RequestMapping(value="orderitem/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name="id") int id) throws IOException, ValidationFaultException {
        orderItemDomain.delete(id);
    }

    @RequestMapping(value="order/{id}/orderItems",method = RequestMethod.GET)
    public OrderItemDtoCollectionResponse getOrderItemsByOrderId(@PathVariable(name="id") int id) throws IOException, ValidationFaultException {
        return orderItemMapper.DomainCollectionToDtoCollection(orderItemDomain.getByOrderId(id));
    }





}
