package org.woof.woofjoybackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.woof.woofjoybackend.dto.ImagemDTO;
import org.woof.woofjoybackend.dto.mapper.ImagemMapper;
import org.woof.woofjoybackend.entity.Imagem;
import org.woof.woofjoybackend.service.ServiceImagem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final ServiceImagem imageService;

    public ImagemController(ServiceImagem imageService) {
        this.imageService = imageService;
    }


    @PostMapping("/upload/{idDono}")
    public ResponseEntity<ImagemDTO> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer idDono, @RequestBody Imagem imagem) {
        try {
            File convertedFile = convertMultipartFileToFile(file);
            Imagem imagemSalva = imageService.uploadDownloadImage(convertedFile, imagem, idDono);
            return ResponseEntity.ok(ImagemMapper.toDTO(imagemSalva));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/atualizar-url-imagem/{id}/{idDono}")
    public ResponseEntity<ImagemDTO> attUrlImagem(@PathVariable Integer id, @PathVariable Integer idDono, @RequestBody Imagem imagem) {
        Imagem img = imageService.atualizarURLImagem(id, idDono, imagem);
        return img != null  ? ResponseEntity.ok(ImagemMapper.toDTO(img)):ResponseEntity.badRequest().build();
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        file.transferTo(convertedFile);
        return convertedFile;
    }

}
