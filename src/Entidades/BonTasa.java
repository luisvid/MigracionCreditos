/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "BonTasa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonTasa.findAll", query = "SELECT b FROM BonTasa b"),
    @NamedQuery(name = "BonTasa.findById", query = "SELECT b FROM BonTasa b WHERE b.id = :id"),
    @NamedQuery(name = "BonTasa.findByAsesor", query = "SELECT b FROM BonTasa b WHERE b.asesor = :asesor"),
    @NamedQuery(name = "BonTasa.findByCertifNormas", query = "SELECT b FROM BonTasa b WHERE b.certifNormas = :certifNormas"),
    @NamedQuery(name = "BonTasa.findByCondicionamientos", query = "SELECT b FROM BonTasa b WHERE b.condicionamientos = :condicionamientos"),
    @NamedQuery(name = "BonTasa.findByExpediente", query = "SELECT b FROM BonTasa b WHERE b.expediente = :expediente"),
    @NamedQuery(name = "BonTasa.findByFacturacionMercExterno", query = "SELECT b FROM BonTasa b WHERE b.facturacionMercExterno = :facturacionMercExterno"),
    @NamedQuery(name = "BonTasa.findByFechaDesembolso", query = "SELECT b FROM BonTasa b WHERE b.fechaDesembolso = :fechaDesembolso"),
    @NamedQuery(name = "BonTasa.findByFechaMutuo", query = "SELECT b FROM BonTasa b WHERE b.fechaMutuo = :fechaMutuo"),
    @NamedQuery(name = "BonTasa.findByFechaResolucion", query = "SELECT b FROM BonTasa b WHERE b.fechaResolucion = :fechaResolucion"),
    @NamedQuery(name = "BonTasa.findByFechaSolicitud", query = "SELECT b FROM BonTasa b WHERE b.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "BonTasa.findByFrecuenciaCapital", query = "SELECT b FROM BonTasa b WHERE b.frecuenciaCapital = :frecuenciaCapital"),
    @NamedQuery(name = "BonTasa.findByFrecuenciaInteres", query = "SELECT b FROM BonTasa b WHERE b.frecuenciaInteres = :frecuenciaInteres"),
    @NamedQuery(name = "BonTasa.findByHasBeneficiadas", query = "SELECT b FROM BonTasa b WHERE b.hasBeneficiadas = :hasBeneficiadas"),
    @NamedQuery(name = "BonTasa.findByInicioActivid", query = "SELECT b FROM BonTasa b WHERE b.inicioActivid = :inicioActivid"),
    @NamedQuery(name = "BonTasa.findByInicioActividad", query = "SELECT b FROM BonTasa b WHERE b.inicioActividad = :inicioActividad"),
    @NamedQuery(name = "BonTasa.findByMontoFinanciamiento", query = "SELECT b FROM BonTasa b WHERE b.montoFinanciamiento = :montoFinanciamiento"),
    @NamedQuery(name = "BonTasa.findByNumeroBonificacion", query = "SELECT b FROM BonTasa b WHERE b.numeroBonificacion = :numeroBonificacion"),
    @NamedQuery(name = "BonTasa.findByObjeto", query = "SELECT b FROM BonTasa b WHERE b.objeto = :objeto"),
    @NamedQuery(name = "BonTasa.findByObservaciones", query = "SELECT b FROM BonTasa b WHERE b.observaciones = :observaciones"),
    @NamedQuery(name = "BonTasa.findByPeriodoVolVtaAnual", query = "SELECT b FROM BonTasa b WHERE b.periodoVolVtaAnual = :periodoVolVtaAnual"),
    @NamedQuery(name = "BonTasa.findByPersonalOcupado", query = "SELECT b FROM BonTasa b WHERE b.personalOcupado = :personalOcupado"),
    @NamedQuery(name = "BonTasa.findByPlazoCapital", query = "SELECT b FROM BonTasa b WHERE b.plazoCapital = :plazoCapital"),
    @NamedQuery(name = "BonTasa.findByPlazoCompensatorio", query = "SELECT b FROM BonTasa b WHERE b.plazoCompensatorio = :plazoCompensatorio"),
    @NamedQuery(name = "BonTasa.findByPrimerVencCapital", query = "SELECT b FROM BonTasa b WHERE b.primerVencCapital = :primerVencCapital"),
    @NamedQuery(name = "BonTasa.findByPrimerVencInteres", query = "SELECT b FROM BonTasa b WHERE b.primerVencInteres = :primerVencInteres"),
    @NamedQuery(name = "BonTasa.findByQqCosechados", query = "SELECT b FROM BonTasa b WHERE b.qqCosechados = :qqCosechados"),
    @NamedQuery(name = "BonTasa.findByResolucion", query = "SELECT b FROM BonTasa b WHERE b.resolucion = :resolucion"),
    @NamedQuery(name = "BonTasa.findByTipoAmortizacion", query = "SELECT b FROM BonTasa b WHERE b.tipoAmortizacion = :tipoAmortizacion"),
    @NamedQuery(name = "BonTasa.findByTipoEmpresa", query = "SELECT b FROM BonTasa b WHERE b.tipoEmpresa = :tipoEmpresa"),
    @NamedQuery(name = "BonTasa.findByTipoEstadoBonTasa", query = "SELECT b FROM BonTasa b WHERE b.tipoEstadoBonTasa = :tipoEstadoBonTasa"),
    @NamedQuery(name = "BonTasa.findByVencimiento", query = "SELECT b FROM BonTasa b WHERE b.vencimiento = :vencimiento"),
    @NamedQuery(name = "BonTasa.findByVolumenVtaAnual", query = "SELECT b FROM BonTasa b WHERE b.volumenVtaAnual = :volumenVtaAnual"),
    @NamedQuery(name = "BonTasa.findByMonedaIDMONEDA", query = "SELECT b FROM BonTasa b WHERE b.monedaIDMONEDA = :monedaIDMONEDA"),
    @NamedQuery(name = "BonTasa.findByUnidadId", query = "SELECT b FROM BonTasa b WHERE b.unidadId = :unidadId"),
    @NamedQuery(name = "BonTasa.findByBancoCODIBA", query = "SELECT b FROM BonTasa b WHERE b.bancoCODIBA = :bancoCODIBA")})
