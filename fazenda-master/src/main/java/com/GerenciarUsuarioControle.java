package com;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import modelo.Usuario;
import util.Dao;

public class GerenciarUsuarioControle {

    @FXML
    private ComboBox<String> listaUsuarios; 

    @FXML
    private TextField campoNomeCompleto; 

    @FXML
    private TextField campoIdentificacao; 

    @FXML
    private PasswordField campoChaveAcesso; 

    private Dao<Usuario> daoUsuario;

    @FXML
    private void initialize() {
        daoUsuario = new Dao<>(Usuario.class);
        atualizarListaUsuarios(); 
    }

    private void atualizarListaUsuarios() {
        try {
            List<Usuario> usuarios = daoUsuario.listarTodos();
            ObservableList<String> identificadores = FXCollections.observableArrayList();

            for (Usuario usuario : usuarios) {
                identificadores.add(usuario.getIdentificacao()); 
            }

            listaUsuarios.setItems(identificadores);
            listaUsuarios.setOnAction(event -> carregarDetalhesUsuario());
        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar a lista de usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void carregarDetalhesUsuario() {
        String usuarioSelecionado = listaUsuarios.getValue();

        if (usuarioSelecionado != null) {
            try {
                Usuario usuario = daoUsuario.buscarPorChave("identificacao", usuarioSelecionado);

                if (usuario != null) {
                    campoNomeCompleto.setText(usuario.getNomeCompleto()); 
                    campoIdentificacao.setText(usuario.getIdentificacao()); 
                    campoChaveAcesso.setText(usuario.getChaveAcesso()); 
                }
            } catch (Exception e) {
                exibirAlertaErro("Erro ao buscar os dados do usuário: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void salvarModificacoes() {
        String identificacao = campoIdentificacao.getText();
        String nome = campoNomeCompleto.getText();
        String chaveAcesso = campoChaveAcesso.getText();

        if (identificacao.isBlank() || nome.isBlank() || chaveAcesso.isBlank()) {
            exibirAlertaErro("Preencha todos os campos antes de salvar as alterações.");
            return;
        }

        try {
            Usuario usuario = new Usuario(identificacao, nome, chaveAcesso);

            daoUsuario.alterar("identificacao", identificacao, usuario);

            exibirAlertaSucesso("Dados do usuário atualizados com sucesso!");
            limparCampos();
            atualizarListaUsuarios();
        } catch (Exception e) {
            exibirAlertaErro("Erro ao salvar alterações: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void retornarMenu() throws IOException {
        App.setRoot("menu");
    }

    private void limparCampos() {
        campoNomeCompleto.clear();
        campoIdentificacao.clear();
        campoChaveAcesso.clear();
        listaUsuarios.getSelectionModel().clearSelection();
    }

    private void exibirAlertaSucesso(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.show();
    }

    private void exibirAlertaErro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.show();
    }

    @FXML
    private void removerUsuario() {
        String usuarioSelecionado = listaUsuarios.getValue();

        if (usuarioSelecionado == null) {
            exibirAlertaErro("Selecione um usuário para excluir.");
            return;
        }

        try {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação");
            alerta.setHeaderText("Excluir Usuário");
            alerta.setContentText("Tem certeza que deseja remover: " + usuarioSelecionado + "?");

            if (alerta.showAndWait().get().getButtonData().isCancelButton()) {
                return;
            }

            daoUsuario.excluir("identificacao", usuarioSelecionado);
            exibirAlertaSucesso("Usuário removido com sucesso!");

            limparCampos();
            atualizarListaUsuarios();
        } catch (Exception e) {
            exibirAlertaErro("Erro ao excluir usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
