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
@Table(name = "ObjetoiBonificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetoiBonificacion.findAll", query = "SELECT o FROM ObjetoiBonificacion o"),
    @NamedQuery(name = "ObjetoiBonificacion.findById", query = "SELECT o FROM ObjetoiBonificacion o WHERE o.id = :id"),
    @NamedQuery(name = "ObjetoiBonificacion.findByCantidadCuotas", query = "SELECT o FROM ObjetoiBonificacion o WHERE o.cantidadCuotas = :cantidadCuotas"),
     @NamedQuery(name = "ObjetoiBonificacion.findByCredito_id", query = "SELECT c FROM ObjetoiBonificacion c WHERE c.idCredito = :Objetoi"),
    @NamedQuery(name = "ObjetoiBonificacion.findByFechaCaducidad", query = "SELECT o FROM ObjetoiBonificacion o WHERE o.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "ObjetoiBonificacion.findByValor", query = "SELECT o FROM ObjetoiBonificacion o WHERE o.valor = :valor")})
public class ObjetoiBonificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "cantidadCuotas")
    private Integer cantidadCuotas;
    @Column(name = "fechaCaducidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    @Column(name = "valor")
    private Double valor;
    @JoinColumn(name = "idCredito", referencedColumnName = "id")
    @ManyToOne
    private Objetoi idCredito;
    @JoinColumn(name = "idBonificacion", referencedColumnName = "id")
    @ManyToOne
    private Bonificacion idBonificacion;

    public ObjetoiBonificacion() {
    }

    public ObjetoiBonificacion(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(Integer cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Objetoi getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(Objetoi idCredito) {
        this.idCredito = idCredito;
    }

    public Bonificacion getIdBonificacion() {
        return idBonificacion;
    }

    public void setIdBonificacion(Bonificacion idBonificacion) {
        this.idBonificacion = idBonificacion;
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
        if (!(object instanceof ObjetoiBonificacion)) {
            return false;
        }
        ObjetoiBonificacion other = (ObjetoiBonificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ObjetoiBonificacion[ id=" + id + " ]";
    }
    
}
