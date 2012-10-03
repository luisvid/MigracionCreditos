/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.DesembolsoJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Desembolso;
import Entidades.Objetoi;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class DesembolsosADesembolso {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.desembolsos();
    // List<Desembolso> desembolsos = new ArrayList<Desembolso>();
    Desembolso desemb = new Desembolso();
    private Date fecha;
    Objetoi objetoi = new Objetoi();
    private BigDecimal nroCredito;
    private EntityManager em;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    //em  = factory.createEntityManager();
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    DesembolsoJpaController desembolso = new DesembolsoJpaController(factory);

    public void migrarADesembolso() throws SQLException {

        String credito = null;

        while (obj.next()) {

            List<Objetoi> obi = objetoiJpa.findByCredito(obj.getString("IDProyecto"));
            fecha = obj.getDate("Fecha");
            double importe = obj.getDouble("Importe");
            int nroDesembolso = obj.getInt(4);
            String nroTransferencia= obj.getString(5);
            double importeReal = obj.getDouble(6);
            for (Iterator<Objetoi> it = obi.iterator(); it.hasNext();) {
                Objetoi objetoi1 = it.next();
                desemb.setFecha(fecha);
                desemb.setFechaReal(fecha);
                desemb.setImporte(importe);
                desemb.setNumero(nroDesembolso);
                desemb.setObservacion("NroTransferencia" + " " + nroTransferencia);
                desemb.setImporteReal(importeReal);
                desemb.setEstado("2");
                credito = objetoi1.getId().toString();
                //desembolso.persist(desemb);
                // }
                desembolso.insertar("INSERT INTO Desembolso (importeReal, importe,  observacion,  fechaReal, fecha, estado,  numero,  credito_id) VALUES (" + desemb.getImporteReal() + ", " + desemb.getImporte() + ", '" + desemb.getObservacion() + "', '" + desemb.getFechaReal() + "', '" + desemb.getFecha() + "', '" + desemb.getEstado() + "', " + desemb.getNumero() + ", '" + credito + "')");
                // System.out.println("INSERT INTO Desembolso (importeReal, importe,  observacion,  fechaReal, fecha, estado,  numero,  credito_id) VALUES (" + desemb.getImporteReal() + ", " + desemb.getImporte() + ", '" + desemb.getObservacion() + "', '" + desemb.getFechaReal() + "', '" + desemb.getFecha() + "', '" + desemb.getEstado() + "', " + desemb.getNumero() + ", '" + credito + "')");


            }
        }


    }
}
