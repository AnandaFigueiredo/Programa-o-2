/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Classe que representa um usuário do sistema.
 * Contém as informações de identificação, nome e credenciais de acesso.
 *
 * @author SEU_NOME
 */
public class Usuario {
    
    private String identificacao; // Identificação única do usuário
    private String nomeCompleto; // Nome completo do usuário
    private String chaveAcesso; // Senha do usuário

    public Usuario() {}

    public Usuario(String identificacao, String nomeCompleto, String chaveAcesso) {
        this.identificacao = identificacao;
        this.nomeCompleto = nomeCompleto;
        this.chaveAcesso = chaveAcesso;
    }

    // Getters e Setters
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

