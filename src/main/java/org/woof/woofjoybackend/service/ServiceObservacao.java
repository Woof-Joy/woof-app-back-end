package org.woof.woofjoybackend.service;

import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.entity.Observacao;
import org.woof.woofjoybackend.repository.ObservacaoRepository;

@Service
public class ServiceObservacao {

    private ObservacaoRepository observacaoRepository;

    public ServiceObservacao(ObservacaoRepository observacaoRepository) {
        this.observacaoRepository = observacaoRepository;
    }

    public Observacao listarObservacao(Integer id){
        Observacao obsCadastrada = observacaoRepository.findById(id).get();
        return obsCadastrada;
    }

    public Observacao cadastrar(Observacao obs){
        return observacaoRepository.save(obs);
    }

    public Observacao atulizar(Integer id, Observacao obs){
        System.out.println("Tentando atulizar....");
        obs.setId(id);
        if (observacaoRepository.existsById(id)) {
            Observacao obsAtualizada = observacaoRepository.save(obs);
            System.out.println("Observacao atulizada!");
            return obsAtualizada;
        }
        System.out.println("O pet não foi atulizado, pois ele não existe.");
        return null;
    }
}
