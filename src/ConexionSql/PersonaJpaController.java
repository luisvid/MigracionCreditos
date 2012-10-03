/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Persona;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Tsoci;
import Entidades.Tipodoc;
import Entidades.Provin;
import Entidades.Nacionalidad;
import Entidades.Localidad;
import Entidades.Calle;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author analian
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tsoci codi21 = persona.getCodi21();
            if (codi21 != null) {
                codi21 = em.getReference(codi21.getClass(), codi21.getCodi21());
                persona.setCodi21(codi21);
            }
            Tipodoc codi47 = persona.getCodi47();
            if (codi47 != null) {
                codi47 = em.getReference(codi47.getClass(), codi47.getCodi47());
                persona.setCodi47(codi47);
            }
            Provin codi08 = persona.getCodi08();
            if (codi08 != null) {
                codi08 = em.getReference(codi08.getClass(), codi08.getCodi08());
                persona.setCodi08(codi08);
            }
            Nacionalidad pais12 = persona.getPais12();
            if (pais12 != null) {
                pais12 = em.getReference(pais12.getClass(), pais12.getIdnacionalidad());
                persona.setPais12(pais12);
            }
            Localidad idlocalidad = persona.getIdlocalidad();
            if (idlocalidad != null) {
                idlocalidad = em.getReference(idlocalidad.getClass(), idlocalidad.getIdlocalidad());
                persona.setIdlocalidad(idlocalidad);
            }
            Calle idcalle = persona.getIdcalle();
            if (idcalle != null) {
                idcalle = em.getReference(idcalle.getClass(), idcalle.getIdcalle());
                persona.setIdcalle(idcalle);
            }
            em.persist(persona);
            if (codi21 != null) {
                codi21.getPersonaCollection().add(persona);
                codi21 = em.merge(codi21);
            }
            if (codi47 != null) {
                codi47.getPersonaCollection().add(persona);
                codi47 = em.merge(codi47);
            }
            if (codi08 != null) {
                codi08.getPersonaCollection().add(persona);
                codi08 = em.merge(codi08);
            }
            if (pais12 != null) {
                pais12.getPersonaCollection().add(persona);
                pais12 = em.merge(pais12);
            }
            if (idlocalidad != null) {
                idlocalidad.getPersonaCollection().add(persona);
                idlocalidad = em.merge(idlocalidad);
            }
            if (idcalle != null) {
                idcalle.getPersonaCollection().add(persona);
                idcalle = em.merge(idcalle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getIdpersona()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getIdpersona());
            Tsoci codi21Old = persistentPersona.getCodi21();
            Tsoci codi21New = persona.getCodi21();
            Tipodoc codi47Old = persistentPersona.getCodi47();
            Tipodoc codi47New = persona.getCodi47();
            Provin codi08Old = persistentPersona.getCodi08();
            Provin codi08New = persona.getCodi08();
            Nacionalidad pais12Old = persistentPersona.getPais12();
            Nacionalidad pais12New = persona.getPais12();
            Localidad idlocalidadOld = persistentPersona.getIdlocalidad();
            Localidad idlocalidadNew = persona.getIdlocalidad();
            Calle idcalleOld = persistentPersona.getIdcalle();
            Calle idcalleNew = persona.getIdcalle();
            if (codi21New != null) {
                codi21New = em.getReference(codi21New.getClass(), codi21New.getCodi21());
                persona.setCodi21(codi21New);
            }
            if (codi47New != null) {
                codi47New = em.getReference(codi47New.getClass(), codi47New.getCodi47());
                persona.setCodi47(codi47New);
            }
            if (codi08New != null) {
                codi08New = em.getReference(codi08New.getClass(), codi08New.getCodi08());
                persona.setCodi08(codi08New);
            }
            if (pais12New != null) {
                pais12New = em.getReference(pais12New.getClass(), pais12New.getIdnacionalidad());
                persona.setPais12(pais12New);
            }
            if (idlocalidadNew != null) {
                idlocalidadNew = em.getReference(idlocalidadNew.getClass(), idlocalidadNew.getIdlocalidad());
                persona.setIdlocalidad(idlocalidadNew);
            }
            if (idcalleNew != null) {
                idcalleNew = em.getReference(idcalleNew.getClass(), idcalleNew.getIdcalle());
                persona.setIdcalle(idcalleNew);
            }
            persona = em.merge(persona);
            if (codi21Old != null && !codi21Old.equals(codi21New)) {
                codi21Old.getPersonaCollection().remove(persona);
                codi21Old = em.merge(codi21Old);
            }
            if (codi21New != null && !codi21New.equals(codi21Old)) {
                codi21New.getPersonaCollection().add(persona);
                codi21New = em.merge(codi21New);
            }
            if (codi47Old != null && !codi47Old.equals(codi47New)) {
                codi47Old.getPersonaCollection().remove(persona);
                codi47Old = em.merge(codi47Old);
            }
            if (codi47New != null && !codi47New.equals(codi47Old)) {
                codi47New.getPersonaCollection().add(persona);
                codi47New = em.merge(codi47New);
            }
            if (codi08Old != null && !codi08Old.equals(codi08New)) {
                codi08Old.getPersonaCollection().remove(persona);
                codi08Old = em.merge(codi08Old);
            }
            if (codi08New != null && !codi08New.equals(codi08Old)) {
                codi08New.getPersonaCollection().add(persona);
                codi08New = em.merge(codi08New);
            }
            if (pais12Old != null && !pais12Old.equals(pais12New)) {
                pais12Old.getPersonaCollection().remove(persona);
                pais12Old = em.merge(pais12Old);
            }
            if (pais12New != null && !pais12New.equals(pais12Old)) {
                pais12New.getPersonaCollection().add(persona);
                pais12New = em.merge(pais12New);
            }
            if (idlocalidadOld != null && !idlocalidadOld.equals(idlocalidadNew)) {
                idlocalidadOld.getPersonaCollection().remove(persona);
                idlocalidadOld = em.merge(idlocalidadOld);
            }
            if (idlocalidadNew != null && !idlocalidadNew.equals(idlocalidadOld)) {
                idlocalidadNew.getPersonaCollection().add(persona);
                idlocalidadNew = em.merge(idlocalidadNew);
            }
            if (idcalleOld != null && !idcalleOld.equals(idcalleNew)) {
                idcalleOld.getPersonaCollection().remove(persona);
                idcalleOld = em.merge(idcalleOld);
            }
            if (idcalleNew != null && !idcalleNew.equals(idcalleOld)) {
                idcalleNew.getPersonaCollection().add(persona);
                idcalleNew = em.merge(idcalleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = persona.getIdpersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            Tsoci codi21 = persona.getCodi21();
            if (codi21 != null) {
                codi21.getPersonaCollection().remove(persona);
                codi21 = em.merge(codi21);
            }
            Tipodoc codi47 = persona.getCodi47();
            if (codi47 != null) {
                codi47.getPersonaCollection().remove(persona);
                codi47 = em.merge(codi47);
            }
            Provin codi08 = persona.getCodi08();
            if (codi08 != null) {
                codi08.getPersonaCollection().remove(persona);
                codi08 = em.merge(codi08);
            }
            Nacionalidad pais12 = persona.getPais12();
            if (pais12 != null) {
                pais12.getPersonaCollection().remove(persona);
                pais12 = em.merge(pais12);
            }
            Localidad idlocalidad = persona.getIdlocalidad();
            if (idlocalidad != null) {
                idlocalidad.getPersonaCollection().remove(persona);
                idlocalidad = em.merge(idlocalidad);
            }
            Calle idcalle = persona.getIdcalle();
            if (idcalle != null) {
                idcalle.getPersonaCollection().remove(persona);
                idcalle = em.merge(idcalle);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Persona as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Persona findPersona(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Persona as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void insertar(String string) throws SQLException {
        EntityManager em = getEntityManager();


        try {
            // Query q = em.createNativeQuery(string);
            String url = "jdbc:sqlserver://SRV-SII\\SQL_SII:0;databaseName=MIGRA3_CRED_FTYC";

            Connection conn = DriverManager.getConnection(url, "admin", "1234567");
            boolean statement = conn.createStatement().execute(string);


        } finally {
            em.close();
        }
    }

    public List<Persona> findXCuitOrDni(Long cuil, Long dni) {

        EntityManager em = getEntityManager();

        try {

            Query q = em.createNamedQuery("Persona.findXCuitOrDni");
            q.setParameter("cuil12", cuil);
            q.setParameter("nudo12", dni);
            
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            //em.close();
        }
    }
    public List<Persona> findXCuit(Long cuil) {

        EntityManager em = getEntityManager();

        try {

            Query q = em.createNamedQuery("Persona.findXCuit");
            q.setParameter("cuil12", cuil);
            
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }
    
    public List<Persona> findXDni(Long dni) {

        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Persona.findByNudo12");
            q.setParameter("nudo12", dni);
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }

    public List<Persona> findXdniNombre(String dni, String nombre) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Persona.findByDniNombre");
            q.setParameter("nudo12", BigInteger.valueOf(Long.parseLong(dni)));
            q.setParameter("nomb12", nombre);
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }

    public List<Persona> findXCuitNombre(long cuit, String nombre) {
    
              EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("Persona.findByCuilNombre");
            q.setParameter("nomb12", nombre);
            q.setParameter("cuil12", cuit);
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }

    public List<Persona> findXCuitRazonSocial(long cuit, String nombre) {
     
              EntityManager em = getEntityManager();
        try {

            Query q = em.createNamedQuery("Persona.findByCuilNombre");
            q.setParameter("nomb12", nombre);
            q.setParameter("cuil12", cuit);
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }

    public List<Persona> findXNombre(String nombre) {
              EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Persona.findByNomb12");
            q.setParameter("nomb12", nombre);
            List<Persona> o = q.getResultList();

            return o;
        } finally {
            //em.close();
        }
    }
    
    public void closeEntityManager(){
        EntityManager em = getEntityManager();
        em.close();
    }

    
}
