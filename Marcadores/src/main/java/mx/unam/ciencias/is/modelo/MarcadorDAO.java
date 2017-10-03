/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.modelo;
import mx.unam.ciencias.is.mapeobd.Marcador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import org.hibernate.HibernateException;
/**
 *
 * @author jonathan
 */
public class MarcadorDAO {
    
    /*Sesion para conectarnos a la base de datos*/
    private SessionFactory sessionFactory;
    
    /**
     * Inicializamos la sesion a la base de datos.
     * @param sessionFactory 
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Guarda un marcador a la base de datos 
     * @param marcador el marcador a guardar.
     */
    public void guardar(Marcador marcador) {
        //se inicia la sesion
        Session session = sessionFactory.openSession();
        //la transaccion a relizar
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //guardamos el marcador
            session.persist(marcador);
           
            tx.commit();
        }
        catch (Exception e) {
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
     * Regresa la lista de todos los marcadores en la base de datos
     * @return la lista que contiene a todos los marcadores de la base de datos
     */
    public List<Marcador> getMarcadores(){
        List<Marcador> marcadores = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "SELECT * FROM marcador";
            SQLQuery query = session.createSQLQuery(s);
            query.addEntity(Marcador.class);
            marcadores = query.list();
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
        return marcadores;
    }
    
    /**
     * Regresa el marcador con la longitud  y latitud dada. 
     * @param lattitud
     * @param longitud
     * @return el marcador con la longitud y latitud dada.
     */
    public Marcador getMarcador(double latitud, double longitud) {
        Marcador m = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "FROM Marcador WHERE latitud = :lat AND longitud = :long";
            Query query  = session.createQuery(s);
            query.setParameter("long", longitud);
            query.setParameter("lat", latitud);
            m = (Marcador)query.uniqueResult();
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
        return m;
    }
    
    /**
     * Regresa el marcador con el id dado
     * @param id del marcador 
     * @return el marcador con ese id
     */
    public Marcador getMarcadorId(int id) {
        Marcador m = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "FROM Marcador WHERE idmarcador = :id";
            Query query  = session.createQuery(s);
            query.setParameter("id", id);
            m = (Marcador)query.uniqueResult();
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
        return m;
    }
    
    
    /**
     * Elimina el marcador de la base de datos
     * @param marcador el marcador a eliminar
     */
    public void eliminar(Marcador marcador) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String s = "delete from Marcador where id = :id";
            Query query  = session.createQuery(s);
            query.setParameter("id", marcador.getMarcador_id());
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
    
    /**
     * Actualiza el marcardor en la base de datos
     * @param marcador con los nuevos valores 
     */
    public void actualizar(Marcador marcador) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int idMarcador = marcador.getMarcador_id();
        String nombreMarcador = marcador.getNombreM();
        double longitudMarcador = marcador.getLongitud();
        double latitudMarcador = marcador.getLatitud();
        String descripcionMarcador = marcador.getDescripcion();
        
        try{ 
            tx = session.beginTransaction();
            String s = "update Marcador set nombreM =: nombre"
                    + ", latitud =: lat, longitud =: long, descripcion =: desc"
                    + "where marcador_id =: id";
            Query query = session.createQuery(s);
            query.setParameter("id", idMarcador);
            query.setParameter("nombre",nombreMarcador);
            query.setParameter("long", longitudMarcador);
            query.setParameter("lat", latitudMarcador);
            query.setParameter("desc", descripcionMarcador);
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
