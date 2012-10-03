/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.CtacteJpaController;
import ConexionSql.CuotaJpaController;
import ConexionSql.ObjetoiJpaController;
import Entidades.Ctacte;
import Entidades.Cuota;
import Entidades.Objetoi;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author analian
 */
public class CuotaCapitalACtaCte2 {

    BaseAccess ba = new BaseAccess();
    ResultSet obj = ba.pagos();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    ObjetoiJpaController objetoiJpa = new ObjetoiJpaController(factory);
    Ctacte ctaCte = new Ctacte();
    List<Cuota> cuotaCredito = new ArrayList<Cuota>();
    CuotaJpaController cuotaJpa = new CuotaJpaController(factory);
    CtacteJpaController ctaCteJpa = new CtacteJpaController(factory);
    private int asociado_id;
    private int facturado_id;
    private int tipoMov_id;
    private List<Cuota> cuotasLista;
    private Date fechaPago;
    private int idPago;
    private String idProyecto;
    private int numeroCuota;
    private Date Vencimiento;
    private int itemCtaCte;
    private int verificadorCtaCte;
    private BigDecimal mov;
    private int year;
    private int n = 1;

    public void migrarCuotaDeCapital2() throws SQLException {
        // long cuenta=  ctaCteJpa.findUltimoMovimiento();
        int vec = 0;
        Vector cuenta = ctaCteJpa.findUltimoMovimiento();
        mov = (BigDecimal) cuenta.elementAt(0);
        int movimientoCtaCte = mov.intValue() + 1;
         movimientoCtaCte= cuenta.lastIndexOf(cuenta)+1;
        

//        System.out.println(movimientoCtaCte + "movimiento cuenta corriente");
         
        try{ 
        while (obj.next()) {    // RECORRO TODOS LOS PAGOS DE LA BASE A MIGRAR
            idPago = obj.getInt(1);
            idProyecto = obj.getString(2);
            List<Objetoi> obi = objetoiJpa.findByCredito(idProyecto); // BUSCO EL PROYECTO AL QUE PERTENECE EL PAGO
            for (Iterator<Objetoi> it = obi.iterator(); it.hasNext();) {
                Objetoi objetoi = it.next();
                //ctaCte.set
                //ctaCte.set
                ctaCte.setObjetoi(objetoi);
                fechaPago = obj.getDate(4);
                year = fechaPago.getYear() + 1900;
                ctaCte.setImporte(obj.getDouble(3)*-1);
                ctaCte.setUsuariocauserK("Migracion");
                numeroCuota = obj.getInt(5);
                cuotasLista = cuotaJpa.finCuotasCredito(objetoi);  // BUSCO  LAS CUOTAS ASOCIADAS AL CREDITO
                itemCtaCte = 1;
                verificadorCtaCte = 0;
                // buscar el nro del último movimiento de cuenta corriente insertado y sumarle uno
                for (Iterator<Cuota> it1 = cuotasLista.iterator(); it1.hasNext();) {
                    Cuota cuotasi = it1.next();

                    if (cuotasi.getNumero() == numeroCuota) {
                        //idPago = obj.getInt(1);
                        switch (idPago) {
                            case 12: {
                                //CONCEPTO CAPITAL CRED

                                ctaCte.setFechaGeneracion(fechaPago);

                                java.sql.Date sqlDate = new java.sql.Date(cuotasi.getFechaVencimiento().getTime());

                                ctaCte.setFechaVencimiento(sqlDate);
                                asociado_id = 1;
                                ctaCte.setCuotaId(cuotasi);
                                facturado_id = 1;
                                tipoMov_id = 1;
                                ctaCte.setTipoMovimiento("pago");
                                //Insert
                                if (ctaCte.getFechaGeneracion() == null) {
                                    ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                   //  System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                } else {
                                   ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                  //   System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                }
                                break;
                            }

                            case 22: {                              
                                //obtener CuotaDeInteresCompensatorio.Vencimiento(Usando Pagos.idProyecto + Pagos.numeroCuota,
                                //--> CuotasDeInterésCompensatorio.IDProyecto + idCuota--> obtener vencimiento)
                                ResultSet intCompensatorio = ba.buscarInteresCompensatorio(idProyecto, numeroCuota);

                                while (intCompensatorio.next()) {
                                    Vencimiento = intCompensatorio.getDate(1);

                                 //   if (cuotasi.getFechaVencimiento() == Vencimiento) {
                                        //INSERTAR CTACTE CONCEPTO COMPENSATORIO DEBITO
                                        ctaCte.setFechaGeneracion(null);
                                        ctaCte.setFechaVencimiento(Vencimiento);
                                        asociado_id = 2;      //COMPENSATORIO
                                        ctaCte.setCuotaId(cuotasi);
                                        facturado_id = 2;
                                        tipoMov_id = 2;       //CUOTA
                                        ctaCte.setTipoMovimiento("cuota");
                                        // Insert
                                        if (ctaCte.getFechaGeneracion() == null) {
                                           ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
//                                          //   System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                        } else {
                                           ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                         //   System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                        }
                                        //  System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + "," + ctaCte.getFechaGeneracion() + "," + ctaCte.getFechaVencimiento() + "," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                  //  }

                                    
                                    //INSERTAR CTACTE CONCEPTO COMPENSATORIO CREDITO
                                    ctaCte.setFechaGeneracion(fechaPago);
                                    ctaCte.setFechaVencimiento(Vencimiento);
                                    asociado_id = 2;         // COMPENSATORIO
                                    facturado_id = 2;
                                    tipoMov_id = 1;          // PAGO
                                    ctaCte.setCuotaId(cuotasi);// revisar que sea así
                                    ctaCte.setTipoMovimiento("pago");
                                    //Insertar   

                                    if (ctaCte.getFechaGeneracion() == null) {
                                         ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                      //  System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                    } else {
                                         ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                       // System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                    }
                                //    System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                }
                            }
                                 break;

                            case 32: {
                                //INSERTAR CONCEPTO MORATORIO, DEBITO Y CREDITO
                                ctaCte.setFechaGeneracion(null);
                                java.sql.Date sqlDate = new java.sql.Date(cuotasi.getFechaVencimiento().getTime());

                                ctaCte.setFechaVencimiento(sqlDate);
                                // ctaCte.setFechaVencimiento(cuotasi.getFechaVencimiento());
                                asociado_id = 3;        //MORATORIO
                                ctaCte.setCuotaId(cuotasi);
                                facturado_id = 3;
                                tipoMov_id = 2;        // CUOTA    
                                ctaCte.setTipoMovimiento("cuota");
                                //Insertar

                                ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                               //System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                ctaCte.setFechaGeneracion(fechaPago);
                                ctaCte.setFechaVencimiento(sqlDate);
                                //ctaCte.setFechaVencimiento(cuotasi.getFechaVencimiento());
                                asociado_id = 3;       //MORATORIO
                                ctaCte.setCuotaId(cuotasi);
                                facturado_id = 3;
                                tipoMov_id = 1;        //PAGO
                                ctaCte.setTipoMovimiento("pago");
                                //Insertar

                                if (ctaCte.getFechaGeneracion() == null) {
                                    ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                 //   System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte +",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                } else {
                                    ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                   // System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                }                                //System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + "," + ctaCte.getFechaGeneracion() + "," + ctaCte.getFechaVencimiento() + "," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                
                                
                                //INSERTAR CONCEPTO PUNITORIO, DEBITO Y CREDITO
                                ctaCte.setImporte(0.0);
                                asociado_id = 4;             // PUNITORIO
                                facturado_id = 4;
                                tipoMov_id = 2;              //PAGO   
                                        
                                ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                
                                tipoMov_id = 1;
                                
                                if (ctaCte.getFechaGeneracion() == null) {
                                    ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                          //          System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte +",'" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");

                                } else {
                                    ctaCteJpa.insertar("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                 //   System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + ",'" + ctaCte.getFechaGeneracion() + "','" + ctaCte.getFechaVencimiento() + "'," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                }                                
                                //System.out.println("INSERT INTO Ctacte(itemCtacte,movimientoCtacte,periodoCtacte,verificadorCtacte,fechaGeneracion,fechaVencimiento,importe,objetoi_id,asociado_id,cuota_id,facturado_id,tipomov_id,usuario_causerK,tipoMovimiento) VALUES(" + itemCtaCte + "," + movimientoCtaCte++ + ", " + year + "," + verificadorCtaCte + "," + ctaCte.getFechaGeneracion() + "," + ctaCte.getFechaVencimiento() + "," + ctaCte.getImporte() + "," + ctaCte.getObjetoi().getId() + "," + asociado_id + "," + ctaCte.getCuotaId().getId() + "," + facturado_id + "," + tipoMov_id + ",'" + ctaCte.getUsuariocauserK() + "','" + ctaCte.getTipoMovimiento() + "')");
                                break;
                            }
                                
                        }
                    }
                }


            }
            n++;

        }
        }finally{
            cuotaJpa.closeEEntityManager();
            ctaCteJpa.closeEEntityManager();
            objetoiJpa.closeEEntityManager();
        }
            

    }
}
