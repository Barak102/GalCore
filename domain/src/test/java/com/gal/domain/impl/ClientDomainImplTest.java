package com.gal.domain.impl;


import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gal.db.repository.ClientRepository;
import com.gal.domain.common.exception.ValidationFaultException;
import com.gal.domain.request.ClientDomainRequest;
import com.gal.domain.validator.hibernate.HibernateDomainValidator;
import com.gal.entities.GalClient;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ClientDomainImplTest {

    @Tested
    private ClientDomainImpl clientDomain;

    @Injectable
    ClientRepository db;

    @Injectable
    HibernateDomainValidator<GalClient> ClientHibernateDomainValidatorImpl;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void validClient() throws IOException, ValidationFaultException {
        new Expectations() {
            {
                db.save((GalClient) any);
                result = 5;
            }
        };

        ClientDomainRequest request = new ClientDomainRequest();
        GalClient client = new GalClient();
        client.setFirstName("Barak");
        client.setLastName("Josef");
        client.setEmail("barak102@gmail.com");
        client.setCity("Ramla");
        client.setAddress("Harama 15");
        client.setPhone("0545857223");
        request.setClient(client);


        int id = clientDomain.add(request);

        Assert.assertEquals(id, 5);

    }


    @Test
    public void getC() {
        Assert.assertTrue("Barak",true);
    }

}
