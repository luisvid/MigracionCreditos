/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

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
@Table(name = "CREDITOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditos.findAll", query = "SELECT c FROM Creditos c"),
    //@NamedQuery(name = "Creditos.findAll", query = "SELECT c FROM Creditos c WHERE c.nuri < 1000000"),
    @NamedQuery(name = "Creditos.findByNuri", query = "SELECT c FROM Creditos c WHERE c.nuri = :nuri"),
    @NamedQuery(name = "Creditos.findByCodigo", query = "SELECT c FROM Creditos c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Creditos.findByImporteOriginal", query = "SELECT c FROM Creditos c WHERE c.importeOriginal = :importeOriginal"),
    @NamedQuery(name = "Creditos.findByCntCuotas", query = "SELECT c FROM Creditos c WHERE c.cntCuotas = :cntCuotas"),
    @NamedQuery(name = "Creditos.findByTasaBco", query = "SELECT c FROM Creditos c WHERE c.tasaBco = :tasaBco"),
    @NamedQuery(name = "Creditos.findByFecha", query = "SELECT c FROM Creditos c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Creditos.findByTasaSubsidio", query = "SELECT c FROM Creditos c WHERE c.tasaSubsidio = :tasaSubsidio"),
    @NamedQuery(name = "Creditos.findByBancoNuri", query = "SELECT c FROM Creditos c WHERE c.bancoNuri = :bancoNuri"),
    @NamedQuery(name = "Creditos.findByLineaCreditoNuri", query = "SELECT c FROM Creditos c WHERE c.lineaCreditoNuri = :lineaCreditoNuri"),
    @NamedQuery(name = "Creditos.findByPlazoGracia", query = "SELECT c FROM Creditos c WHERE c.plazoGracia = :plazoGracia"),
    @NamedQuery(name = "Creditos.findByPlazoAmort", query = "SELECT c FROM Creditos c WHERE c.plazoAmort = :plazoAmort"),
    @NamedQuery(name = "Creditos.findByPlazoTotal", query = "SELECT c FROM Creditos c WHERE c.plazoTotal = :plazoTotal"),
    @NamedQuery(name = "Creditos.findByPlanNuri", query = "SELECT c FROM Creditos c WHERE c.planNuri = :planNuri"),
    @NamedQuery(name = "Creditos.findBySistCalculo", query = "SELECT c FROM Creditos c WHERE c.sistCalculo = :sistCalculo"),
    @NamedQuery(name = "Creditos.findByCupoNuri", query = "SELECT c FROM Creditos c WHERE c.cupoNuri = :cupoNuri"),
    @NamedQuery(name = "Creditos.findByPeriodicidad", query = "SELECT c FROM Creditos c WHERE c.periodicidad = :periodicidad"),
    @NamedQuery(name = "Creditos.findByPeriodicidadInteres", query = "SELECT c FROM Creditos c WHERE c.periodicidadInteres = :periodicidadInteres"),
    @NamedQuery(name = "Creditos.findByFecPriCtaInt", query = "SELECT c FROM Creditos c WHERE c.fecPriCtaInt = :fecPriCtaInt"),
    @NamedQuery(name = "Creditos.findByTipoSumaDias", query = "SELECT c FROM Creditos c WHERE c.tipoSumaDias = :tipoSumaDias"),
    @NamedQuery(name = "Creditos.findByBonifiAdministradora", query = "SELECT c FROM Creditos c WHERE c.bonifiAdministradora = :bonifiAdministradora"),
    @NamedQuery(name = "Creditos.findByBonifiSepyme", query = "SELECT c FROM Creditos c WHERE c.bonifiSepyme = :bonifiSepyme"),
    @NamedQuery(name = "Creditos.findByTipoNuri", query = "SELECT c FROM Creditos c WHERE c.tipoNuri = :tipoNuri"),
    @NamedQuery(name = "Creditos.findBySectorNuri", query = "SELECT c FROM Creditos c WHERE c.sectorNuri = :sectorNuri"),
    @NamedQuery(name = "Creditos.findBySubtipoNuri", query = "SELECT c FROM Creditos c WHERE c.subtipoNuri = :subtipoNuri"),
    @NamedQuery(name = "Creditos.findByRecalcular", query = "SELECT c FROM Creditos c WHERE c.recalcular = :recalcular"),
    @NamedQuery(name = "Creditos.findByBonifiLey", query = "SELECT c FROM Creditos c WHERE c.bonifiLey = :bonifiLey")})
