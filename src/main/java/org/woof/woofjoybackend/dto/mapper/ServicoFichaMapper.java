package org.woof.woofjoybackend.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Servico;
import org.woof.woofjoybackend.dto.ParceiroFichaServicoDTO;
import org.woof.woofjoybackend.dto.ServicoFichaDTO;

import java.util.List;

@Mapper
public interface ServicoFichaMapper {
    @Mapping(source = "inicioDoServico", target = "dataHoraInicio")
    @Mapping(source = "fimDoServico", target = "dataHoraFim")
    @Mapping(source = "relatorio", target = "relatorio")
    @Mapping(expression = "java(servico.getCliente().getUsuario().getNome()+\" \"+servico.getCliente().getUsuario().getSobrenome())", target = "cliente")
    ServicoFichaDTO servicoToServicoFichaDTO(Servico servico);

    List<ServicoFichaDTO> servicosToServicoFichaDTOs(List<Servico> servicos);

    @Mapping(target = "qtdServico", expression = "java(ficha.getServicos().size())")
    @Mapping(target = "servicos", expression = "java(servicosToServicoFichaDTOs(ficha.getServicos()))")
    ParceiroFichaServicoDTO fichaServicoToParceiroFichaServicoDTO(FichaServico ficha);

    List<ParceiroFichaServicoDTO> fichaServicosToParceiroFichaServicoDTOs(List<FichaServico> fichas);
}