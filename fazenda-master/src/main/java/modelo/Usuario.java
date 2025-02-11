package modelo;


public class Usuario {
    
    private String identificacao; 
    private String nomeCompleto; 
    private String chaveAcesso; 

    public Usuario() {}

    public Usuario(String identificacao, String nomeCompleto, String chaveAcesso) {
        this.identificacao = identificacao;
        this.nomeCompleto = nomeCompleto;
        this.chaveAcesso = chaveAcesso;
    }

        public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }
}

