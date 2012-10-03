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
public class CompararPersonas {

    BaseAccess ba = new BaseAccess();
    ResultSet titular = ba.idTitular();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaJpaController personaJpa = new PersonaJpaController(factory);

    public void compararP() throws SQLException {
        BigInteger cuitInt = null;
        while (titular.next()) {
            String idTitular = titular.getString(1);
            String cuit = titular.getString(2).replace("-", "");
            
            List<Persona> personas = personaJpa.findPersonaEntities();
            for (Iterator<Persona> it = personas.iterator(); it.hasNext();) {
                Persona persona = it.next();
                if (cuit != null && !cuit.contains("_") && cuit.length() == 13) {
                    cuitInt = BigInteger.valueOf(Long.parseLong(cuit));

                    if (persona.getCuil12() == cuitInt) {
                        if (!persona.getOrigen().equals(idTitular)) {
                        
                            System.out.println(cuitInt + " idOrigen:" + idTitular);
                        }
                    }
                    else {
                         System.out.println(cuitInt + " idOrigen:" + idTitular);
                    }

                }
            }
        }
    }
}
