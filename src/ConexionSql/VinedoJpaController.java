/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Vinedo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import Entidades.Localidad;
import Entidades.QQIngresados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author analian
 */
public class VinedoJpaController implements Serializable {

    public VinedoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vinedo vinedo) throws PreexistingEntityException, Exception {
        if (vinedo.getQQIngresadosCollection() == null) {
            vinedo.setQQIngresadosCollection(new ArrayList<QQIngresados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi creditoId = vinedo.getCreditoId();
            if (creditoId != null) {
                creditoId = em.getReference(creditoId.getClass(), creditoId.getId());
                vinedo.setCreditoId(creditoId);
            }
            Localidad localidadIDLOCALIDAD = vinedo.getLocalidadIDLOCALIDAD();
            if (localidadIDLOCALIDAD != null) {
                localidadIDLOCALIDAD = em.getReference(localidadIDLOCALIDAD.getClass(), localidadIDLOCALIDAD.getIdlocalidad());
                vinedo.setLocalidadIDLOCALIDAD(localidadIDLOCALIDAD);
            }
            Collection<QQIngresados> attachedQQIngresadosCollection = new ArrayList<QQIngresados>();
            for (QQIngresados QQIngresadosCollectionQQIngresadosToAttach : vinedo.getQQIngresadosCollection()) {
                QQIngresadosCollectionQQIngresadosToAttach = em.getReference(QQIngresadosCollectionQQIngresadosToAttach.getClass(), QQIngresadosCollectionQQIngresadosToAttach.getId());
                attachedQQIngresadosCollection.add(QQIngresadosCollectionQQIngresadosToAttach);
            }
            vinedo.setQQIngresadosCollection(attachedQQIngresadosCollection);
            em.persist(vinedo);
            if (creditoId != null) {
                creditoId.getVinedoCollection().add(vinedo);
                creditoId = em.merge(creditoId);
            }
            if (localidadIDLOCALIDAD != null) {
                localidadIDLOCALIDAD.getVinedoCollection().add(vinedo);
                localidadIDLOCALIDAD = em.merge(localidadIDLOCALIDAD);
            }
            for (QQIngresados QQIngresadosCollectionQQIngresados : vinedo.getQQIngresadosCollection()) {
                Vinedo oldVinedoIdOfQQIngresadosCollectionQQIngresados = QQIngresadosCollectionQQIngresados.getVinedoId();
                QQIngresadosCollectionQQIngresados.setVinedoId(vinedo);
                QQIngresadosCollectionQQIngresados = em.merge(QQIngresadosCollectionQQIngresados);
                if (oldVinedoIdOfQQIngresadosCollectionQQIngresados != null) {
                    oldVinedoIdOfQQIngresadosCollectionQQIngresados.getQQIngresadosCollection().remove(QQIngresadosCollectionQQIngresados);
                    oldVinedoIdOfQQIngresadosCollectionQQIngresados = em.merge(oldVinedoIdOfQQIngresadosCollectionQQIngresados);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVinedo(vinedo.getId()) != null) {
                throw new PreexistingEntityException("Vinedo " + vinedo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vinedo vinedo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinedo persistentVinedo = em.find(Vinedo.class, vinedo.getId());
            Objetoi creditoIdOld = persistentVinedo.getCreditoId();
            Objetoi creditoIdNew = vinedo.getCreditoId();
            Localidad localidadIDLOCALIDADOld = persistentVinedo.getLocalidadIDLOCALIDAD();
            Localidad localidadIDLOCALIDADNew = vinedo.getLocalidadIDLOCALIDAD();
            Collection<QQIngresados> QQIngresadosCollectionOld = persistentVinedo.getQQIngresadosCollection();
            Collection<QQIngresados> QQIngresadosCollectionNew = vinedo.getQQIngresadosCollection();
            if (creditoIdNew != null) {
                creditoIdNew = em.getReference(creditoIdNew.getClass(), creditoIdNew.getId());
                vinedo.setCreditoId(creditoIdNew);
            }
            if (localidadIDLOCALIDADNew != null) {
                localidadIDLOCALIDADNew = em.getReference(localidadIDLOCALIDADNew.getClass(), localidadIDLOCALIDADNew.getIdlocalidad());
                vinedo.setLocalidadIDLOCALIDAD(localidadIDLOCALIDADNew);
            }
            Collection<QQIngresados> attachedQQIngresadosCollectionNew = new ArrayList<QQIngresados>();
            for (QQIngresados QQIngresadosCollectionNewQQIngresadosToAttach : QQIngresadosCollectionNew) {
                QQIngresadosCollectionNewQQIngresadosToAttach = em.getReference(QQIngresadosCollectionNewQQIngresadosToAttach.getClass(), QQIngresadosCollectionNewQQIngresadosToAttach.getId());
                attachedQQIngresadosCollectionNew.add(QQIngresadosCollectionNewQQIngresadosToAttach);
            }
            QQIngresadosCollectionNew = attachedQQIngresadosCollectionNew;
            vinedo.setQQIngresadosCollection(QQIngresadosCollectionNew);
            vinedo = em.merge(vinedo);
            if (creditoIdOld != null && !creditoIdOld.equals(creditoIdNew)) {
                creditoIdOld.getVinedoCollection().remove(vinedo);
                creditoIdOld = em.merge(creditoIdOld);
            }
            if (creditoIdNew != null && !creditoIdNew.equals(creditoIdOld)) {
                creditoIdNew.getVinedoCollection().add(vinedo);
                creditoIdNew = em.merge(creditoIdNew);
            }
            if (localidadIDLOCALIDADOld != null && !localidadIDLOCALIDADOld.equals(localidadIDLOCALIDADNew)) {
                localidadIDLOCALIDADOld.getVinedoCollection().remove(vinedo);
                localidadIDLOCALIDADOld = em.merge(localidadIDLOCALIDADOld);
            }
            if (localidadIDLOCALIDADNew != null && !localidadIDLOCALIDADNew.equals(localidadIDLOCALIDADOld)) {
                localidadIDLOCALIDADNew.getVinedoCollection().add(vinedo);
                localidadIDLOCALIDADNew = em.merge(localidadIDLOCALIDADNew);
            }
            for (QQIngresados QQIngresadosCollectionOldQQIngresados : QQIngresadosCollectionOld) {
                if (!QQIngresadosCollectionNew.contains(QQIngresadosCollectionOldQQIngresados)) {
                    QQIngresadosCollectionOldQQIngresados.setVinedoId(null);
                    QQIngresadosCollectionOldQQIngresados = em.merge(QQIngresadosCollectionOldQQIngresados);
                }
            }
            for (QQIngresados QQIngresadosCollectionNewQQIngresados : QQIngresadosCollectionNew) {
                if (!QQIngresadosCollectionOld.contains(QQIngresadosCollectionNewQQIngresados)) {
                    Vinedo oldVinedoIdOfQQIngresadosCollectionNewQQIngresados = QQIngresadosCollectionNewQQIngresados.getVinedoId();
                    QQIngresadosCollectionNewQQIngresados.setVinedoId(vinedo);
                    QQIngresadosCollectionNewQQIngresados = em.merge(QQIngresadosCollectionNewQQIngresados);
                    if (oldVinedoIdOfQQIngresadosCollectionNewQQIngresados != null && !oldVinedoIdOfQQIngresadosCollectionNewQQIngresados.equals(vinedo)) {
                        oldVinedoIdOfQQIngresadosCollectionNewQQIngresados.getQQIngresadosCollection().remove(QQIngresadosCollectionNewQQIngresados);
                        oldVinedoIdOfQQIngresadosCollectionNewQQIngresados = em.merge(oldVinedoIdOfQQIngresadosCollectionNewQQIngresados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = vinedo.getId();
                if (findVinedo(id) == null) {
                    throw new NonexistentEntityException("The vinedo with id " + id + " no longer exists.");
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
            Vinedo vinedo;
            try {
                vinedo = em.getReference(Vinedo.class, id);
                vinedo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vinedo with id " + id + " no longer exists.", enfe);
            }
            Objetoi creditoId = vinedo.getCreditoId();
            if (creditoId != null) {
                creditoId.getVinedoCollection().remove(vinedo);
                creditoId = em.merge(creditoId);
            }
            Localidad localidadIDLOCALIDAD = vinedo.getLocalidadIDLOCALIDAD();
            if (localidadIDLOCALIDAD != null) {
                localidadIDLOCALIDAD.getVinedoCollection().remove(vinedo);
                localidadIDLOCALIDAD = em.merge(localidadIDLOCALIDAD);
            }
            Collection<QQIngresados> QQIngresadosCollection = vinedo.getQQIngresadosCollection();
            for (QQIngresados QQIngresadosCollectionQQIngresados : QQIngresadosCollection) {
                QQIngresadosCollectionQQIngresados.setVinedoId(null);
                QQIngresadosCollectionQQIngresados = em.merge(QQIngresadosCollectionQQIngresados);
            }
            em.remove(vinedo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vinedo> findVinedoEntities() {
        return findVinedoEntities(true, -1, -1);
    }

    public List<Vinedo> findVinedoEntities(int maxResults, int firstResult) {
        return findVinedoEntities(false, maxResults, firstResult);
    }

    private List<Vinedo> findVinedoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Vinedo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vinedo findVinedo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vinedo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVinedoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Vinedo as o");
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
