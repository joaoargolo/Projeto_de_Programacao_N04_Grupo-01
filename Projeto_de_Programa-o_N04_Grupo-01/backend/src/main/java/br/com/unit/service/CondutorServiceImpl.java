package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.unit.classes.Condutor;

@Service
public class CondutorServiceImpl implements CondutorService {

    private static Map<Integer, Condutor> condutorRepo = new HashMap<>();

    @Override
    public void createCondutor(Condutor condutor){
        boolean jaExiste = condutorRepo.values().stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(condutor.getEmail()) || s.getCpf().equals(condutor.getCpf()));

        if (jaExiste){
            throw new IllegalArgumentException("Já existe um condutor com este e-mail ou CPF!");
        }

        condutorRepo.put(condutor.getIdCondutor(), condutor);
    }

    @Override
    public void updateCondutor(int id, Condutor condutor) {
        condutorRepo.remove(id);
        condutor.setIdCondutor(id);
        condutorRepo.put(id, condutor);
    }

    @Override
    public void deleteCondutor(int id) {
        condutorRepo.remove(id);
    }

    @Override
    public Collection<Condutor> getCondutor() {
        return condutorRepo.values();
    }
}