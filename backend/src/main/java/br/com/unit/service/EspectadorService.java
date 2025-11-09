package br.com.unit.service;

import java.util.Collection;
import java.util.Optional;

import br.com.unit.classes.Espectador;

public interface EspectadorService {
    public abstract void createEspectador(Espectador espectador);
    public abstract void updateEspectador(int id, Espectador espectador);
    public abstract void deleteEspectador(int id);
    public abstract Collection<Espectador> getEspectador();

    // 🔍 Novo método: buscar por e-mail
    public abstract Optional<Espectador> buscarPorEmail(String email);

    // 🔐 Novo método: validar login
    public abstract boolean validarLogin(String email, String senha);
}

