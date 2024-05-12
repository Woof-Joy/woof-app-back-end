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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.woof.woofjoybackend.configuration.security.jwt.GerenciadorTokenJwt;
import org.woof.woofjoybackend.domain.entity.DonoImagem;
import org.woof.woofjoybackend.domain.entity.Imagem;
import org.woof.woofjoybackend.domain.entity.Usuario;
import org.woof.woofjoybackend.repository.DonoImagemRepository;
import org.woof.woofjoybackend.repository.ImagemRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        fileName = "perfil_" + idDono + fileExtension;

        List<Imagem> imagemExistenteOptional = imagemRepository.findByDono_IdAndTipo(idDono, "perfil");
        if (!imagemExistenteOptional.isEmpty()) {
            Imagem imagemExistente = imagemExistenteOptional.get(0);
            String urlImagemExistente = imagemExistente.getUrlImagem();

            String existingFileName = urlImagemExistente.substring(urlImagemExistente.lastIndexOf("/") + 1);
            s3Client.deleteObject(bucketName, existingFileName);

            imagemRepository.delete(imagemExistente);
        }

        Imagem img = new Imagem();
        DonoImagem dono = donoImagemRepository.findById(idDono).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        img.setDono(dono);
        img.setUrlImagem(bucketUrl + fileName);
        img.setTipo("perfil");
        imagemRepository.save(img);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    public String uploadImg(MultipartFile file, Integer idDono) {
        File fileObj = convertMultiPartFileToFile(file);

        List<Imagem> imagemExistenteList = imagemRepository.findByDono_IdAndTipo(idDono, "img");
        String fileName = "img_" + idDono + "_" + (imagemExistenteList.size() + 1);

        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));

        fileObj.delete();

        Imagem img = new Imagem();
        DonoImagem dono = donoImagemRepository.findById(idDono).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        img.setDono(dono);
        img.setUrlImagem(bucketUrl + fileName);
        img.setTipo("img");
        imagemRepository.save(img);

        return "Imagem salva: " + fileName;
    }


    public List<String> getImgUrl(Integer idDono, String tipo) {
        List<Imagem> imagens = imagemRepository.findByDono_IdAndTipo(idDono, tipo);
        List<String> urls = new ArrayList<>();
        for (Imagem i : imagens) {
            urls.add(i.getUrlImagem());
        }
        return urls;
    }

    public String deleteFile(String fileName, Integer idDono) {
        Optional<Imagem> imagemOptional = imagemRepository.findByUrlImagem(bucketUrl + fileName);
        if (imagemOptional.isPresent()) {
            Imagem imagem = imagemOptional.get();
            if (imagem.getDono().getId() == idDono) {
                imagemRepository.delete(imagem);
                s3Client.deleteObject(bucketName, fileName);
            } else {
                return "Não autorizado: Você não tem permissão para deletar essa imagem";
            }
        } else {
            return "Imagem não encontrada no banco de dados";
        }

        return fileName + " removida do S3 e do banco";
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

    public Boolean connectionHealthCheck() {
        if (s3Client == null) {
            return false;
        }
        return true;
    }
}
