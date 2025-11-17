package br.com.unit.service;

import java.util.Collection;
import java.util.List;

import br.com.unit.classes.Espectador;
import br.com.unit.classes.Evento;
import br.com.unit.repository.EspectadorRepository;
import br.com.unit.repository.EventoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    @Autowired
    private EventoRepository eventoRepository;


    @Override
    public void createEspectador(Espectador espectador) {
        boolean jaExiste = espectadorRepository.existsByEmailOrCpf(espectador.getEmail(), espectador.getCpf());

        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        espectadorRepository.save(espectador);
    }


    @Override
    public boolean autenticar(String email, String senha) {
        return espectadorRepository.findByEmail(email)
                .map(e -> e.getSenha().equals(senha))
                .orElse(false);
    }


    @Override
    public Espectador getByEmail(String email) {
        return espectadorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado com o email: " + email));
    }


    @Override
    public Espectador buscarPorEmail(String email) {
        return espectadorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado com o email: " + email));
    }


    @Override
    public void updateEspectador(int id, Espectador espectador) {
        if (!espectadorRepository.existsById(id)) {
            throw new IllegalArgumentException("Espectador com ID " + id + " não encontrado!");
        }

        espectador.setIdEspectador(id);
        espectadorRepository.save(espectador);
    }


    @Override
    public void deleteEspectador(int id) {
        espectadorRepository.deleteById(id);
    }


    @Override
    public Collection<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }



    @Override
    public void participarDeEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado!"));

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));

        espectador.getEventos().add(evento);
        espectadorRepository.save(espectador);
    }


    @Override
    public void sairDoEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado!"));

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));

        espectador.getEventos().remove(evento);
        espectadorRepository.save(espectador);
    }
}
