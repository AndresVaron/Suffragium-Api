package persistence;

import entities.BusquedaEntity;
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
public class BusquedaPersistence {

    private static final Logger LOGGER = Logger.getLogger(BusquedaPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SuffragiumPU");

    EntityManager em = emf.createEntityManager();

    /**
     * Crear una busqueda
     *
     * Crea una nuevo busqueda con la información recibida en la entidad.
     *
     * @param busquedaEntity La entidad que representa el nuevo busqueda
     * @return La entidad creada
     */
    public BusquedaEntity create(BusquedaEntity busquedaEntity) {
        LOGGER.log(Level.INFO, "Creando un busqueda nuevo");
        em.getTransaction().begin();
        em.persist(busquedaEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Busqueda creado.");
        return busquedaEntity;
    }

    /**
     * Devuelve todos los busquedas de la base de datos.
     *
     * @return una lista con todos los busquedas que encuentre en la base de
     * datos
     */
    public List<BusquedaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los busquedas");
        TypedQuery query = em.createQuery("select u from BusquedaEntity u", BusquedaEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun busqueda con el id que se envía de argumento
     *
     * @param busquedasId: id correspondiente a el busqueda buscado.
     * @return una busqueda.
     */
    public BusquedaEntity find(Long busquedasId) {
        LOGGER.log(Level.INFO, "Consultando busqueda con id={0}", busquedasId);
        return em.find(BusquedaEntity.class, busquedasId);
    }

    /**
     * Actualizar un busqueda
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param busquedaEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public BusquedaEntity update(BusquedaEntity busquedaEntity) {
        LOGGER.log(Level.INFO, "Actualizando busqueda con id = {0}", busquedaEntity.getId());
        em.getTransaction().begin();
        busquedaEntity = em.merge(busquedaEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de actualizar el busqueda con id = {0}", busquedaEntity.getId());
        return busquedaEntity;
    }

    /**
     * Eliminar un busqueda
     *
     * Elimina el busqueda asociada al ID que recibe
     *
     * @param busquedasId El ID de la busqueda que se desea borrar
     */
    public void delete(Long busquedasId) {
        LOGGER.log(Level.INFO, "Borrando el busqueda con id = {0}", busquedasId);
        em.getTransaction().begin();
        BusquedaEntity entity = em.find(BusquedaEntity.class, busquedasId);
        em.remove(entity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de borrar el busqueda con id = {0}", busquedasId);
    }

    /**
     * Buscar una busqueda
     *
     * Busca si hay algun busqueda asociada a un nombre específico
     *
     * @param busqueda la busqueda a verificar
     * @return La busqueda encontrado o null. Nota: Si existe una o más
     * busquedas devuelve siempre la primera que encuentra
     */
    public BusquedaEntity findByBusqueda(String busqueda) {
        LOGGER.log(Level.INFO, "Consultando la busqueda = {0}", busqueda);
        TypedQuery<BusquedaEntity> q = em.createQuery("select p from BusquedaEntity p where p.busqueda = :busquedaParam", BusquedaEntity.class);
        q.setParameter("busquedaParam", busqueda);
        List<BusquedaEntity> results = q.getResultList();
        BusquedaEntity busquedaEntity = null;
        if (results == null) {
            busqueda = null;
        } else if (results.isEmpty()) {
            busqueda = null; 
        } else if (results.size() >= 1) {
            busquedaEntity = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la busqueda = {0}", busqueda);
        return busquedaEntity;
    }
}
