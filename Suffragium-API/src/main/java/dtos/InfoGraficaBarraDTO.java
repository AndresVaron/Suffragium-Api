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
public class InfoGraficaBarraDTO implements Serializable {

    private String nombreCandidato;
    private Double porcentaje;

    public InfoGraficaBarraDTO() {

    }

    public InfoGraficaBarraDTO(String nombreCandidato, Double porcentaje) {
        this.nombreCandidato = nombreCandidato;
        this.porcentaje = porcentaje;
    }

    /**
     * @return the nombreCandidato
     */
    public String getNombreCandidato() {
        return nombreCandidato;
    }

    /**
     * @param nombreCandidato the nombreCandidato to set
     */
    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    /**
     * @return the porcentaje
     */
    public Double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
