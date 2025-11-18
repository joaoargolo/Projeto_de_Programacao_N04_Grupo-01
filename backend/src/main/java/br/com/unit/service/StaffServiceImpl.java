package br.com.unit.service;

import br.com.unit.classes.Pessoa;
import br.com.unit.classes.Staff;
import br.com.unit.classes.Evento;
import br.com.unit.repository.StaffRepository;
import br.com.unit.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    @Transactional
    public void createStaff(Staff staff) {
        boolean jaExiste = staffRepository.existsByEmailOrCpf(staff.getEmail(), staff.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um staff com este e-mail ou CPF!");
        }

        String senhaCriptografada = passwordService.criptografar(staff.getSenha());
        staff.setSenha(senhaCriptografada);

        staff.atualizarStatusAutomaticamente();

        if (staff.getEventosAuxiliados() != null && !staff.getEventosAuxiliados().isEmpty()) {
            List<Evento> eventosValidados = staff.getEventosAuxiliados().stream()
                    .map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException(
                            "Evento com ID " + e.getIdEvento() + " não encontrado!")))
                    .toList();

            // Set no lado inverso da relação (usar lista mutável)
            staff.setEventosAuxiliados(new ArrayList<>(eventosValidados));

            // Primeiro salva o staff para garantir que ele tem ID
            Staff staffSalvo = staffRepository.save(staff);

            // Atualiza o lado dono (Evento.staffs) para incluir o staff salvo
            for (Evento ev : eventosValidados) {
                List<Staff> listaStaffs = ev.getStaffs();
                if (listaStaffs == null) {
                    listaStaffs = new ArrayList<>();
                    ev.setStaffs(listaStaffs);
                }
                boolean presente = listaStaffs.stream()
                        .anyMatch(s -> s.getIdStaff() != null && s.getIdStaff().equals(staffSalvo.getIdStaff()));
                if (!presente) {
                    listaStaffs.add(staffSalvo);
                    eventoRepository.save(ev);
                }
            }

            return;
        } else {
            staff.setEventosAuxiliados(null);
            staffRepository.save(staff);
        }
    }

    @Override
    public boolean autenticar(String email, String senha) {
        return staffRepository.findByEmail(email)
                .map(s -> {

                    return passwordService.verificar(senha, s.getSenha());
                })
                .orElse(false);
    }

    @Override
    public Staff getByEmail(String email) {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Staff não encontrado com o email: " + email));
    }

    @Override

    @Transactional
    public void updateStaff(int id, Staff staffAtualizado) {
        Staff staffExistente = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff com ID " + id + " não encontrado!"));

        staffExistente.setNome(staffAtualizado.getNome());
        staffExistente.setEmail(staffAtualizado.getEmail());
        staffExistente.setCpf(staffAtualizado.getCpf());

        if (staffAtualizado.getSenha() != null && !staffAtualizado.getSenha().isEmpty()) {
            if (!passwordService.jaEstaCriptografada(staffAtualizado.getSenha())) {
                String senhaCriptografada = passwordService.criptografar(staffAtualizado.getSenha());
                staffExistente.setSenha(senhaCriptografada);
            } else {
                staffExistente.setSenha(staffAtualizado.getSenha());
            }
        }

        staffExistente.setDataNasc(staffAtualizado.getDataNasc());
        staffExistente.setTelefone(staffAtualizado.getTelefone());
        staffExistente.setPerfil(staffAtualizado.getPerfil());
        staffExistente.setEspecializacao(staffAtualizado.getEspecializacao());
        staffExistente.atualizarStatusAutomaticamente();

        if (staffAtualizado.getEventosAuxiliados() != null) {
            // build validated events list (allow empty list)
            List<Evento> eventosValidados = staffAtualizado.getEventosAuxiliados().isEmpty()
                    ? Collections.emptyList()
                    : staffAtualizado.getEventosAuxiliados().stream()
                            .map(e -> eventoRepository.findById(e.getIdEvento())
                                    .orElseThrow(() -> new IllegalArgumentException(
                                            "Evento com ID " + e.getIdEvento() + " não encontrado!")))
                            .toList();

            List<Evento> eventosAntigos = staffExistente.getEventosAuxiliados();
            if (eventosAntigos == null)
                eventosAntigos = Collections.emptyList();

            Set<Integer> oldIds = new HashSet<>();
            for (Evento ev : eventosAntigos)
                if (ev.getIdEvento() != null)
                    oldIds.add(ev.getIdEvento());

            Set<Integer> newIds = new HashSet<>();
            for (Evento ev : eventosValidados)
                if (ev.getIdEvento() != null)
                    newIds.add(ev.getIdEvento());

            // remove staff from events that are no longer associated
            for (Evento evAnt : eventosAntigos) {
                if (!newIds.contains(evAnt.getIdEvento())) {
                    List<Staff> sList = evAnt.getStaffs();
                    if (sList != null) {
                        sList.removeIf(
                                s -> s.getIdStaff() != null && s.getIdStaff().equals(staffExistente.getIdStaff()));
                        eventoRepository.save(evAnt);
                    }
                }
            }

            // add staff to new events
            for (Evento evNovo : eventosValidados) {
                if (!oldIds.contains(evNovo.getIdEvento())) {
                    List<Staff> sList = evNovo.getStaffs();
                    if (sList == null) {
                        sList = new ArrayList<>();
                        evNovo.setStaffs(sList);
                    }
                    boolean presente = sList.stream().anyMatch(
                            s -> s.getIdStaff() != null && s.getIdStaff().equals(staffExistente.getIdStaff()));
                    if (!presente) {
                        sList.add(staffExistente);
                        eventoRepository.save(evNovo);
                    }
                }
            }

            staffExistente.setEventosAuxiliados(new ArrayList<>(eventosValidados));
        } else {
            // if null, remove from all old events
            List<Evento> eventosAntigos = staffExistente.getEventosAuxiliados();
            if (eventosAntigos != null) {
                for (Evento evAnt : eventosAntigos) {
                    List<Staff> sList = evAnt.getStaffs();
                    if (sList != null) {
                        sList.removeIf(
                                s -> s.getIdStaff() != null && s.getIdStaff().equals(staffExistente.getIdStaff()));
                        eventoRepository.save(evAnt);
                    }
                }
            }
            staffExistente.setEventosAuxiliados(null);
        }

        staffRepository.save(staffExistente);
    }

    @Override
    @Transactional
    public void deleteStaff(int id) {
        if (!staffRepository.existsById(id)) {
            throw new IllegalArgumentException("Staff com ID " + id + " não encontrado!");
        }
        staffRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Staff> getStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff buscarPorEmail(String email) {
        return staffRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void ativarStaff(int id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff não encontrado!"));

        if (staff.getNome() != null && staff.getCpf() != null
                && staff.getEmail() != null && staff.getSenha() != null
                && staff.getDataNasc() != null) {

            staff.setStatus(Pessoa.Status.ATIVO);
        } else {
            staff.setStatus(Pessoa.Status.PENDENTE_DE_CONFIRMACAO);
        }

        staffRepository.save(staff);
    }
}
