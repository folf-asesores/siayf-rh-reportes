/*
 * Generador.java
 * Creado el 9/sep/2016 1:37:04 PM
 * 
 */

package siayf.rh.reportes.api;

import java.io.Serializable;
import java.util.Map;

/**
 * Esta interfaz describe el mecanismo para generar el reporte.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 * @author Eduardo Chuc Mex (Lic.Eduardo_Mex@hotmail.com)
 */
public interface Generador extends Serializable {

    /**
     * Permite generar el reporte con los parámetros requeridos.
     * 
     * @param parametros los parametros del reporte.
     * @return un objecto que contiene la información del reporte además del
     * descripción del mismo.
     */
    Archivo obtenerReporte(Map<String, String> parametros);
}
