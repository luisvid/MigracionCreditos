/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionFirebird.ConexionF;
import ConexionSql.PersonaJpaController;
import Entidades.Cuentabancaria;
import Entidades.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class ACuentaBancaria {

    ConexionF conexion = new ConexionF();
    private int nroPersona;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaJpaController personaJpa = new PersonaJpaController(factory);
    // Persona persona = new Persona();

    public void cuentaBancaria() throws SQLException {
        ResultSet datosCuenta = conexion.conexionCosecha();
        String cuit;
        String cuitPersona = null;
        List<Persona> personas = new ArrayList();
        int cantCarCuit;
        while (datosCuenta.next()) {
            nroPersona = datosCuenta.getInt("PERS_NURI");
            ResultSet conexionPersona = conexion.buscarPersonas(nroPersona);

            while (conexionPersona.next()) {
                cuit = conexionPersona.getString(1);
               

                    cuitPersona = cuit.replaceAll("-","");
                   // System.out.println(cuitPersona);
                                                        
                personas = personaJpa.findXCuit(Long.valueOf(cuitPersona));
                for (Iterator<Persona> it = personas.iterator(); it.hasNext();) {
                    Persona persona1 = it.next();


                    persona1.getIdpersona();
                    Cuentabancaria cuenta = new Cuentabancaria();

                    cuenta.setCbu(datosCuenta.getString("CBU"));
                    cuenta.setNroCuentaBancaria(datosCuenta.getString("NRO_CUENTA"));
                    cuenta.setBanco(datosCuenta.getString("BANCO_NURI"));
                    cuenta.setTipoCuentaBancaria("0" + datosCuenta.getString("TIPO_CTA_NURI"));
                    
                    System.out.println("INSERT INTO cuentabancaria(cbu,nroCuentaBancaria,persona_IDPERSONA"
                            + ",banco,tipoCuentaBancaria) VALUES('" + cuenta.getCbu() + "','" + cuenta.getNroCuentaBancaria()
                           + "'," + persona1.getIdpersona() + ",'" + cuenta.getBanco() + "','" + cuenta.getTipoCuentaBancaria() + "')");

                  //  personaJpa.insertar("INSERT INTO cuentabancaria(cbu,nroCuentaBancaria,persona_IDPERSONA"
                  //          + ",banco,tipoCuentaBancaria) VALUES('" + cuenta.getCbu() + "','" + cuenta.getNroCuentaBancaria()
                  //          + "'," + persona1.getIdpersona() + ",'" + cuenta.getBanco() + "','" + cuenta.getTipoCuentaBancaria() + "')");

                }

            }

        }

    }
}
