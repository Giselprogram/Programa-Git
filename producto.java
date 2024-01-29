/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jhon Gerardo
 */
public class producto {
    private int IdProducto;
    private String nombre;
    private String descripción;
    private int precio;
    
    //constructor vacio

    public producto() {
    }
//constructor para registrar
    public producto(String nombre, String descripción, int precio) {
        this.nombre = nombre;
        this.descripción = descripción;
        this.precio = precio;
    }
    
    //constructor actualizar

    public producto(int IdProducto,String nombre, String descripción, int precio) {
        this.IdProducto = IdProducto;
        this.nombre = nombre;
        this.descripción = descripción;
        this.precio = precio;
    }
//getter and setter
    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }
     public String getNombre() {
        return nombre;
    }
     public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }
  
    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
   }
