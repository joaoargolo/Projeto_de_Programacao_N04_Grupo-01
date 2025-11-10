package br.com.unit.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public String criptografar(String senhaTextoPlano) {
        if (senhaTextoPlano == null || senhaTextoPlano.isEmpty()) {
            throw new IllegalArgumentException("Senha não deve ser vazia");
        }
        return encoder.encode(senhaTextoPlano);
    }

    public boolean verificar(String senhaTextoPlano, String senhaCriptografada) {
        if (senhaTextoPlano == null || senhaCriptografada == null) {
            return false;
        }
        return encoder.matches(senhaTextoPlano, senhaCriptografada);
    }

    public boolean jaEstaCriptografada(String senha) {
        if (senha == null) return false;
        return senha.matches("^\\$2[aby]\\$\\d{2}\\$.{53}$");
    }
}
