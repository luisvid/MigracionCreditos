/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.ObjetoiEmergencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;

/**
 *
 * @author analian
 */
public class ObjetoiEmergenciaJpaController implements Serializable {

    public ObjetoiEmergenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjetoiEmergencia objetoiEmergencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi creditoId = objetoiEmergencia.getCreditoId();
            if (creditoId != null) {
                creditoId = em.getReference(creditoId.getClass(), creditoId.getId());
                objetoiEmergencia.setCreditoId(creditoId);
            }
            em.persist(objetoiEmergencia);
            if (creditoId != null) {
                creditoId.getObjetoiEmergenciaCollection().add(objetoiEmergencia);
                creditoId = em.merge(creditoId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoiEmergencia(objetoiEmergencia.getId()) != null) {
                throw new PreexistingEntityException("ObjetoiEmergencia " + objetoiEmergencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjetoiEmergencia objetoiEmergencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObjetoiEmergencia persistentObjetoiEmergencia = em.find(ObjetoiEmergencia.class, objetoiEmergencia.getId());
            Objetoi creditoIdOld = persistentObjetoiEmergencia.getCreditoId();
            Objetoi creditoIdNew = objetoiEmergencia.getCreditoId();
            if (creditoIdNew != null) {
                creditoIdNew = em.getReference(creditoIdNew.getClass(), creditoIdNew.getId());
                objetoiEmergencia.setCreditoId(creditoIdNew);
            }
            objetoiEmergencia = em.merge(objetoiEmergencia);
            if (creditoIdOld != null && !creditoIdOld.equals(creditoIdNew)) {
                creditoIdOld.getObjetoiEmergenciaCollection().remove(objetoiEmergencia);
                creditoIdOld = em.merge(creditoIdOld);
            }
            if (creditoIdNew != null && !creditoIdNew.equals(creditoIdOld)) {
                creditoIdNew.getObjetoiEmergenciaCollection().add(objetoiEmergencia);
                creditoIdNew = em.merge(creditoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoiEmergencia.getId();
                if (findObjetoiEmergencia(id) == null) {
                    throw new NonexistentEntityException("The objetoiEmergencia with id " + id + " no longer exists.");
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
            ObjetoiEmergencia objetoiEmergencia;
            try {
                objetoiEmergencia = em.getReference(ObjetoiEmergencia.class, id);
                objetoiEmergencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoiEmergencia with id " + id + " no longer exists.", enfe);
            }
            Objetoi creditoId = objetoiEmergencia.getCreditoId();
            if (creditoId != null) {
                creditoId.getObjetoiEmergenciaCollection().remove(objetoiEmergencia);
                creditoId = em.merge(creditoId);
            }
            em.remove(objetoiEmergencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjetoiEmergencia> findObjetoiEmergenciaEntities() {
        return findObjetoiEmergenciaEntities(true, -1, -1);
    }

    public List<ObjetoiEmergencia> findObjetoiEmergenciaEntities(int maxResults, int firstResult) {
        return findObjetoiEmergenciaEntities(false, maxResults, firstResult);
    }

    private List<ObjetoiEmergencia> findObjetoiEmergenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObjetoiEmergencia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjetoiEmergencia findObjetoiEmergencia(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjetoiEmergencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoiEmergenciaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObjetoiEmergencia as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
