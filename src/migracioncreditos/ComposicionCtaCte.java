/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionSql.CtacteJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Ctacte;
import Entidades.Objetoi;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class ComposicionCtaCte {
    
    // se compara la composición con la cuenta corriente-->Cuotas, para verificar que la migración esté bien realizada
    // la comparación se realiza a nivel de Capital, Compensatorio, Moratorio y Punitorio
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    CtacteJpaController ctacteJpa = new CtacteJpaController(factory);
    ObjetoiJpaController objetoiJpa= new ObjetoiJpaController(factory);
    List<Objetoi> objetoiLista= objetoiJpa.findObjetoiEntities();
    
  //  List<Ctacte> ctaCteLista = ctacteJpa.findAll1();
    public void comparar(){
        Double capital = 0.0;
        Double compensatorio = 0.0;
        Double punitorio = 0.0;
        for (Iterator<Objetoi> it = objetoiLista.iterator(); it.hasNext();) {
            Objetoi objetoi = it.next();
            List<Ctacte> ctaCteLista= ctacteJpa.findCtaCteObjetoi(objetoi);
            for (Iterator<Ctacte> it1 = ctaCteLista.iterator(); it1.hasNext();) {
                Ctacte ctacte = it1.next();
                if (ctacte.getAsociadoId().getId().toString().equals("1")&& ctacte.getTipomovId().getId().toString().equals("2")){
                    capital+= ctacte.getCuotaId().getCapital();
                    
                }
                if(ctacte.getAsociadoId().getId().toString().equals("2")&& ctacte.getTipomovId().getId().toString().equals("2")){
                    compensatorio+= ctacte.getCuotaId().getCompensatorio();
                }
                if(ctacte.getAsociadoId().getId().toString().equals("3")&& ctacte.getTipomovId().getId().toString().equals("2")){
                    punitorio+= ctacte.getCuotaId().getMoratorio();
                }
                
               //System.out.println(ctacte.getAsociadoId());
//                System.out.println("capital: "+ capital+", compensatorio: "+compensatorio+ ", punitorio: "+ punitorio);
            }
            
            
            
            
        }
            
        }
        
    }
    

