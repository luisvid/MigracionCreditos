/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionAccess.exceptions.NonexistentEntityException;
import ConexionAccess.exceptions.PreexistingEntityException;
import Entidades.ObjetoiComportamiento;
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
public class ObjetoiComportamientoJpaController implements Serializable {

    public ObjetoiComportamientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjetoiComportamiento objetoiComportamiento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi objetoiId = objetoiComportamiento.getObjetoiId();
            if (objetoiId != null) {
                objetoiId = em.getReference(objetoiId.getClass(), objetoiId.getId());
                objetoiComportamiento.setObjetoiId(objetoiId);
            }
            em.persist(objetoiComportamiento);
            if (objetoiId != null) {
                objetoiId.getObjetoiComportamientoCollection().add(objetoiComportamiento);
                objetoiId = em.merge(objetoiId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoiComportamiento(objetoiComportamiento.getId()) != null) {
                throw new PreexistingEntityException("ObjetoiComportamiento " + objetoiComportamiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjetoiComportamiento objetoiComportamiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObjetoiComportamiento persistentObjetoiComportamiento = em.find(ObjetoiComportamiento.class, objetoiComportamiento.getId());
            Objetoi objetoiIdOld = persistentObjetoiComportamiento.getObjetoiId();
            Objetoi objetoiIdNew = objetoiComportamiento.getObjetoiId();
            if (objetoiIdNew != null) {
                objetoiIdNew = em.getReference(objetoiIdNew.getClass(), objetoiIdNew.getId());
                objetoiComportamiento.setObjetoiId(objetoiIdNew);
            }
            objetoiComportamiento = em.merge(objetoiComportamiento);
            if (objetoiIdOld != null && !objetoiIdOld.equals(objetoiIdNew)) {
                objetoiIdOld.getObjetoiComportamientoCollection().remove(objetoiComportamiento);
                objetoiIdOld = em.merge(objetoiIdOld);
            }
            if (objetoiIdNew != null && !objetoiIdNew.equals(objetoiIdOld)) {
                objetoiIdNew.getObjetoiComportamientoCollection().add(objetoiComportamiento);
                objetoiIdNew = em.merge(objetoiIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoiComportamiento.getId();
                if (findObjetoiComportamiento(id) == null) {
                    throw new NonexistentEntityException("The objetoiComportamiento with id " + id + " no longer exists.");
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
            ObjetoiComportamiento objetoiComportamiento;
            try {
                objetoiComportamiento = em.getReference(ObjetoiComportamiento.class, id);
                objetoiComportamiento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoiComportamiento with id " + id + " no longer exists.", enfe);
            }
            Objetoi objetoiId = objetoiComportamiento.getObjetoiId();
            if (objetoiId != null) {
                objetoiId.getObjetoiComportamientoCollection().remove(objetoiComportamiento);
                objetoiId = em.merge(objetoiId);
            }
            em.remove(objetoiComportamiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjetoiComportamiento> findObjetoiComportamientoEntities() {
        return findObjetoiComportamientoEntities(true, -1, -1);
    }

    public List<ObjetoiComportamiento> findObjetoiComportamientoEntities(int maxResults, int firstResult) {
        return findObjetoiComportamientoEntities(false, maxResults, firstResult);
    }

    private List<ObjetoiComportamiento> findObjetoiComportamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObjetoiComportamiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjetoiComportamiento findObjetoiComportamiento(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjetoiComportamiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoiComportamientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObjetoiComportamiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA4_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");             
            conn.createStatement().execute(string);             conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF"); 
           
            
        } finally {
            em.close();
        }
    }
}
