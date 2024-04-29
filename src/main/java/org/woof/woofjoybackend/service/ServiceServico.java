package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.dto.ServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.mapper.ServicoMapper;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Servico;
import org.woof.woofjoybackend.repository.ServicoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServico {
    private final ServicoRepository servicoRepository;
    private final ServiceFichaServico serviceFichaServico;


    public Servico post(ServicoCriacaoDTO servico) {
        FichaServico ficha = serviceFichaServico.getByArgs(servico.getIdParceiro(), servico.getTipoServico()).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        Servico servicoSalvo = servicoRepository.save(ServicoMapper.toEntity(servico, ficha));
        ficha.getServicos().add(servicoSalvo);

        return servicoSalvo;
    }


    public Servico patch(Integer id) {
        Servico servicoOriginal = servicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

        if (servicoOriginal.getStatus().equalsIgnoreCase("aguardandoConfirmacao")) {
            servicoOriginal.setStatus("aguardandoInicio");
        } else if (servicoOriginal.getStatus().equalsIgnoreCase("aguardandoInicio")) {
            servicoOriginal.setStatus("emAndamento");
        } else if (servicoOriginal.getStatus().equalsIgnoreCase("emAndamento")) {
            servicoOriginal.setStatus("concluido");
        }
        return servicoRepository.save(servicoOriginal);
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
