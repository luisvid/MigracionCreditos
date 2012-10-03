/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.ObjetoiBonificacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import Entidades.Bonificacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class ObjetoiBonificacionJpaController implements Serializable {

    public ObjetoiBonificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjetoiBonificacion objetoiBonificacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi idCredito = objetoiBonificacion.getIdCredito();
            if (idCredito != null) {
                idCredito = em.getReference(idCredito.getClass(), idCredito.getId());
                objetoiBonificacion.setIdCredito(idCredito);
            }
            Bonificacion idBonificacion = objetoiBonificacion.getIdBonificacion();
            if (idBonificacion != null) {
                idBonificacion = em.getReference(idBonificacion.getClass(), idBonificacion.getId());
                objetoiBonificacion.setIdBonificacion(idBonificacion);
            }
            em.persist(objetoiBonificacion);
            if (idCredito != null) {
                idCredito.getObjetoiBonificacionCollection().add(objetoiBonificacion);
                idCredito = em.merge(idCredito);
            }
            if (idBonificacion != null) {
                idBonificacion.getObjetoiBonificacionCollection().add(objetoiBonificacion);
                idBonificacion = em.merge(idBonificacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoiBonificacion(objetoiBonificacion.getId()) != null) {
                throw new PreexistingEntityException("ObjetoiBonificacion " + objetoiBonificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjetoiBonificacion objetoiBonificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObjetoiBonificacion persistentObjetoiBonificacion = em.find(ObjetoiBonificacion.class, objetoiBonificacion.getId());
            Objetoi idCreditoOld = persistentObjetoiBonificacion.getIdCredito();
            Objetoi idCreditoNew = objetoiBonificacion.getIdCredito();
            Bonificacion idBonificacionOld = persistentObjetoiBonificacion.getIdBonificacion();
            Bonificacion idBonificacionNew = objetoiBonificacion.getIdBonificacion();
            if (idCreditoNew != null) {
                idCreditoNew = em.getReference(idCreditoNew.getClass(), idCreditoNew.getId());
                objetoiBonificacion.setIdCredito(idCreditoNew);
            }
            if (idBonificacionNew != null) {
                idBonificacionNew = em.getReference(idBonificacionNew.getClass(), idBonificacionNew.getId());
                objetoiBonificacion.setIdBonificacion(idBonificacionNew);
            }
            objetoiBonificacion = em.merge(objetoiBonificacion);
            if (idCreditoOld != null && !idCreditoOld.equals(idCreditoNew)) {
                idCreditoOld.getObjetoiBonificacionCollection().remove(objetoiBonificacion);
                idCreditoOld = em.merge(idCreditoOld);
            }
            if (idCreditoNew != null && !idCreditoNew.equals(idCreditoOld)) {
                idCreditoNew.getObjetoiBonificacionCollection().add(objetoiBonificacion);
                idCreditoNew = em.merge(idCreditoNew);
            }
            if (idBonificacionOld != null && !idBonificacionOld.equals(idBonificacionNew)) {
                idBonificacionOld.getObjetoiBonificacionCollection().remove(objetoiBonificacion);
                idBonificacionOld = em.merge(idBonificacionOld);
            }
            if (idBonificacionNew != null && !idBonificacionNew.equals(idBonificacionOld)) {
                idBonificacionNew.getObjetoiBonificacionCollection().add(objetoiBonificacion);
                idBonificacionNew = em.merge(idBonificacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoiBonificacion.getId();
                if (findObjetoiBonificacion(id) == null) {
                    throw new NonexistentEntityException("The objetoiBonificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObjetoiBonificacion objetoiBonificacion;
            try {
                objetoiBonificacion = em.getReference(ObjetoiBonificacion.class, id);
                objetoiBonificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoiBonificacion with id " + id + " no longer exists.", enfe);
            }
            Objetoi idCredito = objetoiBonificacion.getIdCredito();
            if (idCredito != null) {
                idCredito.getObjetoiBonificacionCollection().remove(objetoiBonificacion);
                idCredito = em.merge(idCredito);
            }
            Bonificacion idBonificacion = objetoiBonificacion.getIdBonificacion();
            if (idBonificacion != null) {
                idBonificacion.getObjetoiBonificacionCollection().remove(objetoiBonificacion);
                idBonificacion = em.merge(idBonificacion);
            }
            em.remove(objetoiBonificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjetoiBonificacion> findObjetoiBonificacionEntities() {
        return findObjetoiBonificacionEntities(true, -1, -1);
    }

    public List<ObjetoiBonificacion> findObjetoiBonificacionEntities(int maxResults, int firstResult) {
        return findObjetoiBonificacionEntities(false, maxResults, firstResult);
    }

    private List<ObjetoiBonificacion> findObjetoiBonificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObjetoiBonificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjetoiBonificacion findObjetoiBonificacion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjetoiBonificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoiBonificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObjetoiBonificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();

        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA3_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            boolean statement =conn.createStatement().execute(string);
           
            
        } finally {
            em.close();
        }
    }

    public List<ObjetoiBonificacion> findByCredito(Objetoi objetoi) {
         EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("ObjetoiBonificacion.findByCredito_id");
            q.setParameter("Objetoi", objetoi);
            List<ObjetoiBonificacion> o = q.getResultList();
          
            return o;
        } finally {
            em.close();
        }
    }
}