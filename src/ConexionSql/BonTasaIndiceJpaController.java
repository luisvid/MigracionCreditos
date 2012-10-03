/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.BonTasaIndice;
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
public class BonTasaIndiceJpaController implements Serializable {

    public BonTasaIndiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonTasaIndice bonTasaIndice) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bonTasaIndice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonTasaIndice(bonTasaIndice.getId()) != null) {
                throw new PreexistingEntityException("BonTasaIndice " + bonTasaIndice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonTasaIndice bonTasaIndice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bonTasaIndice = em.merge(bonTasaIndice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonTasaIndice.getId();
                if (findBonTasaIndice(id) == null) {
                    throw new NonexistentEntityException("The bonTasaIndice with id " + id + " no longer exists.");
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
            BonTasaIndice bonTasaIndice;
            try {
                bonTasaIndice = em.getReference(BonTasaIndice.class, id);
                bonTasaIndice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonTasaIndice with id " + id + " no longer exists.", enfe);
            }
            em.remove(bonTasaIndice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonTasaIndice> findBonTasaIndiceEntities() {
        return findBonTasaIndiceEntities(true, -1, -1);
    }

    public List<BonTasaIndice> findBonTasaIndiceEntities(int maxResults, int firstResult) {
        return findBonTasaIndiceEntities(false, maxResults, firstResult);
    }

    private List<BonTasaIndice> findBonTasaIndiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BonTasaIndice as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BonTasaIndice findBonTasaIndice(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonTasaIndice.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonTasaIndiceCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BonTasaIndice as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
