/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.BonTasaEstado;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.BonTasa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class BonTasaEstadoJpaController implements Serializable {

    public BonTasaEstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonTasaEstado bonTasaEstado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonTasa bonTasaid = bonTasaEstado.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid = em.getReference(bonTasaid.getClass(), bonTasaid.getId());
                bonTasaEstado.setBonTasaid(bonTasaid);
            }
            em.persist(bonTasaEstado);
            if (bonTasaid != null) {
                bonTasaid.getBonTasaEstadoCollection().add(bonTasaEstado);
                bonTasaid = em.merge(bonTasaid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonTasaEstado(bonTasaEstado.getId()) != null) {
                throw new PreexistingEntityException("BonTasaEstado " + bonTasaEstado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonTasaEstado bonTasaEstado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonTasaEstado persistentBonTasaEstado = em.find(BonTasaEstado.class, bonTasaEstado.getId());
            BonTasa bonTasaidOld = persistentBonTasaEstado.getBonTasaid();
            BonTasa bonTasaidNew = bonTasaEstado.getBonTasaid();
            if (bonTasaidNew != null) {
                bonTasaidNew = em.getReference(bonTasaidNew.getClass(), bonTasaidNew.getId());
                bonTasaEstado.setBonTasaid(bonTasaidNew);
            }
            bonTasaEstado = em.merge(bonTasaEstado);
            if (bonTasaidOld != null && !bonTasaidOld.equals(bonTasaidNew)) {
                bonTasaidOld.getBonTasaEstadoCollection().remove(bonTasaEstado);
                bonTasaidOld = em.merge(bonTasaidOld);
            }
            if (bonTasaidNew != null && !bonTasaidNew.equals(bonTasaidOld)) {
                bonTasaidNew.getBonTasaEstadoCollection().add(bonTasaEstado);
                bonTasaidNew = em.merge(bonTasaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonTasaEstado.getId();
                if (findBonTasaEstado(id) == null) {
                    throw new NonexistentEntityException("The bonTasaEstado with id " + id + " no longer exists.");
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
            BonTasaEstado bonTasaEstado;
            try {
                bonTasaEstado = em.getReference(BonTasaEstado.class, id);
                bonTasaEstado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonTasaEstado with id " + id + " no longer exists.", enfe);
            }
            BonTasa bonTasaid = bonTasaEstado.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid.getBonTasaEstadoCollection().remove(bonTasaEstado);
                bonTasaid = em.merge(bonTasaid);
            }
            em.remove(bonTasaEstado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonTasaEstado> findBonTasaEstadoEntities() {
        return findBonTasaEstadoEntities(true, -1, -1);
    }

    public List<BonTasaEstado> findBonTasaEstadoEntities(int maxResults, int firstResult) {
        return findBonTasaEstadoEntities(false, maxResults, firstResult);
    }

    private List<BonTasaEstado> findBonTasaEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BonTasaEstado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BonTasaEstado findBonTasaEstado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonTasaEstado.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonTasaEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BonTasaEstado as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
   public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA4_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");
            conn.createStatement().execute(string);
            conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF");

           
            
        } finally {
            em.close();
        }
    } 
}
