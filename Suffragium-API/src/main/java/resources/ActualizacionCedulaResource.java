/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import logic.JuradoLogic;

/**
 * Clase que implementa el recurso "authors/{id}/books".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("cedula")
@Produces("application/json")
@Consumes(MediaType.TEXT_PLAIN)
@RequestScoped
public class ActualizacionCedulaResource {

    private static final Logger LOGGER = Logger.getLogger(ActualizacionCedulaResource.class.getName());

    @Inject
    private JuradoLogic juradoLogic;

    /**
     * Envia la informacion de la ActualizacionCedulaResource a la mesa de
     * jurado
     *
     * @param nombreCompleto
     * @param numeroDocumento
     * @param genero
     * @param fechaDeNacimiento
     * @param extra
     * @return la informacino de la peticion
     */
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response.ResponseBuilder leerCedula(@QueryParam("nombreCompleto") String nombreCompleto,
            @QueryParam("numeroDocumento") String numeroDocumento,
            @QueryParam("fechaDeNacimiento") String fechaDeNacimiento,
            @QueryParam("genero") String genero,
            @QueryParam("extra") String extra
    ) {
        String datos = "LECTURACEDULA:" + nombreCompleto + "," + numeroDocumento + "," + fechaDeNacimiento + "," + genero + "," + extra.split("\n")[4].split(":")[1] + "," + extra.split("\n")[3].split(":")[1];
        LOGGER.log(Level.INFO, "Datos del votante: {0}", datos);
        try {
            String direccion = juradoLogic.getDireccion();
            Socket socket = new Socket(direccion.split(":")[0], Integer.parseInt(direccion.split(":")[1]));
            PrintWriter escritor;
            escritor = new PrintWriter(socket.getOutputStream(), true);
            LOGGER.log(Level.INFO, "Preparándose para enviar los datos de la cedula");
            escritor.println(datos);
            LOGGER.log(Level.INFO, "ENVIÓ la peticion de la cedula");
        } catch (IOException ex) {
            LOGGER.log(Level.INFO, "Hay un error con los sockets");
        }
        return Response.ok();
    }

}
