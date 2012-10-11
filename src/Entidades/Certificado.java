/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Certificado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificado.findAll", query = "SELECT c FROM Certificado c"),
    @NamedQuery(name = "Certificado.findById", query = "SELECT c FROM Certificado c WHERE c.id = :id"),
    @NamedQuery(name = "Certificado.findByCicloAnual", query = "SELECT c FROM Certificado c WHERE c.cicloAnual = :cicloAnual"),
    @NamedQuery(name = "Certificado.findByIrrigacion", query = "SELECT c FROM Certificado c WHERE c.irrigacion = :irrigacion"),
    @NamedQuery(name = "Certificado.findByNomenclaturaCatastral", query = "SELECT c FROM Certificado c WHERE c.nomenclaturaCatastral = :nomenclaturaCatastral"),
    @NamedQuery(name = "Certificado.findByNroCertificado", query = "SELECT c FROM Certificado c WHERE c.nroCertificado = :nroCertificado"),
    @NamedQuery(name = "Certificado.findByNroINV", query = "SELECT c FROM Certificado c WHERE c.nroINV = :nroINV"),
    @NamedQuery(name = "Certificado.findByTipoDano", query = "SELECT c FROM Certificado c WHERE c.tipoDano = :tipoDano")})
public class Certificado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "cicloAnual")
    private short cicloAnual;
    @Column(name = "irrigacion")
    private String irrigacion;
    @Column(name = "nomenclaturaCatastral")
    private String nomenclaturaCatastral;
    @Column(name = "nroCertificado")
    private String nroCertificado;
    @Column(name = "nroINV")
    private String nroINV;
    @Column(name = "tipoDano")
    private String tipoDano;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;

    public Certificado() {
    }

    public Certificado(BigDecimal id) {
        this.id = id;
    }

    public Certificado(BigDecimal id, short cicloAnual) {
        this.id = id;
        this.cicloAnual = cicloAnual;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public short getCicloAnual() {
        return cicloAnual;
    }

    public void setCicloAnual(short cicloAnual) {
        this.cicloAnual = cicloAnual;
    }

    public String getIrrigacion() {
        return irrigacion;
    }

    public void setIrrigacion(String irrigacion) {
        this.irrigacion = irrigacion;
    }

    public String getNomenclaturaCatastral() {
        return nomenclaturaCatastral;
    }

    public void setNomenclaturaCatastral(String nomenclaturaCatastral) {
        this.nomenclaturaCatastral = nomenclaturaCatastral;
    }

    public String getNroCertificado() {
        return nroCertificado;
    }

    public void setNroCertificado(String nroCertificado) {
        this.nroCertificado = nroCertificado;
    }

    public String getNroINV() {
        return nroINV;
    }

    public void setNroINV(String nroINV) {
        this.nroINV = nroINV;
    }

    public String getTipoDano() {
        return tipoDano;
    }

    public void setTipoDano(String tipoDano) {
        this.tipoDano = tipoDano;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
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
        if (!(object instanceof Certificado)) {
            return false;
        }
        Certificado other = (Certificado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Certificado[ id=" + id + " ]";
    }
    
}
