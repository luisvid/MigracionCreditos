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
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Objetoi.findByBonificacion", query = "SELECT o FROM Objetoi o WHERE o.bonificacion = :bonificacion"),
    @NamedQuery(name = "Objetoi.findByCantHas", query = "SELECT o FROM Objetoi o WHERE o.cantHas = :cantHas"),
    @NamedQuery(name = "Objetoi.findByNroINV", query = "SELECT o FROM Objetoi o WHERE o.nroINV = :nroINV"),
    @NamedQuery(name = "Objetoi.findByQuintalesEstimados", query = "SELECT o FROM Objetoi o WHERE o.quintalesEstimados = :quintalesEstimados"),
    @NamedQuery(name = "Objetoi.findByQuintalesIngresados", query = "SELECT o FROM Objetoi o WHERE o.quintalesIngresados = :quintalesIngresados"),
    @NamedQuery(name = "Objetoi.findByTipoCultivo", query = "SELECT o FROM Objetoi o WHERE o.tipoCultivo = :tipoCultivo"),
    @NamedQuery(name = "Objetoi.findByCantPersonal", query = "SELECT o FROM Objetoi o WHERE o.cantPersonal = :cantPersonal"),
    @NamedQuery(name = "Objetoi.findByQqsolicitado", query = "SELECT o FROM Objetoi o WHERE o.qqsolicitado = :qqsolicitado"),
    @NamedQuery(name = "Objetoi.findByTipoEmpresa", query = "SELECT o FROM Objetoi o WHERE o.tipoEmpresa = :tipoEmpresa"),
    @NamedQuery(name = "Objetoi.findByUltimaCuotaEmitida", query = "SELECT o FROM Objetoi o WHERE o.ultimaCuotaEmitida = :ultimaCuotaEmitida"),
    @NamedQuery(name = "Objetoi.findByFechaFirmaFinalizacion", query = "SELECT o FROM Objetoi o WHERE o.fechaFirmaFinalizacion = :fechaFirmaFinalizacion"),
    @NamedQuery(name = "Objetoi.findByFechaFirmaResolucion", query = "SELECT o FROM Objetoi o WHERE o.fechaFirmaResolucion = :fechaFirmaResolucion"),
    @NamedQuery(name = "Objetoi.findByNumeroResolucion", query = "SELECT o FROM Objetoi o WHERE o.numeroResolucion = :numeroResolucion")})
public class Objetoi implements Serializable {

