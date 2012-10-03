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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "Emideta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emideta.findAll", query = "SELECT e FROM Emideta e"),
    @NamedQuery(name = "Emideta.findById", query = "SELECT e FROM Emideta e WHERE e.id = :id"),
    @NamedQuery(name = "Emideta.findByActualizada", query = "SELECT e FROM Emideta e WHERE e.actualizada = :actualizada"),
    @NamedQuery(name = "Emideta.findByCapital", query = "SELECT e FROM Emideta e WHERE e.capital = :capital"),
    @NamedQuery(name = "Emideta.findByCompensatorio", query = "SELECT e FROM Emideta e WHERE e.compensatorio = :compensatorio"),
    @NamedQuery(name = "Emideta.findByMoratorio", query = "SELECT e FROM Emideta e WHERE e.moratorio = :moratorio"),
    @NamedQuery(name = "Emideta.findByNumero", query = "SELECT e FROM Emideta e WHERE e.numero = :numero"),
    @NamedQuery(name = "Emideta.findByPunitorio", query = "SELECT e FROM Emideta e WHERE e.punitorio = :punitorio"),
    @NamedQuery(name = "Emideta.findByTasaCompensatorio", query = "SELECT e FROM Emideta e WHERE e.tasaCompensatorio = :tasaCompensatorio"),
    @NamedQuery(name = "Emideta.findByTasaMoratorio", query = "SELECT e FROM Emideta e WHERE e.tasaMoratorio = :tasaMoratorio"),
    @NamedQuery(name = "Emideta.findByTasaPunitorio", query = "SELECT e FROM Emideta e WHERE e.tasaPunitorio = :tasaPunitorio"),
    @NamedQuery(name = "Emideta.findByBonificacion", query = "SELECT e FROM Emideta e WHERE e.bonificacion = :bonificacion"),
    @NamedQuery(name = "Emideta.findByGastos", query = "SELECT e FROM Emideta e WHERE e.gastos = :gastos"),
    @NamedQuery(name = "Emideta.findByMultas", query = "SELECT e FROM Emideta e WHERE e.multas = :multas")})
public class Emideta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "actualizada")
    private Short actualizada;
    @Column(name = "capital")
    private Double capital;
    @Column(name = "compensatorio")
    private Double compensatorio;
    @Column(name = "moratorio")
    private Double moratorio;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "punitorio")
    private Double punitorio;
    @Column(name = "tasaCompensatorio")
    private Double tasaCompensatorio;
    @Column(name = "tasaMoratorio")
    private Double tasaMoratorio;
    @Column(name = "tasaPunitorio")
    private Double tasaPunitorio;
    @Column(name = "bonificacion")
    private Double bonificacion;
    @Column(name = "gastos")
    private Double gastos;
    @Column(name = "multas")
    private Double multas;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;
    @JoinColumn(name = "emision_id", referencedColumnName = "id")
    @ManyToOne
    private Emision emisionId;
    @JoinColumns({
        @JoinColumn(name = "boleto_numeroBoleto", referencedColumnName = "numeroBoleto"),
        @JoinColumn(name = "boleto_periodoBoleto", referencedColumnName = "periodoBoleto"),
        @JoinColumn(name = "boleto_verificadorBoleto", referencedColumnName = "verificadorBoleto")})
    @ManyToOne
    private Boleto boleto;
    @OneToMany(mappedBy = "emidetaId")
    private Collection<Ctacte> ctacteCollection;

    public Emideta() {
    }

    public Emideta(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Short getActualizada() {
        return actualizada;
    }

    public void setActualizada(Short actualizada) {
        this.actualizada = actualizada;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getCompensatorio() {
        return compensatorio;
    }

    public void setCompensatorio(Double compensatorio) {
        this.compensatorio = compensatorio;
    }

    public Double getMoratorio() {
        return moratorio;
    }

    public void setMoratorio(Double moratorio) {
        this.moratorio = moratorio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getPunitorio() {
        return punitorio;
    }

    public void setPunitorio(Double punitorio) {
        this.punitorio = punitorio;
    }

    public Double getTasaCompensatorio() {
        return tasaCompensatorio;
    }

    public void setTasaCompensatorio(Double tasaCompensatorio) {
        this.tasaCompensatorio = tasaCompensatorio;
    }

    public Double getTasaMoratorio() {
        return tasaMoratorio;
    }

    public void setTasaMoratorio(Double tasaMoratorio) {
        this.tasaMoratorio = tasaMoratorio;
    }

    public Double getTasaPunitorio() {
        return tasaPunitorio;
    }

    public void setTasaPunitorio(Double tasaPunitorio) {
        this.tasaPunitorio = tasaPunitorio;
    }

    public Double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Double bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Double getGastos() {
        return gastos;
    }

    public void setGastos(Double gastos) {
        this.gastos = gastos;
    }

    public Double getMultas() {
        return multas;
    }

    public void setMultas(Double multas) {
        this.multas = multas;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
    }

    public Emision getEmisionId() {
        return emisionId;
    }

    public void setEmisionId(Emision emisionId) {
        this.emisionId = emisionId;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection() {
        return ctacteCollection;
    }

    public void setCtacteCollection(Collection<Ctacte> ctacteCollection) {
        this.ctacteCollection = ctacteCollection;
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
        if (!(object instanceof Emideta)) {
            return false;
        }
        Emideta other = (Emideta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Emideta[ id=" + id + " ]";
    }
    
}
