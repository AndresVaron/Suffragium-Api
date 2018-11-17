/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.VotoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Equipo Suffragium
 */
public class VotoDTO implements Serializable {

    private Long idCandidato;
    private String municipio;
    private String departamento;

    public VotoDTO() {

    }

    public VotoEntity toEntity() {
        VotoEntity entity = new VotoEntity();
        entity.setDepartamento(this.getDepartamento());
        entity.setIdCandidato(this.getIdCandidato());
        entity.setMunicipio(this.getMunicipio());
        return entity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the idCandidato
     */
    public Long getIdCandidato() {
        return idCandidato;
    }

    /**
     * @param idCandidato the idCandidato to set
     */
    public void setIdCandidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
