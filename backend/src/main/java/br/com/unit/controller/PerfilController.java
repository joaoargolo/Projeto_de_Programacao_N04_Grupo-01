package br.com.unit.controller;

import br.com.unit.classes.Espectador;
import br.com.unit.classes.Gerente;
import br.com.unit.classes.Condutor;
import br.com.unit.classes.Staff;
import br.com.unit.repository.CondutorRepository;
import br.com.unit.repository.EspectadorRepository;
import br.com.unit.repository.GerenteRepository;
import br.com.unit.repository.StaffRepository;
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

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private StaffRepository staffRepository;

    @PostMapping("/uploadFoto")
    public ResponseEntity<String> uploadFotoPerfil(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("userId") Integer userId,
                                                   @RequestParam(value = "tipo", defaultValue = "espectador") String tipo) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo não enviado.");
        }

        String tipoLower = tipo.toLowerCase();

        String nomeOriginal = file.getOriginalFilename();
        String extensao = "";
        if (nomeOriginal != null && nomeOriginal.contains(".")) {
            extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf('.'));
        }
        if (extensao.isBlank()) {
            extensao = ".png";
        }

        String prefixo = tipoLower;
        String nomeArquivo = prefixo + "_" + userId + "_profile_pic" + extensao;

        try {
            fotoPerfilService.salvarFoto(file, nomeArquivo);

            switch (tipoLower) {
                case "espectador" -> {
                    Optional<Espectador> espectadorOpt = espectadorRepository.findById(userId);
                    if (espectadorOpt.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
                    }
                    Espectador espectador = espectadorOpt.get();
                    espectador.setPerfil(nomeArquivo);
                    espectadorRepository.save(espectador);
                }
                case "gerente" -> {
                    Optional<Gerente> gerenteOpt = gerenteRepository.findById(userId);
                    if (gerenteOpt.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
                    }
                    Gerente gerente = gerenteOpt.get();
                    gerente.setPerfil(nomeArquivo);
                    gerenteRepository.save(gerente);
                }
                case "condutor" -> {
                    Optional<Condutor> condutorOpt = condutorRepository.findById(userId);
                    if (condutorOpt.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
                    }
                    Condutor condutor = condutorOpt.get();
                    condutor.setPerfil(nomeArquivo);
                    condutorRepository.save(condutor);
                }
                case "staff" -> {
                    Optional<Staff> staffOpt = staffRepository.findById(userId);
                    if (staffOpt.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
                    }
                    Staff staff = staffOpt.get();
                    staff.setPerfil(nomeArquivo);
                    staffRepository.save(staff);
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de usuário inválido.");
                }
            }

            return ResponseEntity.ok("Upload realizado com sucesso.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no upload da foto.");
        }
    }
}
