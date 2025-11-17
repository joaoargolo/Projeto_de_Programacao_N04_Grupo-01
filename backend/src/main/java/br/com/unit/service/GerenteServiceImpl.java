package br.com.unit.service;

import br.com.unit.classes.Gerente;
import br.com.unit.classes.Evento;
import br.com.unit.repository.GerenteRepository;
import br.com.unit.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public void createGerente(Gerente gerente) {
        boolean jaExiste = gerenteRepository.existsByEmailOrCpf(gerente.getEmail(), gerente.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um gerente com este e-mail ou CPF!");
        }
        
        String senhaCriptografada = passwordService.criptografar(gerente.getSenha());
        gerente.setSenha(senhaCriptografada);
       
        if (gerente.getEventosGerenciados() != null && !gerente.getEventosGerenciados().isEmpty()) {
            // validate events
            java.util.List<Evento> eventosValidados = gerente.getEventosGerenciados().stream()
                    .map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!")))
                    .toList();

            gerente.setEventosGerenciados(new java.util.ArrayList<>(eventosValidados));

            // save gerente to obtain id
            Gerente gerenteSalvo = gerenteRepository.save(gerente);

            // set gerente as owner in each event
            for (Evento ev : eventosValidados) {
                ev.setGerente(gerenteSalvo);
                eventoRepository.save(ev);
            }
            return;
        }

        gerenteRepository.save(gerente);
    }

    @Override
    @Transactional
    public void updateGerente(int id, Gerente gerenteAtualizado) {
        Gerente gerenteExistente = gerenteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Gerente com ID " + id + " não encontrado!"));

        gerenteExistente.setNome(gerenteAtualizado.getNome());
        gerenteExistente.setEmail(gerenteAtualizado.getEmail());
        gerenteExistente.setCpf(gerenteAtualizado.getCpf());
        
        if (gerenteAtualizado.getSenha() != null && !gerenteAtualizado.getSenha().isEmpty()) {
            if (!passwordService.jaEstaCriptografada(gerenteAtualizado.getSenha())) {
                String senhaCriptografada = passwordService.criptografar(gerenteAtualizado.getSenha());
                gerenteExistente.setSenha(senhaCriptografada);
            } else {
                gerenteExistente.setSenha(gerenteAtualizado.getSenha());
            }
        }
        
        gerenteExistente.setDataNasc(gerenteAtualizado.getDataNasc());
        gerenteExistente.setTelefone(gerenteAtualizado.getTelefone());
        gerenteExistente.setPerfil(gerenteAtualizado.getPerfil());

        // handle events association (if provided)
        if (gerenteAtualizado.getEventosGerenciados() != null) {
            java.util.List<Evento> eventosValidados = gerenteAtualizado.getEventosGerenciados().isEmpty()
                    ? java.util.Collections.emptyList()
                    : gerenteAtualizado.getEventosGerenciados().stream()
                    .map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!")))
                    .toList();

            java.util.List<Evento> eventosAntigos = gerenteExistente.getEventosGerenciados();
            if (eventosAntigos == null) eventosAntigos = java.util.Collections.emptyList();

            java.util.Set<Integer> oldIds = new java.util.HashSet<>();
            for (Evento ev : eventosAntigos) if (ev.getIdEvento() != null) oldIds.add(ev.getIdEvento());

            java.util.Set<Integer> newIds = new java.util.HashSet<>();
            for (Evento ev : eventosValidados) if (ev.getIdEvento() != null) newIds.add(ev.getIdEvento());

            // remove gerente from old events not in new set
            for (Evento evAnt : eventosAntigos) {
                if (!newIds.contains(evAnt.getIdEvento())) {
                    evAnt.setGerente(null);
                    eventoRepository.save(evAnt);
                }
            }

            // add gerente to new events
            for (Evento evNovo : eventosValidados) {
                if (!oldIds.contains(evNovo.getIdEvento())) {
                    evNovo.setGerente(gerenteExistente);
                    eventoRepository.save(evNovo);
                }
            }

            gerenteExistente.setEventosGerenciados(new java.util.ArrayList<>(eventosValidados));
        }

        gerenteRepository.save(gerenteExistente);
    }

    @Override
    @Transactional
    public void deleteGerente(int id) {
        if (!gerenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Gerente com ID " + id + " não encontrado!");
        }
        gerenteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Gerente> getGerente() {
        return gerenteRepository.findAll();
    }
}