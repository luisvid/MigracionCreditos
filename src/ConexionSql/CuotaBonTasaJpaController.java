/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.CuotaBonTasa;
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
public class CuotaBonTasaJpaController implements Serializable {

    public CuotaBonTasaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CuotaBonTasa cuotaBonTasa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonTasa bonTasaid = cuotaBonTasa.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid = em.getReference(bonTasaid.getClass(), bonTasaid.getId());
                cuotaBonTasa.setBonTasaid(bonTasaid);
            }
            em.persist(cuotaBonTasa);
            if (bonTasaid != null) {
                bonTasaid.getCuotaBonTasaCollection().add(cuotaBonTasa);
                bonTasaid = em.merge(bonTasaid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCuotaBonTasa(cuotaBonTasa.getId()) != null) {
                throw new PreexistingEntityException("CuotaBonTasa " + cuotaBonTasa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CuotaBonTasa cuotaBonTasa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CuotaBonTasa persistentCuotaBonTasa = em.find(CuotaBonTasa.class, cuotaBonTasa.getId());
            BonTasa bonTasaidOld = persistentCuotaBonTasa.getBonTasaid();
            BonTasa bonTasaidNew = cuotaBonTasa.getBonTasaid();
            if (bonTasaidNew != null) {
                bonTasaidNew = em.getReference(bonTasaidNew.getClass(), bonTasaidNew.getId());
                cuotaBonTasa.setBonTasaid(bonTasaidNew);
            }
            cuotaBonTasa = em.merge(cuotaBonTasa);
            if (bonTasaidOld != null && !bonTasaidOld.equals(bonTasaidNew)) {
                bonTasaidOld.getCuotaBonTasaCollection().remove(cuotaBonTasa);
                bonTasaidOld = em.merge(bonTasaidOld);
            }
            if (bonTasaidNew != null && !bonTasaidNew.equals(bonTasaidOld)) {
                bonTasaidNew.getCuotaBonTasaCollection().add(cuotaBonTasa);
                bonTasaidNew = em.merge(bonTasaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = cuotaBonTasa.getId();
                if (findCuotaBonTasa(id) == null) {
                    throw new NonexistentEntityException("The cuotaBonTasa with id " + id + " no longer exists.");
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
            CuotaBonTasa cuotaBonTasa;
            try {
                cuotaBonTasa = em.getReference(CuotaBonTasa.class, id);
                cuotaBonTasa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuotaBonTasa with id " + id + " no longer exists.", enfe);
            }
            BonTasa bonTasaid = cuotaBonTasa.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid.getCuotaBonTasaCollection().remove(cuotaBonTasa);
                bonTasaid = em.merge(bonTasaid);
            }
            em.remove(cuotaBonTasa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CuotaBonTasa> findCuotaBonTasaEntities() {
        return findCuotaBonTasaEntities(true, -1, -1);
    }

    public List<CuotaBonTasa> findCuotaBonTasaEntities(int maxResults, int firstResult) {
        return findCuotaBonTasaEntities(false, maxResults, firstResult);
    }

    private List<CuotaBonTasa> findCuotaBonTasaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CuotaBonTasa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CuotaBonTasa findCuotaBonTasa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CuotaBonTasa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuotaBonTasaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CuotaBonTasa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
