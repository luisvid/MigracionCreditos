/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
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
@Table(name = "BARRIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b"),
    @NamedQuery(name = "Barrio.findByCodiBrr", query = "SELECT b FROM Barrio b WHERE b.codiBrr = :codiBrr"),
    @NamedQuery(name = "Barrio.findByDescBrr", query = "SELECT b FROM Barrio b WHERE b.descBrr = :descBrr"),
    @NamedQuery(name = "Barrio.findByOfic99", query = "SELECT b FROM Barrio b WHERE b.ofic99 = :ofic99"),
    @NamedQuery(name = "Barrio.findByAbrBrr", query = "SELECT b FROM Barrio b WHERE b.abrBrr = :abrBrr")})
public class Barrio implements Serializable {
    @OneToMany(mappedBy = "barrioCODIBRR")
    private List<Domicilio> domicilioList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODI_BRR")
    private BigDecimal codiBrr;
    @Column(name = "DESC_BRR")
    private String descBrr;
    @Column(name = "OFIC_99")
    private BigInteger ofic99;
    @Column(name = "ABR_BRR")
    private String abrBrr;
    @OneToMany(mappedBy = "barrioCODIBRR")
    private Collection<Domicilio> domicilioCollection;

    public Barrio() {
    }

    public Barrio(BigDecimal codiBrr) {
        this.codiBrr = codiBrr;
    }

    public BigDecimal getCodiBrr() {
        return codiBrr;
    }

    public void setCodiBrr(BigDecimal codiBrr) {
        this.codiBrr = codiBrr;
    }

    public String getDescBrr() {
        return descBrr;
    }

    public void setDescBrr(String descBrr) {
        this.descBrr = descBrr;
    }

    public BigInteger getOfic99() {
        return ofic99;
    }

    public void setOfic99(BigInteger ofic99) {
        this.ofic99 = ofic99;
    }

    public String getAbrBrr() {
        return abrBrr;
    }

    public void setAbrBrr(String abrBrr) {
        this.abrBrr = abrBrr;
    }

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiBrr != null ? codiBrr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barrio)) {
            return false;
        }
        Barrio other = (Barrio) object;
        if ((this.codiBrr == null && other.codiBrr != null) || (this.codiBrr != null && !this.codiBrr.equals(other.codiBrr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Barrio[ codiBrr=" + codiBrr + " ]";
    }

    @XmlTransient
    public List<Domicilio> getDomicilioList() {
        return domicilioList;
    }

    public void setDomicilioList(List<Domicilio> domicilioList) {
        this.domicilioList = domicilioList;
    }
    
}
