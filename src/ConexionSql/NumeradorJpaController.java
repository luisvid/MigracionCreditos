/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Numerador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author analian
 */
public class NumeradorJpaController implements Serializable {

    public NumeradorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Numerador numerador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(numerador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNumerador(numerador.getNombre()) != null) {
                throw new PreexistingEntityException("Numerador " + numerador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Numerador numerador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            numerador = em.merge(numerador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = numerador.getNombre();
                if (findNumerador(id) == null) {
                    throw new NonexistentEntityException("The numerador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Numerador numerador;
            try {
                numerador = em.getReference(Numerador.class, id);
                numerador.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The numerador with id " + id + " no longer exists.", enfe);
            }
            em.remove(numerador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Numerador> findNumeradorEntities() {
        return findNumeradorEntities(true, -1, -1);
    }

    public List<Numerador> findNumeradorEntities(int maxResults, int firstResult) {
        return findNumeradorEntities(false, maxResults, firstResult);
    }

    private List<Numerador> findNumeradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Numerador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Numerador findNumerador(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Numerador.class, id);
        } finally {
            em.close();
        }
    }

    public int getNumeradorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Numerador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
