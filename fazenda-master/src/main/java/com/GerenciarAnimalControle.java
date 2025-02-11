package com;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Animal;
import util.Dao;


public class GerenciarAnimalControle {

    @FXML
    private ComboBox<String> listaIdentificacao; 

    @FXML
    private TextField campoNome; 

    @FXML
    private TextField campoRaca; 

    private Dao<Animal> daoAnimal;

    @FXML
    private void initialize() {
        daoAnimal = new Dao<>(Animal.class);
        atualizarListaAnimais(); 
    }

    private void atualizarListaAnimais() {
        try {
            List<Animal> animais = daoAnimal.listarTodos();
            ObservableList<String> identificadores = FXCollections.observableArrayList();

            for (Animal animal : animais) {
                identificadores.add(animal.getIdentificacao());
            }

            
            listaIdentificacao.setItems(identificadores);

            
            listaIdentificacao.setOnAction(event -> carregarDetalhesAnimal());
        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar a lista de animais: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void carregarDetalhesAnimal() {
        String identificacaoSelecionada = listaIdentificacao.getValue();

        if (identificacaoSelecionada != null) {
            try {
                
                Animal animal = daoAnimal.buscarPorChave("identificacao", identificacaoSelecionada);

                if (animal != null) {
                    campoNome.setText(animal.getApelido());
                    campoRaca.setText(animal.getTipoRaca());
                }
            } catch (Exception e) {
                exibirAlertaErro("Erro ao buscar os dados do animal: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void salvarModificacoes() {
        String identificacao = listaIdentificacao.getValue();
        String nome = campoNome.getText();
        String raca = campoRaca.getText();

        if (identificacao == null || nome.isBlank() || raca.isBlank()) {
            exibirAlertaErro("Preencha todos os campos antes de salvar as alterações.");
            return;
        }

        try {
            Animal animal = new Animal(identificacao, nome, raca);
            
            daoAnimal.alterar("identificacao", identificacao, animal);

            exibirAlertaSucesso("Dados do animal atualizados com sucesso!");
            limparCampos();
            atualizarListaAnimais(); 
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
        campoNome.clear();
        campoRaca.clear();
        listaIdentificacao.getSelectionModel().clearSelection();
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
    private void removerAnimal() {
        String identificacaoSelecionada = listaIdentificacao.getValue();

        if (identificacaoSelecionada == null) {
            exibirAlertaErro("Selecione um animal para excluir.");
            return;
        }

        try {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação");
            alerta.setHeaderText("Excluir Animal");
            alerta.setContentText("Tem certeza que deseja remover o animal com identificador: " + identificacaoSelecionada + "?");

            if (alerta.showAndWait().get().getButtonData().isCancelButton()) {
                return;
            }

           
            daoAnimal.excluir("identificacao", identificacaoSelecionada);
            exibirAlertaSucesso("Animal removido com sucesso!");

           
            limparCampos();
            atualizarListaAnimais();
        } catch (Exception e) {
            exibirAlertaErro("Erro ao excluir animal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
