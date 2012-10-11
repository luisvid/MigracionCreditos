/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.BonTasa;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Turno;
import Entidades.Sector;
import Entidades.BonTasaEstado;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.TitularesBonTasa;
import Entidades.BonTasaIndice;
import Entidades.CuotaBonTasa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class BonTasaJpaController implements Serializable {

    public BonTasaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BonTasa bonTasa) throws PreexistingEntityException, Exception {
        if (bonTasa.getBonTasaEstadoCollection() == null) {
            bonTasa.setBonTasaEstadoCollection(new ArrayList<BonTasaEstado>());
        }
        if (bonTasa.getTitularesBonTasaCollection() == null) {
            bonTasa.setTitularesBonTasaCollection(new ArrayList<TitularesBonTasa>());
        }
        if (bonTasa.getBonTasaIndiceCollection() == null) {
            bonTasa.setBonTasaIndiceCollection(new ArrayList<BonTasaIndice>());
        }
        if (bonTasa.getCuotaBonTasaCollection() == null) {
            bonTasa.setCuotaBonTasaCollection(new ArrayList<CuotaBonTasa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turnoId = bonTasa.getTurnoId();
            if (turnoId != null) {
                turnoId = em.getReference(turnoId.getClass(), turnoId.getId());
                bonTasa.setTurnoId(turnoId);
            }
            Sector sectorId = bonTasa.getSectorId();
            if (sectorId != null) {
                sectorId = em.getReference(sectorId.getClass(), sectorId.getId());
                bonTasa.setSectorId(sectorId);
            }
            Collection<BonTasaEstado> attachedBonTasaEstadoCollection = new ArrayList<BonTasaEstado>();
            for (BonTasaEstado bonTasaEstadoCollectionBonTasaEstadoToAttach : bonTasa.getBonTasaEstadoCollection()) {
                bonTasaEstadoCollectionBonTasaEstadoToAttach = em.getReference(bonTasaEstadoCollectionBonTasaEstadoToAttach.getClass(), bonTasaEstadoCollectionBonTasaEstadoToAttach.getId());
                attachedBonTasaEstadoCollection.add(bonTasaEstadoCollectionBonTasaEstadoToAttach);
            }
            bonTasa.setBonTasaEstadoCollection(attachedBonTasaEstadoCollection);
            Collection<TitularesBonTasa> attachedTitularesBonTasaCollection = new ArrayList<TitularesBonTasa>();
            for (TitularesBonTasa titularesBonTasaCollectionTitularesBonTasaToAttach : bonTasa.getTitularesBonTasaCollection()) {
                titularesBonTasaCollectionTitularesBonTasaToAttach = em.getReference(titularesBonTasaCollectionTitularesBonTasaToAttach.getClass(), titularesBonTasaCollectionTitularesBonTasaToAttach.getId());
                attachedTitularesBonTasaCollection.add(titularesBonTasaCollectionTitularesBonTasaToAttach);
            }
            bonTasa.setTitularesBonTasaCollection(attachedTitularesBonTasaCollection);
            Collection<BonTasaIndice> attachedBonTasaIndiceCollection = new ArrayList<BonTasaIndice>();
            for (BonTasaIndice bonTasaIndiceCollectionBonTasaIndiceToAttach : bonTasa.getBonTasaIndiceCollection()) {
                bonTasaIndiceCollectionBonTasaIndiceToAttach = em.getReference(bonTasaIndiceCollectionBonTasaIndiceToAttach.getClass(), bonTasaIndiceCollectionBonTasaIndiceToAttach.getId());
                attachedBonTasaIndiceCollection.add(bonTasaIndiceCollectionBonTasaIndiceToAttach);
            }
            bonTasa.setBonTasaIndiceCollection(attachedBonTasaIndiceCollection);
            Collection<CuotaBonTasa> attachedCuotaBonTasaCollection = new ArrayList<CuotaBonTasa>();
            for (CuotaBonTasa cuotaBonTasaCollectionCuotaBonTasaToAttach : bonTasa.getCuotaBonTasaCollection()) {
                cuotaBonTasaCollectionCuotaBonTasaToAttach = em.getReference(cuotaBonTasaCollectionCuotaBonTasaToAttach.getClass(), cuotaBonTasaCollectionCuotaBonTasaToAttach.getId());
                attachedCuotaBonTasaCollection.add(cuotaBonTasaCollectionCuotaBonTasaToAttach);
            }
            bonTasa.setCuotaBonTasaCollection(attachedCuotaBonTasaCollection);
            em.persist(bonTasa);
            if (turnoId != null) {
                turnoId.getBonTasaCollection().add(bonTasa);
                turnoId = em.merge(turnoId);
            }
            if (sectorId != null) {
                sectorId.getBonTasaCollection().add(bonTasa);
                sectorId = em.merge(sectorId);
            }
            for (BonTasaEstado bonTasaEstadoCollectionBonTasaEstado : bonTasa.getBonTasaEstadoCollection()) {
                BonTasa oldBonTasaidOfBonTasaEstadoCollectionBonTasaEstado = bonTasaEstadoCollectionBonTasaEstado.getBonTasaid();
                bonTasaEstadoCollectionBonTasaEstado.setBonTasaid(bonTasa);
                bonTasaEstadoCollectionBonTasaEstado = em.merge(bonTasaEstadoCollectionBonTasaEstado);
                if (oldBonTasaidOfBonTasaEstadoCollectionBonTasaEstado != null) {
                    oldBonTasaidOfBonTasaEstadoCollectionBonTasaEstado.getBonTasaEstadoCollection().remove(bonTasaEstadoCollectionBonTasaEstado);
                    oldBonTasaidOfBonTasaEstadoCollectionBonTasaEstado = em.merge(oldBonTasaidOfBonTasaEstadoCollectionBonTasaEstado);
                }
            }
            for (TitularesBonTasa titularesBonTasaCollectionTitularesBonTasa : bonTasa.getTitularesBonTasaCollection()) {
                BonTasa oldBonTasaidOfTitularesBonTasaCollectionTitularesBonTasa = titularesBonTasaCollectionTitularesBonTasa.getBonTasaid();
                titularesBonTasaCollectionTitularesBonTasa.setBonTasaid(bonTasa);
                titularesBonTasaCollectionTitularesBonTasa = em.merge(titularesBonTasaCollectionTitularesBonTasa);
                if (oldBonTasaidOfTitularesBonTasaCollectionTitularesBonTasa != null) {
                    oldBonTasaidOfTitularesBonTasaCollectionTitularesBonTasa.getTitularesBonTasaCollection().remove(titularesBonTasaCollectionTitularesBonTasa);
                    oldBonTasaidOfTitularesBonTasaCollectionTitularesBonTasa = em.merge(oldBonTasaidOfTitularesBonTasaCollectionTitularesBonTasa);
                }
            }
            for (BonTasaIndice bonTasaIndiceCollectionBonTasaIndice : bonTasa.getBonTasaIndiceCollection()) {
                BonTasa oldBonTasaidOfBonTasaIndiceCollectionBonTasaIndice = bonTasaIndiceCollectionBonTasaIndice.getBonTasaid();
                bonTasaIndiceCollectionBonTasaIndice.setBonTasaid(bonTasa);
                bonTasaIndiceCollectionBonTasaIndice = em.merge(bonTasaIndiceCollectionBonTasaIndice);
                if (oldBonTasaidOfBonTasaIndiceCollectionBonTasaIndice != null) {
                    oldBonTasaidOfBonTasaIndiceCollectionBonTasaIndice.getBonTasaIndiceCollection().remove(bonTasaIndiceCollectionBonTasaIndice);
                    oldBonTasaidOfBonTasaIndiceCollectionBonTasaIndice = em.merge(oldBonTasaidOfBonTasaIndiceCollectionBonTasaIndice);
                }
            }
            for (CuotaBonTasa cuotaBonTasaCollectionCuotaBonTasa : bonTasa.getCuotaBonTasaCollection()) {
                BonTasa oldBonTasaidOfCuotaBonTasaCollectionCuotaBonTasa = cuotaBonTasaCollectionCuotaBonTasa.getBonTasaid();
                cuotaBonTasaCollectionCuotaBonTasa.setBonTasaid(bonTasa);
                cuotaBonTasaCollectionCuotaBonTasa = em.merge(cuotaBonTasaCollectionCuotaBonTasa);
                if (oldBonTasaidOfCuotaBonTasaCollectionCuotaBonTasa != null) {
                    oldBonTasaidOfCuotaBonTasaCollectionCuotaBonTasa.getCuotaBonTasaCollection().remove(cuotaBonTasaCollectionCuotaBonTasa);
                    oldBonTasaidOfCuotaBonTasaCollectionCuotaBonTasa = em.merge(oldBonTasaidOfCuotaBonTasaCollectionCuotaBonTasa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBonTasa(bonTasa.getId()) != null) {
                throw new PreexistingEntityException("BonTasa " + bonTasa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BonTasa bonTasa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BonTasa persistentBonTasa = em.find(BonTasa.class, bonTasa.getId());
            Turno turnoIdOld = persistentBonTasa.getTurnoId();
            Turno turnoIdNew = bonTasa.getTurnoId();
            Sector sectorIdOld = persistentBonTasa.getSectorId();
            Sector sectorIdNew = bonTasa.getSectorId();
            Collection<BonTasaEstado> bonTasaEstadoCollectionOld = persistentBonTasa.getBonTasaEstadoCollection();
            Collection<BonTasaEstado> bonTasaEstadoCollectionNew = bonTasa.getBonTasaEstadoCollection();
            Collection<TitularesBonTasa> titularesBonTasaCollectionOld = persistentBonTasa.getTitularesBonTasaCollection();
            Collection<TitularesBonTasa> titularesBonTasaCollectionNew = bonTasa.getTitularesBonTasaCollection();
            Collection<BonTasaIndice> bonTasaIndiceCollectionOld = persistentBonTasa.getBonTasaIndiceCollection();
            Collection<BonTasaIndice> bonTasaIndiceCollectionNew = bonTasa.getBonTasaIndiceCollection();
            Collection<CuotaBonTasa> cuotaBonTasaCollectionOld = persistentBonTasa.getCuotaBonTasaCollection();
            Collection<CuotaBonTasa> cuotaBonTasaCollectionNew = bonTasa.getCuotaBonTasaCollection();
            if (turnoIdNew != null) {
                turnoIdNew = em.getReference(turnoIdNew.getClass(), turnoIdNew.getId());
                bonTasa.setTurnoId(turnoIdNew);
            }
            if (sectorIdNew != null) {
                sectorIdNew = em.getReference(sectorIdNew.getClass(), sectorIdNew.getId());
                bonTasa.setSectorId(sectorIdNew);
            }
            Collection<BonTasaEstado> attachedBonTasaEstadoCollectionNew = new ArrayList<BonTasaEstado>();
            for (BonTasaEstado bonTasaEstadoCollectionNewBonTasaEstadoToAttach : bonTasaEstadoCollectionNew) {
                bonTasaEstadoCollectionNewBonTasaEstadoToAttach = em.getReference(bonTasaEstadoCollectionNewBonTasaEstadoToAttach.getClass(), bonTasaEstadoCollectionNewBonTasaEstadoToAttach.getId());
                attachedBonTasaEstadoCollectionNew.add(bonTasaEstadoCollectionNewBonTasaEstadoToAttach);
            }
            bonTasaEstadoCollectionNew = attachedBonTasaEstadoCollectionNew;
            bonTasa.setBonTasaEstadoCollection(bonTasaEstadoCollectionNew);
            Collection<TitularesBonTasa> attachedTitularesBonTasaCollectionNew = new ArrayList<TitularesBonTasa>();
            for (TitularesBonTasa titularesBonTasaCollectionNewTitularesBonTasaToAttach : titularesBonTasaCollectionNew) {
                titularesBonTasaCollectionNewTitularesBonTasaToAttach = em.getReference(titularesBonTasaCollectionNewTitularesBonTasaToAttach.getClass(), titularesBonTasaCollectionNewTitularesBonTasaToAttach.getId());
                attachedTitularesBonTasaCollectionNew.add(titularesBonTasaCollectionNewTitularesBonTasaToAttach);
            }
            titularesBonTasaCollectionNew = attachedTitularesBonTasaCollectionNew;
            bonTasa.setTitularesBonTasaCollection(titularesBonTasaCollectionNew);
            Collection<BonTasaIndice> attachedBonTasaIndiceCollectionNew = new ArrayList<BonTasaIndice>();
            for (BonTasaIndice bonTasaIndiceCollectionNewBonTasaIndiceToAttach : bonTasaIndiceCollectionNew) {
                bonTasaIndiceCollectionNewBonTasaIndiceToAttach = em.getReference(bonTasaIndiceCollectionNewBonTasaIndiceToAttach.getClass(), bonTasaIndiceCollectionNewBonTasaIndiceToAttach.getId());
                attachedBonTasaIndiceCollectionNew.add(bonTasaIndiceCollectionNewBonTasaIndiceToAttach);
            }
            bonTasaIndiceCollectionNew = attachedBonTasaIndiceCollectionNew;
            bonTasa.setBonTasaIndiceCollection(bonTasaIndiceCollectionNew);
            Collection<CuotaBonTasa> attachedCuotaBonTasaCollectionNew = new ArrayList<CuotaBonTasa>();
            for (CuotaBonTasa cuotaBonTasaCollectionNewCuotaBonTasaToAttach : cuotaBonTasaCollectionNew) {
                cuotaBonTasaCollectionNewCuotaBonTasaToAttach = em.getReference(cuotaBonTasaCollectionNewCuotaBonTasaToAttach.getClass(), cuotaBonTasaCollectionNewCuotaBonTasaToAttach.getId());
                attachedCuotaBonTasaCollectionNew.add(cuotaBonTasaCollectionNewCuotaBonTasaToAttach);
            }
            cuotaBonTasaCollectionNew = attachedCuotaBonTasaCollectionNew;
            bonTasa.setCuotaBonTasaCollection(cuotaBonTasaCollectionNew);
            bonTasa = em.merge(bonTasa);
            if (turnoIdOld != null && !turnoIdOld.equals(turnoIdNew)) {
                turnoIdOld.getBonTasaCollection().remove(bonTasa);
                turnoIdOld = em.merge(turnoIdOld);
            }
            if (turnoIdNew != null && !turnoIdNew.equals(turnoIdOld)) {
                turnoIdNew.getBonTasaCollection().add(bonTasa);
                turnoIdNew = em.merge(turnoIdNew);
            }
            if (sectorIdOld != null && !sectorIdOld.equals(sectorIdNew)) {
                sectorIdOld.getBonTasaCollection().remove(bonTasa);
                sectorIdOld = em.merge(sectorIdOld);
            }
            if (sectorIdNew != null && !sectorIdNew.equals(sectorIdOld)) {
                sectorIdNew.getBonTasaCollection().add(bonTasa);
                sectorIdNew = em.merge(sectorIdNew);
            }
            for (BonTasaEstado bonTasaEstadoCollectionOldBonTasaEstado : bonTasaEstadoCollectionOld) {
                if (!bonTasaEstadoCollectionNew.contains(bonTasaEstadoCollectionOldBonTasaEstado)) {
                    bonTasaEstadoCollectionOldBonTasaEstado.setBonTasaid(null);
                    bonTasaEstadoCollectionOldBonTasaEstado = em.merge(bonTasaEstadoCollectionOldBonTasaEstado);
                }
            }
            for (BonTasaEstado bonTasaEstadoCollectionNewBonTasaEstado : bonTasaEstadoCollectionNew) {
                if (!bonTasaEstadoCollectionOld.contains(bonTasaEstadoCollectionNewBonTasaEstado)) {
                    BonTasa oldBonTasaidOfBonTasaEstadoCollectionNewBonTasaEstado = bonTasaEstadoCollectionNewBonTasaEstado.getBonTasaid();
                    bonTasaEstadoCollectionNewBonTasaEstado.setBonTasaid(bonTasa);
                    bonTasaEstadoCollectionNewBonTasaEstado = em.merge(bonTasaEstadoCollectionNewBonTasaEstado);
                    if (oldBonTasaidOfBonTasaEstadoCollectionNewBonTasaEstado != null && !oldBonTasaidOfBonTasaEstadoCollectionNewBonTasaEstado.equals(bonTasa)) {
                        oldBonTasaidOfBonTasaEstadoCollectionNewBonTasaEstado.getBonTasaEstadoCollection().remove(bonTasaEstadoCollectionNewBonTasaEstado);
                        oldBonTasaidOfBonTasaEstadoCollectionNewBonTasaEstado = em.merge(oldBonTasaidOfBonTasaEstadoCollectionNewBonTasaEstado);
                    }
                }
            }
            for (TitularesBonTasa titularesBonTasaCollectionOldTitularesBonTasa : titularesBonTasaCollectionOld) {
                if (!titularesBonTasaCollectionNew.contains(titularesBonTasaCollectionOldTitularesBonTasa)) {
                    titularesBonTasaCollectionOldTitularesBonTasa.setBonTasaid(null);
                    titularesBonTasaCollectionOldTitularesBonTasa = em.merge(titularesBonTasaCollectionOldTitularesBonTasa);
                }
            }
            for (TitularesBonTasa titularesBonTasaCollectionNewTitularesBonTasa : titularesBonTasaCollectionNew) {
                if (!titularesBonTasaCollectionOld.contains(titularesBonTasaCollectionNewTitularesBonTasa)) {
                    BonTasa oldBonTasaidOfTitularesBonTasaCollectionNewTitularesBonTasa = titularesBonTasaCollectionNewTitularesBonTasa.getBonTasaid();
                    titularesBonTasaCollectionNewTitularesBonTasa.setBonTasaid(bonTasa);
                    titularesBonTasaCollectionNewTitularesBonTasa = em.merge(titularesBonTasaCollectionNewTitularesBonTasa);
                    if (oldBonTasaidOfTitularesBonTasaCollectionNewTitularesBonTasa != null && !oldBonTasaidOfTitularesBonTasaCollectionNewTitularesBonTasa.equals(bonTasa)) {
                        oldBonTasaidOfTitularesBonTasaCollectionNewTitularesBonTasa.getTitularesBonTasaCollection().remove(titularesBonTasaCollectionNewTitularesBonTasa);
                        oldBonTasaidOfTitularesBonTasaCollectionNewTitularesBonTasa = em.merge(oldBonTasaidOfTitularesBonTasaCollectionNewTitularesBonTasa);
                    }
                }
            }
            for (BonTasaIndice bonTasaIndiceCollectionOldBonTasaIndice : bonTasaIndiceCollectionOld) {
                if (!bonTasaIndiceCollectionNew.contains(bonTasaIndiceCollectionOldBonTasaIndice)) {
                    bonTasaIndiceCollectionOldBonTasaIndice.setBonTasaid(null);
                    bonTasaIndiceCollectionOldBonTasaIndice = em.merge(bonTasaIndiceCollectionOldBonTasaIndice);
                }
            }
            for (BonTasaIndice bonTasaIndiceCollectionNewBonTasaIndice : bonTasaIndiceCollectionNew) {
                if (!bonTasaIndiceCollectionOld.contains(bonTasaIndiceCollectionNewBonTasaIndice)) {
                    BonTasa oldBonTasaidOfBonTasaIndiceCollectionNewBonTasaIndice = bonTasaIndiceCollectionNewBonTasaIndice.getBonTasaid();
                    bonTasaIndiceCollectionNewBonTasaIndice.setBonTasaid(bonTasa);
                    bonTasaIndiceCollectionNewBonTasaIndice = em.merge(bonTasaIndiceCollectionNewBonTasaIndice);
                    if (oldBonTasaidOfBonTasaIndiceCollectionNewBonTasaIndice != null && !oldBonTasaidOfBonTasaIndiceCollectionNewBonTasaIndice.equals(bonTasa)) {
                        oldBonTasaidOfBonTasaIndiceCollectionNewBonTasaIndice.getBonTasaIndiceCollection().remove(bonTasaIndiceCollectionNewBonTasaIndice);
                        oldBonTasaidOfBonTasaIndiceCollectionNewBonTasaIndice = em.merge(oldBonTasaidOfBonTasaIndiceCollectionNewBonTasaIndice);
                    }
                }
            }
            for (CuotaBonTasa cuotaBonTasaCollectionOldCuotaBonTasa : cuotaBonTasaCollectionOld) {
                if (!cuotaBonTasaCollectionNew.contains(cuotaBonTasaCollectionOldCuotaBonTasa)) {
                    cuotaBonTasaCollectionOldCuotaBonTasa.setBonTasaid(null);
                    cuotaBonTasaCollectionOldCuotaBonTasa = em.merge(cuotaBonTasaCollectionOldCuotaBonTasa);
                }
            }
            for (CuotaBonTasa cuotaBonTasaCollectionNewCuotaBonTasa : cuotaBonTasaCollectionNew) {
                if (!cuotaBonTasaCollectionOld.contains(cuotaBonTasaCollectionNewCuotaBonTasa)) {
                    BonTasa oldBonTasaidOfCuotaBonTasaCollectionNewCuotaBonTasa = cuotaBonTasaCollectionNewCuotaBonTasa.getBonTasaid();
                    cuotaBonTasaCollectionNewCuotaBonTasa.setBonTasaid(bonTasa);
                    cuotaBonTasaCollectionNewCuotaBonTasa = em.merge(cuotaBonTasaCollectionNewCuotaBonTasa);
                    if (oldBonTasaidOfCuotaBonTasaCollectionNewCuotaBonTasa != null && !oldBonTasaidOfCuotaBonTasaCollectionNewCuotaBonTasa.equals(bonTasa)) {
                        oldBonTasaidOfCuotaBonTasaCollectionNewCuotaBonTasa.getCuotaBonTasaCollection().remove(cuotaBonTasaCollectionNewCuotaBonTasa);
                        oldBonTasaidOfCuotaBonTasaCollectionNewCuotaBonTasa = em.merge(oldBonTasaidOfCuotaBonTasaCollectionNewCuotaBonTasa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = bonTasa.getId();
                if (findBonTasa(id) == null) {
                    throw new NonexistentEntityException("The bonTasa with id " + id + " no longer exists.");
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
            BonTasa bonTasa;
            try {
                bonTasa = em.getReference(BonTasa.class, id);
                bonTasa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bonTasa with id " + id + " no longer exists.", enfe);
            }
            Turno turnoId = bonTasa.getTurnoId();
            if (turnoId != null) {
                turnoId.getBonTasaCollection().remove(bonTasa);
                turnoId = em.merge(turnoId);
            }
            Sector sectorId = bonTasa.getSectorId();
            if (sectorId != null) {
                sectorId.getBonTasaCollection().remove(bonTasa);
                sectorId = em.merge(sectorId);
            }
            Collection<BonTasaEstado> bonTasaEstadoCollection = bonTasa.getBonTasaEstadoCollection();
            for (BonTasaEstado bonTasaEstadoCollectionBonTasaEstado : bonTasaEstadoCollection) {
                bonTasaEstadoCollectionBonTasaEstado.setBonTasaid(null);
                bonTasaEstadoCollectionBonTasaEstado = em.merge(bonTasaEstadoCollectionBonTasaEstado);
            }
            Collection<TitularesBonTasa> titularesBonTasaCollection = bonTasa.getTitularesBonTasaCollection();
            for (TitularesBonTasa titularesBonTasaCollectionTitularesBonTasa : titularesBonTasaCollection) {
                titularesBonTasaCollectionTitularesBonTasa.setBonTasaid(null);
                titularesBonTasaCollectionTitularesBonTasa = em.merge(titularesBonTasaCollectionTitularesBonTasa);
            }
            Collection<BonTasaIndice> bonTasaIndiceCollection = bonTasa.getBonTasaIndiceCollection();
            for (BonTasaIndice bonTasaIndiceCollectionBonTasaIndice : bonTasaIndiceCollection) {
                bonTasaIndiceCollectionBonTasaIndice.setBonTasaid(null);
                bonTasaIndiceCollectionBonTasaIndice = em.merge(bonTasaIndiceCollectionBonTasaIndice);
            }
            Collection<CuotaBonTasa> cuotaBonTasaCollection = bonTasa.getCuotaBonTasaCollection();
            for (CuotaBonTasa cuotaBonTasaCollectionCuotaBonTasa : cuotaBonTasaCollection) {
                cuotaBonTasaCollectionCuotaBonTasa.setBonTasaid(null);
                cuotaBonTasaCollectionCuotaBonTasa = em.merge(cuotaBonTasaCollectionCuotaBonTasa);
            }
            em.remove(bonTasa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BonTasa> findBonTasaEntities() {
        return findBonTasaEntities(true, -1, -1);
    }

    public List<BonTasa> findBonTasaEntities(int maxResults, int firstResult) {
        return findBonTasaEntities(false, maxResults, firstResult);
    }

    private List<BonTasa> findBonTasaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from BonTasa as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BonTasa findBonTasa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BonTasa.class, id);
        } finally {
            em.close();
        }
    }

    public int getBonTasaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from BonTasa as o");
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
