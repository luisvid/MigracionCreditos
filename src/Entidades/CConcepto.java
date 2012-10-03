/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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
@Table(name = "CConcepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CConcepto.findAll", query = "SELECT c FROM CConcepto c"),
    @NamedQuery(name = "CConcepto.findByConcepto", query = "SELECT c FROM CConcepto c WHERE c.concepto = :concepto"),
    @NamedQuery(name = "CConcepto.findByAbreviatura", query = "SELECT c FROM CConcepto c WHERE c.abreviatura = :abreviatura"),
    @NamedQuery(name = "CConcepto.findByDetalle", query = "SELECT c FROM CConcepto c WHERE c.detalle = :detalle")})
public class CConcepto implements Serializable {
    @OneToMany(mappedBy = "conceptoConcepto")
    private Collection<Concepto> conceptoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "abreviatura")
    private String abreviatura;
    @Column(name = "detalle")
    private String detalle;
    @OneToMany(mappedBy = "conceptoAforoconcepto")
    private Collection<Bonificacion> bonificacionCollection;
    @OneToMany(mappedBy = "punitorioId")
    private Collection<Linea> lineaCollection;
    @OneToMany(mappedBy = "capitalId")
    private Collection<Linea> lineaCollection1;
    @OneToMany(mappedBy = "moratorioId")
    private Collection<Linea> lineaCollection2;
    @OneToMany(mappedBy = "multaId")
    private Collection<Linea> lineaCollection3;
    @OneToMany(mappedBy = "compensatorioId")
    private Collection<Linea> lineaCollection4;
    @OneToMany(mappedBy = "gastoId")
    private Collection<Linea> lineaCollection5;

    public CConcepto() {
    }

    public CConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @XmlTransient
    public Collection<Bonificacion> getBonificacionCollection() {
        return bonificacionCollection;
    }

    public void setBonificacionCollection(Collection<Bonificacion> bonificacionCollection) {
        this.bonificacionCollection = bonificacionCollection;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection() {
        return lineaCollection;
    }

    public void setLineaCollection(Collection<Linea> lineaCollection) {
        this.lineaCollection = lineaCollection;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection1() {
        return lineaCollection1;
    }

    public void setLineaCollection1(Collection<Linea> lineaCollection1) {
        this.lineaCollection1 = lineaCollection1;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection2() {
        return lineaCollection2;
    }

    public void setLineaCollection2(Collection<Linea> lineaCollection2) {
        this.lineaCollection2 = lineaCollection2;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection3() {
        return lineaCollection3;
    }

    public void setLineaCollection3(Collection<Linea> lineaCollection3) {
        this.lineaCollection3 = lineaCollection3;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection4() {
        return lineaCollection4;
    }

    public void setLineaCollection4(Collection<Linea> lineaCollection4) {
        this.lineaCollection4 = lineaCollection4;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection5() {
        return lineaCollection5;
    }

    public void setLineaCollection5(Collection<Linea> lineaCollection5) {
        this.lineaCollection5 = lineaCollection5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (concepto != null ? concepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CConcepto)) {
            return false;
        }
        CConcepto other = (CConcepto) object;
        if ((this.concepto == null && other.concepto != null) || (this.concepto != null && !this.concepto.equals(other.concepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CConcepto[ concepto=" + concepto + " ]";
    }

    @XmlTransient
    public Collection<Concepto> getConceptoCollection() {
        return conceptoCollection;
    }

    public void setConceptoCollection(Collection<Concepto> conceptoCollection) {
        this.conceptoCollection = conceptoCollection;
    }
    
}
