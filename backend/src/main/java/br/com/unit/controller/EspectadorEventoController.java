package br.com.unit.controller;

import br.com.unit.dto.ParticipacaoEventoDTO;
import br.com.unit.service.EspectadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/espectadores/eventos")
public class EspectadorEventoController {

    @Autowired
    private EspectadorService espectadorService;

    //http://localhost:8080/espectadores/eventos/participar
    //{
    //  "idEspectador": 1,
    //  "idEvento": 2
    //}
    @PostMapping("/participar")
    public ResponseEntity<String> participar(@RequestBody ParticipacaoEventoDTO dto) {
        try {
            espectadorService.participarDeEvento(dto.getIdEspectador(), dto.getIdEvento());
            return ResponseEntity.ok("Espectador " + dto.getIdEspectador() + " adicionado ao evento " + dto.getIdEvento());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //http://localhost:8080/espectadores/eventos/sair
    //{
    //  "idEspectador": 1,
    //  "idEvento": 2
    //}
    @PostMapping("/sair")
    public ResponseEntity<String> sair(@RequestBody ParticipacaoEventoDTO dto) {
        try {
            espectadorService.sairDoEvento(dto.getIdEspectador(), dto.getIdEvento());
            return ResponseEntity.ok("Espectador " + dto.getIdEspectador() + " removido do evento " + dto.getIdEvento());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

