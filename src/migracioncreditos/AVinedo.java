/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionFirebird.ConexionF;
import ConexionSql.VinedoJpaController;
import Entidades.Vinedo;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class AVinedo {

    ConexionF conexionF = new ConexionF();
    ResultSet numeroInv = conexionF.numInv();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    ResultSet datosFincas;
    Vinedo vinedo = new Vinedo();
    VinedoJpaController vinedoJpa = new VinedoJpaController(factory);

    public void migrarFincas() throws SQLException {
        String localidad;
        String observaciones;
        while (numeroInv.next()) {
            String nroInv = numeroInv.getString("NRO_INV");

            datosFincas = conexionF.buscarFincas(nroInv);
         //   System.out.println(nroInv);
            
            while (datosFincas.next()) {
                vinedo.setCodigo(datosFincas.getString("NRO_INV"));
                vinedo.setDepartamento(datosFincas.getString("DESCRIP"));
                localidad = datosFincas.getString("LOCALIDAD");
                observaciones = datosFincas.getString("OBSERVACIONES");
                vinedo.setHectareas(datosFincas.getDouble("HECTAREAS"));
                if (!localidad.equalsIgnoreCase("") && !observaciones.equalsIgnoreCase("")) {
                    vinedo.setObservaciones(observaciones + " - " + localidad);
                 }else if (!localidad.equalsIgnoreCase("")) {
                    vinedo.setObservaciones(localidad);
                    
                } else if (!observaciones.equals("")){
                    vinedo.setObservaciones(observaciones);
                }
//                System.out.println("INSERT INTO Vinedo(codigo,departamento, hectareas, observaciones) VALUES ('" + vinedo.getCodigo() + "','" + vinedo.getDepartamento() + "'," + vinedo.getHectareas()+ ",'"+ vinedo.getObservaciones() + "')");

                  vinedoJpa.insertar("INSERT INTO Vinedo(codigo,departamento,observaciones) VALUES ('"+ vinedo.getCodigo()+"','"+ vinedo.getDepartamento()+"','"+vinedo.getObservaciones()+"')");
                break;
            }

        }

    }
}
