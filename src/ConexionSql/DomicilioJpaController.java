/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Domicilio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Provin;
import Entidades.PersonaRev;
import Entidades.Localidad;
import Entidades.Calle;
import Entidades.Barrio;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author analian
 */
public class DomicilioJpaController implements Serializable {

    public DomicilioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Domicilio domicilio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provin provinciaCODI08 = domicilio.getProvinciaCODI08();
            if (provinciaCODI08 != null) {
                provinciaCODI08 = em.getReference(provinciaCODI08.getClass(), provinciaCODI08.getCodi08());
                domicilio.setProvinciaCODI08(provinciaCODI08);
            }
            PersonaRev personaIDPERSONA = domicilio.getPersonaIDPERSONA();
            if (personaIDPERSONA != null) {
                personaIDPERSONA = em.getReference(personaIDPERSONA.getClass(), personaIDPERSONA.getIdpersona());
                domicilio.setPersonaIDPERSONA(personaIDPERSONA);
            }
            Localidad localidadIDLOCALIDAD = domicilio.getLocalidadIDLOCALIDAD();
            if (localidadIDLOCALIDAD != null) {
                localidadIDLOCALIDAD = em.getReference(localidadIDLOCALIDAD.getClass(), localidadIDLOCALIDAD.getIdlocalidad());
                domicilio.setLocalidadIDLOCALIDAD(localidadIDLOCALIDAD);
            }
            Calle calleIDCALLE = domicilio.getCalleIDCALLE();
            if (calleIDCALLE != null) {
                calleIDCALLE = em.getReference(calleIDCALLE.getClass(), calleIDCALLE.getIdcalle());
                domicilio.setCalleIDCALLE(calleIDCALLE);
            }
            Barrio barrioCODIBRR = domicilio.getBarrioCODIBRR();
            if (barrioCODIBRR != null) {
                barrioCODIBRR = em.getReference(barrioCODIBRR.getClass(), barrioCODIBRR.getCodiBrr());
                domicilio.setBarrioCODIBRR(barrioCODIBRR);
            }
            em.persist(domicilio);
            if (provinciaCODI08 != null) {
                provinciaCODI08.getDomicilioCollection().add(domicilio);
                provinciaCODI08 = em.merge(provinciaCODI08);
            }
            if (personaIDPERSONA != null) {
                personaIDPERSONA.getDomicilioList().add(domicilio);
                personaIDPERSONA = em.merge(personaIDPERSONA);
            }
            if (localidadIDLOCALIDAD != null) {
                localidadIDLOCALIDAD.getDomicilioCollection().add(domicilio);
                localidadIDLOCALIDAD = em.merge(localidadIDLOCALIDAD);
            }
            if (calleIDCALLE != null) {
                calleIDCALLE.getDomicilioCollection().add(domicilio);
                calleIDCALLE = em.merge(calleIDCALLE);
            }
            if (barrioCODIBRR != null) {
                barrioCODIBRR.getDomicilioCollection().add(domicilio);
                barrioCODIBRR = em.merge(barrioCODIBRR);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDomicilio(domicilio.getId()) != null) {
                throw new PreexistingEntityException("Domicilio " + domicilio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Domicilio domicilio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilio persistentDomicilio = em.find(Domicilio.class, domicilio.getId());
            Provin provinciaCODI08Old = persistentDomicilio.getProvinciaCODI08();
            Provin provinciaCODI08New = domicilio.getProvinciaCODI08();
            PersonaRev personaIDPERSONAOld = persistentDomicilio.getPersonaIDPERSONA();
            PersonaRev personaIDPERSONANew = domicilio.getPersonaIDPERSONA();
            Localidad localidadIDLOCALIDADOld = persistentDomicilio.getLocalidadIDLOCALIDAD();
            Localidad localidadIDLOCALIDADNew = domicilio.getLocalidadIDLOCALIDAD();
            Calle calleIDCALLEOld = persistentDomicilio.getCalleIDCALLE();
            Calle calleIDCALLENew = domicilio.getCalleIDCALLE();
            Barrio barrioCODIBRROld = persistentDomicilio.getBarrioCODIBRR();
            Barrio barrioCODIBRRNew = domicilio.getBarrioCODIBRR();
            if (provinciaCODI08New != null) {
                provinciaCODI08New = em.getReference(provinciaCODI08New.getClass(), provinciaCODI08New.getCodi08());
                domicilio.setProvinciaCODI08(provinciaCODI08New);
            }
            if (personaIDPERSONANew != null) {
                personaIDPERSONANew = em.getReference(personaIDPERSONANew.getClass(), personaIDPERSONANew.getIdpersona());
                domicilio.setPersonaIDPERSONA(personaIDPERSONANew);
            }
            if (localidadIDLOCALIDADNew != null) {
                localidadIDLOCALIDADNew = em.getReference(localidadIDLOCALIDADNew.getClass(), localidadIDLOCALIDADNew.getIdlocalidad());
                domicilio.setLocalidadIDLOCALIDAD(localidadIDLOCALIDADNew);
            }
            if (calleIDCALLENew != null) {
                calleIDCALLENew = em.getReference(calleIDCALLENew.getClass(), calleIDCALLENew.getIdcalle());
                domicilio.setCalleIDCALLE(calleIDCALLENew);
            }
            if (barrioCODIBRRNew != null) {
                barrioCODIBRRNew = em.getReference(barrioCODIBRRNew.getClass(), barrioCODIBRRNew.getCodiBrr());
                domicilio.setBarrioCODIBRR(barrioCODIBRRNew);
            }
            domicilio = em.merge(domicilio);
            if (provinciaCODI08Old != null && !provinciaCODI08Old.equals(provinciaCODI08New)) {
                provinciaCODI08Old.getDomicilioCollection().remove(domicilio);
                provinciaCODI08Old = em.merge(provinciaCODI08Old);
            }
            if (provinciaCODI08New != null && !provinciaCODI08New.equals(provinciaCODI08Old)) {
                provinciaCODI08New.getDomicilioCollection().add(domicilio);
                provinciaCODI08New = em.merge(provinciaCODI08New);
            }
            if (personaIDPERSONAOld != null && !personaIDPERSONAOld.equals(personaIDPERSONANew)) {
                personaIDPERSONAOld.getDomicilioList().remove(domicilio);
                personaIDPERSONAOld = em.merge(personaIDPERSONAOld);
            }
            if (personaIDPERSONANew != null && !personaIDPERSONANew.equals(personaIDPERSONAOld)) {
                personaIDPERSONANew.getDomicilioList().add(domicilio);
                personaIDPERSONANew = em.merge(personaIDPERSONANew);
            }
            if (localidadIDLOCALIDADOld != null && !localidadIDLOCALIDADOld.equals(localidadIDLOCALIDADNew)) {
                localidadIDLOCALIDADOld.getDomicilioCollection().remove(domicilio);
                localidadIDLOCALIDADOld = em.merge(localidadIDLOCALIDADOld);
            }
            if (localidadIDLOCALIDADNew != null && !localidadIDLOCALIDADNew.equals(localidadIDLOCALIDADOld)) {
                localidadIDLOCALIDADNew.getDomicilioCollection().add(domicilio);
                localidadIDLOCALIDADNew = em.merge(localidadIDLOCALIDADNew);
            }
            if (calleIDCALLEOld != null && !calleIDCALLEOld.equals(calleIDCALLENew)) {
                calleIDCALLEOld.getDomicilioCollection().remove(domicilio);
                calleIDCALLEOld = em.merge(calleIDCALLEOld);
            }
            if (calleIDCALLENew != null && !calleIDCALLENew.equals(calleIDCALLEOld)) {
                calleIDCALLENew.getDomicilioCollection().add(domicilio);
                calleIDCALLENew = em.merge(calleIDCALLENew);
            }
            if (barrioCODIBRROld != null && !barrioCODIBRROld.equals(barrioCODIBRRNew)) {
                barrioCODIBRROld.getDomicilioCollection().remove(domicilio);
                barrioCODIBRROld = em.merge(barrioCODIBRROld);
            }
            if (barrioCODIBRRNew != null && !barrioCODIBRRNew.equals(barrioCODIBRROld)) {
                barrioCODIBRRNew.getDomicilioCollection().add(domicilio);
                barrioCODIBRRNew = em.merge(barrioCODIBRRNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = domicilio.getId();
                if (findDomicilio(id) == null) {
                    throw new NonexistentEntityException("The domicilio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Domicilio domicilio;
            try {
                domicilio = em.getReference(Domicilio.class, id);
                domicilio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The domicilio with id " + id + " no longer exists.", enfe);
            }
            Provin provinciaCODI08 = domicilio.getProvinciaCODI08();
            if (provinciaCODI08 != null) {
                provinciaCODI08.getDomicilioCollection().remove(domicilio);
                provinciaCODI08 = em.merge(provinciaCODI08);
            }
            PersonaRev personaIDPERSONA = domicilio.getPersonaIDPERSONA();
            if (personaIDPERSONA != null) {
                personaIDPERSONA.getDomicilioList().remove(domicilio);
                personaIDPERSONA = em.merge(personaIDPERSONA);
            }
            Localidad localidadIDLOCALIDAD = domicilio.getLocalidadIDLOCALIDAD();
            if (localidadIDLOCALIDAD != null) {
                localidadIDLOCALIDAD.getDomicilioCollection().remove(domicilio);
                localidadIDLOCALIDAD = em.merge(localidadIDLOCALIDAD);
            }
            Calle calleIDCALLE = domicilio.getCalleIDCALLE();
            if (calleIDCALLE != null) {
                calleIDCALLE.getDomicilioCollection().remove(domicilio);
                calleIDCALLE = em.merge(calleIDCALLE);
            }
            Barrio barrioCODIBRR = domicilio.getBarrioCODIBRR();
            if (barrioCODIBRR != null) {
                barrioCODIBRR.getDomicilioCollection().remove(domicilio);
                barrioCODIBRR = em.merge(barrioCODIBRR);
            }
            em.remove(domicilio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Domicilio> findDomicilioEntities() {
        return findDomicilioEntities(true, -1, -1);
    }

    public List<Domicilio> findDomicilioEntities(int maxResults, int firstResult) {
        return findDomicilioEntities(false, maxResults, firstResult);
    }

    private List<Domicilio> findDomicilioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Domicilio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Domicilio findDomicilio(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Domicilio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDomicilioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Domicilio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public void insertar(String string) throws SQLException {
      EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA4_CRED_FTYC";

            Connection conn = DriverManager.getConnection(url, "admin", "1234567");
            conn.createStatement().execute("SET IDENTITY_INSERT Linea ON");
            conn.createStatement().execute(string);
            conn.createStatement().execute("SET IDENTITY_INSERT Linea OFF");

        } finally {
            //System.out.println(string);
            //em.close();
        }
    }

    public List<Domicilio> findXPersona(PersonaRev personaRev) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Domicilio.findByPersona");
            q.setParameter("persona", personaRev);
            List<Domicilio> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }

    }
    
}
