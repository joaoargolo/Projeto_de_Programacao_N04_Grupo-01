package br.com.unit.service;

import br.com.unit.classes.*;
import br.com.unit.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepo;
    private final EspectadorRepository espectadorRepo;
    private final GerenteRepository gerenteRepo;
    private final CondutorRepository condutorRepo;
    private final StaffRepository staffRepo;

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

        if (usuario instanceof Espectador u) u.setSenha(novaSenha);
        if (usuario instanceof Gerente u) u.setSenha(novaSenha);
        if (usuario instanceof Condutor u) u.setSenha(novaSenha);
        if (usuario instanceof Staff u) u.setSenha(novaSenha);

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
