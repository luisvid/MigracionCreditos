/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
    @NamedQuery(name = "Linea.findById", query = "SELECT l FROM Linea l WHERE l.id = :id"),
    @NamedQuery(name = "Linea.findByDetalle", query = "SELECT l FROM Linea l WHERE l.detalle = :detalle"),
    @NamedQuery(name = "Linea.findByMonto", query = "SELECT l FROM Linea l WHERE l.monto = :monto"),
    @NamedQuery(name = "Linea.findByMontoOPorcentaje", query = "SELECT l FROM Linea l WHERE l.montoOPorcentaje = :montoOPorcentaje"),
    @NamedQuery(name = "Linea.findByNombre", query = "SELECT l FROM Linea l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Linea.findByObjeto", query = "SELECT l FROM Linea l WHERE l.objeto = :objeto"),
    @NamedQuery(name = "Linea.findByMonedaIDMONEDA", query = "SELECT l FROM Linea l WHERE l.monedaIDMONEDA = :monedaIDMONEDA"),
    @NamedQuery(name = "Linea.findByActiva", query = "SELECT l FROM Linea l WHERE l.activa = :activa"),
    @NamedQuery(name = "Linea.findByMuestraCosecha", query = "SELECT l FROM Linea l WHERE l.muestraCosecha = :muestraCosecha"),
    @NamedQuery(name = "Linea.findByMuestraHectareas", query = "SELECT l FROM Linea l WHERE l.muestraHectareas = :muestraHectareas"),
    @NamedQuery(name = "Linea.findByMuestraMateriasPrimas", query = "SELECT l FROM Linea l WHERE l.muestraMateriasPrimas = :muestraMateriasPrimas"),
    @NamedQuery(name = "Linea.findByMuestraTaxis", query = "SELECT l FROM Linea l WHERE l.muestraTaxis = :muestraTaxis"),
    @NamedQuery(name = "Linea.findByNombreProceso", query = "SELECT l FROM Linea l WHERE l.nombreProceso = :nombreProceso"),
    @NamedQuery(name = "Linea.findBySubtipoLineaid", query = "SELECT l FROM Linea l WHERE l.subtipoLineaid = :subtipoLineaid"),
    @NamedQuery(name = "Linea.findByCodigoCuenta", query = "SELECT l FROM Linea l WHERE l.codigoCuenta = :codigoCuenta"),
    @NamedQuery(name = "Linea.findByCuentaIDPLANCTA", query = "SELECT l FROM Linea l WHERE l.cuentaIDPLANCTA = :cuentaIDPLANCTA"),
    @NamedQuery(name = "Linea.findByCodigoCtaContablePatrimonioCapital", query = "SELECT l FROM Linea l WHERE l.codigoCtaContablePatrimonioCapital = :codigoCtaContablePatrimonioCapital"),
    @NamedQuery(name = "Linea.findByCodigoCtaContablePatrimonioCompensatorio", query = "SELECT l FROM Linea l WHERE l.codigoCtaContablePatrimonioCompensatorio = :codigoCtaContablePatrimonioCompensatorio"),
    @NamedQuery(name = "Linea.findByCodigoCtaContablePatrimonioGasto", query = "SELECT l FROM Linea l WHERE l.codigoCtaContablePatrimonioGasto = :codigoCtaContablePatrimonioGasto"),
    @NamedQuery(name = "Linea.findByCodigoCtaContablePatrimonioMoratorio", query = "SELECT l FROM Linea l WHERE l.codigoCtaContablePatrimonioMoratorio = :codigoCtaContablePatrimonioMoratorio"),
    @NamedQuery(name = "Linea.findByCodigoCtaContablePatrimonioMulta", query = "SELECT l FROM Linea l WHERE l.codigoCtaContablePatrimonioMulta = :codigoCtaContablePatrimonioMulta"),
    @NamedQuery(name = "Linea.findByCodigoCtaContablePatrimonioPunitorio", query = "SELECT l FROM Linea l WHERE l.codigoCtaContablePatrimonioPunitorio = :codigoCtaContablePatrimonioPunitorio"),
    @NamedQuery(name = "Linea.findByCodigoCtaContableRecursosCapital", query = "SELECT l FROM Linea l WHERE l.codigoCtaContableRecursosCapital = :codigoCtaContableRecursosCapital"),
    @NamedQuery(name = "Linea.findByCodigoCtaContableRecursosCompensatorio", query = "SELECT l FROM Linea l WHERE l.codigoCtaContableRecursosCompensatorio = :codigoCtaContableRecursosCompensatorio"),
    @NamedQuery(name = "Linea.findByCodigoCtaContableRecursosGasto", query = "SELECT l FROM Linea l WHERE l.codigoCtaContableRecursosGasto = :codigoCtaContableRecursosGasto"),
    @NamedQuery(name = "Linea.findByCodigoCtaContableRecursosMoratorio", query = "SELECT l FROM Linea l WHERE l.codigoCtaContableRecursosMoratorio = :codigoCtaContableRecursosMoratorio"),
    @NamedQuery(name = "Linea.findByCodigoCtaContableRecursosMulta", query = "SELECT l FROM Linea l WHERE l.codigoCtaContableRecursosMulta = :codigoCtaContableRecursosMulta"),
    @NamedQuery(name = "Linea.findByCodigoCtaContableRecursosPunitorio", query = "SELECT l FROM Linea l WHERE l.codigoCtaContableRecursosPunitorio = :codigoCtaContableRecursosPunitorio"),
    @NamedQuery(name = "Linea.findByIegresoId", query = "SELECT l FROM Linea l WHERE l.iegresoId = :iegresoId"),
    @NamedQuery(name = "Linea.findByInstitucionalId", query = "SELECT l FROM Linea l WHERE l.institucionalId = :institucionalId"),
    @NamedQuery(name = "Linea.findByNomencladorId", query = "SELECT l FROM Linea l WHERE l.nomencladorId = :nomencladorId"),
    @NamedQuery(name = "Linea.findByCtaContablePatrimonioCapitalId", query = "SELECT l FROM Linea l WHERE l.ctaContablePatrimonioCapitalId = :ctaContablePatrimonioCapitalId"),
    @NamedQuery(name = "Linea.findByCtaContablePatrimonioCompensatorioId", query = "SELECT l FROM Linea l WHERE l.ctaContablePatrimonioCompensatorioId = :ctaContablePatrimonioCompensatorioId"),
    @NamedQuery(name = "Linea.findByCtaContablePatrimonioGastoId", query = "SELECT l FROM Linea l WHERE l.ctaContablePatrimonioGastoId = :ctaContablePatrimonioGastoId"),
    @NamedQuery(name = "Linea.findByCtaContablePatrimonioMoratorioId", query = "SELECT l FROM Linea l WHERE l.ctaContablePatrimonioMoratorioId = :ctaContablePatrimonioMoratorioId"),
    @NamedQuery(name = "Linea.findByCtaContablePatrimonioMultaId", query = "SELECT l FROM Linea l WHERE l.ctaContablePatrimonioMultaId = :ctaContablePatrimonioMultaId"),
    @NamedQuery(name = "Linea.findByCtaContablePatrimonioPunitorioId", query = "SELECT l FROM Linea l WHERE l.ctaContablePatrimonioPunitorioId = :ctaContablePatrimonioPunitorioId"),
    @NamedQuery(name = "Linea.findByCtaContableRecursosCapitalId", query = "SELECT l FROM Linea l WHERE l.ctaContableRecursosCapitalId = :ctaContableRecursosCapitalId"),
    @NamedQuery(name = "Linea.findByCtaContableRecursosCompensatorioId", query = "SELECT l FROM Linea l WHERE l.ctaContableRecursosCompensatorioId = :ctaContableRecursosCompensatorioId"),
    @NamedQuery(name = "Linea.findByCtaContableRecursosGastoId", query = "SELECT l FROM Linea l WHERE l.ctaContableRecursosGastoId = :ctaContableRecursosGastoId"),
    @NamedQuery(name = "Linea.findByCtaContableRecursosMoratorioId", query = "SELECT l FROM Linea l WHERE l.ctaContableRecursosMoratorioId = :ctaContableRecursosMoratorioId"),
    @NamedQuery(name = "Linea.findByCtaContableRecursosMultaId", query = "SELECT l FROM Linea l WHERE l.ctaContableRecursosMultaId = :ctaContableRecursosMultaId"),
    @NamedQuery(name = "Linea.findByCtaContableRecursosPunitorioId", query = "SELECT l FROM Linea l WHERE l.ctaContableRecursosPunitorioId = :ctaContableRecursosPunitorioId")})
