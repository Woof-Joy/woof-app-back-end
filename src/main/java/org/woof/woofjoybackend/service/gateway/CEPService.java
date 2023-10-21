package org.woof.woofjoybackend.service.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.response.CEP;
import org.woof.woofjoybackend.web.client.CEPClient;

@Service
public class CEPService  {

    private final CEPClient cepClient;

    @Autowired
    public CEPService(CEPClient cepClient) {
        this.cepClient = cepClient;
    }

    public CEP buscaCEP(String cep){
       return cepClient.buscaCep(cep);
    }


}
