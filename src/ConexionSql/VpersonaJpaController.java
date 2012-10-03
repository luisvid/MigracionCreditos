/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Vpersona;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author analian
 */
public class VpersonaJpaController implements Serializable {

    public VpersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vpersona vpersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vpersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVpersona(vpersona.getIdpersona()) != null) {
                throw new PreexistingEntityException("Vpersona " + vpersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vpersona vpersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vpersona = em.merge(vpersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigInteger id = vpersona.getIdpersona();
                if (findVpersona(id) == null) {
                    throw new NonexistentEntityException("The vpersona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigInteger id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vpersona vpersona;
            try {
                vpersona = em.getReference(Vpersona.class, id);
                vpersona.getIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vpersona with id " + id + " no longer exists.", enfe);
            }
            em.remove(vpersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vpersona> findVpersonaEntities() {
        return findVpersonaEntities(true, -1, -1);
    }

    public List<Vpersona> findVpersonaEntities(int maxResults, int firstResult) {
        return findVpersonaEntities(false, maxResults, firstResult);
    }

    private List<Vpersona> findVpersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Vpersona as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vpersona findVpersona(BigInteger id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vpersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getVpersonaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Vpersona as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Vpersona> findXCuit(Long cuitTitular) {
         EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Vpersona.findByCuil12");
            q.setParameter("cuil12", cuitTitular);
            List<Vpersona> p = q.getResultList();
            return p;
        } finally {
            em.close();
        }
    }
    
}
