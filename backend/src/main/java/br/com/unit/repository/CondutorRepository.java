package br.com.unit.repository;

import br.com.unit.classes.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Integer> {

    Optional<Condutor> findByEmail(String email);

    Optional<Condutor> findByCpf(String cpf);

    boolean existsByEmailOrCpf(String email, String cpf);
}

