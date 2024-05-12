package org.woof.woofjoybackend.s3connect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.woof.woofjoybackend.configuration.security.jwt.GerenciadorTokenJwt;
import org.woof.woofjoybackend.domain.entity.Usuario;
import org.woof.woofjoybackend.s3connect.service.StorageService;
import org.woof.woofjoybackend.service.users.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService service;
    private final ServiceUser serviceUser;
    private final GerenciadorTokenJwt jwtTokenManager;

    @GetMapping("/connection")
    public ResponseEntity<Boolean> connectionHealthCheck() {
        Boolean estado = service.connectionHealthCheck();
        return ResponseEntity.status(200).body(estado);
    }

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<String> uploadProfileImg(@PathVariable String tipo, @RequestParam(value = "file") MultipartFile file, @RequestHeader("Authorization") String bearerToken) {
        String emailDono = jwtTokenManager.getUsernameFromToken(bearerToken.substring(7));
        Usuario dono = serviceUser.getByEmail(emailDono);

        if (tipo.equalsIgnoreCase("perfil")) {
            return new ResponseEntity<>(service.uploadProfileImg(file, dono.getDonoImagem().getId()), HttpStatus.OK);
        } else if (tipo.equalsIgnoreCase("img")){
            return new ResponseEntity<>(service.uploadImg(file, dono.getDonoImagem().getId()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/url/{tipo}")
    public ResponseEntity<List<String>> getImgUrl(@PathVariable String tipo, @RequestHeader("Authorization") String bearerToken) {
        String emailDono = jwtTokenManager.getUsernameFromToken(bearerToken.substring(7));
        Usuario dono = serviceUser.getByEmail(emailDono);
        return ResponseEntity.ok().body(service.getImgUrl(dono.getDonoImagem().getId(), tipo));
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName, @RequestHeader("Authorization") String bearerToken) {
        String emailDono = jwtTokenManager.getUsernameFromToken(bearerToken.substring(7));
        Usuario dono = serviceUser.getByEmail(emailDono);
        return new ResponseEntity<>(service.deleteFile(fileName, dono.getId()), HttpStatus.OK);
    }
}