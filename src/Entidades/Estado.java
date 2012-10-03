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
@Table(name = "Estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByIdEstado", query = "SELECT e FROM Estado e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estado.findByColor", query = "SELECT e FROM Estado e WHERE e.color = :color"),
    @NamedQuery(name = "Estado.findByNombreEstado", query = "SELECT e FROM Estado e WHERE e.nombreEstado = :nombreEstado"),
    @NamedQuery(name = "Estado.findByTipo", query = "SELECT e FROM Estado e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Estado.findByManual", query = "SELECT e FROM Estado e WHERE e.manual = :manual"),
    @NamedQuery(name = "Estado.findByIgnorarInstanciaJudicial", query = "SELECT e FROM Estado e WHERE e.ignorarInstanciaJudicial = :ignorarInstanciaJudicial")})
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "idEstado")
    private BigDecimal idEstado;
    @Column(name = "color")
    private String color;
    @Column(name = "nombreEstado")
    private String nombreEstado;
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "manual")
    private short manual;
    @Column(name = "ignorarInstanciaJudicial")
    private Short ignorarInstanciaJudicial;
    @OneToMany(mappedBy = "estadoidEstado")
    private Collection<ObjetoiEstado> objetoiEstadoCollection;

    public Estado() {
    }

    public Estado(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    public Estado(BigDecimal idEstado, short manual) {
        this.idEstado = idEstado;
        this.manual = manual;
    }

    public BigDecimal getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public short getManual() {
        return manual;
    }

    public void setManual(short manual) {
        this.manual = manual;
    }

    public Short getIgnorarInstanciaJudicial() {
        return ignorarInstanciaJudicial;
    }

    public void setIgnorarInstanciaJudicial(Short ignorarInstanciaJudicial) {
        this.ignorarInstanciaJudicial = ignorarInstanciaJudicial;
    }

    @XmlTransient
    public Collection<ObjetoiEstado> getObjetoiEstadoCollection() {
        return objetoiEstadoCollection;
    }

    public void setObjetoiEstadoCollection(Collection<ObjetoiEstado> objetoiEstadoCollection) {
        this.objetoiEstadoCollection = objetoiEstadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Estado[ idEstado=" + idEstado + " ]";
    }
    
}
