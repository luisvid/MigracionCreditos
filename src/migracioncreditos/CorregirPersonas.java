/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.PersonaJpaController;
import Entidades.Persona;
import java.math.BigInteger;
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
public class CorregirPersonas {

    BaseAccess ba = new BaseAccess();
    ResultSet titular = ba.idTitular();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaJpaController personaJpa = new PersonaJpaController(factory);


    public void agregarOrigenPersonas() throws SQLException {
        while (titular.next()) {
            long cuit;
            // int documento= 
            String armarCuit = titular.getString(2);
            String idTitular = titular.getString(1);
            String dni = titular.getString(4);
            String nombre = titular.getString(3);

            if (armarCuit != null && !armarCuit.contains("_") && armarCuit.length() == 13 && !armarCuit.equals("00-00000000-0")) {
                cuit = Long.parseLong(armarCuit.substring(0, 2) + armarCuit.substring(3, 11) + armarCuit.substring(12, 13));
                List<Persona> personaLista = personaJpa.findXCuit(cuit);

                if (!personaLista.isEmpty()) {

                    for (Iterator<Persona> it = personaLista.iterator(); it.hasNext();) {
                        Persona persona = it.next();
                        persona.setIdorigen(BigInteger.valueOf(Long.parseLong(idTitular)));
                        persona.setOrigen("CREDITOS");
                        //System.out.println("UPDATE PersonaRev SET IDORIGEN =" + persona.getIdorigen() + ", ORIGEN='" + persona.getOrigen() + "' where IDPERSONA= " + persona.getIdpersona());
                        //System.out.println(persona.getNomb12() + " El idTitular es: "+ idTitular);

                    }

                } else {
                    if (!dni.contains("_")) {
                        cuit = Long.parseLong(armarCuit.substring(0, 2) + armarCuit.substring(3, 11) + armarCuit.substring(12, 13));
                        dni = dni.replace(".", "");
                        List<Persona> personasNombre = personaJpa.findXdniNombre(dni, nombre);
                        if (!personasNombre.isEmpty()) {
                            for (Iterator<Persona> it = personasNombre.iterator(); it.hasNext();) {
                                Persona persona = it.next();
                                persona.setCuil12(BigInteger.valueOf(cuit));
                                persona.setOrigen("CREDITOS");
                                persona.setIdorigen(BigInteger.valueOf(Long.parseLong(idTitular)));
                               System.out.println("UPDATE PersonaRev SET CUIL_12=" +persona.getCuil12()+", IDORIGEN =" + persona.getIdorigen() + ", ORIGEN='" + persona.getOrigen() + "' where IDPERSONA= " + persona.getIdpersona());
                               // System.out.println(dni + "  " + nombre);
                            }

                        }
                    }
                }

            }


        }
    }
}
