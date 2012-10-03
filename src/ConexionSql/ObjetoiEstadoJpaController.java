/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.ObjetoiEstado;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import Entidades.Estado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class ObjetoiEstadoJpaController implements Serializable {

    public ObjetoiEstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjetoiEstado objetoiEstado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi objetoiId = objetoiEstado.getObjetoiId();
            if (objetoiId != null) {
                objetoiId = em.getReference(objetoiId.getClass(), objetoiId.getId());
                objetoiEstado.setObjetoiId(objetoiId);
            }
            Estado estadoidEstado = objetoiEstado.getEstadoidEstado();
            if (estadoidEstado != null) {
                estadoidEstado = em.getReference(estadoidEstado.getClass(), estadoidEstado.getIdEstado());
                objetoiEstado.setEstadoidEstado(estadoidEstado);
            }
            em.persist(objetoiEstado);
            if (objetoiId != null) {
                objetoiId.getObjetoiEstadoCollection().add(objetoiEstado);
                objetoiId = em.merge(objetoiId);
            }
            if (estadoidEstado != null) {
                estadoidEstado.getObjetoiEstadoCollection().add(objetoiEstado);
                estadoidEstado = em.merge(estadoidEstado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoiEstado(objetoiEstado.getId()) != null) {
                throw new PreexistingEntityException("ObjetoiEstado " + objetoiEstado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjetoiEstado objetoiEstado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObjetoiEstado persistentObjetoiEstado = em.find(ObjetoiEstado.class, objetoiEstado.getId());
            Objetoi objetoiIdOld = persistentObjetoiEstado.getObjetoiId();
            Objetoi objetoiIdNew = objetoiEstado.getObjetoiId();
            Estado estadoidEstadoOld = persistentObjetoiEstado.getEstadoidEstado();
            Estado estadoidEstadoNew = objetoiEstado.getEstadoidEstado();
            if (objetoiIdNew != null) {
                objetoiIdNew = em.getReference(objetoiIdNew.getClass(), objetoiIdNew.getId());
                objetoiEstado.setObjetoiId(objetoiIdNew);
            }
            if (estadoidEstadoNew != null) {
                estadoidEstadoNew = em.getReference(estadoidEstadoNew.getClass(), estadoidEstadoNew.getIdEstado());
                objetoiEstado.setEstadoidEstado(estadoidEstadoNew);
            }
            objetoiEstado = em.merge(objetoiEstado);
            if (objetoiIdOld != null && !objetoiIdOld.equals(objetoiIdNew)) {
                objetoiIdOld.getObjetoiEstadoCollection().remove(objetoiEstado);
                objetoiIdOld = em.merge(objetoiIdOld);
            }
            if (objetoiIdNew != null && !objetoiIdNew.equals(objetoiIdOld)) {
                objetoiIdNew.getObjetoiEstadoCollection().add(objetoiEstado);
                objetoiIdNew = em.merge(objetoiIdNew);
            }
            if (estadoidEstadoOld != null && !estadoidEstadoOld.equals(estadoidEstadoNew)) {
                estadoidEstadoOld.getObjetoiEstadoCollection().remove(objetoiEstado);
                estadoidEstadoOld = em.merge(estadoidEstadoOld);
            }
            if (estadoidEstadoNew != null && !estadoidEstadoNew.equals(estadoidEstadoOld)) {
                estadoidEstadoNew.getObjetoiEstadoCollection().add(objetoiEstado);
                estadoidEstadoNew = em.merge(estadoidEstadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoiEstado.getId();
                if (findObjetoiEstado(id) == null) {
                    throw new NonexistentEntityException("The objetoiEstado with id " + id + " no longer exists.");
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
            ObjetoiEstado objetoiEstado;
            try {
                objetoiEstado = em.getReference(ObjetoiEstado.class, id);
                objetoiEstado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoiEstado with id " + id + " no longer exists.", enfe);
            }
            Objetoi objetoiId = objetoiEstado.getObjetoiId();
            if (objetoiId != null) {
                objetoiId.getObjetoiEstadoCollection().remove(objetoiEstado);
                objetoiId = em.merge(objetoiId);
            }
            Estado estadoidEstado = objetoiEstado.getEstadoidEstado();
            if (estadoidEstado != null) {
                estadoidEstado.getObjetoiEstadoCollection().remove(objetoiEstado);
                estadoidEstado = em.merge(estadoidEstado);
            }
            em.remove(objetoiEstado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjetoiEstado> findObjetoiEstadoEntities() {
        return findObjetoiEstadoEntities(true, -1, -1);
    }

    public List<ObjetoiEstado> findObjetoiEstadoEntities(int maxResults, int firstResult) {
        return findObjetoiEstadoEntities(false, maxResults, firstResult);
    }

    private List<ObjetoiEstado> findObjetoiEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObjetoiEstado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjetoiEstado findObjetoiEstado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjetoiEstado.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoiEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObjetoiEstado as o");
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
    
}
