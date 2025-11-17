package service;

import dto.PaginaUsuarioDTO;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UsuarioService {

    private static final String URL_BASE = "http://localhost:8080/usuario";

    public static PaginaUsuarioDTO parseUsuarioJson(String json) {
        PaginaUsuarioDTO dto = new PaginaUsuarioDTO();

        if (json == null || json.isEmpty()) {
            return dto;
        }

        json = json.replace("{", "").replace("}", "").replace("\"", "");

        String[] campos = json.split(",");

        for (String campo : campos) {
            String[] par = campo.split(":");
            if (par.length < 2)
                continue;

            String chave = par[0].trim();
            String valor = par[1].trim();

            switch (chave) {
                case "nome" -> dto.setNome(valor);
                case "cpf" -> dto.setCpf(valor);
                case "funcao" -> dto.setFuncao(valor);
                case "dataNascimento" -> dto.setDataNascimento(valor);
                case "nomeEvento" -> dto.setNomeEvento(valor);
                case "descricaoEvento" -> dto.setDescricaoEvento(valor);
                case "dataInicioEvento" -> dto.setDataInicioEvento(valor);
                case "dataFimEvento" -> dto.setDataFimEvento(valor);
            }
        }

        return dto;
    }

    public static PaginaUsuarioDTO getUsuario(String email) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL_BASE + "?email=" + email))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return parseUsuarioJson(response.body());
        } else {
            throw new RuntimeException("Erro ao buscar usuário: " + response.statusCode() + " - " + response.body());
        }
    }
}
