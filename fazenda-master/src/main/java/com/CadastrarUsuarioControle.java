package com;

/**
 *
 * @author End User
 */
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import modelo.Usuario;
import util.Dao;

public class CadastrarUsuarioControle {
    @FXML
    private TextField campoIdentificacao; 
    @FXML
    private TextField campoNomeCompleto;  
    @FXML
    private PasswordField campoChaveAcesso;

    private Dao<Usuario> daoUsuario;

    public CadastrarUsuarioControle() {
        try {
            daoUsuario = new Dao<>(Usuario.class);
        } catch (Exception e) {
            exibirAlerta("Erro", "Erro ao conectar ao banco de dados.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void salvarUsuario(ActionEvent event) {
        String identificacao = campoIdentificacao.getText();
        String nome = campoNomeCompleto.getText();
        String chaveAcesso = campoChaveAcesso.getText();

        if (identificacao.isEmpty() || nome.isEmpty() || chaveAcesso.isEmpty()) {
            exibirAlerta("Erro", "Todos os campos são obrigatórios.", Alert.AlertType.WARNING);
            return;
        }

        if (chaveAcesso.length() < 6) {
            exibirAlerta("Erro", "A senha deve ter pelo menos 6 caracteres.", Alert.AlertType.WARNING);
            return;
        }

        try {
            Usuario usuarioExistente = daoUsuario.buscarPorChave("identificacao", identificacao);
            if (usuarioExistente != null) {
                exibirAlerta("Erro", "Este login já está em uso.", Alert.AlertType.ERROR);
                return;
            }

            Usuario novoUsuario = new Usuario(identificacao, nome, chaveAcesso);
            daoUsuario.inserir(novoUsuario);

            exibirAlerta("Sucesso", "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);

            limparCampos();
        } catch (Exception e) {
            exibirAlerta("Erro", "Erro ao salvar usuário: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        campoIdentificacao.clear();
        campoNomeCompleto.clear();
        campoChaveAcesso.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void voltarParaMenu(ActionEvent event) throws IOException {
        App.setRoot("menu"); 
    }
}
