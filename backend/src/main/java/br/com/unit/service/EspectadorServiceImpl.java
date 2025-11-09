package br.com.unit.service;

import br.com.unit.classes.Espectador;
import br.com.unit.repository.EspectadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    @Override
    public void createEspectador(Espectador espectador) {
        espectadorRepository.save(espectador);
    }

    @Override
    public void updateEspectador(int id, Espectador espectador) {
        Espectador existente = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado"));
        existente.setNome(espectador.getNome());
        existente.setCpf(espectador.getCpf());
        existente.setEmail(espectador.getEmail());
        existente.setSenha(espectador.getSenha());
        existente.setDataNasc(espectador.getDataNasc());
        espectadorRepository.save(existente);
    }

    @Override
    public void deleteEspectador(int id) {
        espectadorRepository.deleteById(id);
    }

    @Override
    public Collection<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }

    // 🔍 Buscar por e-mail
    @Override
    public Optional<Espectador> buscarPorEmail(String email) {
        return espectadorRepository.findByEmail(email);
    }

    // 🔐 Validar login (email + senha)
    @Override
    public boolean validarLogin(String email, String senha) {
        Optional<Espectador> espectador = espectadorRepository.findByEmail(email);
        return espectador.isPresent() && espectador.get().getSenha().equals(senha);
    }
}
