/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.DomicilioObjetoi;
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
public class DomicilioObjetoiJpaController implements Serializable {

    public DomicilioObjetoiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DomicilioObjetoi domicilioObjetoi) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi objetoiId = domicilioObjetoi.getObjetoiId();
            if (objetoiId != null) {
                objetoiId = em.getReference(objetoiId.getClass(), objetoiId.getId());
                domicilioObjetoi.setObjetoiId(objetoiId);
            }
            em.persist(domicilioObjetoi);
            if (objetoiId != null) {
                objetoiId.getDomicilioObjetoiCollection().add(domicilioObjetoi);
                objetoiId = em.merge(objetoiId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDomicilioObjetoi(domicilioObjetoi.getId()) != null) {
                throw new PreexistingEntityException("DomicilioObjetoi " + domicilioObjetoi + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DomicilioObjetoi domicilioObjetoi) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DomicilioObjetoi persistentDomicilioObjetoi = em.find(DomicilioObjetoi.class, domicilioObjetoi.getId());
            Objetoi objetoiIdOld = persistentDomicilioObjetoi.getObjetoiId();
            Objetoi objetoiIdNew = domicilioObjetoi.getObjetoiId();
            if (objetoiIdNew != null) {
                objetoiIdNew = em.getReference(objetoiIdNew.getClass(), objetoiIdNew.getId());
                domicilioObjetoi.setObjetoiId(objetoiIdNew);
            }
            domicilioObjetoi = em.merge(domicilioObjetoi);
            if (objetoiIdOld != null && !objetoiIdOld.equals(objetoiIdNew)) {
                objetoiIdOld.getDomicilioObjetoiCollection().remove(domicilioObjetoi);
                objetoiIdOld = em.merge(objetoiIdOld);
            }
            if (objetoiIdNew != null && !objetoiIdNew.equals(objetoiIdOld)) {
                objetoiIdNew.getDomicilioObjetoiCollection().add(domicilioObjetoi);
                objetoiIdNew = em.merge(objetoiIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = domicilioObjetoi.getId();
                if (findDomicilioObjetoi(id) == null) {
                    throw new NonexistentEntityException("The domicilioObjetoi with id " + id + " no longer exists.");
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
            DomicilioObjetoi domicilioObjetoi;
            try {
                domicilioObjetoi = em.getReference(DomicilioObjetoi.class, id);
                domicilioObjetoi.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The domicilioObjetoi with id " + id + " no longer exists.", enfe);
            }
            Objetoi objetoiId = domicilioObjetoi.getObjetoiId();
            if (objetoiId != null) {
                objetoiId.getDomicilioObjetoiCollection().remove(domicilioObjetoi);
                objetoiId = em.merge(objetoiId);
            }
            em.remove(domicilioObjetoi);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DomicilioObjetoi> findDomicilioObjetoiEntities() {
        return findDomicilioObjetoiEntities(true, -1, -1);
    }

    public List<DomicilioObjetoi> findDomicilioObjetoiEntities(int maxResults, int firstResult) {
        return findDomicilioObjetoiEntities(false, maxResults, firstResult);
    }

    private List<DomicilioObjetoi> findDomicilioObjetoiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DomicilioObjetoi as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DomicilioObjetoi findDomicilioObjetoi(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DomicilioObjetoi.class, id);
        } finally {
            em.close();
        }
    }

    public int getDomicilioObjetoiCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from DomicilioObjetoi as o");
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
            conn.createStatement().execute(string);             
            conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF"); 
           
            
        } finally {
            em.close();
        }
    }
     }
