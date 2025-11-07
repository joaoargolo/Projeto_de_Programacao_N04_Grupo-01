package br.com.unit.service;

import br.com.unit.classes.Evento;
import br.com.unit.classes.Staff;
import br.com.unit.classes.Condutor;
import br.com.unit.classes.Gerente;
import br.com.unit.repository.EventoRepository;
import br.com.unit.repository.StaffRepository;
import br.com.unit.repository.CondutorRepository;
import br.com.unit.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private GerenteRepository gerenteRepository;

    @Override
    @Transactional
    public void createEvento(Evento evento) {
        if (evento.getGerente() == null || evento.getGerente().getIdGerente() == null) {
            throw new IllegalArgumentException("É necessário associar um gerente ao evento!");
        }

        Gerente gerente = gerenteRepository.findById(evento.getGerente().getIdGerente()).orElseThrow(() -> new IllegalArgumentException("Gerente não encontrado!"));
        evento.setGerente(gerente);

        if (evento.getStaffs() != null && !evento.getStaffs().isEmpty()) {
            List<Staff> staffs = new ArrayList<>();
            for (Staff staff : evento.getStaffs()) {
                Staff staffEncontrado = staffRepository.findById(staff.getIdStaff()).orElseThrow(() -> new IllegalArgumentException("Staff com ID " + staff.getIdStaff() + " não encontrado!"));
                staffs.add(staffEncontrado);
            }
            evento.setStaffs(staffs);
        }

        if (evento.getCondutores() != null && !evento.getCondutores().isEmpty()) {
            List<Condutor> condutores = new ArrayList<>();
            for (Condutor condutor : evento.getCondutores()) {
                Condutor condutorEncontrado = condutorRepository.findById(condutor.getIdCondutor()).orElseThrow(() -> new IllegalArgumentException("Condutor com ID " + condutor.getIdCondutor() + " não encontrado!"));
                condutores.add(condutorEncontrado);
            }
            evento.setCondutores(condutores);
        }

        eventoRepository.save(evento);
    }

    @Override
    @Transactional
    public void updateEvento(int id, Evento eventoAtualizado) {
        Evento eventoExistente = eventoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Evento com ID " + id + " não encontrado!"));

        eventoExistente.setNomeEvento(eventoAtualizado.getNomeEvento());
        eventoExistente.setDescricaoEvento(eventoAtualizado.getDescricaoEvento());
        eventoExistente.setDataInicio(eventoAtualizado.getDataInicio());
        eventoExistente.setDataFim(eventoAtualizado.getDataFim());
        eventoExistente.setCapacidade(eventoAtualizado.getCapacidade());

        if (eventoAtualizado.getGerente() != null && eventoAtualizado.getGerente().getIdGerente() != null) {
            Gerente gerente = gerenteRepository.findById(eventoAtualizado.getGerente().getIdGerente()).orElseThrow(() -> new IllegalArgumentException("Gerente não encontrado!"));
            eventoExistente.setGerente(gerente);
        }

        if (eventoAtualizado.getStaffs() != null) {
            List<Staff> staffsAtualizados = new ArrayList<>();
            for (Staff s : eventoAtualizado.getStaffs()) {
                Staff staffBanco = staffRepository.findById(s.getIdStaff()).orElseThrow(() -> new IllegalArgumentException("Staff com ID " + s.getIdStaff() + " não encontrado!"));
                staffsAtualizados.add(staffBanco);
            }
            eventoExistente.setStaffs(staffsAtualizados);
        }

        if (eventoAtualizado.getCondutores() != null) {
            List<Condutor> condutoresAtualizados = new ArrayList<>();
            for (Condutor c : eventoAtualizado.getCondutores()) {
                Condutor condutorBanco = condutorRepository.findById(c.getIdCondutor()).orElseThrow(() -> new IllegalArgumentException("Condutor com ID " + c.getIdCondutor() + " não encontrado!"));
                condutoresAtualizados.add(condutorBanco);
            }
            eventoExistente.setCondutores(condutoresAtualizados);
        }

        eventoRepository.save(eventoExistente);
    }


    @Override
    @Transactional
    public void deleteEvento(int id) {
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("Evento com ID " + id + " não encontrado!");
        }

        eventoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Evento> getEvento() {
        return eventoRepository.findAll();
    }
}