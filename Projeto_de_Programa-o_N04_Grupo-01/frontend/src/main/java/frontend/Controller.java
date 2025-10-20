package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button SubmitButton;
    @FXML
    private TextField NameField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField PasswordField;

    @FXML
    protected void onButtonClick() {

        System.out.println("Botão clicado!");

    }
}
