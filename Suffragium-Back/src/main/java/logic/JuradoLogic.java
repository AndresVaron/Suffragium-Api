package logic;

import entities.JuradoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.JuradoPersistence;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class JuradoLogic {

    private static final Logger LOGGER = Logger.getLogger(JuradoLogic.class.getName());

    @Inject
    private JuradoPersistence persistence;

    /**
     * Actualiza la direccion de la maquina de jurado en la persistencia.
     *
     * @param direccion La direccion del jurado a actualizar.
     * @param llave de la maquina del jurado
     */
    public void actualizar(String direccion,String llave) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizacion de el jurado");
        persistence.actualizar(direccion,llave);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de el jurado");
    }

    public String getInfo() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la direccion y la llave del jurado");
        String direccion = persistence.getInfo();
        LOGGER.log(Level.INFO, "Termina proceso de consultar la direccion y la llave del jurado");
        return direccion;
    }
    public String getDireccion() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la direccion del jurado");
        String direccion = persistence.getDireccion();
        LOGGER.log(Level.INFO, "Termina proceso de consultar la direccion del jurado");
        return direccion;
    }

}
