/**
 * 
 */
package com.moustercorp.app.services;

/**
 * Define las operaciones para persisitir... algo.
 * @author jbatis
 */
public interface PersistService<T> {

	/**
	 * Permite persistir la información solicitada.
	 * @param entity
	 */
	public void doPersist(T entity);
	
}
