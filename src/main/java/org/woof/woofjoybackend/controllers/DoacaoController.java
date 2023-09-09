package org.woof.woofjoybackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {
    private final ServiceUser serviceUser;
    private List<Usuario> usuarios;

    @Autowired
    public DoacaoController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
        this.usuarios = serviceUser.getItens();
    }




}
