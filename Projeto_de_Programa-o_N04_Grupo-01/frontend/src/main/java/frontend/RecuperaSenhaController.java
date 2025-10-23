package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RecuperaSenhaController {

    @FXML
    private TextField RecuperaEmailField;

    @FXML
    private Button RecuperaEnviaBotao;

    @FXML
    private Button RecuperaCancelaBotao;

    @FXML
    private Label EmailErrorLabel;

    @FXML
    public void initialize() {
        // sem uso por enquanto
    }

    @FXML
    protected void handleRecuperaEnviaBotaoClick() {
        String email = RecuperaEmailField.getText();
        if (email.isEmpty()) {
            EmailErrorLabel.setText("Por favor, insira um email.");
        } else {

            System.out.println("Email de recuperação enviado para: " + email);
            EmailErrorLabel.setText("Email de recuperação enviado!");
        }
    }

    @FXML
    protected void handleRecuperaCancelaBotaoClick() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            AnchorPane root = loader.load();
            Stage stage = (Stage) RecuperaCancelaBotao.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}