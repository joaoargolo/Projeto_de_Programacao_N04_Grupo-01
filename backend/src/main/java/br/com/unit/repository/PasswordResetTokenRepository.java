package br.com.unit.repository;

import br.com.unit.classes.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    void deleteByUsuarioIdAndUsuarioTipo(Integer usuarioId, String usuarioTipo);
}
