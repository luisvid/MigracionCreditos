/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionSql;

import ConexionSql.exceptions.NonexistentEntityException;
import ConexionSql.exceptions.PreexistingEntityException;
import Entidades.Certificado;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import Entidades.Objetoi;

/**
 *
 * @author analian
 */
public class CertificadoJpaController implements Serializable {

    public CertificadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Certificado certificado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetoi creditoId = certificado.getCreditoId();
            if (creditoId != null) {
                creditoId = em.getReference(creditoId.getClass(), creditoId.getId());
                certificado.setCreditoId(creditoId);
            }
            em.persist(certificado);
            if (creditoId != null) {
                creditoId.getCertificadoCollection().add(certificado);
                creditoId = em.merge(creditoId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCertificado(certificado.getId()) != null) {
                throw new PreexistingEntityException("Certificado " + certificado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Certificado certificado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificado persistentCertificado = em.find(Certificado.class, certificado.getId());
            Objetoi creditoIdOld = persistentCertificado.getCreditoId();
            Objetoi creditoIdNew = certificado.getCreditoId();
            if (creditoIdNew != null) {
                creditoIdNew = em.getReference(creditoIdNew.getClass(), creditoIdNew.getId());
                certificado.setCreditoId(creditoIdNew);
            }
            certificado = em.merge(certificado);
            if (creditoIdOld != null && !creditoIdOld.equals(creditoIdNew)) {
                creditoIdOld.getCertificadoCollection().remove(certificado);
                creditoIdOld = em.merge(creditoIdOld);
            }
            if (creditoIdNew != null && !creditoIdNew.equals(creditoIdOld)) {
                creditoIdNew.getCertificadoCollection().add(certificado);
                creditoIdNew = em.merge(creditoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = certificado.getId();
                if (findCertificado(id) == null) {
                    throw new NonexistentEntityException("The certificado with id " + id + " no longer exists.");
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
            Certificado certificado;
            try {
                certificado = em.getReference(Certificado.class, id);
                certificado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The certificado with id " + id + " no longer exists.", enfe);
            }
            Objetoi creditoId = certificado.getCreditoId();
            if (creditoId != null) {
                creditoId.getCertificadoCollection().remove(certificado);
                creditoId = em.merge(creditoId);
            }
            em.remove(certificado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Certificado> findCertificadoEntities() {
        return findCertificadoEntities(true, -1, -1);
    }

    public List<Certificado> findCertificadoEntities(int maxResults, int firstResult) {
        return findCertificadoEntities(false, maxResults, firstResult);
    }

    private List<Certificado> findCertificadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Certificado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Certificado findCertificado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Certificado.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertificadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Certificado as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
