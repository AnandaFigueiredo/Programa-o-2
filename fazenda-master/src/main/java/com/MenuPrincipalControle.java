package com;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MenuPrincipalControle {
    
     @FXML
    private void abrirTelaLogin() throws IOException {
        App.setRoot("login"); 
    }

  
    @FXML
    private void cadastrarAnimal() throws IOException { 
        App.setRoot("cadastrarvaca");
    }

    @FXML
    private void alterarAnimal() throws IOException { 
        App.setRoot("alteraranimal"); 
    }

    @FXML
    private void cadastrarUsuario() throws IOException { 
        App.setRoot("cadastrousuario"); 
    }

    @FXML
    private void gerenciarUsuario() throws IOException { 
        App.setRoot("alterarusuario"); 
    }

    @FXML
    private void registrarProducao() throws IOException { 
        App.setRoot("RegistrarProducao"); 
    }

    @FXML
    private void consultarProducaoPeriodo() throws IOException { 
        App.setRoot("producaoPeriodo"); 
    }

    @FXML
    private void consultarProducaoAnimal() throws IOException { 
        App.setRoot("listarproducao"); 
    }

    @FXML
    private void sair() { 
        Platform.exit();
        System.exit(0);
    }
}
