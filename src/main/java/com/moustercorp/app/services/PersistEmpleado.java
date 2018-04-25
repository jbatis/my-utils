/**
 * 
 */
package com.moustercorp.app.services;

import java.util.logging.Logger;

/**
 * @author jbatis
 */
public class PersistEmpleado implements PersistService<Empleado> {

	/**
	 * mi simple logger
	 */
	private Logger log = Logger.getLogger("AppTest");
	
	/**
	 * Constructor sin argumentos
	 */
	public PersistEmpleado() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.moustercorp.app.services.PersistService#doPersist(java.lang.Object)
	 */
	public void doPersist(Empleado entity) {
		if (entity.getId() == 0) {
			log.info(String.format("SAVE empleado: %s", entity));
		} else {
			log.info(String.format("UPDATE empleado: %s", entity));
		}
		
	}
	
}
