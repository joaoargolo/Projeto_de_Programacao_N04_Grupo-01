package br.com.unit.service;

import br.com.unit.classes.Staff;
import br.com.unit.classes.Evento;
import br.com.unit.repository.StaffRepository;
import br.com.unit.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    @Transactional
    public void createStaff(Staff staff) {
        boolean jaExiste = staffRepository.existsByEmailOrCpf(staff.getEmail(), staff.getCpf());
        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um staff com este e-mail ou CPF!");
        }

        if (staff.getEventosAuxiliados() != null) {
                List<Evento> eventosValidados = staff.getEventosAuxiliados().stream().map(e -> {
                    if (e.getIdEvento() == null) {
                        throw new IllegalArgumentException("ID do evento não pode ser nulo!");
                    }
                    return eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!"));
                }).toList();
                staff.setEventosAuxiliados(eventosValidados);

        } else {
            staff.setEventosAuxiliados(null);
        }

        staffRepository.save(staff);
    }

    @Override
    @Transactional
    public void updateStaff(int id, Staff staffAtualizado) {
        Staff staffExistente = staffRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Staff com ID " + id + " não encontrado!"));

        staffExistente.setNome(staffAtualizado.getNome());
        staffExistente.setEmail(staffAtualizado.getEmail());
        staffExistente.setCpf(staffAtualizado.getCpf());
        staffExistente.setSenha(staffAtualizado.getSenha());
        staffExistente.setDataNasc(staffAtualizado.getDataNasc());
        staffExistente.setTelefone(staffAtualizado.getTelefone());
        staffExistente.setPerfil(staffAtualizado.getPerfil());
        staffExistente.setEspecializacao(staffAtualizado.getEspecializacao());

        if (staffAtualizado.getEventosAuxiliados() != null && !staffAtualizado.getEventosAuxiliados().isEmpty()) {
            List<Evento> eventosValidados = staffAtualizado.getEventosAuxiliados().stream().map(e -> eventoRepository.findById(e.getIdEvento()).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + e.getIdEvento() + " não encontrado!"))).toList();
            staffExistente.setEventosAuxiliados(eventosValidados);
        } else {
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
}
