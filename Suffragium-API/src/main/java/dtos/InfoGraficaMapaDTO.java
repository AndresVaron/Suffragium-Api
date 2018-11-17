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
    private Integer numvotos;

    public InfoGraficaMapaDTO() {

    }

    public InfoGraficaMapaDTO(String nombreLugar, Integer numvotos) {
        this.nombreLugar = nombreLugar;
        this.numvotos = numvotos;
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

    /**
     * @return the numvotos
     */
    public Integer getNumVotos() {
        return numvotos;
    }

    /**
     * @param numvotos the numvotos to set
     */
    public void setNumVotos(Integer numvotos) {
        this.numvotos = numvotos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
