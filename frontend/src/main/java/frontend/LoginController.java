package frontend;

import dto.PaginaUsuarioDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.LoginService;
import service.UsuarioService;

public class LoginController {
    @FXML
    private TextField EmailLoginField;

    @FXML
    private PasswordField SenhaLoginField;

    @FXML
    private Button EntrarBotao;

    @FXML
    private Button CriarContaBotao;

    @FXML
    private Button EsqueciSenhaBotao;

    @FXML
    private Label EmailErrorLabel;

    @FXML
    private Label SenhaErrorLabel;

    @FXML
    public void initialize() {
        // sem uso por enquanto
    }

    @FXML
    protected void EntrarClick() {
        String email = EmailLoginField.getText();
        String senha = SenhaLoginField.getText();

        boolean camposValidos = true;

        if (email.isEmpty()) {
            EmailErrorLabel.setText("Por favor, insira um email.");
            camposValidos = false;
        } else if (!Validador.validarEmail(email)) {
            EmailErrorLabel.setText("Formatação errada de email. Ex: teste@teste.com");
            camposValidos = false;
        } else {
            EmailErrorLabel.setText("");
        }

        if (senha.isEmpty()) {
            SenhaErrorLabel.setText("Por favor, insira uma senha.");
            camposValidos = false;
        } else {
            SenhaErrorLabel.setText("");
        }

        if (!camposValidos)
            return;

        try {
            var response = LoginService.postLogin(email, senha);
            if (response.statusCode() == 200) {
                System.out.println("Login bem-sucedido!");

                try {

                    String responseBody = response.body();
                    System.out.println("JSON recebido do backend: " + responseBody);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaUsuario.fxml"));
                    Pane paginaUsuarioPane = loader.load();

                    PaginaUsuarioController usuarioController = loader.getController();

                    PaginaUsuarioDTO usuario = UsuarioService.parseUsuarioJson(responseBody);
                    usuarioController.carregarUsuario(usuario);

                    Stage stage = (Stage) EntrarBotao.getScene().getWindow();
                    Scene scene = new Scene(paginaUsuarioPane);
                    stage.setScene(scene);
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmailLoginField.clear();
        SenhaLoginField.clear();
    }

    @FXML
    protected void CriarContaClick() {
        try {
            Pane newPane = FXMLLoader.load(getClass().getResource("interface.fxml"));
            Scene newScene = new Scene(newPane);
            Stage currentStage = (Stage) CriarContaBotao.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void EsqueciSenhaClick() {
        try {
            Pane newPane = FXMLLoader.load(getClass().getResource("recuperasenha.fxml"));
            Scene newScene = new Scene(newPane);
            Stage currentStage = (Stage) EsqueciSenhaBotao.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
