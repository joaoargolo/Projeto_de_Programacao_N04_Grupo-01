package frontend;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import dto.PaginaUsuarioDTO;

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
        // sem função ainda
    }

    // ✅ Método que atualiza os campos da tela com os dados do DTO
    public void carregarUsuario(PaginaUsuarioDTO usuario) {
        if (usuario == null)
            return;

        textoNome.setText(usuario.getNome() != null ? usuario.getNome() : "—");
        textoCpf.setText(usuario.getCpf() != null ? usuario.getCpf() : "—");
        textoFuncao.setText(usuario.getFuncao() != null ? usuario.getFuncao() : "—");
        textoDataNascimento.setText(usuario.getDataNascimento() != null ? usuario.getDataNascimento() : "—");

        nomeEvento.setText(usuario.getNomeEvento() != null ? usuario.getNomeEvento() : "Nenhum evento");
        descEvento.setText(usuario.getDescricaoEvento() != null ? usuario.getDescricaoEvento() : "—");
        dataInicioEvento.setText(usuario.getDataInicioEvento() != null ? usuario.getDataInicioEvento() : "—");
        dataEventoFim.setText(usuario.getDataFimEvento() != null ? usuario.getDataFimEvento() : "—");
    }
}