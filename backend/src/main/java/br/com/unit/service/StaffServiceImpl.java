package br.com.unit.service;

import br.com.unit.classes.Staff;
import br.com.unit.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public void createStaff(Staff staff) {
        boolean jaExiste = staffRepository.existsByEmailOrCpf(staff.getEmail(), staff.getCpf());

        if (jaExiste) {
            throw new IllegalArgumentException("Já existe um staff com este e-mail ou CPF!");
        }

        staffRepository.save(staff);
    }

    @Override
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
        staffRepository.save(staffExistente);
    }

    @Override
    public void deleteStaff(int id) {
        if (!staffRepository.existsById(id)) {
            throw new IllegalArgumentException("Staff com ID " + id + " não encontrado!");
        }

        staffRepository.deleteById(id);
    }

    @Override
    public Collection<Staff> getStaff() {
        List<Staff> staffList = staffRepository.findAll();
        return staffList;
    }
}