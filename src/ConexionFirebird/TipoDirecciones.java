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
@Table(name = "TIPO_DIRECCIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDirecciones.findAll", query = "SELECT t FROM TipoDirecciones t"),
    @NamedQuery(name = "TipoDirecciones.findByTipoDirNuri", query = "SELECT t FROM TipoDirecciones t WHERE t.tipoDirNuri = :tipoDirNuri"),
    @NamedQuery(name = "TipoDirecciones.findByDescrip", query = "SELECT t FROM TipoDirecciones t WHERE t.descrip = :descrip")})
public class TipoDirecciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPO_DIR_NURI")
    private Integer tipoDirNuri;
    @Basic(optional = false)
    @Column(name = "DESCRIP")
    private String descrip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDirNuri")
    private List<PersDireccion> persDireccionList;

    public TipoDirecciones() {
    }

    public TipoDirecciones(Integer tipoDirNuri) {
        this.tipoDirNuri = tipoDirNuri;
    }

    public TipoDirecciones(Integer tipoDirNuri, String descrip) {
        this.tipoDirNuri = tipoDirNuri;
        this.descrip = descrip;
    }

    public Integer getTipoDirNuri() {
        return tipoDirNuri;
    }

    public void setTipoDirNuri(Integer tipoDirNuri) {
        this.tipoDirNuri = tipoDirNuri;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
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
        hash += (tipoDirNuri != null ? tipoDirNuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDirecciones)) {
            return false;
        }
        TipoDirecciones other = (TipoDirecciones) object;
        if ((this.tipoDirNuri == null && other.tipoDirNuri != null) || (this.tipoDirNuri != null && !this.tipoDirNuri.equals(other.tipoDirNuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.TipoDirecciones[ tipoDirNuri=" + tipoDirNuri + " ]";
    }
    
}
