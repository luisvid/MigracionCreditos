/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Indice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indice.findAll", query = "SELECT i FROM Indice i"),
    @NamedQuery(name = "Indice.findById", query = "SELECT i FROM Indice i WHERE i.id = :id"),
    @NamedQuery(name = "Indice.findByAplicaEn", query = "SELECT i FROM Indice i WHERE i.aplicaEn = :aplicaEn"),
    @NamedQuery(name = "Indice.findByDiasPromedio", query = "SELECT i FROM Indice i WHERE i.diasPromedio = :diasPromedio"),
    @NamedQuery(name = "Indice.findByNombre", query = "SELECT i FROM Indice i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Indice.findByTipo", query = "SELECT i FROM Indice i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "Indice.findByValorInicial", query = "SELECT i FROM Indice i WHERE i.valorInicial = :valorInicial"),
    @NamedQuery(name = "Indice.findByFechaBase", query = "SELECT i FROM Indice i WHERE i.fechaBase = :fechaBase")})
public class Indice implements Serializable {
    @Column(name =     "fechaBase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBase;
    @OneToMany(mappedBy = "indiceCompid")
    private Collection<Linea> lineaCollection;
    @OneToMany(mappedBy = "indicePunid")
    private Collection<Linea> lineaCollection1;
    @OneToMany(mappedBy = "indiceMorid")
    private Collection<Linea> lineaCollection2;
    @OneToMany(mappedBy = "indiceId")
    private Collection<BonTasaIndice> bonTasaIndiceCollection;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
 //   @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "aplicaEn")
    private String aplicaEn;
    @Column(name = "diasPromedio")
    private Integer diasPromedio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "valorInicial")
    private Double valorInicial;
    @OneToMany(mappedBy = "indiceId")
    private Collection<IndiceValor> indiceValorCollection;
    @OneToMany(mappedBy = "indiceId")
    private Collection<ObjetoiIndice> objetoiIndiceCollection;

    public Indice() {
    }

    public Indice(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAplicaEn() {
        return aplicaEn;
    }

    public void setAplicaEn(String aplicaEn) {
        this.aplicaEn = aplicaEn;
    }

    public Integer getDiasPromedio() {
        return diasPromedio;
    }

    public void setDiasPromedio(Integer diasPromedio) {
        this.diasPromedio = diasPromedio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    @XmlTransient
    public Collection<IndiceValor> getIndiceValorCollection() {
        return indiceValorCollection;
    }

    public void setIndiceValorCollection(Collection<IndiceValor> indiceValorCollection) {
        this.indiceValorCollection = indiceValorCollection;
    }

    @XmlTransient
    public Collection<ObjetoiIndice> getObjetoiIndiceCollection() {
        return objetoiIndiceCollection;
    }

    public void setObjetoiIndiceCollection(Collection<ObjetoiIndice> objetoiIndiceCollection) {
        this.objetoiIndiceCollection = objetoiIndiceCollection;
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
        if (!(object instanceof Indice)) {
            return false;
        }
        Indice other = (Indice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Indice[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BonTasaIndice> getBonTasaIndiceCollection() {
        return bonTasaIndiceCollection;
    }

    public void setBonTasaIndiceCollection(Collection<BonTasaIndice> bonTasaIndiceCollection) {
        this.bonTasaIndiceCollection = bonTasaIndiceCollection;
    }

    public Date getFechaBase() {
        return fechaBase;
    }

    public void setFechaBase(Date fechaBase) {
        this.fechaBase = fechaBase;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection() {
        return lineaCollection;
    }

    public void setLineaCollection(Collection<Linea> lineaCollection) {
        this.lineaCollection = lineaCollection;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection1() {
        return lineaCollection1;
    }

    public void setLineaCollection1(Collection<Linea> lineaCollection1) {
        this.lineaCollection1 = lineaCollection1;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection2() {
        return lineaCollection2;
    }

    public void setLineaCollection2(Collection<Linea> lineaCollection2) {
        this.lineaCollection2 = lineaCollection2;
    }
}
