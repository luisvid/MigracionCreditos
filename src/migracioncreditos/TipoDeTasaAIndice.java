/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.IndiceJpaController;
import Entidades.Indice;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class TipoDeTasaAIndice {

    int i = 1;
    private EntityManager em;

    void migrarTipoDeTasaAIndice() throws SQLException {

        BaseAccess ba = new BaseAccess();
        ResultSet obj = ba.tiposDeTasa();
        List<Indice> ind = new ArrayList<Indice>();
        Indice indice = new Indice();
        String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
        em = factory.createEntityManager();
       
        IndiceJpaController indiceJpa = new IndiceJpaController(factory);
        while (obj.next()) {
            String strId = obj.getObject(1).toString();
            BigDecimal bigDId = new BigDecimal(strId);
            indice.setId(bigDId);
            indice.setNombre(obj.getObject(2).toString());
            //System.out.println("objeto: "+ obj.getObject(2).toString());
            if ((indice.getNombre().equalsIgnoreCase("LIBOR")) || (indice.getNombre().equalsIgnoreCase("CER"))) {
                indice.setTipo("1");

            } else {
                indice.setTipo("2");
            }
            
            indiceJpa.insertar("INSERT INTO Indice (id, tipo, nombre) VALUES ( "+indice.getId()+", "+indice.getTipo()+", '"+indice.getNombre()+"')");
           // System.out.println("INSERT INTO Indice (id, tipo, nombre) VALUES ( "+indice.getId()+", "+indice.getTipo()+", '"+indice.getNombre()+"')");
            //indiceJpa.persist(indice);

        }


    }

   
    }

