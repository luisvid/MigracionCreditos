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
@Table(name = "Objetoi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetoi.findAll", query = "SELECT o FROM Objetoi o"),
    @NamedQuery(name = "Objetoi.findById", query = "SELECT o FROM Objetoi o WHERE o.id = :id"),
    @NamedQuery(name = "Objetoi.findByAporteAgente", query = "SELECT o FROM Objetoi o WHERE o.aporteAgente = :aporteAgente"),
    @NamedQuery(name = "Objetoi.findByAporteCofinanciador", query = "SELECT o FROM Objetoi o WHERE o.aporteCofinanciador = :aporteCofinanciador"),
    @NamedQuery(name = "Objetoi.findByAportePropio", query = "SELECT o FROM Objetoi o WHERE o.aportePropio = :aportePropio"),
    @NamedQuery(name = "Objetoi.findByExpediente", query = "SELECT o FROM Objetoi o WHERE o.expediente = :expediente"),
    @NamedQuery(name = "Objetoi.findByFechaExpediente", query = "SELECT o FROM Objetoi o WHERE o.fechaExpediente = :fechaExpediente"),
    @NamedQuery(name = "Objetoi.findByFechaMutuo", query = "SELECT o FROM Objetoi o WHERE o.fechaMutuo = :fechaMutuo"),
    @NamedQuery(name = "Objetoi.findByFechaResolucion", query = "SELECT o FROM Objetoi o WHERE o.fechaResolucion = :fechaResolucion"),
    @NamedQuery(name = "Objetoi.findByFechaSolicitud", query = "SELECT o FROM Objetoi o WHERE o.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Objetoi.findByFinanciamiento", query = "SELECT o FROM Objetoi o WHERE o.financiamiento = :financiamiento"),
    @NamedQuery(name = "Objetoi.findByFrecuenciaCapital", query = "SELECT o FROM Objetoi o WHERE o.frecuenciaCapital = :frecuenciaCapital"),
    @NamedQuery(name = "Objetoi.findByFrecuenciaInteres", query = "SELECT o FROM Objetoi o WHERE o.frecuenciaInteres = :frecuenciaInteres"),
    @NamedQuery(name = "Objetoi.findByInicioActividad", query = "SELECT o FROM Objetoi o WHERE o.inicioActividad = :inicioActividad"),
    @NamedQuery(name = "Objetoi.findByMontoTotal", query = "SELECT o FROM Objetoi o WHERE o.montoTotal = :montoTotal"),
    @NamedQuery(name = "Objetoi.findByNumeroAtencion", query = "SELECT o FROM Objetoi o WHERE o.numeroAtencion = :numeroAtencion"),
    @NamedQuery(name = "Objetoi.findByNumeroCredito", query = "SELECT o FROM Objetoi o WHERE o.numeroCredito = :numeroCredito"),
    @NamedQuery(name = "Objetoi.findByObjeto", query = "SELECT o FROM Objetoi o WHERE o.objeto = :objeto"),
    @NamedQuery(name = "Objetoi.findByObservaciones", query = "SELECT o FROM Objetoi o WHERE o.observaciones = :observaciones"),
    @NamedQuery(name = "Objetoi.findByOtrosAportes", query = "SELECT o FROM Objetoi o WHERE o.otrosAportes = :otrosAportes"),
    @NamedQuery(name = "Objetoi.findByPeriodoVolVtaAnual", query = "SELECT o FROM Objetoi o WHERE o.periodoVolVtaAnual = :periodoVolVtaAnual"),
    @NamedQuery(name = "Objetoi.findByPlazoCapital", query = "SELECT o FROM Objetoi o WHERE o.plazoCapital = :plazoCapital"),
    @NamedQuery(name = "Objetoi.findByPlazoCompensatorio", query = "SELECT o FROM Objetoi o WHERE o.plazoCompensatorio = :plazoCompensatorio"),
    @NamedQuery(name = "Objetoi.findByPorcentajeGastosCuota", query = "SELECT o FROM Objetoi o WHERE o.porcentajeGastosCuota = :porcentajeGastosCuota"),
    @NamedQuery(name = "Objetoi.findByPrimerVencCapital", query = "SELECT o FROM Objetoi o WHERE o.primerVencCapital = :primerVencCapital"),
    @NamedQuery(name = "Objetoi.findByPrimerVencInteres", query = "SELECT o FROM Objetoi o WHERE o.primerVencInteres = :primerVencInteres"),
    @NamedQuery(name = "Objetoi.findByNroCatastral", query = "SELECT o FROM Objetoi o WHERE o.nroCatastral = :nroCatastral"),
    @NamedQuery(name = "Objetoi.findByNroEstabRural", query = "SELECT o FROM Objetoi o WHERE o.nroEstabRural = :nroEstabRural"),
    @NamedQuery(name = "Objetoi.findByNroIrrigacion", query = "SELECT o FROM Objetoi o WHERE o.nroIrrigacion = :nroIrrigacion"),
    @NamedQuery(name = "Objetoi.findByResolucion", query = "SELECT o FROM Objetoi o WHERE o.resolucion = :resolucion"),
    @NamedQuery(name = "Objetoi.findByTipoAmortizacion", query = "SELECT o FROM Objetoi o WHERE o.tipoAmortizacion = :tipoAmortizacion"),
    @NamedQuery(name = "Objetoi.findByVencimiento", query = "SELECT o FROM Objetoi o WHERE o.vencimiento = :vencimiento"),
    @NamedQuery(name = "Objetoi.findByVolumenVtaAnual", query = "SELECT o FROM Objetoi o WHERE o.volumenVtaAnual = :volumenVtaAnual"),
    @NamedQuery(name = "Objetoi.findByIdAgente", query = "SELECT o FROM Objetoi o WHERE o.idAgente = :idAgente"),
    @NamedQuery(name = "Objetoi.findByAsesorcauserK", query = "SELECT o FROM Objetoi o WHERE o.asesorcauserK = :asesorcauserK"),
    @NamedQuery(name = "Objetoi.findByIdCofinanciador", query = "SELECT o FROM Objetoi o WHERE o.idCofinanciador = :idCofinanciador"),
    @NamedQuery(name = "Objetoi.findByDelegacionId", query = "SELECT o FROM Objetoi o WHERE o.delegacionId = :delegacionId"),
    @NamedQuery(name = "Objetoi.findByMonedaIDMONEDA", query = "SELECT o FROM Objetoi o WHERE o.monedaIDMONEDA = :monedaIDMONEDA"),
    @NamedQuery(name = "Objetoi.findByCuitAuthValores1", query = "SELECT o FROM Objetoi o WHERE o.cuitAuthValores1 = :cuitAuthValores1"),
    @NamedQuery(name = "Objetoi.findByCuitAuthValores2", query = "SELECT o FROM Objetoi o WHERE o.cuitAuthValores2 = :cuitAuthValores2"),
    @NamedQuery(name = "Objetoi.findByNombreAuthValores1", query = "SELECT o FROM Objetoi o WHERE o.nombreAuthValores1 = :nombreAuthValores1"),
    @NamedQuery(name = "Objetoi.findByNombreAuthValores2", query = "SELECT o FROM Objetoi o WHERE o.nombreAuthValores2 = :nombreAuthValores2"),
    @NamedQuery(name = "Objetoi.findByDestino", query = "SELECT o FROM Objetoi o WHERE o.destino = :destino"),
    @NamedQuery(name = "Objetoi.findByFechaCosecha", query = "SELECT o FROM Objetoi o WHERE o.fechaCosecha = :fechaCosecha"),
    @NamedQuery(name = "Objetoi.findByFecovita", query = "SELECT o FROM Objetoi o WHERE o.fecovita = :fecovita"),
    @NamedQuery(name = "Objetoi.findByNumeroAtencionStr", query = "SELECT o FROM Objetoi o WHERE o.numeroAtencionStr = :numeroAtencionStr"),
    @NamedQuery(name = "Objetoi.findByBonificacion", query = "SELECT o FROM Objetoi o WHERE o.bonificacion = :bonificacion"),
    @NamedQuery(name = "Objetoi.findByCantHas", query = "SELECT o FROM Objetoi o WHERE o.cantHas = :cantHas"),
    @NamedQuery(name = "Objetoi.findByNroINV", query = "SELECT o FROM Objetoi o WHERE o.nroINV = :nroINV"),
    @NamedQuery(name = "Objetoi.findByQuintalesEstimados", query = "SELECT o FROM Objetoi o WHERE o.quintalesEstimados = :quintalesEstimados"),
    @NamedQuery(name = "Objetoi.findByQuintalesIngresados", query = "SELECT o FROM Objetoi o WHERE o.quintalesIngresados = :quintalesIngresados"),
    @NamedQuery(name = "Objetoi.findByTipoCultivo", query = "SELECT o FROM Objetoi o WHERE o.tipoCultivo = :tipoCultivo"),
    @NamedQuery(name = "Objetoi.findByCantPersonal", query = "SELECT o FROM Objetoi o WHERE o.cantPersonal = :cantPersonal"),
    @NamedQuery(name = "Objetoi.findByFechaCalculo", query = "SELECT o FROM Objetoi o WHERE o.fechaCalculo = :fechaCalculo"),
    @NamedQuery(name = "Objetoi.findByFinanciamientoMaximo", query = "SELECT o FROM Objetoi o WHERE o.financiamientoMaximo = :financiamientoMaximo"),
    @NamedQuery(name = "Objetoi.findByQqsolicitado", query = "SELECT o FROM Objetoi o WHERE o.qqsolicitado = :qqsolicitado"),
    @NamedQuery(name = "Objetoi.findByTipoEmpresa", query = "SELECT o FROM Objetoi o WHERE o.tipoEmpresa = :tipoEmpresa"),
    @NamedQuery(name = "Objetoi.findByTipoSector", query = "SELECT o FROM Objetoi o WHERE o.tipoSector = :tipoSector"),
    @NamedQuery(name = "Objetoi.findByUltimaCuotaEmitida", query = "SELECT o FROM Objetoi o WHERE o.ultimaCuotaEmitida = :ultimaCuotaEmitida"),
    @NamedQuery(name = "Objetoi.findByFechaMutuoFin", query = "SELECT o FROM Objetoi o WHERE o.fechaMutuoFin = :fechaMutuoFin"),
    @NamedQuery(name = "Objetoi.findByFechaResolFin", query = "SELECT o FROM Objetoi o WHERE o.fechaResolFin = :fechaResolFin"),
    @NamedQuery(name = "Objetoi.findByNroResolFin", query = "SELECT o FROM Objetoi o WHERE o.nroResolFin = :nroResolFin"),
    @NamedQuery(name = "Objetoi.findByFechaFirmaFinalizacion", query = "SELECT o FROM Objetoi o WHERE o.fechaFirmaFinalizacion = :fechaFirmaFinalizacion"),
    @NamedQuery(name = "Objetoi.findByFechaFirmaResolucion", query = "SELECT o FROM Objetoi o WHERE o.fechaFirmaResolucion = :fechaFirmaResolucion"),
    @NamedQuery(name = "Objetoi.findByNumeroResolucion", query = "SELECT o FROM Objetoi o WHERE o.numeroResolucion = :numeroResolucion")})
