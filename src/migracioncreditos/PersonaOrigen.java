/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.PersonaRevJpaController;
import Entidades.PersonaRev;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class PersonaOrigen {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.buscarOrigen();
    private EntityManagerFactory emf;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaRevJpaController perJpa = new PersonaRevJpaController(factory);

    public void migrarOrigen() {


        try {
            while (obj.next()) {
                String cuitI = obj.getString("Cuit").replace("-", "");
                cuitI = cuitI.replace("_", "0");
                cuitI = cuitI.replace(" ", "");
                BigInteger cuit = BigInteger.valueOf(Long.parseLong(cuitI));
                String idTitular = obj.getString("idTitular");
                PersonaRev per = perJpa.findPersonaRev((BigDecimal.valueOf(Integer.parseInt(idTitular))));
                String nombre = obj.getString(3);

                if (per != null) {
                    BigInteger cuil = per.getCuil12();
                    if (nombre.contains("A COMPLETAR")) {
                      //  System.out.println(idTitular + " " + "Este idTitular es A COMPLETAR");

                    } else if (cuit.equals(cuil)) {
                        per.setOrigen(idTitular);
                        per.setOrigen("ORIGEN");
                         System.out.println ("Update PersonaRev set IDORIGEN='"+idTitular +"', ORIGEN= 'CREDITOS' where IDPERSONA="+ per.getIdpersona()); 
                                                    
                    } else {
                        //      System.out.println(idTitular +"  "+ cuit +" "+ per.getCuil12() +" "+"Este cuit  no coincide con el id titular en la base");
                    }
                } else {

                    if (!nombre.contains("A COMPLETAR") && (!idTitular.equals("0") && nombre.equals("NULL"))) {
                        String dni = obj.getString("NroDocumento").replace(".", "");
                        //  perJpa.insertar("INSERT INTO [MIGRA3_CRED_FTYC].[dbo].[PersonaRev]([IDPERSONA],[NUDO_12],[CUIL_12],"
                        //        + "[IDORIGEN],[ORIGEN])VALUES("+ idTitular +",'"+dni.replace("_","")+"','" + cuit +"','"+ idTitular +"','"+"ORIGEN" + "')");

                        //perJpa.insertar("UPDATE [MIGRA3_CRED_FTYC].[dbo].[PersonaRev] SET [NOM_12] = <NOM_12, varchar(255),>

                        System.out.println("INSERT INTO [MIGRA3_CRED_FTYC].[dbo].[PersonaRev]([IDPERSONA],[NUDO_12],[CUIL_12],"
                                + "[IDORIGEN],[ORIGEN])VALUES(" + idTitular + ",'" + dni.replace("_", "") + "','" + cuit + "','" + idTitular + "','" + "ORIGEN" + "')");
                    } else if (nombre.contains("A COMPLETAR")) {
//                        System.out.println(idTitular + " " + "Este idTitular es A COMPLETAR");

                    }
                }



            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaOrigen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
