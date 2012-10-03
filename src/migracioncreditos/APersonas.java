/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.PersonaJpaController;
import Entidades.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class APersonas {

    BaseAccess ba = new BaseAccess();
    ResultSet titular = ba.titular();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaJpaController personaJpa = new PersonaJpaController(factory);

    public void migrarPersonas() throws SQLException {
        String creditos = "CREDITOS";
        long cuit = 0;
        long dni = 0;
        String dniStr = "";
        String cuitStr = "";
        String nombre = "";
        String sql = "";
        List<Persona> personaLista = new ArrayList<Persona>();
        int count = 0;

        try {

            while (titular.next()) {
                nombre = titular.getString(2);

                if (nombre.equalsIgnoreCase("A COMPLETAR")) {
                    continue;
                }

                dniStr = titular.getString(5);
                cuitStr = titular.getString(3);

                if (dniStr == null) {
                    dni = 0;
                } else {
                    dniStr = dniStr.replaceAll("-", "");
                    dniStr = dniStr.replaceAll("_", "");
                    dniStr = dniStr.replaceAll("\\.", "");
                    if (dniStr.equals("")) {
                        dni = 0;
                    } else {
                        dni = Long.parseLong(dniStr);
                    }
                }

                if (cuitStr == null) {
                    cuit = 0;
                } else {
                    cuitStr = cuitStr.replaceAll("-", "");
                    cuitStr = cuitStr.replaceAll("_", "");
                    if (cuitStr.equals("")) {
                        cuit = 0;
                    } else {
                         //System.out.println(cuitStr);
                         cuit = Long.parseLong(cuitStr.trim());
                    }
                }

                if (dni == 0 && cuit == 0) {
                    personaLista = personaJpa.findXNombre(nombre);
                } else {
                    personaLista = personaJpa.findXCuitOrDni(cuit, dni);
                }
                
               
                String calle = titular.getString(6);
                if (calle == null)
                    calle = "";
                
                Integer numero = titular.getInt(7);
                if(numero  == null)
                    numero = 0;
                
                String piso = titular.getString(8);
                if(piso == null)
                    piso = "";
                
                String depto = titular.getString(9);
                if(depto == null)
                    depto = "";

                String localidad = titular.getString(14);
                if(localidad == null)
                    localidad = "";
                
                if (personaLista.isEmpty()) {
                    
                    sql = "INSERT INTO PERSONA(NOMB_12,ZONA_12,NUDO_12,CEDU_12,CPOS_12,CUIL_12,CODI_08,CODI_47,IDLOCALIDAD"
                            + ",CALLE,NUMERO,PISO,DPTO,LOCALIDAD,IDORIGEN,ORIGEN) VALUES('" + nombre + "','" + 0
                            + "'," + dni + "," + 0 + ",'" + 0 + "'," + cuit + "," + titular.getInt(10) + ","
                            + titular.getInt(4) + "," + titular.getInt(15) + ",'" + calle + "','" + Integer.toString(numero) + "','"
                            + piso + "','" + depto + "','" + localidad + "'," + titular.getInt(1)
                            + ",'" + creditos + "')";

                    
                    personaJpa.insertar(sql);
                } else {
                    //existe
                    count++;
                    //System.out.println("Existe: " + count);
                    //System.out.println("este falla : " + armarCuit);
                }
            }
        } finally {
            personaJpa.closeEntityManager();
            
        }
    }
}
