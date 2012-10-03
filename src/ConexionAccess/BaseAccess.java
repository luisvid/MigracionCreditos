/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author analian
 */
public class BaseAccess {

    public ResultSet valoresTasa() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT ValoresDeTasas.IDFechaTasa, ValoresDeTasas.Valor, ValoresDeTasas.IDTipo FROM ValoresDeTasas");
            /* while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet tiposDeTasa() {




        int i = 0;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();


            ResultSet rs = st.executeQuery("select IDTipo, Descripcion from TiposDeTasa");

            /*  while (rs.next()) {
            
            
            
            System.out.println(rs.getObject(1));
            
            System.out.println(rs.getObject(2).toString());
            
            
            
            }*/

            return rs;

        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet proyectosTasa() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("select DiasAntesCompensatorios, CompensatoriosMas, PunitoriosMas, MoratoriosMas, CompensatoriosPor, PunitoriosPor, MoratoriosPor, idTasaMoratorios, idTasaPunitorios, idTasaCompensatorios, idProyecto from ProyectosTasas");
            /*while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(9));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet proyectos() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("select Expediente, FechaMutuo, FechaResolucion, FechaIngreso, idProyecto, Objeto, Observacion, Usuario, idBcoAgenteCofinanciador, idTitular, ResolucionAprobatoria from Proyectos");
            while (rs.next()) {
                System.out.println(rs.getObject(1));
                System.out.println(rs.getObject(5));
            }

            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;


    }

    public ResultSet proyectosAportes(String proyecto) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("select BcoCofinanciador, Fondo, Otros, Propio from ProyectosAportes where ProyectosAportes.idProyecto ='" + proyecto + "' ");
            /*  while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println("El contenido sin pasar a Daouble "+ (rs.getString(2)));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet proyectosParamCuotas(String proyecto) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();
            String consulta = "select CuotasPorAño, CantidadCapital, CantidadInteres, PrimerCapital, PrimerInteres   from ProyectosParamCuotas where ProyectosParamCuotas.idProyecto ='" + proyecto + "'";

            ResultSet rs = st.executeQuery(consulta);
            /*  while (rs.next()){
            System.out.println(rs.getObject(1));
            int plazoI = Integer.valueOf(rs.getObject(2).toString());
            System.out.println("Este es el plazoI: "+plazoI);
            }*/
            return rs;

        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;


    }

    public ResultSet titulares(int idTit) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("select FechaInicioActividad, VolumenVenta, idTitular from Titulares where Titulares.idTitular =" + idTit);
            /* while (rs.next()) {
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet titular() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Titulares.idTitular, Titulares.Nombre, Titulares.Cuit, Titulares.idTipoDocumento, Titulares.NroDocumento, Titulares.Calle, Titulares.Numero, Titulares.Piso, Titulares.Departamento, Titulares.idProvincia, Titulares.idDepartamento, Titulares.CodigoPostal, Provincias.Descripcion, Localidades.Descripcion, Titulares.idLocalidad FROM (Titulares INNER JOIN Provincias ON Titulares.idProvincia = Provincias.IDProvincia) INNER JOIN Localidades ON Titulares.idLocalidad = Localidades.IDLocalidad; ");
            /* while (rs.next()) {
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet desembolsos() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Desembolsos.IDProyecto, Desembolsos.Fecha, Desembolsos.Importe, Desembolsos.NroDesembolso, Desembolsos.NroTrasferencia, Desembolsos.ImporteReal FROM Desembolsos");
            /* while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet superConsulta() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Proyectos.FechaMutuo, Proyectos.FechaResolucion, Proyectos.FechaIngreso, Proyectos.idProyecto, Proyectos.Objeto, Proyectos.Observacion, Proyectos.Usuario, Proyectos.idBcoAgenteCofinanciador, Proyectos.idTitular, Proyectos.ResolucionAprobatoria, Proyectos.Expediente, ProyectosParamCuotas.CuotasPorAño, ProyectosParamCuotas.CantidadCapital, ProyectosParamCuotas.CantidadInteres, ProyectosParamCuotas.PrimerCapital, ProyectosParamCuotas.PrimerInteres, ProyectosAportes.BcoCofinanciador, ProyectosAportes.Propio, ProyectosAportes.Fondo, ProyectosAportes.Otros, Titulares.idTitular, Titulares.FechaInicioActividad, Titulares.VolumenVenta, Titulares.Cuit, Titulares.nroDocumento, Titulares.Nombre FROM ((Proyectos INNER JOIN Titulares ON Proyectos.idTitular = Titulares.idTitular) INNER JOIN ProyectosAportes ON Proyectos.idProyecto = ProyectosAportes.idProyecto) INNER JOIN ProyectosParamCuotas ON Proyectos.idProyecto = ProyectosParamCuotas.idProyecto");
            /* while (rs.next()) {
            System.out.println(rs.getObject(4));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet subTipoConsulta() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM SubTipos");
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }
     public ResultSet tipoConsulta() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Tipos");
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet domicilios() {

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Proyectos.idProyecto, Departamentos.Descripcion, Proyectos.Calle, Proyectos.Numero, Proyectos.idDepartamento, Proyectos.idLocalidad FROM Proyectos INNER JOIN Departamentos ON Proyectos.idDepartamento = Departamentos.IDDepartamento");
//            while (rs.next()) {
//                System.out.println(rs.getRow() + " idProyecto: " + rs.getObject(1) + "Descripcion: " + rs.getObject(2));
//            }
            return rs;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;

    }

    public ResultSet estadoProyectos() {

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Proyectos.idEstado, Estados.Descripcion, Proyectos.idProyecto FROM Proyectos INNER JOIN Estados ON Proyectos.idEstado = Estados.IDEstado");
            /* while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet cuotaDeCapital() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            //ResultSet rs = st.executeQuery("SELECT CuotasDeCapital.IDCuota, CuotasDeCapital.IDProyecto, CuotasDeCapital.Vencimiento, CuotasDeCapital.Importe FROM CuotasDeCapital ORDER BY CuotasDeCapital.IDProyecto, CuotasDeCapital.Vencimiento ");

            ResultSet rs = st.executeQuery("SELECT CC.IDCuota, CC.IDProyecto, CC.Vencimiento, CC.Importe, D.Fecha as FechaDesembolso "
                    + "FROM CuotasDeCapital CC "
                    + "INNER JOIN Desembolsos D on CC.IDProyecto = D.IDPRoyecto "
                    + "WHERE D.NroDesembolso = 1 ORDER BY CC.IDProyecto, CC.Vencimiento ");

            /*  while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public int calcularGracia() {
        int periodoGracia = 0;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT  T1.CantInteres - T2.CantCapital as Diff FROM "
                    + "(SELECT count(IDPROYECTO) as CantInteres FROM cuotasDeInteresCompensatorio where IDPRoyecto = '0232020163') T1 "
                    + ", "
                    + "(SELECT count(IDPROYECTO) as CantCapital FROM cuotasDeCapital where IDPRoyecto = '0232020163') T2");

            while (rs.next()) {
                periodoGracia = rs.getInt(1);

            }
            return periodoGracia;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return 0;
    }

    public ResultSet cuotaDeInteresCompensatorio() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT CuotasDeInteresCompensatorio.IDProyecto, CuotasDeInteresCompensatorio.IDCuota, CuotasDeInteresCompensatorio.Vencimiento FROM CuotasDeInteresCompensatorio");
            //  while (rs.next()) {
            // System.out.println(rs.getObject(1));
            //  System.out.println(rs.getObject(3));
            // }
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public ResultSet pagos() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Pagos.idTipoCuota, Pagos.idProyecto, Pagos.Importe, Pagos.FechaPago, Pagos.NumeroCuota FROM Pagos ");
            /*  while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public ResultSet buscarInteresCompensatorio(String idProyecto, int numeroCuota) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT CuotasDeInteresCompensatorio.Vencimiento, CuotasDeInteresCompensatorio.IDCuota, CuotasDeInteresCompensatorio.IDProyecto FROM CuotasDeInteresCompensatorio WHERE ((CuotasDeInteresCompensatorio.IDCuota =" + numeroCuota + ") AND (CuotasDeInteresCompensatorio.IDProyecto ='" + idProyecto + "'))");
            //  while (rs.next()) {
            // System.out.println(rs.getObject(1));
            //  System.out.println(rs.getObject(3));
            // }
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;

    }

    public ResultSet cuotaDeCapital2() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT CuotasDeCapital.IDCuota, CuotasDeCapital.IDProyecto, CuotasDeCapital.Vencimiento, CuotasDeCapital.Importe FROM CuotasDeCapital ORDER BY CuotasDeCapital.IDProyecto, CuotasDeCapital.Vencimiento ");
            /*  while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public ResultSet idTitular() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessServidor", "", "santa01rita");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Titulares.idTitular, Titulares.Cuit, Titulares.Nombre, Titulares.NroDocumento FROM Titulares order by idTitular; ");
            /* while (rs.next()) {
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public ResultSet titularCuitValido() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessServidor", "", "santa01rita");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("select idtitular, nombre, calle, numero, NroDocumento, cuit from titulares where cuit <> '00-00000000-0' and len(cuit)=13");
            /* while (rs.next()) {
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(2));
            }*/
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public ResultSet buscarOrigen() {
           try {
               
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava", "", "santa01rita");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Titulares.idTitular, Titulares.Cuit, Titulares.Nombre, Titulares.NroDocumento FROM Titulares where Titulares.idTitular > 2609 and Titulares.idTitular<4858 Order by Titulares.idTitular ");
           //  while (rs.next()) {
           // System.out.println(rs.getObject(3));
           // System.out.println(rs.getObject(2));
           // }
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    public ResultSet buscarOrigenNuevo() {
      try {
               
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conexion = DriverManager.getConnection("jdbc:odbc:accessJava", "", "santa01rita");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT Titulares.idTitular, Titulares.Cuit, Titulares.Nombre, Titulares.NroDocumento FROM Titulares where Titulares.idTitular>4857 Order by Titulares.idTitular ");
           //  while (rs.next()) {
           // System.out.println(rs.getObject(3));
           // System.out.println(rs.getObject(2));
           // }
            return rs;
        } catch (Exception e) {
            System.out.println("Algun error en algun sitio");
        }
        return null;
    }

    }
 

    

