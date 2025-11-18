package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Gerente;

public interface GerenteService {
    void createGerente(Gerente gerente);
    void updateGerente(int id, Gerente gerente);
    void deleteGerente(int id);
    Collection<Gerente> getGerente();
    Gerente buscarPorEmail(String email);
    void ativarGerente(int id);
}
