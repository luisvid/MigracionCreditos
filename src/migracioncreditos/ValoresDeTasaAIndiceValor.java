/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.IndiceValorJpaController;
import Entidades.Indice;
import Entidades.IndiceValor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class ValoresDeTasaAIndiceValor {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.valoresTasa();
    Object fecha = null;
    List<IndiceValor> ind = new ArrayList<IndiceValor>();
    IndiceValor indice = new IndiceValor();
    private EntityManager em;
    int i=1;
    
    

    public void migrarValoresTasaAIndiceValor() throws SQLException {
        String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
        em = factory.createEntityManager();
        IndiceValorJpaController indiceValorJpa= new IndiceValorJpaController(factory);
        while (obj.next()) {
            

            indice.setFecha(obj.getDate(1));// Agrego la Fecha

            
            indice.setValor(obj.getDouble(2)); //Agrego el valor
            String strId= obj.getString(3);
            BigDecimal bigDId= new BigDecimal(strId);
            Indice indiceId= new Indice();
            indiceId.setId(bigDId);// Revisar el indice, tal vez tenga que buscar el indice con ese id y setearselo
            indice.setIndiceId(indiceId);
            //indiceValorJpa.persist(indice); 
            //ind.add(indice);
            indiceValorJpa.insertar("INSERT INTO IndiceValor ( id, fecha, valor, indice_id) VALUES ("+ i + ", '" + indice.getFecha()+"', " +indice.getValor()+", " + indice.getIndiceId().getId()  + ")");
            //System.out.println("INSERT INTO IndiceValor ( id, fecha, valor, indice_id) VALUES ("+ i + ", '" + indice.getFecha()+"', " +indice.getValor()+", " + indice.getIndiceId().getId()  + ")");
          // System.out.println("Esta es la fecha:" + " " + indice.getFecha() + " Este es el valor:" + " " + indice.getValor() + " Este es el idTipo relacionado:" + " " + indice.getIndiceId()+" " +i);
        i++;
        
        }
        
    }
}
