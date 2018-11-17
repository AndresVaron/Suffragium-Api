/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.JuradoLogic;

/**
 *
 * @author Equipo Suffragium
 */
@Path("jurado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ActualizacionJuradoResource {

    private static final Logger LOGGER = Logger.getLogger(ActualizacionJuradoResource.class.getName());

    @Inject
    private JuradoLogic juradoLogic;

    @PUT
    public void actualizarDireccion(@QueryParam("dir") String direccion,@QueryParam("key")String llave) {
        LOGGER.log(Level.INFO, "ActualizacionJuradoResource actualizarDireccion()");
        juradoLogic.actualizar(direccion,llave);
        LOGGER.log(Level.INFO, "ActualizacionJuradoResource actualizarDireccion()");
    }

    @GET
    public String getInfo() {
        LOGGER.log(Level.INFO, "ActualizacionJuradoResource getDireccion()");
        String resp = juradoLogic.getInfo();
        LOGGER.log(Level.INFO, "ActualizacionJuradoResource getDireccion(): OutPut = {0}", resp);
        return resp;
    }
}
