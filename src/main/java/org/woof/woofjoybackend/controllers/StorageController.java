package org.woof.woofjoybackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.woof.woofjoybackend.domain.entity.DonoImagem;
import org.woof.woofjoybackend.service.StorageService;
import org.woof.woofjoybackend.service.users.ServiceUser;

import java.util.List;

@RestController
@RequestMapping("/api/img")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService service;
    private final ServiceUser serviceUser;


    @GetMapping("/connection")
    public ResponseEntity<Boolean> connectionHealthCheck() {
        Boolean estado = service.connectionHealthCheck();
        return ResponseEntity.status(200).body(estado);
    }

    @PostMapping("/upload/{tipo}")
    public ResponseEntity<String> uploadProfileImg(@PathVariable String tipo, @RequestParam(value = "file") MultipartFile file, @RequestHeader("Authorization") String bearerToken) {
        DonoImagem dono = serviceUser.getDonoByToken(bearerToken);
        if (dono == null) {
            return ResponseEntity.notFound().build();
        }
        if (tipo.equalsIgnoreCase("perfil")) {
            return new ResponseEntity<>(service.uploadProfileImg(file, dono.getId()), HttpStatus.OK);
        } else if (tipo.equalsIgnoreCase("img")) {
            return new ResponseEntity<>(service.uploadImg(file, dono.getId()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/url/{tipo}")
    public ResponseEntity<List<String>> getImgUrl(@PathVariable String tipo, @RequestHeader("Authorization") String bearerToken) {
        DonoImagem dono = serviceUser.getDonoByToken(bearerToken);
        if (dono == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(service.getImgUrl(dono.getId(), tipo));
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName, @RequestHeader("Authorization") String bearerToken) {
        DonoImagem dono = serviceUser.getDonoByToken(bearerToken);
        if (dono == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(service.deleteFile(fileName, dono.getId()), HttpStatus.OK);
    }
}