public class BonTasa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "asesor")
    private String asesor;
    @Column(name = "certifNormas")
    private String certifNormas;
    @Column(name = "condicionamientos")
    private String condicionamientos;
    @Lob
    @Column(name = "ente")
    private byte[] ente;
    @Column(name = "expediente")
    private String expediente;
    @Column(name = "facturacionMercExterno")
    private Double facturacionMercExterno;
    @Column(name = "fechaDesembolso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesembolso;
    @Column(name = "fechaMutuo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMutuo;
    @Column(name = "fechaResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    @Column(name = "fechaSolicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "frecuenciaCapital")
    private String frecuenciaCapital;
    @Column(name = "frecuenciaInteres")
    private String frecuenciaInteres;
    @Column(name = "hasBeneficiadas")
    private Double hasBeneficiadas;
    @Column(name = "inicioActivid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioActivid;
    @Column(name = "inicioActividad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioActividad;
    @Column(name = "montoFinanciamiento")
    private Double montoFinanciamiento;
    @Column(name = "numeroBonificacion")
    private BigInteger numeroBonificacion;
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "periodoVolVtaAnual")
    private Integer periodoVolVtaAnual;
    @Column(name = "personalOcupado")
    private Integer personalOcupado;
    @Column(name = "plazoCapital")
    private Integer plazoCapital;
    @Column(name = "plazoCompensatorio")
    private Integer plazoCompensatorio;
    @Column(name = "primerVencCapital")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencCapital;
    @Column(name = "primerVencInteres")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencInteres;
    @Column(name = "qqCosechados")
    private Double qqCosechados;
    @Column(name = "resolucion")
    private String resolucion;
    @Column(name = "tipoAmortizacion")
    private String tipoAmortizacion;
    @Column(name = "tipoEmpresa")
    private BigInteger tipoEmpresa;
    @Column(name = "tipoEstadoBonTasa")
    private String tipoEstadoBonTasa;
    @Column(name = "vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimiento;
    @Column(name = "volumenVtaAnual")
    private BigInteger volumenVtaAnual;
    @Column(name = "moneda_IDMONEDA")
    private BigInteger monedaIDMONEDA;
    @Column(name = "unidad_id")
    private BigInteger unidadId;
    @Column(name = "banco_CODI_BA")
    private BigInteger bancoCODIBA;
    @OneToMany(mappedBy = "bonTasaid")
    private Collection<BonTasaEstado> bonTasaEstadoCollection;
    @OneToMany(mappedBy = "bonTasaid")
    private Collection<TitularesBonTasa> titularesBonTasaCollection;
    @JoinColumn(name = "turno_id", referencedColumnName = "id")
    @ManyToOne
    private Turno turnoId;
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    @ManyToOne
    private Sector sectorId;
    @JoinColumn(name = "persona_IDPERSONA", referencedColumnName = "IDPERSONA")
    @ManyToOne
    private Persona personaIDPERSONA;
    @OneToMany(mappedBy = "bonTasaid")
    private Collection<BonTasaIndice> bonTasaIndiceCollection;
    @OneToMany(mappedBy = "bonTasaid")
    private Collection<CuotaBonTasa> cuotaBonTasaCollection;

    public BonTasa() {
    }

    public BonTasa(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getCertifNormas() {
        return certifNormas;
    }

    public void setCertifNormas(String certifNormas) {
        this.certifNormas = certifNormas;
    }

    public String getCondicionamientos() {
        return condicionamientos;
    }

    public void setCondicionamientos(String condicionamientos) {
        this.condicionamientos = condicionamientos;
    }

    public byte[] getEnte() {
        return ente;
    }

    public void setEnte(byte[] ente) {
        this.ente = ente;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Double getFacturacionMercExterno() {
        return facturacionMercExterno;
    }

    public void setFacturacionMercExterno(Double facturacionMercExterno) {
        this.facturacionMercExterno = facturacionMercExterno;
    }

    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(Date fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public Date getFechaMutuo() {
        return fechaMutuo;
    }

    public void setFechaMutuo(Date fechaMutuo) {
        this.fechaMutuo = fechaMutuo;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getFrecuenciaCapital() {
        return frecuenciaCapital;
    }

    public void setFrecuenciaCapital(String frecuenciaCapital) {
        this.frecuenciaCapital = frecuenciaCapital;
    }

    public String getFrecuenciaInteres() {
        return frecuenciaInteres;
    }

    public void setFrecuenciaInteres(String frecuenciaInteres) {
        this.frecuenciaInteres = frecuenciaInteres;
    }

    public Double getHasBeneficiadas() {
        return hasBeneficiadas;
    }

    public void setHasBeneficiadas(Double hasBeneficiadas) {
        this.hasBeneficiadas = hasBeneficiadas;
    }

    public Date getInicioActivid() {
        return inicioActivid;
    }

    public void setInicioActivid(Date inicioActivid) {
        this.inicioActivid = inicioActivid;
    }

    public Date getInicioActividad() {
        return inicioActividad;
    }

    public void setInicioActividad(Date inicioActividad) {
        this.inicioActividad = inicioActividad;
    }

    public Double getMontoFinanciamiento() {
        return montoFinanciamiento;
    }

    public void setMontoFinanciamiento(Double montoFinanciamiento) {
        this.montoFinanciamiento = montoFinanciamiento;
    }

    public BigInteger getNumeroBonificacion() {
        return numeroBonificacion;
    }

    public void setNumeroBonificacion(BigInteger numeroBonificacion) {
        this.numeroBonificacion = numeroBonificacion;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getPeriodoVolVtaAnual() {
        return periodoVolVtaAnual;
    }

    public void setPeriodoVolVtaAnual(Integer periodoVolVtaAnual) {
        this.periodoVolVtaAnual = periodoVolVtaAnual;
    }

    public Integer getPersonalOcupado() {
        return personalOcupado;
    }

    public void setPersonalOcupado(Integer personalOcupado) {
        this.personalOcupado = personalOcupado;
    }

    public Integer getPlazoCapital() {
        return plazoCapital;
    }

    public void setPlazoCapital(Integer plazoCapital) {
        this.plazoCapital = plazoCapital;
    }

    public Integer getPlazoCompensatorio() {
        return plazoCompensatorio;
    }

    public void setPlazoCompensatorio(Integer plazoCompensatorio) {
        this.plazoCompensatorio = plazoCompensatorio;
    }

    public Date getPrimerVencCapital() {
        return primerVencCapital;
    }

    public void setPrimerVencCapital(Date primerVencCapital) {
        this.primerVencCapital = primerVencCapital;
    }

    public Date getPrimerVencInteres() {
        return primerVencInteres;
    }

    public void setPrimerVencInteres(Date primerVencInteres) {
        this.primerVencInteres = primerVencInteres;
    }

    public Double getQqCosechados() {
        return qqCosechados;
    }

    public void setQqCosechados(Double qqCosechados) {
        this.qqCosechados = qqCosechados;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getTipoAmortizacion() {
        return tipoAmortizacion;
    }

    public void setTipoAmortizacion(String tipoAmortizacion) {
        this.tipoAmortizacion = tipoAmortizacion;
    }

    public BigInteger getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(BigInteger tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getTipoEstadoBonTasa() {
        return tipoEstadoBonTasa;
    }

    public void setTipoEstadoBonTasa(String tipoEstadoBonTasa) {
        this.tipoEstadoBonTasa = tipoEstadoBonTasa;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public BigInteger getVolumenVtaAnual() {
        return volumenVtaAnual;
    }

    public void setVolumenVtaAnual(BigInteger volumenVtaAnual) {
        this.volumenVtaAnual = volumenVtaAnual;
    }

    public BigInteger getMonedaIDMONEDA() {
        return monedaIDMONEDA;
    }

    public void setMonedaIDMONEDA(BigInteger monedaIDMONEDA) {
        this.monedaIDMONEDA = monedaIDMONEDA;
    }

    public BigInteger getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(BigInteger unidadId) {
        this.unidadId = unidadId;
    }

    public BigInteger getBancoCODIBA() {
        return bancoCODIBA;
    }

    public void setBancoCODIBA(BigInteger bancoCODIBA) {
        this.bancoCODIBA = bancoCODIBA;
    }

    @XmlTransient
    public Collection<BonTasaEstado> getBonTasaEstadoCollection() {
        return bonTasaEstadoCollection;
    }

    public void setBonTasaEstadoCollection(Collection<BonTasaEstado> bonTasaEstadoCollection) {
        this.bonTasaEstadoCollection = bonTasaEstadoCollection;
    }

    @XmlTransient
    public Collection<TitularesBonTasa> getTitularesBonTasaCollection() {
        return titularesBonTasaCollection;
    }

    public void setTitularesBonTasaCollection(Collection<TitularesBonTasa> titularesBonTasaCollection) {
        this.titularesBonTasaCollection = titularesBonTasaCollection;
    }

    public Turno getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Turno turnoId) {
        this.turnoId = turnoId;
    }

    public Sector getSectorId() {
        return sectorId;
    }

    public void setSectorId(Sector sectorId) {
        this.sectorId = sectorId;
    }

    public Persona getPersonaIDPERSONA() {
        return personaIDPERSONA;
    }

    public void setPersonaIDPERSONA(Persona personaIDPERSONA) {
        this.personaIDPERSONA = personaIDPERSONA;
    }

    @XmlTransient
    public Collection<BonTasaIndice> getBonTasaIndiceCollection() {
        return bonTasaIndiceCollection;
    }

    public void setBonTasaIndiceCollection(Collection<BonTasaIndice> bonTasaIndiceCollection) {
        this.bonTasaIndiceCollection = bonTasaIndiceCollection;
    }

    @XmlTransient
    public Collection<CuotaBonTasa> getCuotaBonTasaCollection() {
        return cuotaBonTasaCollection;
    }

    public void setCuotaBonTasaCollection(Collection<CuotaBonTasa> cuotaBonTasaCollection) {
        this.cuotaBonTasaCollection = cuotaBonTasaCollection;
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
        if (!(object instanceof BonTasa)) {
            return false;
        }
        BonTasa other = (BonTasa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BonTasa[ id=" + id + " ]";
    }
    
}
