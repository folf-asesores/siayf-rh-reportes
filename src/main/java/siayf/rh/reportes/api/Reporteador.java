/*
 * Reporteador.java
 * Creado el 29/sep/2017 10:31:49 AM
 * 
 */

package siayf.rh.reportes.api;

import java.io.Serializable;

/**
 *
 * @author Freddy Barrera
 */
public interface Reporteador extends Serializable {

    byte [] generarReporte(Object ... parametros);
}
