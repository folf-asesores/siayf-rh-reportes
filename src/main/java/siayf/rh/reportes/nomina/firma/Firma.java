/*
 * Firma.java
 * Creado el 11/sep/2017 11:05:32 AM
 * 
 */

package siayf.rh.reportes.nomina.firma;

import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Firma extends Serializable {

    byte [] generarReporte(final Integer idProductoNomina);

}
