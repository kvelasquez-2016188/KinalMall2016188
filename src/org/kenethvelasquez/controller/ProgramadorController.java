
package org.kenethvelasquez.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kenethvelasquez.system.Principal;


public class ProgramadorController implements Initializable{
    
    private Principal escenarioPrincipal;
    @FXML Button btnProgramador;
    @FXML Button btnAdministrador;
    @FXML Label lblNombres;
    @FXML Label lblApellidos;
    @FXML Label lblOcupacion;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML 
    private void opciones (ActionEvent event){
        if (event.getSource()== btnProgramador){
        lblNombres.setText("Keneth Abraham");
        lblApellidos.setText("Velásquez Batz");
        lblOcupacion.setText("Estudiante");
        }
        else if (event.getSource()== btnAdministrador){
        lblNombres.setText("Fundación");
        lblApellidos.setText("®Kinal");
        lblOcupacion.setText("2021");
        }
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
     
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
   
}
