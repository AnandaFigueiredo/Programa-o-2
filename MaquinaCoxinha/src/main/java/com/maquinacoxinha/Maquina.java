/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maquinacoxinha;

/**
 *
 * @author End User
 */
public class Maquina {
    private Integer estoque;
    
    public Maquina(){
        estoque = 0; 
    }
    
    public void abastecer( Integer quantidade ){
        if ( quantidade < 0){
            return;
        } 
        estoque = quantidade; 
    }
    
    public void venderCoxinha(){
        if (estoque > 0){
            estoque--; 
        }
        else{
            System.out.println("Não há estoque");
        }
    }
    
    public void venderCoxinha( Integer quantidade ){
        if ( quantidade <= estoque ){
            estoque -= quantidade; 
        }
        else{
            System.out.println("Não há coxinhas suficientes ");
        }
    }
    
    public void zerarMaquina(){
        estoque = 0; 
    }
    
    
    public void setEstoque(Integer estoque){
        if ( estoque > 0){
            this.estoque = estoque; 
        } 
    }
    
    public Integer getEstoque(){
        return estoque; 
    }
    
}
