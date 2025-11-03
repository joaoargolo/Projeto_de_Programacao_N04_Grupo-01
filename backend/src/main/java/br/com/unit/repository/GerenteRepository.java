package br.com.unit.repository;

import br.com.unit.classes.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {

    Optional<Gerente> findByEmail(String email);

    Optional<Gerente> findByCpf(String cpf);

    boolean existsByEmailOrCpf(String email, String cpf);
}