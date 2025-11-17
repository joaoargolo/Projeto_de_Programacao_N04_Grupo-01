package br.com.unit.service;

import java.util.Collection;

import br.com.unit.classes.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unit.classes.Staff;
import br.com.unit.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository repository;

    @Override
    public void createStaff(Staff staff) {
        repository.save(staff);
    }

    @Override
    public void updateStaff(int id, Staff staff) {
        staff.setIdStaff(id);
        repository.save(staff);
    }

    @Override
    public void deleteStaff(int id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<Staff> getStaff() {
        return repository.findAll();
    }

    @Override
    public Staff buscarPorEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public void ativarStaff(int id) {
        Staff staff = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff não encontrado!"));

        if (staff.getNome() != null && staff.getCpf() != null
                && staff.getEmail() != null && staff.getSenha() != null
                && staff.getDataNasc() != null) {

            staff.setStatus(Pessoa.Status.ATIVO);
        } else {
            staff.setStatus(Pessoa.Status.PENDENTE_DE_CONFIRMACAO);
        }

        repository.save(staff);
    }

}
