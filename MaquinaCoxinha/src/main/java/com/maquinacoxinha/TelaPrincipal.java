/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maquinacoxinha;

/**
 *
 * @author End User
 */
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TelaPrincipal
{
    private Maquina coxinha;
    @FXML
    private TextField campoEstoque;
    @FXML
    private TextField campoAbastecer;
    @FXML 
    private TextField campoVender;
    
    
    @FXML
    private void initialize()
    {
        coxinha = new Maquina();    
        campoEstoque.setText(""+coxinha.getEstoque());
    }
    
    @FXML
    private void abastecer()
    {
        int quantidade = Integer.parseInt(campoAbastecer.getText());
        coxinha.abastecer(quantidade);
        campoEstoque.setText(""+coxinha.getEstoque());
    }
    
    @FXML 
    private void zerarMaquina(){
        coxinha.zerarMaquina();
        campoEstoque.setText("" +coxinha.getEstoque());
        
    }
    
    @FXML 
    private void venderCoxinha(){
        int quantidade = Integer.parseInt(campoVender.getText());
        coxinha.venderCoxinha(quantidade);
        campoEstoque.setText("" +coxinha.getEstoque());
        
    }
}
