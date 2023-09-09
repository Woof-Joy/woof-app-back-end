package org.woof.woofjoybackend.controllers.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {
    private final ServiceUser serviceUser;
    private List<Usuario> usuarios;

    @Autowired
    public ProfissionalController(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }




}
