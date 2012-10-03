/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "OrdePago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdePago.findAll", query = "SELECT o FROM OrdePago o"),
    @NamedQuery(name = "OrdePago.findByIdordepago", query = "SELECT o FROM OrdePago o WHERE o.idordepago = :idordepago"),
    @NamedQuery(name = "OrdePago.findByAnulada", query = "SELECT o FROM OrdePago o WHERE o.anulada = :anulada"),
    @NamedQuery(name = "OrdePago.findByConcepto", query = "SELECT o FROM OrdePago o WHERE o.concepto = :concepto"),
    @NamedQuery(name = "OrdePago.findByEjercicio", query = "SELECT o FROM OrdePago o WHERE o.ejercicio = :ejercicio"),
    @NamedQuery(name = "OrdePago.findByExpediente", query = "SELECT o FROM OrdePago o WHERE o.expediente = :expediente"),
    @NamedQuery(name = "OrdePago.findByExpediente2", query = "SELECT o FROM OrdePago o WHERE o.expediente2 = :expediente2"),
    @NamedQuery(name = "OrdePago.findByExpediente3", query = "SELECT o FROM OrdePago o WHERE o.expediente3 = :expediente3"),
    @NamedQuery(name = "OrdePago.findByExpepago", query = "SELECT o FROM OrdePago o WHERE o.expepago = :expepago"),
    @NamedQuery(name = "OrdePago.findByFechaanulacion", query = "SELECT o FROM OrdePago o WHERE o.fechaanulacion = :fechaanulacion"),
    @NamedQuery(name = "OrdePago.findByFechaordenpago", query = "SELECT o FROM OrdePago o WHERE o.fechaordenpago = :fechaordenpago"),
    @NamedQuery(name = "OrdePago.findByFechapago", query = "SELECT o FROM OrdePago o WHERE o.fechapago = :fechapago"),
    @NamedQuery(name = "OrdePago.findByFecharemision", query = "SELECT o FROM OrdePago o WHERE o.fecharemision = :fecharemision"),
    @NamedQuery(name = "OrdePago.findByFecharendicion", query = "SELECT o FROM OrdePago o WHERE o.fecharendicion = :fecharendicion"),
    @NamedQuery(name = "OrdePago.findByFechasaldo", query = "SELECT o FROM OrdePago o WHERE o.fechasaldo = :fechasaldo"),
    @NamedQuery(name = "OrdePago.findByIdtipoexpediente", query = "SELECT o FROM OrdePago o WHERE o.idtipoexpediente = :idtipoexpediente"),
    @NamedQuery(name = "OrdePago.findByIdtipoexpediente2", query = "SELECT o FROM OrdePago o WHERE o.idtipoexpediente2 = :idtipoexpediente2"),
    @NamedQuery(name = "OrdePago.findByIdtipoexpediente3", query = "SELECT o FROM OrdePago o WHERE o.idtipoexpediente3 = :idtipoexpediente3"),
    @NamedQuery(name = "OrdePago.findByIdtipoexpediente4", query = "SELECT o FROM OrdePago o WHERE o.idtipoexpediente4 = :idtipoexpediente4"),
    @NamedQuery(name = "OrdePago.findByMotivoanulacion", query = "SELECT o FROM OrdePago o WHERE o.motivoanulacion = :motivoanulacion"),
    @NamedQuery(name = "OrdePago.findByNroarchivopago", query = "SELECT o FROM OrdePago o WHERE o.nroarchivopago = :nroarchivopago"),
    @NamedQuery(name = "OrdePago.findByNrofoja", query = "SELECT o FROM OrdePago o WHERE o.nrofoja = :nrofoja"),
    @NamedQuery(name = "OrdePago.findByNroorden", query = "SELECT o FROM OrdePago o WHERE o.nroorden = :nroorden"),
    @NamedQuery(name = "OrdePago.findByNropagoparcial", query = "SELECT o FROM OrdePago o WHERE o.nropagoparcial = :nropagoparcial"),
    @NamedQuery(name = "OrdePago.findByOrdenpago", query = "SELECT o FROM OrdePago o WHERE o.ordenpago = :ordenpago"),
    @NamedQuery(name = "OrdePago.findByReservado", query = "SELECT o FROM OrdePago o WHERE o.reservado = :reservado"),
    @NamedQuery(name = "OrdePago.findByProyecto", query = "SELECT o FROM OrdePago o WHERE o.proyecto = :proyecto"),
    @NamedQuery(name = "OrdePago.findByFechavigencia", query = "SELECT o FROM OrdePago o WHERE o.fechavigencia = :fechavigencia"),
    @NamedQuery(name = "OrdePago.findByTipo", query = "SELECT o FROM OrdePago o WHERE o.tipo = :tipo"),
    @NamedQuery(name = "OrdePago.findByIdlinea", query = "SELECT o FROM OrdePago o WHERE o.idlinea = :idlinea"),
    @NamedQuery(name = "OrdePago.findByUsuario", query = "SELECT o FROM OrdePago o WHERE o.usuario = :usuario")})
