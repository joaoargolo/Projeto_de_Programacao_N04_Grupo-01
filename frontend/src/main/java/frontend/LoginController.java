package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField EmailLoginField;

    @FXML
    private TextField SenhaLoginField;

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

        if (email.isEmpty()) {
            EmailErrorLabel.setText("Por favor, insira um email.");

        } else if (!Validador.validarEmail(email)) {
            EmailErrorLabel.setText("Formatação errada de email. deve ser ex: teste@teste.com");
        }
        if (senha.isEmpty()) {
            SenhaErrorLabel.setText("Por favor, insira uma senha.");
        } else {
            SenhaErrorLabel.setText("");
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
