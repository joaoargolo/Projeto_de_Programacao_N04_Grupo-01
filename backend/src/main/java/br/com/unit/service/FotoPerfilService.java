package br.com.unit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FotoPerfilService {

    @Value("${app.upload-dir}")
    private String uploadDir;

    public String salvarFoto(MultipartFile arquivo, String nomeArquivo) throws IOException {
        Path diretorioUpload = Paths.get(uploadDir);
        if (!Files.exists(diretorioUpload)) {
            Files.createDirectories(diretorioUpload);
        }

        Path caminhoArquivo = diretorioUpload.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

        return caminhoArquivo.toString();
    }
}
