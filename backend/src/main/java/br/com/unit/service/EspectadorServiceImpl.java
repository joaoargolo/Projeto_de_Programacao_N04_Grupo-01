package br.com.unit.service;

<<<<<<< HEAD
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
=======
import br.com.unit.classes.Espectador;
import br.com.unit.repository.EspectadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
>>>>>>> 790a6f9bb50b4c4f62bd6a5ba0b1123470d8f939

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

<<<<<<< HEAD
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    @Transactional
    public void createEspectador(Espectador espectador){
        boolean jaExiste = espectadorRepository.existsByEmailOrCpf(espectador.getEmail(), espectador.getCpf());
        if (jaExiste){
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        espectador.setStatus(Espectador.Status.INATIVO);
=======
    // ✅ Encoder de senha do Spring Security
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Cria um novo espectador com a senha criptografada
     */
    @Override
    public void createEspectador(Espectador espectador) {
        espectador.setSenha(passwordEncoder.encode(espectador.getSenha()));
>>>>>>> 790a6f9bb50b4c4f62bd6a5ba0b1123470d8f939
        espectadorRepository.save(espectador);
    }

    /**
     * Atualiza os dados de um espectador existente
     */
    @Override
<<<<<<< HEAD
    @Transactional
    public void updateEspectador(int id, Espectador espectadorAtualizado) {
        Espectador espectadorExistente = espectadorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Espectador com ID " + id + " não encontrado!"));

        espectadorExistente.setNome(espectadorAtualizado.getNome());
        espectadorExistente.setEmail(espectadorAtualizado.getEmail());
        espectadorExistente.setCpf(espectadorAtualizado.getCpf());
        espectadorExistente.setSenha(espectadorAtualizado.getSenha());
        espectadorExistente.setDataNasc(espectadorAtualizado.getDataNasc());
        espectadorExistente.setTelefone(espectadorAtualizado.getTelefone());
        espectadorExistente.setPerfil(espectadorAtualizado.getPerfil());

        espectadorExistente.atualizarStatus();
        espectadorRepository.save(espectadorExistente);
=======
    public void updateEspectador(int id, Espectador espectador) {
        Espectador existente = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado"));

        existente.setNome(espectador.getNome());
        existente.setCpf(espectador.getCpf());
        existente.setEmail(espectador.getEmail());
        existente.setDataNasc(espectador.getDataNasc());

        // Criptografa nova senha (se fornecida)
        if (espectador.getSenha() != null && !espectador.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(espectador.getSenha()));
        }

        espectadorRepository.save(existente);
>>>>>>> 790a6f9bb50b4c4f62bd6a5ba0b1123470d8f939
    }

    /**
     * Remove um espectador
     */
    @Override
    @Transactional
    public void deleteEspectador(int id) {
        if (!espectadorRepository.existsById(id)) {
            throw new IllegalArgumentException("Espectador com ID " + id + " não encontrado!");
        }

        espectadorRepository.deleteById(id);
    }

    /**
     * Retorna todos os espectadores
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }

<<<<<<< HEAD
    @Transactional
    public void participarDeEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(() -> new IllegalArgumentException("Espectador com ID " + idEspectador + " não encontrado!"));
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
                .orElseThrow(() -> new IllegalArgumentException("Espectador com ID " + idEspectador + " não encontrado!"));
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com ID " + idEvento + " não encontrado!"));

        espectador.getEventosDoEspectador().remove(evento);
        evento.getEspectadores().remove(espectador);

        espectador.atualizarStatus();
        espectadorRepository.save(espectador);
=======
    /**
     * Busca espectador por e-mail
     */
    @Override
    public Optional<Espectador> buscarPorEmail(String email) {
        return espectadorRepository.findByEmail(email);
    }

    /**
     * Valida login comparando senha informada com a senha criptografada
     */
    @Override
    public boolean validarLogin(String email, String senha) {
        Optional<Espectador> espectador = espectadorRepository.findByEmail(email);
        return espectador.isPresent() &&
                passwordEncoder.matches(senha, espectador.get().getSenha());
    }

    /**
     * Gera token de redefinição de senha e define tempo de expiração
     */
    public String gerarTokenRedefinicao(String email) {
        Espectador espectador = espectadorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado."));

        String token = UUID.randomUUID().toString();
        espectador.setResetToken(token);
        espectador.setTokenExpiration(LocalDateTime.now().plusMinutes(15)); // expira em 15 min
        espectadorRepository.save(espectador);

        return token; // em produção você enviaria por e-mail
    }

    /**
     * Atualiza senha com base no token de redefinição
     */
    public boolean atualizarSenhaComToken(String token, String novaSenha) {
        Optional<Espectador> opt = espectadorRepository.findByResetToken(token);
        if (opt.isEmpty()) return false;

        Espectador espectador = opt.get();

        // Verifica expiração do token
        if (espectador.getTokenExpiration() == null ||
                espectador.getTokenExpiration().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Atualiza senha e limpa o token
        espectador.setSenha(passwordEncoder.encode(novaSenha));
        espectador.setResetToken(null);
        espectador.setTokenExpiration(null);
        espectadorRepository.save(espectador);
        return true;
>>>>>>> 790a6f9bb50b4c4f62bd6a5ba0b1123470d8f939
    }
}