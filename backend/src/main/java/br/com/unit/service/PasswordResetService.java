package br.com.unit.service;

import br.com.unit.classes.Condutor;
import br.com.unit.classes.Espectador;
import br.com.unit.classes.Gerente;
import br.com.unit.classes.PasswordResetToken;
import br.com.unit.classes.Staff;
import br.com.unit.repository.CondutorRepository;
import br.com.unit.repository.EspectadorRepository;
import br.com.unit.repository.GerenteRepository;
import br.com.unit.repository.PasswordResetTokenRepository;
import br.com.unit.repository.StaffRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepo;
    private final EspectadorRepository espectadorRepo;
    private final GerenteRepository gerenteRepo;
    private final CondutorRepository condutorRepo;
    private final StaffRepository staffRepo;
    private final PasswordService passwordService;
    private final EmailService emailService;



    public String gerarToken(Integer userId, String tipo) {

        tokenRepo.deleteByUsuarioIdAndUsuarioTipo(userId, tipo);

        PasswordResetToken token = new PasswordResetToken();
        token.setUsuarioId(userId);
        token.setUsuarioTipo(tipo);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiration(LocalDateTime.now().plusMinutes(30));

        tokenRepo.save(token);

        return token.getToken();
    }


    public Object validarToken(String tokenStr) {
        PasswordResetToken token = tokenRepo.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (token.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        return buscarUsuario(token.getUsuarioId(), token.getUsuarioTipo());
    }


    public void resetarSenha(String tokenStr, String novaSenha) {

        PasswordResetToken token = tokenRepo.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (token.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        Object usuario = buscarUsuario(token.getUsuarioId(), token.getUsuarioTipo());

        String senhaCriptografada = passwordService.criptografar(novaSenha);

        if (usuario instanceof Espectador u) u.setSenha(senhaCriptografada);
        if (usuario instanceof Gerente u) u.setSenha(senhaCriptografada);
        if (usuario instanceof Condutor u) u.setSenha(senhaCriptografada);
        if (usuario instanceof Staff u) u.setSenha(senhaCriptografada);

        salvarUsuario(usuario);

        tokenRepo.delete(token);
    }

    private Object buscarUsuario(Integer id, String tipo) {
        return switch (tipo) {
            case "ESPECTADOR" -> espectadorRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Espectador não encontrado"));
            case "GERENTE" -> gerenteRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
            case "CONDUTOR" -> condutorRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Condutor não encontrado"));
            case "STAFF" -> staffRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Staff não encontrado"));
            default -> throw new RuntimeException("Tipo desconhecido");
        };
    }


    private void salvarUsuario(Object usuario) {
        if (usuario instanceof Espectador u) espectadorRepo.save(u);
        if (usuario instanceof Gerente u) gerenteRepo.save(u);
        if (usuario instanceof Condutor u) condutorRepo.save(u);
        if (usuario instanceof Staff u) staffRepo.save(u);
    }
}
