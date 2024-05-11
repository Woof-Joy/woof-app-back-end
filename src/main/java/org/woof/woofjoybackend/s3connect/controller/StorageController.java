package org.woof.woofjoybackend.s3connect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.woof.woofjoybackend.s3connect.service.StorageService;

@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService service;

    @GetMapping("/connection")
    public ResponseEntity<Boolean> connectionHealthCheck() {
        Boolean estado = service.connectionHealthCheck();
        return ResponseEntity.status(200).body(estado);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfileImg(@RequestParam(value = "file") MultipartFile file, @RequestParam Integer idDono) {
        return new ResponseEntity<>(service.uploadProfileImg
                (file, idDono), HttpStatus.OK);
    }

    @GetMapping("/url/{idDono}/{tipo}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String idDono, @PathVariable String tipo) {
//        byte[] data = service.getImgUrl(fileName);
//        ByteArrayResource resource = new ByteArrayResource(data);
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
//                .body(resource);
        return
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
}
