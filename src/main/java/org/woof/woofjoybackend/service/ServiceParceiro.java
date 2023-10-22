package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.iVerificaveis;
import org.woof.woofjoybackend.entity.Usuario;
import org.woof.woofjoybackend.entity.Parceiro;
import org.woof.woofjoybackend.repository.ParceiroRepository;
import org.woof.woofjoybackend.repository.UsuarioRepository;

import java.util.List;

@Service
public class ServiceParceiro implements iVerificaveis {

    private UsuarioRepository usuarioRepository;
    private ParceiroRepository parceiroRepository;

    public ServiceParceiro(UsuarioRepository usuarioRepository, ParceiroRepository parceiroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.parceiroRepository = parceiroRepository;
    }

    public List<Parceiro> listaParceiros() {
        return parceiroRepository.findAll();
    }

    public Parceiro listaParceiroPorId(Integer id) {
        return parceiroRepository.findById(id).get();
    }

    public Parceiro attParceiro(Parceiro parceiro, Integer id) {
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
}
