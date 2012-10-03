/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.BonTasa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author luisv
 */
public class BonTasaJpaController implements Serializable {

    public BonTasaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonTasa bonTasa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bonTasa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonTasa(bonTasa.getId()) != null) {
                throw new PreexistingEntityException("BonTasa " + bonTasa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonTasa bonTasa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bonTasa = em.merge(bonTasa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonTasa.getId();
                if (findBonTasa(id) == null) {
                    throw new NonexistentEntityException("The bonTasa with id " + id + " no longer exists.");
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
            BonTasa bonTasa;
            try {
                bonTasa = em.getReference(BonTasa.class, id);
                bonTasa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonTasa with id " + id + " no longer exists.", enfe);
            }
            em.remove(bonTasa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonTasa> findBonTasaEntities() {
        return findBonTasaEntities(true, -1, -1);
    }

    public List<BonTasa> findBonTasaEntities(int maxResults, int firstResult) {
        return findBonTasaEntities(false, maxResults, firstResult);
    }

    private List<BonTasa> findBonTasaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BonTasa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BonTasa findBonTasa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonTasa.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonTasaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BonTasa as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
         public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA3_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            boolean statement =conn.createStatement().execute(string);
           
            
        } finally {
            em.close();
        }
    } 
}
