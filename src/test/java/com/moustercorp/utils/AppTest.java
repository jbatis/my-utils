package com.moustercorp.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Before;
import org.junit.Test;

import com.moustercorp.app.model.DireccionModel;
import com.moustercorp.app.model.EmpleadoModel;
import com.moustercorp.app.model.FormModel;
import com.moustercorp.app.services.Empleado;
import com.moustercorp.app.services.PersistEmpleado;
import com.moustercorp.app.services.PersistService;

/**
 * Pruebas de API para commons beans utils
 * @author jbatis
 */
public class AppTest {

	/**
	 * mi simple logger
	 */
	private Logger log = Logger.getLogger("AppTest");
	
	/**
	 * Modelo a utilizar
	 */
	private FormModel model;
	
	/**
	 * Servicio a exponer
	 */
	PersistService<Empleado> service;
	
    /**
     * Simulamos el llenado de la info de la GUI
     */
    @Before
    public void setFormModel() {
    	log.info("Simulamos la entrada de la vista...");
    	model = new FormModel();
    	model.setAccion(FormModel.ACCION.ALTA);
    	model.setHoraSolicitud(LocalTime.now());
    	
    	DireccionModel direccionModel = new DireccionModel();
    	direccionModel.setEstado("CIUDAD DE MÉXICO");
    	direccionModel.setMunicipio("CUAUHTEMOC");
    	direccionModel.setCalle("CALLE CENTRO");
    	direccionModel.setNumero("25 A");
    	direccionModel.setCp("45651");
    	model.setDireccion(direccionModel);
    	
    	EmpleadoModel empleadoModel = new EmpleadoModel();
    	empleadoModel.setNombre("JUAN");
    	empleadoModel.setNumEmpleado(240319);
    	empleadoModel.setApellidoPaterno("BUATISTA");
    	empleadoModel.setFechaNacimiento(LocalDateTime.now().withMonth(7).withDayOfMonth(19).withYear(1982));
    	model.setEmpleado(empleadoModel);
    	
    	//instanciamos servicio
    	service = new PersistEmpleado();
    }
    
    /**
     * <p>Simulamos en esta prueba la accion del controller.</p>
     * 
	 * <p>El objeto model, que hace referencia a los datos de la vista, se solicita que se persista:
	 * Sin embargo el servicio de persistencia tiene su propio objeto que expone para realizar su tabajo.<br>
	 * En este caso el objeto {@link Empleado}, nada que ver con:
	 * {@link FormModel} que viene de la vista.</p>
	 * 
	 * <p>Normalmente tendriamos que ir campo por campo y hacer uso de sus seters y geters de cada clase
	 * para ir llenando la información, actividad que se hace muy tardada, tediosa y dolosa cuando hablamos de 
	 * muchos campos, sin hablar del mantenimiento, ya sea cuando se borra o se agrega un campo, o más.</p>
	 * 
	 * <p>Aqui viene el uso de commos beans utils</p>
	 */
    @Test
    public void doPersist() {
    	log.info(String.format("El valor del form, a partir de la GUI es: %s", model.toString()));
    	log.info("accion a persist..." + model.getAccion());
    	try {
    		Empleado empleado = new Empleado();
			BeanUtils.copyProperties(empleado, getModel().getDireccion());
	    	log.info(String.format("El valor del empleado, al setear la info de la direccion: %s", empleado));
	    	BeanUtils.copyProperties(empleado, getModel().getEmpleado());
	    	log.info(String.format("El valor del empleado, al setear la info del empleado: %s", empleado));
	    	//llamamos al servicio
	    	getService().doPersist(empleado);
		} catch (IllegalAccessException e) {
			log.warning("ERROR, ACCESO ILEGAL AL TRATAR DE COPIAR LAS PROPIEDADES DEL BEAN: " + e.getMessage());
		} catch (InvocationTargetException e) {
			log.warning("ERROR, AL TRATAR DE INVOCAR EL OBJECTO SOBRE AL QUE SE COPIA LAS PROPIEDADES DEL BEAN: " + e.getMessage());
		}
    }
    
    /**
     * @return el moedlo que hace referencia a la información de la vista.
     */
    private FormModel getModel() {
		return model;
	}
 
    /**
     * @return service
     */
    private PersistService<Empleado> getService() {
		return service;
	}
}
