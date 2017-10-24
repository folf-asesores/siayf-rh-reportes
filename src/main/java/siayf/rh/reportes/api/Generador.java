/*
 * Generador.java
 * Creado el 9/Sep/2016 1:37:04 PM
 * 
 */

package siayf.rh.reportes.api;

import java.io.Serializable;
import java.util.Map;

/**
 * Esta interfaz describe el mecanismo para generar el reporte.
 * 
 * @author Freddy Barrera
 * @author Eduardo Mex
 */
public interface Generador extends Serializable {

    /**
     * Permite generar el reporte con los parámetros requeridos.
     * 
     * @param parametros los parametros del reporte.
     * @return si el reporte se genera correctamente devuelve un arreglo de 
     * bytes que representa el reporte.
     */
    byte[] obtenerReporte(Map<String, String> parametros);
}
