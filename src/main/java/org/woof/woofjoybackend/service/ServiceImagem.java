package org.woof.woofjoybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woof.woofjoybackend.domain.entity.DonoImagem;
import org.woof.woofjoybackend.domain.entity.Imagem;
import org.woof.woofjoybackend.repository.DonoImagemRepository;
import org.woof.woofjoybackend.repository.ImagemRepository;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Random;

@Service
public class ServiceImagem {

    private static final String BUCKET_NAME = "bucketwb";
    private static final int URL_EXPIRATION_TIME_SECONDS = 360000; // Tempo de expiração do URL em segundos (1 hora)
    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private DonoImagemRepository donoImagemRepository;


    public String gerarPalavraAleatoria() {
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder palavraAleatoria = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            palavraAleatoria.append(caracteres.charAt(indiceAleatorio));
        }
        return palavraAleatoria.toString();
    }
    public Imagem postImagem(Imagem imagem, Integer idDono) {
        DonoImagem dono = gerarDono(idDono);
        if (dono == null){
            return null;
        }
        imagem.setDono(dono);
        Imagem imgCadastrada = imagemRepository.save(imagem);
        return imgCadastrada;
    }

    public DonoImagem gerarDono(Integer idDono){
        DonoImagem donoImagem = donoImagemRepository.findById(idDono).get();
        return donoImagem;
    }


    public Imagem uploadDownloadImage(File file, String tipo, Integer idDono) {
        StsClient stsClient = StsClient.create();

        String roleArn = "arn:aws:iam::760533273170:role/LabRole";

        AssumeRoleResponse assumeRoleResponse = stsClient.assumeRole(AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .build());
        AwsCredentials temporaryCredentials = AwsBasicCredentials.create(
                assumeRoleResponse.credentials().accessKeyId(),
                assumeRoleResponse.credentials().secretAccessKey()
        );

        S3Client s3Client = S3Client.builder()
                .credentialsProvider(() -> temporaryCredentials)
                .build();

        //tipo = tipo.toLowerCase().trim();
        //if (tipo.equalsIgnoreCase("carrosel") || tipo.equalsIgnoreCase("perfil")){
            Imagem img = new Imagem();
            // img.setTipo(tipo);
            //Imagem imgCadastrada = postImagem(img, idDono);
            //if(imgCadastrada != null){
                String s3ObjectKey = "imagens/" + gerarPalavraAleatoria() + ".jpg";
                //imgCadastrada.setPath(s3ObjectKey);
                // Lógica para fazer upload da imagem para o S3
                s3Client.putObject(PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(s3ObjectKey)
                        .build(), file.toPath());
                //imgCadastrada.setUrlImagem(generatePresignedUrl(s3ObjectKey).toString());
                //imagemRepository.save(imgCadastrada);
                // Gerar URL assinado para a imagem
                //return imgCadastrada
                return img;
            //}
        //}

        //return null;
    }

    public Imagem atualizarURLImagem(Integer id, Integer idDono, Imagem imagem) {
       Imagem img = imagemRepository.findById(id).get();
       if (img != null){
           URL nova_url = generatePresignedUrl(imagem.getPath());
           img.setId(id);
           img.setUrlImagem(nova_url.toString());
           img.setDono(gerarDono(idDono));
           return imagemRepository.save(img);
       }
        return null;
    }

    private URL generatePresignedUrl(String objectKey) {
        S3Presigner presigner = S3Presigner.builder().build();

        // Substitua "REGION" pelo nome da região do seu bucket
        GetObjectPresignRequest getObjectRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofSeconds(URL_EXPIRATION_TIME_SECONDS))
                .getObjectRequest(builder ->
                        builder.bucket(BUCKET_NAME)
                                .key(objectKey))
                .build();

        URL presignedUrl = presigner.presignGetObject(getObjectRequest).url();

        // Fechar o presigner após o uso
        presigner.close();

        return presignedUrl;
    }
}