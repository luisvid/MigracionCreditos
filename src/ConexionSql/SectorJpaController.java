/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionAccess.exceptions.NonexistentEntityException;
import ConexionAccess.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Linea;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.BonTasa;
import Entidades.Sector;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author analian
 */
public class SectorJpaController implements Serializable {

    public SectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sector sector) throws PreexistingEntityException, Exception {
        if (sector.getLineaCollection() == null) {
            sector.setLineaCollection(new ArrayList<Linea>());
        }
        if (sector.getBonTasaCollection() == null) {
            sector.setBonTasaCollection(new ArrayList<BonTasa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Linea> attachedLineaCollection = new ArrayList<Linea>();
            for (Linea lineaCollectionLineaToAttach : sector.getLineaCollection()) {
                lineaCollectionLineaToAttach = em.getReference(lineaCollectionLineaToAttach.getClass(), lineaCollectionLineaToAttach.getId());
                attachedLineaCollection.add(lineaCollectionLineaToAttach);
            }
            sector.setLineaCollection(attachedLineaCollection);
            Collection<BonTasa> attachedBonTasaCollection = new ArrayList<BonTasa>();
            for (BonTasa bonTasaCollectionBonTasaToAttach : sector.getBonTasaCollection()) {
                bonTasaCollectionBonTasaToAttach = em.getReference(bonTasaCollectionBonTasaToAttach.getClass(), bonTasaCollectionBonTasaToAttach.getId());
                attachedBonTasaCollection.add(bonTasaCollectionBonTasaToAttach);
            }
            sector.setBonTasaCollection(attachedBonTasaCollection);
            em.persist(sector);
            for (Linea lineaCollectionLinea : sector.getLineaCollection()) {
                Sector oldSectorIdOfLineaCollectionLinea = lineaCollectionLinea.getSectorId();
                lineaCollectionLinea.setSectorId(sector);
                lineaCollectionLinea = em.merge(lineaCollectionLinea);
                if (oldSectorIdOfLineaCollectionLinea != null) {
                    oldSectorIdOfLineaCollectionLinea.getLineaCollection().remove(lineaCollectionLinea);
                    oldSectorIdOfLineaCollectionLinea = em.merge(oldSectorIdOfLineaCollectionLinea);
                }
            }
            for (BonTasa bonTasaCollectionBonTasa : sector.getBonTasaCollection()) {
                Sector oldSectorIdOfBonTasaCollectionBonTasa = bonTasaCollectionBonTasa.getSectorId();
                bonTasaCollectionBonTasa.setSectorId(sector);
                bonTasaCollectionBonTasa = em.merge(bonTasaCollectionBonTasa);
                if (oldSectorIdOfBonTasaCollectionBonTasa != null) {
                    oldSectorIdOfBonTasaCollectionBonTasa.getBonTasaCollection().remove(bonTasaCollectionBonTasa);
                    oldSectorIdOfBonTasaCollectionBonTasa = em.merge(oldSectorIdOfBonTasaCollectionBonTasa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSector(sector.getId()) != null) {
                throw new PreexistingEntityException("Sector " + sector + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sector sector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sector persistentSector = em.find(Sector.class, sector.getId());
            Collection<Linea> lineaCollectionOld = persistentSector.getLineaCollection();
            Collection<Linea> lineaCollectionNew = sector.getLineaCollection();
            Collection<BonTasa> bonTasaCollectionOld = persistentSector.getBonTasaCollection();
            Collection<BonTasa> bonTasaCollectionNew = sector.getBonTasaCollection();
            Collection<Linea> attachedLineaCollectionNew = new ArrayList<Linea>();
            for (Linea lineaCollectionNewLineaToAttach : lineaCollectionNew) {
                lineaCollectionNewLineaToAttach = em.getReference(lineaCollectionNewLineaToAttach.getClass(), lineaCollectionNewLineaToAttach.getId());
                attachedLineaCollectionNew.add(lineaCollectionNewLineaToAttach);
            }
            lineaCollectionNew = attachedLineaCollectionNew;
            sector.setLineaCollection(lineaCollectionNew);
            Collection<BonTasa> attachedBonTasaCollectionNew = new ArrayList<BonTasa>();
            for (BonTasa bonTasaCollectionNewBonTasaToAttach : bonTasaCollectionNew) {
                bonTasaCollectionNewBonTasaToAttach = em.getReference(bonTasaCollectionNewBonTasaToAttach.getClass(), bonTasaCollectionNewBonTasaToAttach.getId());
                attachedBonTasaCollectionNew.add(bonTasaCollectionNewBonTasaToAttach);
            }
            bonTasaCollectionNew = attachedBonTasaCollectionNew;
            sector.setBonTasaCollection(bonTasaCollectionNew);
            sector = em.merge(sector);
            for (Linea lineaCollectionOldLinea : lineaCollectionOld) {
                if (!lineaCollectionNew.contains(lineaCollectionOldLinea)) {
                    lineaCollectionOldLinea.setSectorId(null);
                    lineaCollectionOldLinea = em.merge(lineaCollectionOldLinea);
                }
            }
            for (Linea lineaCollectionNewLinea : lineaCollectionNew) {
                if (!lineaCollectionOld.contains(lineaCollectionNewLinea)) {
                    Sector oldSectorIdOfLineaCollectionNewLinea = lineaCollectionNewLinea.getSectorId();
                    lineaCollectionNewLinea.setSectorId(sector);
                    lineaCollectionNewLinea = em.merge(lineaCollectionNewLinea);
                    if (oldSectorIdOfLineaCollectionNewLinea != null && !oldSectorIdOfLineaCollectionNewLinea.equals(sector)) {
                        oldSectorIdOfLineaCollectionNewLinea.getLineaCollection().remove(lineaCollectionNewLinea);
                        oldSectorIdOfLineaCollectionNewLinea = em.merge(oldSectorIdOfLineaCollectionNewLinea);
                    }
                }
            }
            for (BonTasa bonTasaCollectionOldBonTasa : bonTasaCollectionOld) {
                if (!bonTasaCollectionNew.contains(bonTasaCollectionOldBonTasa)) {
                    bonTasaCollectionOldBonTasa.setSectorId(null);
                    bonTasaCollectionOldBonTasa = em.merge(bonTasaCollectionOldBonTasa);
                }
            }
            for (BonTasa bonTasaCollectionNewBonTasa : bonTasaCollectionNew) {
                if (!bonTasaCollectionOld.contains(bonTasaCollectionNewBonTasa)) {
                    Sector oldSectorIdOfBonTasaCollectionNewBonTasa = bonTasaCollectionNewBonTasa.getSectorId();
                    bonTasaCollectionNewBonTasa.setSectorId(sector);
                    bonTasaCollectionNewBonTasa = em.merge(bonTasaCollectionNewBonTasa);
                    if (oldSectorIdOfBonTasaCollectionNewBonTasa != null && !oldSectorIdOfBonTasaCollectionNewBonTasa.equals(sector)) {
                        oldSectorIdOfBonTasaCollectionNewBonTasa.getBonTasaCollection().remove(bonTasaCollectionNewBonTasa);
                        oldSectorIdOfBonTasaCollectionNewBonTasa = em.merge(oldSectorIdOfBonTasaCollectionNewBonTasa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = sector.getId();
                if (findSector(id) == null) {
                    throw new NonexistentEntityException("The sector with id " + id + " no longer exists.");
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
            Sector sector;
            try {
                sector = em.getReference(Sector.class, id);
                sector.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sector with id " + id + " no longer exists.", enfe);
            }
            Collection<Linea> lineaCollection = sector.getLineaCollection();
            for (Linea lineaCollectionLinea : lineaCollection) {
                lineaCollectionLinea.setSectorId(null);
                lineaCollectionLinea = em.merge(lineaCollectionLinea);
            }
            Collection<BonTasa> bonTasaCollection = sector.getBonTasaCollection();
            for (BonTasa bonTasaCollectionBonTasa : bonTasaCollection) {
                bonTasaCollectionBonTasa.setSectorId(null);
                bonTasaCollectionBonTasa = em.merge(bonTasaCollectionBonTasa);
            }
            em.remove(sector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sector> findSectorEntities() {
        return findSectorEntities(true, -1, -1);
    }

    public List<Sector> findSectorEntities(int maxResults, int firstResult) {
        return findSectorEntities(false, maxResults, firstResult);
    }

    private List<Sector> findSectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Sector as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Sector findSector(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sector.class, id);
        } finally {
            em.close();
        }
    }

    public int getSectorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Sector as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
