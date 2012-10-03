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
@Table(name = "CODIGO_POSTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CodigoPostal.findAll", query = "SELECT c FROM CodigoPostal c"),
    @NamedQuery(name = "CodigoPostal.findByCpNuri", query = "SELECT c FROM CodigoPostal c WHERE c.cpNuri = :cpNuri"),
    @NamedQuery(name = "CodigoPostal.findByCodPostal", query = "SELECT c FROM CodigoPostal c WHERE c.codPostal = :codPostal"),
    @NamedQuery(name = "CodigoPostal.findByLocalidad", query = "SELECT c FROM CodigoPostal c WHERE c.localidad = :localidad")})
public class CodigoPostal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CP_NURI")
    private Integer cpNuri;
    @Basic(optional = false)
    @Column(name = "COD_POSTAL")
    private String codPostal;
    @Basic(optional = false)
    @Column(name = "LOCALIDAD")
    private String localidad;
    @JoinColumn(name = "PROV_NURI", referencedColumnName = "NURI")
    @ManyToOne(optional = false)
    private Provincias provNuri;
    @OneToMany(mappedBy = "cpNuri")
    private List<PersDireccion> persDireccionList;

    public CodigoPostal() {
    }

    public CodigoPostal(Integer cpNuri) {
        this.cpNuri = cpNuri;
    }

    public CodigoPostal(Integer cpNuri, String codPostal, String localidad) {
        this.cpNuri = cpNuri;
        this.codPostal = codPostal;
        this.localidad = localidad;
    }

    public Integer getCpNuri() {
        return cpNuri;
    }

    public void setCpNuri(Integer cpNuri) {
        this.cpNuri = cpNuri;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Provincias getProvNuri() {
        return provNuri;
    }

    public void setProvNuri(Provincias provNuri) {
        this.provNuri = provNuri;
    }

    @XmlTransient
    public List<PersDireccion> getPersDireccionList() {
        return persDireccionList;
    }

    public void setPersDireccionList(List<PersDireccion> persDireccionList) {
        this.persDireccionList = persDireccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpNuri != null ? cpNuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodigoPostal)) {
            return false;
        }
        CodigoPostal other = (CodigoPostal) object;
        if ((this.cpNuri == null && other.cpNuri != null) || (this.cpNuri != null && !this.cpNuri.equals(other.cpNuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.CodigoPostal[ cpNuri=" + cpNuri + " ]";
    }
    
}
