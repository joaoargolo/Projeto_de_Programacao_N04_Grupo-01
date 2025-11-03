package br.com.unit.service;

import br.com.unit.classes.Evento;
import br.com.unit.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public void createEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    @Override
    public void updateEvento(int id, Evento evento) {
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("Evento com ID " + id + " não encontrado!");
        }
        evento.setIdEvento(id);
        eventoRepository.save(evento);
    }

    @Override
    public void deleteEvento(int id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public Collection<Evento> getEvento() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos;
    }
}