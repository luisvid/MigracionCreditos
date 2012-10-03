/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionFirebird.ConexionF;
import ConexionSql.BonificacionJpaController;
import ConexionSql.CuotaJpaController;
import ConexionSql.ObjetoiBonificacionJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.BonDetalle;
import Entidades.Bonificacion;
import Entidades.Cuota;
import Entidades.Objetoi;
import java.math.BigDecimal;
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
public class ABonDetalle {

    ConexionF conexionF = new ConexionF();
    ResultSet resultado = conexionF.conexionPlan();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    CuotaJpaController cuotaJpa = new CuotaJpaController(factory);
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    ObjetoiBonificacionJpaController oBonifJpa = new ObjetoiBonificacionJpaController(factory);
    private String codigo;
    BonificacionJpaController bonificacionJpa = new BonificacionJpaController(factory);
    short s;

    public void migrarABonDetalle() throws SQLException {
        double tasaSubsidio;
        String bonificadora;
        int enteBonificador;
        BigDecimal pP;
        BigDecimal cred;
        Bonificacion bonificaciones = new Bonificacion();
        String pagada;
        // Recorriendo PLAN_PAGO_CUOTA, obtener cuota_id que le corresponde:
        while (resultado.next()) {
            //1) Obtener el PLAN_PAGO al que pertenece el PLAN_PAGO_CUOTA PLAN_PAGO_CUOTA.PLAN_PAGO_NURI-->PLAN_PAGO
            pP = resultado.getBigDecimal("PLAN_PAGO_NURI");
            int tipo = resultado.getInt("Tipo");

            //  if (pP != null) {
            //    ResultSet planPago = conexionF.buscarPlanPago(pP);
            //    while (planPago.next()) {
            //    2) Obtener el CODIGO del CREDITO al que pertenece el PLAN_PAGO PLAN_PAGO.NURI-->CREDITO.PLAN_NURI-->CREDITO.CODIGO
            //        cred = planPago.getBigDecimal(1);
            //        if (cred != null) {
            //            ResultSet creditos = conexionF.buscarCredito(cred);
            //            while (creditos.next()) {
            //3) Obtener el Objetoi correspondiente al CREDITO CREDITO.CODIGO--> Objetoi.numeroCredito
            codigo = resultado.getString("Codigo");
            if (codigo.length() > 9) {
                codigo = codigo.substring(0, 10);
                List<Objetoi> objetosi = objetoiJpa.findByCredito(codigo);
                for (Iterator<Objetoi> it = objetosi.iterator(); it.hasNext();) {
                    Objetoi objetoi = it.next();
                    //4) Obtener todas las cuotas del Objetoi Cuota.credito_id == Objetoi.id
                    List<Cuota> cuotas = cuotaJpa.finCuotasCredito(objetoi);
                    for (Iterator<Cuota> it1 = cuotas.iterator(); it1.hasNext();) {

                        Cuota cuota = it1.next();
                        //5) Obtener de la lista de Cuota del Objetoi, el id de la Cuota correspondiente PLAN_PAGO_CUOTA.NRO_CTA == Cuota.numero-->Cuota.id
                        int ppcNroCuota = resultado.getInt("NRO_CTA");
                        int c = cuota.getNumero();
                        if (ppcNroCuota == c) {
                            BonDetalle bonDetalle = new BonDetalle();
                            bonDetalle.setCuotaId(cuota);
                            bonDetalle.setFechaBonificada(resultado.getDate("FECHA_VTO"));

                            bonDetalle.setMonto(resultado.getDouble("CAPITAL"));

                            bonificaciones = verificarBonificacion(resultado);
                            bonDetalle.setBonificacionId(bonificaciones);
                            pagada = resultado.getString("PAGADA");
                            if (pagada != null) {
                                if (pagada.equalsIgnoreCase("S")) {
                                    s = 1;
                                    bonDetalle.setEsEfectiva(s);
                                }
                            } else {
                                s = 0;
                                bonDetalle.setEsEfectiva(s);
                            }
                            //System.out.println("Este es el id: "+resultado.getObject(1));
                            bonificacionJpa.insertar("INSERT INTO BonDetalle(esEfectiva,fechaBonificada,monto,cuota_id, objetoiBonificacion)VALUES "
                                    + "(" + bonDetalle.getEsEfectiva() + ",'" + bonDetalle.getFechaBonificada() + "'," + bonDetalle.getMonto()  + ", " + bonDetalle.getCuotaId().getId() + "," + bonDetalle.getBonificacionId().getId()+ ")");

                            // System.out.println("INSERT INTO BonDetalle(esEfectiva,fechaBonificada,monto,bonificacion_id,cuota_id)VALUES "
                            //         + "(" + bonDetalle.getEsEfectiva() + ",'" + bonDetalle.getFechaBonificada() + "'," + bonDetalle.getMonto() + "," + bonDetalle.getBonificacionId().getId() + " " + bonDetalle.getCuotaId().getId() + ")");
                            break;
                            //******************** bonificacion_id saqué porque en la tabla no figura, ver si es lo mismo que objetoibonificacion
                        }
                    }

                }

                //    }

            }
        }

    }
    //    }
    // }
    // }

    private Bonificacion verificarBonificacion(ResultSet resultado) throws SQLException {
        double tasaSubsidio = resultado.getDouble("TASA_SUBSIDIO");
        String bonificadora = resultado.getString("BONIFI_ADMINISTRADORA");
        String sepyme = resultado.getString("BONIFI_SEPYME");
        String ley = resultado.getString("BONIFI_LEY");
        List<Bonificacion> bonificacion = new ArrayList<Bonificacion>();
        Bonificacion bonifica = new Bonificacion();
        //   while (resultado.next()) {
        if (bonificadora != null && bonificadora.equalsIgnoreCase("S")) {
            int enteBonificador = 47;
            bonificacion = bonificacionJpa.buscarBonificacion(tasaSubsidio, enteBonificador);
            bonifica = verBonificacion(bonificacion);

            //      }
        }
        if (sepyme != null && sepyme.equalsIgnoreCase("S")) {
            int enteBonificador = 46;
            bonificacion = bonificacionJpa.buscarBonificacion(tasaSubsidio, enteBonificador);
            bonifica = verBonificacion(bonificacion);

        }

        if (ley != null && ley.equalsIgnoreCase("S")) {
            int enteBonificador = 48;
            if (tasaSubsidio == 8.20) {

                tasaSubsidio = 8.195;
            } else if (tasaSubsidio == 10.20) {
                tasaSubsidio = 10.195;

            } else if (tasaSubsidio == 13.20) {
                tasaSubsidio = 13.195;
            }
            bonificacion = bonificacionJpa.buscarBonificacion(tasaSubsidio, enteBonificador);
            bonifica = verBonificacion(bonificacion);
        }
        return bonifica;
    }

    private Bonificacion verBonificacion(List<Bonificacion> bonificacion) {
        for (Iterator<Bonificacion> it = bonificacion.iterator(); it.hasNext();) {
            Bonificacion bonificacion1 = it.next();
            //  System.out.println(bonificacion1.getId());
            return bonificacion1;
        }
        return null;
    }
}
