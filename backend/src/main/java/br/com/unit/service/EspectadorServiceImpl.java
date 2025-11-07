package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unit.repository.EspectadorRepository;
import org.springframework.stereotype.Service;
import br.com.unit.classes.Espectador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    @Override
    @Transactional
    public void createEspectador(Espectador espectador){
        boolean jaExiste = espectadorRepository.existsByEmailOrCpf(espectador.getEmail(), espectador.getCpf());
        if (jaExiste){
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        espectadorRepository.save(espectador);
    }

    @Override
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
}