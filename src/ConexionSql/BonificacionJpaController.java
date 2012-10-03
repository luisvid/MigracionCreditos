/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Bonificacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.CConcepto;
import Entidades.ObjetoiBonificacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author analian
 */
public class BonificacionJpaController implements Serializable {

    public BonificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bonificacion bonificacion) throws PreexistingEntityException, Exception {
        if (bonificacion.getObjetoiBonificacionCollection() == null) {
            bonificacion.setObjetoiBonificacionCollection(new ArrayList<ObjetoiBonificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CConcepto conceptoAforoconcepto = bonificacion.getConceptoAforoconcepto();
            if (conceptoAforoconcepto != null) {
                conceptoAforoconcepto = em.getReference(conceptoAforoconcepto.getClass(), conceptoAforoconcepto.getConcepto());
                bonificacion.setConceptoAforoconcepto(conceptoAforoconcepto);
            }
            Collection<ObjetoiBonificacion> attachedObjetoiBonificacionCollection = new ArrayList<ObjetoiBonificacion>();
            for (ObjetoiBonificacion objetoiBonificacionCollectionObjetoiBonificacionToAttach : bonificacion.getObjetoiBonificacionCollection()) {
                objetoiBonificacionCollectionObjetoiBonificacionToAttach = em.getReference(objetoiBonificacionCollectionObjetoiBonificacionToAttach.getClass(), objetoiBonificacionCollectionObjetoiBonificacionToAttach.getId());
                attachedObjetoiBonificacionCollection.add(objetoiBonificacionCollectionObjetoiBonificacionToAttach);
            }
            bonificacion.setObjetoiBonificacionCollection(attachedObjetoiBonificacionCollection);
            em.persist(bonificacion);
            if (conceptoAforoconcepto != null) {
                conceptoAforoconcepto.getBonificacionCollection().add(bonificacion);
                conceptoAforoconcepto = em.merge(conceptoAforoconcepto);
            }
            for (ObjetoiBonificacion objetoiBonificacionCollectionObjetoiBonificacion : bonificacion.getObjetoiBonificacionCollection()) {
                Bonificacion oldIdBonificacionOfObjetoiBonificacionCollectionObjetoiBonificacion = objetoiBonificacionCollectionObjetoiBonificacion.getIdBonificacion();
                objetoiBonificacionCollectionObjetoiBonificacion.setIdBonificacion(bonificacion);
                objetoiBonificacionCollectionObjetoiBonificacion = em.merge(objetoiBonificacionCollectionObjetoiBonificacion);
                if (oldIdBonificacionOfObjetoiBonificacionCollectionObjetoiBonificacion != null) {
                    oldIdBonificacionOfObjetoiBonificacionCollectionObjetoiBonificacion.getObjetoiBonificacionCollection().remove(objetoiBonificacionCollectionObjetoiBonificacion);
                    oldIdBonificacionOfObjetoiBonificacionCollectionObjetoiBonificacion = em.merge(oldIdBonificacionOfObjetoiBonificacionCollectionObjetoiBonificacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonificacion(bonificacion.getId()) != null) {
                throw new PreexistingEntityException("Bonificacion " + bonificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bonificacion bonificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bonificacion persistentBonificacion = em.find(Bonificacion.class, bonificacion.getId());
            CConcepto conceptoAforoconceptoOld = persistentBonificacion.getConceptoAforoconcepto();
            CConcepto conceptoAforoconceptoNew = bonificacion.getConceptoAforoconcepto();
            Collection<ObjetoiBonificacion> objetoiBonificacionCollectionOld = persistentBonificacion.getObjetoiBonificacionCollection();
            Collection<ObjetoiBonificacion> objetoiBonificacionCollectionNew = bonificacion.getObjetoiBonificacionCollection();
            if (conceptoAforoconceptoNew != null) {
                conceptoAforoconceptoNew = em.getReference(conceptoAforoconceptoNew.getClass(), conceptoAforoconceptoNew.getConcepto());
                bonificacion.setConceptoAforoconcepto(conceptoAforoconceptoNew);
            }
            Collection<ObjetoiBonificacion> attachedObjetoiBonificacionCollectionNew = new ArrayList<ObjetoiBonificacion>();
            for (ObjetoiBonificacion objetoiBonificacionCollectionNewObjetoiBonificacionToAttach : objetoiBonificacionCollectionNew) {
                objetoiBonificacionCollectionNewObjetoiBonificacionToAttach = em.getReference(objetoiBonificacionCollectionNewObjetoiBonificacionToAttach.getClass(), objetoiBonificacionCollectionNewObjetoiBonificacionToAttach.getId());
                attachedObjetoiBonificacionCollectionNew.add(objetoiBonificacionCollectionNewObjetoiBonificacionToAttach);
            }
            objetoiBonificacionCollectionNew = attachedObjetoiBonificacionCollectionNew;
            bonificacion.setObjetoiBonificacionCollection(objetoiBonificacionCollectionNew);
            bonificacion = em.merge(bonificacion);
            if (conceptoAforoconceptoOld != null && !conceptoAforoconceptoOld.equals(conceptoAforoconceptoNew)) {
                conceptoAforoconceptoOld.getBonificacionCollection().remove(bonificacion);
                conceptoAforoconceptoOld = em.merge(conceptoAforoconceptoOld);
            }
            if (conceptoAforoconceptoNew != null && !conceptoAforoconceptoNew.equals(conceptoAforoconceptoOld)) {
                conceptoAforoconceptoNew.getBonificacionCollection().add(bonificacion);
                conceptoAforoconceptoNew = em.merge(conceptoAforoconceptoNew);
            }
            for (ObjetoiBonificacion objetoiBonificacionCollectionOldObjetoiBonificacion : objetoiBonificacionCollectionOld) {
                if (!objetoiBonificacionCollectionNew.contains(objetoiBonificacionCollectionOldObjetoiBonificacion)) {
                    objetoiBonificacionCollectionOldObjetoiBonificacion.setIdBonificacion(null);
                    objetoiBonificacionCollectionOldObjetoiBonificacion = em.merge(objetoiBonificacionCollectionOldObjetoiBonificacion);
                }
            }
            for (ObjetoiBonificacion objetoiBonificacionCollectionNewObjetoiBonificacion : objetoiBonificacionCollectionNew) {
                if (!objetoiBonificacionCollectionOld.contains(objetoiBonificacionCollectionNewObjetoiBonificacion)) {
                    Bonificacion oldIdBonificacionOfObjetoiBonificacionCollectionNewObjetoiBonificacion = objetoiBonificacionCollectionNewObjetoiBonificacion.getIdBonificacion();
                    objetoiBonificacionCollectionNewObjetoiBonificacion.setIdBonificacion(bonificacion);
                    objetoiBonificacionCollectionNewObjetoiBonificacion = em.merge(objetoiBonificacionCollectionNewObjetoiBonificacion);
                    if (oldIdBonificacionOfObjetoiBonificacionCollectionNewObjetoiBonificacion != null && !oldIdBonificacionOfObjetoiBonificacionCollectionNewObjetoiBonificacion.equals(bonificacion)) {
                        oldIdBonificacionOfObjetoiBonificacionCollectionNewObjetoiBonificacion.getObjetoiBonificacionCollection().remove(objetoiBonificacionCollectionNewObjetoiBonificacion);
                        oldIdBonificacionOfObjetoiBonificacionCollectionNewObjetoiBonificacion = em.merge(oldIdBonificacionOfObjetoiBonificacionCollectionNewObjetoiBonificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonificacion.getId();
                if (findBonificacion(id) == null) {
                    throw new NonexistentEntityException("The bonificacion with id " + id + " no longer exists.");
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
            Bonificacion bonificacion;
            try {
                bonificacion = em.getReference(Bonificacion.class, id);
                bonificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonificacion with id " + id + " no longer exists.", enfe);
            }
            CConcepto conceptoAforoconcepto = bonificacion.getConceptoAforoconcepto();
            if (conceptoAforoconcepto != null) {
                conceptoAforoconcepto.getBonificacionCollection().remove(bonificacion);
                conceptoAforoconcepto = em.merge(conceptoAforoconcepto);
            }
            Collection<ObjetoiBonificacion> objetoiBonificacionCollection = bonificacion.getObjetoiBonificacionCollection();
            for (ObjetoiBonificacion objetoiBonificacionCollectionObjetoiBonificacion : objetoiBonificacionCollection) {
                objetoiBonificacionCollectionObjetoiBonificacion.setIdBonificacion(null);
                objetoiBonificacionCollectionObjetoiBonificacion = em.merge(objetoiBonificacionCollectionObjetoiBonificacion);
            }
            em.remove(bonificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bonificacion> findBonificacionEntities() {
        return findBonificacionEntities(true, -1, -1);
    }

    public List<Bonificacion> findBonificacionEntities(int maxResults, int firstResult) {
        return findBonificacionEntities(false, maxResults, firstResult);
    }

    private List<Bonificacion> findBonificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Bonificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bonificacion findBonificacion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bonificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Bonificacion as o");
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

    public List<Bonificacion> buscarBonificacion(Double tasaSubsidio, int enteBonificador) {
        EntityManager em = getEntityManager();    

        try {

            Query q = em.createNativeQuery("Select * from Bonificacion where enteBonificador_CODI_BA="+ enteBonificador+" And tasaBonificada = "+tasaSubsidio, Bonificacion.class );
    
            //List<Bonificacion> o = q.getResultList();
          
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
