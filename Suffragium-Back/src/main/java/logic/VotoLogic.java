package logic;

import entities.BusquedaEntity;
import entities.VotoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import persistence.BusquedaPersistence;
import persistence.VotoPersistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Equipo Suffragium
 */
@Stateless
public class VotoLogic {

    private static final Logger LOGGER = Logger.getLogger(VotoLogic.class.getName());

    @Inject
    private VotoPersistence persistence;

    @Inject
    private BusquedaPersistence busquedaPersistence;

    /**
     * Crea un voto en la persistencia.
     *
     * @param votoEntity La entidad que representa el voto a persistir.
     * @return La entidad de el voto luego de persistirla.
     */
    public VotoEntity registrarVoto(VotoEntity votoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de el voto");
        persistence.create(votoEntity);
        buscar(votoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de el voto");
        return votoEntity;
    }

    private void buscar(VotoEntity voto) {
        BusquedaEntity busqueda = busquedaPersistence.findByBusqueda(voto.getDepartamento());
        if (busqueda == null) {
            busqueda = new BusquedaEntity();
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + voto.getDepartamento()
                    + "&key=AIzaSyAbKGGFId6vb-FfOnHLEoxSGx68m3c06-Q";
            URL obj;
            try {
                obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.getResponseMessage();

                StringBuffer response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                busqueda.setBusqueda(voto.getDepartamento());
                busqueda.setRespuesta(response.toString().trim());
                busquedaPersistence.create(busqueda);
            } catch (IOException e) {
            }
        }
        busqueda = busquedaPersistence.findByBusqueda(voto.getMunicipio());
        if (busqueda == null) {
            busqueda = new BusquedaEntity();
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + voto.getMunicipio()
                    + "&key=AIzaSyAbKGGFId6vb-FfOnHLEoxSGx68m3c06-Q";
            URL obj;
            try {
                obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.getResponseMessage();

                StringBuffer response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                busqueda.setBusqueda(voto.getMunicipio());
                busqueda.setRespuesta(response.toString().trim());
                busquedaPersistence.create(busqueda);
            } catch (IOException e) {
            }
        }
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
