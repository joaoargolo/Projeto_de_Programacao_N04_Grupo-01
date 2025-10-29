package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.unit.classes.Gerente;

@Service
public class GerenteServiceImpl implements GerenteService {

    private static Map<Integer, Gerente> gerenteRepo = new HashMap<>();

    @Override
    public void createGerente(Gerente gerente){
        boolean jaExiste = gerenteRepo.values().stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(gerente.getEmail()) || s.getCpf().equals(gerente.getCpf()));

        if (jaExiste){
            throw new IllegalArgumentException("Já existe um gerente com este e-mail ou CPF!");
        }

        gerenteRepo.put(gerente.getIdGerente(), gerente);
    }

    @Override
    public void updateGerente(int id, Gerente gerente) {
        gerenteRepo.remove(id);
        gerente.setIdGerente(id);
        gerenteRepo.put(id, gerente);
    }

    @Override
    public void deleteGerente(int id) {
        gerenteRepo.remove(id);
    }

    @Override
    public Collection<Gerente> getGerente() {
        return gerenteRepo.values();
    }
}