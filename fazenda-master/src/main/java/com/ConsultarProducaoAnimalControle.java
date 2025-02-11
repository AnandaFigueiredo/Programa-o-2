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

/**
 * Classe de controle para consulta da produção de leite de um animal específico.
 * Permite visualizar a produção de um animal selecionado.
 *
 * @author SEU_NOME
 */
public class ConsultarProducaoAnimalControle {

    @FXML
    private void retornarMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private ComboBox<String> listaIdentificacao; // ComboBox para selecionar o identificador do animal

    @FXML
    private TableView<RegistroProducao> tabelaRegistros; // TableView para exibir as produções registradas

    @FXML
    private TableColumn<RegistroProducao, String> colunaDataColeta; // Coluna para exibir a data da coleta

    @FXML
    private TableColumn<RegistroProducao, Double> colunaVolumeLeite; // Coluna para exibir o volume de leite

    private Dao<Animal> daoAnimal; // DAO para animais
    private Dao<RegistroProducao> daoProducao; // DAO para produções

    @FXML
    private void initialize() {
        daoAnimal = new Dao<>(Animal.class);
        daoProducao = new Dao<>(RegistroProducao.class);

        configurarTabela();
        carregarIdentificadores();
    }

    private void configurarTabela() {
        // Configuração das colunas da tabela
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
            // Busca todas as produções do animal selecionado
            List<RegistroProducao> registros = daoProducao.listarTodos();
            ObservableList<RegistroProducao> registrosFiltrados = FXCollections.observableArrayList();

            // Filtra as produções pelo identificador do animal
            for (RegistroProducao registro : registros) {
                if (registro.getIdentificacaoAnimal().equals(identificacaoSelecionada)) {
                    registrosFiltrados.add(registro);
                }
            }

            // Atualiza a tabela com os dados filtrados
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
