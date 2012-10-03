/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class MigracionCreditos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
//        System.out.println(" --- INDICES  ---");
//         TipoDeTasaAIndice tipoAIndice = new TipoDeTasaAIndice();
//         tipoAIndice.migrarTipoDeTasaAIndice();
//         
//         ValoresDeTasaAIndiceValor valoresTasa = new ValoresDeTasaAIndiceValor();
//         valoresTasa.migrarValoresTasaAIndiceValor();
//         
//         ValoresDeTasaAIndiceValor ti = new ValoresDeTasaAIndiceValor();
//         ti.migrarValoresTasaAIndiceValor();
//
//        System.out.println(" --- PERSONAS ---");
//        APersonas personas = new APersonas(); 
//        personas.migrarPersonas();

        System.out.println(" --- ProyectoAOjetoI2 ---");
        ProyectoAOjetoI2 pAObjetoi = new ProyectoAOjetoI2();
        pAObjetoi.migrar();

        System.out.println(" --- DESEMBOLSOS ---");
        DesembolsosADesembolso desembolso = new DesembolsosADesembolso();
        desembolso.migrarADesembolso();

        System.out.println(" --- DOMICILIOS ---");
        DomicilioADomicilio proyectoAO = new DomicilioADomicilio();
        proyectoAO.migrarADomicilio();

        System.out.println(" --- ESTADOS ---");
        AObjetoIEstado objetoiEstado = new AObjetoIEstado();
        objetoiEstado.migrarAEstado();

        System.out.println(" --- CUOTAS CAP---");
        CuotaDeCapitalACuota cuota = new CuotaDeCapitalACuota();
        cuota.migrarACuota();

        System.out.println(" --- CUOTAS COMP ---");
        CuotaCompensatorioACuota compensatorioACuota = new
        CuotaCompensatorioACuota(); compensatorioACuota.migrarCCACuota();

        System.out.println(" --- CTA CTE CUOTAS ---");
        CuotaCapitalACtaCte cuotaCtaCte = new CuotaCapitalACtaCte();
        cuotaCtaCte.migrarCuotaDeCapital();

        System.out.println(" --- CTA CTE CAPITAL ---");
        CuotaCapitalACtaCte2 ctaCte2 = new CuotaCapitalACtaCte2();
        ctaCte2.migrarCuotaDeCapital2();

        System.out.println(" --- BONIFICACION ---");
        AObjetoiBonificacion objiBonificacion = new AObjetoiBonificacion();
        objiBonificacion.migrarAObjetoibonificacion();

        System.out.println(" --- BON DETALLE ---");
        ABonDetalle bonDetalle2 = new ABonDetalle();
        bonDetalle2.migrarABonDetalle();
          
        //ya migrado (sept.2011)
//        AVinedo vinedo = new AVinedo(); vinedo.migrarFincas();
//
//        ACuentaBancaria aCuenta = new ACuentaBancaria();
//        aCuenta.cuentaBancaria();

        
        //********  NO MIGRAR ************
        //CorregirPersonas corregir = new CorregirPersonas();
        //corregir.agregarOrigenPersonas();
        // VerificarPersonas verp = new VerificarPersonas();
        // verp.verPersonas();
        // CompPersNombre comp= new CompPersNombre();
        // comp.compararPorNombre();
        //PersonaOrigen po = new PersonaOrigen();
        //po.migrarOrigen();
       // PersonasOrigenNuevas po = new PersonasOrigenNuevas();
       // po.migrarOrigenNuevos();
    }
}
