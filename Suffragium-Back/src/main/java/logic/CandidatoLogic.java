package logic;

import entities.CandidatoEntity;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.CandidatoPersistence;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class CandidatoLogic {

    private static final Logger LOGGER = Logger.getLogger(CandidatoLogic.class.getName());

    @Inject
    private CandidatoPersistence persistence;

    /**
     * Crea un candidato en la persistencia.
     *
     * @param candidatoEntity La entidad que representa el candidato a persistir.
     * @return La entidad de el candidato luego de persistirla.
     * @throws exceptions.BusinessLogicException si ya existe un candidato con el
     * mismo usuario
     */
    public CandidatoEntity registrarCandidato(CandidatoEntity candidatoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de el candidato");
        if (persistence.findByName(candidatoEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un candidato con el nombre \"" + candidatoEntity.getNombre() + "\"");
        }
        persistence.create(candidatoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de el candidato");
        return candidatoEntity;
    }

    /**
     *
     * Obtener todas los candidatos existentes en la base de datos.
     *
     * @return una lista de candidatos.
     */
    public List<CandidatoEntity> getCandidatos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los candidatos");
        List<CandidatoEntity> candidatos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los candidatos");
        return candidatos;
    }

    /**
     *
     * Obtener un candidato por medio de su usuario.
     *
     * @param candidatosId: id de el candidato para ser buscada.
     * @return el candidato solicitado por medio de su id.
     */
    public CandidatoEntity getCandidato(Long candidatosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el candidato con el id = {0}", candidatosId);
        CandidatoEntity candidatoEntity = persistence.find(candidatosId);
        if (candidatoEntity == null) {
            LOGGER.log(Level.SEVERE, "El candidato con el id = {0} no existe", candidatosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el candidato con el id = {0}", candidatosId);
        return candidatoEntity;
    }

    /**
     *
     * Actualizar un candidato.
     *
     * @param candidatosId: id de el candidato para buscarla en la base de
     * datos.
     * @param candidatoEntity: candidato con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return el candidato con los cambios actualizados en la base de datos.
     */
    public CandidatoEntity updateCandidato(Long candidatosId, CandidatoEntity candidatoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el candidato con id = {0}", candidatosId);
        CandidatoEntity newEntity = persistence.update(candidatoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el candidato con id = {0}", candidatoEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un candidato
     *
     * @param candidatosId: id de el candidato a borrar
     */
    public void deleteCandidato(Long candidatosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el candidato con usuario = {0}", candidatosId);
        persistence.delete(candidatosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el candidato con usuario = {0}", candidatosId);
    }
}
