/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "EmergenciaPeriodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmergenciaPeriodo.findAll", query = "SELECT e FROM EmergenciaPeriodo e"),
    @NamedQuery(name = "EmergenciaPeriodo.findById", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.id = :id"),
    @NamedQuery(name = "EmergenciaPeriodo.findByAnio", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.anio = :anio"),
    @NamedQuery(name = "EmergenciaPeriodo.findByBaseCalculo", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.baseCalculo = :baseCalculo"),
    @NamedQuery(name = "EmergenciaPeriodo.findByCoefExpFrac", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.coefExpFrac = :coefExpFrac"),
    @NamedQuery(name = "EmergenciaPeriodo.findByCoefExpGranel", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.coefExpGranel = :coefExpGranel"),
    @NamedQuery(name = "EmergenciaPeriodo.findByCoefMosto", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.coefMosto = :coefMosto"),
    @NamedQuery(name = "EmergenciaPeriodo.findByDescripcionCiclo", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.descripcionCiclo = :descripcionCiclo"),
    @NamedQuery(name = "EmergenciaPeriodo.findByTasa", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.tasa = :tasa"),
    @NamedQuery(name = "EmergenciaPeriodo.findByTolerancia", query = "SELECT e FROM EmergenciaPeriodo e WHERE e.tolerancia = :tolerancia")})
public class EmergenciaPeriodo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "anio")
    private int anio;
    @Column(name = "baseCalculo")
    private Double baseCalculo;
    @Column(name = "coefExpFrac")
    private Double coefExpFrac;
    @Column(name = "coefExpGranel")
    private Double coefExpGranel;
    @Column(name = "coefMosto")
    private Double coefMosto;
    @Column(name = "descripcionCiclo")
    private String descripcionCiclo;
    @Column(name = "tasa")
    private Double tasa;
    @Column(name = "tolerancia")
    private Double tolerancia;
    @OneToMany(mappedBy = "emergenciaPeriodoid")
    private Collection<Emergencia> emergenciaCollection;

    public EmergenciaPeriodo() {
    }

    public EmergenciaPeriodo(BigDecimal id) {
        this.id = id;
    }

    public EmergenciaPeriodo(BigDecimal id, int anio) {
        this.id = id;
        this.anio = anio;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Double getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public Double getCoefExpFrac() {
        return coefExpFrac;
    }

    public void setCoefExpFrac(Double coefExpFrac) {
        this.coefExpFrac = coefExpFrac;
    }

    public Double getCoefExpGranel() {
        return coefExpGranel;
    }

    public void setCoefExpGranel(Double coefExpGranel) {
        this.coefExpGranel = coefExpGranel;
    }

    public Double getCoefMosto() {
        return coefMosto;
    }

    public void setCoefMosto(Double coefMosto) {
        this.coefMosto = coefMosto;
    }

    public String getDescripcionCiclo() {
        return descripcionCiclo;
    }

    public void setDescripcionCiclo(String descripcionCiclo) {
        this.descripcionCiclo = descripcionCiclo;
    }

    public Double getTasa() {
        return tasa;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public Double getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Double tolerancia) {
        this.tolerancia = tolerancia;
    }

    @XmlTransient
    public Collection<Emergencia> getEmergenciaCollection() {
        return emergenciaCollection;
    }

    public void setEmergenciaCollection(Collection<Emergencia> emergenciaCollection) {
        this.emergenciaCollection = emergenciaCollection;
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
        if (!(object instanceof EmergenciaPeriodo)) {
            return false;
        }
        EmergenciaPeriodo other = (EmergenciaPeriodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.EmergenciaPeriodo[ id=" + id + " ]";
    }
    
}
