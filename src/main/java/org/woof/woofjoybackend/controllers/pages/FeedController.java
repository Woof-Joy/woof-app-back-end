package org.woof.woofjoybackend.controllers.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.woof.woofjoybackend.domain.Usuario;
import org.woof.woofjoybackend.service.ServiceUser;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    private ServiceUser serviceUser;
    private List<Usuario> clientes = new ArrayList<>();

    @Autowired
    public FeedController(ServiceUser serviceUser) {

        this.serviceUser = serviceUser;
        //this.clientes = serviceUser.getClientes();
    }

//    @GetMapping
//    public ResponseEntity<Usuario> trazer() {
//        int indexUserLogado = serviceUser.getIndexUsuario();
//        if (indexUserLogado < 0) {
//            return ResponseEntity.status(404).build();
//        }
//        return ResponseEntity.status(200).body(clientes.get(indexUserLogado));
//    }


}
