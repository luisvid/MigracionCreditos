/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.QQIngresados;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Vinedo;

/**
 *
 * @author analian
 */
public class QQIngresadosJpaController implements Serializable {

    public QQIngresadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(QQIngresados QQIngresados) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinedo vinedoId = QQIngresados.getVinedoId();
            if (vinedoId != null) {
                vinedoId = em.getReference(vinedoId.getClass(), vinedoId.getId());
                QQIngresados.setVinedoId(vinedoId);
            }
            em.persist(QQIngresados);
            if (vinedoId != null) {
                vinedoId.getQQIngresadosCollection().add(QQIngresados);
                vinedoId = em.merge(vinedoId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findQQIngresados(QQIngresados.getId()) != null) {
                throw new PreexistingEntityException("QQIngresados " + QQIngresados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(QQIngresados QQIngresados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            QQIngresados persistentQQIngresados = em.find(QQIngresados.class, QQIngresados.getId());
            Vinedo vinedoIdOld = persistentQQIngresados.getVinedoId();
            Vinedo vinedoIdNew = QQIngresados.getVinedoId();
            if (vinedoIdNew != null) {
                vinedoIdNew = em.getReference(vinedoIdNew.getClass(), vinedoIdNew.getId());
                QQIngresados.setVinedoId(vinedoIdNew);
            }
            QQIngresados = em.merge(QQIngresados);
            if (vinedoIdOld != null && !vinedoIdOld.equals(vinedoIdNew)) {
                vinedoIdOld.getQQIngresadosCollection().remove(QQIngresados);
                vinedoIdOld = em.merge(vinedoIdOld);
            }
            if (vinedoIdNew != null && !vinedoIdNew.equals(vinedoIdOld)) {
                vinedoIdNew.getQQIngresadosCollection().add(QQIngresados);
                vinedoIdNew = em.merge(vinedoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = QQIngresados.getId();
                if (findQQIngresados(id) == null) {
                    throw new NonexistentEntityException("The qQIngresados with id " + id + " no longer exists.");
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
            QQIngresados QQIngresados;
            try {
                QQIngresados = em.getReference(QQIngresados.class, id);
                QQIngresados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The QQIngresados with id " + id + " no longer exists.", enfe);
            }
            Vinedo vinedoId = QQIngresados.getVinedoId();
            if (vinedoId != null) {
                vinedoId.getQQIngresadosCollection().remove(QQIngresados);
                vinedoId = em.merge(vinedoId);
            }
            em.remove(QQIngresados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<QQIngresados> findQQIngresadosEntities() {
        return findQQIngresadosEntities(true, -1, -1);
    }

    public List<QQIngresados> findQQIngresadosEntities(int maxResults, int firstResult) {
        return findQQIngresadosEntities(false, maxResults, firstResult);
    }

    private List<QQIngresados> findQQIngresadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from QQIngresados as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public QQIngresados findQQIngresados(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(QQIngresados.class, id);
        } finally {
            em.close();
        }
    }

    public int getQQIngresadosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from QQIngresados as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
