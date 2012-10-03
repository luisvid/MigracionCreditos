/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionAccess.exceptions.NonexistentEntityException;
import ConexionAccess.exceptions.PreexistingEntityException;
import Entidades.SubTipoLinea;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author analian
 */
public class SubTipoLineaJpaController implements Serializable {

    public SubTipoLineaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubTipoLinea subTipoLinea) throws PreexistingEntityException, Exception {
        if (subTipoLinea.getObjetoiCollection() == null) {
            subTipoLinea.setObjetoiCollection(new ArrayList<Objetoi>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Objetoi> attachedObjetoiCollection = new ArrayList<Objetoi>();
            for (Objetoi objetoiCollectionObjetoiToAttach : subTipoLinea.getObjetoiCollection()) {
                objetoiCollectionObjetoiToAttach = em.getReference(objetoiCollectionObjetoiToAttach.getClass(), objetoiCollectionObjetoiToAttach.getId());
                attachedObjetoiCollection.add(objetoiCollectionObjetoiToAttach);
            }
            subTipoLinea.setObjetoiCollection(attachedObjetoiCollection);
            em.persist(subTipoLinea);
            for (Objetoi objetoiCollectionObjetoi : subTipoLinea.getObjetoiCollection()) {
                SubTipoLinea oldSubTipoLineaidOfObjetoiCollectionObjetoi = objetoiCollectionObjetoi.getSubTipoLineaid();
                objetoiCollectionObjetoi.setSubTipoLineaid(subTipoLinea);
                objetoiCollectionObjetoi = em.merge(objetoiCollectionObjetoi);
                if (oldSubTipoLineaidOfObjetoiCollectionObjetoi != null) {
                    oldSubTipoLineaidOfObjetoiCollectionObjetoi.getObjetoiCollection().remove(objetoiCollectionObjetoi);
                    oldSubTipoLineaidOfObjetoiCollectionObjetoi = em.merge(oldSubTipoLineaidOfObjetoiCollectionObjetoi);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSubTipoLinea(subTipoLinea.getId()) != null) {
                throw new PreexistingEntityException("SubTipoLinea " + subTipoLinea + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubTipoLinea subTipoLinea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SubTipoLinea persistentSubTipoLinea = em.find(SubTipoLinea.class, subTipoLinea.getId());
            Collection<Objetoi> objetoiCollectionOld = persistentSubTipoLinea.getObjetoiCollection();
            Collection<Objetoi> objetoiCollectionNew = subTipoLinea.getObjetoiCollection();
            Collection<Objetoi> attachedObjetoiCollectionNew = new ArrayList<Objetoi>();
            for (Objetoi objetoiCollectionNewObjetoiToAttach : objetoiCollectionNew) {
                objetoiCollectionNewObjetoiToAttach = em.getReference(objetoiCollectionNewObjetoiToAttach.getClass(), objetoiCollectionNewObjetoiToAttach.getId());
                attachedObjetoiCollectionNew.add(objetoiCollectionNewObjetoiToAttach);
            }
            objetoiCollectionNew = attachedObjetoiCollectionNew;
            subTipoLinea.setObjetoiCollection(objetoiCollectionNew);
            subTipoLinea = em.merge(subTipoLinea);
            for (Objetoi objetoiCollectionOldObjetoi : objetoiCollectionOld) {
                if (!objetoiCollectionNew.contains(objetoiCollectionOldObjetoi)) {
                    objetoiCollectionOldObjetoi.setSubTipoLineaid(null);
                    objetoiCollectionOldObjetoi = em.merge(objetoiCollectionOldObjetoi);
                }
            }
            for (Objetoi objetoiCollectionNewObjetoi : objetoiCollectionNew) {
                if (!objetoiCollectionOld.contains(objetoiCollectionNewObjetoi)) {
                    SubTipoLinea oldSubTipoLineaidOfObjetoiCollectionNewObjetoi = objetoiCollectionNewObjetoi.getSubTipoLineaid();
                    objetoiCollectionNewObjetoi.setSubTipoLineaid(subTipoLinea);
                    objetoiCollectionNewObjetoi = em.merge(objetoiCollectionNewObjetoi);
                    if (oldSubTipoLineaidOfObjetoiCollectionNewObjetoi != null && !oldSubTipoLineaidOfObjetoiCollectionNewObjetoi.equals(subTipoLinea)) {
                        oldSubTipoLineaidOfObjetoiCollectionNewObjetoi.getObjetoiCollection().remove(objetoiCollectionNewObjetoi);
                        oldSubTipoLineaidOfObjetoiCollectionNewObjetoi = em.merge(oldSubTipoLineaidOfObjetoiCollectionNewObjetoi);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = subTipoLinea.getId();
                if (findSubTipoLinea(id) == null) {
                    throw new NonexistentEntityException("The subTipoLinea with id " + id + " no longer exists.");
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
            SubTipoLinea subTipoLinea;
            try {
                subTipoLinea = em.getReference(SubTipoLinea.class, id);
                subTipoLinea.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subTipoLinea with id " + id + " no longer exists.", enfe);
            }
            Collection<Objetoi> objetoiCollection = subTipoLinea.getObjetoiCollection();
            for (Objetoi objetoiCollectionObjetoi : objetoiCollection) {
                objetoiCollectionObjetoi.setSubTipoLineaid(null);
                objetoiCollectionObjetoi = em.merge(objetoiCollectionObjetoi);
            }
            em.remove(subTipoLinea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SubTipoLinea> findSubTipoLineaEntities() {
        return findSubTipoLineaEntities(true, -1, -1);
    }

    public List<SubTipoLinea> findSubTipoLineaEntities(int maxResults, int firstResult) {
        return findSubTipoLineaEntities(false, maxResults, firstResult);
    }

    private List<SubTipoLinea> findSubTipoLineaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SubTipoLinea as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SubTipoLinea findSubTipoLinea(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubTipoLinea.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubTipoLineaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SubTipoLinea as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List findSubtipoLinea(Integer SubTipoLinea_id) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("SubTipoLinea.findById");
            q.setParameter("id", SubTipoLinea_id);
            List<Objetoi> o = q.getResultList();

            return o;
        } finally {
            ////em.close();
        }
    }

    public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA3_CRED_FTYC";

            Connection conn = DriverManager.getConnection(url, "admin", "1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT SubTipoLinea ON");
            conn.createStatement().execute(string);
            conn.createStatement().execute("SET IDENTITY_INSERT SubTipoLinea OFF");

        } finally {
            //System.out.println(string);
            //em.close();
        }
    }
}
