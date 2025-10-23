package frontend;

import java.util.List;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class Controller {
    @FXML
    private Button EntrarBotao;

    @FXML
    private Button CadastroBotao;

    @FXML
    private TextField NomeCadastroField;

    @FXML
    private TextField EmailLoginField;

    @FXML
    private TextField EmailCadastroField;

    @FXML
    private TextField SenhaCadastroField;

    @FXML
    private TextField CpfField;

    @FXML
    private ChoiceBox<String> FuncaoCadastroField;

    @FXML
    private DatePicker DataNascField;

    @FXML
    public void initialize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Teste1");
        list.add("Teste2");

        FuncaoCadastroField.setItems(FXCollections.observableArrayList(list));
    }

    private void LimparFields() {
        NomeCadastroField.clear();
        EmailCadastroField.clear();
        SenhaCadastroField.clear();
        CpfField.clear();
        FuncaoCadastroField.setValue(null);
        DataNascField.setValue(null);
    }

    private void IrParaLogin() {
        try {
            Parent PaginaLogin = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) CadastroBotao.getScene().getWindow();
            stage.setScene(new Scene(PaginaLogin));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void CadastroClick() {
        String nome = NomeCadastroField.getText();
        String email = EmailCadastroField.getText();
        String password = SenhaCadastroField.getText();
        String cpf = CpfField.getText();
        String funcao = FuncaoCadastroField.getValue();
        System.out.println("Botão clicado!");
        System.out.println(nome.length());
        if (nome.length() < 12) {
            System.out.println("O tamanho do nome é muito pequeno!");
            NomeCadastroField.setText("Valor inválido! , o nome deve ser maior que 12 caracteres");
            NomeCadastroField.setStyle("-fx-border-color : red");
        } else {
            IrParaLogin();
        }
        LimparFields();
    }

    @FXML
    protected void EntrarClick() {
        String email = EmailLoginField.getText();
        System.out.println("Botão de login clicado!");
        EmailLoginField.clear();
    }
}
