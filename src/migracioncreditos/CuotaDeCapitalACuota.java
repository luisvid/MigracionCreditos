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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class CuotaDeCapitalACuota {

    BaseAccess ba = new BaseAccess();
    ResultSet cuotasCapital = ba.cuotaDeCapital();
    private EntityManager em;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    CuotaJpaController cuotaJpa = new CuotaJpaController(factory);
    List<Cuota> cuotasLista = new ArrayList<Cuota>();
    private ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    private double capital;
    private int numero;
    private String idProyecto = "0";
    private int cuotaDistinta = 0;
    private String idProyectoAnterior = "1";
    List<Objetoi> obi = new ArrayList<Objetoi>();
    private int estaInsertada = 0;
    private int elementosLista;

    public void migrarACuota() throws SQLException {
        Cuota cuota = new Cuota();
        Date fechaVencimiento;

        try {
            while (cuotasCapital.next()) {
                idProyecto = cuotasCapital.getString(2);
                // Comparar idProyecto siguiente con el idProyecto anterior si son distintos buscar, sino no
                if (!idProyectoAnterior.equals(idProyecto)) {
                    obi = objetoiJpa.findByCredito(idProyecto);
                }
                fechaVencimiento = cuotasCapital.getDate(3);
                capital = cuotasCapital.getDouble(4);
                numero = cuotasCapital.getInt(1);
                //System.out.println(idProyecto);
                for (Iterator<Objetoi> it = obi.iterator(); it.hasNext();) {
                    Objetoi objetoi = it.next();
                    cuotasLista = cuotaJpa.finCuotasCredito(objetoi);
                    elementosLista = 0;
                    for (Iterator<Cuota> it1 = cuotasLista.iterator(); it1.hasNext();) {

                        Cuota cuotai = it1.next();

                        if ((cuotai.getFechaVencimiento().equals(fechaVencimiento))) { // ver por las dudas corroborar que lo haga por idProyecto
                            estaInsertada = 1;
                            //GUARDAR IMPORTE EN LA CUOTA: Cuota.capital
                            cuota.setCapital(capital);
                            cuota.setCreditoId(objetoi);
                            cuotaJpa.insertar("UPDATE Cuota SET [capital] = " + cuota.getCapital() + " WHERE [numero] = " + cuotai.getNumero() + " AND [credito_id] = " + cuota.getCreditoId().getId() + ";");
                            // System.out.println("UPDATE Cuota SET [capital] = " + cuota.getCapital() + " WHERE [numero] = "+ cuota.getNumero() + " AND [credito_id] = "+ cuota.getCreditoId().getId() + ";");
                            break;
                        }
                    }
                    if (estaInsertada != 1) {
                        cuota.setCapital(capital);
                        cuota.setFechaVencimiento(fechaVencimiento);
                        cuota.setNumero(numero);
                        cuota.setCreditoId(objetoi);

                        //GUARDAR DATOS EN ARCHIVO LOG
                        idProyectoAnterior = idProyecto;

//                        System.out.println("INSERT INTO Cuota ([capital],[fechaVencimiento],[numero], [credito_id]) VALUES (" + cuota.getCapital() + ", '" + cuota.getFechaVencimiento() + "', " + cuota.getNumero() + ", " + cuota.getCreditoId().getId() + ")");
                    }
                }
            }
        }finally{
            cuotaJpa.closeEEntityManager();
            objetoiJpa.closeEEntityManager();
        }
    }
}
