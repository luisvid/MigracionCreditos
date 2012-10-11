/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.IndiceValor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Indice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class IndiceValorJpaController implements Serializable {

    public IndiceValorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IndiceValor indiceValor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indice indiceId = indiceValor.getIndiceId();
            if (indiceId != null) {
                indiceId = em.getReference(indiceId.getClass(), indiceId.getId());
                indiceValor.setIndiceId(indiceId);
            }
            em.persist(indiceValor);
            if (indiceId != null) {
                indiceId.getIndiceValorCollection().add(indiceValor);
                indiceId = em.merge(indiceId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIndiceValor(indiceValor.getId()) != null) {
                throw new PreexistingEntityException("IndiceValor " + indiceValor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IndiceValor indiceValor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IndiceValor persistentIndiceValor = em.find(IndiceValor.class, indiceValor.getId());
            Indice indiceIdOld = persistentIndiceValor.getIndiceId();
            Indice indiceIdNew = indiceValor.getIndiceId();
            if (indiceIdNew != null) {
                indiceIdNew = em.getReference(indiceIdNew.getClass(), indiceIdNew.getId());
                indiceValor.setIndiceId(indiceIdNew);
            }
            indiceValor = em.merge(indiceValor);
            if (indiceIdOld != null && !indiceIdOld.equals(indiceIdNew)) {
                indiceIdOld.getIndiceValorCollection().remove(indiceValor);
                indiceIdOld = em.merge(indiceIdOld);
            }
            if (indiceIdNew != null && !indiceIdNew.equals(indiceIdOld)) {
                indiceIdNew.getIndiceValorCollection().add(indiceValor);
                indiceIdNew = em.merge(indiceIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = indiceValor.getId();
                if (findIndiceValor(id) == null) {
                    throw new NonexistentEntityException("The indiceValor with id " + id + " no longer exists.");
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
            IndiceValor indiceValor;
            try {
                indiceValor = em.getReference(IndiceValor.class, id);
                indiceValor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indiceValor with id " + id + " no longer exists.", enfe);
            }
            Indice indiceId = indiceValor.getIndiceId();
            if (indiceId != null) {
                indiceId.getIndiceValorCollection().remove(indiceValor);
                indiceId = em.merge(indiceId);
            }
            em.remove(indiceValor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IndiceValor> findIndiceValorEntities() {
        return findIndiceValorEntities(true, -1, -1);
    }

    public List<IndiceValor> findIndiceValorEntities(int maxResults, int firstResult) {
        return findIndiceValorEntities(false, maxResults, firstResult);
    }

    private List<IndiceValor> findIndiceValorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from IndiceValor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public IndiceValor findIndiceValor(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IndiceValor.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndiceValorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from IndiceValor as o");
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
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA4_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT IndiceValor ON");             
            conn.createStatement().execute(string);             
            conn.createStatement().execute("SET IDENTITY_INSERT IndiceValor OFF"); 
           
            
        } finally {
            em.close();
        }
    }
    
}
