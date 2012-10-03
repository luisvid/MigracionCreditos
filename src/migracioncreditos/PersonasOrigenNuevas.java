/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.PersonaRevJpaController;
import Entidades.PersonaRev;
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
public class PersonasOrigenNuevas {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.buscarOrigenNuevo();
    private EntityManagerFactory emf;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaRevJpaController perJpa = new PersonaRevJpaController(factory);

    public void migrarOrigenNuevos() throws SQLException {



        while (obj.next()) {
            String cuitI = obj.getString("Cuit").replace("-", "");
            cuitI = cuitI.replace("_", "0");
            cuitI = cuitI.replace(" ", "");
            Long cuit = Long.parseLong(cuitI);
            String idTitular = obj.getString("idTitular");
            Long comp= new Long("0");
            if (!cuit.equals(comp)){
            List<PersonaRev> per = perJpa.findXCuit(cuit);
           
            String nombre = obj.getString(3);

            if (per != null) {
                for (Iterator<PersonaRev> it = per.iterator(); it.hasNext();) {
                    PersonaRev personaRev = it.next();
                 //   if (personaRev.getNom12().equals(nombre)){
                     
                  //   System.out.println(cuit + " "+ personaRev.getCuil12()+" " +idTitular + " " + personaRev.getIdpersona()+" "+nombre + "||"+ personaRev.getNom12());
                  //SOLO TENGO QUE ACTUALIZAR EL IDORIGEN Y ORIGEN
                     
                  System.out.println ("Update PersonaRev set IDORIGEN='"+idTitular +"', ORIGEN= 'CREDITOS' where IDPERSONA="+ personaRev.getIdpersona()); 

            }
        }
    }
}
}
}
