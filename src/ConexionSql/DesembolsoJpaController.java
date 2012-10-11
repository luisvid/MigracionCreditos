/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Desembolso;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Requisito;
import Entidades.Objetoi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class DesembolsoJpaController implements Serializable {

    public DesembolsoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Desembolso desembolso) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Requisito requisitoId = desembolso.getRequisitoId();
            if (requisitoId != null) {
                requisitoId = em.getReference(requisitoId.getClass(), requisitoId.getId());
                desembolso.setRequisitoId(requisitoId);
            }
            Objetoi creditoId = desembolso.getCreditoId();
            if (creditoId != null) {
                creditoId = em.getReference(creditoId.getClass(), creditoId.getId());
                desembolso.setCreditoId(creditoId);
            }
            em.persist(desembolso);
            if (requisitoId != null) {
                requisitoId.getDesembolsoCollection().add(desembolso);
                requisitoId = em.merge(requisitoId);
            }
            if (creditoId != null) {
                creditoId.getDesembolsoCollection().add(desembolso);
                creditoId = em.merge(creditoId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDesembolso(desembolso.getId()) != null) {
                throw new PreexistingEntityException("Desembolso " + desembolso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Desembolso desembolso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Desembolso persistentDesembolso = em.find(Desembolso.class, desembolso.getId());
            Requisito requisitoIdOld = persistentDesembolso.getRequisitoId();
            Requisito requisitoIdNew = desembolso.getRequisitoId();
            Objetoi creditoIdOld = persistentDesembolso.getCreditoId();
            Objetoi creditoIdNew = desembolso.getCreditoId();
            if (requisitoIdNew != null) {
                requisitoIdNew = em.getReference(requisitoIdNew.getClass(), requisitoIdNew.getId());
                desembolso.setRequisitoId(requisitoIdNew);
            }
            if (creditoIdNew != null) {
                creditoIdNew = em.getReference(creditoIdNew.getClass(), creditoIdNew.getId());
                desembolso.setCreditoId(creditoIdNew);
            }
            desembolso = em.merge(desembolso);
            if (requisitoIdOld != null && !requisitoIdOld.equals(requisitoIdNew)) {
                requisitoIdOld.getDesembolsoCollection().remove(desembolso);
                requisitoIdOld = em.merge(requisitoIdOld);
            }
            if (requisitoIdNew != null && !requisitoIdNew.equals(requisitoIdOld)) {
                requisitoIdNew.getDesembolsoCollection().add(desembolso);
                requisitoIdNew = em.merge(requisitoIdNew);
            }
            if (creditoIdOld != null && !creditoIdOld.equals(creditoIdNew)) {
                creditoIdOld.getDesembolsoCollection().remove(desembolso);
                creditoIdOld = em.merge(creditoIdOld);
            }
            if (creditoIdNew != null && !creditoIdNew.equals(creditoIdOld)) {
                creditoIdNew.getDesembolsoCollection().add(desembolso);
                creditoIdNew = em.merge(creditoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = desembolso.getId();
                if (findDesembolso(id) == null) {
                    throw new NonexistentEntityException("The desembolso with id " + id + " no longer exists.");
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
            Desembolso desembolso;
            try {
                desembolso = em.getReference(Desembolso.class, id);
                desembolso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The desembolso with id " + id + " no longer exists.", enfe);
            }
            Requisito requisitoId = desembolso.getRequisitoId();
            if (requisitoId != null) {
                requisitoId.getDesembolsoCollection().remove(desembolso);
                requisitoId = em.merge(requisitoId);
            }
            Objetoi creditoId = desembolso.getCreditoId();
            if (creditoId != null) {
                creditoId.getDesembolsoCollection().remove(desembolso);
                creditoId = em.merge(creditoId);
            }
            em.remove(desembolso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Desembolso> findDesembolsoEntities() {
        return findDesembolsoEntities(true, -1, -1);
    }

    public List<Desembolso> findDesembolsoEntities(int maxResults, int firstResult) {
        return findDesembolsoEntities(false, maxResults, firstResult);
    }

    private List<Desembolso> findDesembolsoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Desembolso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Desembolso findDesembolso(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Desembolso.class, id);
        } finally {
            em.close();
        }
    }

    public int getDesembolsoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Desembolso as o");
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
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");             
            conn.createStatement().execute(string);             conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF"); 
           
            
        } finally {
            em.close();
        }
    }
    
}
