/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.ObjetoiIndice;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import Entidades.Indice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class ObjetoiIndiceJpaController implements Serializable {

    public ObjetoiIndiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjetoiIndice objetoiIndice) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi creditoId = objetoiIndice.getCreditoId();
            if (creditoId != null) {
                creditoId = em.getReference(creditoId.getClass(), creditoId.getId());
                objetoiIndice.setCreditoId(creditoId);
            }
            Indice indiceId = objetoiIndice.getIndiceId();
            if (indiceId != null) {
                indiceId = em.getReference(indiceId.getClass(), indiceId.getId());
                objetoiIndice.setIndiceId(indiceId);
            }
            em.persist(objetoiIndice);
            if (creditoId != null) {
                creditoId.getObjetoiIndiceCollection().add(objetoiIndice);
                creditoId = em.merge(creditoId);
            }
            if (indiceId != null) {
                indiceId.getObjetoiIndiceCollection().add(objetoiIndice);
                indiceId = em.merge(indiceId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoiIndice(objetoiIndice.getId()) != null) {
                throw new PreexistingEntityException("ObjetoiIndice " + objetoiIndice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ObjetoiIndice objetoiIndice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ObjetoiIndice persistentObjetoiIndice = em.find(ObjetoiIndice.class, objetoiIndice.getId());
            Objetoi creditoIdOld = persistentObjetoiIndice.getCreditoId();
            Objetoi creditoIdNew = objetoiIndice.getCreditoId();
            Indice indiceIdOld = persistentObjetoiIndice.getIndiceId();
            Indice indiceIdNew = objetoiIndice.getIndiceId();
            if (creditoIdNew != null) {
                creditoIdNew = em.getReference(creditoIdNew.getClass(), creditoIdNew.getId());
                objetoiIndice.setCreditoId(creditoIdNew);
            }
            if (indiceIdNew != null) {
                indiceIdNew = em.getReference(indiceIdNew.getClass(), indiceIdNew.getId());
                objetoiIndice.setIndiceId(indiceIdNew);
            }
            objetoiIndice = em.merge(objetoiIndice);
            if (creditoIdOld != null && !creditoIdOld.equals(creditoIdNew)) {
                creditoIdOld.getObjetoiIndiceCollection().remove(objetoiIndice);
                creditoIdOld = em.merge(creditoIdOld);
            }
            if (creditoIdNew != null && !creditoIdNew.equals(creditoIdOld)) {
                creditoIdNew.getObjetoiIndiceCollection().add(objetoiIndice);
                creditoIdNew = em.merge(creditoIdNew);
            }
            if (indiceIdOld != null && !indiceIdOld.equals(indiceIdNew)) {
                indiceIdOld.getObjetoiIndiceCollection().remove(objetoiIndice);
                indiceIdOld = em.merge(indiceIdOld);
            }
            if (indiceIdNew != null && !indiceIdNew.equals(indiceIdOld)) {
                indiceIdNew.getObjetoiIndiceCollection().add(objetoiIndice);
                indiceIdNew = em.merge(indiceIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoiIndice.getId();
                if (findObjetoiIndice(id) == null) {
                    throw new NonexistentEntityException("The objetoiIndice with id " + id + " no longer exists.");
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
            ObjetoiIndice objetoiIndice;
            try {
                objetoiIndice = em.getReference(ObjetoiIndice.class, id);
                objetoiIndice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoiIndice with id " + id + " no longer exists.", enfe);
            }
            Objetoi creditoId = objetoiIndice.getCreditoId();
            if (creditoId != null) {
                creditoId.getObjetoiIndiceCollection().remove(objetoiIndice);
                creditoId = em.merge(creditoId);
            }
            Indice indiceId = objetoiIndice.getIndiceId();
            if (indiceId != null) {
                indiceId.getObjetoiIndiceCollection().remove(objetoiIndice);
                indiceId = em.merge(indiceId);
            }
            em.remove(objetoiIndice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ObjetoiIndice> findObjetoiIndiceEntities() {
        return findObjetoiIndiceEntities(true, -1, -1);
    }

    public List<ObjetoiIndice> findObjetoiIndiceEntities(int maxResults, int firstResult) {
        return findObjetoiIndiceEntities(false, maxResults, firstResult);
    }

    private List<ObjetoiIndice> findObjetoiIndiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ObjetoiIndice as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjetoiIndice findObjetoiIndice(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ObjetoiIndice.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetoiIndiceCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ObjetoiIndice as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
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
