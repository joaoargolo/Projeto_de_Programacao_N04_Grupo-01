package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unit.repository.EspectadorRepository;
import org.springframework.stereotype.Service;
import br.com.unit.classes.Espectador;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    private EspectadorRepository espectadorRepository;

    @Override
    public void createEspectador(Espectador espectador){
        boolean jaExiste = espectadorRepository.existsByEmailOrCpf(espectador.getEmail(), espectador.getCpf());

        if (jaExiste){
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        espectadorRepository.save(espectador);
    }

    @Override
    public void updateEspectador(int id, Espectador espectador) {
        if (!espectadorRepository.existsById(id)) {
            throw new IllegalArgumentException("Espectador com ID " + id + " não encontrado!");
        }

        espectador.setIdEspectador(id);
        espectadorRepository.save(espectador);
    }

    @Override
    public void deleteEspectador(int id) {
        espectadorRepository.deleteById(id);
    }

    @Override
    public Collection<Espectador> getEspectador() {
        List<Espectador> espectadores = espectadorRepository.findAll();
        return espectadores;
    }
}