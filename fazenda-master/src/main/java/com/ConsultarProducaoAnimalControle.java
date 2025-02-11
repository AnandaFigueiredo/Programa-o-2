package com;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.RegistroProducao;
import modelo.Animal;
import util.Dao;

public class ConsultarProducaoAnimalControle {

    @FXML
    private void retornarMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private ComboBox<String> listaIdentificacao; 

    @FXML
    private TableView<RegistroProducao> tabelaRegistros; 
    @FXML
    private TableColumn<RegistroProducao, String> colunaDataColeta; 

    @FXML
    private TableColumn<RegistroProducao, Double> colunaVolumeLeite; 

    private Dao<Animal> daoAnimal; 
    private Dao<RegistroProducao> daoProducao; 

    @FXML
    private void initialize() {
        daoAnimal = new Dao<>(Animal.class);
        daoProducao = new Dao<>(RegistroProducao.class);

        configurarTabela();
        carregarIdentificadores();
    }

    private void configurarTabela() {
        
        colunaDataColeta.setCellValueFactory(new PropertyValueFactory<>("dataColeta"));
        colunaVolumeLeite.setCellValueFactory(new PropertyValueFactory<>("volumeLeite"));
    }

    private void carregarIdentificadores() {
        try {
            List<Animal> animais = daoAnimal.listarTodos();
            ObservableList<String> identificadores = FXCollections.observableArrayList();
            for (Animal animal : animais) {
                identificadores.add(animal.getIdentificacao());
            }
            listaIdentificacao.setItems(identificadores);

            // Listener para carregar a produção ao selecionar um identificador
            listaIdentificacao.setOnAction(event -> carregarProducaoAnimal());
        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar os identificadores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void carregarProducaoAnimal() {
        String identificacaoSelecionada = listaIdentificacao.getValue();

        if (identificacaoSelecionada == null) {
            exibirAlertaErro("Selecione um animal para exibir a produção.");
            return;
        }

        try {
            
            List<RegistroProducao> registros = daoProducao.listarTodos();
            ObservableList<RegistroProducao> registrosFiltrados = FXCollections.observableArrayList();

            
            for (RegistroProducao registro : registros) {
                if (registro.getIdentificacaoAnimal().equals(identificacaoSelecionada)) {
                    registrosFiltrados.add(registro);
                }
            }

            
            tabelaRegistros.setItems(registrosFiltrados);

        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar a produção do animal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void exibirAlertaErro(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.show();
    }
}
