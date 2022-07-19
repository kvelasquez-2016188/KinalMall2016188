/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kenethvelasquez.system;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kenethvelasquez.controller.AdministracionController;
import org.kenethvelasquez.controller.CargosController;
import org.kenethvelasquez.controller.ClientesController;
import org.kenethvelasquez.controller.CuentasPorCobrarController;
import org.kenethvelasquez.controller.CuentasPorPagarController;
import org.kenethvelasquez.controller.DepartamentosController;
import org.kenethvelasquez.controller.EmpleadosController;
import org.kenethvelasquez.controller.HorariosController;
import org.kenethvelasquez.controller.LocalesController;
import org.kenethvelasquez.controller.LoginController;
import org.kenethvelasquez.controller.MenuPrincipalController;
import org.kenethvelasquez.controller.ProgramadorController;
import org.kenethvelasquez.controller.ProveedoresController;
import org.kenethvelasquez.controller.TipoClientesController;
import org.kenethvelasquez.controller.UsuarioController;

/**
 *
 * @author kenet
 */
public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/kenethvelasquez/view/"; 
    private Stage escenarioPrincipal;
    private Scene escena;
    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("KinalMall 2021");
        //Parent root = FXMLLoader.load(getClass().getResource("/org/kenethvelasquez/view/MenuPrincipalView.fxml"));
        //Scene escena = new Scene (root);
        //escenarioPrincipal.setScene(escena);
        ventanaLogin();
        escenarioPrincipal.show();
    }

    public void menuPrincipal() {
        try {
            MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml", 552, 407);
            menuPrincipal.setEscenarioPrincipal(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProgramador(){
        try{
            ProgramadorController programador = (ProgramadorController)cambiarEscena ("ProgramadorView.fxml", 600, 400);
            programador.setEscenarioPrincipal(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaAdministracion(){
        try{
            AdministracionController admin = (AdministracionController)cambiarEscena ("AdministracionView.fxml", 826, 400);
            admin.setEscenarioPrincipal(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoCliente(){
        try {TipoClientesController tipoCliente = (TipoClientesController) cambiarEscena("TipoClienteView.fxml", 740, 400);
            tipoCliente.setEscenarioPrincipal(this);
        }
        catch(Exception e){
            e.printStackTrace();
                }
    }
    
    
    public void ventanaLocales(){
    try{
        LocalesController locales = (LocalesController) cambiarEscena("LocalesView.fxml", 936, 427);
        locales.setEscenarioPrincipal(this);
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    public void ventanaDepartamentos(){
        try {
            DepartamentosController departamentos = (DepartamentosController) cambiarEscena("DepartamentosView.fxml", 752, 400);
            departamentos.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProveedores(){
        try {
            ProveedoresController proveedores = (ProveedoresController) cambiarEscena ("ProveedoresView.fxml", 1012, 471);
            proveedores.setEscenarioPrincipal(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCuentasPorPagar(){
        try {
            CuentasPorPagarController cuentasPorPagar = (CuentasPorPagarController) cambiarEscena ("CuentasPorPagarView.fxml", 988, 471);
            cuentasPorPagar.setEscenarioPrincipal(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaHorarios(){
        try{
            HorariosController horarios = (HorariosController) cambiarEscena("HorariosView.fxml", 883, 426);
            horarios.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCargos(){
        try {
            CargosController cargos = (CargosController) cambiarEscena ("CargosView.fxml",600, 400);
            cargos.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ventanaClientes(){
        try{
            ClientesController clientes = (ClientesController) cambiarEscena ("ClientesView.fxml", 1016, 462);
            clientes.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpleados(){
        try{
            EmpleadosController empleados = (EmpleadosController) cambiarEscena ("EmpleadosView.fxml", 1044, 535);
            empleados.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void ventanaCuentasPorCobrar(){
        try{
            CuentasPorCobrarController cuentasPorCobrar = (CuentasPorCobrarController) cambiarEscena ("CuentasPorCobrarView.fxml", 949, 456);
            cuentasPorCobrar.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaUsuario(){
        try{
            UsuarioController usuario = (UsuarioController) cambiarEscena("UsuarioView.fxml", 600, 400);
            usuario.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ventanaLogin(){
       try{
            LoginController login = (LoginController) cambiarEscena ("LoginView.fxml", 600, 431);
            login.setEscenarioPrincipal(this);
       }catch(Exception e){
           e.printStackTrace();
       }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
  /*  public Initializable cambiarEscena (String fxml, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA + fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    }
    */
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA +fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo), ancho, alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    }
}
