package logic;

import entities.VotoEntity;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.VotoPersistence;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class VotoLogic {

    private static final Logger LOGGER = Logger.getLogger(VotoLogic.class.getName());

    @Inject
    private VotoPersistence persistence;

    /**
     * Crea un voto en la persistencia.
     *
     * @param votoEntity La entidad que representa el voto a
     * persistir.
     * @return La entidad de el voto luego de persistirla.
     */
    public VotoEntity registrarVoto(VotoEntity votoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de el voto");
        persistence.create(votoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de el voto");
        return votoEntity;
    }

    /**
     *
     * Obtener todas los votos existentes en la base de datos.
     *
     * @return una lista de votos.
     */
    public List<VotoEntity> getVotos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los votos");
        List<VotoEntity> votos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los votos");
        return votos;
    }

}
