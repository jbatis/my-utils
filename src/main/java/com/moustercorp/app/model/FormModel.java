/**
 * 
 */
package com.moustercorp.app.model;

import java.io.IOException;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Esta clase es la que detallamos y/o exponemos hacia la vista.
 * @author jbatis
 */
public class FormModel {

	public enum ACCION {
		ALTA, EDICION
	}
	
    private ACCION accion;
    private LocalTime horaSolicitud;
    private DireccionModel direccion;
    private EmpleadoModel empleado;
    
	public FormModel() {
		super();
	}

	public ACCION getAccion() {
		return accion;
	}

	public void setAccion(ACCION accion) {
		this.accion = accion;
	}

	public LocalTime getHoraSolicitud() {
		return horaSolicitud;
	}

	public void setHoraSolicitud(LocalTime horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}

	public DireccionModel getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionModel direccion) {
		this.direccion = direccion;
	}

	public EmpleadoModel getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoModel empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (IOException e) {
			return StringUtils.EMPTY;
		} 
	}
	
}
