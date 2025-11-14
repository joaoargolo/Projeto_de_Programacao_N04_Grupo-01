package br.com.unit.service;

import java.util.Collection;
import java.util.Optional;
import br.com.unit.classes.Condutor;

public interface CondutorService {
    void createCondutor(Condutor condutor);
    void updateCondutor(int id, Condutor condutor);
    void deleteCondutor(int id);
    Collection<Condutor> getCondutor();
    Optional<Condutor> buscarPorEmail(String email);
}
