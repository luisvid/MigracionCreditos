/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package migracioncreditos;

import ConexionAccess.BaseAccess;
import ConexionSql.*;
import Entidades.*;
import java.math.BigDecimal;
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
public class ProyectoAOjetoI2 {

    private Objetoi objetoI = new Objetoi();
    BaseAccess ba = new BaseAccess();
    String UNIDAD_DE_PERSISTENCIA = "MigracionCreditosPU";
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(UNIDAD_DE_PERSISTENCIA, System.getProperties());
    PersonaJpaController personaJpa = new PersonaJpaController(factory);
    SubTipoLineaJpaController subtipoLJpa = new SubTipoLineaJpaController(factory);
    LineaJpaController tipoLineaJpa = new LineaJpaController(factory);
    private ResultSet rs;
    Long cuitTitular;
    private String idProyecto;
    private String tipo;
    private String tipoLineaID;
    private String subtipo;
    private String subtipoLineaID;
    private String destino;
    private int banco;
    private int i = 0;
    private String SubTipoLinea_id;
    private String IDSector = "";
    private String IDTipo = "";
    private String IDSubtipo = "";
    List<Persona> personasCuit = new ArrayList<Persona>();
    private String cuitBase;
    private String dniBase;
    private String nombre;
    ObjetoiJpaController objiJpa = new ObjetoiJpaController(factory);
    private String descripcion = "";
    private ObservacionObjetoi observacionObjetoi = null;
    private ObservacionObjetoiJpaController ooJPA = new ObservacionObjetoiJpaController(factory);
    private BigDecimal tipoLineaId;
    private String ti;
    private String po;

    public void migrarSubtipos() throws SQLException {


        rs = ba.subTipoConsulta();

        while (rs.next()) {
            IDSector = rs.getString("IDSector"); //NO VERIFICAR LONGITUD, MANTENER ACTUAL.
            IDTipo = rs.getString("IDTipo"); //VERIFICAR QUE TENGA LONG 2, SINO AGREGAR CERO ADELANTE
            IDSubtipo = rs.getString("IDSubtipo"); //VERIFICAR QUE TENGA LONG 2, SINO AGREGAR CERO ADELANTE
            descripcion = rs.getString("Descripcion");
            if (IDTipo.length() == 1) {
                IDTipo = 0 + IDTipo;

            }
            if (IDSubtipo.length() == 1) {
                IDSubtipo = 0 + IDSubtipo;

            }
            SubTipoLinea_id = IDSector + IDTipo + IDSubtipo;
            List<SubTipoLinea> subtipoLineaList = subtipoLJpa.findSubtipoLinea(Integer.parseInt(SubTipoLinea_id));
            if (subtipoLineaList.isEmpty()) {
                subtipoLJpa.insertar("INSERT INTO SubTipoLinea(Id, nombre) VALUES(" + SubTipoLinea_id + ",'" + descripcion + "')");

            }






        }
    }

    public List<Persona> BuscarTitular(String cuitStr, String dniStr, String nombre) {

        long dni = 0;
        long cuit = 0;
        List<Persona> personaLista = new ArrayList<Persona>();

        if (dniStr == null) {
            dni = 0;
        } else {
            dniStr = dniStr.replaceAll("-", "");
            dniStr = dniStr.replaceAll("_", "");
            dniStr = dniStr.replaceAll("\\.", "");
            if (dniStr.equals("")) {
                dni = 0;
            } else {
                dni = Long.parseLong(dniStr);
            }
        }

        if (cuitStr == null) {
            cuit = 0;
        } else {
            cuitStr = cuitStr.replaceAll("-", "");
            cuitStr = cuitStr.replaceAll("_", "");
            if (cuitStr.equals("")) {
                cuit = 0;
            } else {

                cuit = Long.parseLong(cuitStr.trim());
            }
        }

        if (dni == 0 && cuit == 0) {
            personaLista = personaJpa.findXNombre(nombre);
        } else {
            personaLista = personaJpa.findXCuitOrDni(cuit, dni);
        }

        return personaLista;
    }

    public void insertObservacion(String observacion, Objetoi objetoi) throws SQLException {
        java.util.Date momentoDate = new java.util.Date();

        java.sql.Timestamp momentoTimestamp = new java.sql.Timestamp(momentoDate.getTime());



        ooJPA.insertar("INSERT INTO ObservacionObjetoi(fecha, observacion, objetoi_id) "
                + "VALUES ('" + momentoTimestamp + "', '" + observacion + "', '" + objetoi.getId().toString() + "')");
    }
//SELECT distinct  MID(IdProyecto, 3, 4) from Proyectos;

