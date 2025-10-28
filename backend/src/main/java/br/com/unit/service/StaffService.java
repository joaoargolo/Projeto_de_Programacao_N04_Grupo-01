package br.com.unit.service;

import java.util.Collection;
import br.com.unit.classes.Staff;

public interface StaffService {
    public abstract void createStaff(Staff staff);
    public abstract void updateStaff(int id, Staff staff);
    public abstract void deleteStaff(int id);
    public abstract Collection<Staff> getStaff();
}