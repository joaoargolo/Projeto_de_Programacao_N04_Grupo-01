package frontend;

public class Validador {

    public static boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validarSenha(String senha) {
        return senha.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
    }

    public static boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}");
    }
}
