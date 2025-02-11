
package modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import java.time.LocalDate;


public class RegistroProducao {

    @BsonProperty(value = "identificacao_animal")
    private String identificacaoAnimal; 

    @BsonProperty(value = "data_coleta")
    private LocalDate dataColeta; 

    @BsonProperty(value = "volume_leite")
    private double volumeLeite; 

    public RegistroProducao() {}

    public RegistroProducao(String identificacaoAnimal, LocalDate dataColeta, double volumeLeite) {
        this.identificacaoAnimal = identificacaoAnimal;
        this.dataColeta = dataColeta;
        this.volumeLeite = volumeLeite;
    }

    
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

