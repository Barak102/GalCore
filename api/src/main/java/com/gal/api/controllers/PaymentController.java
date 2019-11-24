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

import com.gal.api.mappers.PaymentMapper;
import com.gal.domain.PaymentDomain;
import com.gal.domain.request.PaymentDomainRequest;
import com.gal.domain.response.PaymentDomainCollectionResponse;
import com.gal.domain.response.PaymentDomainResponse;
import com.gal.dto.request.api.PaymentDtoRequest;
import com.gal.dto.response.api.PaymentDtoCollectionResponse;
import com.gal.dto.response.api.PaymentDtoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api")
@Api(value = "PaymentControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    @Autowired
    private PaymentDomain paymentDomain;

    @Autowired
    private PaymentMapper paymentMapper;


    @ApiOperation("get payment of client by paymentId")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = PaymentDtoResponse.class)
    })
    // defined the api responses
    @RequestMapping(value = "payment/{id}", method = RequestMethod.GET)
    public PaymentDtoResponse getById(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        PaymentDomainResponse domainResponse = paymentDomain.getById(id);
        PaymentDtoResponse respone = paymentMapper.DomainToDto(domainResponse);
        return respone;
    }

    @RequestMapping(value = "payment/get", method = RequestMethod.GET)
    public PaymentDtoCollectionResponse getPayments(@RequestParam(name = "ids", required = false) int[] ids) throws IOException {

        if (ids != null) {
            if (ids.length > 0) {
                PaymentDomainCollectionResponse domainResponse = paymentDomain.getList(ids);
                return paymentMapper.DomainCollectionToDtoCollection(domainResponse);
            }
        }
        return paymentMapper.DomainCollectionToDtoCollection(paymentDomain.getAll());
    }


    @RequestMapping(value = "payment/payment", method = RequestMethod.POST)
    public int add(@RequestBody PaymentDtoRequest request) throws IOException, ValidationFaultException {
        PaymentDomainRequest domainRequest = paymentMapper.DtoToDomain(request);
        return paymentDomain.add(domainRequest);
    }


    @RequestMapping(value = "payment/", method = RequestMethod.PUT)
    public void update(@RequestBody PaymentDtoRequest request) throws IOException, ValidationFaultException {
        PaymentDomainRequest domainRequest = paymentMapper.DtoToDomain(request);
        paymentDomain.update(domainRequest);
    }

    @RequestMapping(value = "payment/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        paymentDomain.delete(id);
    }


    @RequestMapping(value = "order/{id}/payments", method = RequestMethod.GET)
    public PaymentDtoCollectionResponse getByOrderId(@PathVariable(name = "id") int id) throws IOException, ValidationFaultException {
        PaymentDomainCollectionResponse response = paymentDomain.getByOrderId(id);
        return paymentMapper.DomainCollectionToDtoCollection(response);
    }

}
