/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.CtacteJpaController;
import ConexionSql.CuotaJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Ctacte;
import Entidades.Cuota;
import Entidades.Objetoi;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class CuotaCapitalACtaCte {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.cuotaDeCapital();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    CtacteJpaController ctaCteJpa = new CtacteJpaController(factory);
    Ctacte ctaCte = new Ctacte();
    List<Cuota> cuotaCredito = new ArrayList<Cuota>();
    CuotaJpaController cuotaJpa = new CuotaJpaController(factory);
    private int asociado_id;
    private int facturado_id;
    private int tipoMov_id;
    private List<Cuota> cuotasLista;
    private int verificadorCtaCte;
    private int itemCtaCte;
    //private int numero;
    private int movimientoCtaCte = 1;
    private Date fechaVencimiento;
    private Date fechaDesembolso;
    private int year;
    private Date fechaNull;

    public void migrarCuotaDeCapital() throws SQLException {
        try {
            while (obj.next()) {
                List<Objetoi> obi = objetoiJpa.findByCredito(obj.getString(2));
                for (Iterator<Objetoi> it = obi.iterator(); it.hasNext();) {
                    Objetoi objetoi = it.next();
                    //  numero = obj.getInt(1);
                    int numero = 0;
                    numero += ba.calcularGracia();
                    cuotasLista = cuotaJpa.finCuotasCredito(objetoi);
                    for (Iterator<Cuota> it1 = cuotasLista.iterator(); it1.hasNext();) {
                        Cuota cuotasi = it1.next();



                        if (cuotasi.getNumero() == numero) {
                            fechaVencimiento = obj.getDate(3);
                            ctaCte.setFechaVencimiento(fechaVencimiento);
                            fechaDesembolso = obj.getDate(5);
                            ctaCte.setFechaGeneracion(fechaDesembolso);

                            year = fechaDesembolso.getYear() + 1900;
                            //System.out.println(year+1900);
                            ctaCte.setImporte(obj.getDouble(4));
                            ctaCte.setObjetoi(objetoi);
                            asociado_id = 1;
                            itemCtaCte = 1;
                            verificadorCtaCte = 0;
                            facturado_id = 1;
                            tipoMov_id = 2;
                            ctaCte.setTipoMovimiento("Cuota");
                            ctaCte.setUsuariocauserK("Migracion");

                            ctaCte.setCuotaId(cuotasi);

                            if (ctaCte.getFechaGeneracion() == null) {

                                ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte, verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                //System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte, verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");


                            } else {
                                ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte, verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                //System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte, verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                            }

                        }
                    }






                }
            }
        } finally {
            cuotaJpa.closeEEntityManager();
            objetoiJpa.closeEEntityManager();
        }



    }
}