public class Objetoi implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "aporteAgente")
    private Double aporteAgente;
    @Column(name = "aporteCofinanciador")
    private Double aporteCofinanciador;
    @Column(name = "aportePropio")
    private Double aportePropio;
    @Column(name = "expediente")
    private String expediente;
    @Column(name = "fechaExpediente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpediente;
    @Column(name = "fechaMutuo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMutuo;
    @Column(name = "fechaResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    @Column(name = "fechaSolicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "financiamiento")
    private Double financiamiento;
    @Column(name = "frecuenciaCapital")
    private String frecuenciaCapital;
    @Column(name = "frecuenciaInteres")
    private String frecuenciaInteres;
    @Column(name = "inicioActividad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioActividad;
    @Column(name = "montoTotal")
    private Double montoTotal;
    @Column(name = "numeroAtencion")
    private BigInteger numeroAtencion;
    @Column(name = "numeroCredito")
    private String numeroCredito;
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "otrosAportes")
    private Double otrosAportes;
    @Column(name = "periodoVolVtaAnual")
    private String periodoVolVtaAnual;
    @Column(name = "plazoCapital")
    private Integer plazoCapital;
    @Column(name = "plazoCompensatorio")
    private Integer plazoCompensatorio;
    @Column(name = "porcentajeGastosCuota")
    private Double porcentajeGastosCuota;
    @Column(name = "primerVencCapital")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencCapital;
    @Column(name = "primerVencInteres")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencInteres;
    @Column(name = "nroCatastral")
    private String nroCatastral;
    @Column(name = "nroEstabRural")
    private String nroEstabRural;
    @Column(name = "nroIrrigacion")
    private String nroIrrigacion;
    @Column(name = "resolucion")
    private String resolucion;
    @Column(name = "tipoAmortizacion")
    private String tipoAmortizacion;
    @Column(name = "vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimiento;
    @Column(name = "volumenVtaAnual")
    private Double volumenVtaAnual;
    @Column(name = "idAgente")
    private BigInteger idAgente;
    @Column(name = "asesor_causerK")
    private String asesorcauserK;
    @Column(name = "idCofinanciador")
    private BigInteger idCofinanciador;
    @Column(name = "delegacion_id")
    private BigInteger delegacionId;
    @Column(name = "moneda_IDMONEDA")
    private BigInteger monedaIDMONEDA;
    @Column(name = "cuitAuthValores1")
    private String cuitAuthValores1;
    @Column(name = "cuitAuthValores2")
    private String cuitAuthValores2;
    @Column(name = "nombreAuthValores1")
    private String nombreAuthValores1;
    @Column(name = "nombreAuthValores2")
    private String nombreAuthValores2;
    @Column(name = "destino")
    private String destino;
    @Column(name = "fechaCosecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCosecha;
    @Column(name = "fecovita")
    private Integer fecovita;
    @Column(name = "numeroAtencionStr")
    private String numeroAtencionStr;
    @Column(name = "bonificacion")
    private Double bonificacion;
    @Column(name = "cantHas")
    private Integer cantHas;
    @Column(name = "nroINV")
    private String nroINV;
    @Column(name = "quintalesEstimados")
    private Integer quintalesEstimados;
    @Column(name = "quintalesIngresados")
    private Integer quintalesIngresados;
    @Column(name = "tipoCultivo")
    private String tipoCultivo;
    @Column(name = "cantPersonal")
    private Integer cantPersonal;
    @Column(name = "fechaCalculo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalculo;
    @Column(name = "financiamientoMaximo")
    private Double financiamientoMaximo;
    @Column(name = "qqsolicitado")
    private Double qqsolicitado;
    @Column(name = "tipoEmpresa")
    private String tipoEmpresa;
    @Column(name = "tipoSector")
    private String tipoSector;
    @Column(name = "ultimaCuotaEmitida")
    private Integer ultimaCuotaEmitida;
    @Column(name = "fechaMutuoFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMutuoFin;
    @Column(name = "fechaResolFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolFin;
    @Column(name = "nroResolFin")
    private Integer nroResolFin;
    @Column(name = "fechaFirmaFinalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirmaFinalizacion;
    @Column(name = "fechaFirmaResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirmaResolucion;
    @Column(name = "numeroResolucion")
    private String numeroResolucion;
    @OneToMany(mappedBy = "idCredito")
    private Collection<ObjetoiBonificacion> objetoiBonificacionCollection;
    @JoinColumn(name = "turno_id", referencedColumnName = "id")
    @ManyToOne
    private Turno turnoId;
    @JoinColumn(name = "subTipoLinea_id", referencedColumnName = "id")
    @ManyToOne
    private SubTipoLinea subTipoLineaid;
    @JoinColumn(name = "persona_IDPERSONA", referencedColumnName = "IDPERSONA")
    @ManyToOne
    private Persona personaIDPERSONA;
    @OneToMany(mappedBy = "acuerdoPagoid")
    private Collection<Objetoi> objetoiCollection;
    @JoinColumn(name = "acuerdoPago_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi acuerdoPagoid;
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    @ManyToOne
    private Linea lineaId;
    @JoinColumn(name = "procesoAprobacion_ID_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessinstance procesoAprobacionID;
    @OneToMany(mappedBy = "creditoId")
    private Collection<Desembolso> desembolsoCollection;
    @OneToMany(mappedBy = "creditoId")
    private Collection<ObjetoiIndice> objetoiIndiceCollection;

    public Objetoi() {
    }

    public Objetoi(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getAporteAgente() {
        return aporteAgente;
    }

    public void setAporteAgente(Double aporteAgente) {
        this.aporteAgente = aporteAgente;
    }

    public Double getAporteCofinanciador() {
        return aporteCofinanciador;
    }

    public void setAporteCofinanciador(Double aporteCofinanciador) {
        this.aporteCofinanciador = aporteCofinanciador;
    }

    public Double getAportePropio() {
        return aportePropio;
    }

    public void setAportePropio(Double aportePropio) {
        this.aportePropio = aportePropio;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Date getFechaExpediente() {
        return fechaExpediente;
    }

    public void setFechaExpediente(Date fechaExpediente) {
        this.fechaExpediente = fechaExpediente;
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

    public Double getFinanciamiento() {
        return financiamiento;
    }

    public void setFinanciamiento(Double financiamiento) {
        this.financiamiento = financiamiento;
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

    public Date getInicioActividad() {
        return inicioActividad;
    }

    public void setInicioActividad(Date inicioActividad) {
        this.inicioActividad = inicioActividad;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigInteger getNumeroAtencion() {
        return numeroAtencion;
    }

    public void setNumeroAtencion(BigInteger numeroAtencion) {
        this.numeroAtencion = numeroAtencion;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
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

    public Double getOtrosAportes() {
        return otrosAportes;
    }

    public void setOtrosAportes(Double otrosAportes) {
        this.otrosAportes = otrosAportes;
    }

    public String getPeriodoVolVtaAnual() {
        return periodoVolVtaAnual;
    }

    public void setPeriodoVolVtaAnual(String periodoVolVtaAnual) {
        this.periodoVolVtaAnual = periodoVolVtaAnual;
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

    public Double getPorcentajeGastosCuota() {
        return porcentajeGastosCuota;
    }

    public void setPorcentajeGastosCuota(Double porcentajeGastosCuota) {
        this.porcentajeGastosCuota = porcentajeGastosCuota;
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

    public String getNroCatastral() {
        return nroCatastral;
    }

    public void setNroCatastral(String nroCatastral) {
        this.nroCatastral = nroCatastral;
    }

    public String getNroEstabRural() {
        return nroEstabRural;
    }

    public void setNroEstabRural(String nroEstabRural) {
        this.nroEstabRural = nroEstabRural;
    }

    public String getNroIrrigacion() {
        return nroIrrigacion;
    }

    public void setNroIrrigacion(String nroIrrigacion) {
        this.nroIrrigacion = nroIrrigacion;
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

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Double getVolumenVtaAnual() {
        return volumenVtaAnual;
    }

    public void setVolumenVtaAnual(Double volumenVtaAnual) {
        this.volumenVtaAnual = volumenVtaAnual;
    }

    public BigInteger getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(BigInteger idAgente) {
        this.idAgente = idAgente;
    }

    public String getAsesorcauserK() {
        return asesorcauserK;
    }

    public void setAsesorcauserK(String asesorcauserK) {
        this.asesorcauserK = asesorcauserK;
    }

    public BigInteger getIdCofinanciador() {
        return idCofinanciador;
    }

    public void setIdCofinanciador(BigInteger idCofinanciador) {
        this.idCofinanciador = idCofinanciador;
    }

    public BigInteger getDelegacionId() {
        return delegacionId;
    }

    public void setDelegacionId(BigInteger delegacionId) {
        this.delegacionId = delegacionId;
    }

    public BigInteger getMonedaIDMONEDA() {
        return monedaIDMONEDA;
    }

    public void setMonedaIDMONEDA(BigInteger monedaIDMONEDA) {
        this.monedaIDMONEDA = monedaIDMONEDA;
    }

    public String getCuitAuthValores1() {
        return cuitAuthValores1;
    }

    public void setCuitAuthValores1(String cuitAuthValores1) {
        this.cuitAuthValores1 = cuitAuthValores1;
    }

    public String getCuitAuthValores2() {
        return cuitAuthValores2;
    }

    public void setCuitAuthValores2(String cuitAuthValores2) {
        this.cuitAuthValores2 = cuitAuthValores2;
    }

    public String getNombreAuthValores1() {
        return nombreAuthValores1;
    }

    public void setNombreAuthValores1(String nombreAuthValores1) {
        this.nombreAuthValores1 = nombreAuthValores1;
    }

    public String getNombreAuthValores2() {
        return nombreAuthValores2;
    }

    public void setNombreAuthValores2(String nombreAuthValores2) {
        this.nombreAuthValores2 = nombreAuthValores2;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(Date fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public Integer getFecovita() {
        return fecovita;
    }

    public void setFecovita(Integer fecovita) {
        this.fecovita = fecovita;
    }

    public String getNumeroAtencionStr() {
        return numeroAtencionStr;
    }

    public void setNumeroAtencionStr(String numeroAtencionStr) {
        this.numeroAtencionStr = numeroAtencionStr;
    }

    public Double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Double bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Integer getCantHas() {
        return cantHas;
    }

    public void setCantHas(Integer cantHas) {
        this.cantHas = cantHas;
    }

    public String getNroINV() {
        return nroINV;
    }

    public void setNroINV(String nroINV) {
        this.nroINV = nroINV;
    }

    public Integer getQuintalesEstimados() {
        return quintalesEstimados;
    }

    public void setQuintalesEstimados(Integer quintalesEstimados) {
        this.quintalesEstimados = quintalesEstimados;
    }

    public Integer getQuintalesIngresados() {
        return quintalesIngresados;
    }

    public void setQuintalesIngresados(Integer quintalesIngresados) {
        this.quintalesIngresados = quintalesIngresados;
    }

    public String getTipoCultivo() {
        return tipoCultivo;
    }

    public void setTipoCultivo(String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }

    public Integer getCantPersonal() {
        return cantPersonal;
    }

    public void setCantPersonal(Integer cantPersonal) {
        this.cantPersonal = cantPersonal;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public Double getFinanciamientoMaximo() {
        return financiamientoMaximo;
    }

    public void setFinanciamientoMaximo(Double financiamientoMaximo) {
        this.financiamientoMaximo = financiamientoMaximo;
    }

    public Double getQqsolicitado() {
        return qqsolicitado;
    }

    public void setQqsolicitado(Double qqsolicitado) {
        this.qqsolicitado = qqsolicitado;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getTipoSector() {
        return tipoSector;
    }

    public void setTipoSector(String tipoSector) {
        this.tipoSector = tipoSector;
    }

    public Integer getUltimaCuotaEmitida() {
        return ultimaCuotaEmitida;
    }

    public void setUltimaCuotaEmitida(Integer ultimaCuotaEmitida) {
        this.ultimaCuotaEmitida = ultimaCuotaEmitida;
    }

    public Date getFechaMutuoFin() {
        return fechaMutuoFin;
    }

    public void setFechaMutuoFin(Date fechaMutuoFin) {
        this.fechaMutuoFin = fechaMutuoFin;
    }

    public Date getFechaResolFin() {
        return fechaResolFin;
    }

    public void setFechaResolFin(Date fechaResolFin) {
        this.fechaResolFin = fechaResolFin;
    }

    public Integer getNroResolFin() {
        return nroResolFin;
    }

    public void setNroResolFin(Integer nroResolFin) {
        this.nroResolFin = nroResolFin;
    }

    public Date getFechaFirmaFinalizacion() {
        return fechaFirmaFinalizacion;
    }

    public void setFechaFirmaFinalizacion(Date fechaFirmaFinalizacion) {
        this.fechaFirmaFinalizacion = fechaFirmaFinalizacion;
    }

    public Date getFechaFirmaResolucion() {
        return fechaFirmaResolucion;
    }

    public void setFechaFirmaResolucion(Date fechaFirmaResolucion) {
        this.fechaFirmaResolucion = fechaFirmaResolucion;
    }

    public String getNumeroResolucion() {
        return numeroResolucion;
    }

    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    @XmlTransient
    public Collection<ObjetoiBonificacion> getObjetoiBonificacionCollection() {
        return objetoiBonificacionCollection;
    }

    public void setObjetoiBonificacionCollection(Collection<ObjetoiBonificacion> objetoiBonificacionCollection) {
        this.objetoiBonificacionCollection = objetoiBonificacionCollection;
    }

    public Turno getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Turno turnoId) {
        this.turnoId = turnoId;
    }

    public SubTipoLinea getSubTipoLineaid() {
        return subTipoLineaid;
    }

    public void setSubTipoLineaid(SubTipoLinea subTipoLineaid) {
        this.subTipoLineaid = subTipoLineaid;
    }

    public Persona getPersonaIDPERSONA() {
        return personaIDPERSONA;
    }

    public void setPersonaIDPERSONA(Persona personaIDPERSONA) {
        this.personaIDPERSONA = personaIDPERSONA;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
    }

    public Objetoi getAcuerdoPagoid() {
        return acuerdoPagoid;
    }

    public void setAcuerdoPagoid(Objetoi acuerdoPagoid) {
        this.acuerdoPagoid = acuerdoPagoid;
    }

    public Linea getLineaId() {
        return lineaId;
    }

    public void setLineaId(Linea lineaId) {
        this.lineaId = lineaId;
    }

    public JbpmProcessinstance getProcesoAprobacionID() {
        return procesoAprobacionID;
    }

    public void setProcesoAprobacionID(JbpmProcessinstance procesoAprobacionID) {
        this.procesoAprobacionID = procesoAprobacionID;
    }

    @XmlTransient
    public Collection<Desembolso> getDesembolsoCollection() {
        return desembolsoCollection;
    }

    public void setDesembolsoCollection(Collection<Desembolso> desembolsoCollection) {
        this.desembolsoCollection = desembolsoCollection;
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
        if (!(object instanceof Objetoi)) {
            return false;
        }
        Objetoi other = (Objetoi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Objetoi[ id=" + id + " ]";
    }
    
}
