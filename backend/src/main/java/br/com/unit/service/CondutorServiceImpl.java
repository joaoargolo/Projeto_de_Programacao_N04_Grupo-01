package br.com.unit.service;

import java.util.Collection;
import java.util.List;

import br.com.unit.repository.CondutorRepository;
import br.com.unit.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import br.com.unit.classes.Condutor;
import br.com.unit.classes.Evento;

@Service
public class CondutorServiceImpl implements CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    @Transactional
    public void createCondutor(Condutor condutor) {
        boolean jaExiste = condutorRepository.existsByEmailOrCpf(condutor.getEmail(), condutor.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um condutor com este e-mail ou CPF!");
        }

        if (condutor.getEventosConduzidos() != null && !condutor.getEventosConduzidos().isEmpty()) {
            List<Evento> eventosValidados = condutor.getEventosConduzidos().stream().map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!"))).toList();
            condutor.setEventosConduzidos(eventosValidados);
        }

        condutorRepository.save(condutor);
    }

    @Override
    @Transactional
    public void updateCondutor(int id, Condutor condutorAtualizado) {
        Condutor condutorExistente = condutorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Condutor com ID " + id + " não encontrado!"));

        condutorExistente.setNome(condutorAtualizado.getNome());
        condutorExistente.setEmail(condutorAtualizado.getEmail());
        condutorExistente.setCpf(condutorAtualizado.getCpf());
        condutorExistente.setSenha(condutorAtualizado.getSenha());
        condutorExistente.setDataNasc(condutorAtualizado.getDataNasc());
        condutorExistente.setTelefone(condutorAtualizado.getTelefone());
        condutorExistente.setPerfil(condutorAtualizado.getPerfil());

        if (condutorAtualizado.getEventosConduzidos() != null && !condutorAtualizado.getEventosConduzidos().isEmpty()) {
            List<Evento> eventosValidados = condutorAtualizado.getEventosConduzidos().stream().map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!"))).toList();
            condutorExistente.setEventosConduzidos(eventosValidados);
        } else {
            condutorExistente.setEventosConduzidos(null);
        }

        condutorRepository.save(condutorExistente);
    }

    @Override
    @Transactional
    public void deleteCondutor(int id) {
        if (!condutorRepository.existsById(id)) {
            throw new IllegalArgumentException("Condutor com ID " + id + " não encontrado!");
        }
        condutorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Condutor> getCondutor() {
        return condutorRepository.findAll();
    }
}
