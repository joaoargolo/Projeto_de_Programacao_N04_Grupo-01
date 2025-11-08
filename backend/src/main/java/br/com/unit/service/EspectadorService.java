package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Espectador;

public interface EspectadorService {
    public abstract void createEspectador(Espectador espectador);
    public abstract void updateEspectador(int id, Espectador espectador);
    public abstract void deleteEspectador(int id);
    public abstract Collection<Espectador> getEspectador();
    public abstract void participarDeEvento(int idEspectador, int idEvento);
    public abstract void sairDoEvento(int idEspectador, int idEvento);
}