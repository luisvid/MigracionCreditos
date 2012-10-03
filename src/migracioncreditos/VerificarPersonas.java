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
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class VerificarPersonas {

    BaseAccess ba = new BaseAccess();
    ResultSet titular = ba.idTitular();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaJpaController personaJpa = new PersonaJpaController(factory);

    public void verPersonas() throws SQLException {

        while (titular.next()) {
            long cuit;
            String armarCuit = titular.getString(2);
            String idTitular = titular.getString(1);
            String dni = titular.getString(4);
            String nombre = titular.getString(3);
            if (armarCuit != null && !armarCuit.contains("_") && armarCuit.length() == 13) {
                cuit = Long.parseLong(armarCuit.substring(0, 2) + armarCuit.substring(3, 11) + armarCuit.substring(12, 13));
                List<Persona> personaLista = personaJpa.findXCuitNombre(cuit, nombre);
                if (personaLista.isEmpty()) {
                    if (!dni.contains("_")) {
                        dni = dni.replace(".", "");
                        List<Persona> personasDoc = personaJpa.findXdniNombre(dni, nombre);
                        if (personasDoc.isEmpty()) {
                            List<Persona> personaRazonSocial = personaJpa.findXCuitRazonSocial(cuit, nombre);
                            if (personaRazonSocial.isEmpty()) {
                                System.out.println(nombre + " " + dni + " " + cuit + " " + idTitular);
                            }

                        }
                    }
                }
            }
        }
    }
}
