package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button myButton;

    @FXML
    protected void onButtonClick() {
        System.out.println("Botão clicado!");
    }
}
