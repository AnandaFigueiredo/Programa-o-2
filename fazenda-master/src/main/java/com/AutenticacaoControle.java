package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import util.Dao;
import modelo.Usuario;

public class AutenticacaoControle {

    @FXML
    private TextField campoIdentificacao; 

    @FXML
    private PasswordField campoChaveAcesso; 

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
                App.setRoot("menu"); 

                
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
