/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.EmergenciaPeriodo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Emergencia;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author analian
 */
public class EmergenciaPeriodoJpaController implements Serializable {

    public EmergenciaPeriodoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmergenciaPeriodo emergenciaPeriodo) throws PreexistingEntityException, Exception {
        if (emergenciaPeriodo.getEmergenciaCollection() == null) {
            emergenciaPeriodo.setEmergenciaCollection(new ArrayList<Emergencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Emergencia> attachedEmergenciaCollection = new ArrayList<Emergencia>();
            for (Emergencia emergenciaCollectionEmergenciaToAttach : emergenciaPeriodo.getEmergenciaCollection()) {
                emergenciaCollectionEmergenciaToAttach = em.getReference(emergenciaCollectionEmergenciaToAttach.getClass(), emergenciaCollectionEmergenciaToAttach.getId());
                attachedEmergenciaCollection.add(emergenciaCollectionEmergenciaToAttach);
            }
            emergenciaPeriodo.setEmergenciaCollection(attachedEmergenciaCollection);
            em.persist(emergenciaPeriodo);
            for (Emergencia emergenciaCollectionEmergencia : emergenciaPeriodo.getEmergenciaCollection()) {
                EmergenciaPeriodo oldEmergenciaPeriodoidOfEmergenciaCollectionEmergencia = emergenciaCollectionEmergencia.getEmergenciaPeriodoid();
                emergenciaCollectionEmergencia.setEmergenciaPeriodoid(emergenciaPeriodo);
                emergenciaCollectionEmergencia = em.merge(emergenciaCollectionEmergencia);
                if (oldEmergenciaPeriodoidOfEmergenciaCollectionEmergencia != null) {
                    oldEmergenciaPeriodoidOfEmergenciaCollectionEmergencia.getEmergenciaCollection().remove(emergenciaCollectionEmergencia);
                    oldEmergenciaPeriodoidOfEmergenciaCollectionEmergencia = em.merge(oldEmergenciaPeriodoidOfEmergenciaCollectionEmergencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmergenciaPeriodo(emergenciaPeriodo.getId()) != null) {
                throw new PreexistingEntityException("EmergenciaPeriodo " + emergenciaPeriodo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmergenciaPeriodo emergenciaPeriodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmergenciaPeriodo persistentEmergenciaPeriodo = em.find(EmergenciaPeriodo.class, emergenciaPeriodo.getId());
            Collection<Emergencia> emergenciaCollectionOld = persistentEmergenciaPeriodo.getEmergenciaCollection();
            Collection<Emergencia> emergenciaCollectionNew = emergenciaPeriodo.getEmergenciaCollection();
            Collection<Emergencia> attachedEmergenciaCollectionNew = new ArrayList<Emergencia>();
            for (Emergencia emergenciaCollectionNewEmergenciaToAttach : emergenciaCollectionNew) {
                emergenciaCollectionNewEmergenciaToAttach = em.getReference(emergenciaCollectionNewEmergenciaToAttach.getClass(), emergenciaCollectionNewEmergenciaToAttach.getId());
                attachedEmergenciaCollectionNew.add(emergenciaCollectionNewEmergenciaToAttach);
            }
            emergenciaCollectionNew = attachedEmergenciaCollectionNew;
            emergenciaPeriodo.setEmergenciaCollection(emergenciaCollectionNew);
            emergenciaPeriodo = em.merge(emergenciaPeriodo);
            for (Emergencia emergenciaCollectionOldEmergencia : emergenciaCollectionOld) {
                if (!emergenciaCollectionNew.contains(emergenciaCollectionOldEmergencia)) {
                    emergenciaCollectionOldEmergencia.setEmergenciaPeriodoid(null);
                    emergenciaCollectionOldEmergencia = em.merge(emergenciaCollectionOldEmergencia);
                }
            }
            for (Emergencia emergenciaCollectionNewEmergencia : emergenciaCollectionNew) {
                if (!emergenciaCollectionOld.contains(emergenciaCollectionNewEmergencia)) {
                    EmergenciaPeriodo oldEmergenciaPeriodoidOfEmergenciaCollectionNewEmergencia = emergenciaCollectionNewEmergencia.getEmergenciaPeriodoid();
                    emergenciaCollectionNewEmergencia.setEmergenciaPeriodoid(emergenciaPeriodo);
                    emergenciaCollectionNewEmergencia = em.merge(emergenciaCollectionNewEmergencia);
                    if (oldEmergenciaPeriodoidOfEmergenciaCollectionNewEmergencia != null && !oldEmergenciaPeriodoidOfEmergenciaCollectionNewEmergencia.equals(emergenciaPeriodo)) {
                        oldEmergenciaPeriodoidOfEmergenciaCollectionNewEmergencia.getEmergenciaCollection().remove(emergenciaCollectionNewEmergencia);
                        oldEmergenciaPeriodoidOfEmergenciaCollectionNewEmergencia = em.merge(oldEmergenciaPeriodoidOfEmergenciaCollectionNewEmergencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = emergenciaPeriodo.getId();
                if (findEmergenciaPeriodo(id) == null) {
                    throw new NonexistentEntityException("The emergenciaPeriodo with id " + id + " no longer exists.");
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
            EmergenciaPeriodo emergenciaPeriodo;
            try {
                emergenciaPeriodo = em.getReference(EmergenciaPeriodo.class, id);
                emergenciaPeriodo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emergenciaPeriodo with id " + id + " no longer exists.", enfe);
            }
            Collection<Emergencia> emergenciaCollection = emergenciaPeriodo.getEmergenciaCollection();
            for (Emergencia emergenciaCollectionEmergencia : emergenciaCollection) {
                emergenciaCollectionEmergencia.setEmergenciaPeriodoid(null);
                emergenciaCollectionEmergencia = em.merge(emergenciaCollectionEmergencia);
            }
            em.remove(emergenciaPeriodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmergenciaPeriodo> findEmergenciaPeriodoEntities() {
        return findEmergenciaPeriodoEntities(true, -1, -1);
    }

    public List<EmergenciaPeriodo> findEmergenciaPeriodoEntities(int maxResults, int firstResult) {
        return findEmergenciaPeriodoEntities(false, maxResults, firstResult);
    }

    private List<EmergenciaPeriodo> findEmergenciaPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from EmergenciaPeriodo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EmergenciaPeriodo findEmergenciaPeriodo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmergenciaPeriodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmergenciaPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from EmergenciaPeriodo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
