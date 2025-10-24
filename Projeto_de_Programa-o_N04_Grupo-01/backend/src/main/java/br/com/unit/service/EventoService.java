package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Evento;

public interface EventoService {
    public abstract void createEvento(Evento evento);
    public abstract void updateEvento(int id, Evento evento);
    public abstract void deleteEvento(int id);
    public abstract Collection<Evento> getEvento();
}