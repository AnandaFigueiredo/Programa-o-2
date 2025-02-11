
package modelo;

import org.bson.codecs.pojo.annotations.BsonProperty;


public class Animal {

    @BsonProperty(value="identificacao") 
    private String identificacao;
    
    @BsonProperty(value="apelido")
    private String apelido;
    
    @BsonProperty(value="tipo_raca")
    private String tipoRaca; 

    public Animal() {}

    public Animal(String identificacao, String apelido, String tipoRaca){
        this.identificacao = identificacao; 
        this.apelido = apelido;
        this.tipoRaca = tipoRaca;
    }
    
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTipoRaca() {
        return tipoRaca;
    }

    public void setTipoRaca(String tipoRaca) {
        this.tipoRaca = tipoRaca;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    @Override
    public String toString() {
        return this.identificacao;
    }
}
