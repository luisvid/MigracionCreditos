/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
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
@Table(name = "CALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calle.findAll", query = "SELECT c FROM Calle c"),
    @NamedQuery(name = "Calle.findByIdcalle", query = "SELECT c FROM Calle c WHERE c.idcalle = :idcalle"),
    @NamedQuery(name = "Calle.findByCodi08", query = "SELECT c FROM Calle c WHERE c.codi08 = :codi08"),
    @NamedQuery(name = "Calle.findByDeta08", query = "SELECT c FROM Calle c WHERE c.deta08 = :deta08"),
    @NamedQuery(name = "Calle.findByObservaciones", query = "SELECT c FROM Calle c WHERE c.observaciones = :observaciones")})
public class Calle implements Serializable {
    @OneToMany(mappedBy = "idcalle")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "calleIDCALLE")
    private List<Domicilio> domicilioList;
    @OneToMany(mappedBy = "calleIDCALLE")
    private Collection<Domicilio> domicilioCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDCALLE")
    private BigDecimal idcalle;
    @Column(name = "CODI_08")
    private String codi08;
    @Column(name = "DETA_08")
    private String deta08;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @OneToMany(mappedBy = "idcalle")
    private Collection<Persona> personaCollection;
    @JoinColumn(name = "CODI_19", referencedColumnName = "IDLOCALIDAD")
    @ManyToOne
    private Localidad codi19;

    public Calle() {
    }

    public Calle(BigDecimal idcalle) {
        this.idcalle = idcalle;
    }

    public BigDecimal getIdcalle() {
        return idcalle;
    }

    public void setIdcalle(BigDecimal idcalle) {
        this.idcalle = idcalle;
    }

    public String getCodi08() {
        return codi08;
    }

    public void setCodi08(String codi08) {
        this.codi08 = codi08;
    }

    public String getDeta08() {
        return deta08;
    }

    public void setDeta08(String deta08) {
        this.deta08 = deta08;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    public Localidad getCodi19() {
        return codi19;
    }

    public void setCodi19(Localidad codi19) {
        this.codi19 = codi19;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalle != null ? idcalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calle)) {
            return false;
        }
        Calle other = (Calle) object;
        if ((this.idcalle == null && other.idcalle != null) || (this.idcalle != null && !this.idcalle.equals(other.idcalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Calle[ idcalle=" + idcalle + " ]";
    }

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @XmlTransient
    public List<Domicilio> getDomicilioList() {
        return domicilioList;
    }

    public void setDomicilioList(List<Domicilio> domicilioList) {
        this.domicilioList = domicilioList;
    }
    
}