public class Linea implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @Column(name = "montoOPorcentaje")
    private Integer montoOPorcentaje;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "moneda_IDMONEDA")
    private BigInteger monedaIDMONEDA;
    @Column(name = "activa")
    private Short activa;
    @Column(name = "muestraCosecha")
    private Short muestraCosecha;
    @Column(name = "muestraHectareas")
    private Short muestraHectareas;
    @Column(name = "muestraMateriasPrimas")
    private Short muestraMateriasPrimas;
    @Column(name = "muestraTaxis")
    private Short muestraTaxis;
    @Column(name = "nombreProceso")
    private String nombreProceso;
    @Column(name = "subtipoLinea_id")
    private Long subtipoLineaid;
    @Column(name = "codigoCuenta")
    private String codigoCuenta;
    @Column(name = "cuenta_IDPLANCTA")
    private BigInteger cuentaIDPLANCTA;
    @Column(name = "codigoCtaContablePatrimonioCapital")
    private String codigoCtaContablePatrimonioCapital;
    @Column(name = "codigoCtaContablePatrimonioCompensatorio")
    private String codigoCtaContablePatrimonioCompensatorio;
    @Column(name = "codigoCtaContablePatrimonioGasto")
    private String codigoCtaContablePatrimonioGasto;
    @Column(name = "codigoCtaContablePatrimonioMoratorio")
    private String codigoCtaContablePatrimonioMoratorio;
    @Column(name = "codigoCtaContablePatrimonioMulta")
    private String codigoCtaContablePatrimonioMulta;
    @Column(name = "codigoCtaContablePatrimonioPunitorio")
    private String codigoCtaContablePatrimonioPunitorio;
    @Column(name = "codigoCtaContableRecursosCapital")
    private String codigoCtaContableRecursosCapital;
    @Column(name = "codigoCtaContableRecursosCompensatorio")
    private String codigoCtaContableRecursosCompensatorio;
    @Column(name = "codigoCtaContableRecursosGasto")
    private String codigoCtaContableRecursosGasto;
    @Column(name = "codigoCtaContableRecursosMoratorio")
    private String codigoCtaContableRecursosMoratorio;
    @Column(name = "codigoCtaContableRecursosMulta")
    private String codigoCtaContableRecursosMulta;
    @Column(name = "codigoCtaContableRecursosPunitorio")
    private String codigoCtaContableRecursosPunitorio;
    @Column(name = "iegresoId")
    private BigInteger iegresoId;
    @Column(name = "institucionalId")
    private BigInteger institucionalId;
    @Column(name = "nomencladorId")
    private BigInteger nomencladorId;
    @Column(name = "ctaContablePatrimonioCapitalId")
    private BigInteger ctaContablePatrimonioCapitalId;
    @Column(name = "ctaContablePatrimonioCompensatorioId")
    private BigInteger ctaContablePatrimonioCompensatorioId;
    @Column(name = "ctaContablePatrimonioGastoId")
    private BigInteger ctaContablePatrimonioGastoId;
    @Column(name = "ctaContablePatrimonioMoratorioId")
    private BigInteger ctaContablePatrimonioMoratorioId;
    @Column(name = "ctaContablePatrimonioMultaId")
    private BigInteger ctaContablePatrimonioMultaId;
    @Column(name = "ctaContablePatrimonioPunitorioId")
    private BigInteger ctaContablePatrimonioPunitorioId;
    @Column(name = "ctaContableRecursosCapitalId")
    private BigInteger ctaContableRecursosCapitalId;
    @Column(name = "ctaContableRecursosCompensatorioId")
    private BigInteger ctaContableRecursosCompensatorioId;
    @Column(name = "ctaContableRecursosGastoId")
    private BigInteger ctaContableRecursosGastoId;
    @Column(name = "ctaContableRecursosMoratorioId")
    private BigInteger ctaContableRecursosMoratorioId;
    @Column(name = "ctaContableRecursosMultaId")
    private BigInteger ctaContableRecursosMultaId;
    @Column(name = "ctaContableRecursosPunitorioId")
    private BigInteger ctaContableRecursosPunitorioId;
    @OneToMany(mappedBy = "lineaId")
    private Collection<Requisito> requisitoCollection;
    @OneToMany(mappedBy = "lineaId")
    private Collection<Objetoi> objetoiCollection;
    @OneToMany(mappedBy = "lineaId")
    private Collection<Turno> turnoCollection;
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    @ManyToOne
    private Sector sectorId;
    @JoinColumn(name = "punitorioId", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto punitorioId;
    @JoinColumn(name = "capitalId", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto capitalId;
    @JoinColumn(name = "moratorioId", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto moratorioId;
    @JoinColumn(name = "multaId", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto multaId;
    @JoinColumn(name = "compensatorioId", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto compensatorioId;
    @JoinColumn(name = "gastoId", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto gastoId;

    public Linea() {
    }

    public Linea(BigDecimal id) {
        this.id = id;
    }

    public Linea(BigDecimal id, double monto) {
        this.id = id;
        this.monto = monto;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Integer getMontoOPorcentaje() {
        return montoOPorcentaje;
    }

    public void setMontoOPorcentaje(Integer montoOPorcentaje) {
        this.montoOPorcentaje = montoOPorcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public BigInteger getMonedaIDMONEDA() {
        return monedaIDMONEDA;
    }

    public void setMonedaIDMONEDA(BigInteger monedaIDMONEDA) {
        this.monedaIDMONEDA = monedaIDMONEDA;
    }

    public Short getActiva() {
        return activa;
    }

    public void setActiva(Short activa) {
        this.activa = activa;
    }

    public Short getMuestraCosecha() {
        return muestraCosecha;
    }

    public void setMuestraCosecha(Short muestraCosecha) {
        this.muestraCosecha = muestraCosecha;
    }

    public Short getMuestraHectareas() {
        return muestraHectareas;
    }

    public void setMuestraHectareas(Short muestraHectareas) {
        this.muestraHectareas = muestraHectareas;
    }

    public Short getMuestraMateriasPrimas() {
        return muestraMateriasPrimas;
    }

    public void setMuestraMateriasPrimas(Short muestraMateriasPrimas) {
        this.muestraMateriasPrimas = muestraMateriasPrimas;
    }

    public Short getMuestraTaxis() {
        return muestraTaxis;
    }

    public void setMuestraTaxis(Short muestraTaxis) {
        this.muestraTaxis = muestraTaxis;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public Long getSubtipoLineaid() {
        return subtipoLineaid;
    }

    public void setSubtipoLineaid(Long subtipoLineaid) {
        this.subtipoLineaid = subtipoLineaid;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public BigInteger getCuentaIDPLANCTA() {
        return cuentaIDPLANCTA;
    }

    public void setCuentaIDPLANCTA(BigInteger cuentaIDPLANCTA) {
        this.cuentaIDPLANCTA = cuentaIDPLANCTA;
    }

    public String getCodigoCtaContablePatrimonioCapital() {
        return codigoCtaContablePatrimonioCapital;
    }

    public void setCodigoCtaContablePatrimonioCapital(String codigoCtaContablePatrimonioCapital) {
        this.codigoCtaContablePatrimonioCapital = codigoCtaContablePatrimonioCapital;
    }

    public String getCodigoCtaContablePatrimonioCompensatorio() {
        return codigoCtaContablePatrimonioCompensatorio;
    }

    public void setCodigoCtaContablePatrimonioCompensatorio(String codigoCtaContablePatrimonioCompensatorio) {
        this.codigoCtaContablePatrimonioCompensatorio = codigoCtaContablePatrimonioCompensatorio;
    }

    public String getCodigoCtaContablePatrimonioGasto() {
        return codigoCtaContablePatrimonioGasto;
    }

    public void setCodigoCtaContablePatrimonioGasto(String codigoCtaContablePatrimonioGasto) {
        this.codigoCtaContablePatrimonioGasto = codigoCtaContablePatrimonioGasto;
    }

    public String getCodigoCtaContablePatrimonioMoratorio() {
        return codigoCtaContablePatrimonioMoratorio;
    }

    public void setCodigoCtaContablePatrimonioMoratorio(String codigoCtaContablePatrimonioMoratorio) {
        this.codigoCtaContablePatrimonioMoratorio = codigoCtaContablePatrimonioMoratorio;
    }

    public String getCodigoCtaContablePatrimonioMulta() {
        return codigoCtaContablePatrimonioMulta;
    }

    public void setCodigoCtaContablePatrimonioMulta(String codigoCtaContablePatrimonioMulta) {
        this.codigoCtaContablePatrimonioMulta = codigoCtaContablePatrimonioMulta;
    }

    public String getCodigoCtaContablePatrimonioPunitorio() {
        return codigoCtaContablePatrimonioPunitorio;
    }

    public void setCodigoCtaContablePatrimonioPunitorio(String codigoCtaContablePatrimonioPunitorio) {
        this.codigoCtaContablePatrimonioPunitorio = codigoCtaContablePatrimonioPunitorio;
    }

    public String getCodigoCtaContableRecursosCapital() {
        return codigoCtaContableRecursosCapital;
    }

    public void setCodigoCtaContableRecursosCapital(String codigoCtaContableRecursosCapital) {
        this.codigoCtaContableRecursosCapital = codigoCtaContableRecursosCapital;
    }

    public String getCodigoCtaContableRecursosCompensatorio() {
        return codigoCtaContableRecursosCompensatorio;
    }

    public void setCodigoCtaContableRecursosCompensatorio(String codigoCtaContableRecursosCompensatorio) {
        this.codigoCtaContableRecursosCompensatorio = codigoCtaContableRecursosCompensatorio;
    }

    public String getCodigoCtaContableRecursosGasto() {
        return codigoCtaContableRecursosGasto;
    }

    public void setCodigoCtaContableRecursosGasto(String codigoCtaContableRecursosGasto) {
        this.codigoCtaContableRecursosGasto = codigoCtaContableRecursosGasto;
    }

    public String getCodigoCtaContableRecursosMoratorio() {
        return codigoCtaContableRecursosMoratorio;
    }

    public void setCodigoCtaContableRecursosMoratorio(String codigoCtaContableRecursosMoratorio) {
        this.codigoCtaContableRecursosMoratorio = codigoCtaContableRecursosMoratorio;
    }

    public String getCodigoCtaContableRecursosMulta() {
        return codigoCtaContableRecursosMulta;
    }

    public void setCodigoCtaContableRecursosMulta(String codigoCtaContableRecursosMulta) {
        this.codigoCtaContableRecursosMulta = codigoCtaContableRecursosMulta;
    }

    public String getCodigoCtaContableRecursosPunitorio() {
        return codigoCtaContableRecursosPunitorio;
    }

    public void setCodigoCtaContableRecursosPunitorio(String codigoCtaContableRecursosPunitorio) {
        this.codigoCtaContableRecursosPunitorio = codigoCtaContableRecursosPunitorio;
    }

    public BigInteger getIegresoId() {
        return iegresoId;
    }

    public void setIegresoId(BigInteger iegresoId) {
        this.iegresoId = iegresoId;
    }

    public BigInteger getInstitucionalId() {
        return institucionalId;
    }

    public void setInstitucionalId(BigInteger institucionalId) {
        this.institucionalId = institucionalId;
    }

    public BigInteger getNomencladorId() {
        return nomencladorId;
    }

    public void setNomencladorId(BigInteger nomencladorId) {
        this.nomencladorId = nomencladorId;
    }

    public BigInteger getCtaContablePatrimonioCapitalId() {
        return ctaContablePatrimonioCapitalId;
    }

    public void setCtaContablePatrimonioCapitalId(BigInteger ctaContablePatrimonioCapitalId) {
        this.ctaContablePatrimonioCapitalId = ctaContablePatrimonioCapitalId;
    }

    public BigInteger getCtaContablePatrimonioCompensatorioId() {
        return ctaContablePatrimonioCompensatorioId;
    }

    public void setCtaContablePatrimonioCompensatorioId(BigInteger ctaContablePatrimonioCompensatorioId) {
        this.ctaContablePatrimonioCompensatorioId = ctaContablePatrimonioCompensatorioId;
    }

    public BigInteger getCtaContablePatrimonioGastoId() {
        return ctaContablePatrimonioGastoId;
    }

    public void setCtaContablePatrimonioGastoId(BigInteger ctaContablePatrimonioGastoId) {
        this.ctaContablePatrimonioGastoId = ctaContablePatrimonioGastoId;
    }

    public BigInteger getCtaContablePatrimonioMoratorioId() {
        return ctaContablePatrimonioMoratorioId;
    }

    public void setCtaContablePatrimonioMoratorioId(BigInteger ctaContablePatrimonioMoratorioId) {
        this.ctaContablePatrimonioMoratorioId = ctaContablePatrimonioMoratorioId;
    }

    public BigInteger getCtaContablePatrimonioMultaId() {
        return ctaContablePatrimonioMultaId;
    }

    public void setCtaContablePatrimonioMultaId(BigInteger ctaContablePatrimonioMultaId) {
        this.ctaContablePatrimonioMultaId = ctaContablePatrimonioMultaId;
    }

    public BigInteger getCtaContablePatrimonioPunitorioId() {
        return ctaContablePatrimonioPunitorioId;
    }

    public void setCtaContablePatrimonioPunitorioId(BigInteger ctaContablePatrimonioPunitorioId) {
        this.ctaContablePatrimonioPunitorioId = ctaContablePatrimonioPunitorioId;
    }

    public BigInteger getCtaContableRecursosCapitalId() {
        return ctaContableRecursosCapitalId;
    }

    public void setCtaContableRecursosCapitalId(BigInteger ctaContableRecursosCapitalId) {
        this.ctaContableRecursosCapitalId = ctaContableRecursosCapitalId;
    }

    public BigInteger getCtaContableRecursosCompensatorioId() {
        return ctaContableRecursosCompensatorioId;
    }

    public void setCtaContableRecursosCompensatorioId(BigInteger ctaContableRecursosCompensatorioId) {
        this.ctaContableRecursosCompensatorioId = ctaContableRecursosCompensatorioId;
    }

    public BigInteger getCtaContableRecursosGastoId() {
        return ctaContableRecursosGastoId;
    }

    public void setCtaContableRecursosGastoId(BigInteger ctaContableRecursosGastoId) {
        this.ctaContableRecursosGastoId = ctaContableRecursosGastoId;
    }

    public BigInteger getCtaContableRecursosMoratorioId() {
        return ctaContableRecursosMoratorioId;
    }

    public void setCtaContableRecursosMoratorioId(BigInteger ctaContableRecursosMoratorioId) {
        this.ctaContableRecursosMoratorioId = ctaContableRecursosMoratorioId;
    }

    public BigInteger getCtaContableRecursosMultaId() {
        return ctaContableRecursosMultaId;
    }

    public void setCtaContableRecursosMultaId(BigInteger ctaContableRecursosMultaId) {
        this.ctaContableRecursosMultaId = ctaContableRecursosMultaId;
    }

    public BigInteger getCtaContableRecursosPunitorioId() {
        return ctaContableRecursosPunitorioId;
    }

    public void setCtaContableRecursosPunitorioId(BigInteger ctaContableRecursosPunitorioId) {
        this.ctaContableRecursosPunitorioId = ctaContableRecursosPunitorioId;
    }

    @XmlTransient
    public Collection<Requisito> getRequisitoCollection() {
        return requisitoCollection;
    }

    public void setRequisitoCollection(Collection<Requisito> requisitoCollection) {
        this.requisitoCollection = requisitoCollection;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
    }

    @XmlTransient
    public Collection<Turno> getTurnoCollection() {
        return turnoCollection;
    }

    public void setTurnoCollection(Collection<Turno> turnoCollection) {
        this.turnoCollection = turnoCollection;
    }

    public Sector getSectorId() {
        return sectorId;
    }

    public void setSectorId(Sector sectorId) {
        this.sectorId = sectorId;
    }

    public CConcepto getPunitorioId() {
        return punitorioId;
    }

    public void setPunitorioId(CConcepto punitorioId) {
        this.punitorioId = punitorioId;
    }

    public CConcepto getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(CConcepto capitalId) {
        this.capitalId = capitalId;
    }

    public CConcepto getMoratorioId() {
        return moratorioId;
    }

    public void setMoratorioId(CConcepto moratorioId) {
        this.moratorioId = moratorioId;
    }

    public CConcepto getMultaId() {
        return multaId;
    }

    public void setMultaId(CConcepto multaId) {
        this.multaId = multaId;
    }

    public CConcepto getCompensatorioId() {
        return compensatorioId;
    }

    public void setCompensatorioId(CConcepto compensatorioId) {
        this.compensatorioId = compensatorioId;
    }

    public CConcepto getGastoId() {
        return gastoId;
    }

    public void setGastoId(CConcepto gastoId) {
        this.gastoId = gastoId;
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
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Linea[ id=" + id + " ]";
    }
    
}