public class OrdePago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "idordepago")
    private int idordepago;
    @Basic(optional = false)
    @Column(name = "anulada")
    private int anulada;
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "ejercicio")
    private int ejercicio;
    @Column(name = "expediente")
    private String expediente;
    @Column(name = "expediente2")
    private String expediente2;
    @Column(name = "expediente3")
    private String expediente3;
    @Basic(optional = false)
    @Column(name = "expepago")
    private String expepago;
    @Column(name = "fechaanulacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaanulacion;
    @Basic(optional = false)
    @Column(name = "fechaordenpago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaordenpago;
    @Column(name = "fechapago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapago;
    @Column(name = "fecharemision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharemision;
    @Column(name = "fecharendicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharendicion;
    @Column(name = "fechasaldo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasaldo;
    @Column(name = "idtipoexpediente")
    private Integer idtipoexpediente;
    @Column(name = "idtipoexpediente2")
    private Integer idtipoexpediente2;
    @Column(name = "idtipoexpediente3")
    private Integer idtipoexpediente3;
    @Column(name = "idtipoexpediente4")
    private Integer idtipoexpediente4;
    @Column(name = "motivoanulacion")
    private String motivoanulacion;
    @Column(name = "nroarchivopago")
    private Integer nroarchivopago;
    @Column(name = "nrofoja")
    private Integer nrofoja;
    @Column(name = "nroorden")
    private Integer nroorden;
    @Basic(optional = false)
    @Column(name = "nropagoparcial")
    private int nropagoparcial;
    @Basic(optional = false)
    @Column(name = "ordenpago")
    private int ordenpago;
    @Basic(optional = false)
    @Column(name = "reservado")
    private int reservado;
    @Column(name = "proyecto")
    private String proyecto;
    @Column(name = "FECHAVIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigencia;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "IDLINEA")
    private BigInteger idlinea;
    @Column(name = "USUARIO")
    private String usuario;

    public OrdePago() {
    }

    public int getIdordepago() {
        return idordepago;
    }

    public void setIdordepago(int idordepago) {
        this.idordepago = idordepago;
    }

    public int getAnulada() {
        return anulada;
    }

    public void setAnulada(int anulada) {
        this.anulada = anulada;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getExpediente2() {
        return expediente2;
    }

    public void setExpediente2(String expediente2) {
        this.expediente2 = expediente2;
    }

    public String getExpediente3() {
        return expediente3;
    }

    public void setExpediente3(String expediente3) {
        this.expediente3 = expediente3;
    }

    public String getExpepago() {
        return expepago;
    }

    public void setExpepago(String expepago) {
        this.expepago = expepago;
    }

    public Date getFechaanulacion() {
        return fechaanulacion;
    }

    public void setFechaanulacion(Date fechaanulacion) {
        this.fechaanulacion = fechaanulacion;
    }

    public Date getFechaordenpago() {
        return fechaordenpago;
    }

    public void setFechaordenpago(Date fechaordenpago) {
        this.fechaordenpago = fechaordenpago;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Date getFecharemision() {
        return fecharemision;
    }

    public void setFecharemision(Date fecharemision) {
        this.fecharemision = fecharemision;
    }

    public Date getFecharendicion() {
        return fecharendicion;
    }

    public void setFecharendicion(Date fecharendicion) {
        this.fecharendicion = fecharendicion;
    }

    public Date getFechasaldo() {
        return fechasaldo;
    }

    public void setFechasaldo(Date fechasaldo) {
        this.fechasaldo = fechasaldo;
    }

    public Integer getIdtipoexpediente() {
        return idtipoexpediente;
    }

    public void setIdtipoexpediente(Integer idtipoexpediente) {
        this.idtipoexpediente = idtipoexpediente;
    }

    public Integer getIdtipoexpediente2() {
        return idtipoexpediente2;
    }

    public void setIdtipoexpediente2(Integer idtipoexpediente2) {
        this.idtipoexpediente2 = idtipoexpediente2;
    }

    public Integer getIdtipoexpediente3() {
        return idtipoexpediente3;
    }

    public void setIdtipoexpediente3(Integer idtipoexpediente3) {
        this.idtipoexpediente3 = idtipoexpediente3;
    }

    public Integer getIdtipoexpediente4() {
        return idtipoexpediente4;
    }

    public void setIdtipoexpediente4(Integer idtipoexpediente4) {
        this.idtipoexpediente4 = idtipoexpediente4;
    }

    public String getMotivoanulacion() {
        return motivoanulacion;
    }

    public void setMotivoanulacion(String motivoanulacion) {
        this.motivoanulacion = motivoanulacion;
    }

    public Integer getNroarchivopago() {
        return nroarchivopago;
    }

    public void setNroarchivopago(Integer nroarchivopago) {
        this.nroarchivopago = nroarchivopago;
    }

    public Integer getNrofoja() {
        return nrofoja;
    }

    public void setNrofoja(Integer nrofoja) {
        this.nrofoja = nrofoja;
    }

    public Integer getNroorden() {
        return nroorden;
    }

    public void setNroorden(Integer nroorden) {
        this.nroorden = nroorden;
    }

    public int getNropagoparcial() {
        return nropagoparcial;
    }

    public void setNropagoparcial(int nropagoparcial) {
        this.nropagoparcial = nropagoparcial;
    }

    public int getOrdenpago() {
        return ordenpago;
    }

    public void setOrdenpago(int ordenpago) {
        this.ordenpago = ordenpago;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Date getFechavigencia() {
        return fechavigencia;
    }

    public void setFechavigencia(Date fechavigencia) {
        this.fechavigencia = fechavigencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigInteger getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(BigInteger idlinea) {
        this.idlinea = idlinea;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
