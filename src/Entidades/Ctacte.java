/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "Ctacte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ctacte.findAll", query = "SELECT c FROM Ctacte c"),
    @NamedQuery(name = "Ctacte.findByItemCtacte", query = "SELECT c FROM Ctacte c WHERE c.ctactePK.itemCtacte = :itemCtacte"),
    @NamedQuery(name = "Ctacte.findByMovimientoCtacte", query = "SELECT c FROM Ctacte c WHERE c.ctactePK.movimientoCtacte = :movimientoCtacte"),
    @NamedQuery(name = "Ctacte.findByPeriodoCtacte", query = "SELECT c FROM Ctacte c WHERE c.ctactePK.periodoCtacte = :periodoCtacte"),
    @NamedQuery(name = "Ctacte.findByVerificadorCtacte", query = "SELECT c FROM Ctacte c WHERE c.ctactePK.verificadorCtacte = :verificadorCtacte"),
    @NamedQuery(name = "Ctacte.findByComprobante", query = "SELECT c FROM Ctacte c WHERE c.comprobante = :comprobante"),
    @NamedQuery(name = "Ctacte.findByExpediente", query = "SELECT c FROM Ctacte c WHERE c.expediente = :expediente"),
    @NamedQuery(name = "Ctacte.findByFechaGeneracion", query = "SELECT c FROM Ctacte c WHERE c.fechaGeneracion = :fechaGeneracion"),
    @NamedQuery(name = "Ctacte.findByFechaVencimiento", query = "SELECT c FROM Ctacte c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Ctacte.findByIdAsiento", query = "SELECT c FROM Ctacte c WHERE c.idAsiento = :idAsiento"),
    @NamedQuery(name = "Ctacte.findByImporte", query = "SELECT c FROM Ctacte c WHERE c.importe = :importe"),
    @NamedQuery(name = "Ctacte.findByObjetoiId", query = "SELECT c FROM Ctacte c WHERE c.ctactePK.objetoiId = :objetoiId"),
    @NamedQuery(name = "Ctacte.findByUsuariocauserK", query = "SELECT c FROM Ctacte c WHERE c.usuariocauserK = :usuariocauserK"),
    @NamedQuery(name = "Ctacte.findByTipoMovimiento", query = "SELECT c FROM Ctacte c WHERE c.tipoMovimiento = :tipoMovimiento")})
public class Ctacte implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CtactePK ctactePK;
    @Column(name = "comprobante")
    private String comprobante;
    @Column(name = "expediente")
    private String expediente;
    @Column(name = "fechaGeneracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "idAsiento")
    private BigInteger idAsiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @Column(name = "usuario_causerK")
    private String usuariocauserK;
    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;
    @JoinColumn(name = "tipomov_id", referencedColumnName = "id")
    @ManyToOne
    private Tipomov tipomovId;
    @JoinColumn(name = "objetoi_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Objetoi objetoi;
    @JoinColumn(name = "emideta_id", referencedColumnName = "id")
    @ManyToOne
    private Emideta emidetaId;
    @JoinColumn(name = "cuota_id", referencedColumnName = "id")
    @ManyToOne
    private Cuota cuotaId;
    @JoinColumn(name = "facturado_id", referencedColumnName = "id")
    @ManyToOne
    private Concepto facturadoId;
    @JoinColumn(name = "asociado_id", referencedColumnName = "id")
    @ManyToOne
    private Concepto asociadoId;
    @JoinColumn(name = "caratula_id", referencedColumnName = "id")
    @ManyToOne
    private Caratula caratulaId;
    @JoinColumns({
        @JoinColumn(name = "boleto_numeroBoleto", referencedColumnName = "numeroBoleto"),
        @JoinColumn(name = "boleto_periodoBoleto", referencedColumnName = "periodoBoleto"),
        @JoinColumn(name = "boleto_verificadorBoleto", referencedColumnName = "verificadorBoleto")})
    @ManyToOne
    private Boleto boleto;

    public Ctacte() {
    }

    public Ctacte(CtactePK ctactePK) {
        this.ctactePK = ctactePK;
    }

    public Ctacte(BigInteger itemCtacte, BigInteger movimientoCtacte, BigInteger periodoCtacte, BigInteger verificadorCtacte, BigInteger objetoiId) {
        this.ctactePK = new CtactePK(itemCtacte, movimientoCtacte, periodoCtacte, verificadorCtacte, objetoiId);
    }

    public CtactePK getCtactePK() {
        return ctactePK;
    }

    public void setCtactePK(CtactePK ctactePK) {
        this.ctactePK = ctactePK;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigInteger getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(BigInteger idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getUsuariocauserK() {
        return usuariocauserK;
    }

    public void setUsuariocauserK(String usuariocauserK) {
        this.usuariocauserK = usuariocauserK;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Tipomov getTipomovId() {
        return tipomovId;
    }

    public void setTipomovId(Tipomov tipomovId) {
        this.tipomovId = tipomovId;
    }

    public Objetoi getObjetoi() {
        return objetoi;
    }

    public void setObjetoi(Objetoi objetoi) {
        this.objetoi = objetoi;
    }

    public Emideta getEmidetaId() {
        return emidetaId;
    }

    public void setEmidetaId(Emideta emidetaId) {
        this.emidetaId = emidetaId;
    }

    public Cuota getCuotaId() {
        return cuotaId;
    }

    public void setCuotaId(Cuota cuotaId) {
        this.cuotaId = cuotaId;
    }

    public Concepto getFacturadoId() {
        return facturadoId;
    }

    public void setFacturadoId(Concepto facturadoId) {
        this.facturadoId = facturadoId;
    }

    public Concepto getAsociadoId() {
        return asociadoId;
    }

    public void setAsociadoId(Concepto asociadoId) {
        this.asociadoId = asociadoId;
    }

    public Caratula getCaratulaId() {
        return caratulaId;
    }

    public void setCaratulaId(Caratula caratulaId) {
        this.caratulaId = caratulaId;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctactePK != null ? ctactePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ctacte)) {
            return false;
        }
        Ctacte other = (Ctacte) object;
        if ((this.ctactePK == null && other.ctactePK != null) || (this.ctactePK != null && !this.ctactePK.equals(other.ctactePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Ctacte[ ctactePK=" + ctactePK + " ]";
    }
    
}
