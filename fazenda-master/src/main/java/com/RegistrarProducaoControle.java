package com;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.RegistroProducao;
import modelo.Animal;
import util.Dao;

/**
 * Classe de controle para registrar a produção de leite dos animais.
 * Permite selecionar um animal, uma data e a quantidade de leite produzida.
 *
 * @author SEU_NOME
 */
public class RegistrarProducaoControle {

    @FXML
    private void retornarMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private ComboBox<String> listaIdentificacao; // ComboBox para listar os identificadores dos animais

    @FXML
    private DatePicker seletorDataColeta; // DatePicker para selecionar a data da coleta

    @FXML
    private TextField campoVolumeLeite; // TextField para registrar o volume de leite

    private Dao<Animal> daoAnimal; // DAO para buscar animais
    private Dao<RegistroProducao> daoProducao; // DAO para registrar a produção

    @FXML
    private void initialize() {
        daoAnimal = new Dao<>(Animal.class);
        daoProducao = new Dao<>(RegistroProducao.class);
        carregarIdentificadores();
    }

    private void carregarIdentificadores() {
        try {
            List<Animal> animais = daoAnimal.listarTodos();
            ObservableList<String> identificadores = FXCollections.observableArrayList();
            for (Animal animal : animais) {
                identificadores.add(animal.getIdentificacao()); // Certifique-se de que o método getIdentificacao() existe na classe Animal
            }
            listaIdentificacao.setItems(identificadores);
        } catch (Exception e) {
            exibirAlertaErro("Erro ao carregar identificadores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void salvarRegistroProducao() {
        String identificacao = listaIdentificacao.getValue();
        LocalDate dataColeta = seletorDataColeta.getValue();
        String volumeLeiteStr = campoVolumeLeite.getText();

        if (identificacao == null || dataColeta == null || volumeLeiteStr.isBlank()) {
            exibirAlertaErro("Todos os campos são obrigatórios.");
            return;
        }

        try {
            double volumeLeite = Double.parseDouble(volumeLeiteStr);
            if (volumeLeite <= 0) {
                exibirAlertaErro("A quantidade de leite deve ser maior que zero.");
                return;
            }

            // Cria um novo registro de produção
            RegistroProducao registro = new RegistroProducao(identificacao, dataColeta, volumeLeite);

            // Salva no banco de dados
            daoProducao.inserir(registro);

            exibirAlertaSucesso("Produção registrada com sucesso!");
            limparCampos();
        } catch (NumberFormatException e) {
            exibirAlertaErro("O campo de volume deve conter um valor numérico.");
        } catch (Exception e) {
            exibirAlertaErro("Erro ao salvar registro de produção: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        listaIdentificacao.getSelectionModel().clearSelection();
        seletorDataColeta.setValue(null);
        campoVolumeLeite.clear();
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
