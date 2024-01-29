/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.producto;
import modelo.productoDAO;
import vista.interfazProd;

/**
 *
 * @author Usuario
 */
public class controladorProducto implements  ActionListener {
//instancias 
    producto Producto = new producto();
    productoDAO productodao = new productoDAO();
    interfazProd vista = new interfazProd();
    DefaultTableModel modelotabla = new DefaultTableModel();
//variables glabales
     private int IdProducto;
    private String nombre;
    private String descripción;
    private int precio;

    /**
     *
     * @param vista
     */
    public controladorProducto(interfazProd vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
        
        
    }

    private void agregarEventos() {
        vista.getBtnregistrar().addActionListener(this);
        vista.getBtnactualizar().addActionListener(this);
        vista.getBtneliminar().addActionListener(this);
        vista.getTbltabla().addMouseListener(new MouseAdapter() {
           
            public void mouseClicked(MouseEvent e){

                llenarCampos(e);
                
            }
     });

    }

    /**
     *
     */
    public void listarTabla(){
        String[]títulos = new String[] {"IdProducto","Nombre","Descripción","Precio"};
       modelotabla = new DefaultTableModel(títulos, 0);
       List<producto>listaProducto = productodao.listar();
        for (producto producto : listaProducto) {
            modelotabla.addRow (new Object[]{producto.getIdProducto(),producto.getNombre(),producto.getDescripción(),producto.getPrecio()});
        }
           vista.getTbltabla().setModel(modelotabla);
vista.getTbltabla().setPreferredSize(new Dimension(350,modelotabla.getRowCount()*16));
        }
    private void llenarCampos(MouseEvent e){
         JTable target = (JTable) e.getSource();
         IdProducto = (int) vista.getTbltabla().getModel().getValueAt(target.getSelectedRow(), 0);
         vista.getTxtnombre().setText(vista.getTbltabla().getModel().getValueAt(target.getSelectedRow(), 1). toString());
         vista.getTxtdescripcion().setText(vista.getTbltabla().getModel().getValueAt(target.getSelectedRow(), 2). toString());
          vista.getTxtprecio().setText(vista.getTbltabla().getModel().getValueAt(target.getSelectedRow(), 3). toString());
    }
    //----------Validar Formulario
    private Boolean validarDatos (){
    if ("".equals(vista.getTxtnombre().getText()) || "".equals(vista.getTxtdescripcion().getText()) || "".equals(vista.getTxtprecio().getText())) {

            JOptionPane.showMessageDialog(null, "los campos no pueden estar vacios","error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    return true;
    }
    //metodo 3 en 1
    //cargar las variables
    //parseando los valores
    //valida que precio sea número
private boolean cargarDatos (){
    try {
        nombre = vista.getTxtnombre().getText();
        descripción =vista.getTxtdescripcion().getText();
       precio = Integer.parseInt(vista.getTxtprecio().getText());
        return true;
        
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "los campos idProducto y precio deben ser numericos","error",JOptionPane.ERROR_MESSAGE);
        System.out.println("error al cargarDatos"+e);
        return false;
    }
    
    }
private void limpiarCampos (){
    vista.getTxtnombre().setText("");
    vista.getTxtdescripcion().setText("");
    vista.getTxtprecio().setText("");
    IdProducto = 0;
    nombre = "";
    descripción = "";
    precio = 0;
}
                          //metodo agregar
private void registrarProducto(){
    try {
        if (validarDatos()) {//validarDatos ()==true
            if (cargarDatos()){//cargarDatos
                producto Producto = new producto(nombre, descripción, precio);
                productodao.registrar(Producto);
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                limpiarCampos ();
            }
            
        }
        
    } catch (HeadlessException e) {
        System.out.println("error al registrarC!"+e);
    }finally {
        listarTabla();
    }
}
private void actualizarProducto(){
    try {
        if (validarDatos()) {
            if (cargarDatos()) {
                producto Producto = new producto(IdProducto, nombre, descripción, precio);
                productodao.actualizar(Producto);
                JOptionPane.showMessageDialog(null, "actualización exitosa");
                limpiarCampos ();
            }
            
        }
    } catch (HeadlessException e) {
        System.out.println("Error en actualizarc:"+e);
  }finally {
        listarTabla();
    }
    
 }
private void eliminarProducto(){
    try {
        if (IdProducto!=0) {
            productodao.eliminar(IdProducto);
             JOptionPane.showMessageDialog(null, "registro eliminado");
              limpiarCampos ();
                
             }
    } catch (HeadlessException e) {
         JOptionPane.showMessageDialog(null, "debe seleccionar un producto de la tabla","error",JOptionPane.ERROR_MESSAGE);
         System.out.println("error al borrarC!"+e);
    }finally {
        listarTabla();
    }
}
   
//Dar funcionalidad a los botones
   @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==vista.getBtnregistrar()) {
            registrarProducto();
        }
        if (ae.getSource()==vista.getBtnactualizar()); {
        actualizarProducto();
        }
        if (ae.getSource()==vista.getBtneliminar()){
            eliminarProducto();
            
        }
        
       
    }
        }
    
   