/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.IllegalOrphanException;
import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Turno;
import Entidades.SubTipoLinea;
import Entidades.Objetoi;
import Entidades.Linea;
import Entidades.JbpmProcessinstance;
import Entidades.ObjetoiBonificacion;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.Desembolso;
import Entidades.ObjetoiIndice;
import Entidades.DomicilioObjetoi;
import Entidades.ObjetoiComportamiento;
import Entidades.ObjetoiEstado;
import Entidades.Boleto;
import Entidades.Emideta;
import Entidades.Certificado;
import Entidades.Ctacte;
import Entidades.Vinedo;
import Entidades.ObjetoiEmergencia;
import Entidades.Cuota;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author analian
 */
public class ObjetoiJpaController implements Serializable {

    public ObjetoiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetoi objetoi) throws PreexistingEntityException, Exception {
        if (objetoi.getObjetoiBonificacionCollection() == null) {
            objetoi.setObjetoiBonificacionCollection(new ArrayList<ObjetoiBonificacion>());
        }
        if (objetoi.getObjetoiCollection() == null) {
            objetoi.setObjetoiCollection(new ArrayList<Objetoi>());
        }
        if (objetoi.getDesembolsoCollection() == null) {
            objetoi.setDesembolsoCollection(new ArrayList<Desembolso>());
        }
        if (objetoi.getObjetoiIndiceCollection() == null) {
            objetoi.setObjetoiIndiceCollection(new ArrayList<ObjetoiIndice>());
        }
        if (objetoi.getDomicilioObjetoiCollection() == null) {
            objetoi.setDomicilioObjetoiCollection(new ArrayList<DomicilioObjetoi>());
        }
        if (objetoi.getObjetoiComportamientoCollection() == null) {
            objetoi.setObjetoiComportamientoCollection(new ArrayList<ObjetoiComportamiento>());
        }
        if (objetoi.getObjetoiEstadoCollection() == null) {
            objetoi.setObjetoiEstadoCollection(new ArrayList<ObjetoiEstado>());
        }
        if (objetoi.getBoletoCollection() == null) {
            objetoi.setBoletoCollection(new ArrayList<Boleto>());
        }
        if (objetoi.getEmidetaCollection() == null) {
            objetoi.setEmidetaCollection(new ArrayList<Emideta>());
        }
        if (objetoi.getCertificadoCollection() == null) {
            objetoi.setCertificadoCollection(new ArrayList<Certificado>());
        }
        if (objetoi.getCtacteCollection() == null) {
            objetoi.setCtacteCollection(new ArrayList<Ctacte>());
        }
        if (objetoi.getVinedoCollection() == null) {
            objetoi.setVinedoCollection(new ArrayList<Vinedo>());
        }
        if (objetoi.getObjetoiEmergenciaCollection() == null) {
            objetoi.setObjetoiEmergenciaCollection(new ArrayList<ObjetoiEmergencia>());
        }
        if (objetoi.getCuotaCollection() == null) {
            objetoi.setCuotaCollection(new ArrayList<Cuota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turnoId = objetoi.getTurnoId();
            if (turnoId != null) {
                turnoId = em.getReference(turnoId.getClass(), turnoId.getId());
                objetoi.setTurnoId(turnoId);
            }
            SubTipoLinea subTipoLineaid = objetoi.getSubTipoLineaid();
            if (subTipoLineaid != null) {
                subTipoLineaid = em.getReference(subTipoLineaid.getClass(), subTipoLineaid.getId());
                objetoi.setSubTipoLineaid(subTipoLineaid);
            }
            Objetoi acuerdoPagoid = objetoi.getAcuerdoPagoid();
            if (acuerdoPagoid != null) {
                acuerdoPagoid = em.getReference(acuerdoPagoid.getClass(), acuerdoPagoid.getId());
                objetoi.setAcuerdoPagoid(acuerdoPagoid);
            }
            Linea lineaId = objetoi.getLineaId();
            if (lineaId != null) {
                lineaId = em.getReference(lineaId.getClass(), lineaId.getId());
                objetoi.setLineaId(lineaId);
            }
            JbpmProcessinstance procesoAprobacionID = objetoi.getProcesoAprobacionID();
            if (procesoAprobacionID != null) {
                procesoAprobacionID = em.getReference(procesoAprobacionID.getClass(), procesoAprobacionID.getId());
                objetoi.setProcesoAprobacionID(procesoAprobacionID);
            }
            Collection<ObjetoiBonificacion> attachedObjetoiBonificacionCollection = new ArrayList<ObjetoiBonificacion>();
            for (ObjetoiBonificacion objetoiBonificacionCollectionObjetoiBonificacionToAttach : objetoi.getObjetoiBonificacionCollection()) {
                objetoiBonificacionCollectionObjetoiBonificacionToAttach = em.getReference(objetoiBonificacionCollectionObjetoiBonificacionToAttach.getClass(), objetoiBonificacionCollectionObjetoiBonificacionToAttach.getId());
                attachedObjetoiBonificacionCollection.add(objetoiBonificacionCollectionObjetoiBonificacionToAttach);
            }
            objetoi.setObjetoiBonificacionCollection(attachedObjetoiBonificacionCollection);
            Collection<Objetoi> attachedObjetoiCollection = new ArrayList<Objetoi>();
            for (Objetoi objetoiCollectionObjetoiToAttach : objetoi.getObjetoiCollection()) {
                objetoiCollectionObjetoiToAttach = em.getReference(objetoiCollectionObjetoiToAttach.getClass(), objetoiCollectionObjetoiToAttach.getId());
                attachedObjetoiCollection.add(objetoiCollectionObjetoiToAttach);
            }
            objetoi.setObjetoiCollection(attachedObjetoiCollection);
            Collection<Desembolso> attachedDesembolsoCollection = new ArrayList<Desembolso>();
            for (Desembolso desembolsoCollectionDesembolsoToAttach : objetoi.getDesembolsoCollection()) {
                desembolsoCollectionDesembolsoToAttach = em.getReference(desembolsoCollectionDesembolsoToAttach.getClass(), desembolsoCollectionDesembolsoToAttach.getId());
                attachedDesembolsoCollection.add(desembolsoCollectionDesembolsoToAttach);
            }
            objetoi.setDesembolsoCollection(attachedDesembolsoCollection);
            Collection<ObjetoiIndice> attachedObjetoiIndiceCollection = new ArrayList<ObjetoiIndice>();
            for (ObjetoiIndice objetoiIndiceCollectionObjetoiIndiceToAttach : objetoi.getObjetoiIndiceCollection()) {
                objetoiIndiceCollectionObjetoiIndiceToAttach = em.getReference(objetoiIndiceCollectionObjetoiIndiceToAttach.getClass(), objetoiIndiceCollectionObjetoiIndiceToAttach.getId());
                attachedObjetoiIndiceCollection.add(objetoiIndiceCollectionObjetoiIndiceToAttach);
            }
            objetoi.setObjetoiIndiceCollection(attachedObjetoiIndiceCollection);
            Collection<DomicilioObjetoi> attachedDomicilioObjetoiCollection = new ArrayList<DomicilioObjetoi>();
            for (DomicilioObjetoi domicilioObjetoiCollectionDomicilioObjetoiToAttach : objetoi.getDomicilioObjetoiCollection()) {
                domicilioObjetoiCollectionDomicilioObjetoiToAttach = em.getReference(domicilioObjetoiCollectionDomicilioObjetoiToAttach.getClass(), domicilioObjetoiCollectionDomicilioObjetoiToAttach.getId());
                attachedDomicilioObjetoiCollection.add(domicilioObjetoiCollectionDomicilioObjetoiToAttach);
            }
            objetoi.setDomicilioObjetoiCollection(attachedDomicilioObjetoiCollection);
            Collection<ObjetoiComportamiento> attachedObjetoiComportamientoCollection = new ArrayList<ObjetoiComportamiento>();
            for (ObjetoiComportamiento objetoiComportamientoCollectionObjetoiComportamientoToAttach : objetoi.getObjetoiComportamientoCollection()) {
                objetoiComportamientoCollectionObjetoiComportamientoToAttach = em.getReference(objetoiComportamientoCollectionObjetoiComportamientoToAttach.getClass(), objetoiComportamientoCollectionObjetoiComportamientoToAttach.getId());
                attachedObjetoiComportamientoCollection.add(objetoiComportamientoCollectionObjetoiComportamientoToAttach);
            }
            objetoi.setObjetoiComportamientoCollection(attachedObjetoiComportamientoCollection);
            Collection<ObjetoiEstado> attachedObjetoiEstadoCollection = new ArrayList<ObjetoiEstado>();
            for (ObjetoiEstado objetoiEstadoCollectionObjetoiEstadoToAttach : objetoi.getObjetoiEstadoCollection()) {
                objetoiEstadoCollectionObjetoiEstadoToAttach = em.getReference(objetoiEstadoCollectionObjetoiEstadoToAttach.getClass(), objetoiEstadoCollectionObjetoiEstadoToAttach.getId());
                attachedObjetoiEstadoCollection.add(objetoiEstadoCollectionObjetoiEstadoToAttach);
            }
            objetoi.setObjetoiEstadoCollection(attachedObjetoiEstadoCollection);
            Collection<Boleto> attachedBoletoCollection = new ArrayList<Boleto>();
            for (Boleto boletoCollectionBoletoToAttach : objetoi.getBoletoCollection()) {
                boletoCollectionBoletoToAttach = em.getReference(boletoCollectionBoletoToAttach.getClass(), boletoCollectionBoletoToAttach.getBoletoPK());
                attachedBoletoCollection.add(boletoCollectionBoletoToAttach);
            }
            objetoi.setBoletoCollection(attachedBoletoCollection);
            Collection<Emideta> attachedEmidetaCollection = new ArrayList<Emideta>();
            for (Emideta emidetaCollectionEmidetaToAttach : objetoi.getEmidetaCollection()) {
                emidetaCollectionEmidetaToAttach = em.getReference(emidetaCollectionEmidetaToAttach.getClass(), emidetaCollectionEmidetaToAttach.getId());
                attachedEmidetaCollection.add(emidetaCollectionEmidetaToAttach);
            }
            objetoi.setEmidetaCollection(attachedEmidetaCollection);
            Collection<Certificado> attachedCertificadoCollection = new ArrayList<Certificado>();
            for (Certificado certificadoCollectionCertificadoToAttach : objetoi.getCertificadoCollection()) {
                certificadoCollectionCertificadoToAttach = em.getReference(certificadoCollectionCertificadoToAttach.getClass(), certificadoCollectionCertificadoToAttach.getId());
                attachedCertificadoCollection.add(certificadoCollectionCertificadoToAttach);
            }
            objetoi.setCertificadoCollection(attachedCertificadoCollection);
            Collection<Ctacte> attachedCtacteCollection = new ArrayList<Ctacte>();
            for (Ctacte ctacteCollectionCtacteToAttach : objetoi.getCtacteCollection()) {
                ctacteCollectionCtacteToAttach = em.getReference(ctacteCollectionCtacteToAttach.getClass(), ctacteCollectionCtacteToAttach.getCtactePK());
                attachedCtacteCollection.add(ctacteCollectionCtacteToAttach);
            }
            objetoi.setCtacteCollection(attachedCtacteCollection);
            Collection<Vinedo> attachedVinedoCollection = new ArrayList<Vinedo>();
            for (Vinedo vinedoCollectionVinedoToAttach : objetoi.getVinedoCollection()) {
                vinedoCollectionVinedoToAttach = em.getReference(vinedoCollectionVinedoToAttach.getClass(), vinedoCollectionVinedoToAttach.getId());
                attachedVinedoCollection.add(vinedoCollectionVinedoToAttach);
            }
            objetoi.setVinedoCollection(attachedVinedoCollection);
            Collection<ObjetoiEmergencia> attachedObjetoiEmergenciaCollection = new ArrayList<ObjetoiEmergencia>();
            for (ObjetoiEmergencia objetoiEmergenciaCollectionObjetoiEmergenciaToAttach : objetoi.getObjetoiEmergenciaCollection()) {
                objetoiEmergenciaCollectionObjetoiEmergenciaToAttach = em.getReference(objetoiEmergenciaCollectionObjetoiEmergenciaToAttach.getClass(), objetoiEmergenciaCollectionObjetoiEmergenciaToAttach.getId());
                attachedObjetoiEmergenciaCollection.add(objetoiEmergenciaCollectionObjetoiEmergenciaToAttach);
            }
            objetoi.setObjetoiEmergenciaCollection(attachedObjetoiEmergenciaCollection);
            Collection<Cuota> attachedCuotaCollection = new ArrayList<Cuota>();
            for (Cuota cuotaCollectionCuotaToAttach : objetoi.getCuotaCollection()) {
                cuotaCollectionCuotaToAttach = em.getReference(cuotaCollectionCuotaToAttach.getClass(), cuotaCollectionCuotaToAttach.getId());
                attachedCuotaCollection.add(cuotaCollectionCuotaToAttach);
            }
            objetoi.setCuotaCollection(attachedCuotaCollection);
            em.persist(objetoi);
            if (turnoId != null) {
                turnoId.getObjetoiCollection().add(objetoi);
                turnoId = em.merge(turnoId);
            }
            if (subTipoLineaid != null) {
                subTipoLineaid.getObjetoiCollection().add(objetoi);
                subTipoLineaid = em.merge(subTipoLineaid);
            }
            if (acuerdoPagoid != null) {
                acuerdoPagoid.getObjetoiCollection().add(objetoi);
                acuerdoPagoid = em.merge(acuerdoPagoid);
            }
            if (lineaId != null) {
                lineaId.getObjetoiCollection().add(objetoi);
                lineaId = em.merge(lineaId);
            }
            if (procesoAprobacionID != null) {
                procesoAprobacionID.getObjetoiCollection().add(objetoi);
                procesoAprobacionID = em.merge(procesoAprobacionID);
            }
            for (ObjetoiBonificacion objetoiBonificacionCollectionObjetoiBonificacion : objetoi.getObjetoiBonificacionCollection()) {
                Objetoi oldIdCreditoOfObjetoiBonificacionCollectionObjetoiBonificacion = objetoiBonificacionCollectionObjetoiBonificacion.getIdCredito();
                objetoiBonificacionCollectionObjetoiBonificacion.setIdCredito(objetoi);
                objetoiBonificacionCollectionObjetoiBonificacion = em.merge(objetoiBonificacionCollectionObjetoiBonificacion);
                if (oldIdCreditoOfObjetoiBonificacionCollectionObjetoiBonificacion != null) {
                    oldIdCreditoOfObjetoiBonificacionCollectionObjetoiBonificacion.getObjetoiBonificacionCollection().remove(objetoiBonificacionCollectionObjetoiBonificacion);
                    oldIdCreditoOfObjetoiBonificacionCollectionObjetoiBonificacion = em.merge(oldIdCreditoOfObjetoiBonificacionCollectionObjetoiBonificacion);
                }
            }
            for (Objetoi objetoiCollectionObjetoi : objetoi.getObjetoiCollection()) {
                Objetoi oldAcuerdoPagoidOfObjetoiCollectionObjetoi = objetoiCollectionObjetoi.getAcuerdoPagoid();
                objetoiCollectionObjetoi.setAcuerdoPagoid(objetoi);
                objetoiCollectionObjetoi = em.merge(objetoiCollectionObjetoi);
                if (oldAcuerdoPagoidOfObjetoiCollectionObjetoi != null) {
                    oldAcuerdoPagoidOfObjetoiCollectionObjetoi.getObjetoiCollection().remove(objetoiCollectionObjetoi);
                    oldAcuerdoPagoidOfObjetoiCollectionObjetoi = em.merge(oldAcuerdoPagoidOfObjetoiCollectionObjetoi);
                }
            }
            for (Desembolso desembolsoCollectionDesembolso : objetoi.getDesembolsoCollection()) {
                Objetoi oldCreditoIdOfDesembolsoCollectionDesembolso = desembolsoCollectionDesembolso.getCreditoId();
                desembolsoCollectionDesembolso.setCreditoId(objetoi);
                desembolsoCollectionDesembolso = em.merge(desembolsoCollectionDesembolso);
                if (oldCreditoIdOfDesembolsoCollectionDesembolso != null) {
                    oldCreditoIdOfDesembolsoCollectionDesembolso.getDesembolsoCollection().remove(desembolsoCollectionDesembolso);
                    oldCreditoIdOfDesembolsoCollectionDesembolso = em.merge(oldCreditoIdOfDesembolsoCollectionDesembolso);
                }
            }
            for (ObjetoiIndice objetoiIndiceCollectionObjetoiIndice : objetoi.getObjetoiIndiceCollection()) {
                Objetoi oldCreditoIdOfObjetoiIndiceCollectionObjetoiIndice = objetoiIndiceCollectionObjetoiIndice.getCreditoId();
                objetoiIndiceCollectionObjetoiIndice.setCreditoId(objetoi);
                objetoiIndiceCollectionObjetoiIndice = em.merge(objetoiIndiceCollectionObjetoiIndice);
                if (oldCreditoIdOfObjetoiIndiceCollectionObjetoiIndice != null) {
                    oldCreditoIdOfObjetoiIndiceCollectionObjetoiIndice.getObjetoiIndiceCollection().remove(objetoiIndiceCollectionObjetoiIndice);
                    oldCreditoIdOfObjetoiIndiceCollectionObjetoiIndice = em.merge(oldCreditoIdOfObjetoiIndiceCollectionObjetoiIndice);
                }
            }
            for (DomicilioObjetoi domicilioObjetoiCollectionDomicilioObjetoi : objetoi.getDomicilioObjetoiCollection()) {
                Objetoi oldObjetoiIdOfDomicilioObjetoiCollectionDomicilioObjetoi = domicilioObjetoiCollectionDomicilioObjetoi.getObjetoiId();
                domicilioObjetoiCollectionDomicilioObjetoi.setObjetoiId(objetoi);
                domicilioObjetoiCollectionDomicilioObjetoi = em.merge(domicilioObjetoiCollectionDomicilioObjetoi);
                if (oldObjetoiIdOfDomicilioObjetoiCollectionDomicilioObjetoi != null) {
                    oldObjetoiIdOfDomicilioObjetoiCollectionDomicilioObjetoi.getDomicilioObjetoiCollection().remove(domicilioObjetoiCollectionDomicilioObjetoi);
                    oldObjetoiIdOfDomicilioObjetoiCollectionDomicilioObjetoi = em.merge(oldObjetoiIdOfDomicilioObjetoiCollectionDomicilioObjetoi);
                }
            }
            for (ObjetoiComportamiento objetoiComportamientoCollectionObjetoiComportamiento : objetoi.getObjetoiComportamientoCollection()) {
                Objetoi oldObjetoiIdOfObjetoiComportamientoCollectionObjetoiComportamiento = objetoiComportamientoCollectionObjetoiComportamiento.getObjetoiId();
                objetoiComportamientoCollectionObjetoiComportamiento.setObjetoiId(objetoi);
                objetoiComportamientoCollectionObjetoiComportamiento = em.merge(objetoiComportamientoCollectionObjetoiComportamiento);
                if (oldObjetoiIdOfObjetoiComportamientoCollectionObjetoiComportamiento != null) {
                    oldObjetoiIdOfObjetoiComportamientoCollectionObjetoiComportamiento.getObjetoiComportamientoCollection().remove(objetoiComportamientoCollectionObjetoiComportamiento);
                    oldObjetoiIdOfObjetoiComportamientoCollectionObjetoiComportamiento = em.merge(oldObjetoiIdOfObjetoiComportamientoCollectionObjetoiComportamiento);
                }
            }
            for (ObjetoiEstado objetoiEstadoCollectionObjetoiEstado : objetoi.getObjetoiEstadoCollection()) {
                Objetoi oldObjetoiIdOfObjetoiEstadoCollectionObjetoiEstado = objetoiEstadoCollectionObjetoiEstado.getObjetoiId();
                objetoiEstadoCollectionObjetoiEstado.setObjetoiId(objetoi);
                objetoiEstadoCollectionObjetoiEstado = em.merge(objetoiEstadoCollectionObjetoiEstado);
                if (oldObjetoiIdOfObjetoiEstadoCollectionObjetoiEstado != null) {
                    oldObjetoiIdOfObjetoiEstadoCollectionObjetoiEstado.getObjetoiEstadoCollection().remove(objetoiEstadoCollectionObjetoiEstado);
                    oldObjetoiIdOfObjetoiEstadoCollectionObjetoiEstado = em.merge(oldObjetoiIdOfObjetoiEstadoCollectionObjetoiEstado);
                }
            }
            for (Boleto boletoCollectionBoleto : objetoi.getBoletoCollection()) {
                Objetoi oldObjetoiIdOfBoletoCollectionBoleto = boletoCollectionBoleto.getObjetoiId();
                boletoCollectionBoleto.setObjetoiId(objetoi);
                boletoCollectionBoleto = em.merge(boletoCollectionBoleto);
                if (oldObjetoiIdOfBoletoCollectionBoleto != null) {
                    oldObjetoiIdOfBoletoCollectionBoleto.getBoletoCollection().remove(boletoCollectionBoleto);
                    oldObjetoiIdOfBoletoCollectionBoleto = em.merge(oldObjetoiIdOfBoletoCollectionBoleto);
                }
            }
            for (Emideta emidetaCollectionEmideta : objetoi.getEmidetaCollection()) {
                Objetoi oldCreditoIdOfEmidetaCollectionEmideta = emidetaCollectionEmideta.getCreditoId();
                emidetaCollectionEmideta.setCreditoId(objetoi);
                emidetaCollectionEmideta = em.merge(emidetaCollectionEmideta);
                if (oldCreditoIdOfEmidetaCollectionEmideta != null) {
                    oldCreditoIdOfEmidetaCollectionEmideta.getEmidetaCollection().remove(emidetaCollectionEmideta);
                    oldCreditoIdOfEmidetaCollectionEmideta = em.merge(oldCreditoIdOfEmidetaCollectionEmideta);
                }
            }
            for (Certificado certificadoCollectionCertificado : objetoi.getCertificadoCollection()) {
                Objetoi oldCreditoIdOfCertificadoCollectionCertificado = certificadoCollectionCertificado.getCreditoId();
                certificadoCollectionCertificado.setCreditoId(objetoi);
                certificadoCollectionCertificado = em.merge(certificadoCollectionCertificado);
                if (oldCreditoIdOfCertificadoCollectionCertificado != null) {
                    oldCreditoIdOfCertificadoCollectionCertificado.getCertificadoCollection().remove(certificadoCollectionCertificado);
                    oldCreditoIdOfCertificadoCollectionCertificado = em.merge(oldCreditoIdOfCertificadoCollectionCertificado);
                }
            }
            for (Ctacte ctacteCollectionCtacte : objetoi.getCtacteCollection()) {
                Objetoi oldObjetoiOfCtacteCollectionCtacte = ctacteCollectionCtacte.getObjetoi();
                ctacteCollectionCtacte.setObjetoi(objetoi);
                ctacteCollectionCtacte = em.merge(ctacteCollectionCtacte);
                if (oldObjetoiOfCtacteCollectionCtacte != null) {
                    oldObjetoiOfCtacteCollectionCtacte.getCtacteCollection().remove(ctacteCollectionCtacte);
                    oldObjetoiOfCtacteCollectionCtacte = em.merge(oldObjetoiOfCtacteCollectionCtacte);
                }
            }
            for (Vinedo vinedoCollectionVinedo : objetoi.getVinedoCollection()) {
                Objetoi oldCreditoIdOfVinedoCollectionVinedo = vinedoCollectionVinedo.getCreditoId();
                vinedoCollectionVinedo.setCreditoId(objetoi);
                vinedoCollectionVinedo = em.merge(vinedoCollectionVinedo);
                if (oldCreditoIdOfVinedoCollectionVinedo != null) {
                    oldCreditoIdOfVinedoCollectionVinedo.getVinedoCollection().remove(vinedoCollectionVinedo);
                    oldCreditoIdOfVinedoCollectionVinedo = em.merge(oldCreditoIdOfVinedoCollectionVinedo);
                }
            }
            for (ObjetoiEmergencia objetoiEmergenciaCollectionObjetoiEmergencia : objetoi.getObjetoiEmergenciaCollection()) {
                Objetoi oldCreditoIdOfObjetoiEmergenciaCollectionObjetoiEmergencia = objetoiEmergenciaCollectionObjetoiEmergencia.getCreditoId();
                objetoiEmergenciaCollectionObjetoiEmergencia.setCreditoId(objetoi);
                objetoiEmergenciaCollectionObjetoiEmergencia = em.merge(objetoiEmergenciaCollectionObjetoiEmergencia);
                if (oldCreditoIdOfObjetoiEmergenciaCollectionObjetoiEmergencia != null) {
                    oldCreditoIdOfObjetoiEmergenciaCollectionObjetoiEmergencia.getObjetoiEmergenciaCollection().remove(objetoiEmergenciaCollectionObjetoiEmergencia);
                    oldCreditoIdOfObjetoiEmergenciaCollectionObjetoiEmergencia = em.merge(oldCreditoIdOfObjetoiEmergenciaCollectionObjetoiEmergencia);
                }
            }
            for (Cuota cuotaCollectionCuota : objetoi.getCuotaCollection()) {
                Objetoi oldCreditoIdOfCuotaCollectionCuota = cuotaCollectionCuota.getCreditoId();
                cuotaCollectionCuota.setCreditoId(objetoi);
                cuotaCollectionCuota = em.merge(cuotaCollectionCuota);
                if (oldCreditoIdOfCuotaCollectionCuota != null) {
                    oldCreditoIdOfCuotaCollectionCuota.getCuotaCollection().remove(cuotaCollectionCuota);
                    oldCreditoIdOfCuotaCollectionCuota = em.merge(oldCreditoIdOfCuotaCollectionCuota);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObjetoi(objetoi.getId()) != null) {
                throw new PreexistingEntityException("Objetoi " + objetoi + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void edit(Objetoi objetoi) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi persistentObjetoi = em.find(Objetoi.class, objetoi.getId());
            Turno turnoIdOld = persistentObjetoi.getTurnoId();
            Turno turnoIdNew = objetoi.getTurnoId();
            SubTipoLinea subTipoLineaidOld = persistentObjetoi.getSubTipoLineaid();
            SubTipoLinea subTipoLineaidNew = objetoi.getSubTipoLineaid();
            Objetoi acuerdoPagoidOld = persistentObjetoi.getAcuerdoPagoid();
            Objetoi acuerdoPagoidNew = objetoi.getAcuerdoPagoid();
            Linea lineaIdOld = persistentObjetoi.getLineaId();
            Linea lineaIdNew = objetoi.getLineaId();
            JbpmProcessinstance procesoAprobacionIDOld = persistentObjetoi.getProcesoAprobacionID();
            JbpmProcessinstance procesoAprobacionIDNew = objetoi.getProcesoAprobacionID();
            Collection<ObjetoiBonificacion> objetoiBonificacionCollectionOld = persistentObjetoi.getObjetoiBonificacionCollection();
            Collection<ObjetoiBonificacion> objetoiBonificacionCollectionNew = objetoi.getObjetoiBonificacionCollection();
            Collection<Objetoi> objetoiCollectionOld = persistentObjetoi.getObjetoiCollection();
            Collection<Objetoi> objetoiCollectionNew = objetoi.getObjetoiCollection();
            Collection<Desembolso> desembolsoCollectionOld = persistentObjetoi.getDesembolsoCollection();
            Collection<Desembolso> desembolsoCollectionNew = objetoi.getDesembolsoCollection();
            Collection<ObjetoiIndice> objetoiIndiceCollectionOld = persistentObjetoi.getObjetoiIndiceCollection();
            Collection<ObjetoiIndice> objetoiIndiceCollectionNew = objetoi.getObjetoiIndiceCollection();
            Collection<DomicilioObjetoi> domicilioObjetoiCollectionOld = persistentObjetoi.getDomicilioObjetoiCollection();
            Collection<DomicilioObjetoi> domicilioObjetoiCollectionNew = objetoi.getDomicilioObjetoiCollection();
            Collection<ObjetoiComportamiento> objetoiComportamientoCollectionOld = persistentObjetoi.getObjetoiComportamientoCollection();
            Collection<ObjetoiComportamiento> objetoiComportamientoCollectionNew = objetoi.getObjetoiComportamientoCollection();
            Collection<ObjetoiEstado> objetoiEstadoCollectionOld = persistentObjetoi.getObjetoiEstadoCollection();
            Collection<ObjetoiEstado> objetoiEstadoCollectionNew = objetoi.getObjetoiEstadoCollection();
            Collection<Boleto> boletoCollectionOld = persistentObjetoi.getBoletoCollection();
            Collection<Boleto> boletoCollectionNew = objetoi.getBoletoCollection();
            Collection<Emideta> emidetaCollectionOld = persistentObjetoi.getEmidetaCollection();
            Collection<Emideta> emidetaCollectionNew = objetoi.getEmidetaCollection();
            Collection<Certificado> certificadoCollectionOld = persistentObjetoi.getCertificadoCollection();
            Collection<Certificado> certificadoCollectionNew = objetoi.getCertificadoCollection();
            Collection<Ctacte> ctacteCollectionOld = persistentObjetoi.getCtacteCollection();
            Collection<Ctacte> ctacteCollectionNew = objetoi.getCtacteCollection();
            Collection<Vinedo> vinedoCollectionOld = persistentObjetoi.getVinedoCollection();
            Collection<Vinedo> vinedoCollectionNew = objetoi.getVinedoCollection();
            Collection<ObjetoiEmergencia> objetoiEmergenciaCollectionOld = persistentObjetoi.getObjetoiEmergenciaCollection();
            Collection<ObjetoiEmergencia> objetoiEmergenciaCollectionNew = objetoi.getObjetoiEmergenciaCollection();
            Collection<Cuota> cuotaCollectionOld = persistentObjetoi.getCuotaCollection();
            Collection<Cuota> cuotaCollectionNew = objetoi.getCuotaCollection();
            List<String> illegalOrphanMessages = null;
            for (Ctacte ctacteCollectionOldCtacte : ctacteCollectionOld) {
                if (!ctacteCollectionNew.contains(ctacteCollectionOldCtacte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ctacte " + ctacteCollectionOldCtacte + " since its objetoi field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (turnoIdNew != null) {
                turnoIdNew = em.getReference(turnoIdNew.getClass(), turnoIdNew.getId());
                objetoi.setTurnoId(turnoIdNew);
            }
            if (subTipoLineaidNew != null) {
                subTipoLineaidNew = em.getReference(subTipoLineaidNew.getClass(), subTipoLineaidNew.getId());
                objetoi.setSubTipoLineaid(subTipoLineaidNew);
            }
            if (acuerdoPagoidNew != null) {
                acuerdoPagoidNew = em.getReference(acuerdoPagoidNew.getClass(), acuerdoPagoidNew.getId());
                objetoi.setAcuerdoPagoid(acuerdoPagoidNew);
            }
            if (lineaIdNew != null) {
                lineaIdNew = em.getReference(lineaIdNew.getClass(), lineaIdNew.getId());
                objetoi.setLineaId(lineaIdNew);
            }
            if (procesoAprobacionIDNew != null) {
                procesoAprobacionIDNew = em.getReference(procesoAprobacionIDNew.getClass(), procesoAprobacionIDNew.getId());
                objetoi.setProcesoAprobacionID(procesoAprobacionIDNew);
            }
            Collection<ObjetoiBonificacion> attachedObjetoiBonificacionCollectionNew = new ArrayList<ObjetoiBonificacion>();
            for (ObjetoiBonificacion objetoiBonificacionCollectionNewObjetoiBonificacionToAttach : objetoiBonificacionCollectionNew) {
                objetoiBonificacionCollectionNewObjetoiBonificacionToAttach = em.getReference(objetoiBonificacionCollectionNewObjetoiBonificacionToAttach.getClass(), objetoiBonificacionCollectionNewObjetoiBonificacionToAttach.getId());
                attachedObjetoiBonificacionCollectionNew.add(objetoiBonificacionCollectionNewObjetoiBonificacionToAttach);
            }
            objetoiBonificacionCollectionNew = attachedObjetoiBonificacionCollectionNew;
            objetoi.setObjetoiBonificacionCollection(objetoiBonificacionCollectionNew);
            Collection<Objetoi> attachedObjetoiCollectionNew = new ArrayList<Objetoi>();
            for (Objetoi objetoiCollectionNewObjetoiToAttach : objetoiCollectionNew) {
                objetoiCollectionNewObjetoiToAttach = em.getReference(objetoiCollectionNewObjetoiToAttach.getClass(), objetoiCollectionNewObjetoiToAttach.getId());
                attachedObjetoiCollectionNew.add(objetoiCollectionNewObjetoiToAttach);
            }
            objetoiCollectionNew = attachedObjetoiCollectionNew;
            objetoi.setObjetoiCollection(objetoiCollectionNew);
            Collection<Desembolso> attachedDesembolsoCollectionNew = new ArrayList<Desembolso>();
            for (Desembolso desembolsoCollectionNewDesembolsoToAttach : desembolsoCollectionNew) {
                desembolsoCollectionNewDesembolsoToAttach = em.getReference(desembolsoCollectionNewDesembolsoToAttach.getClass(), desembolsoCollectionNewDesembolsoToAttach.getId());
                attachedDesembolsoCollectionNew.add(desembolsoCollectionNewDesembolsoToAttach);
            }
            desembolsoCollectionNew = attachedDesembolsoCollectionNew;
            objetoi.setDesembolsoCollection(desembolsoCollectionNew);
            Collection<ObjetoiIndice> attachedObjetoiIndiceCollectionNew = new ArrayList<ObjetoiIndice>();
            for (ObjetoiIndice objetoiIndiceCollectionNewObjetoiIndiceToAttach : objetoiIndiceCollectionNew) {
                objetoiIndiceCollectionNewObjetoiIndiceToAttach = em.getReference(objetoiIndiceCollectionNewObjetoiIndiceToAttach.getClass(), objetoiIndiceCollectionNewObjetoiIndiceToAttach.getId());
                attachedObjetoiIndiceCollectionNew.add(objetoiIndiceCollectionNewObjetoiIndiceToAttach);
            }
            objetoiIndiceCollectionNew = attachedObjetoiIndiceCollectionNew;
            objetoi.setObjetoiIndiceCollection(objetoiIndiceCollectionNew);
            Collection<DomicilioObjetoi> attachedDomicilioObjetoiCollectionNew = new ArrayList<DomicilioObjetoi>();
            for (DomicilioObjetoi domicilioObjetoiCollectionNewDomicilioObjetoiToAttach : domicilioObjetoiCollectionNew) {
                domicilioObjetoiCollectionNewDomicilioObjetoiToAttach = em.getReference(domicilioObjetoiCollectionNewDomicilioObjetoiToAttach.getClass(), domicilioObjetoiCollectionNewDomicilioObjetoiToAttach.getId());
                attachedDomicilioObjetoiCollectionNew.add(domicilioObjetoiCollectionNewDomicilioObjetoiToAttach);
            }
            domicilioObjetoiCollectionNew = attachedDomicilioObjetoiCollectionNew;
            objetoi.setDomicilioObjetoiCollection(domicilioObjetoiCollectionNew);
            Collection<ObjetoiComportamiento> attachedObjetoiComportamientoCollectionNew = new ArrayList<ObjetoiComportamiento>();
            for (ObjetoiComportamiento objetoiComportamientoCollectionNewObjetoiComportamientoToAttach : objetoiComportamientoCollectionNew) {
                objetoiComportamientoCollectionNewObjetoiComportamientoToAttach = em.getReference(objetoiComportamientoCollectionNewObjetoiComportamientoToAttach.getClass(), objetoiComportamientoCollectionNewObjetoiComportamientoToAttach.getId());
                attachedObjetoiComportamientoCollectionNew.add(objetoiComportamientoCollectionNewObjetoiComportamientoToAttach);
            }
            objetoiComportamientoCollectionNew = attachedObjetoiComportamientoCollectionNew;
            objetoi.setObjetoiComportamientoCollection(objetoiComportamientoCollectionNew);
            Collection<ObjetoiEstado> attachedObjetoiEstadoCollectionNew = new ArrayList<ObjetoiEstado>();
            for (ObjetoiEstado objetoiEstadoCollectionNewObjetoiEstadoToAttach : objetoiEstadoCollectionNew) {
                objetoiEstadoCollectionNewObjetoiEstadoToAttach = em.getReference(objetoiEstadoCollectionNewObjetoiEstadoToAttach.getClass(), objetoiEstadoCollectionNewObjetoiEstadoToAttach.getId());
                attachedObjetoiEstadoCollectionNew.add(objetoiEstadoCollectionNewObjetoiEstadoToAttach);
            }
            objetoiEstadoCollectionNew = attachedObjetoiEstadoCollectionNew;
            objetoi.setObjetoiEstadoCollection(objetoiEstadoCollectionNew);
            Collection<Boleto> attachedBoletoCollectionNew = new ArrayList<Boleto>();
            for (Boleto boletoCollectionNewBoletoToAttach : boletoCollectionNew) {
                boletoCollectionNewBoletoToAttach = em.getReference(boletoCollectionNewBoletoToAttach.getClass(), boletoCollectionNewBoletoToAttach.getBoletoPK());
                attachedBoletoCollectionNew.add(boletoCollectionNewBoletoToAttach);
            }
            boletoCollectionNew = attachedBoletoCollectionNew;
            objetoi.setBoletoCollection(boletoCollectionNew);
            Collection<Emideta> attachedEmidetaCollectionNew = new ArrayList<Emideta>();
            for (Emideta emidetaCollectionNewEmidetaToAttach : emidetaCollectionNew) {
                emidetaCollectionNewEmidetaToAttach = em.getReference(emidetaCollectionNewEmidetaToAttach.getClass(), emidetaCollectionNewEmidetaToAttach.getId());
                attachedEmidetaCollectionNew.add(emidetaCollectionNewEmidetaToAttach);
            }
            emidetaCollectionNew = attachedEmidetaCollectionNew;
            objetoi.setEmidetaCollection(emidetaCollectionNew);
            Collection<Certificado> attachedCertificadoCollectionNew = new ArrayList<Certificado>();
            for (Certificado certificadoCollectionNewCertificadoToAttach : certificadoCollectionNew) {
                certificadoCollectionNewCertificadoToAttach = em.getReference(certificadoCollectionNewCertificadoToAttach.getClass(), certificadoCollectionNewCertificadoToAttach.getId());
                attachedCertificadoCollectionNew.add(certificadoCollectionNewCertificadoToAttach);
            }
            certificadoCollectionNew = attachedCertificadoCollectionNew;
            objetoi.setCertificadoCollection(certificadoCollectionNew);
            Collection<Ctacte> attachedCtacteCollectionNew = new ArrayList<Ctacte>();
            for (Ctacte ctacteCollectionNewCtacteToAttach : ctacteCollectionNew) {
                ctacteCollectionNewCtacteToAttach = em.getReference(ctacteCollectionNewCtacteToAttach.getClass(), ctacteCollectionNewCtacteToAttach.getCtactePK());
                attachedCtacteCollectionNew.add(ctacteCollectionNewCtacteToAttach);
            }
            ctacteCollectionNew = attachedCtacteCollectionNew;
            objetoi.setCtacteCollection(ctacteCollectionNew);
            Collection<Vinedo> attachedVinedoCollectionNew = new ArrayList<Vinedo>();
            for (Vinedo vinedoCollectionNewVinedoToAttach : vinedoCollectionNew) {
                vinedoCollectionNewVinedoToAttach = em.getReference(vinedoCollectionNewVinedoToAttach.getClass(), vinedoCollectionNewVinedoToAttach.getId());
                attachedVinedoCollectionNew.add(vinedoCollectionNewVinedoToAttach);
            }
            vinedoCollectionNew = attachedVinedoCollectionNew;
            objetoi.setVinedoCollection(vinedoCollectionNew);
            Collection<ObjetoiEmergencia> attachedObjetoiEmergenciaCollectionNew = new ArrayList<ObjetoiEmergencia>();
            for (ObjetoiEmergencia objetoiEmergenciaCollectionNewObjetoiEmergenciaToAttach : objetoiEmergenciaCollectionNew) {
                objetoiEmergenciaCollectionNewObjetoiEmergenciaToAttach = em.getReference(objetoiEmergenciaCollectionNewObjetoiEmergenciaToAttach.getClass(), objetoiEmergenciaCollectionNewObjetoiEmergenciaToAttach.getId());
                attachedObjetoiEmergenciaCollectionNew.add(objetoiEmergenciaCollectionNewObjetoiEmergenciaToAttach);
            }
            objetoiEmergenciaCollectionNew = attachedObjetoiEmergenciaCollectionNew;
            objetoi.setObjetoiEmergenciaCollection(objetoiEmergenciaCollectionNew);
            Collection<Cuota> attachedCuotaCollectionNew = new ArrayList<Cuota>();
            for (Cuota cuotaCollectionNewCuotaToAttach : cuotaCollectionNew) {
                cuotaCollectionNewCuotaToAttach = em.getReference(cuotaCollectionNewCuotaToAttach.getClass(), cuotaCollectionNewCuotaToAttach.getId());
                attachedCuotaCollectionNew.add(cuotaCollectionNewCuotaToAttach);
            }
            cuotaCollectionNew = attachedCuotaCollectionNew;
            objetoi.setCuotaCollection(cuotaCollectionNew);
            objetoi = em.merge(objetoi);
            if (turnoIdOld != null && !turnoIdOld.equals(turnoIdNew)) {
                turnoIdOld.getObjetoiCollection().remove(objetoi);
                turnoIdOld = em.merge(turnoIdOld);
            }
            if (turnoIdNew != null && !turnoIdNew.equals(turnoIdOld)) {
                turnoIdNew.getObjetoiCollection().add(objetoi);
                turnoIdNew = em.merge(turnoIdNew);
            }
            if (subTipoLineaidOld != null && !subTipoLineaidOld.equals(subTipoLineaidNew)) {
                subTipoLineaidOld.getObjetoiCollection().remove(objetoi);
                subTipoLineaidOld = em.merge(subTipoLineaidOld);
            }
            if (subTipoLineaidNew != null && !subTipoLineaidNew.equals(subTipoLineaidOld)) {
                subTipoLineaidNew.getObjetoiCollection().add(objetoi);
                subTipoLineaidNew = em.merge(subTipoLineaidNew);
            }
            if (acuerdoPagoidOld != null && !acuerdoPagoidOld.equals(acuerdoPagoidNew)) {
                acuerdoPagoidOld.getObjetoiCollection().remove(objetoi);
                acuerdoPagoidOld = em.merge(acuerdoPagoidOld);
            }
            if (acuerdoPagoidNew != null && !acuerdoPagoidNew.equals(acuerdoPagoidOld)) {
                acuerdoPagoidNew.getObjetoiCollection().add(objetoi);
                acuerdoPagoidNew = em.merge(acuerdoPagoidNew);
            }
            if (lineaIdOld != null && !lineaIdOld.equals(lineaIdNew)) {
                lineaIdOld.getObjetoiCollection().remove(objetoi);
                lineaIdOld = em.merge(lineaIdOld);
            }
            if (lineaIdNew != null && !lineaIdNew.equals(lineaIdOld)) {
                lineaIdNew.getObjetoiCollection().add(objetoi);
                lineaIdNew = em.merge(lineaIdNew);
            }
            if (procesoAprobacionIDOld != null && !procesoAprobacionIDOld.equals(procesoAprobacionIDNew)) {
                procesoAprobacionIDOld.getObjetoiCollection().remove(objetoi);
                procesoAprobacionIDOld = em.merge(procesoAprobacionIDOld);
            }
            if (procesoAprobacionIDNew != null && !procesoAprobacionIDNew.equals(procesoAprobacionIDOld)) {
                procesoAprobacionIDNew.getObjetoiCollection().add(objetoi);
                procesoAprobacionIDNew = em.merge(procesoAprobacionIDNew);
            }
            for (ObjetoiBonificacion objetoiBonificacionCollectionOldObjetoiBonificacion : objetoiBonificacionCollectionOld) {
                if (!objetoiBonificacionCollectionNew.contains(objetoiBonificacionCollectionOldObjetoiBonificacion)) {
                    objetoiBonificacionCollectionOldObjetoiBonificacion.setIdCredito(null);
                    objetoiBonificacionCollectionOldObjetoiBonificacion = em.merge(objetoiBonificacionCollectionOldObjetoiBonificacion);
                }
            }
            for (ObjetoiBonificacion objetoiBonificacionCollectionNewObjetoiBonificacion : objetoiBonificacionCollectionNew) {
                if (!objetoiBonificacionCollectionOld.contains(objetoiBonificacionCollectionNewObjetoiBonificacion)) {
                    Objetoi oldIdCreditoOfObjetoiBonificacionCollectionNewObjetoiBonificacion = objetoiBonificacionCollectionNewObjetoiBonificacion.getIdCredito();
                    objetoiBonificacionCollectionNewObjetoiBonificacion.setIdCredito(objetoi);
                    objetoiBonificacionCollectionNewObjetoiBonificacion = em.merge(objetoiBonificacionCollectionNewObjetoiBonificacion);
                    if (oldIdCreditoOfObjetoiBonificacionCollectionNewObjetoiBonificacion != null && !oldIdCreditoOfObjetoiBonificacionCollectionNewObjetoiBonificacion.equals(objetoi)) {
                        oldIdCreditoOfObjetoiBonificacionCollectionNewObjetoiBonificacion.getObjetoiBonificacionCollection().remove(objetoiBonificacionCollectionNewObjetoiBonificacion);
                        oldIdCreditoOfObjetoiBonificacionCollectionNewObjetoiBonificacion = em.merge(oldIdCreditoOfObjetoiBonificacionCollectionNewObjetoiBonificacion);
                    }
                }
            }
            for (Objetoi objetoiCollectionOldObjetoi : objetoiCollectionOld) {
                if (!objetoiCollectionNew.contains(objetoiCollectionOldObjetoi)) {
                    objetoiCollectionOldObjetoi.setAcuerdoPagoid(null);
                    objetoiCollectionOldObjetoi = em.merge(objetoiCollectionOldObjetoi);
                }
            }
            for (Objetoi objetoiCollectionNewObjetoi : objetoiCollectionNew) {
                if (!objetoiCollectionOld.contains(objetoiCollectionNewObjetoi)) {
                    Objetoi oldAcuerdoPagoidOfObjetoiCollectionNewObjetoi = objetoiCollectionNewObjetoi.getAcuerdoPagoid();
                    objetoiCollectionNewObjetoi.setAcuerdoPagoid(objetoi);
                    objetoiCollectionNewObjetoi = em.merge(objetoiCollectionNewObjetoi);
                    if (oldAcuerdoPagoidOfObjetoiCollectionNewObjetoi != null && !oldAcuerdoPagoidOfObjetoiCollectionNewObjetoi.equals(objetoi)) {
                        oldAcuerdoPagoidOfObjetoiCollectionNewObjetoi.getObjetoiCollection().remove(objetoiCollectionNewObjetoi);
                        oldAcuerdoPagoidOfObjetoiCollectionNewObjetoi = em.merge(oldAcuerdoPagoidOfObjetoiCollectionNewObjetoi);
                    }
                }
            }
            for (Desembolso desembolsoCollectionOldDesembolso : desembolsoCollectionOld) {
                if (!desembolsoCollectionNew.contains(desembolsoCollectionOldDesembolso)) {
                    desembolsoCollectionOldDesembolso.setCreditoId(null);
                    desembolsoCollectionOldDesembolso = em.merge(desembolsoCollectionOldDesembolso);
                }
            }
            for (Desembolso desembolsoCollectionNewDesembolso : desembolsoCollectionNew) {
                if (!desembolsoCollectionOld.contains(desembolsoCollectionNewDesembolso)) {
                    Objetoi oldCreditoIdOfDesembolsoCollectionNewDesembolso = desembolsoCollectionNewDesembolso.getCreditoId();
                    desembolsoCollectionNewDesembolso.setCreditoId(objetoi);
                    desembolsoCollectionNewDesembolso = em.merge(desembolsoCollectionNewDesembolso);
                    if (oldCreditoIdOfDesembolsoCollectionNewDesembolso != null && !oldCreditoIdOfDesembolsoCollectionNewDesembolso.equals(objetoi)) {
                        oldCreditoIdOfDesembolsoCollectionNewDesembolso.getDesembolsoCollection().remove(desembolsoCollectionNewDesembolso);
                        oldCreditoIdOfDesembolsoCollectionNewDesembolso = em.merge(oldCreditoIdOfDesembolsoCollectionNewDesembolso);
                    }
                }
            }
            for (ObjetoiIndice objetoiIndiceCollectionOldObjetoiIndice : objetoiIndiceCollectionOld) {
                if (!objetoiIndiceCollectionNew.contains(objetoiIndiceCollectionOldObjetoiIndice)) {
                    objetoiIndiceCollectionOldObjetoiIndice.setCreditoId(null);
                    objetoiIndiceCollectionOldObjetoiIndice = em.merge(objetoiIndiceCollectionOldObjetoiIndice);
                }
            }
            for (ObjetoiIndice objetoiIndiceCollectionNewObjetoiIndice : objetoiIndiceCollectionNew) {
                if (!objetoiIndiceCollectionOld.contains(objetoiIndiceCollectionNewObjetoiIndice)) {
                    Objetoi oldCreditoIdOfObjetoiIndiceCollectionNewObjetoiIndice = objetoiIndiceCollectionNewObjetoiIndice.getCreditoId();
                    objetoiIndiceCollectionNewObjetoiIndice.setCreditoId(objetoi);
                    objetoiIndiceCollectionNewObjetoiIndice = em.merge(objetoiIndiceCollectionNewObjetoiIndice);
                    if (oldCreditoIdOfObjetoiIndiceCollectionNewObjetoiIndice != null && !oldCreditoIdOfObjetoiIndiceCollectionNewObjetoiIndice.equals(objetoi)) {
                        oldCreditoIdOfObjetoiIndiceCollectionNewObjetoiIndice.getObjetoiIndiceCollection().remove(objetoiIndiceCollectionNewObjetoiIndice);
                        oldCreditoIdOfObjetoiIndiceCollectionNewObjetoiIndice = em.merge(oldCreditoIdOfObjetoiIndiceCollectionNewObjetoiIndice);
                    }
                }
            }
            for (DomicilioObjetoi domicilioObjetoiCollectionOldDomicilioObjetoi : domicilioObjetoiCollectionOld) {
                if (!domicilioObjetoiCollectionNew.contains(domicilioObjetoiCollectionOldDomicilioObjetoi)) {
                    domicilioObjetoiCollectionOldDomicilioObjetoi.setObjetoiId(null);
                    domicilioObjetoiCollectionOldDomicilioObjetoi = em.merge(domicilioObjetoiCollectionOldDomicilioObjetoi);
                }
            }
            for (DomicilioObjetoi domicilioObjetoiCollectionNewDomicilioObjetoi : domicilioObjetoiCollectionNew) {
                if (!domicilioObjetoiCollectionOld.contains(domicilioObjetoiCollectionNewDomicilioObjetoi)) {
                    Objetoi oldObjetoiIdOfDomicilioObjetoiCollectionNewDomicilioObjetoi = domicilioObjetoiCollectionNewDomicilioObjetoi.getObjetoiId();
                    domicilioObjetoiCollectionNewDomicilioObjetoi.setObjetoiId(objetoi);
                    domicilioObjetoiCollectionNewDomicilioObjetoi = em.merge(domicilioObjetoiCollectionNewDomicilioObjetoi);
                    if (oldObjetoiIdOfDomicilioObjetoiCollectionNewDomicilioObjetoi != null && !oldObjetoiIdOfDomicilioObjetoiCollectionNewDomicilioObjetoi.equals(objetoi)) {
                        oldObjetoiIdOfDomicilioObjetoiCollectionNewDomicilioObjetoi.getDomicilioObjetoiCollection().remove(domicilioObjetoiCollectionNewDomicilioObjetoi);
                        oldObjetoiIdOfDomicilioObjetoiCollectionNewDomicilioObjetoi = em.merge(oldObjetoiIdOfDomicilioObjetoiCollectionNewDomicilioObjetoi);
                    }
                }
            }
            for (ObjetoiComportamiento objetoiComportamientoCollectionOldObjetoiComportamiento : objetoiComportamientoCollectionOld) {
                if (!objetoiComportamientoCollectionNew.contains(objetoiComportamientoCollectionOldObjetoiComportamiento)) {
                    objetoiComportamientoCollectionOldObjetoiComportamiento.setObjetoiId(null);
                    objetoiComportamientoCollectionOldObjetoiComportamiento = em.merge(objetoiComportamientoCollectionOldObjetoiComportamiento);
                }
            }
            for (ObjetoiComportamiento objetoiComportamientoCollectionNewObjetoiComportamiento : objetoiComportamientoCollectionNew) {
                if (!objetoiComportamientoCollectionOld.contains(objetoiComportamientoCollectionNewObjetoiComportamiento)) {
                    Objetoi oldObjetoiIdOfObjetoiComportamientoCollectionNewObjetoiComportamiento = objetoiComportamientoCollectionNewObjetoiComportamiento.getObjetoiId();
                    objetoiComportamientoCollectionNewObjetoiComportamiento.setObjetoiId(objetoi);
                    objetoiComportamientoCollectionNewObjetoiComportamiento = em.merge(objetoiComportamientoCollectionNewObjetoiComportamiento);
                    if (oldObjetoiIdOfObjetoiComportamientoCollectionNewObjetoiComportamiento != null && !oldObjetoiIdOfObjetoiComportamientoCollectionNewObjetoiComportamiento.equals(objetoi)) {
                        oldObjetoiIdOfObjetoiComportamientoCollectionNewObjetoiComportamiento.getObjetoiComportamientoCollection().remove(objetoiComportamientoCollectionNewObjetoiComportamiento);
                        oldObjetoiIdOfObjetoiComportamientoCollectionNewObjetoiComportamiento = em.merge(oldObjetoiIdOfObjetoiComportamientoCollectionNewObjetoiComportamiento);
                    }
                }
            }
            for (ObjetoiEstado objetoiEstadoCollectionOldObjetoiEstado : objetoiEstadoCollectionOld) {
                if (!objetoiEstadoCollectionNew.contains(objetoiEstadoCollectionOldObjetoiEstado)) {
                    objetoiEstadoCollectionOldObjetoiEstado.setObjetoiId(null);
                    objetoiEstadoCollectionOldObjetoiEstado = em.merge(objetoiEstadoCollectionOldObjetoiEstado);
                }
            }
            for (ObjetoiEstado objetoiEstadoCollectionNewObjetoiEstado : objetoiEstadoCollectionNew) {
                if (!objetoiEstadoCollectionOld.contains(objetoiEstadoCollectionNewObjetoiEstado)) {
                    Objetoi oldObjetoiIdOfObjetoiEstadoCollectionNewObjetoiEstado = objetoiEstadoCollectionNewObjetoiEstado.getObjetoiId();
                    objetoiEstadoCollectionNewObjetoiEstado.setObjetoiId(objetoi);
                    objetoiEstadoCollectionNewObjetoiEstado = em.merge(objetoiEstadoCollectionNewObjetoiEstado);
                    if (oldObjetoiIdOfObjetoiEstadoCollectionNewObjetoiEstado != null && !oldObjetoiIdOfObjetoiEstadoCollectionNewObjetoiEstado.equals(objetoi)) {
                        oldObjetoiIdOfObjetoiEstadoCollectionNewObjetoiEstado.getObjetoiEstadoCollection().remove(objetoiEstadoCollectionNewObjetoiEstado);
                        oldObjetoiIdOfObjetoiEstadoCollectionNewObjetoiEstado = em.merge(oldObjetoiIdOfObjetoiEstadoCollectionNewObjetoiEstado);
                    }
                }
            }
            for (Boleto boletoCollectionOldBoleto : boletoCollectionOld) {
                if (!boletoCollectionNew.contains(boletoCollectionOldBoleto)) {
                    boletoCollectionOldBoleto.setObjetoiId(null);
                    boletoCollectionOldBoleto = em.merge(boletoCollectionOldBoleto);
                }
            }
            for (Boleto boletoCollectionNewBoleto : boletoCollectionNew) {
                if (!boletoCollectionOld.contains(boletoCollectionNewBoleto)) {
                    Objetoi oldObjetoiIdOfBoletoCollectionNewBoleto = boletoCollectionNewBoleto.getObjetoiId();
                    boletoCollectionNewBoleto.setObjetoiId(objetoi);
                    boletoCollectionNewBoleto = em.merge(boletoCollectionNewBoleto);
                    if (oldObjetoiIdOfBoletoCollectionNewBoleto != null && !oldObjetoiIdOfBoletoCollectionNewBoleto.equals(objetoi)) {
                        oldObjetoiIdOfBoletoCollectionNewBoleto.getBoletoCollection().remove(boletoCollectionNewBoleto);
                        oldObjetoiIdOfBoletoCollectionNewBoleto = em.merge(oldObjetoiIdOfBoletoCollectionNewBoleto);
                    }
                }
            }
            for (Emideta emidetaCollectionOldEmideta : emidetaCollectionOld) {
                if (!emidetaCollectionNew.contains(emidetaCollectionOldEmideta)) {
                    emidetaCollectionOldEmideta.setCreditoId(null);
                    emidetaCollectionOldEmideta = em.merge(emidetaCollectionOldEmideta);
                }
            }
            for (Emideta emidetaCollectionNewEmideta : emidetaCollectionNew) {
                if (!emidetaCollectionOld.contains(emidetaCollectionNewEmideta)) {
                    Objetoi oldCreditoIdOfEmidetaCollectionNewEmideta = emidetaCollectionNewEmideta.getCreditoId();
                    emidetaCollectionNewEmideta.setCreditoId(objetoi);
                    emidetaCollectionNewEmideta = em.merge(emidetaCollectionNewEmideta);
                    if (oldCreditoIdOfEmidetaCollectionNewEmideta != null && !oldCreditoIdOfEmidetaCollectionNewEmideta.equals(objetoi)) {
                        oldCreditoIdOfEmidetaCollectionNewEmideta.getEmidetaCollection().remove(emidetaCollectionNewEmideta);
                        oldCreditoIdOfEmidetaCollectionNewEmideta = em.merge(oldCreditoIdOfEmidetaCollectionNewEmideta);
                    }
                }
            }
            for (Certificado certificadoCollectionOldCertificado : certificadoCollectionOld) {
                if (!certificadoCollectionNew.contains(certificadoCollectionOldCertificado)) {
                    certificadoCollectionOldCertificado.setCreditoId(null);
                    certificadoCollectionOldCertificado = em.merge(certificadoCollectionOldCertificado);
                }
            }
            for (Certificado certificadoCollectionNewCertificado : certificadoCollectionNew) {
                if (!certificadoCollectionOld.contains(certificadoCollectionNewCertificado)) {
                    Objetoi oldCreditoIdOfCertificadoCollectionNewCertificado = certificadoCollectionNewCertificado.getCreditoId();
                    certificadoCollectionNewCertificado.setCreditoId(objetoi);
                    certificadoCollectionNewCertificado = em.merge(certificadoCollectionNewCertificado);
                    if (oldCreditoIdOfCertificadoCollectionNewCertificado != null && !oldCreditoIdOfCertificadoCollectionNewCertificado.equals(objetoi)) {
                        oldCreditoIdOfCertificadoCollectionNewCertificado.getCertificadoCollection().remove(certificadoCollectionNewCertificado);
                        oldCreditoIdOfCertificadoCollectionNewCertificado = em.merge(oldCreditoIdOfCertificadoCollectionNewCertificado);
                    }
                }
            }
            for (Ctacte ctacteCollectionNewCtacte : ctacteCollectionNew) {
                if (!ctacteCollectionOld.contains(ctacteCollectionNewCtacte)) {
                    Objetoi oldObjetoiOfCtacteCollectionNewCtacte = ctacteCollectionNewCtacte.getObjetoi();
                    ctacteCollectionNewCtacte.setObjetoi(objetoi);
                    ctacteCollectionNewCtacte = em.merge(ctacteCollectionNewCtacte);
                    if (oldObjetoiOfCtacteCollectionNewCtacte != null && !oldObjetoiOfCtacteCollectionNewCtacte.equals(objetoi)) {
                        oldObjetoiOfCtacteCollectionNewCtacte.getCtacteCollection().remove(ctacteCollectionNewCtacte);
                        oldObjetoiOfCtacteCollectionNewCtacte = em.merge(oldObjetoiOfCtacteCollectionNewCtacte);
                    }
                }
            }
            for (Vinedo vinedoCollectionOldVinedo : vinedoCollectionOld) {
                if (!vinedoCollectionNew.contains(vinedoCollectionOldVinedo)) {
                    vinedoCollectionOldVinedo.setCreditoId(null);
                    vinedoCollectionOldVinedo = em.merge(vinedoCollectionOldVinedo);
                }
            }
            for (Vinedo vinedoCollectionNewVinedo : vinedoCollectionNew) {
                if (!vinedoCollectionOld.contains(vinedoCollectionNewVinedo)) {
                    Objetoi oldCreditoIdOfVinedoCollectionNewVinedo = vinedoCollectionNewVinedo.getCreditoId();
                    vinedoCollectionNewVinedo.setCreditoId(objetoi);
                    vinedoCollectionNewVinedo = em.merge(vinedoCollectionNewVinedo);
                    if (oldCreditoIdOfVinedoCollectionNewVinedo != null && !oldCreditoIdOfVinedoCollectionNewVinedo.equals(objetoi)) {
                        oldCreditoIdOfVinedoCollectionNewVinedo.getVinedoCollection().remove(vinedoCollectionNewVinedo);
                        oldCreditoIdOfVinedoCollectionNewVinedo = em.merge(oldCreditoIdOfVinedoCollectionNewVinedo);
                    }
                }
            }
            for (ObjetoiEmergencia objetoiEmergenciaCollectionOldObjetoiEmergencia : objetoiEmergenciaCollectionOld) {
                if (!objetoiEmergenciaCollectionNew.contains(objetoiEmergenciaCollectionOldObjetoiEmergencia)) {
                    objetoiEmergenciaCollectionOldObjetoiEmergencia.setCreditoId(null);
                    objetoiEmergenciaCollectionOldObjetoiEmergencia = em.merge(objetoiEmergenciaCollectionOldObjetoiEmergencia);
                }
            }
            for (ObjetoiEmergencia objetoiEmergenciaCollectionNewObjetoiEmergencia : objetoiEmergenciaCollectionNew) {
                if (!objetoiEmergenciaCollectionOld.contains(objetoiEmergenciaCollectionNewObjetoiEmergencia)) {
                    Objetoi oldCreditoIdOfObjetoiEmergenciaCollectionNewObjetoiEmergencia = objetoiEmergenciaCollectionNewObjetoiEmergencia.getCreditoId();
                    objetoiEmergenciaCollectionNewObjetoiEmergencia.setCreditoId(objetoi);
                    objetoiEmergenciaCollectionNewObjetoiEmergencia = em.merge(objetoiEmergenciaCollectionNewObjetoiEmergencia);
                    if (oldCreditoIdOfObjetoiEmergenciaCollectionNewObjetoiEmergencia != null && !oldCreditoIdOfObjetoiEmergenciaCollectionNewObjetoiEmergencia.equals(objetoi)) {
                        oldCreditoIdOfObjetoiEmergenciaCollectionNewObjetoiEmergencia.getObjetoiEmergenciaCollection().remove(objetoiEmergenciaCollectionNewObjetoiEmergencia);
                        oldCreditoIdOfObjetoiEmergenciaCollectionNewObjetoiEmergencia = em.merge(oldCreditoIdOfObjetoiEmergenciaCollectionNewObjetoiEmergencia);
                    }
                }
            }
            for (Cuota cuotaCollectionOldCuota : cuotaCollectionOld) {
                if (!cuotaCollectionNew.contains(cuotaCollectionOldCuota)) {
                    cuotaCollectionOldCuota.setCreditoId(null);
                    cuotaCollectionOldCuota = em.merge(cuotaCollectionOldCuota);
                }
            }
            for (Cuota cuotaCollectionNewCuota : cuotaCollectionNew) {
                if (!cuotaCollectionOld.contains(cuotaCollectionNewCuota)) {
                    Objetoi oldCreditoIdOfCuotaCollectionNewCuota = cuotaCollectionNewCuota.getCreditoId();
                    cuotaCollectionNewCuota.setCreditoId(objetoi);
                    cuotaCollectionNewCuota = em.merge(cuotaCollectionNewCuota);
                    if (oldCreditoIdOfCuotaCollectionNewCuota != null && !oldCreditoIdOfCuotaCollectionNewCuota.equals(objetoi)) {
                        oldCreditoIdOfCuotaCollectionNewCuota.getCuotaCollection().remove(cuotaCollectionNewCuota);
                        oldCreditoIdOfCuotaCollectionNewCuota = em.merge(oldCreditoIdOfCuotaCollectionNewCuota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = objetoi.getId();
                if (findObjetoi(id) == null) {
                    throw new NonexistentEntityException("The objetoi with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi objetoi;
            try {
                objetoi = em.getReference(Objetoi.class, id);
                objetoi.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetoi with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ctacte> ctacteCollectionOrphanCheck = objetoi.getCtacteCollection();
            for (Ctacte ctacteCollectionOrphanCheckCtacte : ctacteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetoi (" + objetoi + ") cannot be destroyed since the Ctacte " + ctacteCollectionOrphanCheckCtacte + " in its ctacteCollection field has a non-nullable objetoi field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Turno turnoId = objetoi.getTurnoId();
            if (turnoId != null) {
                turnoId.getObjetoiCollection().remove(objetoi);
                turnoId = em.merge(turnoId);
            }
            SubTipoLinea subTipoLineaid = objetoi.getSubTipoLineaid();
            if (subTipoLineaid != null) {
                subTipoLineaid.getObjetoiCollection().remove(objetoi);
                subTipoLineaid = em.merge(subTipoLineaid);
            }
            Objetoi acuerdoPagoid = objetoi.getAcuerdoPagoid();
            if (acuerdoPagoid != null) {
                acuerdoPagoid.getObjetoiCollection().remove(objetoi);
                acuerdoPagoid = em.merge(acuerdoPagoid);
            }
            Linea lineaId = objetoi.getLineaId();
            if (lineaId != null) {
                lineaId.getObjetoiCollection().remove(objetoi);
                lineaId = em.merge(lineaId);
            }
            JbpmProcessinstance procesoAprobacionID = objetoi.getProcesoAprobacionID();
            if (procesoAprobacionID != null) {
                procesoAprobacionID.getObjetoiCollection().remove(objetoi);
                procesoAprobacionID = em.merge(procesoAprobacionID);
            }
            Collection<ObjetoiBonificacion> objetoiBonificacionCollection = objetoi.getObjetoiBonificacionCollection();
            for (ObjetoiBonificacion objetoiBonificacionCollectionObjetoiBonificacion : objetoiBonificacionCollection) {
                objetoiBonificacionCollectionObjetoiBonificacion.setIdCredito(null);
                objetoiBonificacionCollectionObjetoiBonificacion = em.merge(objetoiBonificacionCollectionObjetoiBonificacion);
            }
            Collection<Objetoi> objetoiCollection = objetoi.getObjetoiCollection();
            for (Objetoi objetoiCollectionObjetoi : objetoiCollection) {
                objetoiCollectionObjetoi.setAcuerdoPagoid(null);
                objetoiCollectionObjetoi = em.merge(objetoiCollectionObjetoi);
            }
            Collection<Desembolso> desembolsoCollection = objetoi.getDesembolsoCollection();
            for (Desembolso desembolsoCollectionDesembolso : desembolsoCollection) {
                desembolsoCollectionDesembolso.setCreditoId(null);
                desembolsoCollectionDesembolso = em.merge(desembolsoCollectionDesembolso);
            }
            Collection<ObjetoiIndice> objetoiIndiceCollection = objetoi.getObjetoiIndiceCollection();
            for (ObjetoiIndice objetoiIndiceCollectionObjetoiIndice : objetoiIndiceCollection) {
                objetoiIndiceCollectionObjetoiIndice.setCreditoId(null);
                objetoiIndiceCollectionObjetoiIndice = em.merge(objetoiIndiceCollectionObjetoiIndice);
            }
            Collection<DomicilioObjetoi> domicilioObjetoiCollection = objetoi.getDomicilioObjetoiCollection();
            for (DomicilioObjetoi domicilioObjetoiCollectionDomicilioObjetoi : domicilioObjetoiCollection) {
                domicilioObjetoiCollectionDomicilioObjetoi.setObjetoiId(null);
                domicilioObjetoiCollectionDomicilioObjetoi = em.merge(domicilioObjetoiCollectionDomicilioObjetoi);
            }
            Collection<ObjetoiComportamiento> objetoiComportamientoCollection = objetoi.getObjetoiComportamientoCollection();
            for (ObjetoiComportamiento objetoiComportamientoCollectionObjetoiComportamiento : objetoiComportamientoCollection) {
                objetoiComportamientoCollectionObjetoiComportamiento.setObjetoiId(null);
                objetoiComportamientoCollectionObjetoiComportamiento = em.merge(objetoiComportamientoCollectionObjetoiComportamiento);
            }
            Collection<ObjetoiEstado> objetoiEstadoCollection = objetoi.getObjetoiEstadoCollection();
            for (ObjetoiEstado objetoiEstadoCollectionObjetoiEstado : objetoiEstadoCollection) {
                objetoiEstadoCollectionObjetoiEstado.setObjetoiId(null);
                objetoiEstadoCollectionObjetoiEstado = em.merge(objetoiEstadoCollectionObjetoiEstado);
            }
            Collection<Boleto> boletoCollection = objetoi.getBoletoCollection();
            for (Boleto boletoCollectionBoleto : boletoCollection) {
                boletoCollectionBoleto.setObjetoiId(null);
                boletoCollectionBoleto = em.merge(boletoCollectionBoleto);
            }
            Collection<Emideta> emidetaCollection = objetoi.getEmidetaCollection();
            for (Emideta emidetaCollectionEmideta : emidetaCollection) {
                emidetaCollectionEmideta.setCreditoId(null);
                emidetaCollectionEmideta = em.merge(emidetaCollectionEmideta);
            }
            Collection<Certificado> certificadoCollection = objetoi.getCertificadoCollection();
            for (Certificado certificadoCollectionCertificado : certificadoCollection) {
                certificadoCollectionCertificado.setCreditoId(null);
                certificadoCollectionCertificado = em.merge(certificadoCollectionCertificado);
            }
            Collection<Vinedo> vinedoCollection = objetoi.getVinedoCollection();
            for (Vinedo vinedoCollectionVinedo : vinedoCollection) {
                vinedoCollectionVinedo.setCreditoId(null);
                vinedoCollectionVinedo = em.merge(vinedoCollectionVinedo);
            }
            Collection<ObjetoiEmergencia> objetoiEmergenciaCollection = objetoi.getObjetoiEmergenciaCollection();
            for (ObjetoiEmergencia objetoiEmergenciaCollectionObjetoiEmergencia : objetoiEmergenciaCollection) {
                objetoiEmergenciaCollectionObjetoiEmergencia.setCreditoId(null);
                objetoiEmergenciaCollectionObjetoiEmergencia = em.merge(objetoiEmergenciaCollectionObjetoiEmergencia);
            }
            Collection<Cuota> cuotaCollection = objetoi.getCuotaCollection();
            for (Cuota cuotaCollectionCuota : cuotaCollection) {
                cuotaCollectionCuota.setCreditoId(null);
                cuotaCollectionCuota = em.merge(cuotaCollectionCuota);
            }
            em.remove(objetoi);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public List<Objetoi> findObjetoiEntities() {
        return findObjetoiEntities(true, -1, -1);
    }

    public List<Objetoi> findObjetoiEntities(int maxResults, int firstResult) {
        return findObjetoiEntities(false, maxResults, firstResult);
    }

    private List<Objetoi> findObjetoiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Objetoi as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            //em.close();
        }
    }

    public Objetoi findObjetoi(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetoi.class, id);
        } finally {
            //em.close();
        }
    }

     public BigDecimal getLastInsertedID() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("select max(o.id) from Objetoi as o");
            Vector id = (Vector) q.getSingleResult();
           return new BigDecimal(id.get(0).toString());
        } finally {
            //em.close()
        }
    }
    public int getObjetoiCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Objetoi as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
   public List<Objetoi> findByCredito(BigDecimal numeroCredito) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Objetoi.findByNumeroCredito");
            q.setParameter("numeroCredito", numeroCredito.toString());
            List<Objetoi> o = q.getResultList();
            return o;
        } finally {
            //em.close();
        }
    }
    public List<Objetoi> findByCredito(String numeroCredito) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Objetoi.findByNumeroCredito");
            q.setParameter("numeroCredito", numeroCredito);
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
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA4_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");
            conn.createStatement().execute(string);
            conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF");

           
            
        } finally {
            //System.out.println(string);
            //em.close();
        }
    } 

    public void closeEEntityManager(){
        EntityManager em = getEntityManager();
        em.close();
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

}
