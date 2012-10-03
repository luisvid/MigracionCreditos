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
public class BoletoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "numeroBoleto")
    private BigInteger numeroBoleto;
    @Basic(optional = false)
    @Column(name = "periodoBoleto")
    private BigInteger periodoBoleto;
    @Basic(optional = false)
    @Column(name = "verificadorBoleto")
    private BigInteger verificadorBoleto;

    public BoletoPK() {
    }

    public BoletoPK(BigInteger numeroBoleto, BigInteger periodoBoleto, BigInteger verificadorBoleto) {
        this.numeroBoleto = numeroBoleto;
        this.periodoBoleto = periodoBoleto;
        this.verificadorBoleto = verificadorBoleto;
    }

    public BigInteger getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(BigInteger numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public BigInteger getPeriodoBoleto() {
        return periodoBoleto;
    }

    public void setPeriodoBoleto(BigInteger periodoBoleto) {
        this.periodoBoleto = periodoBoleto;
    }

    public BigInteger getVerificadorBoleto() {
        return verificadorBoleto;
    }

    public void setVerificadorBoleto(BigInteger verificadorBoleto) {
        this.verificadorBoleto = verificadorBoleto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroBoleto != null ? numeroBoleto.hashCode() : 0);
        hash += (periodoBoleto != null ? periodoBoleto.hashCode() : 0);
        hash += (verificadorBoleto != null ? verificadorBoleto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoletoPK)) {
            return false;
        }
        BoletoPK other = (BoletoPK) object;
        if ((this.numeroBoleto == null && other.numeroBoleto != null) || (this.numeroBoleto != null && !this.numeroBoleto.equals(other.numeroBoleto))) {
            return false;
        }
        if ((this.periodoBoleto == null && other.periodoBoleto != null) || (this.periodoBoleto != null && !this.periodoBoleto.equals(other.periodoBoleto))) {
            return false;
        }
        if ((this.verificadorBoleto == null && other.verificadorBoleto != null) || (this.verificadorBoleto != null && !this.verificadorBoleto.equals(other.verificadorBoleto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BoletoPK[ numeroBoleto=" + numeroBoleto + ", periodoBoleto=" + periodoBoleto + ", verificadorBoleto=" + verificadorBoleto + " ]";
    }
    
}
