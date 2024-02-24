package org.woof.woofjoybackend.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.entity.Endereco;
import org.woof.woofjoybackend.domain.entity.response.CepResponse;
import org.woof.woofjoybackend.resources.webclient.gateway.ClientCEPHttpGateway;

@Service
public class ServiceCEP {
    private final ClientCEPHttpGateway clientCep;
    private final ObjectMapper objectMapper;

    public ServiceCEP(ClientCEPHttpGateway clientCep, ObjectMapper objectMapper) {
        this.clientCep = clientCep;
        this.objectMapper = objectMapper;
    }

    public Endereco registerAdressInUser(String cep) {
//        String cep = endereco.getCep();
        CepResponse enderecoResponse = clientCep.getAddressByCep(cep);

        Endereco enderecoCompleto = objectMapper.convertValue(enderecoResponse, Endereco.class);
        return enderecoCompleto;
    }

}
