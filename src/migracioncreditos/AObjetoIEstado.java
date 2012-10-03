/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.ObjetoiEstadoJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Objetoi;
import Entidades.ObjetoiEstado;
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
public class AObjetoIEstado {
    
    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.estadoProyectos();
    private String idProyecto;
    private List<Objetoi> objetoi;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    private ObjetoiEstado objetoiEstado = new ObjetoiEstado();
    ObjetoiEstadoJpaController objEstadoJpa = new ObjetoiEstadoJpaController(factory);
    private int idEstado;
    private int estado;
    
    public void migrarAEstado() throws SQLException{
        while (obj.next()) {

            idEstado = obj.getInt(1); //acá!
            
            idProyecto = obj.getString(3);
            objetoi = objetoiJpa.findByCredito(idProyecto);
            
            for (Iterator<Objetoi> it = objetoi.iterator(); it.hasNext();) {
                Objetoi objetoi1 = it.next();
                objetoiEstado.setObjetoiId(objetoi1);
                
               // System.out.println(idEstado);
                switch (idEstado) {


                    case 1: {
                        estado=13;
                        objetoiEstado.setObservacion("Estado Migrado (A Codificar)");
                        break;
                    }
                    case 2: {
                        estado=13;
                        objetoiEstado.setObservacion("Estado Migrado (Situación Normal)");
                        break;

                    }
                    case 3: {
                        estado=30;
                        objetoiEstado.setObservacion("Estado Migrado (Gestión Extrajudicial)");
                        break;
                    }
                    case 4: {
                        estado=31;
                        objetoiEstado.setObservacion("Estado Migrado (Gestión Judicial)");
                        break;
                    }
                    case 5: {
                        estado=19;
                        objetoiEstado.setObservacion("Estado Migrado (Original c/Acuerdo)");
                        break;
                    }
                    case 6: {
                        estado=21;
                        objetoiEstado.setObservacion("Estado Migrado (Cancelado)");
                        break;
                    }
                    case 7: {
                        estado= 35;
                        objetoiEstado.setObservacion("Estado Migrado (Con Problemas Atraso 91/180 días)");
                        break;
                    }
                    case 8: {
                        estado = 35;
                        objetoiEstado.setObservacion("Estado Migrado (Alto riesgo atraso 181/365 días)");
                        break;
                    }
                    case 9: {
                        estado= 35;
                        objetoiEstado.setObservacion("Estado Migrado (Incobrable)");
                        break;
                    }
                    case 10: {
                        estado=35;
                        objetoiEstado.setObservacion("Estado Migrado (Inadecuado atraso 61/90 días)");
                        break;
                    }
                }
                objEstadoJpa.insertar("INSERT INTO ObjetoiEstado (observacion, objetoi_id, estado_idEstado) VALUES ('"+ objetoiEstado.getObservacion()+"',"+ objetoiEstado.getObjetoiId().getId()+"," + estado+")");
               // System.out.println("INSERT INTO ObjetoiEstado (observacion, objetoi_id, estado_idEstado) VALUES ('"+ objetoiEstado.getObservacion()+"',"+ objetoiEstado.getObjetoiId().getId()+"," + estado+")");
              
            }
        }
    
        
        
    }
    
}
