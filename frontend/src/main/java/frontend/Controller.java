package frontend;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import service.CadastroService;

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

        // funcoes disponiveis, da p mudar depois(obviamente)
        listaFuncoes.add("Espectador");
        listaFuncoes.add("Gerente");
        listaFuncoes.add("Staff");

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
        String email = EmailCadastroField.getText();
        String senha = SenhaCadastroField.getText();
        String cpf = CpfField.getText();
        String funcao = FuncaoCadastroField.getValue();
        String dataNasc = (DataNascField.getValue() != null) ? DataNascField.getValue().toString() : "";

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty() || funcao == null
                || dataNasc.isEmpty()) {
            System.out.println("Campo vazio detectado!");
            NomeCadastroField.setText("Nenhum campo pode estar vazio");
            NomeCadastroField.setStyle("-fx-border-color : red");
            return;
        }

        if (!Validador.validarEmail(email)) {
            EmailCadastroField.setText("Email inválido!");
            EmailCadastroField.setStyle("-fx-border-color : red");
            return;
        } else if (!Validador.validarSenha(senha)) {
            SenhaCadastroField.setText("Senha inválida!");
            SenhaCadastroField.setStyle("-fx-border-color : red");
            return;
        } else if (!Validador.validarCPF(cpf)) {
            CpfField.setText("CPF inválido!");
            CpfField.setStyle("-fx-border-color : red");
            return;
        }
        switch (funcao) {
            case "Espectador" -> {
                try {
                    var response = CadastroService.postCadastro(nome, cpf, email, senha, dataNasc, "espectador");
                    if (response.statusCode() == 200 || response.statusCode() == 201) {
                        System.out.println("Cadastro de espectador realizado com sucesso!");
                        IrParaLogin();
                        LimparFields();
                    } else {
                        System.out.println("Erro ao cadastrar espectador: " + response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "Gerente" -> {
                try {
                    var response = CadastroService.postCadastro(nome, cpf, email, senha, dataNasc, "gerente");
                    if (response.statusCode() == 200 || response.statusCode() == 201) {
                        System.out.println("Cadastro de gerente realizado com sucesso!");
                        IrParaLogin();
                        LimparFields();
                    } else {
                        System.out.println("Erro ao cadastrar gerente: " + response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "Staff" -> {
                try {
                    var response = CadastroService.postCadastroStaff(
                            nome, cpf, email, senha, dataNasc,
                            "Especialização padrão", "Evento X");
                    if (response.statusCode() == 200 || response.statusCode() == 201) {
                        System.out.println("Cadastro de staff realizado com sucesso!");
                        IrParaLogin();
                        LimparFields();
                    } else {
                        System.out.println("Erro ao cadastrar staff: " + response.body());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            default -> System.out.println("Função inválida!");
        }
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