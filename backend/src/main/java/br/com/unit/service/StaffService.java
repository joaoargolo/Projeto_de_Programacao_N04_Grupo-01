package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Staff;

public interface StaffService {
    void createStaff(Staff staff);
    void updateStaff(int id, Staff staff);
    void deleteStaff(int id);
    Collection<Staff> getStaff();
    Staff buscarPorEmail(String email);
    void ativarStaff(int id);
}
