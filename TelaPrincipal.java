package com.cep;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class TelaPrincipal {

    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtRua;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtNome; // Campo para nome do cliente
    @FXML
    private TextField txtTelefone; // Campo para telefone do cliente

    private Buscador buscador = new Buscador();
    private ArrayList<Cliente> clientes = new ArrayList<>(); // Lista para armazenar clientes

    @FXML
    public void buscarCep() {
        String cep = txtCep.getText();
        try {
            Endereco endereco = buscador.buscar(cep);
            txtRua.setText(endereco.getRua());
            txtCidade.setText(endereco.getCidade());
            txtEstado.setText(endereco.getEstado());
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Formato de CEP inválido.");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao buscar o CEP: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro desconhecido: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void limparCampos() {
        txtNome.clear();
        txtCep.clear();
        txtRua.clear();
        txtNumero.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtTelefone.clear();
    }

    @FXML
    public void gravarCliente() {
        Cliente cliente = new Cliente(
            txtNome.getText(),
            new Endereco(txtCep.getText(), txtRua.getText(), txtNumero.getText(), txtCidade.getText(), txtEstado.getText()),
            txtTelefone.getText()
        );
        clientes.add(cliente);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Cliente cadastrado com sucesso!");
        alert.showAndWait();

        // Exibe os clientes cadastrados no console
        System.out.println("Clientes cadastrados:");
        System.out.println("Nome: " + cliente.getNome() +  " \nTelefone: " + cliente.getTelefone() +  "\nEndereco: " + cliente.getEndereco().getRua() +  "\nNumero: " + cliente.getEndereco().getNumero() +  "\nCidade: " + cliente.getEndereco().getCidade() +  "\nEstado: " + cliente.getEndereco().getEstado());
    }
}
