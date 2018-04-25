/**
 * 
 */
package com.moustercorp.app.model;

import java.time.LocalDateTime;

/**
 * Modelamos los datos del empleado a la vista.
 * @author jbatis
 */
public class EmpleadoModel {

    private String nombre;
    private String apellidoPaterno;
    
    private int numEmpleado;
    private LocalDateTime fechaNacimiento;
    
	public EmpleadoModel() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
