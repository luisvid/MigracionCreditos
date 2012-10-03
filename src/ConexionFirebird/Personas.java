/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
@Table(name = "PERSONAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findByNuri", query = "SELECT p FROM Personas p WHERE p.nuri = :nuri"),
    @NamedQuery(name = "Personas.findByPersona", query = "SELECT p FROM Personas p WHERE p.persona = :persona"),
    @NamedQuery(name = "Personas.findByCuit", query = "SELECT p FROM Personas p WHERE p.cuit = :cuit"),
    @NamedQuery(name = "Personas.findByNroInterno", query = "SELECT p FROM Personas p WHERE p.nroInterno = :nroInterno"),
    @NamedQuery(name = "Personas.findByFechaBaja", query = "SELECT p FROM Personas p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Personas.findByPassw", query = "SELECT p FROM Personas p WHERE p.passw = :passw"),
    @NamedQuery(name = "Personas.findByFecInhabilita", query = "SELECT p FROM Personas p WHERE p.fecInhabilita = :fecInhabilita"),
    @NamedQuery(name = "Personas.findByTipo", query = "SELECT p FROM Personas p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Personas.findByEstado", query = "SELECT p FROM Personas p WHERE p.estado = :estado"),
    @NamedQuery(name = "Personas.findByCodAgrupa", query = "SELECT p FROM Personas p WHERE p.codAgrupa = :codAgrupa"),
    @NamedQuery(name = "Personas.findByRucip", query = "SELECT p FROM Personas p WHERE p.rucip = :rucip"),
    @NamedQuery(name = "Personas.findByExpediente", query = "SELECT p FROM Personas p WHERE p.expediente = :expediente"),
    @NamedQuery(name = "Personas.findByIngBrutos", query = "SELECT p FROM Personas p WHERE p.ingBrutos = :ingBrutos"),
    @NamedQuery(name = "Personas.findByMontoPermitido", query = "SELECT p FROM Personas p WHERE p.montoPermitido = :montoPermitido"),
    @NamedQuery(name = "Personas.findByCodActividad", query = "SELECT p FROM Personas p WHERE p.codActividad = :codActividad"),
    @NamedQuery(name = "Personas.findByFecInicioActividad", query = "SELECT p FROM Personas p WHERE p.fecInicioActividad = :fecInicioActividad")})
public class Personas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NURI")
    private Integer nuri;
    @Basic(optional = false)
    @Column(name = "PERSONA")
    private String persona;
    @Column(name = "CUIT")
    private String cuit;
    @Column(name = "NRO_INTERNO")
    private Integer nroInterno;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "PASSW")
    private String passw;
    @Column(name = "FEC_INHABILITA")
    @Temporal(TemporalType.DATE)
    private Date fecInhabilita;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "COD_AGRUPA")
    private String codAgrupa;
    @Column(name = "RUCIP")
    private String rucip;
    @Column(name = "EXPEDIENTE")
    private String expediente;
    @Column(name = "ING_BRUTOS")
    private String ingBrutos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_PERMITIDO")
    private BigDecimal montoPermitido;
    @Column(name = "COD_ACTIVIDAD")
    private Integer codActividad;
    @Column(name = "FEC_INICIO_ACTIVIDAD")
    @Temporal(TemporalType.DATE)
    private Date fecInicioActividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persNuri")
    private List<PersDireccion> persDireccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persNuri")
    private List<Creditos> creditosList;
    @JoinColumn(name = "PERS_DIR_NURI", referencedColumnName = "NURI")
    @ManyToOne
    private PersDireccion persDirNuri;
    @JoinColumn(name = "ESTADO_NURI", referencedColumnName = "NURI")
    @ManyToOne
    private EstadoEstablecimientos estadoNuri;
    @JoinColumn(name = "BAJA", referencedColumnName = "COD")
    @ManyToOne
    private DNosi baja;
    @JoinColumn(name = "INHABILITADO", referencedColumnName = "COD")
    @ManyToOne
    private DNosi inhabilitado;
    @JoinColumn(name = "CATEGORIA_IVA_NURI", referencedColumnName = "NURI")
    @ManyToOne
    private CategoriaIva categoriaIvaNuri;

    public Personas() {
    }

    public Personas(Integer nuri) {
        this.nuri = nuri;
    }

    public Personas(Integer nuri, String persona) {
        this.nuri = nuri;
        this.persona = persona;
    }

    public Integer getNuri() {
        return nuri;
    }

    public void setNuri(Integer nuri) {
        this.nuri = nuri;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getNroInterno() {
        return nroInterno;
    }

    public void setNroInterno(Integer nroInterno) {
        this.nroInterno = nroInterno;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public Date getFecInhabilita() {
        return fecInhabilita;
    }

    public void setFecInhabilita(Date fecInhabilita) {
        this.fecInhabilita = fecInhabilita;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodAgrupa() {
        return codAgrupa;
    }

    public void setCodAgrupa(String codAgrupa) {
        this.codAgrupa = codAgrupa;
    }

    public String getRucip() {
        return rucip;
    }

    public void setRucip(String rucip) {
        this.rucip = rucip;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getIngBrutos() {
        return ingBrutos;
    }

    public void setIngBrutos(String ingBrutos) {
        this.ingBrutos = ingBrutos;
    }

    public BigDecimal getMontoPermitido() {
        return montoPermitido;
    }

    public void setMontoPermitido(BigDecimal montoPermitido) {
        this.montoPermitido = montoPermitido;
    }

    public Integer getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Integer codActividad) {
        this.codActividad = codActividad;
    }

    public Date getFecInicioActividad() {
        return fecInicioActividad;
    }

    public void setFecInicioActividad(Date fecInicioActividad) {
        this.fecInicioActividad = fecInicioActividad;
    }

    @XmlTransient
    public List<PersDireccion> getPersDireccionList() {
        return persDireccionList;
    }

    public void setPersDireccionList(List<PersDireccion> persDireccionList) {
        this.persDireccionList = persDireccionList;
    }

    @XmlTransient
    public List<Creditos> getCreditosList() {
        return creditosList;
    }

    public void setCreditosList(List<Creditos> creditosList) {
        this.creditosList = creditosList;
    }

    public PersDireccion getPersDirNuri() {
        return persDirNuri;
    }

    public void setPersDirNuri(PersDireccion persDirNuri) {
        this.persDirNuri = persDirNuri;
    }

    public EstadoEstablecimientos getEstadoNuri() {
        return estadoNuri;
    }

    public void setEstadoNuri(EstadoEstablecimientos estadoNuri) {
        this.estadoNuri = estadoNuri;
    }

    public DNosi getBaja() {
        return baja;
    }

    public void setBaja(DNosi baja) {
        this.baja = baja;
    }

    public DNosi getInhabilitado() {
        return inhabilitado;
    }

    public void setInhabilitado(DNosi inhabilitado) {
        this.inhabilitado = inhabilitado;
    }

    public CategoriaIva getCategoriaIvaNuri() {
        return categoriaIvaNuri;
    }

    public void setCategoriaIvaNuri(CategoriaIva categoriaIvaNuri) {
        this.categoriaIvaNuri = categoriaIvaNuri;
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
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.nuri == null && other.nuri != null) || (this.nuri != null && !this.nuri.equals(other.nuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.Personas[ nuri=" + nuri + " ]";
    }
    
}
