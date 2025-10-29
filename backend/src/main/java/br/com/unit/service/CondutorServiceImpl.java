package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unit.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.unit.classes.Condutor;

@Service
public class CondutorServiceImpl implements CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;


    @Override
    public void createCondutor(Condutor condutor){
        boolean jaExiste = condutorRepository.existsByEmailOrCpf(condutor.getEmail(), condutor.getCpf());

        if (jaExiste){
            throw new IllegalArgumentException("Já existe um condutor com este e-mail ou CPF!");
        }

        condutorRepository.save(condutor);
    }

    @Override
    public void updateCondutor(int id, Condutor condutor) {
        if (!condutorRepository.existsById(id)) {
            throw new IllegalArgumentException("Condutor com ID " + id + " não encontrado!");
        }

        condutor.setIdCondutor(id);
        condutorRepository.save(condutor);
    }

    @Override
    public void deleteCondutor(int id) {
        condutorRepository.deleteById(id);
    }

    @Override
    public Collection<Condutor> getCondutor() {
        List<Condutor> condutores = condutorRepository.findAll();
        return condutores;
    }

    @Override
    public void setId(int id, Condutor condutor) {
        condutor.setIdCondutor(id);
    }
}