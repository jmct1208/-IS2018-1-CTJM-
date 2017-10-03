/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.modelo;
import mx.unam.ciencias.is.mapeobd.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import org.hibernate.HibernateException;
/**
 *
 * @author jesus
 */
public class UsuarioDAO {
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
      /**
     * Guarda un usuario a la base de datos 
     * @param usuario el usuario a guardar.
     */
    public void guardar(Usuario usuario) {
        //se inicia la sesion
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //guardamos el marcador
            session.persist(usuario);
            tx.commit();
        }
        catch (HibernateException e) {
            //Se regresa a un estado consistente 
            if (tx!=null){ 
                tx.rollback();
            }
            e.printStackTrace(); 
        }
        finally {
            //cerramos simpre la sesion
            session.close();
        }
    
    }
    
     /**
     * Regresa la lista de todos los usuarios en la base de datos
     * @return la lista que contiene a todos los usuario de la base de datos
     */
    public List<Usuario> getUsuarios(){
        List<Usuario> usuarios = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "SELECT * FROM usuario";
            SQLQuery query = session.createSQLQuery(s);
            query.addEntity(Usuario.class);
            usuarios = query.list();
        }
        catch(HibernateException e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return usuarios;
    }
    
       /**
     * Regresa el usuario con el id dado
     * @param id del usuario 
     * @return el usuario con ese id
     */
    public Usuario getUsuarioID(int id) {
        Usuario u = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "FROM Usuario WHERE idusuario = :id";
            Query query  = session.createQuery(s);
            query.setParameter("id", id);
            u = (Usuario)query.uniqueResult();
            tx.commit();
        }
        catch(Exception e)
        {
         if(tx!=null){
             tx.rollback();
         }
         e.printStackTrace();
        }
        finally{
        session.close();
        }
        return u;
    }
    
      /**
     * Elimina el marcador de la base de datos
     * @param marcador el marcador a eliminar
     */
    public void eliminar(Usuario usuario) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "delete from Usuario where id = :id";
            Query query  = session.createQuery(s);
            query.setParameter("id", usuario.getIdUsuario());
            query.executeUpdate();
            tx.commit();
        }
        catch(Exception e)
        {
         if(tx!=null){
             tx.rollback();
         }
         e.printStackTrace();
        }
        finally{
        session.close();
        }
    }
    
    public Usuario getUsuario(String correo){
        Usuario salida = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Usuario where correo = :c";
            Query query = session.createQuery(hql);
            query.setParameter("c",correo);
            salida = (Usuario)query.uniqueResult();
            tx.commit();
        }
        catch(Exception e)
        {
         if(tx!=null){
             tx.rollback();
         }
         e.printStackTrace();
        }
        finally{
        session.close();
        }
        return salida;
    }
    
    /**
     * Actualiza el marcardor en la base de datos
     * @param marcador con los nuevos valores 
     */
    public void actualizar(Usuario usuario) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int idUsuario = usuario.getIdUsuario();
        String nicknameUsuario = usuario.getNickname();
        String correoUsuario = usuario.getCorreo();
        String contrasenaUsuario = usuario.getContrasena();        
        try{ 
            tx = session.beginTransaction();
            String s = "update Usuario set nickname =: nick, correo =: email,"
                    + "contrasena =:password"
                    + "where idusuario =: id";
            Query query = session.createQuery(s);
            query.setParameter("id", idUsuario);
            query.setParameter("nick",nicknameUsuario);
            query.setParameter("email", correoUsuario);
            query.setParameter("password", contrasenaUsuario);
            int resultado = query.executeUpdate();
            tx.commit();
        }
        catch(Exception e)
        {
         if(tx!=null){
             tx.rollback();
         }
         e.printStackTrace();
        }
        finally{
        session.close();
        }
    }
}
