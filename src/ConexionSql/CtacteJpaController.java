/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Ctacte;
import Entidades.CtactePK;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Tipomov;
import Entidades.Objetoi;
import Entidades.Emideta;
import Entidades.Cuota;
import Entidades.Concepto;
import Entidades.Caratula;
import Entidades.Boleto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import oracle.toplink.essentials.queryframework.ValueReadQuery;
import oracle.toplink.essentials.sessions.Session;
import oracle.toplink.essentials.tools.sessionmanagement.SessionManager;

/**
 *
 * @author analian
 */
public class CtacteJpaController implements Serializable {

    public CtacteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ctacte ctacte) throws PreexistingEntityException, Exception {
        if (ctacte.getCtactePK() == null) {
            ctacte.setCtactePK(new CtactePK());
        }
//        ctacte.getCtactePK().setObjetoiId(ctacte.getObjetoi().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipomov tipomovId = ctacte.getTipomovId();
            if (tipomovId != null) {
                tipomovId = em.getReference(tipomovId.getClass(), tipomovId.getId());
                ctacte.setTipomovId(tipomovId);
            }
            Objetoi objetoi = ctacte.getObjetoi();
            if (objetoi != null) {
                objetoi = em.getReference(objetoi.getClass(), objetoi.getId());
                ctacte.setObjetoi(objetoi);
            }
            Emideta emidetaId = ctacte.getEmidetaId();
            if (emidetaId != null) {
                emidetaId = em.getReference(emidetaId.getClass(), emidetaId.getId());
                ctacte.setEmidetaId(emidetaId);
            }
            Cuota cuotaId = ctacte.getCuotaId();
            if (cuotaId != null) {
                cuotaId = em.getReference(cuotaId.getClass(), cuotaId.getId());
                ctacte.setCuotaId(cuotaId);
            }
            Concepto facturadoId = ctacte.getFacturadoId();
            if (facturadoId != null) {
                facturadoId = em.getReference(facturadoId.getClass(), facturadoId.getId());
                ctacte.setFacturadoId(facturadoId);
            }
            Concepto asociadoId = ctacte.getAsociadoId();
            if (asociadoId != null) {
                asociadoId = em.getReference(asociadoId.getClass(), asociadoId.getId());
                ctacte.setAsociadoId(asociadoId);
            }
            Caratula caratulaId = ctacte.getCaratulaId();
            if (caratulaId != null) {
                caratulaId = em.getReference(caratulaId.getClass(), caratulaId.getId());
                ctacte.setCaratulaId(caratulaId);
            }
            Boleto boleto = ctacte.getBoleto();
            if (boleto != null) {
                boleto = em.getReference(boleto.getClass(), boleto.getBoletoPK());
                ctacte.setBoleto(boleto);
            }
            em.persist(ctacte);
            if (tipomovId != null) {
                tipomovId.getCtacteCollection().add(ctacte);
                tipomovId = em.merge(tipomovId);
            }
            if (objetoi != null) {
                objetoi.getCtacteCollection().add(ctacte);
                objetoi = em.merge(objetoi);
            }
            if (emidetaId != null) {
                emidetaId.getCtacteCollection().add(ctacte);
                emidetaId = em.merge(emidetaId);
            }
            if (cuotaId != null) {
                cuotaId.getCtacteCollection().add(ctacte);
                cuotaId = em.merge(cuotaId);
            }
            if (facturadoId != null) {
                facturadoId.getCtacteCollection().add(ctacte);
                facturadoId = em.merge(facturadoId);
            }
            if (asociadoId != null) {
                asociadoId.getCtacteCollection().add(ctacte);
                asociadoId = em.merge(asociadoId);
            }
            if (caratulaId != null) {
                caratulaId.getCtacteCollection().add(ctacte);
                caratulaId = em.merge(caratulaId);
            }
            if (boleto != null) {
                boleto.getCtacteCollection().add(ctacte);
                boleto = em.merge(boleto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCtacte(ctacte.getCtactePK()) != null) {
                throw new PreexistingEntityException("Ctacte " + ctacte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void edit(Ctacte ctacte) throws NonexistentEntityException, Exception {
//        ctacte.getCtactePK().setObjetoiId(ctacte.getObjetoi().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ctacte persistentCtacte = em.find(Ctacte.class, ctacte.getCtactePK());
            Tipomov tipomovIdOld = persistentCtacte.getTipomovId();
            Tipomov tipomovIdNew = ctacte.getTipomovId();
            Objetoi objetoiOld = persistentCtacte.getObjetoi();
            Objetoi objetoiNew = ctacte.getObjetoi();
            Emideta emidetaIdOld = persistentCtacte.getEmidetaId();
            Emideta emidetaIdNew = ctacte.getEmidetaId();
            Cuota cuotaIdOld = persistentCtacte.getCuotaId();
            Cuota cuotaIdNew = ctacte.getCuotaId();
            Concepto facturadoIdOld = persistentCtacte.getFacturadoId();
            Concepto facturadoIdNew = ctacte.getFacturadoId();
            Concepto asociadoIdOld = persistentCtacte.getAsociadoId();
            Concepto asociadoIdNew = ctacte.getAsociadoId();
            Caratula caratulaIdOld = persistentCtacte.getCaratulaId();
            Caratula caratulaIdNew = ctacte.getCaratulaId();
            Boleto boletoOld = persistentCtacte.getBoleto();
            Boleto boletoNew = ctacte.getBoleto();
            if (tipomovIdNew != null) {
                tipomovIdNew = em.getReference(tipomovIdNew.getClass(), tipomovIdNew.getId());
                ctacte.setTipomovId(tipomovIdNew);
            }
            if (objetoiNew != null) {
                objetoiNew = em.getReference(objetoiNew.getClass(), objetoiNew.getId());
                ctacte.setObjetoi(objetoiNew);
            }
            if (emidetaIdNew != null) {
                emidetaIdNew = em.getReference(emidetaIdNew.getClass(), emidetaIdNew.getId());
                ctacte.setEmidetaId(emidetaIdNew);
            }
            if (cuotaIdNew != null) {
                cuotaIdNew = em.getReference(cuotaIdNew.getClass(), cuotaIdNew.getId());
                ctacte.setCuotaId(cuotaIdNew);
            }
            if (facturadoIdNew != null) {
                facturadoIdNew = em.getReference(facturadoIdNew.getClass(), facturadoIdNew.getId());
                ctacte.setFacturadoId(facturadoIdNew);
            }
            if (asociadoIdNew != null) {
                asociadoIdNew = em.getReference(asociadoIdNew.getClass(), asociadoIdNew.getId());
                ctacte.setAsociadoId(asociadoIdNew);
            }
            if (caratulaIdNew != null) {
                caratulaIdNew = em.getReference(caratulaIdNew.getClass(), caratulaIdNew.getId());
                ctacte.setCaratulaId(caratulaIdNew);
            }
            if (boletoNew != null) {
                boletoNew = em.getReference(boletoNew.getClass(), boletoNew.getBoletoPK());
                ctacte.setBoleto(boletoNew);
            }
            ctacte = em.merge(ctacte);
            if (tipomovIdOld != null && !tipomovIdOld.equals(tipomovIdNew)) {
                tipomovIdOld.getCtacteCollection().remove(ctacte);
                tipomovIdOld = em.merge(tipomovIdOld);
            }
            if (tipomovIdNew != null && !tipomovIdNew.equals(tipomovIdOld)) {
                tipomovIdNew.getCtacteCollection().add(ctacte);
                tipomovIdNew = em.merge(tipomovIdNew);
            }
            if (objetoiOld != null && !objetoiOld.equals(objetoiNew)) {
                objetoiOld.getCtacteCollection().remove(ctacte);
                objetoiOld = em.merge(objetoiOld);
            }
            if (objetoiNew != null && !objetoiNew.equals(objetoiOld)) {
                objetoiNew.getCtacteCollection().add(ctacte);
                objetoiNew = em.merge(objetoiNew);
            }
            if (emidetaIdOld != null && !emidetaIdOld.equals(emidetaIdNew)) {
                emidetaIdOld.getCtacteCollection().remove(ctacte);
                emidetaIdOld = em.merge(emidetaIdOld);
            }
            if (emidetaIdNew != null && !emidetaIdNew.equals(emidetaIdOld)) {
                emidetaIdNew.getCtacteCollection().add(ctacte);
                emidetaIdNew = em.merge(emidetaIdNew);
            }
            if (cuotaIdOld != null && !cuotaIdOld.equals(cuotaIdNew)) {
                cuotaIdOld.getCtacteCollection().remove(ctacte);
                cuotaIdOld = em.merge(cuotaIdOld);
            }
            if (cuotaIdNew != null && !cuotaIdNew.equals(cuotaIdOld)) {
                cuotaIdNew.getCtacteCollection().add(ctacte);
                cuotaIdNew = em.merge(cuotaIdNew);
            }
            if (facturadoIdOld != null && !facturadoIdOld.equals(facturadoIdNew)) {
                facturadoIdOld.getCtacteCollection().remove(ctacte);
                facturadoIdOld = em.merge(facturadoIdOld);
            }
            if (facturadoIdNew != null && !facturadoIdNew.equals(facturadoIdOld)) {
                facturadoIdNew.getCtacteCollection().add(ctacte);
                facturadoIdNew = em.merge(facturadoIdNew);
            }
            if (asociadoIdOld != null && !asociadoIdOld.equals(asociadoIdNew)) {
                asociadoIdOld.getCtacteCollection().remove(ctacte);
                asociadoIdOld = em.merge(asociadoIdOld);
            }
            if (asociadoIdNew != null && !asociadoIdNew.equals(asociadoIdOld)) {
                asociadoIdNew.getCtacteCollection().add(ctacte);
                asociadoIdNew = em.merge(asociadoIdNew);
            }
            if (caratulaIdOld != null && !caratulaIdOld.equals(caratulaIdNew)) {
                caratulaIdOld.getCtacteCollection().remove(ctacte);
                caratulaIdOld = em.merge(caratulaIdOld);
            }
            if (caratulaIdNew != null && !caratulaIdNew.equals(caratulaIdOld)) {
                caratulaIdNew.getCtacteCollection().add(ctacte);
                caratulaIdNew = em.merge(caratulaIdNew);
            }
            if (boletoOld != null && !boletoOld.equals(boletoNew)) {
                boletoOld.getCtacteCollection().remove(ctacte);
                boletoOld = em.merge(boletoOld);
            }
            if (boletoNew != null && !boletoNew.equals(boletoOld)) {
                boletoNew.getCtacteCollection().add(ctacte);
                boletoNew = em.merge(boletoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CtactePK id = ctacte.getCtactePK();
                if (findCtacte(id) == null) {
                    throw new NonexistentEntityException("The ctacte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void destroy(CtactePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ctacte ctacte;
            try {
                ctacte = em.getReference(Ctacte.class, id);
                ctacte.getCtactePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ctacte with id " + id + " no longer exists.", enfe);
            }
            Tipomov tipomovId = ctacte.getTipomovId();
            if (tipomovId != null) {
                tipomovId.getCtacteCollection().remove(ctacte);
                tipomovId = em.merge(tipomovId);
            }
            Objetoi objetoi = ctacte.getObjetoi();
            if (objetoi != null) {
                objetoi.getCtacteCollection().remove(ctacte);
                objetoi = em.merge(objetoi);
            }
            Emideta emidetaId = ctacte.getEmidetaId();
            if (emidetaId != null) {
                emidetaId.getCtacteCollection().remove(ctacte);
                emidetaId = em.merge(emidetaId);
            }
            Cuota cuotaId = ctacte.getCuotaId();
            if (cuotaId != null) {
                cuotaId.getCtacteCollection().remove(ctacte);
                cuotaId = em.merge(cuotaId);
            }
            Concepto facturadoId = ctacte.getFacturadoId();
            if (facturadoId != null) {
                facturadoId.getCtacteCollection().remove(ctacte);
                facturadoId = em.merge(facturadoId);
            }
            Concepto asociadoId = ctacte.getAsociadoId();
            if (asociadoId != null) {
                asociadoId.getCtacteCollection().remove(ctacte);
                asociadoId = em.merge(asociadoId);
            }
            Caratula caratulaId = ctacte.getCaratulaId();
            if (caratulaId != null) {
                caratulaId.getCtacteCollection().remove(ctacte);
                caratulaId = em.merge(caratulaId);
            }
            Boleto boleto = ctacte.getBoleto();
            if (boleto != null) {
                boleto.getCtacteCollection().remove(ctacte);
                boleto = em.merge(boleto);
            }
            em.remove(ctacte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public List<Ctacte> findCtacteEntities() {
        return findCtacteEntities(true, -1, -1);
    }

    public List<Ctacte> findCtacteEntities(int maxResults, int firstResult) {
        return findCtacteEntities(false, maxResults, firstResult);
    }

    private List<Ctacte> findCtacteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Ctacte as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            //em.close();
        }
    }

    public Ctacte findCtacte(CtactePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ctacte.class, id);
        } finally {
            //em.close();
        }
    }

    public int getCtacteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Ctacte as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }

    public Vector findUltimoMovimiento() {
        EntityManager em = getEntityManager();


        try {
            Query q = em.createNativeQuery("SELECT MAX(c.movimientoCtacte) FROM Ctacte c");
            //Number count = (Number) toplink.session-Ana.executeQuery(q);
            //  Session session = SessionManager.getManager().getSession("library");
            // Number count = (Number) session.executeQuery(q);
            //Object numero = q.getSingleResult();
            return (Vector) q.getSingleResult();
        } finally {
            //em.close();
        }
    }

    public List<Ctacte> findAll1() {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Ctacte.findAll");

            List<Ctacte> o = q.getResultList();
            return o;
        } finally {
            //em.close();
        }
    }

    public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA4_CRED_FTYC"; 
            
            Connection conn = DriverManager.getConnection(url,"admin","1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");             
            conn.createStatement().execute(string);             conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF"); 
           
            
        } finally {
            //em.close();
        }
    }

    public List<Ctacte> findCtaCteObjetoi(Objetoi objetoi) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Ctacte.findByObjetoiId");
            q.setParameter("objetoiId", objetoi.getId());

            List<Ctacte> o = q.getResultList();
            return o;
        } finally {
            //em.close();
        }
    }
    
    public void closeEEntityManager(){
        EntityManager em = getEntityManager();
        em.close();
    }        
}
