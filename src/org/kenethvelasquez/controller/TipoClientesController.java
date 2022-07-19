
package org.kenethvelasquez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.kenethvelasquez.bean.TipoClientes;
import org.kenethvelasquez.system.Principal;
import org.kenethvelasquez.db.Conexion;

public class TipoClientesController implements Initializable{
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private Principal escenarioPrincipal;
    private ObservableList <TipoClientes> listaTipoCliente;
    
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    @FXML private TextField txtCodigoTipoCliente;
    @FXML private TextField txtDescripcion;
    @FXML private TableView tblTipoClientes;
    @FXML private TableColumn colCodigoTipoClientes;
    @FXML private TableColumn colDescripcion;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    @FXML private ImageView imgReporte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal() {
        escenarioPrincipal.menuPrincipal();
    }

    public ObservableList <TipoClientes> getTipoCliente() {

        ArrayList<TipoClientes> lista = new ArrayList<TipoClientes>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarTipoCliente()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new TipoClientes(resultado.getInt("codigoTipoCliente"),
                        resultado.getString("descripcion")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTipoCliente = FXCollections.observableArrayList(lista);
    }

    public void cargarDatos() {
        tblTipoClientes.setItems(getTipoCliente());
        colCodigoTipoClientes.setCellValueFactory(new PropertyValueFactory<TipoClientes, Integer>("codigoTipoCliente"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TipoClientes, String>("descripcion"));
    }

    public void seleccionarElemento() {
        
    if (tblTipoClientes.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Debe Selecionar un elemento.");
        }
        else{
        txtCodigoTipoCliente.setText(String.valueOf(((TipoClientes)tblTipoClientes.getSelectionModel().getSelectedItem()).getCodigoTipoCliente()));
        txtDescripcion.setText(((TipoClientes)tblTipoClientes.getSelectionModel().getSelectedItem()).getDescripcion());
        }
    }

    public void desactivarControles() {
        txtCodigoTipoCliente.setEditable(false);
        txtDescripcion.setEditable(false);
    }

    public void activarControles() {
        txtCodigoTipoCliente.setEditable(false);
        txtDescripcion.setEditable(true);
    }

    public void limpiarControles() {
        txtCodigoTipoCliente.clear();
        txtDescripcion.clear();

    }

    public void nuevo() {
        switch (tipoDeOperacion) {
            case NINGUNO:
                activarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                imgNuevo.setImage(new Image("/org/kenethvelasquez/images/guardar.png"));
                imgEliminar.setImage(new Image("/org/kenethvelasquez/images/cancelar.png"));
                tipoDeOperacion = operaciones.GUARDAR;

                break;

            case GUARDAR:
                guardar();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/kenethvelasquez/images/nuevo.png"));
                imgEliminar.setImage(new Image("/org/kenethvelasquez/images/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();

                break;
        }
    }

    public void guardar() {
        TipoClientes registro = new TipoClientes();
        registro.setDescripcion(txtDescripcion.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{CALL sp_AgregarTipoCliente(?)}");
            procedimiento.setString(1, registro.getDescripcion());
            procedimiento.execute();
            listaTipoCliente.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() {
        switch (tipoDeOperacion) {
            case GUARDAR:
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/kenethvelasquez/images/nuevo.png"));
                imgEliminar.setImage(new Image("/org/kenethvelasquez/images/eliminar.png"));
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                break;
            default:
                if (tblTipoClientes.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro?", "Eliminar Administración", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{CALL sp_EliminarTipoCliente(?)}");
                            procedimiento.setInt(1, ((TipoClientes) tblTipoClientes.getSelectionModel().getSelectedItem()).getCodigoTipoCliente());
                            procedimiento.execute();
                            listaTipoCliente.remove(tblTipoClientes.getSelectionModel().getSelectedIndex());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar un registro.");
                    }

                }
        }

    }

    public void editar(){
        switch (tipoDeOperacion){
            case NINGUNO:
                if (tblTipoClientes.getSelectionModel().getSelectedItem() != null) {
                  btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    imgEditar.setImage(new Image("/org/kenethvelasquez/images/actualizar.png"));
                    imgReporte.setImage(new Image("/org/kenethvelasquez/images/cancelar.png"));
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
  
                    
                } else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
                }
                
                break;
                
                
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/kenethvelasquez/images/editar.png"));
                imgReporte.setImage(new Image("/org/kenethvelasquez/images/Reporte.png"));
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
                
            
        }
    }
    
    public void actualizar(){
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{CALL sp_EditarTipoCliente(?,?)}");
            TipoClientes registro = (TipoClientes) tblTipoClientes.getSelectionModel().getSelectedItem();
            registro.setDescripcion(txtDescripcion.getText());
            procedimiento.setInt(1, registro.getCodigoTipoCliente());
            procedimiento.setString(2, registro.getDescripcion());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void reporte(){
        switch(tipoDeOperacion){
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/kenethvelasquez/images/editar.png"));
                imgReporte.setImage(new Image("/org/kenethvelasquez/images/Reporte.png"));
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                
                break;
        }
    }

    public void ventanaClientes(){
        escenarioPrincipal.ventanaClientes();
    }
}

