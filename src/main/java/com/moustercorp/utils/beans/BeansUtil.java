/**
 * 
 */
package com.moustercorp.utils.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.lang3.StringUtils;

import com.moustercorp.exceptions.InitializationException;

/**
 * API que desarrolle para extender las bondades del paquete de org.apache.commons.beanutils.
 * @author jbatis
 */
public final class BeansUtil {

	/**
	 * Logger del aplicativo
	 */
	private static final Logger log = Logger.getLogger("BeansUtil");

	
	/**
	 * Constructor sin argumentos
	 */
	private BeansUtil() {
		super();
	}

	/**
	 * Metodo que permite generar un bean en tiempo de ejecución; esto conforme al arreglo de campos 
	 * que le envie el usuario.
	 * Es decir, se envia en la lista el nobre de las propiedades (variables) del nuevo bean.
	 * Se recomienda utilizarlo cuando todas las propiedades del bean sean {@link String} unicamente, ya que solo
	 * soporta propiedades de tipo {@link String}
	 * @param properties lista con el nombre de propiedades del bean
	 * @return bean con propiedades de tipo String
	 * @throws InitializationException error al tratar de generar el bean.
	 */
	public static final DynaBean getDinamicBean(String... properties) throws InitializationException {
		DynaBean bean = null;
		final int sizeParams = properties.length;
		if(sizeParams == 0) {
			log.warning("Los datos del bean estan vacios, ya que no existen parametros en el request.");
			throw new InitializationException("NO ES POSIBLE OBTENER EL BEAN DINAMICO, PARAMETROS VACIOS.");
		} 
		try {
			DynaProperty[] beanProperties = new DynaProperty[sizeParams];
			int locate = 0;
			for (String property : properties) {
				beanProperties[locate++] = new DynaProperty(property, String.class);
			}
			BasicDynaClass dynaClass = new BasicDynaClass("DinamicBean", BasicDynaBean.class, beanProperties);
			bean = dynaClass.newInstance();			
		} catch (IllegalAccessException e) {
			log.warning("Error al generar el bean dinamico, motivo: " + e.getMessage());
			throw new InitializationException("NO ES POSIBLE GENERAR EL BEAN DINAMICO.");
		} catch (InstantiationException e) {
			log.warning("Error al generar el bean dinamico, motivo: " + e.getMessage());
			throw new InitializationException("NO ES POSIBLE GENERAR EL BEAN DINAMICO.");
		}
		return bean;
	}

	/**
	 * Permite generar un bean en tiempo de ejecución a partir de un {@link Map}, ejemplo:</br>
	 * {@link Map}<{@link String}, {@link Class} < ? >> properties = new {@link HashMap}<{@link String}, {@link Class} < ? >>();</br>
	 *  properties.put("nombre", String.class);</br>
	 *  properties.put("id", Integer.class);</br>
	 *  properties.put("fecha", Date.class);</br>
	 *  properties.put("estatus", Estatus.class);</br>
	 *  try { </br>
	 *  	DynaBean bean = BeansUtil.getDinamicBean(null, properties); </br>
	 *  } catch (InitializationException e) { </br>
	 *  	fail(e.getMessage()); </br>
	 *  }</br>
	 * @param nameBean nombre del bean, por default lleva como nombre 'DinamicBean'
	 * @param properties lista de las propiedades a instanciar dentro del objeto {@link Map}<{@link String}, {@link Class<?>}> 
	 * en donde el key es el nombre de la propiedad y el value es el tipo de dato, en este caso la clase.
	 * @return {@link DynaBean} generado.
	 * @throws InitializationException error en la generación del bean.
	 */
	public static final DynaBean getDinamicBean(String nameBean, final Map<String, Class<?>> properties) 
	throws InitializationException {
		DynaBean bean = null;
		final int sizeParams = properties.size();
		if(sizeParams == 0) {
			log.warning("Los datos del bean estan vacios, ya que no existen parametros en el request.");
			throw new InitializationException("NO ES POSIBLE OBTENER EL BEAN DINAMICO, PARAMETROS VACIOS.");
		}
		Iterator<Entry<String, Class<?>>> propertiesBean = properties.entrySet().iterator();
		if (propertiesBean.hasNext()) {
			nameBean = StringUtils.isBlank(nameBean) ? "DinamicBean" : nameBean;
			DynaProperty[] beanProperties = new DynaProperty[sizeParams];
			int locate = 0;
			do {
				Entry<String, Class<?>> entry = propertiesBean.next(); 
				String property = entry.getKey();
				Class<?> typeClass = entry.getValue();
				beanProperties[locate++] = new DynaProperty(property, typeClass);
			} while (propertiesBean.hasNext());
			try {
				BasicDynaClass dynaClass = new BasicDynaClass(nameBean, BasicDynaBean.class, beanProperties);
				bean = dynaClass.newInstance();
			} catch (IllegalAccessException e) {
				log.warning("Error al generar el bean dinamico, motivo: " + e.getMessage());
				throw new InitializationException("NO ES POSIBLE GENERAR EL BEAN DINAMICO.");
			} catch (InstantiationException e) {
				log.warning("Error al generar el bean dinamico, motivo: " + e.getMessage());
				throw new InitializationException("NO ES POSIBLE GENERAR EL BEAN DINAMICO.");
			}
		}
		return bean;
	}

