package br.com.unit.service;

import br.com.unit.classes.Espectador;
import br.com.unit.repository.EspectadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    public List<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }

    public void createEspectador(Espectador espectador) {
        espectadorRepository.save(espectador);
    }

    public void updateEspectador(int id, Espectador novoEspectador) {
        Espectador espectador = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado"));
        espectador.setNome(novoEspectador.getNome());
        espectador.setCpf(novoEspectador.getCpf());
        espectador.setEmail(novoEspectador.getEmail());
        espectador.setSenha(novoEspectador.getSenha());
        espectador.setDataNasc(novoEspectador.getDataNasc());
        espectadorRepository.save(espectador);
    }

    public void deleteEspectador(int id) {
        espectadorRepository.deleteById(id);
    }

    // 🔍 Buscar por e-mail
    public Optional<Espectador> buscarPorEmail(String email) {
        return espectadorRepository.findByEmail(email);
    }

    // 🔐 Validar login (e-mail + senha)
    public boolean validarLogin(String email, String senha) {
        Optional<Espectador> espectador = espectadorRepository.findByEmail(email);
        return espectador.isPresent() && espectador.get().getSenha().equals(senha);
    }
}
