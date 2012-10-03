/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Emergencia;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.EmergenciaPeriodo;

/**
 *
 * @author analian
 */
public class EmergenciaJpaController implements Serializable {

    public EmergenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emergencia emergencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmergenciaPeriodo emergenciaPeriodoid = emergencia.getEmergenciaPeriodoid();
            if (emergenciaPeriodoid != null) {
                emergenciaPeriodoid = em.getReference(emergenciaPeriodoid.getClass(), emergenciaPeriodoid.getId());
                emergencia.setEmergenciaPeriodoid(emergenciaPeriodoid);
            }
            em.persist(emergencia);
            if (emergenciaPeriodoid != null) {
                emergenciaPeriodoid.getEmergenciaCollection().add(emergencia);
                emergenciaPeriodoid = em.merge(emergenciaPeriodoid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmergencia(emergencia.getId()) != null) {
                throw new PreexistingEntityException("Emergencia " + emergencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emergencia emergencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emergencia persistentEmergencia = em.find(Emergencia.class, emergencia.getId());
            EmergenciaPeriodo emergenciaPeriodoidOld = persistentEmergencia.getEmergenciaPeriodoid();
            EmergenciaPeriodo emergenciaPeriodoidNew = emergencia.getEmergenciaPeriodoid();
            if (emergenciaPeriodoidNew != null) {
                emergenciaPeriodoidNew = em.getReference(emergenciaPeriodoidNew.getClass(), emergenciaPeriodoidNew.getId());
                emergencia.setEmergenciaPeriodoid(emergenciaPeriodoidNew);
            }
            emergencia = em.merge(emergencia);
            if (emergenciaPeriodoidOld != null && !emergenciaPeriodoidOld.equals(emergenciaPeriodoidNew)) {
                emergenciaPeriodoidOld.getEmergenciaCollection().remove(emergencia);
                emergenciaPeriodoidOld = em.merge(emergenciaPeriodoidOld);
            }
            if (emergenciaPeriodoidNew != null && !emergenciaPeriodoidNew.equals(emergenciaPeriodoidOld)) {
                emergenciaPeriodoidNew.getEmergenciaCollection().add(emergencia);
                emergenciaPeriodoidNew = em.merge(emergenciaPeriodoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = emergencia.getId();
                if (findEmergencia(id) == null) {
                    throw new NonexistentEntityException("The emergencia with id " + id + " no longer exists.");
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
            Emergencia emergencia;
            try {
                emergencia = em.getReference(Emergencia.class, id);
                emergencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emergencia with id " + id + " no longer exists.", enfe);
            }
            EmergenciaPeriodo emergenciaPeriodoid = emergencia.getEmergenciaPeriodoid();
            if (emergenciaPeriodoid != null) {
                emergenciaPeriodoid.getEmergenciaCollection().remove(emergencia);
                emergenciaPeriodoid = em.merge(emergenciaPeriodoid);
            }
            em.remove(emergencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emergencia> findEmergenciaEntities() {
        return findEmergenciaEntities(true, -1, -1);
    }

    public List<Emergencia> findEmergenciaEntities(int maxResults, int firstResult) {
        return findEmergenciaEntities(false, maxResults, firstResult);
    }

    private List<Emergencia> findEmergenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Emergencia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Emergencia findEmergencia(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emergencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmergenciaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Emergencia as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
