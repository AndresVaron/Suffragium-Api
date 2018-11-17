package persistence;

import entities.JuradoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class JuradoPersistence {

    private static final Logger LOGGER = Logger.getLogger(JuradoPersistence.class.getName());

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SuffragiumPU");

    EntityManager em = emf.createEntityManager();

    public String getDireccion() {
        LOGGER.log(Level.INFO, "Consultando la direccion del jurado");
        return em.find(JuradoEntity.class, new Long(1)).getDireccion();
    }

    public void actualizar(String direccion, String llave) {
        LOGGER.log(Level.INFO, "Actualizando jurado");
        em.getTransaction().begin();
        JuradoEntity juradoEntity = new JuradoEntity();
        juradoEntity.setId(new Long(1));
        juradoEntity.setDireccion(direccion);
        juradoEntity.setLlave(llave);
        em.merge(juradoEntity);
        em.getTransaction().commit();
        LOGGER.log(Level.INFO, "Saliendo de actualizar el jurado");
    }

    public String getInfo() {
        LOGGER.log(Level.INFO, "Consultando la direccion del jurado");
        JuradoEntity jurado = em.find(JuradoEntity.class, new Long(1));
        String info = jurado.getDireccion() + "\n" + jurado.getLlave();
        return info;
    }
}
