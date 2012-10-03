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
@Table(name = "LOCALIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
    @NamedQuery(name = "Localidad.findByIdlocalidad", query = "SELECT l FROM Localidad l WHERE l.idlocalidad = :idlocalidad"),
    @NamedQuery(name = "Localidad.findByNombre", query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Localidad.findByAbrev", query = "SELECT l FROM Localidad l WHERE l.abrev = :abrev"),
    @NamedQuery(name = "Localidad.findByCp", query = "SELECT l FROM Localidad l WHERE l.cp = :cp")})
public class Localidad implements Serializable {
    @OneToMany(mappedBy = "idlocalidad")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "codi19")
    private List<Calle> calleList;
    @OneToMany(mappedBy = "localidadIDLOCALIDAD")
    private List<Domicilio> domicilioList;
    @OneToMany(mappedBy = "localidadIDLOCALIDAD")
    private Collection<Vinedo> vinedoCollection;
    @OneToMany(mappedBy = "localidadIDLOCALIDAD")
    private Collection<Domicilio> domicilioCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDLOCALIDAD")
    private BigDecimal idlocalidad;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ABREV")
    private String abrev;
    @Basic(optional = false)
    @Column(name = "CP")
    private String cp;
    @OneToMany(mappedBy = "idlocalidad")
    private Collection<Persona> personaCollection;
    @JoinColumn(name = "CODI_08", referencedColumnName = "CODI_08")
    @ManyToOne
    private Provin codi08;
    @OneToMany(mappedBy = "codi19")
    private Collection<Calle> calleCollection;

    public Localidad() {
    }

    public Localidad(BigDecimal idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public Localidad(BigDecimal idlocalidad, String nombre, String abrev, String cp) {
        this.idlocalidad = idlocalidad;
        this.nombre = nombre;
        this.abrev = abrev;
        this.cp = cp;
    }

    public BigDecimal getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(BigDecimal idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    public Provin getCodi08() {
        return codi08;
    }

    public void setCodi08(Provin codi08) {
        this.codi08 = codi08;
    }

    @XmlTransient
    public Collection<Calle> getCalleCollection() {
        return calleCollection;
    }

    public void setCalleCollection(Collection<Calle> calleCollection) {
        this.calleCollection = calleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocalidad != null ? idlocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.idlocalidad == null && other.idlocalidad != null) || (this.idlocalidad != null && !this.idlocalidad.equals(other.idlocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Localidad[ idlocalidad=" + idlocalidad + " ]";
    }

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }

    @XmlTransient
    public Collection<Vinedo> getVinedoCollection() {
        return vinedoCollection;
    }

    public void setVinedoCollection(Collection<Vinedo> vinedoCollection) {
        this.vinedoCollection = vinedoCollection;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @XmlTransient
    public List<Calle> getCalleList() {
        return calleList;
    }

    public void setCalleList(List<Calle> calleList) {
        this.calleList = calleList;
    }

    @XmlTransient
    public List<Domicilio> getDomicilioList() {
        return domicilioList;
    }

    public void setDomicilioList(List<Domicilio> domicilioList) {
        this.domicilioList = domicilioList;
    }
    
}
