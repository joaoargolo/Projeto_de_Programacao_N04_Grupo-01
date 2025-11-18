package frontend;

import java.util.List;
import java.lang.reflect.Array;
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
    private TextField SenhaLoginField;

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

    ArrayList<String> listCadastroFields = new ArrayList<>();

    @FXML
    public void initialize() {
        ArrayList<String> listaFuncoes = new ArrayList<>();

        listaFuncoes.add("Teste1");
        listaFuncoes.add("Teste2");

        FuncaoCadastroField.setItems(FXCollections.observableArrayList(listaFuncoes));
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

        listCadastroFields.clear();

        String nome = NomeCadastroField.getText();
        listCadastroFields.add(NomeCadastroField.getText());
        listCadastroFields.add(EmailCadastroField.getText());
        listCadastroFields.add(SenhaCadastroField.getText());
        listCadastroFields.add(CpfField.getText());
        listCadastroFields.add(FuncaoCadastroField.getValue());
        listCadastroFields.add(
                (DataNascField.getValue() != null) ? DataNascField.getValue().toString() : "");

        System.out.println("Botão clicado!");
        System.out.println(nome.length());

        for (String Campo : listCadastroFields) {
            if (Campo == null || Campo.isEmpty()) {
                System.out.println("Campo vazio detectado!");
                NomeCadastroField.setText("Valor inválido! , nenhum campo pode estar vazio");
                NomeCadastroField.setStyle("-fx-border-color : red");
                return;
            }
        }
        if (!Validador.validarEmail(EmailCadastroField.getText())) {
            EmailCadastroField.setText("Email inválido!");
            EmailCadastroField.setStyle("-fx-border-color : red");
            return;
        } else if (!Validador.validarSenha(SenhaCadastroField.getText())) {
            SenhaCadastroField.setText("Senha inválida!");
            SenhaCadastroField.setStyle("-fx-border-color : red");
            return;
        } else if (!Validador.validarCPF(CpfField.getText())) {
            CpfField.setText("CPF inválido!");
            CpfField.setStyle("-fx-border-color : red");
            return;
        }

        System.out.println("Todos os campos preenchidos!");
        IrParaLogin();
        LimparFields();
    }

    @FXML
    protected void EntrarClick() {
        String email = EmailLoginField.getText();
        String senha = SenhaLoginField.getText();
        if (email.isEmpty() || senha.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        System.out.println("Botão de login clicado!");
        EmailLoginField.clear();
        SenhaLoginField.clear();
    }
}
