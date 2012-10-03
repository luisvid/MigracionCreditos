/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author analian
 */
@Embeddable
public class CtactePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "itemCtacte")
    private BigInteger itemCtacte;
    @Basic(optional = false)
    @Column(name = "movimientoCtacte")
    private BigInteger movimientoCtacte;
    @Basic(optional = false)
    @Column(name = "periodoCtacte")
    private BigInteger periodoCtacte;
    @Basic(optional = false)
    @Column(name = "verificadorCtacte")
    private BigInteger verificadorCtacte;
    @Basic(optional = false)
    @Column(name = "objetoi_id")
    private BigInteger objetoiId;

    public CtactePK() {
    }

    public CtactePK(BigInteger itemCtacte, BigInteger movimientoCtacte, BigInteger periodoCtacte, BigInteger verificadorCtacte, BigInteger objetoiId) {
        this.itemCtacte = itemCtacte;
        this.movimientoCtacte = movimientoCtacte;
        this.periodoCtacte = periodoCtacte;
        this.verificadorCtacte = verificadorCtacte;
        this.objetoiId = objetoiId;
    }

    public BigInteger getItemCtacte() {
        return itemCtacte;
    }

    public void setItemCtacte(BigInteger itemCtacte) {
        this.itemCtacte = itemCtacte;
    }

    public BigInteger getMovimientoCtacte() {
        return movimientoCtacte;
    }

    public void setMovimientoCtacte(BigInteger movimientoCtacte) {
        this.movimientoCtacte = movimientoCtacte;
    }

    public BigInteger getPeriodoCtacte() {
        return periodoCtacte;
    }

    public void setPeriodoCtacte(BigInteger periodoCtacte) {
        this.periodoCtacte = periodoCtacte;
    }

    public BigInteger getVerificadorCtacte() {
        return verificadorCtacte;
    }

    public void setVerificadorCtacte(BigInteger verificadorCtacte) {
        this.verificadorCtacte = verificadorCtacte;
    }

    public BigInteger getObjetoiId() {
        return objetoiId;
    }

    public void setObjetoiId(BigInteger objetoiId) {
        this.objetoiId = objetoiId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCtacte != null ? itemCtacte.hashCode() : 0);
        hash += (movimientoCtacte != null ? movimientoCtacte.hashCode() : 0);
        hash += (periodoCtacte != null ? periodoCtacte.hashCode() : 0);
        hash += (verificadorCtacte != null ? verificadorCtacte.hashCode() : 0);
        hash += (objetoiId != null ? objetoiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtactePK)) {
            return false;
        }
        CtactePK other = (CtactePK) object;
        if ((this.itemCtacte == null && other.itemCtacte != null) || (this.itemCtacte != null && !this.itemCtacte.equals(other.itemCtacte))) {
            return false;
        }
        if ((this.movimientoCtacte == null && other.movimientoCtacte != null) || (this.movimientoCtacte != null && !this.movimientoCtacte.equals(other.movimientoCtacte))) {
            return false;
        }
        if ((this.periodoCtacte == null && other.periodoCtacte != null) || (this.periodoCtacte != null && !this.periodoCtacte.equals(other.periodoCtacte))) {
            return false;
        }
        if ((this.verificadorCtacte == null && other.verificadorCtacte != null) || (this.verificadorCtacte != null && !this.verificadorCtacte.equals(other.verificadorCtacte))) {
            return false;
        }
        if ((this.objetoiId == null && other.objetoiId != null) || (this.objetoiId != null && !this.objetoiId.equals(other.objetoiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CtactePK[ itemCtacte=" + itemCtacte + ", movimientoCtacte=" + movimientoCtacte + ", periodoCtacte=" + periodoCtacte + ", verificadorCtacte=" + verificadorCtacte + ", objetoiId=" + objetoiId + " ]";
    }
    
}
