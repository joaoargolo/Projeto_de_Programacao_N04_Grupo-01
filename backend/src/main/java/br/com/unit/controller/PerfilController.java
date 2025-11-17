package br.com.unit.controller;

import br.com.unit.classes.Espectador;
import br.com.unit.repository.EspectadorRepository;
import br.com.unit.service.FotoPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private FotoPerfilService fotoPerfilService;

    @Autowired
    private EspectadorRepository espectadorRepository;

    @PostMapping("/uploadFoto")
    public ResponseEntity<String> uploadFotoPerfil(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("userId") Integer userId) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo não enviado.");
        }

        Optional<Espectador> espectadorOpt = espectadorRepository.findById(userId);
        if (espectadorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        String nomeOriginal = file.getOriginalFilename();
        String extensao = "";
        if (nomeOriginal != null && nomeOriginal.contains(".")) {
            extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf('.'));
        }
        if (extensao.isBlank()) {
            extensao = ".png";
        }

        String nomeArquivo = "user_" + userId + "_profile_pic" + extensao;

        try {
            fotoPerfilService.salvarFoto(file, nomeArquivo);

            Espectador espectador = espectadorOpt.get();
            espectador.setPerfil(nomeArquivo);
            espectadorRepository.save(espectador);

            return ResponseEntity.ok("Upload realizado com sucesso.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no upload da foto.");
        }
    }
}