	/**
	 * Nos permite imprimir la información del DynaBean
	 * @param beanParams de tipo {@link DynaBean}
	 */
	public static void showInfoDynaBean(DynaBean beanParams) {
		if (beanParams != null) {
			DynaClass dynaClass = beanParams.getDynaClass();
			DynaProperty[] dynaProperty =  dynaClass.getDynaProperties();
			for (int i = 0; i < dynaProperty.length; i++ ) {
				String infoProperty = new StringBuilder("Property [")
				.append(dynaProperty[i].getName())
				.append("] Set Value [").
				append(beanParams.get(dynaProperty[i].getName()))
				.append("]").toString();
				log.info(infoProperty);
			}
		} else {
			log.warning("EL BEAN A TRATAR DE LEER ESTA NULO.");
		}
	}


	/**
	 * Detalla las propiedades y su valor del objeto, que en este caso debe de ser de tipo {@link DynaBean}
	 * @param beanParams beanParams de tipo {@link DynaBean}
	 * @return detalle del beanParams {@link DynaBean}
	 */
	public static String getInfoDynaBean(DynaBean beanParams) {
		StringBuilder infoDynaBean = new StringBuilder(StringUtils.EMPTY);
		DynaClass dynaClass = beanParams.getDynaClass();
		DynaProperty[] dynaProperty =  dynaClass.getDynaProperties();
		for (int i = 0; i < dynaProperty.length; i++ ) {
			String infoProperty = new StringBuilder("Property [")
			.append(dynaProperty[i].getName())
			.append("] Set Value [").
			append(beanParams.get(dynaProperty[i].getName()))
			.append("]").toString();
			log.info(infoProperty);
			infoDynaBean.append(infoProperty);
			infoDynaBean.append("/n");
		}	
		return infoDynaBean.toString();
	}
	
	/**
	 * Permite realizar la copia de un bean a otro.
	 * @param dest bean destino
	 * @param orig bean origen
	 * @throws InitializationException error al tratar de realizar el proceso.
	 */
	public static void doCopyProperties(Object dest, Object orig) throws InitializationException {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			log.warning("ERROR, ACCESO ILEGAL AL TRATAR DE COPIAR LAS PROPIEDADES DEL BEAN: " + e.getMessage());
			throw new InitializationException("ERROR, ACCESO ILEGAL AL TRATAR DE COPIAR LAS PROPIEDADES DEL BEAN.");
		} catch (InvocationTargetException e) {
			log.warning("ERROR, AL TRATAR DE INVOCAR EL OBJECTO SOBRE AL QUE SE COPIA LAS PROPIEDADES DEL BEAN: " + e.getMessage());
			throw new InitializationException("ERROR, AL TRATAR DE INVOCAR EL OBJECTO SOBRE AL QUE SE COPIA LAS PROPIEDADES DEL BEAN.");
		}
	}

	/**
	 * Regresa el las propiedades del bean, en un mapa
	 * @param bean Objeto a inspeccionar
	 * @return Map<String, Object>
	 * @throws InitializationException error al tratar de obtener las propiedades del bean.
	 */
	public static Map<String, String> toMapObject(Object bean) throws InitializationException {
		Map<String, String> mapObject = null;
		try {
			if (bean == null) {
				return null;
			}
			mapObject = BeanUtils.describe(bean);
		} catch (IllegalAccessException e) {
			throw new InitializationException("NO ES POSIBLE INICIALIZAR LOS PARAMETROS IllegalAccessException: " + e.getMessage());
		} catch (InvocationTargetException e) {
			throw new InitializationException("NO ES POSIBLE INICIALIZAR LOS PARAMETROS InvocationTargetException: " + e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new InitializationException("NO ES POSIBLE INICIALIZAR LOS PARAMETROS NoSuchMethodException: " + e.getMessage());
		}
		return mapObject;
	}
	
	public static Object getObjectFromBean(DynaBean params, String value) {
		try {
			return params.get(value);
		} catch (Exception e) {
			return null;
		}
	}
	

	public static String getValueFromBean(DynaBean params, String value) {
		Object object = getObjectFromBean(params, value);
		return object == null ? StringUtils.EMPTY : (String) object;
	}

}
