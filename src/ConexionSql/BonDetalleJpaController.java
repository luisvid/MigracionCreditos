/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.BonDetalle;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Cuota;
import Entidades.Bonificacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class BonDetalleJpaController implements Serializable {

    public BonDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonDetalle bonDetalle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuota cuotaId = bonDetalle.getCuotaId();
            if (cuotaId != null) {
                cuotaId = em.getReference(cuotaId.getClass(), cuotaId.getId());
                bonDetalle.setCuotaId(cuotaId);
            }
            Bonificacion bonificacionId = bonDetalle.getBonificacionId();
            if (bonificacionId != null) {
                bonificacionId = em.getReference(bonificacionId.getClass(), bonificacionId.getId());
                bonDetalle.setBonificacionId(bonificacionId);
            }
            em.persist(bonDetalle);
            if (cuotaId != null) {
                cuotaId.getBonDetalleCollection().add(bonDetalle);
                cuotaId = em.merge(cuotaId);
            }
            if (bonificacionId != null) {
                bonificacionId.getBonDetalleCollection().add(bonDetalle);
                bonificacionId = em.merge(bonificacionId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonDetalle(bonDetalle.getId()) != null) {
                throw new PreexistingEntityException("BonDetalle " + bonDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonDetalle bonDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonDetalle persistentBonDetalle = em.find(BonDetalle.class, bonDetalle.getId());
            Cuota cuotaIdOld = persistentBonDetalle.getCuotaId();
            Cuota cuotaIdNew = bonDetalle.getCuotaId();
            Bonificacion bonificacionIdOld = persistentBonDetalle.getBonificacionId();
            Bonificacion bonificacionIdNew = bonDetalle.getBonificacionId();
            if (cuotaIdNew != null) {
                cuotaIdNew = em.getReference(cuotaIdNew.getClass(), cuotaIdNew.getId());
                bonDetalle.setCuotaId(cuotaIdNew);
            }
            if (bonificacionIdNew != null) {
                bonificacionIdNew = em.getReference(bonificacionIdNew.getClass(), bonificacionIdNew.getId());
                bonDetalle.setBonificacionId(bonificacionIdNew);
            }
            bonDetalle = em.merge(bonDetalle);
            if (cuotaIdOld != null && !cuotaIdOld.equals(cuotaIdNew)) {
                cuotaIdOld.getBonDetalleCollection().remove(bonDetalle);
                cuotaIdOld = em.merge(cuotaIdOld);
            }
            if (cuotaIdNew != null && !cuotaIdNew.equals(cuotaIdOld)) {
                cuotaIdNew.getBonDetalleCollection().add(bonDetalle);
                cuotaIdNew = em.merge(cuotaIdNew);
            }
            if (bonificacionIdOld != null && !bonificacionIdOld.equals(bonificacionIdNew)) {
                bonificacionIdOld.getBonDetalleCollection().remove(bonDetalle);
                bonificacionIdOld = em.merge(bonificacionIdOld);
            }
            if (bonificacionIdNew != null && !bonificacionIdNew.equals(bonificacionIdOld)) {
                bonificacionIdNew.getBonDetalleCollection().add(bonDetalle);
                bonificacionIdNew = em.merge(bonificacionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonDetalle.getId();
                if (findBonDetalle(id) == null) {
                    throw new NonexistentEntityException("The bonDetalle with id " + id + " no longer exists.");
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
            BonDetalle bonDetalle;
            try {
                bonDetalle = em.getReference(BonDetalle.class, id);
                bonDetalle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonDetalle with id " + id + " no longer exists.", enfe);
            }
            Cuota cuotaId = bonDetalle.getCuotaId();
            if (cuotaId != null) {
                cuotaId.getBonDetalleCollection().remove(bonDetalle);
                cuotaId = em.merge(cuotaId);
            }
            Bonificacion bonificacionId = bonDetalle.getBonificacionId();
            if (bonificacionId != null) {
                bonificacionId.getBonDetalleCollection().remove(bonDetalle);
                bonificacionId = em.merge(bonificacionId);
            }
            em.remove(bonDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonDetalle> findBonDetalleEntities() {
        return findBonDetalleEntities(true, -1, -1);
    }

    public List<BonDetalle> findBonDetalleEntities(int maxResults, int firstResult) {
        return findBonDetalleEntities(false, maxResults, firstResult);
    }

    private List<BonDetalle> findBonDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BonDetalle as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BonDetalle findBonDetalle(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BonDetalle as o");
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
