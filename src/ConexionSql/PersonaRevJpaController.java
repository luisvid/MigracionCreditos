/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionAccess.exceptions.NonexistentEntityException;
import ConexionAccess.exceptions.PreexistingEntityException;
import Entidades.PersonaRev;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author analian
 */
public class PersonaRevJpaController implements Serializable {

    public PersonaRevJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PersonaRev personaRev) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(personaRev);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonaRev(personaRev.getIdpersona()) != null) {
                throw new PreexistingEntityException("PersonaRev " + personaRev + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonaRev personaRev) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            personaRev = em.merge(personaRev);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = personaRev.getIdpersona();
                if (findPersonaRev(id) == null) {
                    throw new NonexistentEntityException("The personaRev with id " + id + " no longer exists.");
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
            PersonaRev personaRev;
            try {
                personaRev = em.getReference(PersonaRev.class, id);
                personaRev.getIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personaRev with id " + id + " no longer exists.", enfe);
            }
            em.remove(personaRev);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonaRev> findPersonaRevEntities() {
        return findPersonaRevEntities(true, -1, -1);
    }

    public List<PersonaRev> findPersonaRevEntities(int maxResults, int firstResult) {
        return findPersonaRevEntities(false, maxResults, firstResult);
    }

    private List<PersonaRev> findPersonaRevEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PersonaRev as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PersonaRev findPersonaRev(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonaRev.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaRevCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from PersonaRev as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<PersonaRev> findXNombreYDir(String nro, String calle, String nombre) {
        EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("PersonaRev.findByNombreDir");
            q.setParameter("nom12", nombre);
            q.setParameter("calle", calle);
            q.setParameter("numero", nro);
            List<PersonaRev> o = q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }

    public List<PersonaRev> findXNombre(String nombre) {
         EntityManager em = getEntityManager();


        try {

            Query q = em.createNamedQuery("PersonaRev.findByNom12");
            q.setParameter("nom12", nombre);
            
            List<PersonaRev> o = q.getResultList();

            return o;
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
           // conn.createStatement().execute("SET IDENTITY_INSERT PersonaRev ON");
            conn.createStatement().execute(string);
           // conn.createStatement().execute("SET IDENTITY_INSERT PersonaRev OFF");

        } finally {
            //System.out.println(string);
            //em.close();
        }
    }
       public List<PersonaRev> findXCuit(Long cuil) {

        EntityManager em = getEntityManager();

        try {

            Query q = em.createNamedQuery("PersonaRev.findXCuit");
            q.setParameter("cuil12", cuil);
            
            List<PersonaRev> o =  q.getResultList();

            return o;
        } finally {
            em.close();
        }
    }

  
}
