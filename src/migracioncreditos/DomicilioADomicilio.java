/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.DomicilioJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Domicilio;
import Entidades.DomicilioObjetoi;
import Entidades.Objetoi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class DomicilioADomicilio {

    BaseAccess ba = new BaseAccess();
    ResultSet rsDomiciliosAccess = ba.domicilios();
    private int idLocalidad;
    private EntityManagerFactory emf;
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    // em  = factory.createEntityManager();
    DomicilioJpaController domiciliojpa = new DomicilioJpaController(factory);
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    //DomicilioObjetoiJpaController domObjJpa = new DomicilioObjetoiJpaController(factory);
    private String idProyecto;
    private List<Objetoi> objetoi;
    EntityManager em;
    private Objetoi objeti;
    private int i = 1;

    public void migrarADomicilio() throws SQLException {
        // DEBO AGREGAR EL STRING DE LOCALIDAD  ---->PREGUNTAR SI DEBO AGREGAR EL STRING DE PROVINCIA

        Domicilio domicilio = new Domicilio();
        
        while (rsDomiciliosAccess.next()) {
            
            idProyecto = rsDomiciliosAccess.getString("idProyecto");
            //System.out.println("Calle " + rsDomiciliosAccess.getString("Calle"));

            domicilio.setCalleNom(rsDomiciliosAccess.getString("Calle"));
            domicilio.setNumero(rsDomiciliosAccess.getString("Numero"));
            domicilio.setTipo("Especial");

            idLocalidad = rsDomiciliosAccess.getInt(6);
            // domicilio.setLocalidadIDLOCALIDAD(idLocalidad);
            domicilio.setDepartamentoNom(rsDomiciliosAccess.getString(2));// revisar
            //domiciliojpa.persist(domicilio);
            
            
            objetoi = objetoiJpa.findByCredito(idProyecto);
            

            for (Iterator<Objetoi> it = objetoi.iterator(); it.hasNext();) {
                Objetoi objetoi1 = it.next();

//                //System.out.println("Calle " + rsDomiciliosAccess.getString("Calle"));
//                
//                domicilio.setCalleNom(rsDomiciliosAccess.getString("Calle"));
//                domicilio.setNumero(rsDomiciliosAccess.getString("Numero"));
//                domicilio.setTipo("Especial");
//
//                idLocalidad = rsDomiciliosAccess.getInt(6);
//                // domicilio.setLocalidadIDLOCALIDAD(idLocalidad);
//                domicilio.setDepartamentoNom(rsDomiciliosAccess.getString(2));// revisar
//                //domiciliojpa.persist(domicilio);
                
                DomicilioObjetoi domObjetoi = new DomicilioObjetoi();

                domObjetoi.setDomicilioId(domicilio);
                domObjetoi.setObjetoiId(objetoi1);
                
                domiciliojpa.insertar("INSERT INTO Domicilio ( numero, departamentoNom, calleNom,  localidad_IDLOCALIDAD, tipo, provincia_CODI_08) VALUES ('"+ domicilio.getNumero() + "','" + domicilio.getDepartamentoNom() + "','" + domicilio.getCalleNom() + "'," + idLocalidad + ", '" + domicilio.getTipo() + "'," + 1 + ")");
                domiciliojpa.insertar("INSERT INTO DomicilioObjetoi(domicilio_id, objetoi_id ) VALUES (" + i + "," + domObjetoi.getObjetoiId().getId() + ")");
                //System.out.println("INSERT INTO Domicilio (id, numero, departamentoNom, calleNom,  localidad_IDLOCALIDAD, tipo, provincia_CODI_08) VALUES (" + i + ",'" + domicilio.getNumero() + "','" + domicilio.getDepartamentoNom() + "','" + domicilio.getCalleNom() + "'," + idLocalidad + ", '" + domicilio.getTipo() + "," + 1 + "')");
                //System.out.println("INSERT INTO DomicilioObjetoi(domicilio_id, objetoi_id ) VALUES ("+i+","+ domObjetoi.getObjetoiId().getId()+")"); 
                i++;
                
            } //end for (Iterator<Objetoi>
        
        } //end whilewhile (rsDomiciliosAccess.next())


    }
}
