package com;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.RegistroProducao;
import util.Dao;


public class ConsultarProducaoControle {

    @FXML
    private void retornarMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private DatePicker seletorDataInicio; 

    @FXML
    private DatePicker seletorDataFim; 

    @FXML
    private TableView<RegistroProducao> tabelaRegistros; 

    @FXML
    private TableColumn<RegistroProducao, LocalDate> colunaDataColeta; 

    @FXML
    private TableColumn<RegistroProducao, Double> colunaVolumeLeite; 

    private Dao<RegistroProducao> daoProducao;

    @FXML
    private void initialize() {
        daoProducao = new Dao<>(RegistroProducao.class);
        configurarTabela();
    }

    private void configurarTabela() {
        
        colunaDataColeta.setCellValueFactory(new PropertyValueFactory<>("dataColeta"));
        colunaVolumeLeite.setCellValueFactory(new PropertyValueFactory<>("volumeLeite"));
        
    }

    @FXML
    private void filtrarPorPeriodo() {
        LocalDate dataInicio = seletorDataInicio.getValue();
        LocalDate dataFim = seletorDataFim.getValue();

        if (dataInicio == null || dataFim == null) {
            exibirAlertaErro("Ambas as datas (início e fim) devem ser preenchidas.");
            return;
        }

        if (dataInicio.isAfter(dataFim)) {
            exibirAlertaErro("A data de início não pode ser posterior à data final.");
            return;
        }

        try {
            List<RegistroProducao> registros = daoProducao.listarTodos();
            ObservableList<RegistroProducao> registrosFiltrados = FXCollections.observableArrayList();

            
            for (RegistroProducao registro : registros) {
                if (!registro.getDataColeta().isBefore(dataInicio) && !registro.getDataColeta().isAfter(dataFim)) {
                    registrosFiltrados.add(registro);
                }
            }

          
            tabelaRegistros.setItems(registrosFiltrados);

        } catch (Exception e) {
            exibirAlertaErro("Erro ao buscar os registros de produção: " + e.getMessage());
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
