/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionFirebird.ConexionF;
import ConexionSql.BonificacionJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Bonificacion;
import Entidades.Objetoi;
import Entidades.ObjetoiBonificacion;

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
public class AObjetoiBonificacion {

   // Creditos creditos = new Creditos();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    ObjetoiBonificacion objetoiBonificacion = new ObjetoiBonificacion();
    //CreditosJpaController creditosJpa = new CreditosJpaController(factory);
    BonificacionJpaController bonificacionJpa = new BonificacionJpaController(factory);
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    List<Objetoi> objetoi = new ArrayList<Objetoi>();
    Double tasaSubsidio;
    private String bonificadora;
    private String sepyme;
    private String ley;
    private String codigo;

    public void migrarAObjetoibonificacion() throws SQLException {
        ConexionF conexionF = new ConexionF();
        ResultSet resultado = conexionF.conexion();
        List<Bonificacion> bonificacion = new ArrayList<Bonificacion>();
        int enteBonificador;

        while (resultado.next()) {
            
            tasaSubsidio = resultado.getDouble("TASA_SUBSIDIO");
            bonificadora = resultado.getString("BONIFI_ADMINISTRADORA");
            sepyme = resultado.getString("BONIFI_SEPYME");
            ley = resultado.getString("BONIFI_LEY");
            codigo = resultado.getString("CODIGO");
            if (codigo.length() > 9) {
                codigo = codigo.substring(0, 10);
                
                objetoi = objetoiJpa.findByCredito(codigo);
                for (Iterator<Objetoi> it = objetoi.iterator(); it.hasNext();) {
                    Objetoi objetoi1 = it.next();



                    if (bonificadora != null && bonificadora.equalsIgnoreCase("S")) {
                        enteBonificador = 47;
                        bonificacion = bonificacionJpa.buscarBonificacion(tasaSubsidio, enteBonificador);
                        insertar(bonificacion, objetoi1);
                    }
                    if (sepyme != null && sepyme.equalsIgnoreCase("S")) {
                        enteBonificador = 46;
                        bonificacion = bonificacionJpa.buscarBonificacion(tasaSubsidio, enteBonificador);
                        insertar(bonificacion,objetoi1);
                    }

                    if (ley != null && ley.equalsIgnoreCase("S")) {
                        enteBonificador = 48;
                        if (tasaSubsidio == 8.20) {

                            tasaSubsidio = 8.195;
                        } else if (tasaSubsidio == 10.20) {
                            tasaSubsidio = 10.195;

                        } else if (tasaSubsidio == 13.20) {
                            tasaSubsidio = 13.195;
                        }
                        bonificacion = bonificacionJpa.buscarBonificacion(tasaSubsidio, enteBonificador);
                        insertar(bonificacion, objetoi1);

                    }
                }
            }
        }
    }

    public void insertar(List<Bonificacion> bonificacion, Objetoi objetoi1) throws SQLException {
        for (Iterator<Bonificacion> it = bonificacion.iterator(); it.hasNext();) {
            Bonificacion bonificacion1 = it.next();


            objetoiBonificacion.setIdBonificacion(bonificacion1);
           
           // System.out.println("codigo:" + codigo);
            objetoiJpa.insertar("INSERT INTO ObjetoiBonificacion (idCredito,valor, idBonificacion) VALUES(" + objetoi1.getId()+","+bonificacion1.getTasaBonificada()+","+objetoiBonificacion.getIdBonificacion().getId() + ")");
          // System.out.println("INSERT INTO ObjetoiBonificacion (idCredito,valor,idBonificacion) VALUES(" + objetoi1.getId()+","+bonificacion1.getTasaBonificada()+","+objetoiBonificacion.getIdBonificacion().getId() + ")");


        }
    }
}
