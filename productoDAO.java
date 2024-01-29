/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ConexionDb;
import java.sql.Connection;
import java.sql.PreparedStatement;  // Corregido aquí
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productoDAO {
    ConexionDb conexiondb = new ConexionDb();  // Instancia de ConexionDb
   
    Connection cx;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        String sql = "select *from producto";
        List<producto> lista = new ArrayList<>();
        try {
            cx = conexiondb.conectar();
             ps = cx.prepareStatement(sql);
            rs = ps.executeQuery();
            
              while (rs.next()) {
                  producto Producto = new producto();
                  Producto.setIdProducto(rs.getInt(1));
                  Producto.setNombre(rs.getString(2));
                  Producto.setDescripción(rs.getString(3));
                  Producto.setPrecio(rs.getInt(4));
                   lista.add(Producto);
              }
            
        }catch (SQLException e){
            System.out.println("error al listar:"+ e);
        }
            return lista;
            //método agregar
       }
    public void registrar(producto producto){
        String sql= "insert into producto(nombre,descripcion,precio)values (?,?,?,)";
        try {
            cx= conexiondb.conectar();
            ps=cx.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripción());
            ps.setInt(3, producto.getPrecio());
            ps.executeUpdate();
            
            
            
        } catch (SQLException e) {
            System.out.println("error al agregar:" + e);
            
        }
    }//fin del método agregar
    //método actualizar
    public void actualizar(producto producto){
        String sql = "Update producto set nombre=?, descripción=?,precio=? where IdProducto=?";
        try {
            cx= conexiondb.conectar();
            ps=cx.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripción());
            ps.setInt(3, producto.getPrecio());
             ps.setInt(4, producto.getIdProducto());
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("error en actualizarDao"+e);
        }
        
    }//fin del metodo actualizar
    //metodo eliminar
    public void eliminar(int IdProducto){
        String sql ="delete from producto where IdProducto="+IdProducto;
        try {
             cx= conexiondb.conectar();
            ps=cx.prepareStatement(sql);
            ps.executeUpdate();
            
             } catch (SQLException e) {
                 System.out.println("error en eliminarDao"+e);
                 //fin del metodo eliminar
        }
    }
}//fin de la clase productoDao


