package br.com.unit.repository;

import br.com.unit.classes.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    StaffRepository extends JpaRepository<Staff, Integer> {

    Optional<Staff> findByEmail(String email);
    boolean existsByEmailOrCpf(String email, String cpf);
}