package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import util.Dao;
import modelo.Usuario;

/**
 * Classe de controle para autenticação do usuário.
 * Verifica o login e a senha do usuário para acesso ao sistema.
 *
 * @author SEU_NOME
 */
public class AutenticacaoControle {

    @FXML
    private TextField campoIdentificacao; // Campo de texto para o login

    @FXML
    private PasswordField campoChaveAcesso; // Campo de texto para a senha

    private Dao<Usuario> daoUsuario;

    public AutenticacaoControle() {
        try {
            daoUsuario = new Dao<>(Usuario.class);
        } catch (Exception e) {
            exibirAlerta("Erro", "Erro ao conectar ao banco de dados.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void realizarLogin(ActionEvent event) {
        String identificacao = campoIdentificacao.getText();
        String chaveAcesso = campoChaveAcesso.getText();

        if (identificacao.isEmpty() || chaveAcesso.isEmpty()) {
            exibirAlerta("Erro", "Usuário ou senha não podem estar vazios.", Alert.AlertType.WARNING);
            return;
        }

        try {
            Usuario usuarioEncontrado = daoUsuario.buscarPorChave("identificacao", identificacao);

            if (usuarioEncontrado != null && usuarioEncontrado.getChaveAcesso().equals(chaveAcesso)) {
                App.setRoot("menu"); // Substituição de Principal.alterarTela()

                // Lógica para trocar a cena ou prosseguir no sistema
            } else {
                exibirAlerta("Erro", "Usuário ou senha inválidos.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            exibirAlerta("Erro", "Erro ao verificar usuário: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    @FXML
    public void retornarMenu() throws IOException {
        App.setRoot("menu");
    }
}
