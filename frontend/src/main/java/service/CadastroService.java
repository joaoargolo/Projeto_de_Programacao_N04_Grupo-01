package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CadastroService {
        private static final String BASE_URL = "http://localhost:8080";
        private static final HttpClient client = HttpClient.newHttpClient();

        public static HttpResponse<String> postCadastro(
                        String nome,
                        String cpf,
                        String email,
                        String senha,
                        String dataNasc,
                        String tipo) throws Exception {

                String endpoint = switch (tipo) {
                        case "espectador" -> "/espectadores/criar";
                        case "gerente" -> "/gerentes/criar";
                        case "staff" -> "/staff/criar";
                        case "condutor" -> "/condutores/criar";
                        default -> throw new IllegalArgumentException("Tipo inválido para cadastro simples: " + tipo);
                };

                String json = String.format(
                                "{\"nome\":\"%s\", \"cpf\":\"%s\", \"email\":\"%s\", \"senha\":\"%s\", \"dataNasc\":\"%s\"}",
                                nome, cpf, email, senha, dataNasc);

                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + endpoint))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(json))
                                .build();

                return client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        public static HttpResponse<String> postCadastroStaff(
                        String nome,
                        String cpf,
                        String email,
                        String senha,
                        String dataNasc,
                        String especializacao,
                        String eventoAuxiliado) throws Exception {

                String json = String.format(
                                "{\"nome\":\"%s\", \"cpf\":\"%s\", \"email\":\"%s\", \"senha\":\"%s\", \"dataNasc\":\"%s\", \"especializacao\":\"%s\", \"eventoAuxiliado\":\"%s\"}",
                                nome, cpf, email, senha, dataNasc, especializacao, eventoAuxiliado);

                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + "/staff/criar"))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(json))
                                .build();

                return client.send(request, HttpResponse.BodyHandlers.ofString());
        }
}
