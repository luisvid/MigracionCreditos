/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Numerador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Numerador.findAll", query = "SELECT n FROM Numerador n"),
    @NamedQuery(name = "Numerador.findByNombre", query = "SELECT n FROM Numerador n WHERE n.nombre = :nombre"),
    @NamedQuery(name = "Numerador.findByNumero", query = "SELECT n FROM Numerador n WHERE n.numero = :numero")})
public class Numerador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "numero")
    private BigInteger numero;

    public Numerador() {
    }

    public Numerador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numerador)) {
            return false;
        }
        Numerador other = (Numerador) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Numerador[ nombre=" + nombre + " ]";
    }
    
}
