package br.com.unit.service;

import java.util.*;

import br.com.unit.classes.Pessoa;
import br.com.unit.repository.CondutorRepository;
import br.com.unit.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import br.com.unit.classes.Condutor;
import br.com.unit.classes.Evento;

@Service
public class CondutorServiceImpl implements CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    @Transactional
    public void createCondutor(Condutor condutor) {
        boolean jaExiste = condutorRepository.existsByEmailOrCpf(condutor.getEmail(), condutor.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um condutor com este e-mail ou CPF!");
        }

        condutor.atualizarStatusAutomaticamente();

        if (condutor.getEventosConduzidos() != null && !condutor.getEventosConduzidos().isEmpty()) {
            List<Evento> eventosValidados = condutor.getEventosConduzidos().stream().map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!"))).toList();
            condutor.setEventosConduzidos(new ArrayList<>(eventosValidados));

            // Salva o condutor para garantir ID
            Condutor condutorSalvo = condutorRepository.save(condutor);

            // Atualiza o lado dono (Evento.condutores)
            for (Evento ev : eventosValidados) {
                List<Condutor> listaCondutores = ev.getCondutores();
                if (listaCondutores == null) {
                    listaCondutores = new ArrayList<>();
                    ev.setCondutores(listaCondutores);
                }
                boolean presente = listaCondutores.stream().anyMatch(c -> c.getIdCondutor() != null && c.getIdCondutor().equals(condutorSalvo.getIdCondutor()));
                if (!presente) {
                    listaCondutores.add(condutorSalvo);
                    eventoRepository.save(ev);
                }
            }
            return;
        } else {
            condutor.setEventosConduzidos(null);
            condutorRepository.save(condutor);
        }
    }

    @Override
    @Transactional
    public void updateCondutor(int id, Condutor condutorAtualizado) {
        Condutor condutorExistente = condutorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Condutor com ID " + id + " não encontrado!"));

        condutorExistente.setNome(condutorAtualizado.getNome());
        condutorExistente.setEmail(condutorAtualizado.getEmail());
        condutorExistente.setCpf(condutorAtualizado.getCpf());
        condutorExistente.setSenha(condutorAtualizado.getSenha());
        condutorExistente.setDataNasc(condutorAtualizado.getDataNasc());
        condutorExistente.setTelefone(condutorAtualizado.getTelefone());
        condutorExistente.setPerfil(condutorAtualizado.getPerfil());
        condutorExistente.atualizarStatusAutomaticamente();

        if (condutorAtualizado.getEventosConduzidos() != null) {
            List<Evento> eventosValidados = condutorAtualizado.getEventosConduzidos().isEmpty()
                    ? Collections.emptyList()
                    : condutorAtualizado.getEventosConduzidos().stream()
                    .map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!")))
                    .toList();

            List<Evento> eventosAntigos = condutorExistente.getEventosConduzidos();
            if (eventosAntigos == null) eventosAntigos = Collections.emptyList();

            Set<Integer> oldIds = new HashSet<>();
            for (Evento ev : eventosAntigos) if (ev.getIdEvento() != null) oldIds.add(ev.getIdEvento());

            Set<Integer> newIds = new HashSet<>();
            for (Evento ev : eventosValidados) if (ev.getIdEvento() != null) newIds.add(ev.getIdEvento());

            // remove condutor from events that are no longer associated
            for (Evento evAnt : eventosAntigos) {
                if (!newIds.contains(evAnt.getIdEvento())) {
                    List<Condutor> cList = evAnt.getCondutores();
                    if (cList != null) {
                        cList.removeIf(c -> c.getIdCondutor() != null && c.getIdCondutor().equals(condutorExistente.getIdCondutor()));
                        eventoRepository.save(evAnt);
                    }
                }
            }

            // add condutor to new events
            for (Evento evNovo : eventosValidados) {
                if (!oldIds.contains(evNovo.getIdEvento())) {
                    List<Condutor> cList = evNovo.getCondutores();
                    if (cList == null) {
                        cList = new ArrayList<>();
                        evNovo.setCondutores(cList);
                    }
                    boolean presente = cList.stream().anyMatch(c -> c.getIdCondutor() != null && c.getIdCondutor().equals(condutorExistente.getIdCondutor()));
                    if (!presente) {
                        cList.add(condutorExistente);
                        eventoRepository.save(evNovo);
                    }
                }
            }

            condutorExistente.setEventosConduzidos(new ArrayList<>(eventosValidados));
        } else {
            List<Evento> eventosAntigos = condutorExistente.getEventosConduzidos();
            if (eventosAntigos != null) {
                for (Evento evAnt : eventosAntigos) {
                    List<Condutor> cList = evAnt.getCondutores();
                    if (cList != null) {
                        cList.removeIf(c -> c.getIdCondutor() != null && c.getIdCondutor().equals(condutorExistente.getIdCondutor()));
                        eventoRepository.save(evAnt);
                    }
                }
            }
            condutorExistente.setEventosConduzidos(null);
        }

        condutorRepository.save(condutorExistente);
    }

    @Override
    @Transactional
    public void deleteCondutor(int id) {
        Condutor condutor = condutorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado!"));

        condutor.desativar();
        condutorRepository.save(condutor);
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Condutor> getCondutor() {
        return condutorRepository.findAll();
    }

    @Override
    public Optional<Condutor> buscarPorEmail(String email) {
        return condutorRepository.findByEmail(email);
    }

    @Override
    public void ativarCondutor(int id) {
        Condutor condutor = condutorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Condutor não encontrado!"));

        if (condutor.getNome() != null && condutor.getCpf() != null
                && condutor.getEmail() != null && condutor.getSenha() != null
                && condutor.getDataNasc() != null) {

            condutor.setStatus(Pessoa.Status.ATIVO);
        } else {
            condutor.setStatus(Pessoa.Status.PENDENTE_DE_CONFIRMACAO);
        }

        condutorRepository.save(condutor);
    }

}