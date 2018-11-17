/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entities.VotoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
     * @param valorVoto el voto a registrar
     * @param municipio donde se voto
     * @param departamento donde se voto
     */
    @POST
    public void votar(@QueryParam("voto") Long valorVoto, @QueryParam("municipio") String municipio, @QueryParam("departamento") String departamento) {
        LOGGER.log(Level.INFO, "VotosResource votar: Input = {0},{1},{2}", new Object[]{valorVoto, municipio, departamento});
        VotoEntity voto = new VotoEntity();
        voto.setIdCandidato(valorVoto);
        voto.setDepartamento(departamento);
        voto.setMunicipio(municipio);
        votoLogic.registrarVoto(voto);
        LOGGER.log(Level.INFO, "VotosResource votar");
    }

}
