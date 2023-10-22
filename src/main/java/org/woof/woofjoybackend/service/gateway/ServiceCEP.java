package org.woof.woofjoybackend.service.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.response.Endereco;
import org.woof.woofjoybackend.web.client.ClientCEP;

@Service
public class ServiceCEP {

    private final ClientCEP cepClient;

    @Autowired
    public ServiceCEP(ClientCEP cepClient) {
        this.cepClient = cepClient;
    }

    public Endereco buscaCEP(String cep){
       return cepClient.buscaCep(cep);
    }


}
//    MUDO DENOVO