    public void migrar() throws SQLException {
        migrarTipos();
        migrarSubtipos();
        rs = ba.superConsulta();
        while (rs.next()) {
            banco = rs.getInt(8);
            idProyecto = rs.getString(4);
            tipo = idProyecto.substring(2, 4);
            tipoLineaID = idProyecto.substring(0, 4);
            subtipo = idProyecto.substring(4, 6);
            subtipoLineaID = idProyecto.substring(0, 6);
            objetoI.setFechaMutuo(rs.getDate(1));
            objetoI.setFechaResolucion(rs.getDate(2));
            objetoI.setFechaSolicitud(rs.getDate(3));
            objetoI.setNumeroCredito(idProyecto);
            objetoI.setObjeto(rs.getString(5));
            objetoI.setObservaciones(rs.getString(6));
            objetoI.setAsesorcauserK(rs.getString(7));
            cuitBase = rs.getString(24);
            dniBase = rs.getString(25);
            nombre = rs.getString(26);

            //BUSCAR POR CUIT, DNI O NOMBRE PERSONA. EN CASO DE ENCONTRAR MAS DE UNA COINCIDENCIA, USAR EL PRIMERO.
            personasCuit = BuscarTitular(cuitBase, dniBase, nombre);

            objetoI.setResolucion(rs.getString(10));
            objetoI.setPlazoCapital(rs.getInt(13));
            objetoI.setPlazoCompensatorio(rs.getInt(14));
            objetoI.setPrimerVencCapital(rs.getDate(15));
            objetoI.setPrimerVencInteres(rs.getDate(16));
            objetoI.setAporteCofinanciador(rs.getDouble(17));
            objetoI.setAportePropio(rs.getDouble(18));
            objetoI.setFinanciamiento(rs.getDouble(19));
            objetoI.setOtrosAportes(rs.getDouble(20));
            objetoI.setExpediente(rs.getString(11));
            objetoI.setInicioActividad(rs.getDate(22));
            objetoI.setVolumenVtaAnual(rs.getDouble(23));
            objetoI.setTipoAmortizacion("A");

            //IMPORTANTE !!!
            // 1) VERIFICAR QUE SE ESTE GUARDANDO EL IDTITULAR
            // 2) CREAR UN OBJETO ObservacionObjetoi y guardar el nombre del Titular Origen (TITULARES.nombre)

            if (tipo.equalsIgnoreCase("41")) {
                if (subtipo.equalsIgnoreCase("06") || subtipo.equalsIgnoreCase("08") || subtipo.equalsIgnoreCase("10") || subtipo.equalsIgnoreCase("12")) {
                    objetoI.setDestino("CRDA");
                } else {
                    objetoI.setDestino("CRDC");
                }
            }
            String cuotasAño = rs.getString(12);
            if (cuotasAño.equalsIgnoreCase("1")) {
                objetoI.setFrecuenciaCapital("12");
                objetoI.setFrecuenciaInteres("12");
            } else if (cuotasAño.equalsIgnoreCase("2")) {
                objetoI.setFrecuenciaCapital("6");
                objetoI.setFrecuenciaInteres("6");
            } else if (cuotasAño.equalsIgnoreCase("3")) {
                objetoI.setFrecuenciaCapital("4");
                objetoI.setFrecuenciaInteres("4");
            } else if (cuotasAño.equalsIgnoreCase("6")) {
                objetoI.setFrecuenciaCapital("2");
                objetoI.setFrecuenciaInteres("2");
            } else if (cuotasAño.equalsIgnoreCase("1")) {
                objetoI.setFrecuenciaCapital("12");
                objetoI.setFrecuenciaInteres("12");
            }
            if (banco == 1) {
                banco = 26;
            } else if (banco == 2) {
                banco = 27;
            } else if (banco == 3) {
                banco = 28;
            } else if (banco == 5) {
                banco = 29;
            } else if (banco == 6) {
                banco = 30;
            } else if (banco == 7) {
                banco = 31;
            } else if (banco == 8) {
                banco = 32;
            } else if (banco == 9) {
                banco = 33;
            } else if (banco == 11) {
                banco = 34;
            } else if (banco == 12) {
                banco = 35;
            } else if (banco == 13) {
                banco = 36;
            } else if (banco == 16) {
                banco = 37;
            } else if (banco == 19) {
                banco = 38;
            } else if (banco == 20) {
                banco = 39;
            } else if (banco == 21) {
                banco = 40;
            } else if (banco == 22) {
                banco = 41;
            } else if (banco == 26) {
                banco = 42;
            } else if (banco == 27) {
                banco = 43;
            } else if (banco == 28) {
                banco = 44;
            } else if (banco == 30) {
                banco = 45;
            } else if (banco == 14) {
                banco = 10;
            } else if (banco == 25) {
                banco = 8;
            }

            i++;
            if (!personasCuit.isEmpty()) {
                for (Iterator<Persona> it = personasCuit.iterator(); it.hasNext();) {
                    Persona vpersona = it.next();
                    objetoI.setPersonaIDPERSONA(vpersona);


                    objiJpa.insertar("INSERT INTO Objetoi(aporteCofinanciador, aportePropio, expediente, fechaMutuo, fechaResolucion, fechaSolicitud, financiamiento, frecuenciaCapital, frecuenciaInteres, inicioActividad, numeroAtencion, numeroCredito, objeto, observaciones, otrosAportes, plazoCapital, plazoCompensatorio, primerVencCapital, primerVencInteres, tipoAmortizacion, volumenVtaAnual,asesor_causerK,idCofinanciador,linea_id,persona_IDPERSONA,subTipoLinea_id,destino,numeroResolucion) VALUES(" + objetoI.getAporteCofinanciador() + ", " + objetoI.getAportePropio() + ", '" + objetoI.getExpediente() + "', '" + objetoI.getFechaMutuo() + "', '" + objetoI.getFechaResolucion() + "', '" + objetoI.getFechaSolicitud() + "', " + objetoI.getFinanciamiento() + ", '" + objetoI.getFrecuenciaCapital() + "', '" + objetoI.getFrecuenciaInteres() + "', " + objetoI.getInicioActividad() + ", " + i + ", '" + objetoI.getNumeroCredito() + "', '" + objetoI.getObjeto() + "', '" + objetoI.getObservaciones() + "', '" + objetoI.getOtrosAportes() + "', " + objetoI.getPlazoCapital() + ", " + objetoI.getPlazoCompensatorio() + ", '" + objetoI.getPrimerVencCapital() + "', '" + objetoI.getPrimerVencInteres() + "', '" + objetoI.getTipoAmortizacion() + "', " + objetoI.getVolumenVtaAnual() + ", '" + objetoI.getAsesorcauserK() + "', " + banco + ", " + tipoLineaID + ", " + objetoI.getPersonaIDPERSONA().getIdpersona() + ", " + subtipoLineaID + ", '" + objetoI.getDestino() + "', '" + objetoI.getNumeroResolucion() + "' )");
                    //System.out.println("INSERT INTO Objetoi(aporteCofinanciador, aportePropio, expediente, fechaMutuo, fechaResolucion, fechaSolicitud, financiamiento, frecuenciaCapital, frecuenciaInteres, inicioActividad, numeroAtencion, numeroCredito, objeto, observaciones, otrosAportes, plazoCapital, plazoCompensatorio, primerVencCapital, primerVencInteres, tipoAmortizacion, volumenVtaAnual,asesor_causerK,idCofinanciador,linea_id,persona_IDPERSONA,subTipoLinea_id,destino,numeroResolucion) VALUES(" + objetoI.getAporteCofinanciador() + ", " + objetoI.getAportePropio() + ", '" + objetoI.getExpediente() + "', '" + objetoI.getFechaMutuo() + "', '" + objetoI.getFechaResolucion() + "', '" + objetoI.getFechaSolicitud() + "', " + objetoI.getFinanciamiento() + ", '" + objetoI.getFrecuenciaCapital() + "', '" + objetoI.getFrecuenciaInteres() + "', " + objetoI.getInicioActividad() + ", " + i + ", '" + objetoI.getNumeroCredito() + "', '" + objetoI.getObjeto() + "', '" + objetoI.getObservaciones() + "', '" + objetoI.getOtrosAportes() + "', " + objetoI.getPlazoCapital() + ", " + objetoI.getPlazoCompensatorio() + ", '" + objetoI.getPrimerVencCapital() + "', '" + objetoI.getPrimerVencInteres() + "', '" + objetoI.getTipoAmortizacion() + "', " + objetoI.getVolumenVtaAnual() + ", '" + objetoI.getAsesorcauserK() + "', " + banco + ", " + tipoLineaID + ", " + objetoI.getPersonaIDPERSONA().getIdpersona() + ", " + subtipoLineaID + ", '" + objetoI.getDestino() + "', '" + objetoI.getNumeroResolucion() + "' )");

                    objetoI.setId(objiJpa.getLastInsertedID());
                    insertObservacion("Nombre Titular Original: " + nombre, objetoI);
                    insertObservacion(objetoI.getObservaciones(), objetoI);


                    //QUE SE USE EL PRIMERO QUE SE ENCUENTRA EN CASO DE QUE SE ESTEN ENCONTRANDO MAS DE UNO
                    break;
                }
            } else {
                System.out.println("NO SE INSERTA EL OBJETOI: IDPROYECTO" + objetoI.getNumeroCredito().toString());
            }
        }


    }
    //   }

    private void migrarTipos() throws SQLException {
        ResultSet r = ba.tipoConsulta();
        while (r.next()) {
            ti = r.getString(1);
            po = r.getString(2);
            if (po.length() == 1) {
                po = '0' + po;              
            }
             tipoLineaId = BigDecimal.valueOf(Long.parseLong(ti+po));
            Linea tipoLinea = tipoLineaJpa.findLinea(tipoLineaId);
            
            if (tipoLinea== null) {
                tipoLineaJpa.insertar("INSERT INTO Linea(Id, monto, nombre) VALUES(" + tipoLineaId + "," + 1 +",'" + descripcion + "')");
                //System.out.println(tipoLineaId);
            }

        }
    }
}
