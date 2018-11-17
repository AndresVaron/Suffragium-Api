package persistence;

import entities.CandidatoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * 
 * @author Equipo Suffragium
 */
@Stateless
public class CandidatoPersistence {

    private static final Logger LOGGER = Logger.getLogger(CandidatoPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SuffragiumPU");

    EntityManager em = emf.createEntityManager();

    /**
     * Crear una candidato
     *
     * Crea una nuevo candidato con la información recibida en la entidad.
     *
     * @param candidatoEntity La entidad que representa el nuevo candidato
     * @return La entidad creada
     */
    public CandidatoEntity create(CandidatoEntity candidatoEntity) {
        LOGGER.log(Level.INFO, "Creando un candidato nuevo");
        em.getTransaction().begin();
        em.persist(candidatoEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Candidato creado.");
        return candidatoEntity;
    }

    /**
     * Devuelve todos los candidatos de la base de datos.
     *
     * @return una lista con todos los candidatos que encuentre en la base de
     * datos
     */
    public List<CandidatoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los candidatos");
        TypedQuery query = em.createQuery("select u from CandidatoEntity u", CandidatoEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun candidato con el id que se envía de argumento
     *
     * @param candidatosId: id correspondiente a el candidato buscado.
     * @return una candidato.
     */
    public CandidatoEntity find(Long candidatosId) {
        LOGGER.log(Level.INFO, "Consultando candidato con id={0}", candidatosId);
        return em.find(CandidatoEntity.class, candidatosId);
    }

    /**
     * Actualizar un candidato
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param candidatoEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public CandidatoEntity update(CandidatoEntity candidatoEntity) {
        LOGGER.log(Level.INFO, "Actualizando candidato con id = {0}", candidatoEntity.getId());
        em.getTransaction().begin();
        candidatoEntity = em.merge(candidatoEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de actualizar el candidato con id = {0}", candidatoEntity.getId());
        return candidatoEntity;
    }

    /**
     * Eliminar un candidato
     *
     * Elimina el candidato asociada al ID que recibe
     *
     * @param candidatosId El ID de la candidato que se desea borrar
     */
    public void delete(Long candidatosId) {
        LOGGER.log(Level.INFO, "Borrando el candidato con id = {0}", candidatosId);
        em.getTransaction().begin();
        CandidatoEntity entity = em.find(CandidatoEntity.class, candidatosId);
        em.remove(entity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de borrar el candidato con id = {0}", candidatosId);
    }

    /**
     * Buscar una candidato
     *
     * Busca si hay algun candidato asociada a un nombre específico
     *
     * @param candidatosNombre El nombre del candidato que se busca
     * @return La candidato encontrado o null. Nota: Si existe uno o más
     * candidatos devuelve siempre el primero que encuentra
     */
    public CandidatoEntity findByName(String candidatosNombre) {
        LOGGER.log(Level.INFO, "Consultando el candidato con nombre = {0}", candidatosNombre);
        TypedQuery<CandidatoEntity> q = em.createQuery("select p from CandidatoEntity p where p.nombre = :candidatosNombre", CandidatoEntity.class);
        q.setParameter("candidatosNombre", candidatosNombre);
        List<CandidatoEntity> results = q.getResultList();
        CandidatoEntity candidato = null;
        if (results == null) {
            candidato = null;
        } else if (results.isEmpty()) {
            candidato = null;
        } else if (results.size() >= 1) {
            candidato = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el candidato con nombre = {0}", candidatosNombre);
        return candidato;
    }
}
