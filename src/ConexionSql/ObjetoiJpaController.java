/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Objetoi;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author luisv
 */
public class ObjetoiJpaController implements Serializable {

    public ObjetoiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetoi objetoi) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objetoi);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoi(objetoi.getId()) != null) {
                throw new PreexistingEntityException("Objetoi " + objetoi + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetoi objetoi) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            objetoi = em.merge(objetoi);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoi.getId();
                if (findObjetoi(id) == null) {
                    throw new NonexistentEntityException("The objetoi with id " + id + " no longer exists.");
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
            Objetoi objetoi;
            try {
                objetoi = em.getReference(Objetoi.class, id);
                objetoi.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoi with id " + id + " no longer exists.", enfe);
            }
            em.remove(objetoi);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetoi> findObjetoiEntities() {
        return findObjetoiEntities(true, -1, -1);
    }

    public List<Objetoi> findObjetoiEntities(int maxResults, int firstResult) {
        return findObjetoiEntities(false, maxResults, firstResult);
    }

    private List<Objetoi> findObjetoiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Objetoi as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Objetoi findObjetoi(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetoi.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoiCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Objetoi as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
