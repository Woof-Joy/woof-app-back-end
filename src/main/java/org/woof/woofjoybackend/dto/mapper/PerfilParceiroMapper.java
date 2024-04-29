package org.woof.woofjoybackend.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Parceiro;
import org.woof.woofjoybackend.domain.entity.Servico;
import org.woof.woofjoybackend.dto.FichaServicoParceiroDTO;
import org.woof.woofjoybackend.dto.ParceiroPerfilDTO;
import org.woof.woofjoybackend.dto.ServicoAvaliacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilParceiroMapper {
    @Mapping(target = "nome", expression = "java(parceiro.getUsuario().getNome()+\" \"+parceiro.getUsuario().getSobrenome())")
    @Mapping(target = "fichas", expression = "java(fichaServicosToFichaServicoParceiroDTOs(parceiro.getServicos()))")
    ParceiroPerfilDTO parceiroToParceiroPerfilDTO(Parceiro parceiro);

    @Mapping(target = "servicos", expression = "java(servicosToServicoAvaliacaoDTOs(ficha.getServicos()))")
    FichaServicoParceiroDTO fichaServicoToFichaServicoParceiroDTO(FichaServico ficha);

    @Mapping(target = "fotoParceiro", expression = "java(servico.getFkFichaServico().getParceiro().getUsuario().getImgUsuario())")
    @Mapping(target = "nomeParceiro", expression = "java(servico.getFkFichaServico().getParceiro().getUsuario().getNome()+\" \"+servico.getFkFichaServico().getParceiro().getUsuario().getSobrenome())")
    @Mapping(target = "nota", expression = "java(servico.getAvaliacao().getNota())")
    @Mapping(target = "comentario", expression = "java(servico.getAvaliacao().getComentario())")
    ServicoAvaliacaoDTO servicoToServicoAvaliacaoDTO(Servico servico);

    List<FichaServicoParceiroDTO> fichaServicosToFichaServicoParceiroDTOs(List<FichaServico> fichas);
    List<ServicoAvaliacaoDTO> servicosToServicoAvaliacaoDTOs(List<Servico> servicos);
}
