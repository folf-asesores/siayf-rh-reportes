/*
 * Prenomina.java
 * Creado el 11/jul/2017 6:51:06 PM
 *
 */

package siayf.rh.reportes.nomina.prenomina;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Prenomina {

    byte[] generarReporte(Integer idProductoNomina);
}
