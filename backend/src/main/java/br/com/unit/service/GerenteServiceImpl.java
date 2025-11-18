package br.com.unit.service;

import java.util.Collection;

import br.com.unit.classes.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unit.classes.Gerente;
import br.com.unit.repository.GerenteRepository;

@Service
public class GerenteServiceImpl implements GerenteService {

    @Autowired
    private GerenteRepository repository;

    @Autowired
    private PasswordService passwordService;

    @Override
    public void createGerente(Gerente gerente) {
        gerente.atualizarStatusAutomaticamente();
        repository.save(gerente);
    }

    @Override
    public void ativarGerente(int id) {
        Gerente gerente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gerente não encontrado!"));

        if (gerente.getNome() != null && gerente.getCpf() != null
                && gerente.getEmail() != null && gerente.getSenha() != null
                && gerente.getDataNasc() != null) {

            gerente.setStatus(Pessoa.Status.ATIVO);
        } else {
            gerente.setStatus(Pessoa.Status.PENDENTE_DE_CONFIRMACAO);
        }

        repository.save(gerente);
    }


    @Override
    public void updateGerente(int id, Gerente gerente) {
        Gerente existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gerente não encontrado!"));

        existente.setNome(gerente.getNome());
        existente.setCpf(gerente.getCpf());
        existente.setEmail(gerente.getEmail());
        existente.setSenha(gerente.getSenha());
        existente.setDataNasc(gerente.getDataNasc());
        existente.setTelefone(gerente.getTelefone());
        existente.setPerfil(gerente.getPerfil());

        existente.atualizarStatusAutomaticamente();

        repository.save(existente);
    }


    @Override
    public void deleteGerente(int id) {
        Gerente gerente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gerente não encontrado!"));

        gerente.desativar();
        repository.save(gerente);
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
