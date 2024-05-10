package org.woof.woofjoybackend.s3connect.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.domain.entity.DonoImagem;
import org.woof.woofjoybackend.domain.entity.Imagem;
import org.woof.woofjoybackend.repository.DonoImagemRepository;
import org.woof.woofjoybackend.repository.ImagemRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;
    @Value("${application.bucket.url}")
    private String bucketUrl;
    private final AmazonS3 s3Client;

    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private DonoImagemRepository donoImagemRepository;

    public String uploadProfileImg(MultipartFile file, Integer idDono) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = "perfil_" + idDono;

        Imagem img = new Imagem();
        DonoImagem dono = donoImagemRepository.findById(idDono).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        img.setDono(dono);
        img.setUrlImagem(bucketUrl+fileName);
        img.setTipo("perfil");
        imagemRepository.save(img);

        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    public String uploadImg(MultipartFile file, Integer idDono) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = "img_" + idDono + "_"+1;
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public Boolean connectionHealthCheck(){
        if (s3Client == null){
            return false;
        }
        return true;
    }
}
