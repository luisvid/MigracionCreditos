/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.TitularesBonTasa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.BonTasa;

/**
 *
 * @author analian
 */
public class TitularesBonTasaJpaController implements Serializable {

    public TitularesBonTasaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TitularesBonTasa titularesBonTasa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonTasa bonTasaid = titularesBonTasa.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid = em.getReference(bonTasaid.getClass(), bonTasaid.getId());
                titularesBonTasa.setBonTasaid(bonTasaid);
            }
            em.persist(titularesBonTasa);
            if (bonTasaid != null) {
                bonTasaid.getTitularesBonTasaCollection().add(titularesBonTasa);
                bonTasaid = em.merge(bonTasaid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTitularesBonTasa(titularesBonTasa.getId()) != null) {
                throw new PreexistingEntityException("TitularesBonTasa " + titularesBonTasa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TitularesBonTasa titularesBonTasa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TitularesBonTasa persistentTitularesBonTasa = em.find(TitularesBonTasa.class, titularesBonTasa.getId());
            BonTasa bonTasaidOld = persistentTitularesBonTasa.getBonTasaid();
            BonTasa bonTasaidNew = titularesBonTasa.getBonTasaid();
            if (bonTasaidNew != null) {
                bonTasaidNew = em.getReference(bonTasaidNew.getClass(), bonTasaidNew.getId());
                titularesBonTasa.setBonTasaid(bonTasaidNew);
            }
            titularesBonTasa = em.merge(titularesBonTasa);
            if (bonTasaidOld != null && !bonTasaidOld.equals(bonTasaidNew)) {
                bonTasaidOld.getTitularesBonTasaCollection().remove(titularesBonTasa);
                bonTasaidOld = em.merge(bonTasaidOld);
            }
            if (bonTasaidNew != null && !bonTasaidNew.equals(bonTasaidOld)) {
                bonTasaidNew.getTitularesBonTasaCollection().add(titularesBonTasa);
                bonTasaidNew = em.merge(bonTasaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = titularesBonTasa.getId();
                if (findTitularesBonTasa(id) == null) {
                    throw new NonexistentEntityException("The titularesBonTasa with id " + id + " no longer exists.");
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
            TitularesBonTasa titularesBonTasa;
            try {
                titularesBonTasa = em.getReference(TitularesBonTasa.class, id);
                titularesBonTasa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The titularesBonTasa with id " + id + " no longer exists.", enfe);
            }
            BonTasa bonTasaid = titularesBonTasa.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid.getTitularesBonTasaCollection().remove(titularesBonTasa);
                bonTasaid = em.merge(bonTasaid);
            }
            em.remove(titularesBonTasa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TitularesBonTasa> findTitularesBonTasaEntities() {
        return findTitularesBonTasaEntities(true, -1, -1);
    }

    public List<TitularesBonTasa> findTitularesBonTasaEntities(int maxResults, int firstResult) {
        return findTitularesBonTasaEntities(false, maxResults, firstResult);
    }

    private List<TitularesBonTasa> findTitularesBonTasaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TitularesBonTasa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TitularesBonTasa findTitularesBonTasa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TitularesBonTasa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTitularesBonTasaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TitularesBonTasa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
