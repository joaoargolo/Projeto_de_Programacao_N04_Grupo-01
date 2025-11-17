package br.com.unit.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unit.classes.Gerente;
import br.com.unit.repository.GerenteRepository;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private GerenteRepository repository;

    @Override
    public void createGerente(Gerente gerente) {
        repository.save(gerente);
    }

    @Override
    public void updateGerente(int id, Gerente gerente) {
        gerente.setIdGerente(id);
        repository.save(gerente);
    }

    @Override
    public void deleteGerente(int id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<Gerente> getGerente() {
        return repository.findAll();
    }

    @Override
    public Gerente buscarPorEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}
