package org.woof.woofjoybackend.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.woof.woofjoybackend.domain.entity.FichaServico;
import org.woof.woofjoybackend.domain.entity.Parceiro;
import org.woof.woofjoybackend.domain.entity.Servico;
import org.woof.woofjoybackend.dto.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilParceiroMapper {
    @Mapping(target = "nome", expression = "java(parceiro.getUsuario().getNome()+\" \"+parceiro.getUsuario().getSobrenome())")
    @Mapping(target = "fichas", expression = "java(fichaServicosToFichaServicoParceiroDTOs(parceiro.getServicos()))")
    @Mapping(target = "imgParceiro", expression = "java(parceiro.getUsuario().getImgUsuario())")
    ParceiroPerfilDTO parceiroToParceiroPerfilDTO(Parceiro parceiro);

    @Mapping(target = "servicos", expression = "java(servicosToServicoAvaliacaoDTOs(ficha.getServicos()))")
//    @Mapping(target = "areaExterna", expression = "java(ficha.getResidencia().getAreaExterna())")
//    @Mapping(target = "temAnimais", expression = "java(ficha.getResidencia().getTemAnimais())")
//    @Mapping(target = "temCriancas", expression = "java(ficha.getResidencia().getTemCriancas())")
//    @Mapping(target = "rotaFuga", expression = "java(ficha.getResidencia().getRotaFuga())")
//    @Mapping(target = "dogSofaCama", expression = "java(ficha.getResidencia().getDogSofaCama())")
    FichaServicoParceiroDTO fichaServicoToFichaServicoParceiroDTO(FichaServico ficha);

    @Mapping(target = "fotoParceiro", expression = "java(servico.getFkFichaServico().getParceiro().getUsuario().getImgUsuario())")
    @Mapping(target = "nomeParceiro", expression = "java(servico.getFkFichaServico().getParceiro().getUsuario().getNome()+\" \"+servico.getFkFichaServico().getParceiro().getUsuario().getSobrenome())")
    @Mapping(target = "nota", expression = "java(servico.getAvaliacao().getNota())")
    @Mapping(target = "comentario", expression = "java(servico.getAvaliacao().getComentario())")
    ServicoAvaliacaoDTO servicoToServicoAvaliacaoDTO(Servico servico);

    List<FichaServicoParceiroDTO> fichaServicosToFichaServicoParceiroDTOs(List<FichaServico> fichas);
    List<ServicoAvaliacaoDTO> servicosToServicoAvaliacaoDTOs(List<Servico> servicos);

    @Mapping(expression = "java(parceiro.getUsuario().getNome() + parceiro.getUsuario().getSobrenome() )", target = "nome")
    @Mapping(target = "cidade", expression = "java(parceiro.getUsuario().getEndereco().getLocalidade())")
    @Mapping(target = "uf", expression = "java(parceiro.getUsuario().getEndereco().getUf())")
    //todoing
    @Mapping(target = "qtdServicosPrestados", expression = "java(2)")
    ParceiroSP2DTO parceiroToParceiroDTO(Parceiro parceiro);
}
