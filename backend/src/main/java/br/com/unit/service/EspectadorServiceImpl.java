package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.unit.classes.Espectador;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    private static Map<Integer, Espectador> espectadorRepo = new HashMap<>();

    @Override
    public void createEspectador(Espectador espectador){
        boolean jaExiste = espectadorRepo.values().stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(espectador.getEmail()) || s.getCpf().equals(espectador.getCpf()));

        if (jaExiste){
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        espectadorRepo.put(espectador.getIdEspectador(), espectador);
    }

    @Override
    public void updateEspectador(int id, Espectador espectador) {
        espectadorRepo.remove(id);
        espectador.setIdEspectador(id);
        espectadorRepo.put(id, espectador);
    }

    @Override
    public void deleteEspectador(int id) {
        espectadorRepo.remove(id);
    }

    @Override
    public Collection<Espectador> getEspectador() {
        return espectadorRepo.values();
    }
}