package frontend;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class PaginaUsuarioController {

    @FXML
    private Text pagUsuarioTexto;

    @FXML
    private Circle iconeUsuario;

    @FXML
    private Pane nomeCelula;
    @FXML
    private Pane cpfCelula;
    @FXML
    private Pane funcaoCelula;
    @FXML
    private Pane dataCelula;

    @FXML
    private Text textoNome;
    @FXML
    private Text textoCpf;
    @FXML
    private Text textoFuncao;
    @FXML
    private Text textoDataNascimento;

    @FXML
    private Pane ultimoEventoCelula;
    @FXML
    private Text nomeEvento;
    @FXML
    private Text descEvento;
    @FXML
    private Text dataInicioEvento;
    @FXML
    private Text dataEventoFim;

    @FXML
    public void initialize() {
        //sem funcao ainda
    }
}