    @Column(name =     "cantMateria")
    private String cantMateria;
    @Column(name =     "cotizaContrato")
    private Double cotizaContrato;
    @Column(name =     "cotizaInicial")
    private Double cotizaInicial;
    @Column(name =     "cotizaResol")
    private Double cotizaResol;
    @Column(name =     "esMiPyme")
    private Short esMiPyme;
    @Column(name =     "fechaAnalisisPatrimonial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnalisisPatrimonial;
    @Column(name =     "fechaAsesoriaLetrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsesoriaLetrada;
    @Column(name =     "fechaCosecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCosecha;
    @Column(name =     "fechaExpediente")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpediente;
    @Column(name =     "fechaFirmaContrato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirmaContrato;
    @Column(name =     "fechaFirmaFinalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirmaFinalizacion;
    @Column(name = "fechaFirmaResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirmaResolucion;
    @Column(name = "fechaInhibicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInhibicion;
    @Column(name = "fechaMutuo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMutuo;
    @Column(name = "fechaResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaResolucion;
    @Column(name = "fechaSolicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "fechaVerificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVerificacion;
    @Column(name = "fecovita")
    private Short fecovita;
    @Column(name = "financiamientoSol")
    private Double financiamientoSol;
    @Column(name = "gastosElaboracion")
    private String gastosElaboracion;
    @Column(name = "inicioActividad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioActividad;
    @Column(name = "observacionAnalisisPatrimonial")
    private String observacionAnalisisPatrimonial;
    @Column(name = "observacionAsesoriaLetrada")
    private String observacionAsesoriaLetrada;
    @Column(name = "observacionInhibicion")
    private String observacionInhibicion;
    @Column(name = "observacionVerificacion")
    private String observacionVerificacion;
    @Column(name = "precioMateria")
    private String precioMateria;
    @Column(name = "primerVencCapital")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencCapital;
    @Column(name = "primerVencInteres")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primerVencInteres;
    @Column(name = "qqFinal")
    private Double qqFinal;
    @Column(name = "taxiDominio")
    private String taxiDominio;
    @Column(name = "taxiMarca")
    private String taxiMarca;
    @Column(name = "taxiModelo")
    private String taxiModelo;
    @Column(name = "tipoCult")
    private String tipoCult;
    @Column(name = "totalBon")
    private Double totalBon;
    @Column(name = "valorCartera")
    private Short valorCartera;
    @Column(name = "vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimiento;
    @JoinColumn(name = "caducidadPlazo_id", referencedColumnName = "id")
    @ManyToOne
    private CaducidadPlazo caducidadPlazoid;
    @OneToMany(mappedBy = "objetoiId")
    private Collection<ObservacionObjetoi> observacionObjetoiCollection;
    @OneToMany(mappedBy = "objetoiId")
    private Collection<Boleto> boletoCollection;
    @OneToMany(mappedBy = "creditoId")
    private Collection<Emideta> emidetaCollection;
    @OneToMany(mappedBy = "creditoId")
    private Collection<Certificado> certificadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetoi")
    private Collection<Ctacte> ctacteCollection;
    @OneToMany(mappedBy = "creditoId")
    private Collection<Vinedo> vinedoCollection;
    @OneToMany(mappedBy = "creditoId")
    private Collection<ObjetoiEmergencia> objetoiEmergenciaCollection;
    @OneToMany(mappedBy = "creditoId")
    private Collection<Cuota> cuotaCollection;
    @OneToMany(mappedBy = "objetoiId")
    private Collection<ObjetoiEstado> objetoiEstadoCollection;
    @OneToMany(mappedBy = "objetoiId")
    private Collection<ObjetoiComportamiento> objetoiComportamientoCollection;
    @OneToMany(mappedBy = "objetoiId")
    private Collection<DomicilioObjetoi> domicilioObjetoiCollection;
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
    @Column(name = "financiamiento")
    private Double financiamiento;
    @Column(name = "frecuenciaCapital")
    private String frecuenciaCapital;
    @Column(name = "frecuenciaInteres")
    private String frecuenciaInteres;
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
    @Column(name = "qqsolicitado")
    private Double qqsolicitado;
    @Column(name = "tipoEmpresa")
    private String tipoEmpresa;
    @Column(name = "ultimaCuotaEmitida")
    private Integer ultimaCuotaEmitida;
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

    public Integer getUltimaCuotaEmitida() {
        return ultimaCuotaEmitida;
    }

    public void setUltimaCuotaEmitida(Integer ultimaCuotaEmitida) {
        this.ultimaCuotaEmitida = ultimaCuotaEmitida;
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

    @XmlTransient
    public Collection<DomicilioObjetoi> getDomicilioObjetoiCollection() {
        return domicilioObjetoiCollection;
    }

    public void setDomicilioObjetoiCollection(Collection<DomicilioObjetoi> domicilioObjetoiCollection) {
        this.domicilioObjetoiCollection = domicilioObjetoiCollection;
    }

    @XmlTransient
    public Collection<ObjetoiComportamiento> getObjetoiComportamientoCollection() {
        return objetoiComportamientoCollection;
    }

    public void setObjetoiComportamientoCollection(Collection<ObjetoiComportamiento> objetoiComportamientoCollection) {
        this.objetoiComportamientoCollection = objetoiComportamientoCollection;
    }

    @XmlTransient
    public Collection<ObjetoiEstado> getObjetoiEstadoCollection() {
        return objetoiEstadoCollection;
    }

    public void setObjetoiEstadoCollection(Collection<ObjetoiEstado> objetoiEstadoCollection) {
        this.objetoiEstadoCollection = objetoiEstadoCollection;
    }

    @XmlTransient
    public Collection<Boleto> getBoletoCollection() {
        return boletoCollection;
    }

    public void setBoletoCollection(Collection<Boleto> boletoCollection) {
        this.boletoCollection = boletoCollection;
    }

    @XmlTransient
    public Collection<Emideta> getEmidetaCollection() {
        return emidetaCollection;
    }

    public void setEmidetaCollection(Collection<Emideta> emidetaCollection) {
        this.emidetaCollection = emidetaCollection;
    }

    @XmlTransient
    public Collection<Certificado> getCertificadoCollection() {
        return certificadoCollection;
    }

    public void setCertificadoCollection(Collection<Certificado> certificadoCollection) {
        this.certificadoCollection = certificadoCollection;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection() {
        return ctacteCollection;
    }

    public void setCtacteCollection(Collection<Ctacte> ctacteCollection) {
        this.ctacteCollection = ctacteCollection;
    }

    @XmlTransient
    public Collection<Vinedo> getVinedoCollection() {
        return vinedoCollection;
    }

    public void setVinedoCollection(Collection<Vinedo> vinedoCollection) {
        this.vinedoCollection = vinedoCollection;
    }

    @XmlTransient
    public Collection<ObjetoiEmergencia> getObjetoiEmergenciaCollection() {
        return objetoiEmergenciaCollection;
    }

    public void setObjetoiEmergenciaCollection(Collection<ObjetoiEmergencia> objetoiEmergenciaCollection) {
        this.objetoiEmergenciaCollection = objetoiEmergenciaCollection;
    }

    @XmlTransient
    public Collection<Cuota> getCuotaCollection() {
        return cuotaCollection;
    }

    public void setCuotaCollection(Collection<Cuota> cuotaCollection) {
        this.cuotaCollection = cuotaCollection;
    }

    public String getCantMateria() {
        return cantMateria;
    }

    public void setCantMateria(String cantMateria) {
        this.cantMateria = cantMateria;
    }

    public Double getCotizaContrato() {
        return cotizaContrato;
    }

    public void setCotizaContrato(Double cotizaContrato) {
        this.cotizaContrato = cotizaContrato;
    }

    public Double getCotizaInicial() {
        return cotizaInicial;
    }

    public void setCotizaInicial(Double cotizaInicial) {
        this.cotizaInicial = cotizaInicial;
    }

    public Double getCotizaResol() {
        return cotizaResol;
    }

    public void setCotizaResol(Double cotizaResol) {
        this.cotizaResol = cotizaResol;
    }

    public Short getEsMiPyme() {
        return esMiPyme;
    }

    public void setEsMiPyme(Short esMiPyme) {
        this.esMiPyme = esMiPyme;
    }

    public Date getFechaAnalisisPatrimonial() {
        return fechaAnalisisPatrimonial;
    }

    public void setFechaAnalisisPatrimonial(Date fechaAnalisisPatrimonial) {
        this.fechaAnalisisPatrimonial = fechaAnalisisPatrimonial;
    }

    public Date getFechaAsesoriaLetrada() {
        return fechaAsesoriaLetrada;
    }

    public void setFechaAsesoriaLetrada(Date fechaAsesoriaLetrada) {
        this.fechaAsesoriaLetrada = fechaAsesoriaLetrada;
    }

    public Date getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(Date fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }

    public Date getFechaExpediente() {
        return fechaExpediente;
    }

    public void setFechaExpediente(Date fechaExpediente) {
        this.fechaExpediente = fechaExpediente;
    }

    public Date getFechaFirmaContrato() {
        return fechaFirmaContrato;
    }

    public void setFechaFirmaContrato(Date fechaFirmaContrato) {
        this.fechaFirmaContrato = fechaFirmaContrato;
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

    public Date getFechaInhibicion() {
        return fechaInhibicion;
    }

    public void setFechaInhibicion(Date fechaInhibicion) {
        this.fechaInhibicion = fechaInhibicion;
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

    public Date getFechaVerificacion() {
        return fechaVerificacion;
    }

    public void setFechaVerificacion(Date fechaVerificacion) {
        this.fechaVerificacion = fechaVerificacion;
    }

    public Short getFecovita() {
        return fecovita;
    }

    public void setFecovita(Short fecovita) {
        this.fecovita = fecovita;
    }

    public Double getFinanciamientoSol() {
        return financiamientoSol;
    }

    public void setFinanciamientoSol(Double financiamientoSol) {
        this.financiamientoSol = financiamientoSol;
    }

    public String getGastosElaboracion() {
        return gastosElaboracion;
    }

    public void setGastosElaboracion(String gastosElaboracion) {
        this.gastosElaboracion = gastosElaboracion;
    }

    public Date getInicioActividad() {
        return inicioActividad;
    }

    public void setInicioActividad(Date inicioActividad) {
        this.inicioActividad = inicioActividad;
    }

    public String getObservacionAnalisisPatrimonial() {
        return observacionAnalisisPatrimonial;
    }

    public void setObservacionAnalisisPatrimonial(String observacionAnalisisPatrimonial) {
        this.observacionAnalisisPatrimonial = observacionAnalisisPatrimonial;
    }

    public String getObservacionAsesoriaLetrada() {
        return observacionAsesoriaLetrada;
    }

    public void setObservacionAsesoriaLetrada(String observacionAsesoriaLetrada) {
        this.observacionAsesoriaLetrada = observacionAsesoriaLetrada;
    }

    public String getObservacionInhibicion() {
        return observacionInhibicion;
    }

    public void setObservacionInhibicion(String observacionInhibicion) {
        this.observacionInhibicion = observacionInhibicion;
    }

    public String getObservacionVerificacion() {
        return observacionVerificacion;
    }

    public void setObservacionVerificacion(String observacionVerificacion) {
        this.observacionVerificacion = observacionVerificacion;
    }

    public String getPrecioMateria() {
        return precioMateria;
    }

    public void setPrecioMateria(String precioMateria) {
        this.precioMateria = precioMateria;
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

    public Double getQqFinal() {
        return qqFinal;
    }

    public void setQqFinal(Double qqFinal) {
        this.qqFinal = qqFinal;
    }

    public String getTaxiDominio() {
        return taxiDominio;
    }

    public void setTaxiDominio(String taxiDominio) {
        this.taxiDominio = taxiDominio;
    }

    public String getTaxiMarca() {
        return taxiMarca;
    }

    public void setTaxiMarca(String taxiMarca) {
        this.taxiMarca = taxiMarca;
    }

    public String getTaxiModelo() {
        return taxiModelo;
    }

    public void setTaxiModelo(String taxiModelo) {
        this.taxiModelo = taxiModelo;
    }

    public String getTipoCult() {
        return tipoCult;
    }

    public void setTipoCult(String tipoCult) {
        this.tipoCult = tipoCult;
    }

    public Double getTotalBon() {
        return totalBon;
    }

    public void setTotalBon(Double totalBon) {
        this.totalBon = totalBon;
    }

    public Short getValorCartera() {
        return valorCartera;
    }

    public void setValorCartera(Short valorCartera) {
        this.valorCartera = valorCartera;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public CaducidadPlazo getCaducidadPlazoid() {
        return caducidadPlazoid;
    }

    public void setCaducidadPlazoid(CaducidadPlazo caducidadPlazoid) {
        this.caducidadPlazoid = caducidadPlazoid;
    }

    @XmlTransient
    public Collection<ObservacionObjetoi> getObservacionObjetoiCollection() {
        return observacionObjetoiCollection;
    }

    public void setObservacionObjetoiCollection(Collection<ObservacionObjetoi> observacionObjetoiCollection) {
        this.observacionObjetoiCollection = observacionObjetoiCollection;
    }
}
