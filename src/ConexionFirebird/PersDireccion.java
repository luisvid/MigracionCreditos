/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import java.io.Serializable;
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
@Table(name = "PERS_DIRECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersDireccion.findAll", query = "SELECT p FROM PersDireccion p"),
    @NamedQuery(name = "PersDireccion.findByNuri", query = "SELECT p FROM PersDireccion p WHERE p.nuri = :nuri"),
    @NamedQuery(name = "PersDireccion.findByDomicilio", query = "SELECT p FROM PersDireccion p WHERE p.domicilio = :domicilio"),
    @NamedQuery(name = "PersDireccion.findByLocalidad", query = "SELECT p FROM PersDireccion p WHERE p.localidad = :localidad"),
    @NamedQuery(name = "PersDireccion.findByTelefono", query = "SELECT p FROM PersDireccion p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PersDireccion.findByFax", query = "SELECT p FROM PersDireccion p WHERE p.fax = :fax"),
    @NamedQuery(name = "PersDireccion.findByEmail", query = "SELECT p FROM PersDireccion p WHERE p.email = :email"),
    @NamedQuery(name = "PersDireccion.findByDeptoNuri", query = "SELECT p FROM PersDireccion p WHERE p.deptoNuri = :deptoNuri")})
public class PersDireccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NURI")
    private Integer nuri;
    @Basic(optional = false)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DEPTO_NURI")
    private Integer deptoNuri;
    @JoinColumn(name = "TIPO_DIR_NURI", referencedColumnName = "TIPO_DIR_NURI")
    @ManyToOne(optional = false)
    private TipoDirecciones tipoDirNuri;
    @JoinColumn(name = "PROV_NURI", referencedColumnName = "NURI")
    @ManyToOne
    private Provincias provNuri;
    @JoinColumn(name = "PERS_NURI", referencedColumnName = "NURI")
    @ManyToOne(optional = false)
    private Personas persNuri;
    @JoinColumn(name = "CP_NURI", referencedColumnName = "CP_NURI")
    @ManyToOne
    private CodigoPostal cpNuri;
    @OneToMany(mappedBy = "persDirNuri")
    private List<Personas> personasList;

    public PersDireccion() {
    }

    public PersDireccion(Integer nuri) {
        this.nuri = nuri;
    }

    public PersDireccion(Integer nuri, String domicilio) {
        this.nuri = nuri;
        this.domicilio = domicilio;
    }

    public Integer getNuri() {
        return nuri;
    }

    public void setNuri(Integer nuri) {
        this.nuri = nuri;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDeptoNuri() {
        return deptoNuri;
    }

    public void setDeptoNuri(Integer deptoNuri) {
        this.deptoNuri = deptoNuri;
    }

    public TipoDirecciones getTipoDirNuri() {
        return tipoDirNuri;
    }

    public void setTipoDirNuri(TipoDirecciones tipoDirNuri) {
        this.tipoDirNuri = tipoDirNuri;
    }

    public Provincias getProvNuri() {
        return provNuri;
    }

    public void setProvNuri(Provincias provNuri) {
        this.provNuri = provNuri;
    }

    public Personas getPersNuri() {
        return persNuri;
    }

    public void setPersNuri(Personas persNuri) {
        this.persNuri = persNuri;
    }

    public CodigoPostal getCpNuri() {
        return cpNuri;
    }

    public void setCpNuri(CodigoPostal cpNuri) {
        this.cpNuri = cpNuri;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nuri != null ? nuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersDireccion)) {
            return false;
        }
        PersDireccion other = (PersDireccion) object;
        if ((this.nuri == null && other.nuri != null) || (this.nuri != null && !this.nuri.equals(other.nuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.PersDireccion[ nuri=" + nuri + " ]";
    }
    
}
