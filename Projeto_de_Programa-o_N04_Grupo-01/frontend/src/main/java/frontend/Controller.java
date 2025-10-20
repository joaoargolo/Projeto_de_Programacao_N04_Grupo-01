package frontend;

import java.util.List;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {
    @FXML
    private Button SubmitButton;
    @FXML
    private TextField NameField;
    @FXML
    private TextField EmailField;
    @FXML
    private VBOX Vbox;

    @FXML
    private TextField PasswordField;
    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    public void initialize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Teste1");
        list.add("Teste2");

        ChoiceBox.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    protected void onButtonClick() {
        String nome = NameField.getText();
        String email = EmailField.getText();
        String password = PasswordField.getText();
        System.out.println("Botão clicado!");
        System.out.println(nome.length());
        if (nome.length() < 12) {
            System.out.println("O tamanho do nome é muito pequeno!");
            NameField.setText("Valor inválido! , o nome deve ser maior que 12 caracteres");
            NameField.setStyle("-fx-border-color : red");
        }
        NameField.clear();
        EmailField.clear();
        PasswordField.clear();

    }
}
