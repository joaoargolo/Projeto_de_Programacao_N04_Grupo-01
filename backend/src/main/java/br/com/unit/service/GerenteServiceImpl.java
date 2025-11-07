package br.com.unit.service;

import br.com.unit.classes.Gerente;
import br.com.unit.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Override
    @Transactional
    public void createGerente(Gerente gerente) {
        boolean jaExiste = gerenteRepository.existsByEmailOrCpf(gerente.getEmail(), gerente.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um gerente com este e-mail ou CPF!");
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
        gerenteExistente.setSenha(gerenteAtualizado.getSenha());
        gerenteExistente.setDataNasc(gerenteAtualizado.getDataNasc());
        gerenteExistente.setTelefone(gerenteAtualizado.getTelefone());
        gerenteExistente.setPerfil(gerenteAtualizado.getPerfil());

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