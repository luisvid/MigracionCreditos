/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "IndiceValor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndiceValor.findAll", query = "SELECT i FROM IndiceValor i"),
    @NamedQuery(name = "IndiceValor.findById", query = "SELECT i FROM IndiceValor i WHERE i.id = :id"),
    @NamedQuery(name = "IndiceValor.findByCantidad", query = "SELECT i FROM IndiceValor i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "IndiceValor.findByFecha", query = "SELECT i FROM IndiceValor i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "IndiceValor.findByImporte", query = "SELECT i FROM IndiceValor i WHERE i.importe = :importe"),
    @NamedQuery(name = "IndiceValor.findByObservaciones", query = "SELECT i FROM IndiceValor i WHERE i.observaciones = :observaciones"),
    @NamedQuery(name = "IndiceValor.findByValor", query = "SELECT i FROM IndiceValor i WHERE i.valor = :valor")})
public class IndiceValor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "valor")
    private Double valor;
    @JoinColumn(name = "indice_id", referencedColumnName = "id")
    @ManyToOne
    private Indice indiceId;

    public IndiceValor() {
    }

    public IndiceValor(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Indice getIndiceId() {
        return indiceId;
    }

    public void setIndiceId(Indice indiceId) {
        this.indiceId = indiceId;
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
        if (!(object instanceof IndiceValor)) {
            return false;
        }
        IndiceValor other = (IndiceValor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.IndiceValor[ id=" + id + " ]";
    }
    
}
