/**
 * 
 */
package com.moustercorp.app.model;

/**
 * Permite detallar la dirección hacia el usuario en la GUI
 * @author jbatis
 */
public class DireccionModel {

    private String calle;
    private String numero;
    private String cp;
    private String estado;
    private String municipio;
	
	public DireccionModel() {
		super();
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
}
