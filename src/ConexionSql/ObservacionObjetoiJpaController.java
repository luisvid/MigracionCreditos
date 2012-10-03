/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionAccess.exceptions.NonexistentEntityException;
import ConexionAccess.exceptions.PreexistingEntityException;
import Entidades.ObservacionObjetoi;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class ObservacionObjetoiJpaController implements Serializable {

    public ObservacionObjetoiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObservacionObjetoi observacionObjetoi) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi objetoiId = observacionObjetoi.getObjetoiId();
            if (objetoiId != null) {
                objetoiId = em.getReference(objetoiId.getClass(), objetoiId.getId());
                observacionObjetoi.setObjetoiId(objetoiId);
            }
            em.persist(observacionObjetoi);
            if (objetoiId != null) {
                objetoiId.getObservacionObjetoiCollection().add(observacionObjetoi);
                objetoiId = em.merge(objetoiId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObservacionObjetoi(observacionObjetoi.getId()) != null) {
                throw new PreexistingEntityException("ObservacionObjetoi " + observacionObjetoi + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObservacionObjetoi observacionObjetoi) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObservacionObjetoi persistentObservacionObjetoi = em.find(ObservacionObjetoi.class, observacionObjetoi.getId());
            Objetoi objetoiIdOld = persistentObservacionObjetoi.getObjetoiId();
            Objetoi objetoiIdNew = observacionObjetoi.getObjetoiId();
            if (objetoiIdNew != null) {
                objetoiIdNew = em.getReference(objetoiIdNew.getClass(), objetoiIdNew.getId());
                observacionObjetoi.setObjetoiId(objetoiIdNew);
            }
            observacionObjetoi = em.merge(observacionObjetoi);
            if (objetoiIdOld != null && !objetoiIdOld.equals(objetoiIdNew)) {
                objetoiIdOld.getObservacionObjetoiCollection().remove(observacionObjetoi);
                objetoiIdOld = em.merge(objetoiIdOld);
            }
            if (objetoiIdNew != null && !objetoiIdNew.equals(objetoiIdOld)) {
                objetoiIdNew.getObservacionObjetoiCollection().add(observacionObjetoi);
                objetoiIdNew = em.merge(objetoiIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = observacionObjetoi.getId();
                if (findObservacionObjetoi(id) == null) {
                    throw new NonexistentEntityException("The observacionObjetoi with id " + id + " no longer exists.");
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
            ObservacionObjetoi observacionObjetoi;
            try {
                observacionObjetoi = em.getReference(ObservacionObjetoi.class, id);
                observacionObjetoi.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The observacionObjetoi with id " + id + " no longer exists.", enfe);
            }
            Objetoi objetoiId = observacionObjetoi.getObjetoiId();
            if (objetoiId != null) {
                objetoiId.getObservacionObjetoiCollection().remove(observacionObjetoi);
                objetoiId = em.merge(objetoiId);
            }
            em.remove(observacionObjetoi);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObservacionObjetoi> findObservacionObjetoiEntities() {
        return findObservacionObjetoiEntities(true, -1, -1);
    }

    public List<ObservacionObjetoi> findObservacionObjetoiEntities(int maxResults, int firstResult) {
        return findObservacionObjetoiEntities(false, maxResults, firstResult);
    }

    private List<ObservacionObjetoi> findObservacionObjetoiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObservacionObjetoi as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObservacionObjetoi findObservacionObjetoi(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObservacionObjetoi.class, id);
        } finally {
            em.close();
        }
    }

    public int getObservacionObjetoiCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObservacionObjetoi as o");
            return ((Long) q.getSingleResult()).intValue();
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
            //boolean statement = 
            conn.createStatement().execute(string);
           
            
        } finally {
            //System.out.println(string);
            //em.close();
        }
    } 
    
}
