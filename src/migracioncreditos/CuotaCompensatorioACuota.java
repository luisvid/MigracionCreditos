/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.CuotaJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Cuota;
import Entidades.Objetoi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class CuotaCompensatorioACuota {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.cuotaDeInteresCompensatorio();
    private EntityManager em;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    CuotaJpaController cuotaJpa = new CuotaJpaController(factory);
    private ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    Cuota cuota = new Cuota();

    public void migrarCCACuota() throws SQLException {
        try {
            while (obj.next()) {
                List<Objetoi> obi = objetoiJpa.findByCredito(obj.getString(1));
                for (Iterator<Objetoi> it = obi.iterator(); it.hasNext();) {
                    Objetoi objetoi = it.next();


                    cuota.setFechaVencimiento(obj.getDate(3));
                    cuota.setNumero(obj.getInt(2));
                    cuota.setCreditoId(objetoi);
                    cuotaJpa.insertar("INSERT INTO Cuota (fechaVencimiento,numero, credito_id) VALUES ('" + cuota.getFechaVencimiento() + "', " + cuota.getNumero() + ", " + cuota.getCreditoId().getId() + ")");
                    //System.out.println("INSERT INTO Cuota (fechaVencimiento,numero, credito_id) VALUES ('" + cuota.getFechaVencimiento() + "', " + cuota.getNumero() + ", " + cuota.getCreditoId().getId() + ")");


                }

            }
        } finally {
            cuotaJpa.closeEEntityManager();
            objetoiJpa.closeEEntityManager();
        }
    }
}
