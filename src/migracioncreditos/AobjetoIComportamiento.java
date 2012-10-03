/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.ObjetoiComportamientoJpaController;
import ConexionSql.DomicilioJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Objetoi;
import Entidades.ObjetoiComportamiento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class AobjetoIComportamiento {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.estadoProyectos();
    private String idProyecto;
    private List<Objetoi> objetoi;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    DomicilioJpaController domiciliojpa = new DomicilioJpaController(factory);
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    ObjetoiComportamientoJpaController objComportamientoJpa= new ObjetoiComportamientoJpaController(factory);
    private int idEstado;
    ObjetoiComportamiento objetoiComportamiento = new ObjetoiComportamiento();

    public void migrarAobjetoiComportamiento() throws SQLException {
        while (obj.next()) {

            idProyecto = obj.getString(3);
            objetoi = objetoiJpa.findByCredito(idProyecto);
            for (Iterator<Objetoi> it = objetoi.iterator(); it.hasNext();) {
                Objetoi objetoi1 = it.next();
                objetoiComportamiento.setObjetoiId(objetoi1);
                idEstado = obj.getInt(1);
               // System.out.println(idEstado);
                switch (idEstado) {


                    case 1: {
                        objetoiComportamiento.setComportamientoPago("1");
                        objetoiComportamiento.setObservaciones("Estado Migrado (A Codificar)");
                        break;
                    }
                    case 2: {
                        objetoiComportamiento.setComportamientoPago("1");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Situación Normal)");
                        break;

                    }
                    case 3: {
                        objetoiComportamiento.setComportamientoPago("1");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Gestión Extrajudicial)");
                        break;
                    }
                    case 4: {
                        objetoiComportamiento.setComportamientoPago("1");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Gestión Judicial)");
                        break;
                    }
                    case 5: {
                        objetoiComportamiento.setComportamientoPago("1");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Original c/Acuerdo)");
                        break;
                    }
                    case 6: {
                        objetoiComportamiento.setComportamientoPago("1");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Cancelado)");
                        break;
                    }
                    case 7: {
                        objetoiComportamiento.setComportamientoPago("3");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Con Problemas Atraso 91/180 días)");
                        break;
                    }
                    case 8: {
                        objetoiComportamiento.setComportamientoPago("4");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Alto riesgo atraso 181/365 días)");
                        break;
                    }
                    case 9: {
                        objetoiComportamiento.setComportamientoPago("5");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Incobrable)");
                        break;
                    }
                    case 10: {
                        objetoiComportamiento.setComportamientoPago("2");
                        objetoiComportamiento.setObservaciones("Estado Migrado (Inadecuado atraso 61/90 días)");
                        break;
                    }
                }
                objComportamientoJpa.insertar("INSERT INTO ObjetoiComportamiento ( comportamientoPago, observaciones, objetoi_id) VALUES ('"+ objetoiComportamiento.getComportamientoPago()+"','"+objetoiComportamiento.getObservaciones()+"',"+ objetoiComportamiento.getObjetoiId().getId()+")");
                System.out.println("INSERT INTO ObjetoiComportamiento ( comportamientoPago, observaciones, objetoi_id) VALUES ('"+ objetoiComportamiento.getComportamientoPago()+"','"+objetoiComportamiento.getObservaciones()+"',"+ objetoiComportamiento.getObjetoiId().getId()+")");
              //  objetoiJpa.persist(objetoiComportamiento);
               // System.out.println("guarde");
            }

        }
    }
}
