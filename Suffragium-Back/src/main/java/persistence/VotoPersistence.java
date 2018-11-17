package persistence;

import entities.VotoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import logic.Lugar;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class VotoPersistence {

    private static final Logger LOGGER = Logger.getLogger(VotoPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SuffragiumPU");

    EntityManager em = emf.createEntityManager();

    /**
     * Crear una voto
     *
     * Crea una nuevo voto con la información recibida en la entidad.
     *
     * @param votoEntity La entidad que representa el nuevo voto
     * @return La entidad creada
     */
    public VotoEntity create(VotoEntity votoEntity) {
        LOGGER.log(Level.INFO, "Creando un voto nuevo");
        em.getTransaction().begin();
        em.persist(votoEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Voto creado.");
        return votoEntity;
    }

    /**
     * Devuelve todos los votos de la base de datos.
     *
     * @return una lista con todos los votos que encuentre en la base de datos
     */
    public List<VotoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los votos");
        TypedQuery query = em.createQuery("select u from VotoEntity u", VotoEntity.class);
        return query.getResultList();
    }

    /**
     * Eliminar un voto
     *
     * Elimina el voto asociada al ID que recibe
     *
     * @param votosId El ID de la voto que se desea borrar
     */
    public void delete(Long votosId) {
        LOGGER.log(Level.INFO, "Borrando el voto con id = {0}", votosId);
        em.getTransaction().begin();
        VotoEntity entity = em.find(VotoEntity.class, votosId);
        em.remove(entity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de borrar el voto con id = {0}", votosId);
    }

    /**
     * Cuenta cuantos votos tiene un candidato
     *
     * @param idCandidato id del candidato al que se le va a contar los votos.
     * @return numero de votos del candidato
     */
    public int countByCandidatoId(Long idCandidato) {
        LOGGER.log(Level.INFO, "Contando los votos del candidato con id = {0}", idCandidato);
        TypedQuery<VotoEntity> q = em.createQuery("select p from VotoEntity p where p.idCandidato = :idCandidato", VotoEntity.class);
        q.setParameter("idCandidato", idCandidato);
        List<VotoEntity> results = q.getResultList();
        if (results == null) {
            results = new ArrayList<>();
        }
        LOGGER.log(Level.INFO, "Saliendo de contar los votos del candidato con id = {0}", idCandidato);
        return results.size();
    }

    public List<Lugar> getDepartamentos() {
        LOGGER.log(Level.INFO, "Calculando los votos de todos los departamentos");
        Query q = em.createNativeQuery("select p.departamento , count(p.*) from VotoEntity p group by p.departamento;");
        List<Object[]> results = q.getResultList();
        List<Lugar> lugares = new ArrayList<>();
        for (Object[] lug : results) {
            lugares.add(new Lugar(""+lug[0], Integer.parseInt(""+lug[1])));
        }
        LOGGER.log(Level.INFO, "Saliendo calcular los votos de todos los departamentos");
        return lugares;
    }
    
    public List<Lugar> getMunicipios() {
        LOGGER.log(Level.INFO, "Calculando los votos de todos los municipios");
        Query q = em.createNativeQuery("select p.municipio , count(p.*) from VotoEntity p group by p.municipio;");
        List<Object[]> results = q.getResultList();
        List<Lugar> lugares = new ArrayList<>();
        for (Object[] lug : results) {
            lugares.add(new Lugar(""+lug[0], Integer.parseInt(""+lug[1])));
        }
        LOGGER.log(Level.INFO, "Saliendo calcular los votos de todos los municipios");
        return lugares;
    }
}
