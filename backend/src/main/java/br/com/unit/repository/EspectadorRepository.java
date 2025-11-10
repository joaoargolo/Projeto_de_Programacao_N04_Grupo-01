package br.com.unit.repository;

import br.com.unit.classes.Espectador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspectadorRepository extends JpaRepository<Espectador, Integer> {

    Optional<Espectador> findByEmail(String email);

    Optional<Espectador> findByCpf(String cpf);

    boolean existsByEmailAndSenha(String email, String senha);

    boolean existsByEmailOrCpf(String email, String cpf);
}