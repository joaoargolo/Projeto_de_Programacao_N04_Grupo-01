package br.com.unit.service;

import java.util.Collection;
import java.util.Optional;

import br.com.unit.classes.Condutor;
import br.com.unit.dto.CondutorInputDTO;

public interface CondutorService {
    public abstract void createCondutor(CondutorInputDTO dto);
    public abstract void updateCondutor(int id, Condutor condutor);
    public abstract void deleteCondutor(int id);
    public abstract Collection<Condutor> getCondutor();
    Optional<Condutor> buscarPorEmail(String email);
    void ativarCondutor(int id);
    public abstract Condutor getByEmail(String email);
    public abstract boolean autenticar(String email, String senha);
}