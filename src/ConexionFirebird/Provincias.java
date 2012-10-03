/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "PROVINCIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p"),
    @NamedQuery(name = "Provincias.findByNuri", query = "SELECT p FROM Provincias p WHERE p.nuri = :nuri"),
    @NamedQuery(name = "Provincias.findByDescrip", query = "SELECT p FROM Provincias p WHERE p.descrip = :descrip"),
    @NamedQuery(name = "Provincias.findByCodigo", query = "SELECT p FROM Provincias p WHERE p.codigo = :codigo")})
public class Provincias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NURI")
    private Integer nuri;
    @Basic(optional = false)
    @Column(name = "DESCRIP")
    private String descrip;
    @Column(name = "CODIGO")
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provNuri")
    private List<CodigoPostal> codigoPostalList;
    @OneToMany(mappedBy = "provNuri")
    private List<PersDireccion> persDireccionList;

    public Provincias() {
    }

    public Provincias(Integer nuri) {
        this.nuri = nuri;
    }

    public Provincias(Integer nuri, String descrip) {
        this.nuri = nuri;
        this.descrip = descrip;
    }

    public Integer getNuri() {
        return nuri;
    }

    public void setNuri(Integer nuri) {
        this.nuri = nuri;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public List<CodigoPostal> getCodigoPostalList() {
        return codigoPostalList;
    }

    public void setCodigoPostalList(List<CodigoPostal> codigoPostalList) {
        this.codigoPostalList = codigoPostalList;
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
        hash += (nuri != null ? nuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincias)) {
            return false;
        }
        Provincias other = (Provincias) object;
        if ((this.nuri == null && other.nuri != null) || (this.nuri != null && !this.nuri.equals(other.nuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.Provincias[ nuri=" + nuri + " ]";
    }
    
}