public class Creditos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NURI")
    private Integer nuri;
    @Column(name = "CODIGO")
    private String codigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "IMPORTE_ORIGINAL")
    private BigDecimal importeOriginal;
    @Column(name = "CNT_CUOTAS")
    private Integer cntCuotas;
    @Column(name = "TASA_BCO")
    private BigDecimal tasaBco;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "TASA_SUBSIDIO")
    private BigDecimal tasaSubsidio;
    @Column(name = "BANCO_NURI")
    private Integer bancoNuri;
    @Column(name = "LINEA_CREDITO_NURI")
    private Integer lineaCreditoNuri;
    @Column(name = "PLAZO_GRACIA")
    private Integer plazoGracia;
    @Column(name = "PLAZO_AMORT")
    private Integer plazoAmort;
    @Column(name = "PLAZO_TOTAL")
    private Integer plazoTotal;
    @Column(name = "PLAN_NURI")
    private Integer planNuri;
    @Column(name = "SIST_CALCULO")
    private Integer sistCalculo;
    @Column(name = "CUPO_NURI")
    private Integer cupoNuri;
    @Column(name = "PERIODICIDAD")
    private Integer periodicidad;
    @Column(name = "PERIODICIDAD_INTERES")
    private Integer periodicidadInteres;
    @Column(name = "FEC_PRI_CTA_INT")
    @Temporal(TemporalType.DATE)
    private Date fecPriCtaInt;
    @Column(name = "TIPO_SUMA_DIAS")
    private String tipoSumaDias;
    @Column(name = "BONIFI_ADMINISTRADORA")
    private String bonifiAdministradora;
    @Column(name = "BONIFI_SEPYME")
    private String bonifiSepyme;
    @Column(name = "TIPO_NURI")
    private Integer tipoNuri;
    @Column(name = "SECTOR_NURI")
    private Integer sectorNuri;
    @Column(name = "SUBTIPO_NURI")
    private Integer subtipoNuri;
    @Column(name = "RECALCULAR")
    private String recalcular;
    @Column(name = "BONIFI_LEY")
    private String bonifiLey;
    @JoinColumn(name = "PERS_NURI", referencedColumnName = "NURI")
    @ManyToOne(optional = false)
    private Personas persNuri;

    public Creditos() {
    }

    public Creditos(Integer nuri) {
        this.nuri = nuri;
    }

    public Integer getNuri() {
        return nuri;
    }

    public void setNuri(Integer nuri) {
        this.nuri = nuri;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getImporteOriginal() {
        return importeOriginal;
    }

    public void setImporteOriginal(BigDecimal importeOriginal) {
        this.importeOriginal = importeOriginal;
    }

    public Integer getCntCuotas() {
        return cntCuotas;
    }

    public void setCntCuotas(Integer cntCuotas) {
        this.cntCuotas = cntCuotas;
    }

    public BigDecimal getTasaBco() {
        return tasaBco;
    }

    public void setTasaBco(BigDecimal tasaBco) {
        this.tasaBco = tasaBco;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTasaSubsidio() {
        return tasaSubsidio;
    }

    public void setTasaSubsidio(BigDecimal tasaSubsidio) {
        this.tasaSubsidio = tasaSubsidio;
    }

    public Integer getBancoNuri() {
        return bancoNuri;
    }

    public void setBancoNuri(Integer bancoNuri) {
        this.bancoNuri = bancoNuri;
    }

    public Integer getLineaCreditoNuri() {
        return lineaCreditoNuri;
    }

    public void setLineaCreditoNuri(Integer lineaCreditoNuri) {
        this.lineaCreditoNuri = lineaCreditoNuri;
    }

    public Integer getPlazoGracia() {
        return plazoGracia;
    }

    public void setPlazoGracia(Integer plazoGracia) {
        this.plazoGracia = plazoGracia;
    }

    public Integer getPlazoAmort() {
        return plazoAmort;
    }

    public void setPlazoAmort(Integer plazoAmort) {
        this.plazoAmort = plazoAmort;
    }

    public Integer getPlazoTotal() {
        return plazoTotal;
    }

    public void setPlazoTotal(Integer plazoTotal) {
        this.plazoTotal = plazoTotal;
    }

    public Integer getPlanNuri() {
        return planNuri;
    }

    public void setPlanNuri(Integer planNuri) {
        this.planNuri = planNuri;
    }

    public Integer getSistCalculo() {
        return sistCalculo;
    }

    public void setSistCalculo(Integer sistCalculo) {
        this.sistCalculo = sistCalculo;
    }

    public Integer getCupoNuri() {
        return cupoNuri;
    }

    public void setCupoNuri(Integer cupoNuri) {
        this.cupoNuri = cupoNuri;
    }

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Integer getPeriodicidadInteres() {
        return periodicidadInteres;
    }

    public void setPeriodicidadInteres(Integer periodicidadInteres) {
        this.periodicidadInteres = periodicidadInteres;
    }

    public Date getFecPriCtaInt() {
        return fecPriCtaInt;
    }

    public void setFecPriCtaInt(Date fecPriCtaInt) {
        this.fecPriCtaInt = fecPriCtaInt;
    }

    public String getTipoSumaDias() {
        return tipoSumaDias;
    }

    public void setTipoSumaDias(String tipoSumaDias) {
        this.tipoSumaDias = tipoSumaDias;
    }

    public String getBonifiAdministradora() {
        return bonifiAdministradora;
    }

    public void setBonifiAdministradora(String bonifiAdministradora) {
        this.bonifiAdministradora = bonifiAdministradora;
    }

    public String getBonifiSepyme() {
        return bonifiSepyme;
    }

    public void setBonifiSepyme(String bonifiSepyme) {
        this.bonifiSepyme = bonifiSepyme;
    }

    public Integer getTipoNuri() {
        return tipoNuri;
    }

    public void setTipoNuri(Integer tipoNuri) {
        this.tipoNuri = tipoNuri;
    }

    public Integer getSectorNuri() {
        return sectorNuri;
    }

    public void setSectorNuri(Integer sectorNuri) {
        this.sectorNuri = sectorNuri;
    }

    public Integer getSubtipoNuri() {
        return subtipoNuri;
    }

    public void setSubtipoNuri(Integer subtipoNuri) {
        this.subtipoNuri = subtipoNuri;
    }

    public String getRecalcular() {
        return recalcular;
    }

    public void setRecalcular(String recalcular) {
        this.recalcular = recalcular;
    }

    public String getBonifiLey() {
        return bonifiLey;
    }

    public void setBonifiLey(String bonifiLey) {
        this.bonifiLey = bonifiLey;
    }

    public Personas getPersNuri() {
        return persNuri;
    }

    public void setPersNuri(Personas persNuri) {
        this.persNuri = persNuri;
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
        if (!(object instanceof Creditos)) {
            return false;
        }
        Creditos other = (Creditos) object;
        if ((this.nuri == null && other.nuri != null) || (this.nuri != null && !this.nuri.equals(other.nuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.Creditos[ nuri=" + nuri + " ]";
    }
    
}
