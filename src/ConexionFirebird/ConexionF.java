/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author analian
 */
public class ConexionF {

    public ResultSet conexion() {
        String dataBaseUrl = "jdbc:firebirdsql:SRV-DC-01:d://teagro//db//FTYCMBONIF.GDB";
                // "Srv-dc-01:d:\\teagro\\db\\FTYCMBonif.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = "Select * from Creditos where pers_nuri < 1000000";
            resultado = consulta.executeQuery(consultaSql);//executeQuery(consultaSql);
           // while(resultado.next()){
            //System.out.println(resultado.getString(24));
            //}
            

            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public ResultSet conexionCosecha() {
        String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\cosecha\\db\\cosecha.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = " SELECT * FROM Creditos  WEHRE nuri < 1000000";
            resultado= consulta.executeQuery(consultaSql);
           
            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    

    public ResultSet conexionPlan() {
        String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\db\\FTYCMBonif.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
//   Select pp.nuri, c.plan_nuri, ppc.plan_pago_nuri, ppc.nro_cta,ppc.fecha_vto, ppc.cuota_bonificada, ppc.pagada, c.bonifi_administradora, c.bonifi_sepyme, c.bonifi_ley, c.tasa_subsidio, c.codigo from plan_pago_cuota ppc inner join plan_pago pp on (pp.nuri = ppc.plan_pago_nuri) inner join creditos c on(pp.nuri=c.plan_nuri ppc where ppc.plan_pago_nuri=(select plan_nuri p from creditos c  where c.codigo="+ codigo+");";            

            String consultaSql = "select pc.*,c.* "
                    + "from plan_pago_cuota pc inner join plan_pago p on pc.plan_pago_nuri = p.nuri "
                    + "inner join creditos c on p.nuri = c.plan_nuri "
                    + "where c.sist_calculo = 5 and pc.tipo=22" + ";";

            //   String consultaSql = "select * from plan_pago_cuota "+";";  

            resultado = consulta.executeQuery(consultaSql);


            return resultado;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public ResultSet buscarPlanPago(BigDecimal bigDecimal) {
        String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\db\\FTYCMBonif.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = "Select * from PLAN_PAGO pp where pp.nuri = " + bigDecimal + ";";
            resultado = consulta.executeQuery(consultaSql);


            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ResultSet buscarCredito(BigDecimal bigDecimal) {
        String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\db\\FTYCMBonif.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = "Select * from creditos c where c.plan_nuri =" + bigDecimal + ";";
            resultado = consulta.executeQuery(consultaSql);
            /*  while (resultado.next()){
            System.out.println("Este es el resultado: "+resultado.getObject(2));
            }*/

            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ResultSet numInv() {
        String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\cosecha\\db\\cosecha.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = "SELECT distinct(F.nro_inv)FROM PERS_FINCAS F order by F.nro_inv";
            resultado = consulta.executeQuery(consultaSql);
            /*  while (resultado.next()){
            System.out.println("Este es el resultado: "+resultado.getObject(2));
            }*/

            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ResultSet buscarFincas(String inv) {
        String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\cosecha\\db\\cosecha.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = "SELECT F.nro_inv, F.observaciones, D.descrip, F.localidad, F.hectareas "
                    + "FROM pers_fincas F INNER JOIN DEPARTAMENTOS D ON F.Depto_nuri = D.nuri   "
                    + "where F.nro_inv='"+ inv +"'order BY F.observaciones  desc";
            resultado = consulta.executeQuery(consultaSql);
            /*  while (resultado.next()){
            System.out.println("Este es el resultado: "+resultado.getObject(2));
            }*/

            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ResultSet buscarPersonas(int nroPersona) {
         String dataBaseUrl = "jdbc:firebirdsql:Srv-dc-01:d:\\teagro\\cosecha\\db\\cosecha.GDB";
        String usuarioDb = "sysdba";
        String password = "masterkey";
        String driverName = "org.firebirdsql.jdbc.FBDriver";
        Connection conexion;
        Statement consulta;
        ResultSet resultado;

        try {
            Class.forName(driverName);
            conexion = DriverManager.getConnection(dataBaseUrl, usuarioDb, password);
            consulta = conexion.createStatement();
            String consultaSql = "Select Cuit from Personas p where p.nuri=" + nroPersona;
            resultado = consulta.executeQuery(consultaSql);
            /*  while (resultado.next()){
            System.out.println("Este es el resultado: "+resultado.getObject(2));
            }*/

            return resultado;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
 
    }

