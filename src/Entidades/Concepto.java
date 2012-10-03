/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c"),
    @NamedQuery(name = "Concepto.findById", query = "SELECT c FROM Concepto c WHERE c.id = :id"),
    @NamedQuery(name = "Concepto.findByPeriodo", query = "SELECT c FROM Concepto c WHERE c.periodo = :periodo"),
    @NamedQuery(name = "Concepto.findByActivoIDPLANCTA", query = "SELECT c FROM Concepto c WHERE c.activoIDPLANCTA = :activoIDPLANCTA"),
    @NamedQuery(name = "Concepto.findByExposicionIDINSTITUCIONAL", query = "SELECT c FROM Concepto c WHERE c.exposicionIDINSTITUCIONAL = :exposicionIDINSTITUCIONAL"),
    @NamedQuery(name = "Concepto.findByExtraPresIDPLANCTA", query = "SELECT c FROM Concepto c WHERE c.extraPresIDPLANCTA = :extraPresIDPLANCTA"),
    @NamedQuery(name = "Concepto.findByFinanciamientoIDNOMENCLADOR", query = "SELECT c FROM Concepto c WHERE c.financiamientoIDNOMENCLADOR = :financiamientoIDNOMENCLADOR"),
    @NamedQuery(name = "Concepto.findByOrdenNoPatrIDPLANCTA", query = "SELECT c FROM Concepto c WHERE c.ordenNoPatrIDPLANCTA = :ordenNoPatrIDPLANCTA"),
    @NamedQuery(name = "Concepto.findByPartidaIDIEGRESO", query = "SELECT c FROM Concepto c WHERE c.partidaIDIEGRESO = :partidaIDIEGRESO")})
public class Concepto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "periodo")
    private BigInteger periodo;
    @Column(name = "activo_IDPLANCTA")
    private BigInteger activoIDPLANCTA;
    @Column(name = "exposicion_IDINSTITUCIONAL")
    private BigInteger exposicionIDINSTITUCIONAL;
    @Column(name = "extraPres_IDPLANCTA")
    private BigInteger extraPresIDPLANCTA;
    @Column(name = "financiamiento_IDNOMENCLADOR")
    private BigInteger financiamientoIDNOMENCLADOR;
    @Column(name = "ordenNoPatr_IDPLANCTA")
    private BigInteger ordenNoPatrIDPLANCTA;
    @Column(name = "partida_IDIEGRESO")
    private BigInteger partidaIDIEGRESO;
    @OneToMany(mappedBy = "facturadoId")
    private Collection<Ctacte> ctacteCollection;
    @OneToMany(mappedBy = "asociadoId")
    private Collection<Ctacte> ctacteCollection1;
    @JoinColumn(name = "concepto_concepto", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto conceptoConcepto;

    public Concepto() {
    }

    public Concepto(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getPeriodo() {
        return periodo;
    }

    public void setPeriodo(BigInteger periodo) {
        this.periodo = periodo;
    }

    public BigInteger getActivoIDPLANCTA() {
        return activoIDPLANCTA;
    }

    public void setActivoIDPLANCTA(BigInteger activoIDPLANCTA) {
        this.activoIDPLANCTA = activoIDPLANCTA;
    }

    public BigInteger getExposicionIDINSTITUCIONAL() {
        return exposicionIDINSTITUCIONAL;
    }

    public void setExposicionIDINSTITUCIONAL(BigInteger exposicionIDINSTITUCIONAL) {
        this.exposicionIDINSTITUCIONAL = exposicionIDINSTITUCIONAL;
    }

    public BigInteger getExtraPresIDPLANCTA() {
        return extraPresIDPLANCTA;
    }

    public void setExtraPresIDPLANCTA(BigInteger extraPresIDPLANCTA) {
        this.extraPresIDPLANCTA = extraPresIDPLANCTA;
    }

    public BigInteger getFinanciamientoIDNOMENCLADOR() {
        return financiamientoIDNOMENCLADOR;
    }

    public void setFinanciamientoIDNOMENCLADOR(BigInteger financiamientoIDNOMENCLADOR) {
        this.financiamientoIDNOMENCLADOR = financiamientoIDNOMENCLADOR;
    }

    public BigInteger getOrdenNoPatrIDPLANCTA() {
        return ordenNoPatrIDPLANCTA;
    }

    public void setOrdenNoPatrIDPLANCTA(BigInteger ordenNoPatrIDPLANCTA) {
        this.ordenNoPatrIDPLANCTA = ordenNoPatrIDPLANCTA;
    }

    public BigInteger getPartidaIDIEGRESO() {
        return partidaIDIEGRESO;
    }

    public void setPartidaIDIEGRESO(BigInteger partidaIDIEGRESO) {
        this.partidaIDIEGRESO = partidaIDIEGRESO;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection() {
        return ctacteCollection;
    }

    public void setCtacteCollection(Collection<Ctacte> ctacteCollection) {
        this.ctacteCollection = ctacteCollection;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection1() {
        return ctacteCollection1;
    }

    public void setCtacteCollection1(Collection<Ctacte> ctacteCollection1) {
        this.ctacteCollection1 = ctacteCollection1;
    }

    public CConcepto getConceptoConcepto() {
        return conceptoConcepto;
    }

    public void setConceptoConcepto(CConcepto conceptoConcepto) {
        this.conceptoConcepto = conceptoConcepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Concepto[ id=" + id + " ]";
    }
    
}
