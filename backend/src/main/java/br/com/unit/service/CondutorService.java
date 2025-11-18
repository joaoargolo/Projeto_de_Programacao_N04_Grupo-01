package br.com.unit.service;

import java.util.Collection;
import java.util.Optional;

import br.com.unit.classes.Condutor;

public interface CondutorService {
    public abstract void createCondutor(Condutor condutor);
    public abstract void updateCondutor(int id, Condutor condutor);
    public abstract void deleteCondutor(int id);
    public abstract Collection<Condutor> getCondutor();
    Optional<Condutor> buscarPorEmail(String email);
    void ativarCondutor(int id);
}