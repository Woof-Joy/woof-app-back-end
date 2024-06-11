package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.entity.Avaliacao;
import org.woof.woofjoybackend.repository.AvaliacaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAvaliacao {
    private final AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> getAvaliacaoByIdParceiro(Integer id){
        return avaliacaoRepository.findByIdParceiro(id);
    }

    public Avaliacao postAvaliacao(Avaliacao avaliacao, Integer idParceiro){
        avaliacao.setIdParceiro(idParceiro);
        Avaliacao avaliResponse= avaliacaoRepository.save(avaliacao);
        return avaliResponse;
    }

}
