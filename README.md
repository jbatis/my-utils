# my-utils - com.moustercorp.utils.beans.BeansUtil
	
Se detallan las utilizades que se exponen en la clase:
**com.moustercorp.utils.beans.BeansUtil**
  
**_DynaBean getDinamicBean(String nameBean, final Map<String, Class<?>> properties)_**

Permite generar un bean en tiempo de ejecución a partir de un Map, ejemplo:

```
public static final DynaBean getBeanSearchCriteria() throws InitializationException {
  Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
  properties.put("instancia", Integer.class);
  properties.put("numEmpleado", Long.class);
  properties.put("fecha", Date.class);
  properties.put("razonComercial", String.class);
  try {
    return BeansUtil.getDinamicBean("BeanSearchCriteria", properties);
  } catch (InitializationException e) {
    throw new InitializationException("PARAMENTROS NO SOPORTADOS PARA GENERAR EL BEAN CON EL CRITERIA NECESARIO.");
  }
}
```


**_DynaBean getDinamicBean(String... properties)_**

Permite generar un bean en tiempo de ejecución; esto conforme al arreglo de campos que le envie el usuario, ejemplo:
```
public static final DynaBean getBeanSimpleParamsConfigMail() throws InitializationException {
  final String[] properties = {ATTRIBUTE_TO, ATTRIBUTE_SUBJECT, ATTRIBUTE_TEXT}; 
  return BeansUtil.getDinamicBean(properties);
}
```

**_doCopyProperties(Object dest, Object orig)_**

Permite realizar la copia de parametros de un bean a otro, ejemplo: 

```
public void doPersist(EmpleadoForm empleadoForm) {
  try {
    Empleado empleado = new Empleado();
    BeansUtil.doCopyProperties(empleado, empleadoForm);
    log.info(String.format("El valor del empleado, al setear la info de la direccion: %s", empleado));
  } catch (InitializationException e) {
    log.warning("ERROR, ACCESO ILEGAL AL TRATAR DE COPIAR LAS PROPIEDADES DEL BEAN: " + e.getMessage());
  } 
}
```

**_Map<String, String> toMapObject(Object bean)_** 

Permite regresa el las propiedades del bean en un mapa, ejemplo:
```
public Map<String, String> toMapObject() throws InitializationException {
	return BeansUtil.toMapObject(this);
}
```



