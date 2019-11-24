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

import com.gal.api.mappers.OrderMapper;
import com.gal.domain.OrderDomain;
import com.gal.domain.request.OrderDomainRequest;
import com.gal.domain.response.OrderDomainCollectionResponse;
import com.gal.domain.response.OrderDomainResponse;
import com.gal.dto.request.api.OrderDtoRequest;
import com.gal.dto.request.api.OrderStatusChangeDtoRequest;
import com.gal.dto.response.api.OrderDtoCollectionResponse;
import com.gal.dto.response.api.OrderDtoResponse;
import com.gal.entities.enums.OrderStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/")
@Api(value = "OrderControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {


    @Autowired
    private OrderDomain orderDomain;

    @Autowired
    private OrderMapper orderMapper;


    @ApiOperation("get client orders by Id get request")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = OrderDtoResponse.class)
    })
    // defined the api responses
    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    public OrderDtoResponse getById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        OrderDomainResponse domainResponse = orderDomain.getById(id);
        return orderMapper.DomainToDto(domainResponse);
    }

    @RequestMapping(value = "order/get", method = RequestMethod.GET)
    public OrderDtoCollectionResponse getOrders(@RequestParam(name = "ids", required = false) int[] ids) throws IOException {
        OrderDomainCollectionResponse domainResponse;
        if (ids != null) {
            if (ids.length > 0) {
                domainResponse = orderDomain.getList(ids);
                return orderMapper.DomainCollectionToDtoCollection(domainResponse);
            }
        }
        domainResponse = orderDomain.getAll();
        return orderMapper.DomainCollectionToDtoCollection(domainResponse);

    }


    @RequestMapping(value = "order/", method = RequestMethod.POST)
    public void add(@RequestBody OrderDtoRequest request) throws IOException, ValidationFaultException {
        OrderDomainRequest domainRequest = orderMapper.DtoToDomain(request);
        orderDomain.add(domainRequest);
    }


    @RequestMapping(value = "order", method = RequestMethod.PUT)
    public void update(@RequestBody OrderDtoRequest request) throws IOException, ValidationFaultException {
        OrderDomainRequest domainRequest = orderMapper.DtoToDomain(request);
        orderDomain.update(domainRequest);
    }

    @RequestMapping(value = "order/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        orderDomain.delete(id);
    }

    /*
     * @RequestMapping(value = "order/get", method = RequestMethod.GET)
     * public OrderDtoCollectionResponse getAll() throws IOException {
     * return orderMapper.DomainCollectionToDtoCollection(orderDomain.getAll());
     * }
     */

    @RequestMapping(value = "order/changeStatus", method = RequestMethod.PATCH)
    public void cancel(@RequestBody OrderStatusChangeDtoRequest request) throws IOException, ValidationFaultException {

        int orderId = request.getOrderId();
        if (request.getStatus() == OrderStatus.APPROVED) {
            orderDomain.approveOrder(orderId);
        } else if (request.getStatus() == OrderStatus.CANCELED) {
            orderDomain.cancelOrder(orderId);
        }
    }


    @RequestMapping(value = "client/{id}/orders", method = RequestMethod.GET)
    public OrderDtoCollectionResponse getByclientId(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        return orderMapper.DomainCollectionToDtoCollection(orderDomain.getByClientId(id));
    }

    @RequestMapping(value = "order/getByTagName", method = RequestMethod.GET)
    public OrderDtoCollectionResponse getByTagName(@RequestParam("tags") String tagNames) throws IOException {
        return orderMapper.DomainCollectionToDtoCollection(orderDomain.getByTagsName(tagNames.split(",")));
    }

    @RequestMapping(value = "order/getByTagId", method = RequestMethod.GET)
    public OrderDtoCollectionResponse getByTagIds(@RequestParam("ids") int[] ids) throws IOException {
        return orderMapper.DomainCollectionToDtoCollection(orderDomain.getByTagIds(ids));
    }
}
