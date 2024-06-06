package org.woof.woofjoybackend.service.users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.domain.entity.Usuario;
import org.woof.woofjoybackend.domain.entity.Parceiro;
import org.woof.woofjoybackend.dto.ParceiroDTO;
import org.woof.woofjoybackend.domain.entity.exception.BadRequestException;
import org.woof.woofjoybackend.repository.ParceiroRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceParceiro {

    private final UsuarioRepository usuarioRepository;
    private final ParceiroRepository parceiroRepository;

    public List<Parceiro> getParceiros() {
        return parceiroRepository.findAll();
    }


    public Parceiro findById(Integer id) {
        return parceiroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public Parceiro findByIdUsuario(Integer id) {
        return parceiroRepository.findByUsuarioId(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public List<Parceiro> getParceirosByTipoServico(String tipo) {
        if (tipo.equalsIgnoreCase("Ambos") ||
                tipo.equalsIgnoreCase("dogWalker") ||
                tipo.equalsIgnoreCase("dogSitter")
        ) {
            return parceiroRepository.findByTipoServicoIgnoreCase(tipo);
        } else if (tipo.equalsIgnoreCase("Todos")) {
            return parceiroRepository.findAll();
        }
        throw new BadRequestException("Ambos, dogWalker, dogSitter, Todos");
    }

    public List<Parceiro> getParceirosByNome(String nome) {
        return parceiroRepository.findByUsuarioNomeContainsIgnoreCase(nome);
    }

    public List<ParceiroDTO> ordenaFeed(String ordenacao, List<ParceiroDTO> lista) {
        switch (ordenacao) {
            case "Estrelas" -> {
                for (int i = 0; i < lista.size() - 1; i++) {
                    int indiceMenor = i;
                    for (int j = i + 1; j < lista.size(); j++) {
                        if (lista.get(j).getEstrelas() > lista.get(indiceMenor).getEstrelas()) {
                            indiceMenor = j;
                        }
                    }
                    if (indiceMenor != i) {
                        ParceiroDTO dto = lista.get(i);
                        lista.set(i, lista.get(indiceMenor));
                        lista.set(indiceMenor, dto);
                    }
                }
            }
            case "Recente" -> {
                for (int i = 0; i < lista.size() - 1; i++) {
                    int indiceMenor = i;
                    for (int j = i + 1; j < lista.size(); j++) {
                        if (lista.get(j).getDataEntrada().isAfter(lista.get(indiceMenor).getDataEntrada())) {
                            indiceMenor = j;
                        }
                    }
                    if (indiceMenor != i) {
                        ParceiroDTO dto = lista.get(i);
                        lista.set(i, lista.get(indiceMenor));
                        lista.set(indiceMenor, dto);
                    }
                }
            }
            case "Antigo" -> {
                for (int i = 0; i < lista.size() - 1; i++) {
                    int indiceMenor = i;
                    for (int j = i + 1; j < lista.size(); j++) {
                        if (lista.get(j).getDataEntrada().isBefore(lista.get(indiceMenor).getDataEntrada())) {
                            indiceMenor = j;
                        }
                    }
                    if (indiceMenor != i) {
                        ParceiroDTO dto = lista.get(i);
                        lista.set(i, lista.get(indiceMenor));
                        lista.set(indiceMenor, dto);
                    }
                }
            }
            default -> throw new BadRequestException("Estrelas, Recente, Antigo");
        }
        return lista;
    }

    public Parceiro putParceiro(Parceiro parceiro, Integer id) {
        parceiro.setIdParceiro(id);
        return parceiroRepository.save(parceiro);
    }

    public void deleteParceiro(Integer id) {
        parceiroRepository.deleteById(id);
    }

    public boolean idExiste(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return parceiroRepository.existsByUsuario(usuario);
    }

    public Parceiro premiumParceiro(Integer id) {
        Parceiro parceiro = findByIdUsuario(id);
        parceiro.setPremium(true);
        return parceiroRepository.save(parceiro);
    }
}