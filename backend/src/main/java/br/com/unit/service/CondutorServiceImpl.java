package br.com.unit.service;

import java.util.*;

import br.com.unit.classes.Pessoa;
import br.com.unit.dto.CondutorInputDTO;
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

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public void createCondutor(CondutorInputDTO dto) {

        boolean jaExiste = condutorRepository.existsByEmailOrCpf(dto.getEmail(), dto.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um condutor com este e-mail ou CPF!");
        }

        Condutor condutor = new Condutor();
        condutor.setNome(dto.getNome());
        condutor.setCpf(dto.getCpf());
        condutor.setEmail(dto.getEmail());
        condutor.setSenha(passwordService.criptografar(dto.getSenha()));
        condutor.setDataNasc(dto.getDataNasc());
        condutor.setTelefone(dto.getTelefone());
        condutor.setPerfil(dto.getPerfil());

        // 🔥 status automático
        condutor.atualizarStatusAutomaticamente();

        // 🔥 converte IDs → Evento
        if (dto.getEventosConduzidos() != null && !dto.getEventosConduzidos().isEmpty()) {

            List<Evento> eventos = dto.getEventosConduzidos().stream()
                    .map(id -> eventoRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Evento com ID " + id + " não encontrado!")))
                    .toList();

            condutor.setEventosConduzidos(new ArrayList<>(eventos));
        }

        Condutor salvo = condutorRepository.save(condutor);

        // 🔥 atualiza o lado do Evento (bidirecional)
        if (salvo.getEventosConduzidos() != null) {
            for (Evento evento : salvo.getEventosConduzidos()) {
                if (evento.getCondutores() == null)
                    evento.setCondutores(new ArrayList<>());

                if (!evento.getCondutores().contains(salvo)) {
                    evento.getCondutores().add(salvo);
                    eventoRepository.save(evento);
                }
            }
        }
    }

    @Override
    @Transactional
    public void updateCondutor(int id, Condutor condutorAtualizado) {
        Condutor condutorExistente = condutorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Condutor com ID " + id + " não encontrado!"));

        condutorExistente.setNome(condutorAtualizado.getNome());
        condutorExistente.setEmail(condutorAtualizado.getEmail());
        condutorExistente.setCpf(condutorAtualizado.getCpf());
        
        if (condutorAtualizado.getSenha() != null && !condutorAtualizado.getSenha().isEmpty()) {
            if (!passwordService.jaEstaCriptografada(condutorAtualizado.getSenha())) {
                String senhaCriptografada = passwordService.criptografar(condutorAtualizado.getSenha());
                condutorExistente.setSenha(senhaCriptografada);
            } else {
                condutorExistente.setSenha(condutorAtualizado.getSenha());
            }
        }
        
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