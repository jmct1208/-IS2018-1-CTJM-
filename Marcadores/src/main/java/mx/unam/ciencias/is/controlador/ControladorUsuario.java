/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.controlador;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.unam.ciencias.is.mapeobd.Usuario;
import mx.unam.ciencias.is.modelo.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author jesus
 */
@Controller
public class ControladorUsuario {
    @Autowired
    UsuarioDAO usuario_db;
    
    @RequestMapping(value= "/guardarUsuario", method = RequestMethod.GET)
     public String guardaUsuario(HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        Usuario u = usuario_db.getUsuario(correo);
        if(u == null){
            Usuario us = new Usuario();
            us.setNickname(nickname);
            us.setCorreo(correo);
            us.setContrasena(contrasena);
            usuario_db.guardar(u);
        }
        if(ma==null){
            Marcador m  = new Marcador();
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setNombre(nombre);
            m.setDescripcion(descripcion);
            marcador_db.guardar(m);
        
        }
        return "redirect:/";
    }
}
