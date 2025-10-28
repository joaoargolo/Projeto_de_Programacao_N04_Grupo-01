package br.com.unit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import br.com.unit.classes.Staff;

@Service
public class StaffServiceImpl implements StaffService {

    private static Map<Integer, Staff> staffRepo = new HashMap<>();

    @Override
    public void createStaff(Staff staff){
        boolean jaExiste = staffRepo.values().stream()
                        .anyMatch(s -> s.getEmail().equalsIgnoreCase(staff.getEmail()) || s.getCpf().equals(staff.getCpf()));

        if (jaExiste){
            throw new IllegalArgumentException("Já existe um membro do staff com este e-mail ou CPF!");
        }

        staffRepo.put(staff.getIdStaff(), staff);
    }

    @Override
    public void updateStaff(int id, Staff staff) {
        staffRepo.remove(id);
        staff.setIdStaff(id);
        staffRepo.put(id, staff);
    }

    @Override
    public void deleteStaff(int id) {
        staffRepo.remove(id);
    }

    @Override
    public Collection<Staff> getStaff() {
        return staffRepo.values();
    }
}