package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.entity.Servico;
import org.woof.woofjoybackend.repository.ServicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServico {
    private final ServicoRepository servicoRepository;

    public Servico post(Servico servico) {
        if (servicoRepository.existsById(servico.getId())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409));
        }
        return servicoRepository.save(servico);
    }

    public Servico getById(Integer id) {
        return servicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public List<Servico> getAll() {
        return servicoRepository.findAll();
    }

    public void delete(Integer id) {
        if (!servicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        servicoRepository.deleteById(id);
    }
}
