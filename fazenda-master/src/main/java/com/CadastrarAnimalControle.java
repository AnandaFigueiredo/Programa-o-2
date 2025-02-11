package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Animal;
import util.Dao;


public class CadastrarAnimalControle {

    @FXML
    private TextField campoIdentificacao;
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private TextField campoRaca;

    private Dao<Animal> daoAnimal;

    @FXML
    private void initialize() {
        daoAnimal = new Dao<>(Animal.class);
    }

    @FXML
    private void salvarAnimal() {
        if (campoIdentificacao.getText().isBlank() || campoRaca.getText().isBlank()) {
            exibirAlertaErro("Preencha os campos obrigatórios.");
            return;
        }
        String identificacao = campoIdentificacao.getText().toUpperCase();
        Animal existente = daoAnimal.buscarPorChave("identificacao", identificacao);
        if (existente != null) {
            exibirAlertaErro("Já existe um animal com este identificador.");
            return;
        }
        Animal animal = new Animal(identificacao, campoNome.getText(), campoRaca.getText());
        daoAnimal.inserir(animal);
        limparCampos();
        exibirAlertaSucesso("Animal cadastrado com sucesso!");
    }

    @FXML
    public void retornarMenu() throws IOException {
        App.setRoot("menu");
    }

    private void limparCampos() {
        this.campoIdentificacao.setText("");
        this.campoNome.setText("");
        this.campoRaca.setText("");
    }

    private void exibirAlertaSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }

    private void exibirAlertaErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }
}
