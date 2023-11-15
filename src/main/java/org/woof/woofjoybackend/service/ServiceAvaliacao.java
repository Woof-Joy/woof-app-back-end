package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.entity.Avaliacao;
import org.woof.woofjoybackend.repository.AvaliacaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAvaliacao {
    private final AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> getAvaliacaoById(Integer id){
        return avaliacaoRepository.findByServicoParceiroIdParceiro(id);
    }

}
