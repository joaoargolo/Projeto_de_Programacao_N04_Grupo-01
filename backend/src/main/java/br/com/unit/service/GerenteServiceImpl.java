package br.com.unit.service;

import br.com.unit.classes.Gerente;
import br.com.unit.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Override
    public void createGerente(Gerente gerente) {
        boolean jaExiste = gerenteRepository.existsByEmailOrCpf(gerente.getEmail(), gerente.getCpf());

        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um gerente com este e-mail ou CPF!");
        }

        gerenteRepository.save(gerente);
    }

    public boolean autenticar(String email, String senha) {
        System.out.println("Senha digitada: " + senha);
        Gerente g = gerenteRepository.findByEmail(email).orElse(null);

        System.out.println("Senha no banco: " + (g != null ? g.getSenha() : "NÃO ACHOU"));

        return g != null && g.getSenha().equals(senha);
    }

    @Override
    public void updateGerente(int id, Gerente gerente) {
        if (!gerenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Gerente com ID " + id + " não encontrado!");
        }

        gerente.setIdGerente(id);
        gerenteRepository.save(gerente);
    }

    @Override
    public void deleteGerente(int id) {
        gerenteRepository.deleteById(id);
    }

    @Override
    public Collection<Gerente> getGerente() {
        List<Gerente> gerentes = gerenteRepository.findAll();
        return gerentes;
    }
}