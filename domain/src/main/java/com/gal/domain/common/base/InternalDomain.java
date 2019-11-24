package com.gal.domain.common.base;

import com.gal.domain.common.exception.ValidationFaultException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InternalDomain<Req extends DomainRequest,Res extends  DomainResponse,CRes extends DomainCollectionResponse> {
    Res getById(int id) throws IOException, ValidationFaultException;
    CRes getList(int[] ids) throws IOException;
    int add(Req request) throws IOException, ValidationFaultException;
    void update(Req request) throws IOException, ValidationFaultException;
    void delete(int id) throws IOException, ValidationFaultException;
    CRes getAll() throws IOException;
}
