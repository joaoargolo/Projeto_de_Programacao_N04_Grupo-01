package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.unit.classes.Evento;

@Service
public class EventoServiceImpl implements EventoService {

    private static Map<Integer, Evento> eventoRepo = new HashMap<>();

    @Override
    public void createEvento(Evento evento){
        eventoRepo.put(evento.getIdEvento(), evento);
    }

    @Override
    public void updateEvento(int id, Evento evento) {
        eventoRepo.remove(id);
        evento.setIdEvento(id);
        eventoRepo.put(id, evento);
    }

    @Override
    public void deleteEvento(int id) {eventoRepo.remove(id);
    }

    @Override
    public Collection<Evento> getEvento() {
        return eventoRepo.values();
    }
}