/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Cuota;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;
import Entidades.Emision;
import Entidades.Ctacte;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.BonDetalle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.FlushModeType;

/**
 *
 * @author analian
 */
public class CuotaJpaController implements Serializable {

    public CuotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuota cuota) throws PreexistingEntityException, Exception {
        if (cuota.getCtacteCollection() == null) {
            cuota.setCtacteCollection(new ArrayList<Ctacte>());
        }
        if (cuota.getBonDetalleCollection() == null) {
            cuota.setBonDetalleCollection(new ArrayList<BonDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi creditoId = cuota.getCreditoId();
            if (creditoId != null) {
                creditoId = em.getReference(creditoId.getClass(), creditoId.getId());
                cuota.setCreditoId(creditoId);
            }
            Emision emisionId = cuota.getEmisionId();
            if (emisionId != null) {
                emisionId = em.getReference(emisionId.getClass(), emisionId.getId());
                cuota.setEmisionId(emisionId);
            }
            Collection<Ctacte> attachedCtacteCollection = new ArrayList<Ctacte>();
            for (Ctacte ctacteCollectionCtacteToAttach : cuota.getCtacteCollection()) {
                ctacteCollectionCtacteToAttach = em.getReference(ctacteCollectionCtacteToAttach.getClass(), ctacteCollectionCtacteToAttach.getCtactePK());
                attachedCtacteCollection.add(ctacteCollectionCtacteToAttach);
            }
            cuota.setCtacteCollection(attachedCtacteCollection);
            Collection<BonDetalle> attachedBonDetalleCollection = new ArrayList<BonDetalle>();
            for (BonDetalle bonDetalleCollectionBonDetalleToAttach : cuota.getBonDetalleCollection()) {
                bonDetalleCollectionBonDetalleToAttach = em.getReference(bonDetalleCollectionBonDetalleToAttach.getClass(), bonDetalleCollectionBonDetalleToAttach.getId());
                attachedBonDetalleCollection.add(bonDetalleCollectionBonDetalleToAttach);
            }
            cuota.setBonDetalleCollection(attachedBonDetalleCollection);
            em.persist(cuota);
            if (creditoId != null) {
                creditoId.getCuotaCollection().add(cuota);
                creditoId = em.merge(creditoId);
            }
            if (emisionId != null) {
                emisionId.getCuotaCollection().add(cuota);
                emisionId = em.merge(emisionId);
            }
            for (Ctacte ctacteCollectionCtacte : cuota.getCtacteCollection()) {
                Cuota oldCuotaIdOfCtacteCollectionCtacte = ctacteCollectionCtacte.getCuotaId();
                ctacteCollectionCtacte.setCuotaId(cuota);
                ctacteCollectionCtacte = em.merge(ctacteCollectionCtacte);
                if (oldCuotaIdOfCtacteCollectionCtacte != null) {
                    oldCuotaIdOfCtacteCollectionCtacte.getCtacteCollection().remove(ctacteCollectionCtacte);
                    oldCuotaIdOfCtacteCollectionCtacte = em.merge(oldCuotaIdOfCtacteCollectionCtacte);
                }
            }
            for (BonDetalle bonDetalleCollectionBonDetalle : cuota.getBonDetalleCollection()) {
                Cuota oldCuotaIdOfBonDetalleCollectionBonDetalle = bonDetalleCollectionBonDetalle.getCuotaId();
                bonDetalleCollectionBonDetalle.setCuotaId(cuota);
                bonDetalleCollectionBonDetalle = em.merge(bonDetalleCollectionBonDetalle);
                if (oldCuotaIdOfBonDetalleCollectionBonDetalle != null) {
                    oldCuotaIdOfBonDetalleCollectionBonDetalle.getBonDetalleCollection().remove(bonDetalleCollectionBonDetalle);
                    oldCuotaIdOfBonDetalleCollectionBonDetalle = em.merge(oldCuotaIdOfBonDetalleCollectionBonDetalle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCuota(cuota.getId()) != null) {
                throw new PreexistingEntityException("Cuota " + cuota + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void edit(Cuota cuota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuota persistentCuota = em.find(Cuota.class, cuota.getId());
            Objetoi creditoIdOld = persistentCuota.getCreditoId();
            Objetoi creditoIdNew = cuota.getCreditoId();
            Emision emisionIdOld = persistentCuota.getEmisionId();
            Emision emisionIdNew = cuota.getEmisionId();
            Collection<Ctacte> ctacteCollectionOld = persistentCuota.getCtacteCollection();
            Collection<Ctacte> ctacteCollectionNew = cuota.getCtacteCollection();
            Collection<BonDetalle> bonDetalleCollectionOld = persistentCuota.getBonDetalleCollection();
            Collection<BonDetalle> bonDetalleCollectionNew = cuota.getBonDetalleCollection();
            if (creditoIdNew != null) {
                creditoIdNew = em.getReference(creditoIdNew.getClass(), creditoIdNew.getId());
                cuota.setCreditoId(creditoIdNew);
            }
            if (emisionIdNew != null) {
                emisionIdNew = em.getReference(emisionIdNew.getClass(), emisionIdNew.getId());
                cuota.setEmisionId(emisionIdNew);
            }
            Collection<Ctacte> attachedCtacteCollectionNew = new ArrayList<Ctacte>();
            for (Ctacte ctacteCollectionNewCtacteToAttach : ctacteCollectionNew) {
                ctacteCollectionNewCtacteToAttach = em.getReference(ctacteCollectionNewCtacteToAttach.getClass(), ctacteCollectionNewCtacteToAttach.getCtactePK());
                attachedCtacteCollectionNew.add(ctacteCollectionNewCtacteToAttach);
            }
            ctacteCollectionNew = attachedCtacteCollectionNew;
            cuota.setCtacteCollection(ctacteCollectionNew);
            Collection<BonDetalle> attachedBonDetalleCollectionNew = new ArrayList<BonDetalle>();
            for (BonDetalle bonDetalleCollectionNewBonDetalleToAttach : bonDetalleCollectionNew) {
                bonDetalleCollectionNewBonDetalleToAttach = em.getReference(bonDetalleCollectionNewBonDetalleToAttach.getClass(), bonDetalleCollectionNewBonDetalleToAttach.getId());
                attachedBonDetalleCollectionNew.add(bonDetalleCollectionNewBonDetalleToAttach);
            }
            bonDetalleCollectionNew = attachedBonDetalleCollectionNew;
            cuota.setBonDetalleCollection(bonDetalleCollectionNew);
            cuota = em.merge(cuota);
            if (creditoIdOld != null && !creditoIdOld.equals(creditoIdNew)) {
                creditoIdOld.getCuotaCollection().remove(cuota);
                creditoIdOld = em.merge(creditoIdOld);
            }
            if (creditoIdNew != null && !creditoIdNew.equals(creditoIdOld)) {
                creditoIdNew.getCuotaCollection().add(cuota);
                creditoIdNew = em.merge(creditoIdNew);
            }
            if (emisionIdOld != null && !emisionIdOld.equals(emisionIdNew)) {
                emisionIdOld.getCuotaCollection().remove(cuota);
                emisionIdOld = em.merge(emisionIdOld);
            }
            if (emisionIdNew != null && !emisionIdNew.equals(emisionIdOld)) {
                emisionIdNew.getCuotaCollection().add(cuota);
                emisionIdNew = em.merge(emisionIdNew);
            }
            for (Ctacte ctacteCollectionOldCtacte : ctacteCollectionOld) {
                if (!ctacteCollectionNew.contains(ctacteCollectionOldCtacte)) {
                    ctacteCollectionOldCtacte.setCuotaId(null);
                    ctacteCollectionOldCtacte = em.merge(ctacteCollectionOldCtacte);
                }
            }
            for (Ctacte ctacteCollectionNewCtacte : ctacteCollectionNew) {
                if (!ctacteCollectionOld.contains(ctacteCollectionNewCtacte)) {
                    Cuota oldCuotaIdOfCtacteCollectionNewCtacte = ctacteCollectionNewCtacte.getCuotaId();
                    ctacteCollectionNewCtacte.setCuotaId(cuota);
                    ctacteCollectionNewCtacte = em.merge(ctacteCollectionNewCtacte);
                    if (oldCuotaIdOfCtacteCollectionNewCtacte != null && !oldCuotaIdOfCtacteCollectionNewCtacte.equals(cuota)) {
                        oldCuotaIdOfCtacteCollectionNewCtacte.getCtacteCollection().remove(ctacteCollectionNewCtacte);
                        oldCuotaIdOfCtacteCollectionNewCtacte = em.merge(oldCuotaIdOfCtacteCollectionNewCtacte);
                    }
                }
            }
            for (BonDetalle bonDetalleCollectionOldBonDetalle : bonDetalleCollectionOld) {
                if (!bonDetalleCollectionNew.contains(bonDetalleCollectionOldBonDetalle)) {
                    bonDetalleCollectionOldBonDetalle.setCuotaId(null);
                    bonDetalleCollectionOldBonDetalle = em.merge(bonDetalleCollectionOldBonDetalle);
                }
            }
            for (BonDetalle bonDetalleCollectionNewBonDetalle : bonDetalleCollectionNew) {
                if (!bonDetalleCollectionOld.contains(bonDetalleCollectionNewBonDetalle)) {
                    Cuota oldCuotaIdOfBonDetalleCollectionNewBonDetalle = bonDetalleCollectionNewBonDetalle.getCuotaId();
                    bonDetalleCollectionNewBonDetalle.setCuotaId(cuota);
                    bonDetalleCollectionNewBonDetalle = em.merge(bonDetalleCollectionNewBonDetalle);
                    if (oldCuotaIdOfBonDetalleCollectionNewBonDetalle != null && !oldCuotaIdOfBonDetalleCollectionNewBonDetalle.equals(cuota)) {
                        oldCuotaIdOfBonDetalleCollectionNewBonDetalle.getBonDetalleCollection().remove(bonDetalleCollectionNewBonDetalle);
                        oldCuotaIdOfBonDetalleCollectionNewBonDetalle = em.merge(oldCuotaIdOfBonDetalleCollectionNewBonDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = cuota.getId();
                if (findCuota(id) == null) {
                    throw new NonexistentEntityException("The cuota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuota cuota;
            try {
                cuota = em.getReference(Cuota.class, id);
                cuota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuota with id " + id + " no longer exists.", enfe);
            }
            Objetoi creditoId = cuota.getCreditoId();
            if (creditoId != null) {
                creditoId.getCuotaCollection().remove(cuota);
                creditoId = em.merge(creditoId);
            }
            Emision emisionId = cuota.getEmisionId();
            if (emisionId != null) {
                emisionId.getCuotaCollection().remove(cuota);
                emisionId = em.merge(emisionId);
            }
            Collection<Ctacte> ctacteCollection = cuota.getCtacteCollection();
            for (Ctacte ctacteCollectionCtacte : ctacteCollection) {
                ctacteCollectionCtacte.setCuotaId(null);
                ctacteCollectionCtacte = em.merge(ctacteCollectionCtacte);
            }
            Collection<BonDetalle> bonDetalleCollection = cuota.getBonDetalleCollection();
            for (BonDetalle bonDetalleCollectionBonDetalle : bonDetalleCollection) {
                bonDetalleCollectionBonDetalle.setCuotaId(null);
                bonDetalleCollectionBonDetalle = em.merge(bonDetalleCollectionBonDetalle);
            }
            em.remove(cuota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public List<Cuota> findCuotaEntities() {
        return findCuotaEntities(true, -1, -1);
    }

    public List<Cuota> findCuotaEntities(int maxResults, int firstResult) {
        return findCuotaEntities(false, maxResults, firstResult);
    }

    private List<Cuota> findCuotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cuota as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            //em.close();
        }
    }

    public Cuota findCuota(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuota.class, id);
        } finally {
            //em.close();
        }
    }

    public int getCuotaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cuota as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
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
            //em.close();
        }
    }

    public List<Cuota> finCuotasCredito(Objetoi objetoi) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Cuota.findByCredito_id");
           // Query q = em.createQuery("Select c "+"from Cuota c"+" where c.creditoId = '"+objetoi+"'");
            q.setParameter("Objetoi", objetoi);
            List<Cuota> o = q.getResultList();
            return o;
        } finally {
           // //em.close();
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
            ////em.close();
        }
    }

    public List<Cuota> findAll() {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNativeQuery("select id, credito_id from Cuota ", Cuota.class);
            //inner Join ObjetoiBonificacion on (Cuota.credito_id = ObjetoiBonificacion.idCredito)
       
            List<Cuota> o = q.getResultList();
          
            return o;
        } finally {
            //em.close();
        }
    }

    public List findAll2() {
       EntityManager em = getEntityManager();


        try {

            Query q = em.createNativeQuery("select credito_id from Cuota inner Join ObjetoiBonificacion on (Cuota.credito_id = ObjetoiBonificacion.idCredito)");
            //inner Join ObjetoiBonificacion on (Cuota.credito_id = ObjetoiBonificacion.idCredito)
      // List<Cuota>
            List o = q.getResultList();
          
            return o;
        } finally {
            //em.close();
        }
    }
    
    public void closeEEntityManager(){
        EntityManager em = getEntityManager();
        em.close();
    }
    
    
}
