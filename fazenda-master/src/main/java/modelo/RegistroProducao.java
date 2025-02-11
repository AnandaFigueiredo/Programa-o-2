/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import java.time.LocalDate;

/**
 * Classe que representa um registro de produção de leite.
 * Contém a identificação do animal, data e quantidade de leite produzido.
 *
 * @author SEU_NOME
 */
public class RegistroProducao {

    @BsonProperty(value = "identificacao_animal")
    private String identificacaoAnimal; // Identifica o animal produtor

    @BsonProperty(value = "data_coleta")
    private LocalDate dataColeta; // Data do registro de produção

    @BsonProperty(value = "volume_leite")
    private double volumeLeite; // Quantidade de leite produzido

    public RegistroProducao() {}

    public RegistroProducao(String identificacaoAnimal, LocalDate dataColeta, double volumeLeite) {
        this.identificacaoAnimal = identificacaoAnimal;
        this.dataColeta = dataColeta;
        this.volumeLeite = volumeLeite;
    }

    // Getters e Setters
    public String getIdentificacaoAnimal() {
        return identificacaoAnimal;
    }

    public void setIdentificacaoAnimal(String identificacaoAnimal) {
        this.identificacaoAnimal = identificacaoAnimal;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public double getVolumeLeite() {
        return volumeLeite;
    }

    public void setVolumeLeite(double volumeLeite) {
        this.volumeLeite = volumeLeite;
    }
}

