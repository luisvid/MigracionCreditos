/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.ObjetoiIndiceJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Indice;
import Entidades.Objetoi;
import Entidades.ObjetoiIndice;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class ProyectoTasaAObjetoIIndice {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.proyectosTasa();
    List<ObjetoiIndice> objIIndice = new ArrayList<ObjetoiIndice>();
    ObjetoiIndice oiiCompensatorio = new ObjetoiIndice();
    ObjetoiIndice oiiPunitorio = new ObjetoiIndice();
    ObjetoiIndice oiiMoratorio = new ObjetoiIndice();
    private EntityManager em;
    //Indice indice= new Indice();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    //em  = factory.createEntityManager();
    private ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    ObjetoiIndiceJpaController objetoiIndiceJpa = new ObjetoiIndiceJpaController(factory);
    private String iPunit;

    public void migrarProyectoTasaAObjetoI() throws SQLException {

        while (obj.next()) {

            // Objetoi idProyecto = new Objetoi();
            String strIdProyecto = obj.getObject(11).toString();
            //idProyecto.setNumeroCredito(strIdProyecto);

            List<Objetoi> obi = objetoiJpa.findByCredito(strIdProyecto);
            //Seteo los datos al objeto que va a llevar lo relacionado a los datos Compensatorios
            for (Iterator<Objetoi> it = obi.iterator(); it.hasNext();) {
                Objetoi objetoi = it.next();


                oiiCompensatorio.setCreditoId(objetoi);
                int d = obj.getInt(1);
                oiiCompensatorio.setDiasAntes(d);
                oiiCompensatorio.setTipoTasa("1");
                oiiCompensatorio.setValorMas(obj.getInt(2));
                oiiCompensatorio.setValorPor(obj.getDouble(5));
                Indice indice = new Indice();
                BigDecimal bigDId = new BigDecimal(obj.getBigDecimal(10).toString());
                indice.setId(bigDId);
                oiiCompensatorio.setIndiceId(indice);// Revisar, tal vez tenga que buscar el indice con ese id y setearle el creado y no generar uno nuevo
                // objIIndice.add(oiiCompensatorio);//Revisar que pueda ir todo en el mismo List
               objetoiIndiceJpa.insertar("INSERT INTO ObjetoiIndice ( valorMas, valorPor, tipoTasa, diasAntes, credito_id, indice_id) VALUES ("+ oiiCompensatorio.getValorMas()+","+ oiiCompensatorio.getValorPor()+",'"+ oiiCompensatorio.getTipoTasa()+"',"+ oiiCompensatorio.getDiasAntes()+","+ oiiCompensatorio.getCreditoId().getId()+","+ oiiCompensatorio.getIndiceId().getId()+")");
               // System.out.println("INSERT INTO ObjetoiIndice ( valorMas, valorPor, tipoTasa, diasAntes, credito_id, indice_id) VALUES ("+ oiiCompensatorio.getValorMas()+","+ oiiCompensatorio.getValorPor()+",'"+ oiiCompensatorio.getTipoTasa()+"',"+ oiiCompensatorio.getDiasAntes()+","+ oiiCompensatorio.getCreditoId().getId()+","+ oiiCompensatorio.getIndiceId().getId()+")");
//Seteo los datos al objeto que va a llevar lo relacionado a los datos Moratorios 


                oiiMoratorio.setCreditoId(objetoi);
                oiiMoratorio.setDiasAntes(d);
                oiiMoratorio.setTipoTasa("2");
                oiiMoratorio.setValorMas(obj.getInt(4));
                oiiMoratorio.setValorPor(obj.getDouble(7));
                Indice indiceM = new Indice();
                BigDecimal bigDIdM = new BigDecimal(obj.getBigDecimal(8).toString());
                indiceM.setId(bigDIdM);
                oiiMoratorio.setIndiceId(indiceM);
                // objIIndice.add(oiiMoratorio);
                objetoiIndiceJpa.insertar("INSERT INTO ObjetoiIndice ( valorMas, valorPor, tipoTasa, diasAntes, credito_id, indice_id) VALUES ("+ oiiMoratorio.getValorMas()+","+ oiiMoratorio.getValorPor()+",'"+ oiiMoratorio.getTipoTasa()+"',"+ oiiMoratorio.getDiasAntes()+","+ oiiMoratorio.getCreditoId().getId()+","+ oiiMoratorio.getIndiceId().getId()+")");
                //System.out.println("INSERT INTO ObjetoiIndice ( valorMas, valorPor, tipoTasa, diasAntes, credito_id, indice_id) VALUES ("+ oiiMoratorio.getValorMas()+","+ oiiMoratorio.getValorPor()+",'"+ oiiMoratorio.getTipoTasa()+"',"+ oiiMoratorio.getDiasAntes()+","+ oiiMoratorio.getCreditoId().getId()+","+ oiiMoratorio.getIndiceId().getId()+")");
//Seteo los datos al objeto que va a llevar lo relacionado a los datos Punitorios

                oiiPunitorio.setCreditoId(objetoi);
                oiiPunitorio.setDiasAntes(d);
                oiiPunitorio.setTipoTasa("3");
                oiiPunitorio.setValorMas(obj.getInt(3));
                oiiPunitorio.setValorPor(obj.getDouble(6));
                Indice indiceP = new Indice();
                iPunit= obj.getBigDecimal(9).toString();
                 BigDecimal bigDIdP = new BigDecimal(iPunit);
                indiceP.setId(bigDIdP);
                oiiPunitorio.setIndiceId(indiceP);
                //objIIndice.add(oiiPunitorio);
               
                //  System.out.println("Esta es la fecha:" + " " + indice.getFecha() + " Este es el valor:" + " " + indice.getValor() + " Este es el idTipo relacionado:" + " " + indice.getIndiceId());
                objetoiIndiceJpa.insertar("INSERT INTO ObjetoiIndice ( valorMas, valorPor, tipoTasa, diasAntes, credito_id, indice_id) VALUES ("+ oiiPunitorio.getValorMas()+","+ oiiPunitorio.getValorPor()+",'"+ oiiPunitorio.getTipoTasa()+"',"+ oiiPunitorio.getDiasAntes()+","+ oiiPunitorio.getCreditoId().getId()+","+ oiiPunitorio.getIndiceId().getId()+")");
               // System.out.println("INSERT INTO ObjetoiIndice ( valorMas, valorPor, tipoTasa, diasAntes, credito_id, indice_id) VALUES ("+ oiiPunitorio.getValorMas()+","+ oiiPunitorio.getValorPor()+",'"+ oiiPunitorio.getTipoTasa()+"',"+ oiiPunitorio.getDiasAntes()+","+ oiiPunitorio.getCreditoId().getId()+","+ oiiPunitorio.getIndiceId().getId()+")");
                //objetoiIndiceJpa.persist(oiiPunitorio);
            }

        }
    }
}
