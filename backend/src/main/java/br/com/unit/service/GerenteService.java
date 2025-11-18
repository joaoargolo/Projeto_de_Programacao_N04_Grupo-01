package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Gerente;

public interface GerenteService {
    public abstract void createGerente(Gerente gerente);

    public abstract void updateGerente(int id, Gerente gerente);

    public abstract Gerente getByEmail(String email);

    public abstract void deleteGerente(int id);

    public abstract boolean autenticar(String email, String senha);

    public abstract Collection<Gerente> getGerente();

    Gerente buscarPorEmail(String email);

    void ativarGerente(int id);
}