/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionAccess.exceptions.NonexistentEntityException;
import ConexionAccess.exceptions.PreexistingEntityException;
import Entidades.*;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author analian
 */
public class LineaJpaController implements Serializable {

    public LineaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Linea linea) throws PreexistingEntityException, Exception {
        if (linea.getRequisitoCollection() == null) {
            linea.setRequisitoCollection(new ArrayList<Requisito>());
        }
        if (linea.getObjetoiCollection() == null) {
            linea.setObjetoiCollection(new ArrayList<Objetoi>());
        }
        if (linea.getTurnoCollection() == null) {
            linea.setTurnoCollection(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sector sectorId = linea.getSectorId();
            if (sectorId != null) {
                sectorId = em.getReference(sectorId.getClass(), sectorId.getId());
                linea.setSectorId(sectorId);
            }
            CConcepto punitorioId = linea.getPunitorioId();
            if (punitorioId != null) {
                punitorioId = em.getReference(punitorioId.getClass(), punitorioId.getConcepto());
                linea.setPunitorioId(punitorioId);
            }
            CConcepto capitalId = linea.getCapitalId();
            if (capitalId != null) {
                capitalId = em.getReference(capitalId.getClass(), capitalId.getConcepto());
                linea.setCapitalId(capitalId);
            }
            CConcepto moratorioId = linea.getMoratorioId();
            if (moratorioId != null) {
                moratorioId = em.getReference(moratorioId.getClass(), moratorioId.getConcepto());
                linea.setMoratorioId(moratorioId);
            }
            CConcepto multaId = linea.getMultaId();
            if (multaId != null) {
                multaId = em.getReference(multaId.getClass(), multaId.getConcepto());
                linea.setMultaId(multaId);
            }
            CConcepto compensatorioId = linea.getCompensatorioId();
            if (compensatorioId != null) {
                compensatorioId = em.getReference(compensatorioId.getClass(), compensatorioId.getConcepto());
                linea.setCompensatorioId(compensatorioId);
            }
            CConcepto gastoId = linea.getGastoId();
            if (gastoId != null) {
                gastoId = em.getReference(gastoId.getClass(), gastoId.getConcepto());
                linea.setGastoId(gastoId);
            }
            Indice indiceCompid = linea.getIndiceCompid();
            if (indiceCompid != null) {
                indiceCompid = em.getReference(indiceCompid.getClass(), indiceCompid.getId());
                linea.setIndiceCompid(indiceCompid);
            }
            Indice indicePunid = linea.getIndicePunid();
            if (indicePunid != null) {
                indicePunid = em.getReference(indicePunid.getClass(), indicePunid.getId());
                linea.setIndicePunid(indicePunid);
            }
            Indice indiceMorid = linea.getIndiceMorid();
            if (indiceMorid != null) {
                indiceMorid = em.getReference(indiceMorid.getClass(), indiceMorid.getId());
                linea.setIndiceMorid(indiceMorid);
            }
            Collection<Requisito> attachedRequisitoCollection = new ArrayList<Requisito>();
            for (Requisito requisitoCollectionRequisitoToAttach : linea.getRequisitoCollection()) {
                requisitoCollectionRequisitoToAttach = em.getReference(requisitoCollectionRequisitoToAttach.getClass(), requisitoCollectionRequisitoToAttach.getId());
                attachedRequisitoCollection.add(requisitoCollectionRequisitoToAttach);
            }
            linea.setRequisitoCollection(attachedRequisitoCollection);
            Collection<Objetoi> attachedObjetoiCollection = new ArrayList<Objetoi>();
            for (Objetoi objetoiCollectionObjetoiToAttach : linea.getObjetoiCollection()) {
                objetoiCollectionObjetoiToAttach = em.getReference(objetoiCollectionObjetoiToAttach.getClass(), objetoiCollectionObjetoiToAttach.getId());
                attachedObjetoiCollection.add(objetoiCollectionObjetoiToAttach);
            }
            linea.setObjetoiCollection(attachedObjetoiCollection);
            Collection<Turno> attachedTurnoCollection = new ArrayList<Turno>();
            for (Turno turnoCollectionTurnoToAttach : linea.getTurnoCollection()) {
                turnoCollectionTurnoToAttach = em.getReference(turnoCollectionTurnoToAttach.getClass(), turnoCollectionTurnoToAttach.getId());
                attachedTurnoCollection.add(turnoCollectionTurnoToAttach);
            }
            linea.setTurnoCollection(attachedTurnoCollection);
            em.persist(linea);
            if (sectorId != null) {
                sectorId.getLineaCollection().add(linea);
                sectorId = em.merge(sectorId);
            }
            if (punitorioId != null) {
                punitorioId.getLineaCollection().add(linea);
                punitorioId = em.merge(punitorioId);
            }
            if (capitalId != null) {
                capitalId.getLineaCollection().add(linea);
                capitalId = em.merge(capitalId);
            }
            if (moratorioId != null) {
                moratorioId.getLineaCollection().add(linea);
                moratorioId = em.merge(moratorioId);
            }
            if (multaId != null) {
                multaId.getLineaCollection().add(linea);
                multaId = em.merge(multaId);
            }
            if (compensatorioId != null) {
                compensatorioId.getLineaCollection().add(linea);
                compensatorioId = em.merge(compensatorioId);
            }
            if (gastoId != null) {
                gastoId.getLineaCollection().add(linea);
                gastoId = em.merge(gastoId);
            }
            if (indiceCompid != null) {
                indiceCompid.getLineaCollection().add(linea);
                indiceCompid = em.merge(indiceCompid);
            }
            if (indicePunid != null) {
                indicePunid.getLineaCollection().add(linea);
                indicePunid = em.merge(indicePunid);
            }
            if (indiceMorid != null) {
                indiceMorid.getLineaCollection().add(linea);
                indiceMorid = em.merge(indiceMorid);
            }
            for (Requisito requisitoCollectionRequisito : linea.getRequisitoCollection()) {
                Linea oldLineaIdOfRequisitoCollectionRequisito = requisitoCollectionRequisito.getLineaId();
                requisitoCollectionRequisito.setLineaId(linea);
                requisitoCollectionRequisito = em.merge(requisitoCollectionRequisito);
                if (oldLineaIdOfRequisitoCollectionRequisito != null) {
                    oldLineaIdOfRequisitoCollectionRequisito.getRequisitoCollection().remove(requisitoCollectionRequisito);
                    oldLineaIdOfRequisitoCollectionRequisito = em.merge(oldLineaIdOfRequisitoCollectionRequisito);
                }
            }
            for (Objetoi objetoiCollectionObjetoi : linea.getObjetoiCollection()) {
                Linea oldLineaIdOfObjetoiCollectionObjetoi = objetoiCollectionObjetoi.getLineaId();
                objetoiCollectionObjetoi.setLineaId(linea);
                objetoiCollectionObjetoi = em.merge(objetoiCollectionObjetoi);
                if (oldLineaIdOfObjetoiCollectionObjetoi != null) {
                    oldLineaIdOfObjetoiCollectionObjetoi.getObjetoiCollection().remove(objetoiCollectionObjetoi);
                    oldLineaIdOfObjetoiCollectionObjetoi = em.merge(oldLineaIdOfObjetoiCollectionObjetoi);
                }
            }
            for (Turno turnoCollectionTurno : linea.getTurnoCollection()) {
                Linea oldLineaIdOfTurnoCollectionTurno = turnoCollectionTurno.getLineaId();
                turnoCollectionTurno.setLineaId(linea);
                turnoCollectionTurno = em.merge(turnoCollectionTurno);
                if (oldLineaIdOfTurnoCollectionTurno != null) {
                    oldLineaIdOfTurnoCollectionTurno.getTurnoCollection().remove(turnoCollectionTurno);
                    oldLineaIdOfTurnoCollectionTurno = em.merge(oldLineaIdOfTurnoCollectionTurno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLinea(linea.getId()) != null) {
                throw new PreexistingEntityException("Linea " + linea + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Linea linea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Linea persistentLinea = em.find(Linea.class, linea.getId());
            Sector sectorIdOld = persistentLinea.getSectorId();
            Sector sectorIdNew = linea.getSectorId();
            CConcepto punitorioIdOld = persistentLinea.getPunitorioId();
            CConcepto punitorioIdNew = linea.getPunitorioId();
            CConcepto capitalIdOld = persistentLinea.getCapitalId();
            CConcepto capitalIdNew = linea.getCapitalId();
            CConcepto moratorioIdOld = persistentLinea.getMoratorioId();
            CConcepto moratorioIdNew = linea.getMoratorioId();
            CConcepto multaIdOld = persistentLinea.getMultaId();
            CConcepto multaIdNew = linea.getMultaId();
            CConcepto compensatorioIdOld = persistentLinea.getCompensatorioId();
            CConcepto compensatorioIdNew = linea.getCompensatorioId();
            CConcepto gastoIdOld = persistentLinea.getGastoId();
            CConcepto gastoIdNew = linea.getGastoId();
            Indice indiceCompidOld = persistentLinea.getIndiceCompid();
            Indice indiceCompidNew = linea.getIndiceCompid();
            Indice indicePunidOld = persistentLinea.getIndicePunid();
            Indice indicePunidNew = linea.getIndicePunid();
            Indice indiceMoridOld = persistentLinea.getIndiceMorid();
            Indice indiceMoridNew = linea.getIndiceMorid();
            Collection<Requisito> requisitoCollectionOld = persistentLinea.getRequisitoCollection();
            Collection<Requisito> requisitoCollectionNew = linea.getRequisitoCollection();
            Collection<Objetoi> objetoiCollectionOld = persistentLinea.getObjetoiCollection();
            Collection<Objetoi> objetoiCollectionNew = linea.getObjetoiCollection();
            Collection<Turno> turnoCollectionOld = persistentLinea.getTurnoCollection();
            Collection<Turno> turnoCollectionNew = linea.getTurnoCollection();
            if (sectorIdNew != null) {
                sectorIdNew = em.getReference(sectorIdNew.getClass(), sectorIdNew.getId());
                linea.setSectorId(sectorIdNew);
            }
            if (punitorioIdNew != null) {
                punitorioIdNew = em.getReference(punitorioIdNew.getClass(), punitorioIdNew.getConcepto());
                linea.setPunitorioId(punitorioIdNew);
            }
            if (capitalIdNew != null) {
                capitalIdNew = em.getReference(capitalIdNew.getClass(), capitalIdNew.getConcepto());
                linea.setCapitalId(capitalIdNew);
            }
            if (moratorioIdNew != null) {
                moratorioIdNew = em.getReference(moratorioIdNew.getClass(), moratorioIdNew.getConcepto());
                linea.setMoratorioId(moratorioIdNew);
            }
            if (multaIdNew != null) {
                multaIdNew = em.getReference(multaIdNew.getClass(), multaIdNew.getConcepto());
                linea.setMultaId(multaIdNew);
            }
            if (compensatorioIdNew != null) {
                compensatorioIdNew = em.getReference(compensatorioIdNew.getClass(), compensatorioIdNew.getConcepto());
                linea.setCompensatorioId(compensatorioIdNew);
            }
            if (gastoIdNew != null) {
                gastoIdNew = em.getReference(gastoIdNew.getClass(), gastoIdNew.getConcepto());
                linea.setGastoId(gastoIdNew);
            }
            if (indiceCompidNew != null) {
                indiceCompidNew = em.getReference(indiceCompidNew.getClass(), indiceCompidNew.getId());
                linea.setIndiceCompid(indiceCompidNew);
            }
            if (indicePunidNew != null) {
                indicePunidNew = em.getReference(indicePunidNew.getClass(), indicePunidNew.getId());
                linea.setIndicePunid(indicePunidNew);
            }
            if (indiceMoridNew != null) {
                indiceMoridNew = em.getReference(indiceMoridNew.getClass(), indiceMoridNew.getId());
                linea.setIndiceMorid(indiceMoridNew);
            }
            Collection<Requisito> attachedRequisitoCollectionNew = new ArrayList<Requisito>();
            for (Requisito requisitoCollectionNewRequisitoToAttach : requisitoCollectionNew) {
                requisitoCollectionNewRequisitoToAttach = em.getReference(requisitoCollectionNewRequisitoToAttach.getClass(), requisitoCollectionNewRequisitoToAttach.getId());
                attachedRequisitoCollectionNew.add(requisitoCollectionNewRequisitoToAttach);
            }
            requisitoCollectionNew = attachedRequisitoCollectionNew;
            linea.setRequisitoCollection(requisitoCollectionNew);
            Collection<Objetoi> attachedObjetoiCollectionNew = new ArrayList<Objetoi>();
            for (Objetoi objetoiCollectionNewObjetoiToAttach : objetoiCollectionNew) {
                objetoiCollectionNewObjetoiToAttach = em.getReference(objetoiCollectionNewObjetoiToAttach.getClass(), objetoiCollectionNewObjetoiToAttach.getId());
                attachedObjetoiCollectionNew.add(objetoiCollectionNewObjetoiToAttach);
            }
            objetoiCollectionNew = attachedObjetoiCollectionNew;
            linea.setObjetoiCollection(objetoiCollectionNew);
            Collection<Turno> attachedTurnoCollectionNew = new ArrayList<Turno>();
            for (Turno turnoCollectionNewTurnoToAttach : turnoCollectionNew) {
                turnoCollectionNewTurnoToAttach = em.getReference(turnoCollectionNewTurnoToAttach.getClass(), turnoCollectionNewTurnoToAttach.getId());
                attachedTurnoCollectionNew.add(turnoCollectionNewTurnoToAttach);
            }
            turnoCollectionNew = attachedTurnoCollectionNew;
            linea.setTurnoCollection(turnoCollectionNew);
            linea = em.merge(linea);
            if (sectorIdOld != null && !sectorIdOld.equals(sectorIdNew)) {
                sectorIdOld.getLineaCollection().remove(linea);
                sectorIdOld = em.merge(sectorIdOld);
            }
            if (sectorIdNew != null && !sectorIdNew.equals(sectorIdOld)) {
                sectorIdNew.getLineaCollection().add(linea);
                sectorIdNew = em.merge(sectorIdNew);
            }
            if (punitorioIdOld != null && !punitorioIdOld.equals(punitorioIdNew)) {
                punitorioIdOld.getLineaCollection().remove(linea);
                punitorioIdOld = em.merge(punitorioIdOld);
            }
            if (punitorioIdNew != null && !punitorioIdNew.equals(punitorioIdOld)) {
                punitorioIdNew.getLineaCollection().add(linea);
                punitorioIdNew = em.merge(punitorioIdNew);
            }
            if (capitalIdOld != null && !capitalIdOld.equals(capitalIdNew)) {
                capitalIdOld.getLineaCollection().remove(linea);
                capitalIdOld = em.merge(capitalIdOld);
            }
            if (capitalIdNew != null && !capitalIdNew.equals(capitalIdOld)) {
                capitalIdNew.getLineaCollection().add(linea);
                capitalIdNew = em.merge(capitalIdNew);
            }
            if (moratorioIdOld != null && !moratorioIdOld.equals(moratorioIdNew)) {
                moratorioIdOld.getLineaCollection().remove(linea);
                moratorioIdOld = em.merge(moratorioIdOld);
            }
            if (moratorioIdNew != null && !moratorioIdNew.equals(moratorioIdOld)) {
                moratorioIdNew.getLineaCollection().add(linea);
                moratorioIdNew = em.merge(moratorioIdNew);
            }
            if (multaIdOld != null && !multaIdOld.equals(multaIdNew)) {
                multaIdOld.getLineaCollection().remove(linea);
                multaIdOld = em.merge(multaIdOld);
            }
            if (multaIdNew != null && !multaIdNew.equals(multaIdOld)) {
                multaIdNew.getLineaCollection().add(linea);
                multaIdNew = em.merge(multaIdNew);
            }
            if (compensatorioIdOld != null && !compensatorioIdOld.equals(compensatorioIdNew)) {
                compensatorioIdOld.getLineaCollection().remove(linea);
                compensatorioIdOld = em.merge(compensatorioIdOld);
            }
            if (compensatorioIdNew != null && !compensatorioIdNew.equals(compensatorioIdOld)) {
                compensatorioIdNew.getLineaCollection().add(linea);
                compensatorioIdNew = em.merge(compensatorioIdNew);
            }
            if (gastoIdOld != null && !gastoIdOld.equals(gastoIdNew)) {
                gastoIdOld.getLineaCollection().remove(linea);
                gastoIdOld = em.merge(gastoIdOld);
            }
            if (gastoIdNew != null && !gastoIdNew.equals(gastoIdOld)) {
                gastoIdNew.getLineaCollection().add(linea);
                gastoIdNew = em.merge(gastoIdNew);
            }
            if (indiceCompidOld != null && !indiceCompidOld.equals(indiceCompidNew)) {
                indiceCompidOld.getLineaCollection().remove(linea);
                indiceCompidOld = em.merge(indiceCompidOld);
            }
            if (indiceCompidNew != null && !indiceCompidNew.equals(indiceCompidOld)) {
                indiceCompidNew.getLineaCollection().add(linea);
                indiceCompidNew = em.merge(indiceCompidNew);
            }
            if (indicePunidOld != null && !indicePunidOld.equals(indicePunidNew)) {
                indicePunidOld.getLineaCollection().remove(linea);
                indicePunidOld = em.merge(indicePunidOld);
            }
            if (indicePunidNew != null && !indicePunidNew.equals(indicePunidOld)) {
                indicePunidNew.getLineaCollection().add(linea);
                indicePunidNew = em.merge(indicePunidNew);
            }
            if (indiceMoridOld != null && !indiceMoridOld.equals(indiceMoridNew)) {
                indiceMoridOld.getLineaCollection().remove(linea);
                indiceMoridOld = em.merge(indiceMoridOld);
            }
            if (indiceMoridNew != null && !indiceMoridNew.equals(indiceMoridOld)) {
                indiceMoridNew.getLineaCollection().add(linea);
                indiceMoridNew = em.merge(indiceMoridNew);
            }
            for (Requisito requisitoCollectionOldRequisito : requisitoCollectionOld) {
                if (!requisitoCollectionNew.contains(requisitoCollectionOldRequisito)) {
                    requisitoCollectionOldRequisito.setLineaId(null);
                    requisitoCollectionOldRequisito = em.merge(requisitoCollectionOldRequisito);
                }
            }
            for (Requisito requisitoCollectionNewRequisito : requisitoCollectionNew) {
                if (!requisitoCollectionOld.contains(requisitoCollectionNewRequisito)) {
                    Linea oldLineaIdOfRequisitoCollectionNewRequisito = requisitoCollectionNewRequisito.getLineaId();
                    requisitoCollectionNewRequisito.setLineaId(linea);
                    requisitoCollectionNewRequisito = em.merge(requisitoCollectionNewRequisito);
                    if (oldLineaIdOfRequisitoCollectionNewRequisito != null && !oldLineaIdOfRequisitoCollectionNewRequisito.equals(linea)) {
                        oldLineaIdOfRequisitoCollectionNewRequisito.getRequisitoCollection().remove(requisitoCollectionNewRequisito);
                        oldLineaIdOfRequisitoCollectionNewRequisito = em.merge(oldLineaIdOfRequisitoCollectionNewRequisito);
                    }
                }
            }
            for (Objetoi objetoiCollectionOldObjetoi : objetoiCollectionOld) {
                if (!objetoiCollectionNew.contains(objetoiCollectionOldObjetoi)) {
                    objetoiCollectionOldObjetoi.setLineaId(null);
                    objetoiCollectionOldObjetoi = em.merge(objetoiCollectionOldObjetoi);
                }
            }
            for (Objetoi objetoiCollectionNewObjetoi : objetoiCollectionNew) {
                if (!objetoiCollectionOld.contains(objetoiCollectionNewObjetoi)) {
                    Linea oldLineaIdOfObjetoiCollectionNewObjetoi = objetoiCollectionNewObjetoi.getLineaId();
                    objetoiCollectionNewObjetoi.setLineaId(linea);
                    objetoiCollectionNewObjetoi = em.merge(objetoiCollectionNewObjetoi);
                    if (oldLineaIdOfObjetoiCollectionNewObjetoi != null && !oldLineaIdOfObjetoiCollectionNewObjetoi.equals(linea)) {
                        oldLineaIdOfObjetoiCollectionNewObjetoi.getObjetoiCollection().remove(objetoiCollectionNewObjetoi);
                        oldLineaIdOfObjetoiCollectionNewObjetoi = em.merge(oldLineaIdOfObjetoiCollectionNewObjetoi);
                    }
                }
            }
            for (Turno turnoCollectionOldTurno : turnoCollectionOld) {
                if (!turnoCollectionNew.contains(turnoCollectionOldTurno)) {
                    turnoCollectionOldTurno.setLineaId(null);
                    turnoCollectionOldTurno = em.merge(turnoCollectionOldTurno);
                }
            }
            for (Turno turnoCollectionNewTurno : turnoCollectionNew) {
                if (!turnoCollectionOld.contains(turnoCollectionNewTurno)) {
                    Linea oldLineaIdOfTurnoCollectionNewTurno = turnoCollectionNewTurno.getLineaId();
                    turnoCollectionNewTurno.setLineaId(linea);
                    turnoCollectionNewTurno = em.merge(turnoCollectionNewTurno);
                    if (oldLineaIdOfTurnoCollectionNewTurno != null && !oldLineaIdOfTurnoCollectionNewTurno.equals(linea)) {
                        oldLineaIdOfTurnoCollectionNewTurno.getTurnoCollection().remove(turnoCollectionNewTurno);
                        oldLineaIdOfTurnoCollectionNewTurno = em.merge(oldLineaIdOfTurnoCollectionNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = linea.getId();
                if (findLinea(id) == null) {
                    throw new NonexistentEntityException("The linea with id " + id + " no longer exists.");
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
            Linea linea;
            try {
                linea = em.getReference(Linea.class, id);
                linea.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The linea with id " + id + " no longer exists.", enfe);
            }
            Sector sectorId = linea.getSectorId();
            if (sectorId != null) {
                sectorId.getLineaCollection().remove(linea);
                sectorId = em.merge(sectorId);
            }
            CConcepto punitorioId = linea.getPunitorioId();
            if (punitorioId != null) {
                punitorioId.getLineaCollection().remove(linea);
                punitorioId = em.merge(punitorioId);
            }
            CConcepto capitalId = linea.getCapitalId();
            if (capitalId != null) {
                capitalId.getLineaCollection().remove(linea);
                capitalId = em.merge(capitalId);
            }
            CConcepto moratorioId = linea.getMoratorioId();
            if (moratorioId != null) {
                moratorioId.getLineaCollection().remove(linea);
                moratorioId = em.merge(moratorioId);
            }
            CConcepto multaId = linea.getMultaId();
            if (multaId != null) {
                multaId.getLineaCollection().remove(linea);
                multaId = em.merge(multaId);
            }
            CConcepto compensatorioId = linea.getCompensatorioId();
            if (compensatorioId != null) {
                compensatorioId.getLineaCollection().remove(linea);
                compensatorioId = em.merge(compensatorioId);
            }
            CConcepto gastoId = linea.getGastoId();
            if (gastoId != null) {
                gastoId.getLineaCollection().remove(linea);
                gastoId = em.merge(gastoId);
            }
            Indice indiceCompid = linea.getIndiceCompid();
            if (indiceCompid != null) {
                indiceCompid.getLineaCollection().remove(linea);
                indiceCompid = em.merge(indiceCompid);
            }
            Indice indicePunid = linea.getIndicePunid();
            if (indicePunid != null) {
                indicePunid.getLineaCollection().remove(linea);
                indicePunid = em.merge(indicePunid);
            }
            Indice indiceMorid = linea.getIndiceMorid();
            if (indiceMorid != null) {
                indiceMorid.getLineaCollection().remove(linea);
                indiceMorid = em.merge(indiceMorid);
            }
            Collection<Requisito> requisitoCollection = linea.getRequisitoCollection();
            for (Requisito requisitoCollectionRequisito : requisitoCollection) {
                requisitoCollectionRequisito.setLineaId(null);
                requisitoCollectionRequisito = em.merge(requisitoCollectionRequisito);
            }
            Collection<Objetoi> objetoiCollection = linea.getObjetoiCollection();
            for (Objetoi objetoiCollectionObjetoi : objetoiCollection) {
                objetoiCollectionObjetoi.setLineaId(null);
                objetoiCollectionObjetoi = em.merge(objetoiCollectionObjetoi);
            }
            Collection<Turno> turnoCollection = linea.getTurnoCollection();
            for (Turno turnoCollectionTurno : turnoCollection) {
                turnoCollectionTurno.setLineaId(null);
                turnoCollectionTurno = em.merge(turnoCollectionTurno);
            }
            em.remove(linea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Linea> findLineaEntities() {
        return findLineaEntities(true, -1, -1);
    }

    public List<Linea> findLineaEntities(int maxResults, int firstResult) {
        return findLineaEntities(false, maxResults, firstResult);
    }

    private List<Linea> findLineaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Linea as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Linea findLinea(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Linea.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Linea as o");
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

            Connection conn = DriverManager.getConnection(url, "admin", "1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");
            conn.createStatement().execute(string);
            conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF");

        } finally {
            //System.out.println(string);
            //em.close();
        }
    }
}
