package br.com.unit.service;

import java.util.Collection;

import br.com.unit.classes.Espectador;
import br.com.unit.classes.Evento;
import br.com.unit.classes.Pessoa;
import br.com.unit.repository.EspectadorRepository;
import br.com.unit.repository.EventoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EspectadorServiceImpl implements EspectadorService {

    @Autowired
    private EspectadorRepository espectadorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public void createEspectador(Espectador espectador) {
        boolean jaExiste = espectadorRepository.existsByEmailOrCpf(espectador.getEmail(), espectador.getCpf());

        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um espectador com este e-mail ou CPF!");
        }

        espectador.atualizarStatusAutomaticamente();
        espectadorRepository.save(espectador);
    }


    @Override
    public boolean autenticar(String email, String senha) {
        return espectadorRepository.findByEmail(email)
                .map(e -> e.getSenha().equals(senha))
                .orElse(false);
    }

    @Override
    public Espectador getByEmail(String email) {
        return espectadorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado com o email: " + email));
    }

    @Override
    public Espectador buscarPorEmail(String email) {
        return espectadorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado com o email: " + email));
    }

    @Override
    public void updateEspectador(int id, Espectador dados) {
        Espectador existente = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado!"));

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setCpf(dados.getCpf());
        existente.setSenha(dados.getSenha());
        existente.setDataNasc(dados.getDataNasc());
        existente.setTelefone(dados.getTelefone());
        existente.setPerfil(dados.getPerfil());
        existente.atualizarStatusAutomaticamente();

        espectadorRepository.save(existente);
    }


    @Override
    public void deleteEspectador(int id) {
        Espectador espectador = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador com ID " + id + " não encontrado!"));

        // Delete lógico: muda status para INATIVO
        espectador.setStatus(Pessoa.Status.INATIVO);

        espectadorRepository.save(espectador);
    }

    @Override
    public void ativarEspectador(int id) {
        Espectador espectador = espectadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Espectador com ID " + id + " não encontrado!"));

        if (espectador.getNome() != null && espectador.getCpf() != null
                && espectador.getEmail() != null && espectador.getSenha() != null
                && espectador.getDataNasc() != null) {

            espectador.setStatus(Pessoa.Status.ATIVO);
        } else {
            espectador.setStatus(Pessoa.Status.PENDENTE_DE_CONFIRMACAO);
        }

        espectadorRepository.save(espectador);
    }


    @Override
    public Collection<Espectador> getEspectador() {
        return espectadorRepository.findAll();
    }

    // -------------------------------
    // PARTICIPAÇÃO EM EVENTOS
    // -------------------------------

    @Override
    public void participarDeEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado!"));

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));

        espectador.getEventosDoEspectador().add(evento);

        espectadorRepository.save(espectador);
    }

    @Override
    public void sairDoEvento(int idEspectador, int idEvento) {
        Espectador espectador = espectadorRepository.findById(idEspectador)
                .orElseThrow(() -> new IllegalArgumentException("Espectador não encontrado!"));

        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado!"));

        espectador.getEventosDoEspectador().remove(evento);

        espectadorRepository.save(espectador);
    }
}
