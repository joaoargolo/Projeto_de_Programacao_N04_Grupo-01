package br.com.unit.service;

import br.com.unit.classes.Espectador;
import br.com.unit.repository.EspectadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    // ✅ Encoder de senha do Spring Security
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Cria um novo espectador com a senha criptografada
     */
    @Override
    public void createEspectador(Espectador espectador) {
        espectador.setSenha(passwordEncoder.encode(espectador.getSenha()));
        espectadorRepository.save(espectador);
    }

    /**
     * Atualiza os dados de um espectador existente
     */
    @Override
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
    }

    /**
     * Remove um espectador
     */
    @Override
    public void deleteEspectador(int id) {
        espectadorRepository.deleteById(id);
    }

    /**
     * Retorna todos os espectadores
     */
    @Override
    public Collection<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }

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
    }
}
