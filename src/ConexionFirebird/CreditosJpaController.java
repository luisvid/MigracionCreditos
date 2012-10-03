/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import ConexionFirebird.exceptions.NonexistentEntityException;
import ConexionFirebird.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author analian
 */
public class CreditosJpaController implements Serializable {

    public CreditosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Creditos creditos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas persNuri = creditos.getPersNuri();
            if (persNuri != null) {
                persNuri = em.getReference(persNuri.getClass(), persNuri.getNuri());
                creditos.setPersNuri(persNuri);
            }
            em.persist(creditos);
            if (persNuri != null) {
                persNuri.getCreditosList().add(creditos);
                persNuri = em.merge(persNuri);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCreditos(creditos.getNuri()) != null) {
                throw new PreexistingEntityException("Creditos " + creditos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Creditos creditos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditos persistentCreditos = em.find(Creditos.class, creditos.getNuri());
            Personas persNuriOld = persistentCreditos.getPersNuri();
            Personas persNuriNew = creditos.getPersNuri();
            if (persNuriNew != null) {
                persNuriNew = em.getReference(persNuriNew.getClass(), persNuriNew.getNuri());
                creditos.setPersNuri(persNuriNew);
            }
            creditos = em.merge(creditos);
            if (persNuriOld != null && !persNuriOld.equals(persNuriNew)) {
                persNuriOld.getCreditosList().remove(creditos);
                persNuriOld = em.merge(persNuriOld);
            }
            if (persNuriNew != null && !persNuriNew.equals(persNuriOld)) {
                persNuriNew.getCreditosList().add(creditos);
                persNuriNew = em.merge(persNuriNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = creditos.getNuri();
                if (findCreditos(id) == null) {
                    throw new NonexistentEntityException("The creditos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Creditos creditos;
            try {
                creditos = em.getReference(Creditos.class, id);
                creditos.getNuri();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The creditos with id " + id + " no longer exists.", enfe);
            }
            Personas persNuri = creditos.getPersNuri();
            if (persNuri != null) {
                persNuri.getCreditosList().remove(creditos);
                persNuri = em.merge(persNuri);
            }
            em.remove(creditos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Creditos> findCreditosEntities() {
        return findCreditosEntities(true, -1, -1);
    }

    public List<Creditos> findCreditosEntities(int maxResults, int firstResult) {
        return findCreditosEntities(false, maxResults, firstResult);
    }

    private List<Creditos> findCreditosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Creditos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Creditos findCreditos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Creditos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCreditosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Creditos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Creditos> findAll() {
         EntityManager em = getEntityManager();


        try {

            Query q = em.createNativeQuery("Select * from Creditos");
            
            //List<Creditos> o = q.getResultList();
          
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
