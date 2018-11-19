/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dtos.InfoGraficaBarraDTO;
import dtos.InfoGraficaMapaDTO;
import entities.CandidatoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.*;

/**
 *
 * @author Equipo Suffragium
 */
@Path("resultados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ResultadosResource {

    private static final Logger LOGGER = Logger.getLogger(ResultadosResource.class.getName());

    @Inject
    private ResultadosLogic resultadosLogic;

    @Inject
    private CandidatoLogic candidatosLogic;

    @Inject
    private VotoLogic votoLogic;

    /**
     * Genera los datos que se muestran en la grafica tipo barra de resultados.
     *
     * @param todos todos los candidatos
     * @return JSON {@link GraficaBarraDTO} - Datos de la grafica
     */
    @GET
    @Path("candidatos")
    public List<InfoGraficaBarraDTO> getBarra(@QueryParam("todos") Boolean todos) {
        LOGGER.log(Level.INFO, "ResultadosResource getBarra");
        ArrayList<InfoGraficaBarraDTO> grafica = new ArrayList<>();
        double total = votoLogic.getVotos().size();
        for (CandidatoEntity candidato : candidatosLogic.getCandidatos()) {
            double votos = resultadosLogic.contarVotosByCandidatoId(candidato.getId());
            if (votos > 0 || todos) {
                double porcentaje = 0;
                if (total > 0) {
                    porcentaje = Math.round((votos / total) * 100);
                }
                InfoGraficaBarraDTO info = new InfoGraficaBarraDTO();
                if (todos) {
                    info.setNombreCandidato(candidato.getNombre());
                } else {
                    info.setNombreCandidato(candidato.getPalabraClave());
                }
                info.setPorcentaje(porcentaje);
                grafica.add(info);
            }
        }
        LOGGER.log(Level.INFO, "ResultadosResource getBarra: output: {0}", grafica);
        return grafica;
    }

    /**
     * Genera los datos que se muestran en la grafica tipo mapa de departamentos
     * de los resultados.
     *
     * @return JSON {@link GraficaMapaDTO} - Datos de la grafica
     */
    @GET
    @Path("mapa/departamentos")
    public List<InfoGraficaMapaDTO> getMapaDepartamentos() {
        LOGGER.log(Level.INFO, "ResultadosResource getMapaDepartamentos");
        ArrayList<InfoGraficaMapaDTO> grafica = new ArrayList<>();
        for (Lugar departamento : resultadosLogic.getDepartamentos()) {
            InfoGraficaMapaDTO info = new InfoGraficaMapaDTO();
            info.setNombreLugar(departamento.getNombreLugar());
            info.setNumVotos(departamento.getNumVotos());
            info.setBusqueda(departamento.getBusqueda());
            grafica.add(info);
        }
        LOGGER.log(Level.INFO, "ResultadosResource getMapaDepartamentos: output: {0}", grafica);
        return grafica;
    }

    /**
     * Genera los datos que se muestran en la grafica tipo mapa de municipios de
     * los resultados.
     *
     * @return JSON {@link GraficaMapaDTO} - Datos de la grafica
     */
    @GET
    @Path("mapa/municipios")
    public List<InfoGraficaMapaDTO> getMapaMunicipios() {
        LOGGER.log(Level.INFO, "ResultadosResource getMapaMunicipios");
        ArrayList<InfoGraficaMapaDTO> grafica = new ArrayList<>();
        for (Lugar municipio : resultadosLogic.getMunicipios()) {
            InfoGraficaMapaDTO info = new InfoGraficaMapaDTO();
            info.setNombreLugar(municipio.getNombreLugar());
            info.setNumVotos(municipio.getNumVotos());
            info.setBusqueda(municipio.getBusqueda());
            grafica.add(info);
        }
        LOGGER.log(Level.INFO, "ResultadosResource getMapaMunicipios: output: {0}", grafica);
        return grafica;
    }
}
