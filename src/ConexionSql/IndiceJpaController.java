/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Indice;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.IndiceValor;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.ObjetoiIndice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class IndiceJpaController implements Serializable {
    

    public IndiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public IndiceJpaController() {
    }
    

    public void create(Indice indice) throws PreexistingEntityException, Exception {
        if (indice.getIndiceValorCollection() == null) {
            indice.setIndiceValorCollection(new ArrayList<IndiceValor>());
        }
        if (indice.getObjetoiIndiceCollection() == null) {
            indice.setObjetoiIndiceCollection(new ArrayList<ObjetoiIndice>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<IndiceValor> attachedIndiceValorCollection = new ArrayList<IndiceValor>();
            for (IndiceValor indiceValorCollectionIndiceValorToAttach : indice.getIndiceValorCollection()) {
                indiceValorCollectionIndiceValorToAttach = em.getReference(indiceValorCollectionIndiceValorToAttach.getClass(), indiceValorCollectionIndiceValorToAttach.getId());
                attachedIndiceValorCollection.add(indiceValorCollectionIndiceValorToAttach);
            }
            indice.setIndiceValorCollection(attachedIndiceValorCollection);
            Collection<ObjetoiIndice> attachedObjetoiIndiceCollection = new ArrayList<ObjetoiIndice>();
            for (ObjetoiIndice objetoiIndiceCollectionObjetoiIndiceToAttach : indice.getObjetoiIndiceCollection()) {
                objetoiIndiceCollectionObjetoiIndiceToAttach = em.getReference(objetoiIndiceCollectionObjetoiIndiceToAttach.getClass(), objetoiIndiceCollectionObjetoiIndiceToAttach.getId());
                attachedObjetoiIndiceCollection.add(objetoiIndiceCollectionObjetoiIndiceToAttach);
            }
            indice.setObjetoiIndiceCollection(attachedObjetoiIndiceCollection);
            em.persist(indice);
            for (IndiceValor indiceValorCollectionIndiceValor : indice.getIndiceValorCollection()) {
                Indice oldIndiceIdOfIndiceValorCollectionIndiceValor = indiceValorCollectionIndiceValor.getIndiceId();
                indiceValorCollectionIndiceValor.setIndiceId(indice);
                indiceValorCollectionIndiceValor = em.merge(indiceValorCollectionIndiceValor);
                if (oldIndiceIdOfIndiceValorCollectionIndiceValor != null) {
                    oldIndiceIdOfIndiceValorCollectionIndiceValor.getIndiceValorCollection().remove(indiceValorCollectionIndiceValor);
                    oldIndiceIdOfIndiceValorCollectionIndiceValor = em.merge(oldIndiceIdOfIndiceValorCollectionIndiceValor);
                }
            }
            for (ObjetoiIndice objetoiIndiceCollectionObjetoiIndice : indice.getObjetoiIndiceCollection()) {
                Indice oldIndiceIdOfObjetoiIndiceCollectionObjetoiIndice = objetoiIndiceCollectionObjetoiIndice.getIndiceId();
                objetoiIndiceCollectionObjetoiIndice.setIndiceId(indice);
                objetoiIndiceCollectionObjetoiIndice = em.merge(objetoiIndiceCollectionObjetoiIndice);
                if (oldIndiceIdOfObjetoiIndiceCollectionObjetoiIndice != null) {
                    oldIndiceIdOfObjetoiIndiceCollectionObjetoiIndice.getObjetoiIndiceCollection().remove(objetoiIndiceCollectionObjetoiIndice);
                    oldIndiceIdOfObjetoiIndiceCollectionObjetoiIndice = em.merge(oldIndiceIdOfObjetoiIndiceCollectionObjetoiIndice);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIndice(indice.getId()) != null) {
                throw new PreexistingEntityException("Indice " + indice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Indice indice) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indice persistentIndice = em.find(Indice.class, indice.getId());
            Collection<IndiceValor> indiceValorCollectionOld = persistentIndice.getIndiceValorCollection();
            Collection<IndiceValor> indiceValorCollectionNew = indice.getIndiceValorCollection();
            Collection<ObjetoiIndice> objetoiIndiceCollectionOld = persistentIndice.getObjetoiIndiceCollection();
            Collection<ObjetoiIndice> objetoiIndiceCollectionNew = indice.getObjetoiIndiceCollection();
            Collection<IndiceValor> attachedIndiceValorCollectionNew = new ArrayList<IndiceValor>();
            for (IndiceValor indiceValorCollectionNewIndiceValorToAttach : indiceValorCollectionNew) {
                indiceValorCollectionNewIndiceValorToAttach = em.getReference(indiceValorCollectionNewIndiceValorToAttach.getClass(), indiceValorCollectionNewIndiceValorToAttach.getId());
                attachedIndiceValorCollectionNew.add(indiceValorCollectionNewIndiceValorToAttach);
            }
            indiceValorCollectionNew = attachedIndiceValorCollectionNew;
            indice.setIndiceValorCollection(indiceValorCollectionNew);
            Collection<ObjetoiIndice> attachedObjetoiIndiceCollectionNew = new ArrayList<ObjetoiIndice>();
            for (ObjetoiIndice objetoiIndiceCollectionNewObjetoiIndiceToAttach : objetoiIndiceCollectionNew) {
                objetoiIndiceCollectionNewObjetoiIndiceToAttach = em.getReference(objetoiIndiceCollectionNewObjetoiIndiceToAttach.getClass(), objetoiIndiceCollectionNewObjetoiIndiceToAttach.getId());
                attachedObjetoiIndiceCollectionNew.add(objetoiIndiceCollectionNewObjetoiIndiceToAttach);
            }
            objetoiIndiceCollectionNew = attachedObjetoiIndiceCollectionNew;
            indice.setObjetoiIndiceCollection(objetoiIndiceCollectionNew);
            indice = em.merge(indice);
            for (IndiceValor indiceValorCollectionOldIndiceValor : indiceValorCollectionOld) {
                if (!indiceValorCollectionNew.contains(indiceValorCollectionOldIndiceValor)) {
                    indiceValorCollectionOldIndiceValor.setIndiceId(null);
                    indiceValorCollectionOldIndiceValor = em.merge(indiceValorCollectionOldIndiceValor);
                }
            }
            for (IndiceValor indiceValorCollectionNewIndiceValor : indiceValorCollectionNew) {
                if (!indiceValorCollectionOld.contains(indiceValorCollectionNewIndiceValor)) {
                    Indice oldIndiceIdOfIndiceValorCollectionNewIndiceValor = indiceValorCollectionNewIndiceValor.getIndiceId();
                    indiceValorCollectionNewIndiceValor.setIndiceId(indice);
                    indiceValorCollectionNewIndiceValor = em.merge(indiceValorCollectionNewIndiceValor);
                    if (oldIndiceIdOfIndiceValorCollectionNewIndiceValor != null && !oldIndiceIdOfIndiceValorCollectionNewIndiceValor.equals(indice)) {
                        oldIndiceIdOfIndiceValorCollectionNewIndiceValor.getIndiceValorCollection().remove(indiceValorCollectionNewIndiceValor);
                        oldIndiceIdOfIndiceValorCollectionNewIndiceValor = em.merge(oldIndiceIdOfIndiceValorCollectionNewIndiceValor);
                    }
                }
            }
            for (ObjetoiIndice objetoiIndiceCollectionOldObjetoiIndice : objetoiIndiceCollectionOld) {
                if (!objetoiIndiceCollectionNew.contains(objetoiIndiceCollectionOldObjetoiIndice)) {
                    objetoiIndiceCollectionOldObjetoiIndice.setIndiceId(null);
                    objetoiIndiceCollectionOldObjetoiIndice = em.merge(objetoiIndiceCollectionOldObjetoiIndice);
                }
            }
            for (ObjetoiIndice objetoiIndiceCollectionNewObjetoiIndice : objetoiIndiceCollectionNew) {
                if (!objetoiIndiceCollectionOld.contains(objetoiIndiceCollectionNewObjetoiIndice)) {
                    Indice oldIndiceIdOfObjetoiIndiceCollectionNewObjetoiIndice = objetoiIndiceCollectionNewObjetoiIndice.getIndiceId();
                    objetoiIndiceCollectionNewObjetoiIndice.setIndiceId(indice);
                    objetoiIndiceCollectionNewObjetoiIndice = em.merge(objetoiIndiceCollectionNewObjetoiIndice);
                    if (oldIndiceIdOfObjetoiIndiceCollectionNewObjetoiIndice != null && !oldIndiceIdOfObjetoiIndiceCollectionNewObjetoiIndice.equals(indice)) {
                        oldIndiceIdOfObjetoiIndiceCollectionNewObjetoiIndice.getObjetoiIndiceCollection().remove(objetoiIndiceCollectionNewObjetoiIndice);
                        oldIndiceIdOfObjetoiIndiceCollectionNewObjetoiIndice = em.merge(oldIndiceIdOfObjetoiIndiceCollectionNewObjetoiIndice);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = indice.getId();
                if (findIndice(id) == null) {
                    throw new NonexistentEntityException("The indice with id " + id + " no longer exists.");
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
            Indice indice;
            try {
                indice = em.getReference(Indice.class, id);
                indice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indice with id " + id + " no longer exists.", enfe);
            }
            Collection<IndiceValor> indiceValorCollection = indice.getIndiceValorCollection();
            for (IndiceValor indiceValorCollectionIndiceValor : indiceValorCollection) {
                indiceValorCollectionIndiceValor.setIndiceId(null);
                indiceValorCollectionIndiceValor = em.merge(indiceValorCollectionIndiceValor);
            }
            Collection<ObjetoiIndice> objetoiIndiceCollection = indice.getObjetoiIndiceCollection();
            for (ObjetoiIndice objetoiIndiceCollectionObjetoiIndice : objetoiIndiceCollection) {
                objetoiIndiceCollectionObjetoiIndice.setIndiceId(null);
                objetoiIndiceCollectionObjetoiIndice = em.merge(objetoiIndiceCollectionObjetoiIndice);
            }
            em.remove(indice);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Indice> findIndiceEntities() {
        return findIndiceEntities(true, -1, -1);
    }

    public List<Indice> findIndiceEntities(int maxResults, int firstResult) {
        return findIndiceEntities(false, maxResults, firstResult);
    }

    private List<Indice> findIndiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Indice as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Indice findIndice(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Indice.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndiceCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Indice as o");
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
            conn.createStatement().execute("SET IDENTITY_INSERT Indice ON");             
            conn.createStatement().execute(string);             
            conn.createStatement().execute("SET IDENTITY_INSERT Indice OFF"); 
           
            
        } finally {
            em.close();
        }
    }
    
}
