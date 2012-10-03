/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Boleto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b"),
    @NamedQuery(name = "Boleto.findByNumeroBoleto", query = "SELECT b FROM Boleto b WHERE b.boletoPK.numeroBoleto = :numeroBoleto"),
    @NamedQuery(name = "Boleto.findByPeriodoBoleto", query = "SELECT b FROM Boleto b WHERE b.boletoPK.periodoBoleto = :periodoBoleto"),
    @NamedQuery(name = "Boleto.findByVerificadorBoleto", query = "SELECT b FROM Boleto b WHERE b.boletoPK.verificadorBoleto = :verificadorBoleto"),
    @NamedQuery(name = "Boleto.findByFechaEmision", query = "SELECT b FROM Boleto b WHERE b.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Boleto.findByFechaVencimiento", query = "SELECT b FROM Boleto b WHERE b.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Boleto.findByImporte", query = "SELECT b FROM Boleto b WHERE b.importe = :importe"),
    @NamedQuery(name = "Boleto.findByImporteVencido", query = "SELECT b FROM Boleto b WHERE b.importeVencido = :importeVencido"),
    @NamedQuery(name = "Boleto.findByTipo", query = "SELECT b FROM Boleto b WHERE b.tipo = :tipo"),
    @NamedQuery(name = "Boleto.findByUsuariocauserK", query = "SELECT b FROM Boleto b WHERE b.usuariocauserK = :usuariocauserK")})
public class Boleto implements Serializable {
    @Column(name =     "fechaEmision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Column(name =     "fechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BoletoPK boletoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @Column(name = "importeVencido")
    private Double importeVencido;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "usuario_causerK")
    private String usuariocauserK;
    @JoinColumn(name = "objetoi_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi objetoiId;
    @OneToMany(mappedBy = "boleto")
    private Collection<Emideta> emidetaCollection;
    @OneToMany(mappedBy = "boleto")
    private Collection<Ctacte> ctacteCollection;

    public Boleto() {
    }

    public Boleto(BoletoPK boletoPK) {
        this.boletoPK = boletoPK;
    }

    public Boleto(BigInteger numeroBoleto, BigInteger periodoBoleto, BigInteger verificadorBoleto) {
        this.boletoPK = new BoletoPK(numeroBoleto, periodoBoleto, verificadorBoleto);
    }

    public BoletoPK getBoletoPK() {
        return boletoPK;
    }

    public void setBoletoPK(BoletoPK boletoPK) {
        this.boletoPK = boletoPK;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getImporteVencido() {
        return importeVencido;
    }

    public void setImporteVencido(Double importeVencido) {
        this.importeVencido = importeVencido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuariocauserK() {
        return usuariocauserK;
    }

    public void setUsuariocauserK(String usuariocauserK) {
        this.usuariocauserK = usuariocauserK;
    }

    public Objetoi getObjetoiId() {
        return objetoiId;
    }

    public void setObjetoiId(Objetoi objetoiId) {
        this.objetoiId = objetoiId;
    }

    @XmlTransient
    public Collection<Emideta> getEmidetaCollection() {
        return emidetaCollection;
    }

    public void setEmidetaCollection(Collection<Emideta> emidetaCollection) {
        this.emidetaCollection = emidetaCollection;
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
        hash += (boletoPK != null ? boletoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.boletoPK == null && other.boletoPK != null) || (this.boletoPK != null && !this.boletoPK.equals(other.boletoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Boleto[ boletoPK=" + boletoPK + " ]";
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
}
