
package org.kenethvelasquez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.kenethvelasquez.bean.Usuario;
import org.kenethvelasquez.db.Conexion;
import org.kenethvelasquez.system.Principal;


public class UsuarioController implements Initializable{
    
    private Principal escenarioPrincipal; 
    
   
    private enum operaciones {NUEVO, GUARDAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Usuario> listaUsuario;
    
    @FXML private TextField txtNombreUsuario;
    @FXML private TextField txtApellidoUsuario;
    @FXML private TextField txtUsuarioLogin;
    @FXML private TextField txtContrasena;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private ImageView imgNuevo;

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @Override
     public void initialize(URL location, ResourceBundle resources) {
    }
     
     
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                limpiarControles();
                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image("/org/kenethvelasquez/images/guardar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            case GUARDAR:
                guardar();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                imgNuevo.setImage(new Image("/org/kenethvelasquez/images/nuevo.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                break;
        }
    }
    public void desactivarControles(){
        txtNombreUsuario.setEditable(false);
        txtApellidoUsuario.setEditable(false);
        txtUsuarioLogin.setEditable(false);
        txtContrasena.setEditable(false);
    }
    public void activarControles(){
        txtNombreUsuario.setEditable(true);
        txtApellidoUsuario.setEditable(true);
        txtUsuarioLogin.setEditable(true);
        txtContrasena.setEditable(true);
    }
    public void limpiarControles(){
        txtNombreUsuario.clear();
        txtApellidoUsuario.clear();
        txtUsuarioLogin.clear();
        txtContrasena.clear();
    }
    public void guardar(){
        Usuario registro = new Usuario();
        registro.setNombreUsuario(txtNombreUsuario.getText());
        registro.setApellidoUsuario(txtApellidoUsuario.getText());
        registro.setUsuarioLogin(txtUsuarioLogin.getText());
        registro.setContrasena(txtContrasena.getText());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{Call sp_AgregarUsuario(?,?,?,?)}");
            procedimiento.setString(1, registro.getNombreUsuario());
            procedimiento.setString(2, registro.getApellidoUsuario());
            procedimiento.setString(3, registro.getUsuarioLogin());
            procedimiento.setString(4, registro.getContrasena());
            procedimiento.execute();
            ventanaLogin();
           
        }catch(Exception e){
            e.printStackTrace();
        }
      
    }
    public void ventanaUsuario(){
        escenarioPrincipal.ventanaUsuario();
    }
    public void ventanaLogin(){
        escenarioPrincipal.ventanaLogin();
    }
    
}
