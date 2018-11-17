/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Equipo Suffraigum
 */
public class Lugar {

    private String nombreLugar;

    private Integer numvotos;

    public Lugar(String nombreLugar, int numVotos) {
        this.nombreLugar = nombreLugar;
        this.numvotos = numVotos;
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
        return getNumvotos();
    }

    /**
     * @param numvotos the numvotos to set
     */
    public void setNumVotos(Integer numvotos) {
        this.setNumvotos(numvotos);
    }

    /**
     * @return the numvotos
     */
    public Integer getNumvotos() {
        return numvotos;
    }

    /**
     * @param numvotos the numvotos to set
     */
    public void setNumvotos(Integer numvotos) {
        this.numvotos = numvotos;
    }
}
