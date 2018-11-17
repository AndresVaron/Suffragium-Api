/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.VotoDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.VotoLogic;

/**
 *
 * @author Equipo Suffragium
 */
@Path("votos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class VotosResource {

    private static final Logger LOGGER = Logger.getLogger(VotosResource.class.getName());

    @Inject
    private VotoLogic votoLogic;

    /**
     * Vota
     *
     * @param voto a registrar en la BD
     */
    @POST
    public void votar(VotoDTO voto) {
        LOGGER.log(Level.INFO, "VotosResource votar, Input: {0} ", voto);
        votoLogic.registrarVoto(voto.toEntity());
        LOGGER.log(Level.INFO, "VotosResource votar");
    }

}
