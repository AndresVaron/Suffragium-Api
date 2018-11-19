/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import persistence.BusquedaPersistence;
import persistence.VotoPersistence;

/**
 *
 * @author Equipo Suffragium
 */
public class ResultadosLogic {

    private static final Logger LOGGER = Logger.getLogger(ResultadosLogic.class.getName());

    @Inject
    private VotoPersistence persistence;

    
    @Inject
    private BusquedaPersistence busquedaPersistence;
    
    public int contarVotosByCandidatoId(Long idCandidato) {
        LOGGER.log(Level.INFO, "Inicia proceso de contar todos los votos de un candidato");
        int total = persistence.countByCandidatoId(idCandidato);
        LOGGER.log(Level.INFO, "Termina proceso de contar todos los votos de un candidato");
        return total;
    }

    public List<Lugar> getDepartamentos() {
        LOGGER.log(Level.INFO, "Inicia proceso de contar todos los departamentos");
        List<Lugar> lugares = persistence.getDepartamentos();
        for (Lugar lugar : lugares) {
            lugar.setBusqueda(busquedaPersistence.findByBusqueda(lugar.getNombreLugar()).getRespuesta());
        }
        LOGGER.log(Level.INFO, "Termina proceso de contar todos los departamentos");
        return lugares;
    }

    public List<Lugar> getMunicipios() {
        LOGGER.log(Level.INFO, "Inicia proceso de contar todos los municipios");
        List<Lugar> lugares = persistence.getMunicipios();
        for (Lugar lugar : lugares) {
            lugar.setBusqueda(busquedaPersistence.findByBusqueda(lugar.getNombreLugar()).getRespuesta());
        }
        LOGGER.log(Level.INFO, "Termina proceso de contar todos los municipios");
        return lugares;
    }
}
