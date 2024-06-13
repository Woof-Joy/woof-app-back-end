package org.woof.woofjoybackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.dto.FichaServicoCriacaoDTO;
import org.woof.woofjoybackend.dto.ParceiroFichaServicoValorDTO;
import org.woof.woofjoybackend.dto.mapper.FichaServicoMapper;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.exception.BadRequestException;
import org.woof.woofjoybackend.repository.FichaServicoRepository;
import org.woof.woofjoybackend.service.users.ServiceParceiro;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceFichaServico {
    private final FichaServicoRepository fichaServicoRepository;
    private final ServiceParceiro serviceParceiro;

    public Optional<FichaServico> getByArgs(Integer idParceiro, String tipo) {
        return fichaServicoRepository.findByArgs(idParceiro, tipo);
    }

    public FichaServico postFicha(FichaServicoCriacaoDTO ficha) {
        if (getByArgs(ficha.getIdParceiro(), ficha.getTipoServico()).isEmpty()) {
            return fichaServicoRepository.save(FichaServicoMapper.toEntity(ficha, serviceParceiro.findById(ficha.getIdParceiro())));
        }
        throw new BadRequestException("Essa ficha de servico já existe!");
    }

    public FichaServico putFicha(ParceiroFichaServicoValorDTO ficha) {
        if (getByArgs(ficha.getIdParceiro(), ficha.getTipoServico()).isPresent()) {
            FichaServico fichaOriginal =  fichaServicoRepository.findByArgs(ficha.getIdParceiro(), ficha.getTipoServico()).get();
            fichaOriginal.setValor(ficha.getValor());
            return fichaServicoRepository.save(fichaOriginal);
        }
        throw new BadRequestException("Essa ainda não foi cadastrada");
    }

    public List<FichaServico> findByParceiro(Integer idParceiro) {
        if (fichaServicoRepository.existsByParceiroUsuarioId(idParceiro)) {
            return fichaServicoRepository.findByParceiroUsuarioId(idParceiro);
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

}
