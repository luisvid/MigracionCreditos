/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.BonTasaIndice;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Indice;
import Entidades.BonTasa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class BonTasaIndiceJpaController implements Serializable {

    public BonTasaIndiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonTasaIndice bonTasaIndice) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indice indiceId = bonTasaIndice.getIndiceId();
            if (indiceId != null) {
                indiceId = em.getReference(indiceId.getClass(), indiceId.getId());
                bonTasaIndice.setIndiceId(indiceId);
            }
            BonTasa bonTasaid = bonTasaIndice.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid = em.getReference(bonTasaid.getClass(), bonTasaid.getId());
                bonTasaIndice.setBonTasaid(bonTasaid);
            }
            em.persist(bonTasaIndice);
            if (indiceId != null) {
                indiceId.getBonTasaIndiceCollection().add(bonTasaIndice);
                indiceId = em.merge(indiceId);
            }
            if (bonTasaid != null) {
                bonTasaid.getBonTasaIndiceCollection().add(bonTasaIndice);
                bonTasaid = em.merge(bonTasaid);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonTasaIndice(bonTasaIndice.getId()) != null) {
                throw new PreexistingEntityException("BonTasaIndice " + bonTasaIndice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonTasaIndice bonTasaIndice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonTasaIndice persistentBonTasaIndice = em.find(BonTasaIndice.class, bonTasaIndice.getId());
            Indice indiceIdOld = persistentBonTasaIndice.getIndiceId();
            Indice indiceIdNew = bonTasaIndice.getIndiceId();
            BonTasa bonTasaidOld = persistentBonTasaIndice.getBonTasaid();
            BonTasa bonTasaidNew = bonTasaIndice.getBonTasaid();
            if (indiceIdNew != null) {
                indiceIdNew = em.getReference(indiceIdNew.getClass(), indiceIdNew.getId());
                bonTasaIndice.setIndiceId(indiceIdNew);
            }
            if (bonTasaidNew != null) {
                bonTasaidNew = em.getReference(bonTasaidNew.getClass(), bonTasaidNew.getId());
                bonTasaIndice.setBonTasaid(bonTasaidNew);
            }
            bonTasaIndice = em.merge(bonTasaIndice);
            if (indiceIdOld != null && !indiceIdOld.equals(indiceIdNew)) {
                indiceIdOld.getBonTasaIndiceCollection().remove(bonTasaIndice);
                indiceIdOld = em.merge(indiceIdOld);
            }
            if (indiceIdNew != null && !indiceIdNew.equals(indiceIdOld)) {
                indiceIdNew.getBonTasaIndiceCollection().add(bonTasaIndice);
                indiceIdNew = em.merge(indiceIdNew);
            }
            if (bonTasaidOld != null && !bonTasaidOld.equals(bonTasaidNew)) {
                bonTasaidOld.getBonTasaIndiceCollection().remove(bonTasaIndice);
                bonTasaidOld = em.merge(bonTasaidOld);
            }
            if (bonTasaidNew != null && !bonTasaidNew.equals(bonTasaidOld)) {
                bonTasaidNew.getBonTasaIndiceCollection().add(bonTasaIndice);
                bonTasaidNew = em.merge(bonTasaidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonTasaIndice.getId();
                if (findBonTasaIndice(id) == null) {
                    throw new NonexistentEntityException("The bonTasaIndice with id " + id + " no longer exists.");
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
            BonTasaIndice bonTasaIndice;
            try {
                bonTasaIndice = em.getReference(BonTasaIndice.class, id);
                bonTasaIndice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonTasaIndice with id " + id + " no longer exists.", enfe);
            }
            Indice indiceId = bonTasaIndice.getIndiceId();
            if (indiceId != null) {
                indiceId.getBonTasaIndiceCollection().remove(bonTasaIndice);
                indiceId = em.merge(indiceId);
            }
            BonTasa bonTasaid = bonTasaIndice.getBonTasaid();
            if (bonTasaid != null) {
                bonTasaid.getBonTasaIndiceCollection().remove(bonTasaIndice);
                bonTasaid = em.merge(bonTasaid);
            }
            em.remove(bonTasaIndice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonTasaIndice> findBonTasaIndiceEntities() {
        return findBonTasaIndiceEntities(true, -1, -1);
    }

    public List<BonTasaIndice> findBonTasaIndiceEntities(int maxResults, int firstResult) {
        return findBonTasaIndiceEntities(false, maxResults, firstResult);
    }

    private List<BonTasaIndice> findBonTasaIndiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BonTasaIndice as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BonTasaIndice findBonTasaIndice(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonTasaIndice.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonTasaIndiceCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BonTasaIndice as o");
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
