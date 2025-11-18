package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unit.classes.Evento;
import br.com.unit.repository.EspectadorRepository;
import br.com.unit.repository.EventoRepository;
import org.springframework.stereotype.Service;
import br.com.unit.classes.Espectador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public void createEspectador(Espectador espectador) {
        boolean jaExiste = espectadorRepository.existsByEmailOrCpf(espectador.getEmail(), espectador.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        String senhaCriptografada = passwordService.criptografar(espectador.getSenha());
        espectador.setSenha(senhaCriptografada);

        espectador.setStatus(Espectador.Status.INATIVO);
        espectadorRepository.save(espectador);
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return espectadorRepository.findByEmail(email)
                .map(e -> {

                    return passwordService.verificar(senha, e.getSenha());
                })
                .orElse(false);
    }

    @Override
    public Espectador getByEmail(String email) {
        return espectadorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado com o email: " + email));
    }

    @Override

    @Transactional
    public void updateEspectador(int id, Espectador espectadorAtualizado) {
        Espectador espectadorExistente = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador com ID " + id + " não encontrado!"));

        espectadorExistente.setNome(espectadorAtualizado.getNome());
        espectadorExistente.setEmail(espectadorAtualizado.getEmail());
        espectadorExistente.setCpf(espectadorAtualizado.getCpf());

        if (espectadorAtualizado.getSenha() != null && !espectadorAtualizado.getSenha().isEmpty()) {
            if (!passwordService.jaEstaCriptografada(espectadorAtualizado.getSenha())) {
                String senhaCriptografada = passwordService.criptografar(espectadorAtualizado.getSenha());
                espectadorExistente.setSenha(senhaCriptografada);
            } else {
                espectadorExistente.setSenha(espectadorAtualizado.getSenha());
            }
        }

        espectadorExistente.setDataNasc(espectadorAtualizado.getDataNasc());
        espectadorExistente.setTelefone(espectadorAtualizado.getTelefone());
        espectadorExistente.setPerfil(espectadorAtualizado.getPerfil());

        espectadorExistente.atualizarStatus();
        espectadorRepository.save(espectadorExistente);
    }

    @Override
    @Transactional
    public void deleteEspectador(int id) {
        if (!espectadorRepository.existsById(id)) {
            throw new IllegalArgumentException("Espectador com ID " + id + " não encontrado!");
        }

        espectadorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }

    @Transactional
    public void participarDeEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(
                        () -> new IllegalArgumentException("Espectador com ID " + idEspectador + " não encontrado!"));
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com ID " + idEvento + " não encontrado!"));

        if (!espectador.getEventosDoEspectador().contains(evento)) {
            espectador.getEventosDoEspectador().add(evento);
            evento.getEspectadores().add(espectador);
        }

        espectador.atualizarStatus();
        espectadorRepository.save(espectador);
    }

    @Transactional
    public void sairDoEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(
                        () -> new IllegalArgumentException("Espectador com ID " + idEspectador + " não encontrado!"));
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com ID " + idEvento + " não encontrado!"));

        espectador.getEventosDoEspectador().remove(evento);
        evento.getEspectadores().remove(espectador);

        espectador.atualizarStatus();
        espectadorRepository.save(espectador);
    }
}