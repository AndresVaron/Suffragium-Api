/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Equipo Suffragium
 */
public class InfoGraficaMapaDTO implements Serializable {

    private String nombreLugar;
    private Integer numVotos;
    private String busqueda;

    public InfoGraficaMapaDTO() {

    }

    public InfoGraficaMapaDTO(String nombreLugar, Integer numvotos) {
        this.nombreLugar = nombreLugar;
        this.numVotos = numvotos;
    }

    /**
     * @return the nombreLugar
     */
    public String getNombreLugar() {
        return nombreLugar;
    }

    /**
     * @param nombreLugar the nombreLugar to set
     */
    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the busqueda
     */
    public String getBusqueda() {
        return busqueda;
    }

    /**
     * @param busqueda the busqueda to set
     */
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    /**
     * @return the numVotos
     */
    public Integer getNumVotos() {
        return numVotos;
    }

    /**
     * @param numVotos the numVotos to set
     */
    public void setNumVotos(Integer numVotos) {
        this.numVotos = numVotos;
    }
}
