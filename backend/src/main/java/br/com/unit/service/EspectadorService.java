package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Espectador;

public interface EspectadorService {

    void createEspectador(Espectador espectador);
    void updateEspectador(int id, Espectador espectador);
    void deleteEspectador(int id);

    Collection<Espectador> getEspectador();

    Espectador buscarPorEmail(String email);

    boolean autenticar(String email, String senha);

    Espectador getByEmail(String email);

    void participarDeEvento(int idEspectador, int idEvento);

    void sairDoEvento(int idEspectador, int idEvento);
    void ativarEspectador(int id);
}
