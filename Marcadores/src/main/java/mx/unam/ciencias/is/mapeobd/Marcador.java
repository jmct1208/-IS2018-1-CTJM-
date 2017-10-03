/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.mapeobd;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
/**
 * Clase que modela un marcador a partir de la tabla marcador
 * @author jonathan
 */
@Entity
@Table(name="marcador")
public class Marcador {
    @Id@GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "idmarcador")
    private int marcador_id;
    
    @Column(name = "nombreM")
    private String nombreM;
    
    @Column(name = "latitud")
    private double latitud;
    
    @Column(name = "longitud")
    private double longitud;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario idusuario;
    
    //Aqui va tu codigo
    public Marcador(){}
    
    /**Nos da el id del marcador
     * @return el id del marcador 
    */
    public int getMarcador_id() {
        return marcador_id;
    }
    
    /** Pone el id del marcador.
      @param marcador_id 
    */
    public void setMarcador_id(int marcador_id) {
        this.marcador_id = marcador_id;
    }
    
    public void setNombreM(String nombreM){
        this.nombreM = nombreM;
    }
    
    public String getNombreM(){
       return nombreM; 
    }
    
    public void setLatitud(double latitud){
        this.latitud = latitud;
    }
    
    public double getLatitud(){
        return this.latitud;
    }

    public void setLongitud(double longitud){
        this.longitud = longitud;
    }
    
    public double getLongitud(){
        return this.longitud;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
}
