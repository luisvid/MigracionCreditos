/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.DomicilioJpaController;
import ConexionSql.PersonaRevJpaController;
import Entidades.Domicilio;
import Entidades.PersonaRev;
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
public class CompPersNombre {

    BaseAccess ba = new BaseAccess();
    ResultSet titular = ba.titularCuitValido();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaRevJpaController personaRevJpa = new PersonaRevJpaController(factory);
    DomicilioJpaController domicilioJpa = new DomicilioJpaController(factory);
    public void compararPorNombre() throws SQLException {
       while (titular.next()) {
            String nombre = titular.getString(2);
            String calle = titular.getString(3).replace("-", "");
            String nro = titular.getString(4).replace(".", "");
            if (!nro.contains("_")) {
                List<PersonaRev> personaLista = personaRevJpa.findXNombre(nombre);
                for (Iterator<PersonaRev> it = personaLista.iterator(); it.hasNext();) {
                    PersonaRev personaRev = it.next();
                    List<Domicilio> domicilioPersona=  domicilioJpa.findXPersona(personaRev);
                    for (Iterator<Domicilio> it1 = domicilioPersona.iterator(); it1.hasNext();) {
                        Domicilio domicilio = it1.next();
                        System.out.println(personaRev.getNom12()+ domicilio.getCalleNom() + domicilio.getNumero());
                    } 
                }
            }
        }
    }
}
