package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MenuEventosController {

    @FXML
    private Pane paginaUsuarioPane;
    @FXML
    private Pane paginaEventoPane;

    @FXML
    private Text usuarioTextoPane;
    @FXML
    private Text eventoTextoPane;

    @FXML
    private Pane eventoCelula;
    @FXML
    private Text NomeEventosCelula;
    @FXML
    private Text descEventoCelula;
    @FXML
    private Button botaoEventoCelula;

    @FXML
    public void initialize() {

        System.out.println("Tela de eventos carregada com sucesso!");

        if (botaoEventoCelula != null) {
            botaoEventoCelula.setOnAction(event -> {
                System.out.println("Botão de evento clicado!");
            });
        }
    }
}