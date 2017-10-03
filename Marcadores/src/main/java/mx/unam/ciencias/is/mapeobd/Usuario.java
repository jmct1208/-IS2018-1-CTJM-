/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.mapeobd;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *
 * @author jesus
 */
@Entity
@Table(name="usuario")
public class Usuario {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idusuario;
    
    @Column(name = "nickname")
    private String nickname;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "contrasena")
    private String contrasena;
    
    @OneToMany(mappedBy = "usuario")
    private Set <Marcador> marcadores;
    
    public Usuario(){}
    
    public int getIdUsuario(){
        return this.idusuario;
    }
    
    public String getNickname(){
        return this.nickname;
    }
    
    public String getCorreo(){
        return this.correo;
    }
    
    public String getContrasena(){
        return this.contrasena;
    }
    
    public Set<Marcador> getMarcadores(){
        return this.marcadores;
    }
    
    public void setIdUsuario(int idusuario){
        this.idusuario = idusuario;
    }
    
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    public void setMarcadores(Set<Marcador> marcadores){
        this.marcadores = marcadores;
    }
}
